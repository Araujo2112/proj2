package com.example.projetov2.service;

import com.example.projetov2.model.Pagamento;
import com.example.projetov2.repository.PagamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PagamentoService {

    @Autowired
    private PagamentoRepository pagamentoRepository;

    // Listar todos os pagamentos
    public List<Pagamento> listarTodos() {
        return pagamentoRepository.findAll();
    }

    // Buscar pagamento por ID
    public Optional<Pagamento> buscarPorId(Integer id) {
        return pagamentoRepository.findById(id);
    }

    // Criar um novo pagamento
    public Pagamento criar(Pagamento pagamento) {
        return pagamentoRepository.save(pagamento);
    }

    // Atualizar um pagamento existente
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

    // Deletar um pagamento pelo ID
    public void deletar(Integer id) {
        if (pagamentoRepository.existsById(id)) {
            pagamentoRepository.deleteById(id);
        } else {
            throw new RuntimeException("Pagamento com ID " + id + " não encontrado.");
        }
    }
}

