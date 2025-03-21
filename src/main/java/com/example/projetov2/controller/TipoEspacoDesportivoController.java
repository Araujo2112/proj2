package com.example.projetov2.controller;

import com.example.projetov2.model.TipoEspacoDesportivo;
import com.example.projetov2.service.TipoEspacoDesportivoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tipos-espacos")
public class TipoEspacoDesportivoController {

    @Autowired
    private TipoEspacoDesportivoService tipoEspacoDesportivoService;

    // Listar todos os tipos de espaço desportivo
    @GetMapping
    public ResponseEntity<List<TipoEspacoDesportivo>> listarTodos() {
        List<TipoEspacoDesportivo> tiposEspaco = tipoEspacoDesportivoService.listarTodos();
        return ResponseEntity.ok(tiposEspaco);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TipoEspacoDesportivo> buscarPorId(@PathVariable Integer id) {
        Optional<TipoEspacoDesportivo> tipoEspaco = tipoEspacoDesportivoService.buscarPorId(id);
        return tipoEspaco.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping
    public ResponseEntity<TipoEspacoDesportivo> criar(@RequestBody TipoEspacoDesportivo tipoEspacoDesportivo) {
        TipoEspacoDesportivo tipoCriado = tipoEspacoDesportivoService.criar(tipoEspacoDesportivo);
        return ResponseEntity.status(HttpStatus.CREATED).body(tipoCriado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TipoEspacoDesportivo> atualizar(@PathVariable Integer id,
                                                          @RequestBody TipoEspacoDesportivo tipoEspacoDesportivo) {
        try {
            TipoEspacoDesportivo tipoAtualizado = tipoEspacoDesportivoService.atualizar(id, tipoEspacoDesportivo);
            return ResponseEntity.ok(tipoAtualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        tipoEspacoDesportivoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
