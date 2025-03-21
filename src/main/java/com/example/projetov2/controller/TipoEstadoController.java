package com.example.projetov2.controller;

import com.example.projetov2.model.TipoEstado;
import com.example.projetov2.service.TipoEstadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tipos-estados")
public class TipoEstadoController {

    private final TipoEstadoService tipoEstadoService;

    @Autowired
    public TipoEstadoController(TipoEstadoService tipoEstadoService) {
        this.tipoEstadoService = tipoEstadoService;
    }

    @GetMapping
    public ResponseEntity<List<TipoEstado>> getAllTipoEstados() {
        List<TipoEstado> tipoEstados = tipoEstadoService.getAllTipoEstados();
        return ResponseEntity.ok(tipoEstados);
    }

    @GetMapping("/{idEstado}")
    public ResponseEntity<TipoEstado> getTipoEstadoById(@PathVariable Integer idEstado) {
        return tipoEstadoService.getTipoEstadoById(idEstado)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<TipoEstado> createTipoEstado(@RequestBody TipoEstado tipoEstado) {
        TipoEstado novoTipoEstado = tipoEstadoService.createTipoEstado(tipoEstado);
        return ResponseEntity.ok(novoTipoEstado);
    }

    @PutMapping("/{idEstado}")
    public ResponseEntity<TipoEstado> updateTipoEstado(@PathVariable Integer idEstado, @RequestBody TipoEstado tipoEstado) {
        try {
            TipoEstado atualizado = tipoEstadoService.updateTipoEstado(idEstado, tipoEstado);
            return ResponseEntity.ok(atualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{idEstado}")
    public ResponseEntity<Void> deleteTipoEstado(@PathVariable Integer idEstado) {
        tipoEstadoService.deleteTipoEstado(idEstado);
        return ResponseEntity.noContent().build();
    }
}

