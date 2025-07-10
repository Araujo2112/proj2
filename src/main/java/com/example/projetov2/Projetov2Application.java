package com.example.projetov2;

import org.springframework.boot.SpringApplication;
// Importa a.html anotação SpringBootApplication que marca a.html classe principal da aplicação
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class Projetov2Application {

    public static void main(String[] args) {
        SpringApplication.run(Projetov2Application.class, args);
    }
}