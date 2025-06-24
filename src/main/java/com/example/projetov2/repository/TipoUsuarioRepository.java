package com.example.projetov2.repository;

import com.example.projetov2.model.TipoUsuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TipoUsuarioRepository extends JpaRepository<TipoUsuario, Integer> {
    Optional<TipoUsuario> findByTipoIgnoreCase(String tipo);
}
