package com.example.projetov2.model;

// Importações necessárias
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.time.LocalDate;

// Define que essa classe representa uma entidade do JPA
@Entity
// Define o nome da tabela na BD
@Table(name = "manutencao")
public class Manutencao {

    // Identificador único da manutenção (chave primária com autoincremento)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idManu;

    // Data de início da manutenção (obrigatória)
    @Column(nullable = false)
    private LocalDate dtIni;

    // Data de fim da manutenção (pode ser nula se estiver em andamento)
    private LocalDate dtFim;

    // Texto descritivo sobre a.html manutenção
    private String descricao;

    // Estado da manutenção — obrigatório
    @ManyToOne
    @JoinColumn(name = "idEstado", nullable = false)
    @JsonIgnore
    private TipoEstado estado;

    // Espaço desportivo que está em manutenção — obrigatório
    @ManyToOne
    @JoinColumn(name = "idEspaco", nullable = false)
    @JsonIgnore
    private EspacoDesportivo espacoDesportivo;

    // Usuário responsável pela manutenção — obrigatório
    @ManyToOne
    @JoinColumn(name = "idUsuario", nullable = false)
    @JsonIgnore
    private Usuario usuario;

    // Custo total da manutenção (obrigatório)
    @Column(nullable = false)
    private Double custo;

    // Construtor padrão obrigatório para JPA
    public Manutencao() {
    }

    // Construtor com todos os campos (inclusive ID)
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

    // Construtor sem o ID (usado ao criar uma nova manutenção)
    public Manutencao(LocalDate dtIni, String descricao, LocalDate dtFim, TipoEstado estado, EspacoDesportivo espacoDesportivo, Usuario usuario, Double custo) {
        this.dtIni = dtIni;
        this.descricao = descricao;
        this.dtFim = dtFim;
        this.estado = estado;
        this.espacoDesportivo = espacoDesportivo;
        this.usuario = usuario;
        this.custo = custo;
    }

    // Getters and Setters
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

