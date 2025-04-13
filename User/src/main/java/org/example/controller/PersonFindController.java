package org.example.controller;

import org.example.Service.PersonFindService;
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
public class PersonFindController
{
    @Autowired
    PersonFindService personBuyingService;

    /// Фильтрация по типу
    @GetMapping(params = "type")
    public ResponseEntity<?> checkClothingByType(@RequestParam String type)
    {
       return personBuyingService.checkClothingByType(type);
    }

    /// Фильтрация по бренду
    @GetMapping(params = "brand")
    public ResponseEntity<?> checkClothingByBrand(@RequestParam String brand)
    {
        return personBuyingService.checkClothingByBrand(brand);
    }

    /// Фильтрация по максимальной стоимости
    @GetMapping(params = "maxCost")
    public ResponseEntity<?> checkClothingByMaxCost(@RequestParam double maxCost)
    {
        return personBuyingService.checkClothingByMaxCost(maxCost);
    }

    /// Фильтрация по минимальной стоимости
    @GetMapping(params = "minCost")
    public ResponseEntity<?> checkClothingByMinCost(@RequestParam double minCost)
    {
        return personBuyingService.checkClothingByMinCost(minCost);
    }

}
