package com.example.projetov2;

import com.example.projetov2.model.Usuario;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@SpringBootApplication
public class Projetov2Application {

    public static void main(String[] args) {
        SpringApplication.run(Projetov2Application.class, args);
    }
}
