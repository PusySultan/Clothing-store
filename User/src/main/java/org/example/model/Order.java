package org.example.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "Orders") // - Отображение в БД
@NoArgsConstructor       // Конструктор без параметров
@Data                    // Генерация get и set
public class Order
{
    @Id                  // Указываем поле как id
    @GeneratedValue      // Генерируем значение автоматически
    private Integer id;

    @CreationTimestamp
    private LocalDateTime localDateTime;

    @ManyToOne
    @JoinColumn(name = "person_id")
    private Person person;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "order_id")
    private List<Clothing> clothing = new ArrayList<>();

    private Status status;

    public Order(Status status, List<Clothing> clothing)
    {
        this.status = status;
        this.clothing = clothing;
    }
}
