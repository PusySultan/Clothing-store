package org.example.service;

import org.example.repository.ClothingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ClothingFindService
{
    @Autowired
    ClothingRepository clothingRepository;

    public ResponseEntity<?> getClothingById(int id)
    {
        return null;
    }

    public ResponseEntity<?> getClothingByType(String type)
    {
        return new ResponseEntity<>(clothingRepository.findByType(type), HttpStatus.OK);
    }

    public ResponseEntity<?> getClothingByBrand(String brand)
    {
        return null;
    }
}
