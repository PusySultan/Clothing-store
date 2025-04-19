package org.example.service;

import org.example.model.Clothing;
import org.example.repository.ClothingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClothingFindService
{
    @Autowired
    ClothingRepository clothingRepository;


    public ResponseEntity<?> getAllClothing()
    {
        return ResponseEntity.ok().body(clothingRepository.findAll());
    }

    public ResponseEntity<?> getClothingById(int id)
    {
       return new ResponseEntity<>(clothingRepository.findById(id), HttpStatus.OK);
    }

    public ResponseEntity<?> getClothingByType(String type)
    {
        return new ResponseEntity<>(clothingRepository.findALLByType(type), HttpStatus.OK);
    }

    public ResponseEntity<?> getClothingByBrand(String brand)
    {
        return new ResponseEntity<>(clothingRepository.findALLByBrand(brand), HttpStatus.OK);
    }

    public ResponseEntity<?> getClothingByMaxCost(double maxCost)
    {
        List<Clothing> result = clothingRepository.findByCostLessThanEqual(maxCost);

         return result.isEmpty()
                 ? ResponseEntity.ok("Нет таких товаров")
                 : new ResponseEntity<>(result, HttpStatus.OK);
    }

    public ResponseEntity<?> getClothingByMinCost(double minCost)
    {
        List<Clothing> result = clothingRepository.findByCostGreaterThanEqual(minCost);

        return result.isEmpty()
                ? ResponseEntity.ok("Нет таких товаров")
                : new ResponseEntity<>(result, HttpStatus.OK);
    }
}
