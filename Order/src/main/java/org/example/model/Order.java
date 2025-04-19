package org.example.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "Orders")
@NoArgsConstructor
@Data
public class Order
{
    @Id
    private Integer id;

    private LocalDateTime creatingTime;

    @ElementCollection
    @CollectionTable(name = "order_clothing", joinColumns = @JoinColumn(name = "order_id"))
    private List<Clothing> clothingList = new ArrayList<>();
    private Status status;

    public Order(int id, LocalDateTime creatingTime, Clothing clothing)
    {
       this.id = id;
       this.creatingTime = creatingTime;
       this.clothingList.add(clothing);
       this.status = null;
    }

    public void AddClothing(Clothing clothing)
    {
        this.clothingList.add(clothing);
    }

    public int getClothingPosition(int productId)
    {
        for(Clothing clothing : clothingList)
        {
            if(clothing.getProductId() == productId)
            {
                return clothingList.indexOf(clothing);
            }
        }

        return -1;
    }

    public List<Integer> getAllId()
    {
        List<Integer> list = new ArrayList<>();
        for (Clothing clothing : clothingList)
        {
            list.add(clothing.getProductId());
        }

        return list;
    }
}