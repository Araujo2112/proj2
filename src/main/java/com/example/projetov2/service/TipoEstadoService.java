package com.example.projetov2.service;

import com.example.projetov2.model.TipoEstado;
import com.example.projetov2.repository.TipoEstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TipoEstadoService {

    private final TipoEstadoRepository tipoEstadoRepository;

    @Autowired
    public TipoEstadoService(TipoEstadoRepository tipoEstadoRepository) {
        this.tipoEstadoRepository = tipoEstadoRepository;
    }

    public List<TipoEstado> getAllTipoEstados() {
        return tipoEstadoRepository.findAll();
    }

    public Optional<TipoEstado> getTipoEstadoById(Integer idEstado) {
        return tipoEstadoRepository.findById(idEstado);
    }

    public TipoEstado createTipoEstado(TipoEstado tipoEstado) {
        return tipoEstadoRepository.save(tipoEstado);
    }

    public TipoEstado updateTipoEstado(Integer idEstado, TipoEstado updatedTipoEstado) {
        return tipoEstadoRepository.findById(idEstado).map(existingTipoEstado -> {
            existingTipoEstado.setEstado(updatedTipoEstado.getEstado());
            return tipoEstadoRepository.save(existingTipoEstado);
        }).orElseThrow(() -> new RuntimeException("TipoEstado com id " + idEstado + " não encontrado."));
    }

    public void deleteTipoEstado(Integer idEstado) {
        tipoEstadoRepository.deleteById(idEstado);
    }
}
