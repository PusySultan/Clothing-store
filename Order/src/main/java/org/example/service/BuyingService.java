package org.example.service;

import jakarta.transaction.Transactional;
import org.example.model.Order;
import org.example.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.*;

@Service
public class BuyingService
{
    @Autowired
    OrderRepository orderRepository;

    @Autowired
    RestTemplate restTemplate;

    @Value("${clothing.url}")
    String clothingUrl;

    ///  Купить товар по id
    @Transactional
    public ResponseEntity<?> buyFromBasketById(Integer userId, Integer productId)
    {
        if(!orderRepository.existsById(userId))
        {
            return ResponseEntity.ok().body("Ваша корзина еще не создана");
        }

        Order order = orderRepository.findById(userId).get();

        if(!order.getAllId().contains(productId))
        {
            return ResponseEntity.ok().body("В вашей корзине нет товара с таким id");
        }

        String url = UriComponentsBuilder
                .fromHttpUrl(String.format("http://%s/buy", clothingUrl))
                .queryParam("productId", productId)
                .toUriString();

        String answer = restTemplate.getForObject(url, String.class);

        if(Objects.equals(answer, "Покупка совершена"))
        {
            order.getClothingList().removeIf(clothing -> productId == clothing.getProductId());
            orderRepository.save(order);
        }

        return ResponseEntity.ok().body(answer);
    }

    /// Выкупить всю корзину
    @Transactional
    public ResponseEntity<?> buyAllBasket(Integer userId)
    {
       if(!orderRepository.existsById(userId))
       {
           return ResponseEntity.ok().body("Ваша корзина еще не создана");
       }

       // Получаем список id сех товаров пользователя
       Order order = orderRepository.findById(userId).get();
       List<Integer> ids = order.getAllId();

       if(ids.isEmpty())
       {
           return ResponseEntity.ok().body("Ваша корзина пуста");
       }

       String url = UriComponentsBuilder
               .fromHttpUrl(String.format("http://%s/buy/many", clothingUrl))
               .queryParam("ids", ids)
               .toUriString();

       HashMap<String, Integer> response = restTemplate.getForObject(url, HashMap.class);

       if (response == null)
       {
           return ResponseEntity.ok().body("Ошибка покупки");
       }

       StringBuilder answer = new StringBuilder(" ");

       // Проверяем какой товар получилось купить, а какой нет
       for (Map.Entry<String, Integer> entry : response.entrySet())
       {
          if(Objects.equals(entry.getKey(), "ok"))
          {
              int index = order.getClothingPosition(entry.getValue());
              order.getClothingList().remove(index);

              answer.append("Куплен товар: ");
              answer.append(entry.getValue());
              continue;
          }

          answer.append("Товар: ");
          answer.append(entry.getValue());
          answer.append(" ");
          answer.append("не куплен");
       }

       orderRepository.save(order);
       return ResponseEntity.ok().body(answer);
    }
}
