package org.example.controller;

import org.example.service.ClothingBuyingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/// Port 8081 -
/// Контроллер покупок
@RestController
@RequestMapping("/buy")
public class ClothingBuyingController
{
    @Autowired
    ClothingBuyingService clothingBuyingService;

    ///  Купить много товаров
    @GetMapping(value = "/many", params = "ids")
    public ResponseEntity<?> buyManyClothing(@RequestParam List<Integer> ids)
    {
        return clothingBuyingService.buyManyClothing(ids);
    }
}
