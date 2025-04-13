package org.example.controller;

import org.example.model.Clothing;
import org.example.service.ClothingAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/// Port 8081
/// Контроллер администрирования магазина
@RestController
@RequestMapping("/admin")
public class ClothingAdminController
{
    @Autowired
    ClothingAdminService clothingAdminService;

    @GetMapping
    public ResponseEntity<?> getAllClothing()
    {
        return clothingAdminService.getAllClothing();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getClothingById(@PathVariable int id)
    {
        return clothingAdminService.getClothingById(id);
    }

    @GetMapping(params = "type")
    public ResponseEntity<?> getClothingByType(@RequestParam String type)
    {
        return clothingAdminService.getClothingByType(type);
    }

    @GetMapping(params = "brand")
    public ResponseEntity<?> getClothingByBrand(@RequestParam String brand)
    {
        return clothingAdminService.getClothingByBrand(brand);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateClothing(@RequestBody Clothing clothing, @PathVariable int id)
    {
        return clothingAdminService.updateClothing(clothing, id);
    }

    @PostMapping
    public  ResponseEntity<?> createClothing(@RequestBody Clothing clothing)
    {
        return clothingAdminService.createClothing(clothing);
    }

    @DeleteMapping
    public void deleteAllClothing()
    {
        clothingAdminService.deleteAllClothing();
    }

    @DeleteMapping("/{id}")
    public void deleteClothingById(@PathVariable int id)
    {
        clothingAdminService.deleteClothingById(id);
    }
}
