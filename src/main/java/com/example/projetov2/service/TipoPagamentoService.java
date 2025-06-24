package com.example.projetov2.service;

import com.example.projetov2.model.TipoPagamento;
import com.example.projetov2.repository.TipoPagamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TipoPagamentoService {
    private final TipoPagamentoRepository tipoPagamentoRepository;

    @Autowired
    public TipoPagamentoService(TipoPagamentoRepository tipoPagamentoRepository) {
        this.tipoPagamentoRepository = tipoPagamentoRepository;
    }

    public List<TipoPagamento> getAllTipoPagamentos() {
        return tipoPagamentoRepository.findAll();
    }

    public Optional<TipoPagamento> getTipoPagamentoById(Integer idTipoPag) {
        return tipoPagamentoRepository.findById(idTipoPag);
    }

    public TipoPagamento createTipoPagamento(TipoPagamento tipoPagamento) {
        return tipoPagamentoRepository.save(tipoPagamento);
    }

    public TipoPagamento updateTipoPagamento(Integer idTipoPag, TipoPagamento updatedTipoPagamento) {
        return tipoPagamentoRepository.findById(idTipoPag).map(existingTipoPag -> {
            existingTipoPag.setNome(updatedTipoPagamento.getNome());
            return tipoPagamentoRepository.save(existingTipoPag);
        }).orElseThrow(() -> new RuntimeException("TipoPagamento com id " + idTipoPag + " não encontrado."));
    }

    public void deleteTipoPagamento(Integer idTipoPag) {
        tipoPagamentoRepository.deleteById(idTipoPag);
    }
}
