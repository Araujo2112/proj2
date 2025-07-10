package com.example.projetov2.repository;

import com.example.projetov2.model.EspacoDesportivo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface EspacoDesportivoRepository extends JpaRepository<EspacoDesportivo, Integer> {
    // Filtra espaços disponíveis, com nome parcial do tipo
    List<EspacoDesportivo> findByDisponibilidadeTrueAndTipoEspaco_TipoContainingIgnoreCase(String tipo);
}

