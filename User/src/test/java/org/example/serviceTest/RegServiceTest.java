package org.example;

import org.example.model.Person;
import org.example.repository.PersonRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RegServiceTest
{
    @Autowired
    PersonRepository personRepository;

    @Test
    public void testCreatePerson_SavedNewPerson_IsPositive()
    {
        Person person = new Person("lastName", "name", "patronymic",
                "email", "password");

        personRepository.save(person);
        Person sevedPerson = personRepository.findById(person.getId()).orElseThrow();

        assertEquals("name", sevedPerson.getName());
    }
}
