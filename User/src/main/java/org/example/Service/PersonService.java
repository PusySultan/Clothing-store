package org.example.Service;

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
import java.util.Optional;

@Service
public class PersonService
{
    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public ResponseEntity<?> getPersons(BodyRequest bodyRequest)
    {
        if(checkAccess(bodyRequest))
        {
            return new ResponseEntity<Iterable<Person>>(personRepository.findAll(), HttpStatus.OK);
        }

        return ResponseEntity.badRequest().body("Отказано в доступе") ;
    }


    public ResponseEntity<?> getPersonById(int id, BodyRequest bodyRequest)
    {
        if(!checkAccess(bodyRequest))
        {
            return ResponseEntity.badRequest().body("Отказано в доступе") ;
        }

        if (personRepository.existsById((long)id))
        {
            return new ResponseEntity<Optional<Person>>(
                    personRepository.findById((long)id), HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @Transactional
    public ResponseEntity<?> createPerson(Person person)
    {
        if(personRepository.existsByEmail(person.getEmail()))
        {
            return ResponseEntity.badRequest().body("Email уже используется") ;
        }

        person.setPassword(passwordEncoder.encode(person.getPassword()));
        return new ResponseEntity<Person>(personRepository.save(person), HttpStatus.CREATED);
    }

    @Transactional
    public ResponseEntity<Person> updatePerson(int id, Person person)
    {
        if(personRepository.existsById((long)id))
        {
            personRepository.deleteById((long)id);
            return new ResponseEntity<Person>(personRepository.save(person), HttpStatus.OK);
        }

        return new ResponseEntity<Person>(HttpStatus.NOT_FOUND);
    }

    @Transactional
    public ResponseEntity<Person> deletePersonById(int id)
    {
        if(personRepository.existsById((long)id))
        {
            personRepository.deleteById((long)id);
            return new ResponseEntity<Person>(HttpStatus.OK);
        }

        return new ResponseEntity<Person>(HttpStatus.NOT_FOUND);
    }

    @Transactional
    public void deleteAllPersons(BodyRequest bodyRequest)
    {
        if(checkAccess(bodyRequest))
        {
            personRepository.deleteAll();
        }
    }

    private boolean checkAccess(BodyRequest bodyRequest)
    {
        String adminPassword = "qwerty";
        String adminEmail = "ADMIN";

        return Objects.equals(bodyRequest.getEmail(), adminEmail) &&
                Objects.equals(bodyRequest.getPassword(), adminPassword);
    }
}
