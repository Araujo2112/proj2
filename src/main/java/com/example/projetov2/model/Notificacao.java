package com.example.projetov2.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "notificacao")
public class Notificacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idNotificacao;

    @ManyToOne
    @JoinColumn(name = "idUsuario", nullable = false)
    @JsonIgnore
    private Usuario usuario;

    @Column(nullable = false, length = 255)
    private String mensagem;

    @Column(name = "data_notificacao", nullable = false)
    private LocalDate dataNotificacao;

    @Column(name = "hora_notificacao", nullable = false)
    private LocalTime horaNotificacao;

    public Notificacao() {}

    public Notificacao(Integer idNotificacao, Usuario usuario, String mensagem, LocalDate dataNotificacao, LocalTime horaNotificacao) {
        this.idNotificacao = idNotificacao;
        this.usuario = usuario;
        this.mensagem = mensagem;
        this.dataNotificacao = dataNotificacao;
        this.horaNotificacao = horaNotificacao;
    }

    public Notificacao(Usuario usuario, String mensagem, LocalDate dataNotificacao, LocalTime horaNotificacao) {
        this.usuario = usuario;
        this.mensagem = mensagem;
        this.dataNotificacao = dataNotificacao;
        this.horaNotificacao = horaNotificacao;
    }

    public Integer getIdNotificacao() {
        return idNotificacao;
    }

    public void setIdNotificacao(Integer idNotificacao) {
        this.idNotificacao = idNotificacao;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public LocalDate getDataNotificacao() {
        return dataNotificacao;
    }

    public void setDataNotificacao(LocalDate dataNotificacao) {
        this.dataNotificacao = dataNotificacao;
    }

    public LocalTime getHoraNotificacao() {
        return horaNotificacao;
    }

    public void setHoraNotificacao(LocalTime horaNotificacao) {
        this.horaNotificacao = horaNotificacao;
    }
}


