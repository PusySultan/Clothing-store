package org.example.service;

import jakarta.transaction.Transactional;
import org.example.model.BodyRequest;
import org.example.model.Person;
import org.example.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class RegService
{
    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional
    public ResponseEntity<?> createPerson(Person person)
    {
        if(personRepository.existsByEmail(person.getEmail()))
        {
            return ResponseEntity.badRequest().body("Email уже используется") ;
        }

        // person.setRole("Admin");
        person.setPassword(passwordEncoder.encode(person.getPassword())); // Хеширование пароля
        return new ResponseEntity<Person>(personRepository.save(person), HttpStatus.CREATED);
    }

    @Transactional
    public ResponseEntity<?> updatePerson(Person person, int id)
    {
        BodyRequest bodyRequest = new BodyRequest(person.getEmail(), person.getPassword());

        if(!checkAccess(bodyRequest, id))
        {
            return ResponseEntity.badRequest().body("Ошибка") ;
        }

        personRepository.deleteById(id);
        return new ResponseEntity<Person>(personRepository.save(person), HttpStatus.OK);
    }

    @Transactional
    public ResponseEntity<?> deletePersonById(BodyRequest bodyRequest, int id)
    {
        if(!checkAccess(bodyRequest, id))
        {
            return ResponseEntity.badRequest().body("Ошибка");
        }

        personRepository.deleteById(id);
        return new ResponseEntity<Person>(HttpStatus.OK);
    }

    public boolean checkAccess(BodyRequest bodyRequest, int id)
    {
        if(personRepository.existsByEmail(bodyRequest.getEmail()))
        {
            Person person = personRepository.findByEmail(bodyRequest.getEmail()).get();
            String password = person.getPassword();

            System.out.println("Password hash person: " + password); // хэш
            System.out.println("Password body: " + passwordEncoder.encode("rawPassword"));
            System.out.println("Password body: " + bodyRequest.getPassword()); // "rawPassword"

            return passwordEncoder.matches(bodyRequest.getPassword(), password);
        }

        return false;
    }
}
