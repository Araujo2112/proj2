package com.example.projetov2.web;

import com.example.projetov2.model.TipoEspacoDesportivo;
import com.example.projetov2.repository.TipoEspacoDesportivoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class EscolherModalidadeController {

    @Autowired
    private TipoEspacoDesportivoRepository tipoRepo;
}