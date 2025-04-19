package org.example.controller;

import org.example.service.BuyingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/// port 8080 -
/// Этот контроллер оформления заказов
@RestController
@RequestMapping("/person")
public class BuyingController
{
    @Autowired
    BuyingService personBuyingService;

    @GetMapping("/basket/add/{id}")
    public ResponseEntity<?> addToBasketById(@PathVariable int id)
    {
        return personBuyingService.addToBasketById(id);
    }

    @GetMapping("/basket/remove/{id}")
    public  ResponseEntity<?> removeFromBasketByID(@PathVariable int id)
    {
        return personBuyingService.removeFromBasketByID(id);
    }

    @GetMapping("/basket/get")
    public  ResponseEntity<?> getBasketById()
    {
        return personBuyingService.getBasketById();
    }

    @GetMapping("/basket/buy/all")
    public  ResponseEntity<?> buyAllBasket()
    {
        return personBuyingService.buyAllBasket();
    }
}
