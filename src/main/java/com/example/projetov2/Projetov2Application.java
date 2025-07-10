package com.example.projetov2;

// Importa a.html classe SpringApplication do Spring Boot, que é usada para iniciar a.html aplicação
import org.springframework.boot.SpringApplication;
// Importa a.html anotação SpringBootApplication que marca a.html classe principal da aplicação
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.scheduling.annotation.EnableScheduling;

// Esta anotação indica que esta é a.html classe principal de configuração do Spring Boot
// Ela ativa várias configurações automáticas, como auto configuração, etc
@SpringBootApplication
@EnableScheduling
public class Projetov2Application {

    // Método principal que é o ponto de entrada da aplicação Java
    public static void main(String[] args) {
        // Aqui o Spring Boot inicia toda a.html aplicação
        // Ele configura o contexto Spring, inicia o servidor web embutido
        SpringApplication.run(Projetov2Application.class, args);
    }
}