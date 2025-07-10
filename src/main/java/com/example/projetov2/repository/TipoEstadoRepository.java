package com.example.projetov2.repository;

import com.example.projetov2.model.TipoEstado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TipoEstadoRepository extends JpaRepository<TipoEstado, Integer> {

    // Este método procura por estado ignorando maiúsculas/minúsculas
    Optional<TipoEstado> findByEstadoIgnoreCase(String estado);
}
