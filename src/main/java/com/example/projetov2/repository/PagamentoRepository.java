package com.example.projetov2.repository;

import com.example.projetov2.model.Pagamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface PagamentoRepository extends JpaRepository<Pagamento, Integer> {
    List<Pagamento> findByEstado_EstadoAndDtPagamentoBetween(String estado, LocalDate de, LocalDate ate);
    Optional<Pagamento> findByReserva_IdReserva(Integer idReserva);
}
