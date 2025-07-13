package com.example.projetov2.service;

import com.example.projetov2.model.Usuario;
import com.example.projetov2.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public String login(String email, String senha) {
        Usuario usuario = usuarioRepository.findByEmail(email);

        if (usuario != null && usuario.getPassword().equals(senha)) {
            return usuario.getTipoUsuario().getTipo();
        }

        return null;
    }
}
