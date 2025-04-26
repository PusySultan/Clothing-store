package org.example.serviceTest;

import org.example.model.BodyRequest;
import org.example.model.Person;
import org.example.repository.PersonRepository;
import org.example.service.AuntService;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AuntServiceTest
{

    @Mock
    private PersonRepository personRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private AuntService auntService;

    private Person testPerson;

    @BeforeEach
    public void setUp()
    {
        testPerson = new Person();
        testPerson.setEmail("test@example.com");
        testPerson.setPassword("rawPassword");
    }

    @Test
    public void getPerson_NoAccess_BadRequest()
    {
        BodyRequest bodyRequest = new BodyRequest(testPerson.getEmail(), testPerson.getPassword());
        when(auntService.checkAccess(bodyRequest)).thenReturn(false);

        ResponseEntity<?> response = auntService.getPerson(bodyRequest);

        // Проверка статуса ответа
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Не верный логин или пароль", response.getBody());
    }

    @Test
    public void getPerson_AccessGranted_OkRequest()
    {
        BodyRequest bodyRequest = new BodyRequest(testPerson.getEmail(), testPerson.getPassword());

        when(personRepository.existsByEmail(bodyRequest.getEmail())).thenReturn(true);
        when(passwordEncoder.matches(bodyRequest.getPassword(), bodyRequest.getPassword())).thenReturn(true);
        when(personRepository.findByEmail(bodyRequest.getEmail())).thenReturn(Optional.of(testPerson));

        ResponseEntity<?> response = auntService.getPerson(bodyRequest);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(testPerson, response.getBody());
    }

    @Test
    public void outPerson_PersonIsNotAunt_BadRequest()
    {
        ResponseEntity<?> response = auntService.outPerson();
        assertEquals("Вы не вошли в систему", response.getBody());
    }

    @Test
    public void outPerson_PersonIsAunt_OkRequest()
    {
        AuntService.auntPerson = new Person();
        ResponseEntity<?> response = auntService.outPerson();

        assertNull(AuntService.auntPerson);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Вы вышли из системы", response.getBody());
    }
}
