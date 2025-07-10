package com.example.projetov2.controller;

import com.example.projetov2.model.TipoUsuario;
import com.example.projetov2.service.TipoUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tipos-usuarios")
public class TipoUsuarioController {
    @Autowired
    private TipoUsuarioService tipoUsuarioService;

    @GetMapping
    public ResponseEntity<List<TipoUsuario>> listarTodos() {
        List<TipoUsuario> tiposUsuarios = tipoUsuarioService.listarTodos();
        return ResponseEntity.ok(tiposUsuarios);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TipoUsuario> buscarPorId(@PathVariable Integer id) {
        return tipoUsuarioService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<TipoUsuario> criar(@RequestBody TipoUsuario tipoUsuario) {
        TipoUsuario novoTipoUsuario = tipoUsuarioService.criar(tipoUsuario);
        return ResponseEntity.ok(novoTipoUsuario);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TipoUsuario> atualizar(@PathVariable Integer id, @RequestBody TipoUsuario tipoUsuarioAtualizado) {
        try {
            TipoUsuario tipoAtualizado = tipoUsuarioService.atualizar(id, tipoUsuarioAtualizado);
            return ResponseEntity.ok(tipoAtualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        try {
            tipoUsuarioService.deletar(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}

