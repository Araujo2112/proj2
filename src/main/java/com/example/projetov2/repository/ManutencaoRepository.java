package com.example.projetov2.repository;

import com.example.projetov2.model.EspacoDesportivo;
import com.example.projetov2.model.Manutencao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface ManutencaoRepository extends JpaRepository<Manutencao, Integer> {
    List<Manutencao> findByDtIniBetween(LocalDate dataInicio, LocalDate dataFim);

    List<Manutencao> findByEspacoDesportivoAndEstado_IdEstadoNot(EspacoDesportivo espaco, int idEstado);
}
