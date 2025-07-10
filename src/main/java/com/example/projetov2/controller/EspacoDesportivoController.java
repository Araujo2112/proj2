package com.example.projetov2.controller;

// Importa o modelo que representa a.html entidade EspacoDesportivo
import com.example.projetov2.model.EspacoDesportivo;
// Importa o serviço que contém a.html lógica para EspacoDesportivo
import com.example.projetov2.service.EspacoDesportivoService;
// Importa classes do Spring para controlar requisições e respostas HTTP
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// Importa classes do Spring para controlar requisições e respostas HTTP
@RestController
// Define o caminho base da API para todos os métodos deste controller
@RequestMapping("/espacos-desportivos")
public class EspacoDesportivoController {

    // Define o caminho base da API para todos os métodos deste controller
    @Autowired
    private EspacoDesportivoService espacoDesportivoService;

    // GET /espacos-desportivos
    // Lista todos os espaços desportivos
    @GetMapping
    public ResponseEntity<List<EspacoDesportivo>> listarTodos() {
        List<EspacoDesportivo> espacos = espacoDesportivoService.listarTodos();
        return ResponseEntity.ok(espacos);
    }

    // GET /espacos-desportivos/{id}
    // Procura um espaço desportivo pelo seu ID
    @GetMapping("/{id}")
    public ResponseEntity<EspacoDesportivo> buscarPorId(@PathVariable Integer id) {
        // Se o espaço for encontrado, retorna OK com os dados
        // Senão, retorna 404 Not Found
        return espacoDesportivoService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // POST /espacos-desportivos
    // Cria um novo espaço desportivo
    @PostMapping
    public ResponseEntity<EspacoDesportivo> criar(@RequestBody EspacoDesportivo espacoDesportivo) {
        EspacoDesportivo criado = espacoDesportivoService.criar(espacoDesportivo);
        return ResponseEntity.ok(criado);
    }

    // PUT /espacos-desportivos/{id}
    // Atualiza os dados de um espaço desportivo existente
    @PutMapping("/{id}")
    public ResponseEntity<EspacoDesportivo> atualizar(@PathVariable Integer id, @RequestBody EspacoDesportivo espacoAtualizado) {
        try {
            EspacoDesportivo atualizado = espacoDesportivoService.atualizar(id, espacoAtualizado);
            return ResponseEntity.ok(atualizado); // Retorna OK com os dados atualizados
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build(); // Se o espaço não for encontrado, retorna 404
        }
    }

    // DELETE /espacos-desportivos/{id}
    // Remove um espaço desportivo com base no ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        espacoDesportivoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}

