package org.example.controller;

import org.example.service.ClothingFindService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/// Port 8081 - 
/// Контроллер поиска одежды
@RestController
@RequestMapping("/find")
public class ClothingFindController
{
    @Autowired
    ClothingFindService clothingFindService;
    
    // Фильтрация товаров по ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getClothingById(@PathVariable int id)
    {
        return clothingFindService.getClothingById(id);
    }

    /// Фильтрация товаров по типу
    @GetMapping(params = "type")
    public ResponseEntity<?> getClothingByType(@RequestParam String type)
    {
        return clothingFindService.getClothingByType(type);
    }

    /// Фильтрация товаров по бренду
    @GetMapping(params = "brand")
    public ResponseEntity<?> getClothingByBrand(@RequestParam String brand)
    {
        return clothingFindService.getClothingByBrand(brand);
    }

    /// Фильтрация по максимальной стоимости
    @GetMapping(params = "maxCost")
    public ResponseEntity<?> getClothingByMaxCost(double maxCost)
    {
        return clothingFindService.getClothingByMaxCost(maxCost);
    }

    /// Фильтрация по минимальной стоимости
    @GetMapping(params = "minCost")
    public ResponseEntity<?> findClothingByMinCost(double minCost)
    {
        return clothingFindService.getClothingByMinCost(minCost);
    }
}
