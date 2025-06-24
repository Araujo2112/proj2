package com.example.projetov2.dto;

import com.example.projetov2.model.CodPostal;
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
        "tipoUsuario"
})
public class UsuarioDTO {

    private Integer idUsuario;
    private String nome;
    private String tel;
    private Integer nif;
    private String rua;

    @JsonProperty("nPorta")
    private Integer nPorta;

    private CodPostal codPostal;

    private String tipoUsuario;

    public UsuarioDTO(Usuario usuario) {
        this.idUsuario = usuario.getIdUsuario();
        this.nome = usuario.getNome();
        this.tel = usuario.getTel();
        this.nif = usuario.getNif();
        this.rua = usuario.getRua();
        this.nPorta = usuario.getNPorta();
        this.codPostal = usuario.getCodPostal();  // <-- Agora é um objeto completo
        this.tipoUsuario = usuario.getTipoUsuario() != null ? usuario.getTipoUsuario().getTipo() : null;
    }

    public Integer getIdUsuario() { return idUsuario; }
    public String getNome() { return nome; }
    public String getTel() { return tel; }
    public Integer getNif() { return nif; }
    public String getRua() { return rua; }

    @JsonProperty("nPorta")
    public Integer getNPorta() { return nPorta; }

    public CodPostal getCodPostal() { return codPostal; }

    public String getTipoUsuario() { return tipoUsuario; }
}
