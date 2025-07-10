package com.example.projetov2.web;

import com.example.projetov2.model.Usuario;
import com.example.projetov2.repository.UsuarioRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class LoginController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping("/login")
    public String showLoginPage(HttpSession session) {
        if (session.getAttribute("utilizador") != null) {
            return "redirect:/home";
        }
        return "login";
    }

    @PostMapping("/login")
    public String processLogin(@RequestParam("nome") String nome,
                               @RequestParam("password") String password,
                               Model model,
                               HttpSession session) {

        Usuario user = usuarioRepository.findByNome(nome);

        if (user == null) {
            model.addAttribute("error", "Utilizador não encontrado.");
            return "login";
        }

        if (!user.getPassword().equals(password)) {
            model.addAttribute("error", "Palavra-passe incorreta.");
            return "login";
        }

        if (!user.getTipoUsuario().getTipo().equalsIgnoreCase("user")) {
            model.addAttribute("error", "Apenas clientes podem aceder pela versão web.");
            return "login";
        }

        session.setAttribute("utilizador", user);
        return "redirect:/home";
    }

    @GetMapping("/home")
    public String showHomePage(HttpSession session, Model model) {
        Usuario user = (Usuario) session.getAttribute("utilizador");
        if (user == null) {
            return "redirect:/login";
        }

        model.addAttribute("nome", user.getNome());
        return "home";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}
