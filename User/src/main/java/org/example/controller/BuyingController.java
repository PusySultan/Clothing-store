package org.example.controller;

import org.example.service.BuyingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


/// Port 8080 -
/// Этот контроллер оформления заказов
@RestController
@RequestMapping("/person")
public class BuyingController
{
    @Autowired
    BuyingService personBuyingService;

    /// Добавить товар в корзину ао id
    @GetMapping("/basket/add/{id}")
    public ResponseEntity<?> addToBasketById(@PathVariable int id)
    {
        return personBuyingService.addToBasketById(id);
    }

    /// Удалить товар из корзины по id
    @GetMapping("/basket/remove/{id}")
    public  ResponseEntity<?> removeFromBasketByID(@PathVariable int id)
    {
        return personBuyingService.removeFromBasketByID(id);
    }

    /// Просмотр корзины
    @GetMapping("/basket/get")
    public  ResponseEntity<?> getBasketById()
    {
        return personBuyingService.getBasketById();
    }

    /// Выкупить товар из корзины по id
    @GetMapping("/basket/buy/{id}")
    public  ResponseEntity<?> buyFromBasketById(@PathVariable int id)
    {
        return personBuyingService.buyFromBasketById(id);
    }

    /// Выкупить всю корзину
    @GetMapping("/basket/buy/all")
    public  ResponseEntity<?> buyAllBasket()
    {
        return personBuyingService.buyAllBasket();
    }
}
