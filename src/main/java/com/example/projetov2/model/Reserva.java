package com.example.projetov2.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "reserva")
public class Reserva {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idReserva;

    @Column(nullable = false)
    private LocalDate dt;

    @Column(nullable = false)
    private LocalTime hIni;

    @Column(nullable = false)
    private LocalTime hFim;

    @Column(nullable = false)
    private boolean lembrete12hEnviado = false;

    @Column(nullable = false)
    private boolean lembrete24hEnviado = false;

    @ManyToOne
    @JoinColumn(name = "idEstado", nullable = false)
    private TipoEstado estado;

    @ManyToOne
    @JoinColumn(name = "idEspaco", nullable = false)
    private EspacoDesportivo espacoDesportivo;

    @ManyToOne
    @JoinColumn(name = "idUsuario", nullable = false)
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "id_tipo_pag", nullable = false)
    private TipoPagamento tipoPagamento;

    public Reserva() {
    }

    public Reserva(Integer idReserva, LocalDate dt, LocalTime hIni, LocalTime hFim, TipoEstado estado,
                   EspacoDesportivo espacoDesportivo, Usuario usuario, TipoPagamento tipoPagamento) {
        this.idReserva = idReserva;
        this.dt = dt;
        this.hIni = hIni;
        this.hFim = hFim;
        this.estado = estado;
        this.espacoDesportivo = espacoDesportivo;
        this.usuario = usuario;
        this.tipoPagamento = tipoPagamento;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public EspacoDesportivo getEspacoDesportivo() {
        return espacoDesportivo;
    }

    public void setEspacoDesportivo(EspacoDesportivo espacoDesportivo) {
        this.espacoDesportivo = espacoDesportivo;
    }

    public TipoEstado getEstado() {
        return estado;
    }

    public void setEstado(TipoEstado estado) {
        this.estado = estado;
    }

    public LocalTime gethFim() {
        return hFim;
    }

    public void sethFim(LocalTime hFim) {
        this.hFim = hFim;
    }

    public LocalDate getDt() {
        return dt;
    }

    public void setDt(LocalDate dt) {
        this.dt = dt;
    }

    public LocalTime gethIni() {
        return hIni;
    }

    public void sethIni(LocalTime hIni) {
        this.hIni = hIni;
    }

    public Integer getIdReserva() {
        return idReserva;
    }

    public void setIdReserva(Integer idReserva) {
        this.idReserva = idReserva;
    }

    public TipoPagamento getTipoPagamento() {
        return tipoPagamento;
    }

    public void setTipoPagamento(TipoPagamento tipoPagamento) {
        this.tipoPagamento = tipoPagamento;
    }

    public boolean isLembrete12hEnviado() {
        return lembrete12hEnviado;
    }

    public void setLembrete12hEnviado(boolean lembrete12hEnviado) {
        this.lembrete12hEnviado = lembrete12hEnviado;
    }

    public boolean isLembrete24hEnviado() {
        return lembrete24hEnviado;
    }

    public void setLembrete24hEnviado(boolean lembrete24hEnviado) {
        this.lembrete24hEnviado = lembrete24hEnviado;
    }
}