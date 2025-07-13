package com.example.projetov2.repository;

import com.example.projetov2.model.EspacoDesportivo;
import com.example.projetov2.model.Reserva;
import com.example.projetov2.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Repository
public interface ReservaRepository extends JpaRepository<Reserva, Integer> {
    List<Reserva> findByUsuario(Usuario usuario);

    List<Reserva> findByUsuarioAndDtAndHIniBetween(
            Usuario usuario,
            LocalDate dt,
            LocalTime hIniStart,
            LocalTime hIniEnd
    );

    List<Reserva> findByEspacoDesportivoAndDt(EspacoDesportivo espaco, LocalDate dt);

    List<Reserva> findByUsuarioAndDt(Usuario usuario, LocalDate dt);

    List<Reserva> findByUsuarioAndEstado_EstadoNotIgnoreCase(Usuario usuario, String estado);

    List<Reserva> findByDtAndHIniBetween(LocalDate dt, LocalTime hIniStart, LocalTime hIniEnd);

    List<Reserva> findByDtBetweenAndEstado_IdEstado(LocalDate de, LocalDate ate, int idEstado);
}