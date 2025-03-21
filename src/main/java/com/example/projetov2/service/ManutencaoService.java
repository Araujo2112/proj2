package com.example.projetov2.service;

import com.example.projetov2.model.Manutencao;
import com.example.projetov2.repository.ManutencaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ManutencaoService {

    @Autowired
    private ManutencaoRepository manutencaoRepository;

    public List<Manutencao> listarTodas() {
        return manutencaoRepository.findAll();
    }

    public Optional<Manutencao> buscarPorId(Integer id) {
        return manutencaoRepository.findById(id);
    }

    public Manutencao criar(Manutencao manutencao) {
        return manutencaoRepository.save(manutencao);
    }

    public Manutencao atualizar(Integer id, Manutencao manutencaoAtualizada) {
        Optional<Manutencao> manutencaoExistente = manutencaoRepository.findById(id);

        if (manutencaoExistente.isPresent()) {
            Manutencao manutencao = manutencaoExistente.get();

            manutencao.setDtIni(manutencaoAtualizada.getDtIni());
            manutencao.setDtFim(manutencaoAtualizada.getDtFim());
            manutencao.setDescricao(manutencaoAtualizada.getDescricao());
            manutencao.setEstado(manutencaoAtualizada.getEstado());
            manutencao.setEspacoDesportivo(manutencaoAtualizada.getEspacoDesportivo());

            return manutencaoRepository.save(manutencao);
        } else {
            throw new RuntimeException("Manutenção com ID " + id + " não encontrada.");
        }
    }

    public void deletar(Integer id) {
        if (manutencaoRepository.existsById(id)) {
            manutencaoRepository.deleteById(id);
        } else {
            throw new RuntimeException("Manutenção com ID " + id + " não encontrada.");
        }
    }
}
