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

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

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
    }
}
