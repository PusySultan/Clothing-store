package org.example.serviceTest;

import org.example.model.Person;
import org.example.repository.PersonRepository;
import org.example.service.AuntService;
import org.example.service.FindService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class FindServiceTest
{
    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private FindService findService;

    @BeforeEach
    void setUp()
    {
        // Устанавливаем значение для clothingUrl через рефлексию
        ReflectionTestUtils.setField(findService, "clothingUrl", "clothing-service:8080");
    }

    @Test
    public void getAllProducts_PersonIsNotAunt_BadRequest()
    {
        AuntService.auntPerson = null;
        ResponseEntity<?> response =  findService.getAllProducts();

        assertEquals("Войдите в систему", response.getBody());
    }

    @Test
    public void getAllProducts_PersonIsAunt_OkRequest()
    {
        AuntService.auntPerson = new Person();
        when(restTemplate.getForObject(anyString(), eq(String.class))).thenReturn("answer");

        ResponseEntity<?> response =  findService.getAllProducts();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("answer", response.getBody());
        verify(restTemplate).getForObject(
                "http://clothing-service:8080/find/all",
                String.class
        );
    }

    @Test
    public void findClothingByType_PersonIsNotAunt_BadRequest()
    {
        AuntService.auntPerson = null;
        ResponseEntity<?> response =  findService.findClothingByType("anyType");

        assertEquals("Войдите в систему", response.getBody());
    }

    @Test
    public void findClothingByType_PersonIsAunt_OkRequest()
    {
        AuntService.auntPerson = new Person();
        when(restTemplate.getForObject(anyString(), eq(String.class))).thenReturn("answer");

        ResponseEntity<?> response =  findService.findClothingByType("anyType");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("answer", response.getBody());

        verify(restTemplate).getForObject(
                "http://clothing-service:8080/find?type=anyType",
                String.class
        );
    }

    @Test
    public void findClothingByBrand_PersonIsNotAunt_BadRequest()
    {
        AuntService.auntPerson = null;
        ResponseEntity<?> response =  findService.findClothingByBrand("anyBrand");

        assertEquals("Войдите в систему", response.getBody());
    }

    @Test
    public void findClothingByBrand_PersonIsAunt_OkRequest()
    {
        AuntService.auntPerson = new Person();
        when(restTemplate.getForObject(anyString(), eq(String.class))).thenReturn("answer");

        ResponseEntity<?> response =  findService.findClothingByBrand("anyBrand");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("answer", response.getBody());

        verify(restTemplate).getForObject(
                "http://clothing-service:8080/find?brand=anyBrand",
                String.class
        );
    }

    @Test
    public void findClothingByMaxCost_PersonIsNotAunt_BadRequest()
    {
        AuntService.auntPerson = null;
        ResponseEntity<?> response =  findService.findClothingByMaxCost(3500);

        assertEquals("Войдите в систему", response.getBody());
    }

    @Test
    public void findClothingByMaxCost_PersonIsAunt_OkRequest()
    {
        AuntService.auntPerson = new Person();
        when(restTemplate.getForObject(anyString(), eq(String.class))).thenReturn("answer");

        ResponseEntity<?> response =  findService.findClothingByMaxCost(3500);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("answer", response.getBody());

        verify(restTemplate).getForObject(
                "http://clothing-service:8080/find?maxCost=3500.0",
                String.class
        );
    }

    @Test
    public void  findClothingByMinCost_PersonIsNotAunt_BadRequest()
    {
        AuntService.auntPerson = null;
        ResponseEntity<?> response =  findService.findClothingByMinCost(3500);

        assertEquals("Войдите в систему", response.getBody());
    }

    @Test
    public void findClothingByMainCost_PersonIsAunt_OkRequest()
    {
        AuntService.auntPerson = new Person();
        when(restTemplate.getForObject(anyString(), eq(String.class))).thenReturn("answer");

        ResponseEntity<?> response =  findService.findClothingByMinCost(3500);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("answer", response.getBody());

        verify(restTemplate).getForObject(
                "http://clothing-service:8080/find?minCost=3500.0",
                String.class
        );
    }
}
