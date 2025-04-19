package org.example.controller;

import org.example.service.BasketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/basket")
public class BasketController
{
    @Autowired
    BasketService basketService;

    /// Добавить товар в корзину
    @GetMapping(value = "/add", params = {"productId", "userId"})
    public ResponseEntity<?> addToBasketById(@RequestParam Integer productId, @RequestParam Integer userId)
    {
       return basketService.addToBasketById(productId, userId);
    }

    /// Удалить товар из корзины
    @GetMapping(value = "/remove", params = {"productId", "userId"})
    public ResponseEntity<?> removeFromBasketByID(@RequestParam Integer productId, @RequestParam Integer userId)
    {
        return basketService.removeFromBasketByID(productId, userId);
    }

    /// Получить всю корзину
    @GetMapping(value = "/get", params = {"userId"})
    public ResponseEntity<?> getBasket( @RequestParam Integer userId)
    {
        return basketService.getBasket(userId);
    }
}
