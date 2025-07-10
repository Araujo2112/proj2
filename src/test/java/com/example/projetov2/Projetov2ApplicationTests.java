package com.example.projetov2;

// Importa a.html anotação para criar testes unitários
import org.junit.jupiter.api.Test;
// Importa a.html anotação que faz o Spring Boot carregar o contexto da aplicação para testes
import org.springframework.boot.test.context.SpringBootTest;

// Esta anotação indica que esta classe é um teste que vai carregar o contexto do Spring Boot
@SpringBootTest
class Projetov2ApplicationTests {

    // Método de teste (vazio, só para garantir que o contexto carrega sem erros)
    @Test
    void contextLoads() {
    }

}
