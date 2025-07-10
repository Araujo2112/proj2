package com.example.projetov2.repository;

import com.example.projetov2.model.EspacoDesportivo;
import com.example.projetov2.model.Reserva;
import com.example.projetov2.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Repository
public interface ReservaRepository extends JpaRepository<Reserva, Integer> {

    List<Reserva> findByUsuario(Usuario usuario);

    // 🔔 Para lembrete de reservas dentro de uma janela horária
    List<Reserva> findByUsuarioAndDtAndHIniBetween(
            Usuario usuario,
            LocalDate dt,
            LocalTime hIniStart,
            LocalTime hIniEnd
    );

    // Reservas de um espaço em certo dia
    List<Reserva> findByEspacoDesportivoAndDt(EspacoDesportivo espaco, LocalDate dt);

    // Reservas de um utilizador num certo dia
    List<Reserva> findByUsuarioAndDt(Usuario usuario, LocalDate dt);

    List<Reserva> findByUsuarioAndEstado_EstadoNotIgnoreCase(Usuario usuario, String estado);

    List<Reserva> findByDtAndHIniBetween(LocalDate dt, LocalTime hIniStart, LocalTime hIniEnd);
}