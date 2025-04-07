package org.example.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity // - Отображение в БД
@NoArgsConstructor // Конструктор без параметров
@AllArgsConstructor // Конструктор со всеми параметрами
@Data // Генерация get и set
public class Person
{
    @Id // Указываем поле как id
    @GeneratedValue// Генерируем значение автоматически
    private Long id;
    private String lastName;
    private String name;
    private String patronymic;

    private String email;
    private String password;
}
