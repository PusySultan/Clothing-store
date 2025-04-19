package org.example.service;

import jakarta.transaction.Transactional;
import org.example.model.Clothing;
import org.example.repository.ClothingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

///  Сервис покупок в магазине
@Service
public class ClothingBuyingService
{
    @Autowired
    ClothingRepository clothingRepository;

    // Покупки
    @Transactional
    public ResponseEntity<?> buyClothingById(int id)
    {
        if(!clothingRepository.existsById(id))
        {
            return ResponseEntity.badRequest().body("Товар не существует");
        }

        Clothing clothing = clothingRepository.findById(id).get();
        int quantity = clothing.getQuantity();

        if(quantity <= 0)
        {
            return ResponseEntity.ok().body("Товар закончился");
        }

        clothing.setQuantity(quantity - 1);
        clothingRepository.save(clothing);

        return ResponseEntity.ok().body("Покупка совершена");
    }


    public ResponseEntity<?> buyManyClothing(List<Integer> ids)
    {
        StringBuilder answer = new StringBuilder();

        for (int id : ids)
        {
          answer.append(buyClothingById(id).getBody());
          answer.append("\n");
        }

        return ResponseEntity.ok().body(answer);
    }
}
