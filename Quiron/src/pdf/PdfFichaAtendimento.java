/*
 * Classe que gera o PDF da ficha de atendimento médico.
 */
package pdf;

import bancodedados.MysqlConnect;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import fichaatendimento.pnlFichaAtendimento;
//import fichaatendimento.FichasAtendimento;
import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import principal.PesquisaPaciente;

/**
 *
 * @author Franciele Alves Barbosa e Rogério Costa Negro Rocha
 */
public class PdfFichaAtendimento {

    Connection conn = null;
    Document documento = new Document();

    //Definindo Fontes
    Font fonteTitulo = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD + Font.UNDERLINE);
    Font fontePergunta = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);
    Font fonteAlternativa = new Font(Font.FontFamily.TIMES_ROMAN, 12);
    Font fonteResposta = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.UNDERLINE);

    public void conectar() {
        try {
            conn = MysqlConnect.connectDB();
        } catch (SQLException sqle) {
            JOptionPane.showMessageDialog(null, "Erro ao conectar com o Banco de Dados " /*+ ex.getMessage()*/, "ERRO", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public String getDatDate(String dataDMA) {
        String reportDate = "";
        // Converte a data no formato yyyy-MM-dd
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        DateFormat dfDMA = new SimpleDateFormat("dd/MM/yyyy");
        java.util.Date enteredDate;
        try {
            enteredDate = df.parse(dataDMA);
            reportDate = dfDMA.format(enteredDate);
            reportDate.replace('-', '/');
        } catch (ParseException ex) {
            Logger.getLogger(pnlFichaAtendimento.class.getName()).log(Level.SEVERE, null, ex);
        }
        //String reportDate = df.format(enteredDate);
        return reportDate;
    }

    public String criarParagrafo(String pergunta, String resposta) throws DocumentException {
        Paragraph novoP = new Paragraph();
        novoP.add(new Chunk(pergunta, fontePergunta));
        if (resposta == null) {
            novoP.add(new Chunk("", fonteResposta));
        } else {
            novoP.add(new Chunk(resposta, fonteResposta));
        }
        documento.add(novoP);
        return "" + novoP;
    }

    public void criarCel1Coluna(String pergunta, String resposta, PdfPTable tabela) throws DocumentException {
        PdfPCell celula = new PdfPCell(new Paragraph("" + pergunta + " " + resposta));
        tabela.addCell(celula);
    }

    public void criarCel2Coluna(String pergunta, String resposta, PdfPTable tabela) throws DocumentException {
        PdfPCell celula = new PdfPCell(new Paragraph("" + pergunta + " " + resposta));
        celula.setColspan(2);
        tabela.addCell(celula);
    }

    public void criarTabela(String idPaciente) throws DocumentException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        //Criação da tabela
        PdfPTable minhaTabela = new PdfPTable(4);
        minhaTabela.setHorizontalAlignment(Element.ALIGN_CENTER);

        //Células principais da tabela
        PdfPCell data = new PdfPCell(new Paragraph("DATA"));
        data.setHorizontalAlignment(Element.ALIGN_CENTER);
        data.setBackgroundColor(BaseColor.LIGHT_GRAY);

        PdfPCell anamnese = new PdfPCell(new Paragraph("ANAMNESE E EXAME FÍSICO"));
        anamnese.setHorizontalAlignment(Element.ALIGN_CENTER);
        anamnese.setBackgroundColor(BaseColor.LIGHT_GRAY);

        PdfPCell prescricao = new PdfPCell(new Paragraph("PRESCRIÇÃO/PROCEDIMENTO"));
        prescricao.setHorizontalAlignment(Element.ALIGN_CENTER);
        prescricao.setBackgroundColor(BaseColor.LIGHT_GRAY);

        PdfPCell assinatura = new PdfPCell(new Paragraph("ASSINATURA/CARIMBO"));
        assinatura.setHorizontalAlignment(Element.ALIGN_CENTER);
        assinatura.setBackgroundColor(BaseColor.LIGHT_GRAY);

        //Consulta no BD para preencher a tabela
        String qry = "SELECT * FROM pacientes WHERE idPaciente= ?";
        String qry2 = "SELECT * FROM fichas WHERE idPaciente= ?";
        try {
            pstmt = conn.prepareStatement(qry);
            pstmt.setString(1, idPaciente);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                minhaTabela.addCell("NOME");
                minhaTabela.addCell(rs.getString("nome"));
                this.criarCel1Coluna("DATA NASCIMENTO: ", rs.getString("dtNascimento"), minhaTabela);
                this.criarCel1Coluna("NÚMERO CARTÃO SUS: ", rs.getString("cartaoSus"), minhaTabela);
                minhaTabela.addCell("CURSO");
                minhaTabela.addCell(rs.getString("curso"));
                this.criarCel2Coluna("ENDEREÇO: ", "Rua: " + rs.getString("rua") + " Nº: " + rs.getString("numero") + " Bairro: " + rs.getString("bairro") + " Município: " + rs.getString("municipio"), minhaTabela);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro: " + ex.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
        }

        minhaTabela.addCell(data);
        minhaTabela.addCell(anamnese);
        minhaTabela.addCell(prescricao);
        minhaTabela.addCell(assinatura);

        try {
            pstmt = conn.prepareStatement(qry2);
            pstmt.setString(1, idPaciente);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                
                minhaTabela.addCell(this.getDatDate(rs.getString("data")));
                minhaTabela.addCell(rs.getString("anamnese"));
                minhaTabela.addCell(rs.getString("prescricao"));
                minhaTabela.addCell(rs.getString("atendente"));
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro: " + ex.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
        }

        documento.add(minhaTabela);
    }

    public void pdfFichaAtendimento(String idPaciente) throws BadElementException, IOException {

        this.conectar();
        try {
            PdfWriter.getInstance(documento, new FileOutputStream("PDF_Ficha_Atendimento.pdf"));
            documento.setPageSize(PageSize.A4.rotate());
            documento.open();

            Image logoSecretaria = Image.getInstance(getClass().getResource("/imagens/Logo Ministerio da Educação.jpg"));
            logoSecretaria.scaleToFit(200, 171);
            logoSecretaria.setAlignment(Image.ALIGN_CENTER);
            documento.add(logoSecretaria);
            
            Image logoIf = Image.getInstance(getClass().getResource("/imagens/Logo IF G.jpg"));
            logoIf.scaleToFit(150, 51);
            logoIf.setAlignment(Image.ALIGN_LEFT);
            documento.add(logoIf);

            //Título do documento
            Paragraph titulo = new Paragraph("FICHA DE ATENDIMENTO MÉDICO", fonteTitulo);
            titulo.setAlignment(Element.ALIGN_CENTER);
            documento.add(titulo);
            documento.add(new Paragraph("\n"));

            this.criarTabela(idPaciente);

            documento.close();
        } catch (FileNotFoundException | DocumentException ex) {
            Logger.getLogger(PesquisaPaciente.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            documento.close();
        }
        try {
            Desktop.getDesktop().open(new File("PDF_Ficha_Atendimento.pdf"));
        } catch (IOException ex) {
            Logger.getLogger(PesquisaPaciente.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
