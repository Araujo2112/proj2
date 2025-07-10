package com.example.projetov2.controller;

// Importa a.html classe do modelo CodPostal
import com.example.projetov2.model.CodPostal;
// Importa o serviço responsável pela lógica relacionada ao CodPostal
import com.example.projetov2.service.CodPostalService;
// Importa anotações e classes úteis do Spring
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// Indica que esta classe é um controller REST
@RestController
// Define o caminho base para todas as rotas deste controller
@RequestMapping("/cod-postais")
public class CodPostalController {
    //o controller depende do service para executar as regras de negócio
    private final CodPostalService codPostalService;

    // Construtor com @Autowired para o Spring injetar automaticamente o CodPostalService
    @Autowired
    public CodPostalController(CodPostalService codPostalService) {
        this.codPostalService = codPostalService;
    }

    // Método que lida com requisições GET para "/cod-postais"
    // Retorna a.html lista de todos os códigos postais
    @GetMapping
    public ResponseEntity<List<CodPostal>> getAllCodPostais() {
        List<CodPostal> codPostais = codPostalService.getAllCodPostais();
        return ResponseEntity.ok(codPostais);
    }

    // Método que lida com GET para "/cod-postais/{idCodPostal}"
    // Retorna um código postal específico pelo ID
    @GetMapping("/{idCodPostal}")
    public ResponseEntity<CodPostal> getCodPostalById(@PathVariable String idCodPostal) {
        return codPostalService.getCodPostalById(idCodPostal)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Método que lida com POST para "/cod-postais"
    // Cria um novo código postal
    @PostMapping
    public ResponseEntity<CodPostal> createCodPostal(@RequestBody CodPostal codPostal) {
        CodPostal novoCodPostal = codPostalService.createCodPostal(codPostal);
        return ResponseEntity.ok(novoCodPostal);
    }

    // Método que lida com PUT para "/cod-postais/{idCodPostal}"
    // Atualiza um código postal existente com base no ID
    @PutMapping("/{idCodPostal}")
    public ResponseEntity<CodPostal> updateCodPostal(@PathVariable String idCodPostal, @RequestBody CodPostal codPostal) {
        try {
            CodPostal atualizado = codPostalService.updateCodPostal(idCodPostal, codPostal);
            return ResponseEntity.ok(atualizado); // Retorna OK com os dados atualizados
        } catch (RuntimeException e) {
            // Se o ID não existir, retorna 404 Not Found
            return ResponseEntity.notFound().build();
        }
    }

    // Método que lida com DELETE para "/cod-postais/{idCodPostal}"
    // Remove o código postal do sistema
    @DeleteMapping("/{idCodPostal}")
    public ResponseEntity<Void> deleteCodPostal(@PathVariable String idCodPostal) {
        codPostalService.deleteCodPostal(idCodPostal);
        return ResponseEntity.noContent().build();
    }
}
