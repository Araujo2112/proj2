package com.example.projetov2.util;

import com.example.projetov2.model.Reserva;
import com.example.projetov2.repository.ReservaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class LembreteAgendado {

    @Autowired
    private ReservaRepository reservaRepo;

    private final Set<Integer> lembretesEnviados = new HashSet<>();

    @Scheduled(fixedRate = 300000) // a.html cada 5 minutos
    @Transactional
    public void verificarReservasProximas() {
        LocalDate hoje = LocalDate.now();
        LocalTime agora = LocalTime.now();
        LocalTime depois = agora.plusMinutes(30);

        List<Reserva> proximas = reservaRepo.findByDtAndHIniBetween(hoje, agora, depois);

        for (Reserva r : proximas) {
            if (r.getEstado().getEstado().equalsIgnoreCase("Em Processo") && !lembretesEnviados.contains(r.getIdReserva())) {
                String email = r.getUsuario().getEmail();
                String tipo = r.getEspacoDesportivo().getTipoEspaco().getTipo();
                String hora = r.gethIni().toString();

                lembretesEnviados.add(r.getIdReserva());
            }
        }
    }
}