package com.example.projetov2.model;

// Controlo de serialização JSON (não estão a.html ser usadas aqui, mas poderiam ser úteis)
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
// Importações do Jakarta Persistence API (JPA)
import jakarta.persistence.*;

import java.util.List;

// Define que esta classe é uma entidade JPA, ou seja, será mapeada para uma tabela na BD
@Entity
// Define o nome da tabela correspondente na BD
@Table(name = "codPostal")
public class CodPostal {
    // Define o campo como chave primária da tabela
    @Id
    // Define o tamanho máximo da coluna
    @Column(length = 10)
    private String idCodPostal;

    // Define uma coluna obrigatória com no máximo 100 caracteres
    @Column(nullable = false, length = 100)
    private String localidade;

    // Construtor vazio exigido pelo JPA para criar instâncias por reflexão
    public CodPostal() {
    }

    // Construtor útil para criar objetos manualmente
    public CodPostal(String idCodPostal, String localidade) {
        this.idCodPostal = idCodPostal;
        this.localidade = localidade;
    }

    // Getters e Setters: permitem aceder e modificar os atributos do objeto
    public String getIdCodPostal() {
        return idCodPostal;
    }

    public void setIdCodPostal(String idCodPostal) {
        this.idCodPostal = idCodPostal;
    }

    public String getLocalidade() {
        return localidade;
    }

    public void setLocalidade(String localidade) {
        this.localidade = localidade;
    }
}

