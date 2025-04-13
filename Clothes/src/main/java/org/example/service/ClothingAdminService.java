package org.example.service;

import jakarta.transaction.Transactional;
import org.example.model.Clothing;
import org.example.repository.ClothingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

///  Сервис администрирования магазина
@Service
public class ClothingAdminService
{
    @Autowired
    ClothingRepository clothingRepository;

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

    @Transactional
    public ResponseEntity<?> updateClothing(Clothing clothing, int id)
    {
        if(clothingRepository.existsById(id))
        {
            clothingRepository.deleteById(id);
            return new ResponseEntity<>(clothingRepository.save(clothing), HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @Transactional
    public ResponseEntity<?> createClothing(Clothing clothing)
    {
       return new ResponseEntity<>(clothingRepository.save(clothing), HttpStatus.OK);
    }

    @Transactional
    public void deleteAllClothing()
    {
        clothingRepository.deleteAll();
    }

    @Transactional
    public void deleteClothingById(int id)
    {
        clothingRepository.deleteById(id);
    }
}
