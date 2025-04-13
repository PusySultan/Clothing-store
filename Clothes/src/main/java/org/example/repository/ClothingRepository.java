package org.example.repository;

import org.example.model.Clothing;
import org.springframework.data.repository.CrudRepository;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ClothingRepository extends CrudRepository<Clothing, Integer>
{

    Clothing findByType(String type);
    Clothing findByBrand(String brand);
    Iterable<Clothing> findALLByType(String type);
    Iterable<Clothing> findALLByBrand(String brand);
    List<Clothing> findByCostLessThanEqual(double maxCost);
    List<Clothing> findByCostGreaterThanEqual(double minCost);
}
