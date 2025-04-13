package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data // Генерация get и set
@AllArgsConstructor
public class BodyRequest
{
    private String email;
    private String password;
}
