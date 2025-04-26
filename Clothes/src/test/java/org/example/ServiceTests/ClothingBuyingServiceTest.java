package org.example.ServiceTests;

import org.example.model.Clothing;
import org.example.repository.ClothingRepository;
import org.example.service.ClothingBuyingService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ClothingBuyingServiceTest
{
    @Mock
    private ClothingRepository clothingRepository;

    @InjectMocks
    private ClothingBuyingService clothingBuyingService;

    @Test
    public void buyClothingById_ProductIsMissing_BadRequest()
    {
        when(clothingRepository.existsById(-1)).thenReturn(false);
        ResponseEntity<?> response =  clothingBuyingService.buyClothingById(-1);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Товар не существует", response.getBody());
    }

    @Test
    public void buyClothingById_QuantityLessNull_BadRequest()
    {
        Clothing clothing = new Clothing();
        clothing.setQuantity(0);

        when(clothingRepository.existsById(-1)).thenReturn(true);
        when(clothingRepository.findById(-1)).thenReturn(Optional.of(clothing));

        ResponseEntity<?> response =  clothingBuyingService.buyClothingById(-1);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Товар закончился", response.getBody());
    }

    @Test
    public void buyClothingById_QuantityMoreOnes_OkRequest()
    {
        Clothing clothing = new Clothing();
        clothing.setQuantity(10);

        when(clothingRepository.existsById(-1)).thenReturn(true);
        when(clothingRepository.findById(-1)).thenReturn(Optional.of(clothing));

        ResponseEntity<?> response =  clothingBuyingService.buyClothingById(-1);

        assertEquals(9, clothing.getQuantity());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Покупка совершена", response.getBody());

        verify(clothingRepository).save(clothing);
    }

    @Test
    public  void buyManyClothing_BuyOnesExistingProduct_Granted()
    {
        List<Integer> list = new ArrayList<>();
        list.add(-1);

        Clothing clothing = new Clothing();
        clothing.setQuantity(10);

        when(clothingRepository.existsById(-1)).thenReturn(true);
        when(clothingRepository.findById(-1)).thenReturn(Optional.of(clothing));

        ResponseEntity<?> response =  clothingBuyingService.buyManyClothing(list);

        assertEquals(9, clothing.getQuantity());
        assertEquals(HttpStatus.OK, response.getStatusCode());

        verify(clothingRepository).save(clothing);
    }

    @Test
    public  void buyManyClothing_BuyTwoExistingProducts_Granted()
    {
        List<Integer> list = new ArrayList<>();
        list.add(-1);
        list.add(-2);

        Clothing clothing = new Clothing();
        clothing.setQuantity(10);

        when(clothingRepository.existsById(-1)).thenReturn(true);
        when(clothingRepository.findById(-1)).thenReturn(Optional.of(clothing));
        when(clothingRepository.existsById(-2)).thenReturn(true);
        when(clothingRepository.findById(-2)).thenReturn(Optional.of(clothing));

        ResponseEntity<?> response =  clothingBuyingService.buyManyClothing(list);

        assertEquals(8, clothing.getQuantity());
        assertEquals(HttpStatus.OK, response.getStatusCode());

        verify(clothingRepository, times(2)).save(clothing);
    }
}
