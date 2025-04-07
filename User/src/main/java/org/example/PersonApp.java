package org.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// Port 8080
@SpringBootApplication
public class PersonApp
{
    public static void main(String[] args)
    {
        SpringApplication.run(PersonApp.class, args);
    }
}