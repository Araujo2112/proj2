package com.example.projetov2.service;

import com.example.projetov2.model.Reserva;
import com.example.projetov2.model.TipoEstado;
import com.example.projetov2.repository.ReservaRepository;
import com.example.projetov2.repository.TipoEstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReservaService {

    @Autowired
    private ReservaRepository reservaRepository;

    @Autowired
    private PagamentoService pagamentoService;

    @Autowired
    private TipoEstadoRepository tipoEstadoRepository;

    public List<Reserva> listarTodas() {
        return reservaRepository.findAll();
    }

    public Optional<Reserva> buscarPorId(Integer id) {
        return reservaRepository.findById(id);
    }

    public Reserva criar(Reserva reserva) {
        Reserva novaReserva = reservaRepository.save(reserva);
        TipoEstado estadoEmEspera = tipoEstadoRepository.findById(3).orElseThrow();
        pagamentoService.criarPagamentoEmEspera(novaReserva, estadoEmEspera);

        return novaReserva;
    }

    public Reserva atualizar(Integer id, Reserva reservaAtualizada) {
        return reservaRepository.findById(id).map(reserva -> {
            reserva.sethIni(reservaAtualizada.gethIni());
            reserva.sethFim(reservaAtualizada.gethFim());
            reserva.setEstado(reservaAtualizada.getEstado());
            reserva.setUsuario(reservaAtualizada.getUsuario());
            reserva.setEspacoDesportivo(reservaAtualizada.getEspacoDesportivo());
            return reservaRepository.save(reserva);
        }).orElseThrow(() -> new RuntimeException("Reserva não encontrada!"));
    }

    public void deletar(Integer id) {
        reservaRepository.deleteById(id);
    }
}

