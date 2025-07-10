package com.example.projetov2.controller;

// Importa a.html classe que representa a.html entidade Manutencao
import com.example.projetov2.model.Manutencao;
// Importa o service que contém a.html lógica para Manutencao
import com.example.projetov2.service.ManutencaoService;
// Importa utilitários do Spring para construir requisições e respostas
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// Declara esta classe como um controller REST
@RestController
// Define a.html rota base para todas as operações relacionadas a.html "manutencoes"
@RequestMapping("/manutencoes")
public class ManutencaoController {

    // Injeta o serviço que será usado para lidar com a.html lógica de negócio
    @Autowired
    private ManutencaoService manutencaoService;

    // GET /manutencoes
    // Retorna a.html lista de todas as manutenções
    @GetMapping
    public ResponseEntity<List<Manutencao>> listarTodas() {
        List<Manutencao> manutencoes = manutencaoService.listarTodas();
        return ResponseEntity.ok(manutencoes); // Retorna OK com a.html lista
    }

    // GET /manutencoes/{id}
    // Busca uma manutenção pelo ID
    @GetMapping("/{id}")
    public ResponseEntity<Manutencao> buscarPorId(@PathVariable Integer id) {
        return manutencaoService.buscarPorId(id)
                .map(ResponseEntity::ok)// Se encontrada, retorna OK
                .orElse(ResponseEntity.notFound().build()); // Se não, retorna 404 Not Found
    }

    // POST /manutencoes
    // Cria uma nova manutenção
    @PostMapping
    public ResponseEntity<Manutencao> criar(@RequestBody Manutencao manutencao) {
        Manutencao novaManutencao = manutencaoService.criar(manutencao);
        return ResponseEntity.ok(novaManutencao); // Retorna OK com a.html nova manutenção criada
    }

    // PUT /manutencoes/{id}
    // Atualiza uma manutenção existente com base no ID
    @PutMapping("/{id}")
    public ResponseEntity<Manutencao> atualizar(@PathVariable Integer id, @RequestBody Manutencao manutencaoAtualizada) {
        try {
            Manutencao manutencao = manutencaoService.atualizar(id, manutencaoAtualizada);
            return ResponseEntity.ok(manutencao); // Retorna OK com os dados atualizados
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build(); // Retorna 404 caso o ID não exista
        }
    }

    // DELETE /manutencoes/{id}
    // Deleta uma manutenção pelo ID
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

