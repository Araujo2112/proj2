package com.example.projetov2.controller;

import com.example.projetov2.model.Notificacao;
import com.example.projetov2.service.NotificacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@RestController
@RequestMapping("/notificacoes")
public class NotificacaoController {

    @Autowired
    private NotificacaoService notificacaoService;

    @GetMapping
    public ResponseEntity<List<Notificacao>> listarTodas() {
        List<Notificacao> notificacoes = notificacaoService.listarTodas();
        return ResponseEntity.ok(notificacoes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Notificacao> buscarPorId(@PathVariable Integer id) {
        return notificacaoService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Notificacao> criar(@RequestBody Notificacao notificacao) {
        // Definindo a data e hora da notificação automaticamente
        notificacao.setDataNotificacao(LocalDate.now());
        notificacao.setHoraNotificacao(LocalTime.now());

        Notificacao novaNotificacao = notificacaoService.criar(notificacao);
        return ResponseEntity.ok(novaNotificacao);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Notificacao> atualizar(@PathVariable Integer id, @RequestBody Notificacao notificacaoAtualizada) {
        try {
            Notificacao notificacao = notificacaoService.atualizar(id, notificacaoAtualizada);
            return ResponseEntity.ok(notificacao);
        } catch (RuntimeException e) {