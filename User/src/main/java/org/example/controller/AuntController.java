package org.example.controller;

import org.example.service.AuntService;
import org.example.model.BodyRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/// port 8080 -
/// Этот контроллер для входа и выхода из системы
@RestController
@RequestMapping("/person/aunt")
public class AuntController
{
    @Autowired
    private AuntService personAuntService;

    /// Получение данных конкретного пользователя (Вход)
    @GetMapping()
    public ResponseEntity<?> getPerson(@RequestBody BodyRequest bodyRequest)
    {
        return personAuntService.getPerson(bodyRequest);
    }

    /// Выход из системы
    @GetMapping("/out")
    public ResponseEntity<?> outPerson()
    {
        return personAuntService.outPerson();
    }
}
