package org.example.serviceTest;

import org.example.model.BodyRequest;
import org.example.model.Person;
import org.example.repository.PersonRepository;
import org.example.service.RegService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class RegServiceTest
{
    @Mock
    private PersonRepository personRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private RegService regService;

    private Person testPerson;

    @BeforeEach
    public void setUp()
    {
        testPerson = new Person();
        testPerson.setEmail("test@example.com");
        testPerson.setPassword("rawPassword");
    }

    @Test
    public void createPerson_EmailExists_ReturnsBadRequest()
    {
        // Вернуть true когда вызван метод existsByEmail
        when(personRepository.existsByEmail(testPerson.getEmail())).thenReturn(true);

        // Вызываем метод
        ResponseEntity<?> response = regService.createPerson(testPerson);

        // Проверка статуса ответа
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());

        // Проверка тела ответа
        assertEquals("Email уже используется", response.getBody());

        // Проверяем что save вызван
        verify(personRepository, never()).save(any(Person.class));
    }

    @Test
    public void createPerson_EmailDoesNotExists_ReturnsOkRequest()
    {
        // Вернуть false когда вызван метод existsByEmail
        when(personRepository.existsByEmail(testPerson.getEmail())).thenReturn(false);

        // Вызываем метод
        ResponseEntity<?> response = regService.createPerson(testPerson);

        // Проверка статуса ответа
        assertEquals(HttpStatus.CREATED, response.getStatusCode());

        // Проверяем что save вызван
        verify(personRepository).save(any(Person.class));
    }

    @Test
    public void updatePerson_HasNotAccess_ReturnBadRequest()
    {
        BodyRequest bodyRequest = new BodyRequest(testPerson.getEmail(), testPerson.getPassword());
        when(regService.checkAccess(bodyRequest, -1)).thenReturn(false);

        ResponseEntity<?> response = regService.updatePerson(testPerson, -1);
        assertEquals("Ошибка", response.getBody());
    }

    @Test
    public void  updatePerson_AccessGranted_ReturnsOkRequest()
    {
        when(personRepository.existsByEmail(testPerson.getEmail())).thenReturn(true);
        when(personRepository.findByEmail(testPerson.getEmail())).thenReturn(Optional.of(testPerson));
        when(passwordEncoder.matches("rawPassword", testPerson.getPassword())).thenReturn(true);

        ResponseEntity<?> response = regService.updatePerson(testPerson, -1);
        assertEquals(HttpStatus.OK, response.getStatusCode());

        verify(personRepository).deleteById(anyInt());
        verify(personRepository).save(any(Person.class));
    }

    @Test
    public void deletePersonById_HasNotAccessReturnsOkRequest()
    {
        BodyRequest bodyRequest = new BodyRequest(testPerson.getEmail(), testPerson.getPassword());
        when(regService.checkAccess(bodyRequest, -1)).thenReturn(false);

        ResponseEntity<?> response = regService.deletePersonById(bodyRequest, 1);
        assertEquals("Ошибка", response.getBody());
    }

    @Test
    public void deletePersonById_AccessGranted_ReturnsOkRequest()
    {
        BodyRequest bodyRequest = new BodyRequest(testPerson.getEmail(), testPerson.getPassword());
        when(personRepository.existsByEmail(testPerson.getEmail())).thenReturn(true);
        when(personRepository.findByEmail(testPerson.getEmail())).thenReturn(Optional.of(testPerson));
        when(passwordEncoder.matches("rawPassword", testPerson.getPassword())).thenReturn(true);

        ResponseEntity<?> response = regService.deletePersonById(bodyRequest, -1);
        assertEquals(HttpStatus.OK, response.getStatusCode());

        verify(personRepository).deleteById(anyInt());
    }

    @Test
    public void checkAccess_AccessMissing_ReturnFalse()
    {
        when(personRepository.existsByEmail(testPerson.getEmail())).thenReturn(false);

        boolean access = regService.checkAccess(new BodyRequest(testPerson.getEmail(), testPerson.getPassword()), -1);
        assertFalse(access);
    }
}
