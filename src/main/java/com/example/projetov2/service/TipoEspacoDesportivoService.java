package com.example.projetov2.service;

import com.example.projetov2.model.TipoEspacoDesportivo;
import com.example.projetov2.repository.TipoEspacoDesportivoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TipoEspacoDesportivoService {

    @Autowired
    private TipoEspacoDesportivoRepository tipoEspacoDesportivoRepository;

    public List<TipoEspacoDesportivo> listarTodos() {
        return tipoEspacoDesportivoRepository.findAll();
    }

    public Optional<TipoEspacoDesportivo> buscarPorId(Integer id) {
        return tipoEspacoDesportivoRepository.findById(id);
    }

    public TipoEspacoDesportivo criar(TipoEspacoDesportivo tipoEspacoDesportivo) {
        return tipoEspacoDesportivoRepository.save(tipoEspacoDesportivo);
    }

    public TipoEspacoDesportivo atualizar(Integer id, TipoEspacoDesportivo tipoAtualizado) {
        return tipoEspacoDesportivoRepository.findById(id).map(tipo -> {
            tipo.setTipo(tipoAtualizado.getTipo());
            return tipoEspacoDesportivoRepository.save(tipo);
        }).orElseThrow(() -> new RuntimeException("Tipo de Espaço Desportivo não encontrado!"));
    }

    public void deletar(Integer id) {
        tipoEspacoDesportivoRepository.deleteById(id);
    }
}

