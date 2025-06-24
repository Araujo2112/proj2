package com.example.projetov2.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "tipoEstado")
public class TipoEstado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idEstado;

    @Column(nullable = false, length = 100)
    private String estado;

    public TipoEstado() {
    }

    public TipoEstado(String estado, Integer idEstado) {
        this.estado = estado;
        this.idEstado = idEstado;
    }

    public TipoEstado(String estado) {
        this.estado = estado;
    }

    public Integer getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(Integer idEstado) {
        this.idEstado = idEstado;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
