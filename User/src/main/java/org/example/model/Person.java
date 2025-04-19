package org.example.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity            // - Отображение в БД
@NoArgsConstructor // Конструктор без параметров
@Data              // Генерация get и set
public class Person
{
    @Id             // Указываем поле как id
    @GeneratedValue // Генерируем значение автоматически
    private Integer id;
    private String lastName;
    private String name;
    private String patronymic;

    @Column(nullable = false)
    private String role = "User";

    private String email;
    private String password;

    public Person(String lastName, String name, String patronymic,
                  String email, String password)
    {
        this.lastName = lastName;
        this.name = name;
        this.patronymic = patronymic;
        this.email = email;
        this.password = password;
    }
}
