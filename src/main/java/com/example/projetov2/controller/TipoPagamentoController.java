package com.example.projetov2.controller;

import com.example.projetov2.model.TipoPagamento;
import com.example.projetov2.service.TipoPagamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tipos-pagamentos")
public class TipoPagamentoController {

    private final TipoPagamentoService tipoPagamentoService;

    @Autowired
    public TipoPagamentoController(TipoPagamentoService tipoPagamentoService) {
        this.tipoPagamentoService = tipoPagamentoService;
    }

    @GetMapping
    public ResponseEntity<List<TipoPagamento>> getAllTipoPagamentos() {
        List<TipoPagamento> tipoPagamentos = tipoPagamentoService.getAllTipoPagamentos();
        return ResponseEntity.ok(tipoPagamentos);
    }

    @GetMapping("/{idTipoPag}")
    public ResponseEntity<TipoPagamento> getTipoPagamentoById(@PathVariable Integer idTipoPag) {
        return tipoPagamentoService.getTipoPagamentoById(idTipoPag)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<TipoPagamento> createTipoPagamento(@RequestBody TipoPagamento tipoPagamento) {
        TipoPagamento novoTipoPagamento = tipoPagamentoService.createTipoPagamento(tipoPagamento);
        return ResponseEntity.ok(novoTipoPagamento);
    }

    @PutMapping("/{idTipoPag}")
    public ResponseEntity<TipoPagamento> updateTipoPagamento(@PathVariable Integer idTipoPag, @RequestBody TipoPagamento tipoPagamento) {
        try {
            TipoPagamento atualizado = tipoPagamentoService.updateTipoPagamento(idTipoPag, tipoPagamento);
            return ResponseEntity.ok(atualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{idTipoPag}")
    public ResponseEntity<Void> deleteTipoPagamento(@PathVariable Integer idTipoPag) {
        tipoPagamentoService.deleteTipoPagamento(idTipoPag);
        return ResponseEntity.noContent().build();
    }
}

