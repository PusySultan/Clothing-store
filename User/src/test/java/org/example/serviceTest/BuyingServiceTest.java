package org.example.serviceTest;

import org.example.model.Person;
import org.example.repository.PersonRepository;
import org.example.service.AuntService;
import org.example.service.BuyingService;
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
public class BuyingServiceTest
{
    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private BuyingService buyingService;

    @BeforeEach
    void setUp()
    {
        // Устанавливаем значение для clothingUrl через рефлексию
        ReflectionTestUtils.setField(buyingService, "orderUrl", "order-service:8080");
    }

    @Test
    void addToBasketById_PersonIsNotAunt_BadRequest()
    {
        AuntService.auntPerson = null;
        ResponseEntity<?> response =  buyingService.addToBasketById(-1);

        assertEquals("Для начала  войдите в систему", response.getBody());
    }

    @Test
    void addToBasketById_PersonIsAunt_OkRequest()
    {
        AuntService.auntPerson = new Person();

        AuntService.auntPerson = new Person();
        when(restTemplate.getForObject(anyString(), eq(String.class))).thenReturn("answer");

        ResponseEntity<?> response =  buyingService.addToBasketById(-1);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("answer", response.getBody());

        verify(restTemplate).getForObject(
                "http://order-service:8080/basket/add?productId=-1&userId",
                String.class
        );
    }

    @Test
    public void removeFromBasketByID_PersonIsNotAunt_BadRequest()
    {
        AuntService.auntPerson = null;
        ResponseEntity<?> response = buyingService.removeFromBasketByID(-1);

        assertEquals("Для начала  войдите в систему", response.getBody());
    }

    @Test
    public void removeFromBasketByID_PersonIsAunt_OkRequest()
    {
        AuntService.auntPerson = new Person();
        when(restTemplate.getForObject(anyString(), eq(String.class))).thenReturn("answer");

        ResponseEntity<?> response = buyingService.removeFromBasketByID(-1);

        assertEquals("answer", response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(restTemplate).getForObject(
                "http://order-service:8080/basket/remove?productId=-1&userId",
                String.class
        );
    }

    @Test
    public  void getBasketById_PersonIsNotAunt_BadRequest()
    {
        AuntService.auntPerson = null;
        ResponseEntity<?> response = buyingService.getBasketById();

        assertEquals("Для начала  войдите в систему", response.getBody());
    }

    @Test
    public  void getBasketById_PersonIsAunt_OkRequest()
    {
        AuntService.auntPerson = new Person();
        AuntService.auntPerson.setId(0);


        when(restTemplate.getForObject(anyString(), eq(String.class))).thenReturn("answer");

        ResponseEntity<?> response = buyingService.getBasketById();

        assertEquals("answer", response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(restTemplate).getForObject(
                "http://order-service:8080/basket/get?userId=0",
                String.class
        );
    }

    @Test
    public void buyFromBasketById_PersonIsNotAunt_BadRequest()
    {
        AuntService.auntPerson = null;
        ResponseEntity<?> response = buyingService.buyFromBasketById(-1);

        assertEquals("Для начала  войдите в систему", response.getBody());
    }

    @Test
    public  void buyFromBasketById_PersonIsAunt_OkRequest()
    {
        AuntService.auntPerson = new Person();
        AuntService.auntPerson.setId(0);


        when(restTemplate.getForObject(anyString(), eq(String.class))).thenReturn("answer");

        ResponseEntity<?> response = buyingService.buyFromBasketById(-1);

        assertEquals("answer", response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(restTemplate).getForObject(
                "http://order-service:8080/buy?userId=0&productId=-1",
                String.class
        );
    }

    @Test
    public void buyAllBasket_PersonIsNotAunt_BadRequest()
    {
        AuntService.auntPerson = null;
        ResponseEntity<?> response = buyingService.buyAllBasket();

        assertEquals("Для начала  войдите в систему", response.getBody());
    }

    @Test
    public void buyAllBasket_PersonIsAunt_OkRequest()
    {
        AuntService.auntPerson = new Person();
        AuntService.auntPerson.setId(0);


        when(restTemplate.getForObject(anyString(), eq(String.class))).thenReturn("answer");

        ResponseEntity<?> response = buyingService.buyAllBasket();

        assertEquals("answer", response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(restTemplate).getForObject(
                "http://order-service:8080/buy/all?userId=0",
                String.class
        );
    }
}
