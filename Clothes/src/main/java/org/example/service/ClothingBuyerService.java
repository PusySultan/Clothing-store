package org.example.service;

import jakarta.transaction.Transactional;
import org.example.model.Clothing;
import org.example.repository.ClothingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

///  Сервис покупок в магазине
@Service
public class ClothingBuyerService
{
    @Autowired
    ClothingRepository clothingRepository;

    // Фильтрация

    public ResponseEntity<?> getAllClothing()
    {
        return new ResponseEntity<Iterable<Clothing>>(clothingRepository.findAll(), HttpStatus.OK);
    }

    public ResponseEntity<?> getClothingById(int id)
    {
        if(clothingRepository.existsById(id))
        {
            return new ResponseEntity<>(clothingRepository.findById(id), HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<?> getClothingByType(String type)
    {
        return new ResponseEntity<Clothing>(clothingRepository.findByType(type), HttpStatus.OK);
    }

    public ResponseEntity<?> getClothingByBrand(String brand)
    {
        return new ResponseEntity<Clothing>(clothingRepository.findByBrand(brand), HttpStatus.OK);
    }

    // Покупки
    @Transactional
    public ResponseEntity<?> buyClothingById(int id)
    {
        if(!clothingRepository.existsById(id))
        {
            return ResponseEntity.badRequest().body("Товар не существует");
        }

        Clothing clothing = clothingRepository.findById(id).get();
        int quantity = clothing.getQuantity() - 1;

        clothingRepository.deleteById(id);

        if(quantity > 0)
        {
            clothing.setQuantity(quantity);
            clothingRepository.save(clothing);
        }

        return ResponseEntity.ok().body("Покупка совершена");
    }
}
