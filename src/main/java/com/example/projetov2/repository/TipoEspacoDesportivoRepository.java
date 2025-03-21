package com.example.projetov2.repository;

import com.example.projetov2.model.TipoEspacoDesportivo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoEspacoDesportivoRepository extends JpaRepository<TipoEspacoDesportivo, Integer> {
}

