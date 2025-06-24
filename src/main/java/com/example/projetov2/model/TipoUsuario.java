package com.example.projetov2.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "tipoUsuario")
public class TipoUsuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idTipoUsuario;

    @Column(nullable = false, length = 100)
    private String tipo;

    public TipoUsuario() {}

    public TipoUsuario(Integer idTipoUsuario, String tipo) {
        this.idTipoUsuario = idTipoUsuario;
        this.tipo = tipo;
    }

    public TipoUsuario(String tipo) {
        this.tipo = tipo;
    }

    public Integer getIdTipoUsuario() {
        return idTipoUsuario;
    }

    public void setIdTipoUsuario(Integer idTipoUsuario) {
        this.idTipoUsuario = idTipoUsuario;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
