package com.example.projetov2.controller;

import com.example.projetov2.model.Relatorio;
import com.example.projetov2.service.RelatorioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/relatorios")
public class RelatorioController {

    @Autowired
    private RelatorioService relatorioService;

    @GetMapping
    public ResponseEntity<List<Relatorio>> listarTodos() {
        return ResponseEntity.ok(relatorioService.listarTodos());
    }

    @GetMapping("/tipo/{tipoId}")
    public ResponseEntity<List<Relatorio>> getRelatoriosPorTipo(@PathVariable int tipoId) {
        List<Relatorio> relatorios = relatorioService.findByTipoId(tipoId);
        return ResponseEntity.ok(relatorios);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Relatorio> buscarPorId(@PathVariable Integer id) {
        Optional<Relatorio> relatorio = relatorioService.buscarPorId(id);
        return relatorio.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        relatorioService.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/faturacao")
    public ResponseEntity<Void> gerarRelatorioFaturacao(
            @RequestParam("de") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate de,
            @RequestParam("ate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate ate) {
        relatorioService.gerarRelatorioFaturacao(de, ate);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/utilizacao")
    public ResponseEntity<Void> gerarRelatorioUtilizacao(
            @RequestParam("de") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate de,
            @RequestParam("ate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate ate) {
        relatorioService.gerarRelatorioUtilizacao(de, ate);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/download/{id}")
    public ResponseEntity<byte[]> downloadRelatorio(@PathVariable Integer id) {
        Relatorio relatorio = relatorioService.buscarPorId(id)
                .orElseThrow(() -> new RuntimeException("Relatório não encontrado"));

        byte[] pdfBytes = relatorioService.downloadPdf(relatorio.getCaminhoPdf());

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=relatorio.pdf");

        return ResponseEntity.ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(pdfBytes);
    }
}
