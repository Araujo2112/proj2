package com.example.projetov2.controller;

import com.example.projetov2.model.EspacoDesportivo;
// Importa o serviço que contém a.html lógica para EspacoDesportivo
import com.example.projetov2.service.EspacoDesportivoService;
// Importa classes do Spring para controlar requisições e respostas HTTP
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

@RequestMapping("/api/espacos-desportivos")
public class EspacoDesportivoController {

    @Autowired
    private EspacoDesportivoService espacoDesportivoService;

    @GetMapping
    public ResponseEntity<List<EspacoDesportivo>> listarTodos() {
        List<EspacoDesportivo> espacos = espacoDesportivoService.listarTodos();
        return ResponseEntity.ok(espacos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EspacoDesportivo> buscarPorId(@PathVariable Integer id) {

        return espacoDesportivoService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<EspacoDesportivo> criar(@RequestBody EspacoDesportivo espacoDesportivo) {
        EspacoDesportivo criado = espacoDesportivoService.criar(espacoDesportivo);
        System.out.println("JSON recebido: " + espacoDesportivo);
        return ResponseEntity.ok(criado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EspacoDesportivo> atualizar(@PathVariable Integer id, @RequestBody EspacoDesportivo espacoAtualizado) {
        try {
            EspacoDesportivo atualizado = espacoDesportivoService.atualizar(id, espacoAtualizado);
            return ResponseEntity.ok(atualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        espacoDesportivoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}

