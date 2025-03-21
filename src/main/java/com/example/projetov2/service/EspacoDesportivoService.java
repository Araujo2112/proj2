package com.example.projetov2.service;

import com.example.projetov2.model.EspacoDesportivo;
import com.example.projetov2.repository.EspacoDesportivoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EspacoDesportivoService {

    @Autowired
    private EspacoDesportivoRepository espacoDesportivoRepository;

    public List<EspacoDesportivo> listarTodos() {
        return espacoDesportivoRepository.findAll();
    }

    public Optional<EspacoDesportivo> buscarPorId(Integer id) {
        return espacoDesportivoRepository.findById(id);
    }

    public EspacoDesportivo criar(EspacoDesportivo espacoDesportivo) {
        return espacoDesportivoRepository.save(espacoDesportivo);
    }

    public EspacoDesportivo atualizar(Integer id, EspacoDesportivo espacoAtualizado) {
        return espacoDesportivoRepository.findById(id).map(espaco -> {
            espaco.setCapacidade(espacoAtualizado.getCapacidade());
            espaco.setDisponibilidade(espacoAtualizado.getDisponibilidade());
            espaco.setLote(espacoAtualizado.getLote());
            espaco.setPrecoHora(espacoAtualizado.getPrecoHora());
            espaco.setTipoEspaco(espacoAtualizado.getTipoEspaco());
            return espacoDesportivoRepository.save(espaco);
        }).orElseThrow(() -> new RuntimeException("Espaço Desportivo não encontrado!"));
    }

    public void deletar(Integer id) {
        espacoDesportivoRepository.deleteById(id);
    }
}

