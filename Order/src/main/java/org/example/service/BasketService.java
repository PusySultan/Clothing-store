package org.example.service;

import jakarta.transaction.Transactional;
import org.example.model.Clothing;
import org.example.model.Order;
import org.example.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class BasketService
{
    @Autowired
    OrderRepository orderRepository;

    @Autowired
    RestTemplate restTemplate;

    @Value("${clothing.url}")
    String clothingUrl;


    /// Добавить товар в корзину
    @Transactional
    public ResponseEntity<?> addToBasketById(int productId, int userId)
    {
        String url = UriComponentsBuilder
                .fromHttpUrl(String.format("http://%s/find/" + productId, clothingUrl))
                .toUriString();

        Clothing dto = restTemplate.getForObject(url, Clothing.class);

        if(dto == null)
        {
            return ResponseEntity.ok().body ("Такого товара не сущетвует");
        }

        Clothing clothing = new Clothing();

        clothing.setProductId(productId);
        clothing.setType(dto.getType());
        clothing.setBrand(dto.getBrand());
        clothing.setDescription(dto.getDescription());
        clothing.setCost( dto.getCost());
        clothing.setQuantity(dto.getQuantity());

        if(orderRepository.existsById(userId))
        {
            Order order = orderRepository.findById(userId).get();
            order.AddClothing(clothing);
            return new ResponseEntity<>(orderRepository.save(order), HttpStatus.OK);
        }
        else
        {
            Order order = new Order(userId, LocalDateTime.now(), clothing);
            orderRepository.save(order);
            return new ResponseEntity<>(order, HttpStatus.OK);
        }
    }

    ///  Удалить товар из корзины
    @Transactional
    public ResponseEntity<?> removeFromBasketByID(Integer productId, Integer userId)
    {
        if(!orderRepository.existsById(userId))
        {
            return ResponseEntity.ok().body("Ваша корзина пуста");
        }

        Order order = orderRepository.findById(userId).get();
        int position = order.getClothingPosition(productId);

        if(position == -1)
        {
            return ResponseEntity.ok().body("В вашей корзине нет такого товара");
        }

        order.getClothingList().remove(position);
        return new  ResponseEntity<>(orderRepository.save(order), HttpStatus.OK);
    }

    /// Получить корзину
    public ResponseEntity<?> getBasket(Integer userId)
    {
        if(!orderRepository.existsById(userId))
        {
            return ResponseEntity.ok().body("Ваша корзина еще не существует");
        }

        return ResponseEntity.ok().body(orderRepository.findById(userId));
    }
}
