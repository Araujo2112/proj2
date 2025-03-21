package com.example.projetov2.service;

import com.example.projetov2.dto.UsuarioDTO;
import com.example.projetov2.model.Usuario;
import com.example.projetov2.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    private UsuarioRepository usuarioRepository;

    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public List<UsuarioDTO> getUsuarios() {
        List<Usuario> usuarios = usuarioRepository.findAll();
        return usuarios.stream()
                .map(UsuarioDTO::new)
                .toList();
    }

    public Optional<UsuarioDTO> getUsuarioById(Integer id) {
        Optional<Usuario> usuario = usuarioRepository.findById(id);
        return usuario.map(UsuarioDTO::new);
    }

    public UsuarioDTO criar(Usuario usuario) {
        Usuario usuarioCriado = usuarioRepository.save(usuario);
        return new UsuarioDTO(usuarioCriado);
    }

    public UsuarioDTO atualizar(Integer id, Usuario usuarioAtualizado) {
        Optional<Usuario> usuarioExistente = usuarioRepository.findById(id);

        if (usuarioExistente.isPresent()) {
            Usuario usuario = usuarioExistente.get();
            // Atualiza os campos do usuário
            usuario.setNome(usuarioAtualizado.getNome());
            usuario.setTel(usuarioAtualizado.getTel());
            usuario.setNif(usuarioAtualizado.getNif());
            usuario.setRua(usuarioAtualizado.getRua());
            usuario.setNPorta(usuarioAtualizado.getNPorta());
            usuario.setCodPostal(usuarioAtualizado.getCodPostal());
            usuario.setTipoUsuario(usuarioAtualizado.getTipoUsuario());
            usuario.setReservas(usuarioAtualizado.getReservas());
            usuario.setPagamentos(usuarioAtualizado.getPagamentos());
            usuario.setNotificacoes(usuarioAtualizado.getNotificacoes());
            usuarioRepository.save(usuario);
            return new UsuarioDTO(usuario);
        } else {
            throw new RuntimeException("Usuário não encontrado!");
        }
    }

    public void deletar(Integer id) {
        if (usuarioRepository.existsById(id)) {
            usuarioRepository.deleteById(id);
        } else {
            throw new RuntimeException("Usuário não encontrado!");
        }
    }
}
