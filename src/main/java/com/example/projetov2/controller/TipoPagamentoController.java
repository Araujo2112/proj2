package com.example.projetov2.controller;

import com.example.projetov2.model.TipoPagamento;
import com.example.projetov2.service.TipoPagamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tipo-pagamentos")
public class TipoPagamentoController {

    private final TipoPagamentoService tipoPagamentoService;

    @Autowired
    public TipoPagamentoController(TipoPagamentoService tipoPagamentoService) {
        this.tipoPagamentoService = tipoPagamentoService;
    }

    @PostMapping
    public ResponseEntity<TipoPagamento> createTipoPagamento(@RequestBody TipoPagamento tipoPagamento) {
        TipoPagamento novoTipoPagamento = tipoPagamentoService.createTipoPagamento(tipoPagamento);
        return ResponseEntity.ok(novoTipoPagamento);
    }

    @GetMapping
    public ResponseEntity<List<TipoPagamento>> getAllTipoPagamentos() {
        return ResponseEntity.ok(tipoPagamentoService.getAllTipoPagamentos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TipoPagamento> getTipoPagamentoById(@PathVariable Integer id) {
        return tipoPagamentoService.getTipoPagamentoById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<TipoPagamento> updateTipoPagamento(@PathVariable Integer id, @RequestBody TipoPagamento tipoPagamento) {
        return ResponseEntity.ok(tipoPagamentoService.updateTipoPagamento(id, tipoPagamento));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTipoPagamento(@PathVariable Integer id) {
        tipoPagamentoService.deleteTipoPagamento(id);
        return ResponseEntity.noContent().build();
    }
}


