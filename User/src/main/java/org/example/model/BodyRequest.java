package org.example.model;

import lombok.Data;

@Data // Генерация get и set
public class BodyRequest
{
    private String email;
    private String password;
}
