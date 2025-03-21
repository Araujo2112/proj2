package com.example.projetov2.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "espaco_desportivo")
public class EspacoDesportivo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_espaco")
    private Integer idEspaco;

    @ManyToOne
    @JoinColumn(name = "id_tipo_espaco", nullable = false)
    @JsonIgnore
    private TipoEspacoDesportivo tipoEspaco;

    @Column(nullable = false)
    private Integer capacidade;

    @Column(length = 50)
    private String lote;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal precoHora;

    @Column(nullable = false)
    private Boolean disponibilidade;

    @OneToMany(mappedBy = "espacoDesportivo", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Reserva> reservas;

    @OneToMany(mappedBy = "espacoDesportivo", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Manutencao> manutencoes;

    public EspacoDesportivo() {}

    public EspacoDesportivo(Integer idEspaco, TipoEspacoDesportivo tipoEspaco, Integer capacidade, String lote, BigDecimal precoHora, Boolean disponibilidade, List<Reserva> reservas, List<Manutencao> manutencoes) {
        this.idEspaco = idEspaco;
        this.tipoEspaco = tipoEspaco;
        this.capacidade = capacidade;
        this.lote = lote;
        this.precoHora = precoHora;
        this.disponibilidade = disponibilidade;
        this.reservas = reservas;
        this.manutencoes = manutencoes;
    }

    public Integer getIdEspaco() {
        return idEspaco;
    }

    public void setIdEspaco(Integer idEspaco) {
        this.idEspaco = idEspaco;
    }

    public TipoEspacoDesportivo getTipoEspaco() {
        return tipoEspaco;
    }

    public void setTipoEspaco(TipoEspacoDesportivo tipoEspaco) {
        this.tipoEspaco = tipoEspaco;
    }

    public Integer getCapacidade() {
        return capacidade;
    }

    public void setCapacidade(Integer capacidade) {
        this.capacidade = capacidade;
    }

    public String getLote() {
        return lote;
    }

    public void setLote(String lote) {
        this.lote = lote;
    }

    public BigDecimal getPrecoHora() {
        return precoHora;
    }

    public void setPrecoHora(BigDecimal precoHora) {
        this.precoHora = precoHora;
    }

    public Boolean getDisponibilidade() {
        return disponibilidade;
    }

    public void setDisponibilidade(Boolean disponibilidade) {
        this.disponibilidade = disponibilidade;
    }

    public List<Reserva> getReservas() {
        return reservas;
    }

    public void setReservas(List<Reserva> reservas) {
        this.reservas = reservas;
    }

    public List<Manutencao> getManutencoes() {
        return manutencoes;
    }

    public void setManutencoes(List<Manutencao> manutencoes) {
        this.manutencoes = manutencoes;
    }
}


