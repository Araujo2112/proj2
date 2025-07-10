package com.example.projetov2.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "manutencao")
public class Manutencao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idManu;

    @Column(nullable = false)
    private LocalDate dtIni;

    private LocalDate dtFim;

    private String descricao;

    @ManyToOne
    @JoinColumn(name = "idEstado", nullable = false)
    private TipoEstado estado;

    @ManyToOne
    @JoinColumn(name = "idEspaco", nullable = false)
    private EspacoDesportivo espacoDesportivo;

    @ManyToOne
    @JoinColumn(name = "idUsuario", nullable = false)
    private Usuario usuario;

    @Column(nullable = false)
    private Double custo;

    public Manutencao() {
    }

    public Manutencao(Integer idManu, LocalDate dtIni, LocalDate dtFim, String descricao, EspacoDesportivo espacoDesportivo, TipoEstado estado, Usuario usuario, Double custo) {
        this.idManu = idManu;
        this.dtIni = dtIni;
        this.dtFim = dtFim;
        this.descricao = descricao;
        this.espacoDesportivo = espacoDesportivo;
        this.estado = estado;
        this.usuario = usuario;
        this.custo = custo;
    }

    public Manutencao(LocalDate dtIni, String descricao, LocalDate dtFim, TipoEstado estado, EspacoDesportivo espacoDesportivo, Usuario usuario, Double custo) {
        this.dtIni = dtIni;
        this.descricao = descricao;
        this.dtFim = dtFim;
        this.estado = estado;
        this.espacoDesportivo = espacoDesportivo;
        this.usuario = usuario;
        this.custo = custo;
    }

    public Integer getIdManu() {
        return idManu;
    }

    public void setIdManu(Integer idManu) {
        this.idManu = idManu;
    }

    public LocalDate getDtIni() {
        return dtIni;
    }

    public void setDtIni(LocalDate dtIni) {
        this.dtIni = dtIni;
    }

    public LocalDate getDtFim() {
        return dtFim;
    }

    public void setDtFim(LocalDate dtFim) {
        this.dtFim = dtFim;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public TipoEstado getEstado() {
        return estado;
    }

    public void setEstado(TipoEstado estado) {
        this.estado = estado;
    }

    public EspacoDesportivo getEspacoDesportivo() {
        return espacoDesportivo;
    }

    public void setEspacoDesportivo(EspacoDesportivo espacoDesportivo) {
        this.espacoDesportivo = espacoDesportivo;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Double getCusto() {
        return custo;
    }

    public void setCusto(Double custo) {
        this.custo = custo;
    }
}

