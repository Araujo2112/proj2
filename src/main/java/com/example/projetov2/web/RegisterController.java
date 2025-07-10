package com.example.projetov2.web;

import com.example.projetov2.model.Usuario;
import com.example.projetov2.model.TipoUsuario;
import com.example.projetov2.model.CodPostal;
import com.example.projetov2.repository.UsuarioRepository;
import com.example.projetov2.repository.TipoUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import java.util.Optional;

@Controller
public class RegisterController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private TipoUsuarioRepository tipoUsuarioRepository;

    // Mostra o formulário
    @GetMapping("/register")
    public String showForm() {
        return "register";
    }

    // Processa o registo
    @PostMapping("/register")
    public String register(@RequestParam("nome") String nome,
                           @RequestParam("password") String password,
                           @RequestParam("tel") String tel,
                           @RequestParam("nif") Integer nif,
                           @RequestParam("email") String email,
                           Model model) {

        if (usuarioRepository.findByNome(nome) != null) {
            model.addAttribute("error", "Nome de utilizador já existe.");
            return "register";
        }

        Optional<TipoUsuario> tipoOpt = tipoUsuarioRepository.findByTipoIgnoreCase("user");
        if (tipoOpt.isEmpty()) {
            model.addAttribute("error", "Tipo de utilizador 'cliente' não encontrado.");
            return "register";
        }

        Usuario novo = new Usuario();
        novo.setNome(nome);
        novo.setPassword(password);
        novo.setTel(tel);
        novo.setNif(nif);
        novo.setEmail(email); // <-- aqui
        novo.setTipoUsuario(tipoOpt.get());

        // ⚠️ Temporário: usar um código postal válido existente
        CodPostal cod = new CodPostal();
        cod.setIdCodPostal("1"); // usa um código válido existente
        novo.setCodPostal(cod);

        usuarioRepository.save(novo);
        return "redirect:/login";
    }
}
