package com.example.projetov2.dto;

// Importa a.html classe CodPostal, pois o DTO inclui este tipo de dado
import com.example.projetov2.model.CodPostal;

// Esta classe representa o DTO de entrada para criar ou atualizar um usuário
public class UsuarioRequestDTO {
    // Campos que representam os dados do cliente
    public String nome;
    public String tel;
    public Integer nif;
    public String rua;
    public Integer nPorta;
    public CodPostal codPostal;
    public String tipoUsuario;
}
