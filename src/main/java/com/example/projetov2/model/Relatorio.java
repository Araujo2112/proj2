package com.example.projetov2.model;

import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDate;

@Entity
@Table(name = "relatorio")
public class Relatorio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Integer id;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "id_tipo", nullable = false)
    private TipoRelatorio idTipo;

    @Column(name = "data_geracao", nullable = false)
    private LocalDate dataGeracao;

    @Column(name = "data_inicio", nullable = false)
    private LocalDate dataInicio;

    @Column(name = "data_fim", nullable = false)
    private LocalDate dataFim;

    @Column(name = "caminho_pdf", nullable = false, length = Integer.MAX_VALUE)
    private String caminhoPdf;

    @Column(name = "descricao", nullable = true, length = 255)
    private String descricao;

    @Column(name = "data_criacao", nullable = true)
    private LocalDate dataCriacao;

    public Relatorio() {
    }

    public Relatorio(Integer id, TipoRelatorio idTipo, LocalDate dataGeracao, LocalDate dataInicio, LocalDate dataFim,
                     String caminhoPdf, String descricao, LocalDate dataCriacao) {
        this.id = id;
        this.idTipo = idTipo;
        this.dataGeracao = dataGeracao;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.caminhoPdf = caminhoPdf;
        this.descricao = descricao;
        this.dataCriacao = dataCriacao;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public TipoRelatorio getIdTipo() {
        return idTipo;
    }

    public void setIdTipo(TipoRelatorio idTipo) {
        this.idTipo = idTipo;
    }

    public LocalDate getDataGeracao() {
        return dataGeracao;
    }

    public void setDataGeracao(LocalDate dataGeracao) {
        this.dataGeracao = dataGeracao;
    }

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }

    public LocalDate getDataFim() {
        return dataFim;
    }

    public void setDataFim(LocalDate dataFim) {
        this.dataFim = dataFim;
    }

    public String getCaminhoPdf() {
        return caminhoPdf;
    }

    public void setCaminhoPdf(String caminhoPdf) {
        this.caminhoPdf = caminhoPdf;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDate getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDate dataCriacao) {
        this.dataCriacao = dataCriacao;
    }
}
