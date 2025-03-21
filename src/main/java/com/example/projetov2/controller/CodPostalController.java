package com.example.projetov2.controller;

import com.example.projetov2.model.CodPostal;
import com.example.projetov2.service.CodPostalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cod-postais")
public class CodPostalController {
    private final CodPostalService codPostalService;

    @Autowired
    public CodPostalController(CodPostalService codPostalService) {
        this.codPostalService = codPostalService;
    }

    @GetMapping
    public ResponseEntity<List<CodPostal>> getAllCodPostais() {
        List<CodPostal> codPostais = codPostalService.getAllCodPostais();
        return ResponseEntity.ok(codPostais);
    }

    @GetMapping("/{idCodPostal}")
    public ResponseEntity<CodPostal> getCodPostalById(@PathVariable String idCodPostal) {
        return codPostalService.getCodPostalById(idCodPostal)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<CodPostal> createCodPostal(@RequestBody CodPostal codPostal) {
        CodPostal novoCodPostal = codPostalService.createCodPostal(codPostal);
        return ResponseEntity.ok(novoCodPostal);
    }

    @PutMapping("/{idCodPostal}")
    public ResponseEntity<CodPostal> updateCodPostal(@PathVariable String idCodPostal, @RequestBody CodPostal codPostal) {
        try {
            CodPostal atualizado = codPostalService.updateCodPostal(idCodPostal, codPostal);
            return ResponseEntity.ok(atualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{idCodPostal}")
    public ResponseEntity<Void> deleteCodPostal(@PathVariable String idCodPostal) {
        codPostalService.deleteCodPostal(idCodPostal);
        return ResponseEntity.noContent().build();
    }
}
