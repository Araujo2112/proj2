package com.example.projetov2.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "tipoPagamento")
public class TipoPagamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idTipoPag;

    @Column(nullable = false, length = 100)
    private String nome;

    public TipoPagamento() {
    }

    public TipoPagamento(Integer idTipoPag, String nome) {
        this.idTipoPag = idTipoPag;
        this.nome = nome;
    }

    public TipoPagamento(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getIdTipoPag() {
        return idTipoPag;
    }

    public void setIdTipoPag(Integer idTipoPag) {
        this.idTipoPag = idTipoPag;
    }
}
