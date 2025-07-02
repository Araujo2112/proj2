package com.example.projetov2.service;

import com.example.projetov2.model.TipoRelatorio;
import com.example.projetov2.repository.TipoRelatorioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TipoRelatorioService {

    @Autowired
    private TipoRelatorioRepository tipoRelatorioRepository;

    public List<TipoRelatorio> getAll() {
        return tipoRelatorioRepository.findAll();
    }

    public Optional<TipoRelatorio> getById(Integer id) {
        return tipoRelatorioRepository.findById(id);
    }

    public TipoRelatorio create(TipoRelatorio tipoRelatorio) {
        return tipoRelatorioRepository.save(tipoRelatorio);
    }

    public TipoRelatorio update(Integer id, TipoRelatorio updated) {
        return tipoRelatorioRepository.findById(id)
                .map(tr -> {
                    tr.setNome(updated.getNome());
                    return tipoRelatorioRepository.save(tr);
                })
                .orElseThrow(() -> new RuntimeException("TipoRelatorio não encontrado com id " + id));
    }

    public void delete(Integer id) {
        tipoRelatorioRepository.deleteById(id);
    }
}
