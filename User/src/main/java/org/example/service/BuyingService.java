package org.example.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class BuyingService
{
    @Autowired
    RestTemplate restTemplate;

    @Value("${order.url}")
    String orderUrl;

    ///  Добавить товар в корзину
    public ResponseEntity<?> addToBasketById(int productId)
    {
        if(AuntService.auntPerson == null)
        {
            return ResponseEntity.badRequest().body("Для начала  войдите в систему");
        }

        String url = UriComponentsBuilder
                .fromHttpUrl(String.format("http://%s/basket/add", orderUrl))
                .queryParam("productId", productId)
                .queryParam("userId", AuntService.auntPerson.getId())
                .toUriString();

        String answer = restTemplate.getForObject(url, String.class);

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(answer);
    }

    /// Удалить товар из корзины
    public ResponseEntity<?> removeFromBasketByID(int productId)
    {
        if(AuntService.auntPerson == null)
        {
            return ResponseEntity.badRequest().body("Для начала  войдите в систему");
        }

        String url = UriComponentsBuilder
                .fromHttpUrl(String.format("http://%s/basket/remove", orderUrl))
                .queryParam("productId", productId)
                .queryParam("userId", AuntService.auntPerson.getId())
                .toUriString();

        String answer = restTemplate.getForObject(url, String.class);

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(answer);
    }

    /// Получить корзину
    public ResponseEntity<?> getBasketById()
    {
        if(AuntService.auntPerson == null)
        {
            return ResponseEntity.badRequest().body("Для начала  войдите в систему");
        }

        String url = UriComponentsBuilder
                .fromHttpUrl(String.format("http://%s/basket/get", orderUrl))
                .queryParam("userId", AuntService.auntPerson.getId())
                .toUriString();

        String answer = restTemplate.getForObject(url, String.class);

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(answer);
    }

    ///  Выкупить товар из корзины по id
    public ResponseEntity<?> buyFromBasketById(int id)
    {
        if(AuntService.auntPerson == null)
        {
            return ResponseEntity.badRequest().body("Для начала  войдите в систему");
        }

        String url = UriComponentsBuilder
                .fromHttpUrl(String.format("http://%s/buy", orderUrl))
                .queryParam("userId", AuntService.auntPerson.getId())
                .queryParam("productId", id)
                .toUriString();

        String answer = restTemplate.getForObject(url, String.class);

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(answer);
    }

    ///  Купить всю корзину
    public ResponseEntity<?> buyAllBasket()
    {
        if(AuntService.auntPerson == null)
        {
            return ResponseEntity.badRequest().body("Для начала  войдите в систему");
        }

        String url = UriComponentsBuilder
                .fromHttpUrl(String.format("http://%s/buy/all", orderUrl))
                .queryParam("userId", AuntService.auntPerson.getId())
                .toUriString();

        String answer = restTemplate.getForObject(url, String.class);

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(answer);
    }
}
