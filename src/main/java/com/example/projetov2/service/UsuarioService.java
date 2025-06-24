package com.example.projetov2.service;

import com.example.projetov2.dto.UsuarioDTO;
import com.example.projetov2.dto.UsuarioRequestDTO;
import com.example.projetov2.model.CodPostal;
import com.example.projetov2.model.TipoUsuario;
import com.example.projetov2.model.Usuario;
import com.example.projetov2.repository.CodPostalRepository;
import com.example.projetov2.repository.TipoUsuarioRepository;
import com.example.projetov2.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final CodPostalRepository codPostalRepository;
    private final TipoUsuarioRepository tipoUsuarioRepository;

    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository,
                          CodPostalRepository codPostalRepository,
                          TipoUsuarioRepository tipoUsuarioRepository) {
        this.usuarioRepository = usuarioRepository;
        this.codPostalRepository = codPostalRepository;
        this.tipoUsuarioRepository = tipoUsuarioRepository;
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

    public UsuarioDTO criarAPartirDTO(UsuarioRequestDTO dto) {
        CodPostal codPostal = codPostalRepository.findById(dto.codPostal.getIdCodPostal())
                .orElseGet(() -> codPostalRepository.save(dto.codPostal));

        TipoUsuario tipoUsuario = tipoUsuarioRepository.findByTipoIgnoreCase(dto.tipoUsuario)
                .orElseThrow(() -> new RuntimeException("Tipo de utilizador inválido: " + dto.tipoUsuario));

        Usuario usuario = new Usuario();
        usuario.setNome(dto.nome);
        usuario.setTel(dto.tel);
        usuario.setNif(dto.nif);
        usuario.setRua(dto.rua);
        usuario.setNPorta(dto.nPorta);
        usuario.setCodPostal(codPostal);
        usuario.setTipoUsuario(tipoUsuario);

        return new UsuarioDTO(usuarioRepository.save(usuario));
    }

    public UsuarioDTO atualizar(Integer id, Usuario usuarioAtualizado) {
        Optional<Usuario> usuarioExistente = usuarioRepository.findById(id);

        if (usuarioExistente.isPresent()) {
            Usuario usuario = usuarioExistente.get();
            usuario.setNome(usuarioAtualizado.getNome());
            usuario.setTel(usuarioAtualizado.getTel());
            usuario.setNif(usuarioAtualizado.getNif());
            usuario.setRua(usuarioAtualizado.getRua());
            usuario.setNPorta(usuarioAtualizado.getNPorta());
            usuario.setCodPostal(usuarioAtualizado.getCodPostal());
            usuario.setTipoUsuario(usuarioAtualizado.getTipoUsuario());
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
