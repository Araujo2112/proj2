package com.example.projetov2.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "codPostal")
public class CodPostal {
    @Id
    @Column(length = 10)
    private String idCodPostal;

    @Column(nullable = false, length = 100)
    private String localidade;

    @OneToMany(mappedBy = "codPostal", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Usuario> usuarios;

    public CodPostal() {
    }

    public CodPostal(String idCodPostal, String localidade, List<Usuario> usuarios) {
        this.idCodPostal = idCodPostal;
        this.localidade = localidade;
        this.usuarios = usuarios;
    }

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

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }
}

