package com.example.projetov2.dto;

// Importações necessárias para usar outras classes e anotações
import com.example.projetov2.model.CodPostal;
import com.example.projetov2.model.Usuario;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

// Define a.html ordem em que os campos aparecerão ao converter o objeto para JSON
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

    // Campos que serão enviados na resposta JSON
    private Integer idUsuario;
    private String nome;
    private String tel;
    private Integer nif;
    private String rua;

    // Mapeia o campo Java "nPorta" para o nome "nPorta" no JSON (mantém igual, mas é útil para garantir)
    @JsonProperty("nPorta")
    private Integer nPorta;

    // Mapeia o campo Java "nPorta" para o nome "nPorta" no JSON (mantém igual, mas é útil para garantir)
    private CodPostal codPostal;

    // Representa o tipo de utilizador como String
    private String tipoUsuario;

    // Construtor que transforma um objeto Usuario (modelo) em um UsuarioDTO (formato de saída)
    public UsuarioDTO(Usuario usuario) {
        this.idUsuario = usuario.getIdUsuario();
        this.nome = usuario.getNome();
        this.tel = usuario.getTel();
        this.nif = usuario.getNif();
        this.rua = usuario.getRua();
        this.nPorta = usuario.getNPorta();
        this.codPostal = usuario.getCodPostal();  // Agora é um objeto completo, inclui o objeto CodPostal completo
        this.tipoUsuario = usuario.getTipoUsuario() != null ? usuario.getTipoUsuario().getTipo() : null; // Extrai o nome do tipo e evita erro caso seja null
    }

    // Métodos getters: usados pelo framework (Jackson) para gerar JSON
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
