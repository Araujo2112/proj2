package com.example.projetov2.repository;

import com.example.projetov2.model.Relatorio;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RelatorioRepository extends JpaRepository<Relatorio, Integer> {
    List<Relatorio> findByIdTipo_Id(Integer tipoId);
}