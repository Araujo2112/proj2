package com.example.projetov2.controller;

import com.example.projetov2.model.TipoRelatorio;
import com.example.projetov2.service.TipoRelatorioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tipo-relatorio")
public class TipoRelatorioController {

    @Autowired
    private TipoRelatorioService tipoRelatorioService;

    @GetMapping
    public ResponseEntity<List<TipoRelatorio>> getAll() {
        return ResponseEntity.ok(tipoRelatorioService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TipoRelatorio> getById(@PathVariable Integer id) {
        return tipoRelatorioService.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<TipoRelatorio> create(@RequestBody TipoRelatorio tipoRelatorio) {
        return ResponseEntity.ok(tipoRelatorioService.create(tipoRelatorio));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TipoRelatorio> update(@PathVariable Integer id, @RequestBody TipoRelatorio tipoRelatorio) {
        return ResponseEntity.ok(tipoRelatorioService.update(id, tipoRelatorio));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        tipoRelatorioService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
