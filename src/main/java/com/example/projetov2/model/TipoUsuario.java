package com.example.projetov2.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "tipoUsuario")
public class TipoUsuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idTipoUsuario;

    @Column(nullable = false, length = 100)
    private String tipo;

    // Relacionamento com Usuario
    @OneToMany(mappedBy = "tipoUsuario")
    private List<Usuario> usuarios;

    public TipoUsuario() {}

    public TipoUsuario(Integer idTipoUsuario, List<Usuario> usuarios, String tipo) {
        this.idTipoUsuario = idTipoUsuario;
        this.usuarios = usuarios;
        this.tipo = tipo;
    }

    public TipoUsuario(String tipo, List<Usuario> usuarios) {
        this.tipo = tipo;
        this.usuarios = usuarios;
    }

    public Integer getIdTipoUsuario() {
        return idTipoUsuario;
    }

    public void setIdTipoUsuario(Integer idTipoUsuario) {
        this.idTipoUsuario = idTipoUsuario;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }
}
