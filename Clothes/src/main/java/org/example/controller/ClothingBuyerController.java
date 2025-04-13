package org.example.controller;

import org.example.service.ClothingBuyerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/// Port 8081 -
/// Контроллер покупок
@RestController
@RequestMapping("/buy")
public class ClothingBuyerController
{
    @Autowired
    ClothingBuyerService clothingBuyerService;
    
    /// Совершить покупку по id
    @GetMapping("/{id}")
    public ResponseEntity<?> buyClothingById(@PathVariable int id)
    {
        return clothingBuyerService.buyClothingById(id);
    }
}
