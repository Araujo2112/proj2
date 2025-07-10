package com.example.projetov2.web;

import com.example.projetov2.model.Usuario;
import com.example.projetov2.repository.UsuarioRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/perfil")
public class PerfilController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // Página de edição do perfil
    @GetMapping
    public String mostrarPerfil(HttpSession session, Model model) {
        Usuario u = (Usuario) session.getAttribute("utilizador");
        if (u == null) return "redirect:/login";

        model.addAttribute("utilizador", u);
        return "perfil";
    }

    // Guardar alterações do perfil
    @PostMapping
    public String atualizarPerfil(@ModelAttribute("utilizador") Usuario dadosAtualizados,
                                  @RequestParam("password") String novaPassword,
                                  @RequestParam("confirmPassword") String confirmPassword,
                                  HttpSession session, Model model) {
        Usuario u = (Usuario) session.getAttribute("utilizador");
        if (u == null) return "redirect:/login";

        // Atualizar apenas os campos permitidos
        u.setNome(dadosAtualizados.getNome());
        u.setTel(dadosAtualizados.getTel());
        u.setEmail(dadosAtualizados.getEmail());
        u.setNif(dadosAtualizados.getNif()); // podes comentar esta linha se o NIF for mesmo fixo

        // Verificar se a.html password foi alterada e é válida
        if (!novaPassword.isBlank()) {
            if (!novaPassword.equals(confirmPassword)) {
                model.addAttribute("utilizador", u);
                model.addAttribute("erro", "As palavras-passe não coincidem.");
                return "perfil";
            }
            u.setPassword(passwordEncoder.encode(novaPassword));
        }

        usuarioRepository.save(u);
        session.setAttribute("utilizador", u); // atualizar sessão

        model.addAttribute("utilizador", u);
        model.addAttribute("sucesso", "Perfil atualizado com sucesso.");
        return "perfil";
    }
}