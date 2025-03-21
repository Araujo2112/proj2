package com.example.projetov2.repository;

import com.example.projetov2.model.EspacoDesportivo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EspacoDesportivoRepository extends JpaRepository<EspacoDesportivo, Integer> {
}

