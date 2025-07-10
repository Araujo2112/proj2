package com.example.projetov2.web;

import com.example.projetov2.model.EspacoDesportivo;
import com.example.projetov2.repository.EspacoDesportivoRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/espacos")
public class EspacoDesportivoWebController {

    @Autowired
    private EspacoDesportivoRepository espacoRepository;

    @GetMapping
    public String listarEspacos(@RequestParam(required = false) String tipo, Model model, HttpSession session) {
        if (session.getAttribute("utilizador") == null) {
            return "redirect:/login";
        }

        List<EspacoDesportivo> espacos;
        if (tipo != null && !tipo.isEmpty()) {
            espacos = espacoRepository.findByDisponibilidadeTrueAndTipoEspaco_TipoContainingIgnoreCase(tipo);
        } else {
            espacos = espacoRepository.findAll()
                    .stream()
                    .filter(EspacoDesportivo::getDisponibilidade)
                    .toList();
        }

        model.addAttribute("espacos", espacos);
        return "espacos";
    }
}
