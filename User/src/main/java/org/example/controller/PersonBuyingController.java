package org.example.controller;

import org.example.Service.PersonBuyingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/// port 8080 -
/// Этот контроллер оформления заказов
@RestController
@RequestMapping("/person/buy")
public class PersonBuyingController
{
    @Autowired
    PersonBuyingService personBuyingService;

    @GetMapping("/buy")
    public ResponseEntity<?> addOrdersByIds(@RequestParam("ids") List<Integer> ids)
    {
        return personBuyingService.addOrdersByIds(ids);
        // return personAuntService.addOrdersByIds(ids);
    }
}
