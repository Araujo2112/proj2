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

    @OneToMany(mappedBy = "estado")
    private List<Reserva> reservas;

    @OneToMany(mappedBy = "estado")
    private List<Pagamento> pagamentos;

    @OneToMany(mappedBy = "estado")
    private List<Manutencao> manutencoes;

    public TipoEstado() {
    }

    public TipoEstado(String estado, List<Reserva> reservas, List<Pagamento> pagamentos, List<Manutencao> manutencoes, Integer idEstado) {
        this.estado = estado;
        this.reservas = reservas;
        this.pagamentos = pagamentos;
        this.manutencoes = manutencoes;
        this.idEstado = idEstado;
    }

    public TipoEstado(List<Manutencao> manutencoes, List<Pagamento> pagamentos, List<Reserva> reservas, String estado) {
        this.manutencoes = manutencoes;
        this.pagamentos = pagamentos;
        this.reservas = reservas;
        this.estado = estado;
    }

    public Integer getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(Integer idEstado) {
        this.idEstado = idEstado;
    }

    public List<Manutencao> getManutencoes() {
        return manutencoes;
    }

    public void setManutencoes(List<Manutencao> manutencoes) {
        this.manutencoes = manutencoes;
    }

    public List<Pagamento> getPagamentos() {
        return pagamentos;
    }

    public void setPagamentos(List<Pagamento> pagamentos) {
        this.pagamentos = pagamentos;
    }

    public List<Reserva> getReservas() {
        return reservas;
    }

    public void setReservas(List<Reserva> reservas) {
        this.reservas = reservas;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
