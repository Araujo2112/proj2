package com.example.projetov2.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "tipo_espaco_desportivo")
public class TipoEspacoDesportivo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tipo_espaco")
    private Integer idTipoEspaco;

    @Column(nullable = false, length = 50, unique = true)
    private String tipo;

    @OneToMany(mappedBy = "tipoEspaco", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<EspacoDesportivo> espacosDesportivos;

    public TipoEspacoDesportivo() {}

    public TipoEspacoDesportivo(Integer idTipoEspaco, String tipo) {
        this.idTipoEspaco = idTipoEspaco;
        this.tipo = tipo;
    }

    public Integer getIdTipoEspaco() {
        return idTipoEspaco;
    }

    public void setIdTipoEspaco(Integer idTipoEspaco) {
        this.idTipoEspaco = idTipoEspaco;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public List<EspacoDesportivo> getEspacosDesportivos() {
        return espacosDesportivos;
    }

    public void setEspacosDesportivos(List<EspacoDesportivo> espacosDesportivos) {
        this.espacosDesportivos = espacosDesportivos;
    }
}


