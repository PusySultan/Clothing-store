package org.example.controller;

import org.example.service.RegService;
import org.example.model.BodyRequest;
import org.example.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/// port 8080 -
/// Это контроллер для регестрации
@RestController
@RequestMapping("/person/reg")
public class RegController
{
    @Autowired
    RegService personRegService;

    /// Созданиие (регистрация) пользователя
    @PostMapping
    public  ResponseEntity<?> createPerson(@RequestBody Person person)
    {
        return personRegService.createPerson(person);
    }

    /// Изменение пользователя
    @PutMapping("/{id}")
    public ResponseEntity<?> updatePerson(@RequestBody Person person, @PathVariable int id)
    {
        return personRegService.updatePerson(person, id);
    }

    /// Удаление пользователя
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@RequestBody BodyRequest bodyRequest, @PathVariable int id)
    {
        return personRegService.deletePersonById(bodyRequest, id);
    }
}
