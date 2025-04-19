package org.example.service;

import jakarta.transaction.Transactional;
import org.example.model.Order;
import org.example.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@Service
public class BuyingService
{
    @Autowired
    OrderRepository orderRepository;

    @Autowired
    RestTemplate restTemplate;

    @Value("${clothing.url}")
    String clothingUrl;


    /// Выкупить всю корзину
    @Transactional
    public ResponseEntity<?> buyAllBasket(Integer userId)
    {
        /// todo buy in Clothing service
        Order order = orderRepository.findById(userId).get();
        List<Integer> ids = order.getAllId();

        String url = UriComponentsBuilder
                .fromHttpUrl(String.format("http://%s/buy/many", clothingUrl))
                .queryParam("ids", ids)
                .toUriString();

        String answer = restTemplate.getForObject(url, String.class);

        /// todo delete all in product in repository
        order.deleteAllProduct();
        orderRepository.save(order);

        return ResponseEntity.ok().body(answer);
    }
}
