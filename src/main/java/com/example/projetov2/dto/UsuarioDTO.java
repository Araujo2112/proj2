package com.example.projetov2.dto;

import com.example.projetov2.model.Usuario;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({
        "idUsuario",
        "nome",
        "tel",
        "nif",
        "rua",
        "nPorta",
        "codPostal",
        "tipoUsuario",
        "reservas",
        "pagamentos",
        "notificacoes"
})
public class UsuarioDTO {

    private Integer idUsuario;
    private String nome;
    private String tel;
    private Integer nif;
    private String rua;

    @JsonProperty("nPorta")
    private Integer nPorta;

    private String codPostal;

    private Integer tipoUsuario; // Novo campo no DTO

    private int reservas;
    private int pagamentos;
    private int notificacoes;

    // Construtor que preenche tudo
    public UsuarioDTO(Usuario usuario) {
        this.idUsuario = usuario.getIdUsuario();
        this.nome = usuario.getNome();
        this.tel = usuario.getTel();
        this.nif = usuario.getNif();
        this.rua = usuario.getRua();
        this.nPorta = usuario.getNPorta();
        this.codPostal = usuario.getCodPostal() != null ? usuario.getCodPostal().getIdCodPostal() : null;
        this.tipoUsuario = usuario.getTipoUsuario() != null ? usuario.getTipoUsuario().getIdTipoUsuario() : null;
        this.reservas = usuario.getReservas() != null ? usuario.getReservas().size() : 0;
        this.pagamentos = usuario.getPagamentos() != null ? usuario.getPagamentos().size() : 0;
        this.notificacoes = usuario.getNotificacoes() != null ? usuario.getNotificacoes().size() : 0;
    }
    
    public Integer getIdUsuario() { return idUsuario; }
    public String getNome() { return nome; }
    public String getTel() { return tel; }
    public Integer getNif() { return nif; }
    public String getRua() { return rua; }

    @JsonProperty("nPorta")
    public Integer getNPorta() { return nPorta; }

    public String getCodPostal() { return codPostal; }

    public Integer getTipoUsuario() { return tipoUsuario; }

    public int getReservas() { return reservas; }
    public int getPagamentos() { return pagamentos; }
    public int getNotificacoes() { return notificacoes; }
}



