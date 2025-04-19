package org.example.controller;

import org.example.service.BuyingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/// port 8082 -
/// Этот контроллер для овормления заказов
@Controller
@RequestMapping("/buy")
public class BuyingController
{
    @Autowired
    BuyingService buyingService;

    @GetMapping(value = "/all", params = "userId")
    public ResponseEntity<?> buyAllBasket(@RequestParam Integer userId)
    {
        return buyingService.buyAllBasket(userId);
    }

}
