package com.example.projetov2.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idUsuario;

    @Column(nullable = false, length = 100)
    private String nome;

    @Column(nullable = false, length = 15)
    private String tel;

    @Column(nullable = false)
    private Integer nif;

    @Column(length = 255)
    private String rua;

    private Integer nPorta;

    @JsonIgnoreProperties("usuario")
    @ManyToOne
    @JoinColumn(name = "codPostal", nullable = false)
    @JsonIgnore
    private CodPostal codPostal;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idTipoUsuario", nullable = false)
    @JsonIgnore
    private TipoUsuario tipoUsuario;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Reserva> reservas;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Pagamento> pagamentos;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Notificacao> notificacoes;

    public Usuario() {}

    public Usuario(String nome, String tel, Integer nif) {
        this.nome = nome;
        this.tel = tel;
        this.nif = nif;
    }

    public Usuario(String nome, String tel, Integer nif, String rua, Integer nPorta, CodPostal codPostal, TipoUsuario tipoUsuario, List<Notificacao> notificacoes, List<Reserva> reservas, List<Pagamento> pagamentos) {
        this.nome = nome;
        this.tel = tel;
        this.nif = nif;
        this.rua = rua;
        this.nPorta = nPorta;
        this.codPostal = codPostal;
        this.tipoUsuario = tipoUsuario;
        this.notificacoes = notificacoes;
        this.reservas = reservas;
        this.pagamentos = pagamentos;
    }

    public Usuario(Integer idUsuario, String nome, String tel, Integer nif, String rua, Integer nPorta, CodPostal codPostal, TipoUsuario tipoUsuario, List<Reserva> reservas, List<Pagamento> pagamentos, List<Notificacao> notificacoes) {
        this.idUsuario = idUsuario;
        this.nome = nome;
        this.tel = tel;
        this.nif = nif;
        this.rua = rua;
        this.nPorta = nPorta;
        this.codPostal = codPostal;
        this.tipoUsuario = tipoUsuario;
        this.reservas = reservas;
        this.pagamentos = pagamentos;
        this.notificacoes = notificacoes;
    }


    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public Integer getNif() {
        return nif;
    }

    public void setNif(Integer nif) {
        this.nif = nif;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public Integer getNPorta() {
        return nPorta;
    }

    public void setNPorta(Integer nPorta) {
        this.nPorta = nPorta;
    }

    public CodPostal getCodPostal() {
        return codPostal;
    }

    public void setCodPostal(CodPostal codPostal) {
        this.codPostal = codPostal;
    }

    public TipoUsuario getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(TipoUsuario tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public List<Reserva> getReservas() {
        return reservas;
    }

    public void setReservas(List<Reserva> reservas) {
        this.reservas = reservas;
    }

    public List<Pagamento> getPagamentos() {
        return pagamentos;
    }

    public void setPagamentos(List<Pagamento> pagamentos) {
        this.pagamentos = pagamentos;
    }

    public List<Notificacao> getNotificacoes() {
        return notificacoes;
    }

    public void setNotificacoes(List<Notificacao> notificacoes) {
        this.notificacoes = notificacoes;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "idUsuario=" + idUsuario +
                ", nome='" + nome + '\'' +
                ", tel='" + tel + '\'' +
                ", nif=" + nif +
                ", rua='" + rua + '\'' +
                ", nPorta=" + nPorta +
                ", codPostal=" + (codPostal != null ? codPostal.getIdCodPostal() : null) +
                ", tipoUsuario=" + (tipoUsuario != null ? tipoUsuario.getIdTipoUsuario() : null) +
                ", reservas=" + (reservas != null ? reservas.size() : 0) +
                ", pagamentos=" + (pagamentos != null ? pagamentos.size() : 0) +
                ", notificacoes=" + (notificacoes != null ? notificacoes.size() : 0) +
                '}';
    }
}
