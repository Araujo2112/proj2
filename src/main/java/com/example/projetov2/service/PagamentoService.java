package com.example.projetov2.service;

import com.example.projetov2.model.Pagamento;
import com.example.projetov2.model.Reserva;
import com.example.projetov2.model.TipoEstado;
import com.example.projetov2.repository.PagamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PagamentoService {

    @Autowired
    private PagamentoRepository pagamentoRepository;

    public List<Pagamento> listarTodos() {
        return pagamentoRepository.findAll();
    }

    public Optional<Pagamento> buscarPorId(Integer id) {
        return pagamentoRepository.findById(id);
    }

    public Pagamento criar(Pagamento pagamento) {
        return pagamentoRepository.save(pagamento);
    }

    public Pagamento atualizar(Integer id, Pagamento pagamentoAtualizado) {
        Optional<Pagamento> pagamentoExistente = pagamentoRepository.findById(id);

        if (pagamentoExistente.isPresent()) {
            Pagamento pagamento = pagamentoExistente.get();

            pagamento.setDtPagamento(pagamentoAtualizado.getDtPagamento());
            pagamento.setTipoPagamento(pagamentoAtualizado.getTipoPagamento());
            pagamento.setUsuario(pagamentoAtualizado.getUsuario());
            pagamento.setEstado(pagamentoAtualizado.getEstado());
            pagamento.setReserva(pagamentoAtualizado.getReserva());

            return pagamentoRepository.save(pagamento);
        } else {
            throw new RuntimeException("Pagamento com ID " + id + " não encontrado.");
        }
    }

    public void deletar(Integer id) {
        if (pagamentoRepository.existsById(id)) {
            pagamentoRepository.deleteById(id);
        } else {
            throw new RuntimeException("Pagamento com ID " + id + " não encontrado.");
        }
    }

    public void criarPagamentoEmEspera(Reserva reserva, TipoEstado estado) {
        Pagamento pagamento = new Pagamento();
        pagamento.setReserva(reserva);
        pagamento.setUsuario(reserva.getUsuario());
        pagamento.setEstado(estado);
        pagamento.setDtPagamento(null);
        pagamento.setTipoPagamento(null);

        pagamentoRepository.save(pagamento);
    }
}

