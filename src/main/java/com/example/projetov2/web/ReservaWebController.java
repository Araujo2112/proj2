package com.example.projetov2.web;

import com.example.projetov2.model.*;
import com.example.projetov2.repository.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpServletResponse;
import com.example.projetov2.util.ReciboPdfGenerator;
import java.io.IOException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/reservas")
public class ReservaWebController {

    @Autowired private TipoEspacoDesportivoRepository tipoEspacoRepo;
    @Autowired private EspacoDesportivoRepository espacoRepo;
    @Autowired private ReservaRepository reservaRepo;
    @Autowired private TipoEstadoRepository estadoRepo;
    @Autowired private TipoPagamentoRepository tipoPagamentoRepo;

    @GetMapping("/nova")
    public String escolherModalidade(HttpSession session, Model m) {
        if (session.getAttribute("utilizador") == null) return "redirect:/login";
        m.addAttribute("modalidades", tipoEspacoRepo.findAll());
        return "escolher_modalidade";
    }

    @GetMapping("/espacos")
    public String listarEspacosPorModalidade(@RequestParam("modalidade") String tipo,
                                             HttpSession session, Model m) {
        if (session.getAttribute("utilizador") == null) return "redirect:/login";
        List<EspacoDesportivo> espacos =
                espacoRepo.findByDisponibilidadeTrueAndTipoEspaco_TipoContainingIgnoreCase(tipo);
        m.addAttribute("espacos", espacos);
        m.addAttribute("modalidade", tipo);
        return "espacos";
    }

    @GetMapping("/novo/{idEspaco}")
    public String mostrarFormulario(@PathVariable Integer idEspaco,
                                    HttpSession session, Model m) {
        if (session.getAttribute("utilizador") == null) return "redirect:/login";
        Optional<EspacoDesportivo> opt = espacoRepo.findById(idEspaco);
        if (opt.isEmpty()) return "redirect:/reservas/nova";

        m.addAttribute("espaco", opt.get());
        return "reserva";
    }

    @PostMapping("/confirmar/{idEspaco}")
    public String confirmarPagamento(@PathVariable Integer idEspaco,
                                     @RequestParam String data,
                                     @RequestParam String horaInicio,
                                     @RequestParam String horaFim,
                                     HttpSession session, Model m) {
        Usuario u = (Usuario) session.getAttribute("utilizador");
        if (u == null) return "redirect:/login";

        Optional<EspacoDesportivo> opt = espacoRepo.findById(idEspaco);
        if (opt.isEmpty()) return "redirect:/reservas/nova";
        EspacoDesportivo espaco = opt.get();

        LocalDate d = LocalDate.parse(data);
        LocalTime hIni = LocalTime.parse(horaInicio);
        LocalTime hFim = LocalTime.parse(horaFim);

        if (d.isBefore(LocalDate.now()) ||
                (d.isEqual(LocalDate.now()) && hIni.isBefore(LocalTime.now()))) {
            m.addAttribute("erro", "A data/hora deve ser futura.");
            m.addAttribute("espaco", espaco);
            return "reserva";
        }

        double horas = java.time.Duration.between(hIni, hFim).toMinutes() / 60.0;
        double precoTotal = horas * espaco.getPrecoHora().doubleValue();

        m.addAttribute("espaco", espaco);
        m.addAttribute("data", d);
        m.addAttribute("horaInicio", horaInicio);
        m.addAttribute("horaFim", horaFim);
        m.addAttribute("precoTotal", String.format("%.2f", precoTotal));
        m.addAttribute("pagamentos", tipoPagamentoRepo.findAll());

        return "confirmar_pagamento";
    }

    @PostMapping("/efetuar/{idEspaco}")
    public String efetuarReserva(@PathVariable Integer idEspaco,
                                 @RequestParam String data,
                                 @RequestParam String horaInicio,
                                 @RequestParam String horaFim,
                                 @RequestParam Integer tipoPagamentoId,
                                 HttpSession session, Model m) {
        Usuario u = (Usuario) session.getAttribute("utilizador");
        if (u == null) return "redirect:/login";

        Optional<EspacoDesportivo> espacoOpt = espacoRepo.findById(idEspaco);
        Optional<TipoPagamento> tipoPagOpt = tipoPagamentoRepo.findById(tipoPagamentoId);
        Optional<TipoEstado> estadoOpt = estadoRepo.findByEstadoIgnoreCase("Em Processo");

        if (espacoOpt.isEmpty() || tipoPagOpt.isEmpty() || estadoOpt.isEmpty()) {
            m.addAttribute("erro", "Dados inválidos. Verifique os campos.");
            return "redirect:/reservas/nova";
        }

        Reserva r = new Reserva();
        r.setDt(LocalDate.parse(data));
        r.sethIni(LocalTime.parse(horaInicio));
        r.sethFim(LocalTime.parse(horaFim));
        r.setEspacoDesportivo(espacoOpt.get());
        r.setUsuario(u);
        r.setEstado(estadoOpt.get());
        r.setTipoPagamento(tipoPagOpt.get());

        reservaRepo.save(r);

        double horas = java.time.Duration.between(r.gethIni(), r.gethFim()).toMinutes() / 60.0;
        double preco = horas * espacoOpt.get().getPrecoHora().doubleValue();

        m.addAttribute("reserva", r);
        m.addAttribute("precoTotal", String.format("%.2f", preco));
        return "recibo";
    }

    @GetMapping("/minhas")
    public String minhasReservas(HttpSession session, Model m) {
        Usuario u = (Usuario) session.getAttribute("utilizador");
        if (u == null) return "redirect:/login";

        m.addAttribute("reservas", reservaRepo.findByUsuarioAndEstado_EstadoNotIgnoreCase(u, "Cancelado"));
        return "minhas_reservas.html";
    }

    @PostMapping("/cancelar/{id}")
    public String cancelarReserva(@PathVariable Integer id, HttpSession session) {
        Usuario u = (Usuario) session.getAttribute("utilizador");
        if (u == null) return "redirect:/login";

        Optional<Reserva> opt = reservaRepo.findById(id);
        if (opt.isPresent()) {
            Reserva r = opt.get();
            boolean jaDecorriu = r.getDt().isBefore(LocalDate.now()) ||
                    (r.getDt().isEqual(LocalDate.now()) && r.gethFim().isBefore(LocalTime.now()));

            if (r.getUsuario().getIdUsuario().equals(u.getIdUsuario()) && !jaDecorriu) {
                Optional<TipoEstado> canceladaOpt = estadoRepo.findByEstadoIgnoreCase("Cancelado");
                canceladaOpt.ifPresent(r::setEstado);
                reservaRepo.save(r);
            }
        }
        return "redirect:/reservas/minhas";
    }

    @GetMapping("/editar/{idReserva}")
    public String editarReserva(@PathVariable Integer idReserva,
                                HttpSession session, Model model) {
        Usuario u = (Usuario) session.getAttribute("utilizador");
        if (u == null) return "redirect:/login";

        Optional<Reserva> opt = reservaRepo.findById(idReserva);
        if (opt.isEmpty()) return "redirect:/reservas/minhas";

        Reserva r = opt.get();
        if (!r.getUsuario().getIdUsuario().equals(u.getIdUsuario()) ||
                !r.getEstado().getEstado().equalsIgnoreCase("Em Processo") ||
                r.getDt().isBefore(LocalDate.now())) {
            return "redirect:/reservas/minhas";
        }

        model.addAttribute("reserva", r);
        model.addAttribute("pagamentos", tipoPagamentoRepo.findAll());
        return "editar_reserva";
    }

    @PostMapping("/editar/{idReserva}")
    public String guardarAlteracoes(@PathVariable Integer idReserva,
                                    @RequestParam String data,
                                    @RequestParam String horaInicio,
                                    @RequestParam String horaFim,
                                    @RequestParam Integer tipoPagamentoId,
                                    HttpSession session, Model m) {
        Usuario u = (Usuario) session.getAttribute("utilizador");
        if (u == null) return "redirect:/login";

        Optional<Reserva> opt = reservaRepo.findById(idReserva);
        Optional<TipoPagamento> pagOpt = tipoPagamentoRepo.findById(tipoPagamentoId);
        if (opt.isEmpty() || pagOpt.isEmpty()) return "redirect:/reservas/minhas";

        Reserva r = opt.get();
        if (!r.getUsuario().getIdUsuario().equals(u.getIdUsuario()) ||
                !r.getEstado().getEstado().equalsIgnoreCase("Em Processo") ||
                r.getDt().isBefore(LocalDate.now())) {
            return "redirect:/reservas/minhas";
        }

        LocalDate novaData = LocalDate.parse(data);
        LocalTime hIni = LocalTime.parse(horaInicio);
        LocalTime hFim = LocalTime.parse(horaFim);

        if (novaData.isBefore(LocalDate.now()) ||
                (novaData.isEqual(LocalDate.now()) && hIni.isBefore(LocalTime.now()))) {
            m.addAttribute("erro", "A data/hora deve ser futura.");
            m.addAttribute("reserva", r);
            m.addAttribute("pagamentos", tipoPagamentoRepo.findAll());
            return "editar_reserva";
        }

        r.setDt(novaData);
        r.sethIni(hIni);
        r.sethFim(hFim);
        r.setTipoPagamento(pagOpt.get());

        reservaRepo.save(r);

        double horas = java.time.Duration
                .between(r.gethIni(), r.gethFim()).toMinutes() / 60.0;
        double preco = horas * r.getEspacoDesportivo().getPrecoHora().doubleValue();

        m.addAttribute("reserva", r);
        m.addAttribute("precoTotal", String.format("%.2f", preco));
        return "reserva_sucesso";
    }

    @GetMapping("/api/lembretes")
    @ResponseBody
    public List<Reserva> lembretes(HttpSession session) {
        Usuario u = (Usuario) session.getAttribute("utilizador");
        if (u == null) return List.of();

        LocalDate hoje = LocalDate.now();
        LocalTime agora = LocalTime.now().minusMinutes(5);
        LocalTime maisTarde = LocalTime.now().plusMinutes(60);

        List<Reserva> todasHoje = reservaRepo.findByUsuarioAndDt(u, hoje);

        return todasHoje.stream()
                .filter(r -> r.gethIni().isAfter(agora) && r.gethIni().isBefore(maisTarde))
                .filter(r -> r.getEstado().getEstado().equalsIgnoreCase("Em Processo"))
                .toList();
    }

    @GetMapping("/recibo/{id}")
    public void gerarRecibo(@PathVariable Integer id, HttpSession session, HttpServletResponse response) {
        Usuario u = (Usuario) session.getAttribute("utilizador");
        if (u == null) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        Optional<Reserva> opt = reservaRepo.findById(id);
        if (opt.isEmpty()) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        Reserva r = opt.get();
        if (!r.getUsuario().getIdUsuario().equals(u.getIdUsuario())) {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            return;
        }

        try {
            ReciboPdfGenerator.gerarRecibo(response, r);
        } catch (IOException e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}