package com.example.projetov2.controller;

import com.example.projetov2.model.TipoUsuario;
import com.example.projetov2.service.TipoUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tipousuarios")
public class TipoUsuarioController {

    @Autowired
    private TipoUsuarioService tipoUsuarioService;

    // GET all tipos de usuários
    @GetMapping
    public ResponseEntity<List<TipoUsuario>> listarTodos() {
        List<TipoUsuario> tiposUsuarios = tipoUsuarioService.listarTodos();
        return ResponseEntity.ok(tiposUsuarios);
    }

    // GET tipo de usuário por id
    @GetMapping("/{id}")
    public ResponseEntity<TipoUsuario> buscarPorId(@PathVariable Integer id) {
        return tipoUsuarioService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // POST cria um novo tipo de usuário
    @PostMapping
    public ResponseEntity<TipoUsuario> criar(@RequestBody TipoUsuario tipoUsuario) {
        TipoUsuario novoTipoUsuario = tipoUsuarioService.criar(tipoUsuario);
        return ResponseEntity.ok(novoTipoUsuario);
    }

    // PUT atualiza um tipo de usuário existente
    @PutMapping("/{id}")
    public ResponseEntity<TipoUsuario> atualizar(@PathVariable Integer id, @RequestBody TipoUsuario tipoUsuarioAtualizado) {
        try {
            TipoUsuario tipoAtualizado = tipoUsuarioService.atualizar(id, tipoUsuarioAtualizado);
            return ResponseEntity.ok(tipoAtualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // DELETE um tipo de usuário
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

