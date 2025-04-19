package org.example.service;

import org.example.model.BodyRequest;
import org.example.model.Person;
import org.example.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuntService
{
    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public static Person auntPerson = null;

    /// вход
    public ResponseEntity<?> getPerson(BodyRequest bodyRequest)
    {
        if(checkAccess(bodyRequest))
        {
            auntPerson = personRepository.findByEmail(bodyRequest.getEmail()).get() ;
            return new ResponseEntity<>(auntPerson, HttpStatus.OK);
        }
            return ResponseEntity.badRequest().body("Не верный логин или пароль");
    }

    /// выход
    public ResponseEntity<?> outPerson()
    {
        if(auntPerson == null)
        {
            return ResponseEntity.badRequest().body("Вы не вошли в систему");
        }

        auntPerson = null;
        return ResponseEntity.ok().body("Вы вышли из системы");
    }

    private boolean checkAccess(BodyRequest bodyRequest)
    {
        if(personRepository.existsByEmail(bodyRequest.getEmail()))
        {
            Person person = personRepository.findByEmail(bodyRequest.getEmail()).get();
            String password = person.getPassword();

            return passwordEncoder.matches(bodyRequest.getPassword(), password);
        }

        return false;
    }
}
