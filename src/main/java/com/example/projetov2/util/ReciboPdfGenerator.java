package com.example.projetov2.util;

import com.example.projetov2.model.Reserva;
import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfWriter;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.text.DecimalFormat;
import java.time.format.DateTimeFormatter;

public class ReciboPdfGenerator {

    public static void gerarRecibo(HttpServletResponse response, Reserva reserva) throws IOException {
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=recibo_reserva.pdf");

        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());

        document.open();

        Font tituloFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18);
        Paragraph titulo = new Paragraph("Recibo de Reserva", tituloFont);
        titulo.setAlignment(Paragraph.ALIGN_CENTER);
        titulo.setSpacingAfter(20);
        document.add(titulo);

        Font normal = FontFactory.getFont(FontFactory.HELVETICA, 12);

        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
        DecimalFormat precoFormat = new DecimalFormat("0.00");

        double horas = java.time.Duration.between(reserva.gethIni(), reserva.gethFim()).toMinutes() / 60.0;
        double precoTotal = horas * reserva.getEspacoDesportivo().getPrecoHora().doubleValue();

        document.add(new Paragraph("Nome do Utilizador: " + reserva.getUsuario().getNome(), normal));
        document.add(new Paragraph("Email: " + reserva.getUsuario().getEmail(), normal));
        document.add(new Paragraph("Espaço Desportivo: " + reserva.getEspacoDesportivo().getTipoEspaco().getTipo(), normal));
        document.add(new Paragraph("Data da Reserva: " + reserva.getDt().format(dateFormatter), normal));
        document.add(new Paragraph("Hora de Início: " + reserva.gethIni().format(timeFormatter), normal));
        document.add(new Paragraph("Hora de Fim: " + reserva.gethFim().format(timeFormatter), normal));
        document.add(new Paragraph("Preço Total: " + precoFormat.format(precoTotal) + " €", normal));
        document.add(new Paragraph("Tipo de Pagamento: " + reserva.getTipoPagamento().getNome(), normal));

        document.close();
    }
}