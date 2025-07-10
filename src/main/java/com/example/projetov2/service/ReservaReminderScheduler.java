package com.example.projetov2.service;

import com.example.projetov2.model.Reserva;
import com.example.projetov2.repository.ReservaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Component
public class ReservaReminderScheduler {

    @Autowired
    private ReservaRepository reservaRepository;

    @Scheduled(fixedRate = 60000) // verifica a.html cada 60 segundos
    @Transactional
    public void verificarReservasParaLembrete() {
        LocalDateTime agora = LocalDateTime.now();

        List<Reserva> reservas = reservaRepository.findAll();

        for (Reserva r : reservas) {
            if (!r.getEstado().getEstado().equalsIgnoreCase("Em Processo")) continue;

            LocalDateTime dataHoraReserva = LocalDateTime.of(r.getDt(), r.gethIni());

            long horasRestantes = java.time.Duration.between(agora, dataHoraReserva).toHours();

            if (horasRestantes <= 12 && !r.isLembrete12hEnviado()) {
                r.setLembrete12hEnviado(true);
                reservaRepository.save(r);
            }

            if (horasRestantes <= 24 && !r.isLembrete24hEnviado()) {
                r.setLembrete24hEnviado(true);
                reservaRepository.save(r);
            }
        }
    }
}