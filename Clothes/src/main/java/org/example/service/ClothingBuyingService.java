package org.example.service;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.transaction.Transactional;
import org.example.model.Clothing;
import org.example.repository.ClothingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

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

        /*
        *  Здесь было бы не плохо реализовать запрос к
        *  пользователяю (или передавать вместе с ответом)
        *  кооличество денег на болансе у него, чтобы
        * проверять возможность покупки
        */

        clothing.setQuantity(quantity - 1);
        clothingRepository.save(clothing);

        return ResponseEntity.ok().body("Покупка совершена");
    }


    public ResponseEntity<?> buyManyClothing(List<Integer> ids)
    {
        Map<String, Integer> isBuy = new HashMap<String, Integer>();
        String answer = "";

        for (int id : ids)
        {
            answer = Objects.requireNonNull(buyClothingById(id).getBody()).toString();
            if(Objects.equals(answer, "Покупка совершена"))
            {
                isBuy.put("ok", id);
                continue;
            }

            isBuy.put("error", id);
        }

        return new ResponseEntity<>(isBuy, HttpStatus.OK);
    }
}
