package org.example.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor
@Data               // Генерация get и set
public class Clothing
{
    private int productId;
    private String type;
    private String brand;
    private String description;
    private double cost;
    private int quantity;

    public Clothing(int productId, String type, String brand, String description, double cost, int quantity)
    {
        this.productId = productId;
        this.type = type;
        this.brand = brand;
        this.description = description;
        this.cost = cost;
        this.quantity = quantity;
    }
}
