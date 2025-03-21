package com.example.projetov2.service;

import com.example.projetov2.model.Notificacao;
import com.example.projetov2.repository.NotificacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
public class NotificacaoService {

    @Autowired
    private NotificacaoRepository notificacaoRepository;

    public List<Notificacao> listarTodas() {
        return notificacaoRepository.findAll();
    }

    public Optional<Notificacao> buscarPorId(Integer id) {
        return notificacaoRepository.findById(id);
    }

    public Notificacao criar(Notificacao notificacao) {
        // Define data e hora atuais automaticamente
        notificacao.setDataNotificacao(LocalDate.now());
        notificacao.setHoraNotificacao(LocalTime.now());

        return notificacaoRepository.save(notificacao);
    }

    public Notificacao atualizar(Integer id, Notificacao notificacaoAtualizada) {
        Optional<Notificacao> notificacaoExistente = notificacaoRepository.findById(id);

        if (notificacaoExistente.isPresent()) {
            Notificacao notificacao = notificacaoExistente.get();

            notificacao.setUsuario(notificacaoAtualizada.getUsuario());
            notificacao.setMensagem(notificacaoAtualizada.getMensagem());

            // Se quiseres atualizar data/hora manualmente, descomenta isso:
            // notificacao.setDataNotificacao(notificacaoAtualizada.getDataNotificacao());
            // notificacao.setHoraNotificacao(notificacaoAtualizada.getHoraNotificacao());

            return notificacaoRepository.save(notificacao);
        } else {
            throw new RuntimeException("Notificação com ID " + id + " não encontrada.");
        }
    }

    public void deletar(Integer id) {
        if (notificacaoRepository.existsById(id)) {
            notificacaoRepository.deleteById(id);
        } else {
            throw new RuntimeException("Notificação com ID " + id + " não encontrada.");
        }
    }
}



