package com.example.projetov2.controller;

import com.example.projetov2.model.Manutencao;
// Importa o service que contém a.html lógica para Manutencao
import com.example.projetov2.service.ManutencaoService;
// Importa utilitários do Spring para construir requisições e respostas
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController

@RequestMapping("/api/manutencoes")
public class ManutencaoController {

    @Autowired
    private ManutencaoService manutencaoService;

    @GetMapping
    public ResponseEntity<List<Manutencao>> listarTodas() {
        List<Manutencao> manutencoes = manutencaoService.listarTodas();
        return ResponseEntity.ok(manutencoes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Manutencao> buscarPorId(@PathVariable Integer id) {
        return manutencaoService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/data/{dataInicio}/{dataFim}")
    public ResponseEntity<List<Manutencao>> buscarManutencaoEntreDatas(
            @PathVariable String dataInicio, @PathVariable String dataFim) {
        try {
            LocalDate inicio = LocalDate.parse(dataInicio);
            LocalDate fim = LocalDate.parse(dataFim);
            List<Manutencao> manutencoes = manutencaoService.buscarManutencaoEntreDatas(inicio, fim);
            return ResponseEntity.ok(manutencoes);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }


    @PostMapping
    public ResponseEntity<Manutencao> criar(@RequestBody Manutencao manutencao) {
        Manutencao novaManutencao = manutencaoService.criar(manutencao);
        return ResponseEntity.ok(novaManutencao);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Manutencao> atualizar(@PathVariable Integer id, @RequestBody Manutencao manutencaoAtualizada) {
        try {
            Manutencao manutencao = manutencaoService.atualizar(id, manutencaoAtualizada);
            return ResponseEntity.ok(manutencao);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        try {
            manutencaoService.deletar(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}

