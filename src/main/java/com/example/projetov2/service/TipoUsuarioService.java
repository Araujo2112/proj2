package com.example.projetov2.service;

import com.example.projetov2.model.TipoUsuario;
import com.example.projetov2.repository.TipoUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TipoUsuarioService {
    @Autowired
    private TipoUsuarioRepository tipoUsuarioRepository;

    public List<TipoUsuario> listarTodos() {
        return tipoUsuarioRepository.findAll();
    }

    public Optional<TipoUsuario> buscarPorId(Integer id) {
        return tipoUsuarioRepository.findById(id);
    }

    public TipoUsuario criar(TipoUsuario tipoUsuario) {
        return tipoUsuarioRepository.save(tipoUsuario);
    }

    public TipoUsuario atualizar(Integer id, TipoUsuario tipoUsuarioAtualizado) {
        Optional<TipoUsuario> tipoUsuarioExistente = tipoUsuarioRepository.findById(id);

        if (tipoUsuarioExistente.isPresent()) {
            TipoUsuario tipoUsuario = tipoUsuarioExistente.get();
            tipoUsuario.setTipo(tipoUsuarioAtualizado.getTipo());
            return tipoUsuarioRepository.save(tipoUsuario);
        } else {
            throw new RuntimeException("Tipo de usuário com ID " + id + " não encontrado.");
        }
    }

    public void deletar(Integer id) {
        if (tipoUsuarioRepository.existsById(id)) {
            tipoUsuarioRepository.deleteById(id);
        } else {
            throw new RuntimeException("Tipo de usuário com ID " + id + " não encontrado.");
        }
    }
}

