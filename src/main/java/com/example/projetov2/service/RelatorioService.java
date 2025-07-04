package com.example.projetov2.service;

import com.example.projetov2.dto.FaturacaoDTO;
import com.example.projetov2.model.Pagamento;
import com.example.projetov2.model.Relatorio;
import com.example.projetov2.model.TipoRelatorio;
import com.example.projetov2.repository.PagamentoRepository;
import com.example.projetov2.repository.RelatorioRepository;
import com.example.projetov2.repository.TipoRelatorioRepository;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class RelatorioService {

    private static final String PASTA_RELATORIOS = "relatorios/";

    @Autowired
    private PagamentoRepository pagamentoRepository;

    @Autowired
    private RelatorioRepository relatorioRepository;

    @Autowired
    private TipoRelatorioRepository tipoRelatorioRepository;

    public List<Relatorio> listarTodos() {
        return relatorioRepository.findAll();
    }

    public Optional<Relatorio> buscarPorId(Integer id) {
        return relatorioRepository.findById(id);
    }

    public Relatorio criar(Relatorio relatorio) {
        return relatorioRepository.save(relatorio);
    }

    public Relatorio atualizar(Integer id, Relatorio relatorioAtualizado) {
        Relatorio existente = relatorioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Relatório não encontrado"));
        existente.setDescricao(relatorioAtualizado.getDescricao());
        existente.setDataCriacao(relatorioAtualizado.getDataCriacao());
        existente.setIdTipo(relatorioAtualizado.getIdTipo());
        return relatorioRepository.save(existente);
    }

    public void deletar(Integer id) {
        Relatorio existente = relatorioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Relatório não encontrado"));
        relatorioRepository.delete(existente);
    }

    public File gerarRelatorioFaturacao(LocalDate de, LocalDate ate) {
        try {
            List<Pagamento> pagamentos = pagamentoRepository.findByEstado_EstadoAndDtPagamentoBetween("Feito", de, ate);

            List<FaturacaoDTO> dtos = pagamentos.stream().map(p -> {
                var r = p.getReserva();
                BigDecimal precoHora = r.getEspacoDesportivo().getPrecoHora();
                long duracaoHoras = Duration.between(r.gethIni(), r.gethFim()).toHours();
                BigDecimal total = precoHora.multiply(BigDecimal.valueOf(duracaoHoras));

                FaturacaoDTO dto = new FaturacaoDTO();
                dto.setIdPagamento(p.getIdPagamento());
                dto.setNomeCliente(p.getUsuario().getNome());
                dto.setDataPagamento(p.getDtPagamento());
                dto.setTipoPagamento(p.getTipoPagamento().getNome());
                dto.setEspaco(r.getEspacoDesportivo().getLote());
                dto.setDataReserva(r.getDt());
                dto.setHoraInicio(r.gethIni());
                dto.setHoraFim(r.gethFim());
                dto.setValor(total);
                return dto;
            }).collect(Collectors.toList());

            File pasta = new File(PASTA_RELATORIOS);
            if (!pasta.exists()) pasta.mkdirs();

            String nomeFicheiro = "relatorio_faturacao_" + de + "_a_" + ate + ".pdf";
            File ficheiro = new File(pasta, nomeFicheiro);

            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(ficheiro));
            document.open();

            Font fontTitle = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 16);
            Font fontHeader = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
            Font fontBody = FontFactory.getFont(FontFactory.HELVETICA, 10);

            document.add(new Paragraph("Relatório de Faturação", fontTitle));
            document.add(new Paragraph("Período: " + de + " até " + ate, fontBody));
            document.add(new Paragraph(" "));

            PdfPTable table = new PdfPTable(8);
            table.setWidthPercentage(100);
            table.setWidths(new int[]{1, 3, 2, 2, 2, 2, 3, 2});

            Stream.of("ID", "Cliente", "Data Pagamento", "Tipo", "Espaço", "Data Reserva", "Horário", "Valor")
                    .forEach(header -> {
                        PdfPCell headerCell = new PdfPCell(new Phrase(header, fontHeader));
                        headerCell.setBackgroundColor(BaseColor.LIGHT_GRAY);
                        headerCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                        table.addCell(headerCell);
                    });

            for (FaturacaoDTO dto : dtos) {
                table.addCell(new PdfPCell(new Phrase(dto.getIdPagamento().toString(), fontBody)));
                table.addCell(new PdfPCell(new Phrase(dto.getNomeCliente(), fontBody)));
                table.addCell(new PdfPCell(new Phrase(dto.getDataPagamento().toString(), fontBody)));
                table.addCell(new PdfPCell(new Phrase(dto.getTipoPagamento(), fontBody)));
                table.addCell(new PdfPCell(new Phrase(dto.getEspaco(), fontBody)));
                table.addCell(new PdfPCell(new Phrase(dto.getDataReserva().toString(), fontBody)));
                table.addCell(new PdfPCell(new Phrase(dto.getHoraInicio() + " - " + dto.getHoraFim(), fontBody)));
                table.addCell(new PdfPCell(new Phrase(dto.getValor().toPlainString(), fontBody)));
            }

            document.add(table);
            document.close();

            TipoRelatorio tipo = tipoRelatorioRepository.findById(1)
                    .orElseThrow(() -> new RuntimeException("Tipo de relatório não encontrado"));

            Relatorio relatorio = new Relatorio();
            relatorio.setDataCriacao(LocalDate.now());
            relatorio.setDataInicio(de);
            relatorio.setDataFim(ate);
            relatorio.setDescricao("Faturação de " + de + " a " + ate);
            relatorio.setIdTipo(tipo);
            relatorio.setCaminhoPdf(ficheiro.getAbsolutePath());
            relatorio.setDataGeracao(LocalDate.now());

            relatorioRepository.save(relatorio);

            return ficheiro;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public byte[] downloadPdf(String caminhoAbsoluto) {
        try {
            File ficheiro = new File(caminhoAbsoluto);
            return java.nio.file.Files.readAllBytes(ficheiro.toPath());
        } catch (IOException e) {
            throw new RuntimeException("Erro ao ler o PDF.", e);
        }
    }
}
