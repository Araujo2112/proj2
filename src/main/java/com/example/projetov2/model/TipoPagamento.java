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

    @OneToMany(mappedBy = "tipoPagamento")
    private List<Pagamento> pagamentos;

    public TipoPagamento() {
    }

    public TipoPagamento(Integer idTipoPag, List<Pagamento> pagamentos, String nome) {
        this.idTipoPag = idTipoPag;
        this.pagamentos = pagamentos;
        this.nome = nome;
    }

    public TipoPagamento(String nome, List<Pagamento> pagamentos) {
        this.nome = nome;
        this.pagamentos = pagamentos;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Pagamento> getPagamentos() {
        return pagamentos;
    }

    public void setPagamentos(List<Pagamento> pagamentos) {
        this.pagamentos = pagamentos;
    }

    public Integer getIdTipoPag() {
        return idTipoPag;
    }

    public void setIdTipoPag(Integer idTipoPag) {
        this.idTipoPag = idTipoPag;
    }
}
