package org.example.controller;

import org.example.Service.PersonAdminService;
import org.example.model.BodyRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/// port 8080
/// Этот контроллер для админа
@RestController
@RequestMapping("/person/admin")
public class PersonAdminController
{
    @Autowired
    PersonAdminService personAdminService;

    /// Получение всех пользователей
    @GetMapping()
    public ResponseEntity<?> getPersons(@RequestBody BodyRequest bodyRequest)
    {
        return personAdminService.getPersons(bodyRequest);
    }

    /// Удалить всех пользователей
    @DeleteMapping
    public void deleteAllPersons(@RequestBody BodyRequest bodyRequest)
    {
        personAdminService.deleteAllPersons(bodyRequest);
    }
}
