package org.example.controller;

import org.example.service.FindService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/// port 8080 -
/// Этот контроллер для просмотра одежды в магазине и оформления заказов
@RestController
@RequestMapping("/person/find")
public class FindController
{
    @Autowired
    FindService findService;

    /// Получить все товары
    @GetMapping
    public ResponseEntity<?> getAllProducts()
    {
        return findService.getAllProducts();
    }

    /// Фильтрация по типу
    @GetMapping(params = "type")
    public ResponseEntity<?> findClothingByType(@RequestParam String type)
    {
       return findService.findClothingByType(type);
    }

    /// Фильтрация по бренду
    @GetMapping(params = "brand")
    public ResponseEntity<?> findClothingByBrand(@RequestParam String brand)
    {
        return findService.findClothingByBrand(brand);
    }

    /// Фильтрация по максимальной стоимости
    @GetMapping(params = "maxCost")
    public ResponseEntity<?> findClothingByMaxCost(@RequestParam double maxCost)
    {
        return findService.findClothingByMaxCost(maxCost);
    }

    /// Фильтрация по минимальной стоимости
    @GetMapping(params = "minCost")
    public ResponseEntity<?> findClothingByMinCost(@RequestParam double minCost)
    {
        return findService.findClothingByMinCost(minCost);
    }

}
