package org.example.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity // - Отображение в БД
@NoArgsConstructor // Конструктор без параметров
@AllArgsConstructor // Конструктор со всеми параметрами
@Data // Генерация get и set
public class Clothing
{
    @Id // Указываем поле как id
    @GeneratedValue// Генерируем значение автоматически
    int id;
    String type;
    String brand;
    String description;
    double cost;
    int quantity;
}
