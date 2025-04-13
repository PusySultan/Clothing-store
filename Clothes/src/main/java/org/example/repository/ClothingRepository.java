package org.example.repository;

import org.example.model.Clothing;
import org.springframework.data.repository.CrudRepository;
import org.springframework.http.ResponseEntity;

public interface ClothingRepository extends CrudRepository<Clothing, Integer>
{

    Clothing findByType(String type);
    Clothing findByBrand(String brand);
}
