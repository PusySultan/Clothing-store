package org.example.controller;

import org.example.Service.PersonService;
import org.example.model.BodyRequest;
import org.example.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

//port 8080
@RestController
@RequestMapping("/person")
public class PersonController
{
    @Autowired
    private PersonService personService;

    @GetMapping
    public ResponseEntity<?> getPersons(@RequestBody BodyRequest bodyRequest)
    {
        return personService.getPersons(bodyRequest);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getPersonById(@PathVariable int id, @RequestBody BodyRequest bodyRequest)
    {
        return personService.getPersonById(id, bodyRequest);
    }

    @PostMapping
    public  ResponseEntity<?> createPerson(@RequestBody Person person)
    {
       return personService.createPerson(person);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Person> updatePerson(@PathVariable int id, @RequestBody Person person)
    {
        return personService.updatePerson(id, person);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Person> deleteById(@PathVariable int id)
    {
       return personService.deletePersonById(id);
    }

    @DeleteMapping
    public void deleteAllPersons(@RequestBody BodyRequest bodyRequest)
    {
        personService.deleteAllPersons(bodyRequest);
    }
}
