/*
 * Classe que gera o PDF do cartão de vacina do paciente.
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

import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;

import principal.PesquisaPaciente;

/**
 *
 * @author Franciele Alves Barbosa e Rogério Costa Negro Rocha
 */
public class PdfCartaoVacina {

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

    public String criarDoses(String data, String lote, String validade) {
        String escrita = "";
        if (data == null || lote == null || validade == null) {
            String dt = "  /  /    ";
            String lt = "\nLOTE:        ";
            String val = "\nVAL.:   /  /    ";
            String ass = "\nASS.:         ";
            escrita = dt + lt + val + ass;
        } else {
            String dt = data;
            String lt = "\nLOTE: " + lote;
            String val = "\nVAL.: " + validade;
            String ass = "\nASS.: ";
            escrita = dt + lt + val + ass;
        }
        return escrita;
    }

    public String criarDosesOutras(String descricao, String data, String lote, String validade) {
        String escrita = "";
        if (data == null || lote == null || validade == null) {
            String desc = "Descrição: ";
            String dt = "\nData:  /  /    ";
            String lt = "\nLOTE:        ";
            String val = "\nVAL.:   /  /    ";
            String ass = "\nASS.:         ";
            escrita = desc + dt + lt + val + ass;
        } else {
            String desc = "Descrição: " + descricao;
            String dt = "\nData: " + data;
            String lt = "\nLOTE: " + lote;
            String val = "\nVAL.: " + validade;
            String ass = "\nASS.: ";
            escrita = desc + dt + lt + val + ass;
        }
        return escrita;
    }

    public void criarLinha1Coluna(String pergunta, String resposta, PdfPTable tabela) throws DocumentException {
        PdfPCell cabecalho = new PdfPCell(new Paragraph("" + pergunta + " " + resposta));
        cabecalho.setColspan(2);
        tabela.addCell(cabecalho);
    }

    public void criarLinha2Coluna(String pergunta1, String resposta1, String pergunta2, String resposta2, PdfPTable tabela) throws DocumentException {
        PdfPCell col1 = new PdfPCell(new Paragraph("" + pergunta1 + " " + resposta1));
        col1.setColspan(1);
        tabela.addCell(col1);
        PdfPCell col2 = new PdfPCell(new Paragraph("" + pergunta2 + " " + resposta2));
        if (resposta2 == null) {
            col2 = new PdfPCell(new Paragraph(pergunta2));
        }
        col1.setColspan(1);
        tabela.addCell(col2);
    }

    public void identificacao(int idPaciente) throws DocumentException {

        Paragraph titulo = new Paragraph("CARTÃO DE VACINAÇÃO DO ADULTO", fonteTitulo);
        titulo.setAlignment(Element.ALIGN_CENTER);
        documento.add(titulo);
        documento.add(new Paragraph("\n"));
        PdfPCell cabecalho = new PdfPCell(new Paragraph("IDENTIFICAÇÃO"));//definindo a primeira linha da tabela
        cabecalho.setHorizontalAlignment(Element.ALIGN_CENTER);
        cabecalho.setBackgroundColor(BaseColor.LIGHT_GRAY);
        cabecalho.setColspan(2);//falando que a primeira linha vai ocupar 2 colunas
        PdfPTable minhaTabela = new PdfPTable(2);//definindo a tabela com duas colunas
        minhaTabela.setHorizontalAlignment(Element.ALIGN_CENTER);
        minhaTabela.addCell(cabecalho);//colocando a primeira linha na tabela
        //Ainda não adicionei a tabela ao PDF

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String qry = "SELECT * FROM cartaovacina WHERE idPaciente= ?";
        try {
            pstmt = conn.prepareStatement(qry);
            pstmt.setInt(1, idPaciente);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                this.criarLinha1Coluna("NOME: ", rs.getString("nome"), minhaTabela);
                this.criarLinha2Coluna("DT. NASC.: ", rs.getString("dn"), "TIPO SANGUÍNEO: ", rs.getString("tipoSanguineo"), minhaTabela);
                this.criarLinha1Coluna("END: ", "Rua: " + rs.getString("rua") + " Nº: " + rs.getString("numero") + " Bairro: " + rs.getString("bairro"), minhaTabela);
                this.criarLinha2Coluna("MUNICÍPIO: ", rs.getString("municipio"), "UF: ", rs.getString("uf"), minhaTabela);
                this.criarLinha2Coluna("TELEFONE: ", rs.getString("telefone"), "GRS: ", rs.getString("grs"), minhaTabela);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro: " + ex.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
        }
        documento.add(minhaTabela);//adicionando a tabela ao PDF
    }

    public void duplaAdulto(int idPaciente) throws DocumentException {

        //Paragraph titulo = new Paragraph("DUPLA ADULTO (CONTRA TÉTANO E DIFITERIA)", fonteTitulo);
        //titulo.setAlignment(Element.ALIGN_CENTER);
        //documento.add(titulo);
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String qry = "SELECT * FROM cartaovacina WHERE idPaciente= ?";

        PdfPCell cabecalho = new PdfPCell(new Paragraph(" DUPLA ADULTO (CONTRA TÉTANO E DIFITERIA) "));
        cabecalho.setHorizontalAlignment(Element.ALIGN_CENTER);
        cabecalho.setBackgroundColor(BaseColor.LIGHT_GRAY);
        cabecalho.setColspan(4);
        PdfPTable minhaTabela = new PdfPTable(4);
        minhaTabela.setHorizontalAlignment(Element.ALIGN_CENTER);
        minhaTabela.addCell(cabecalho);
        minhaTabela.addCell(" 1ª DOSE ");
        minhaTabela.addCell(" 2ª DOSE ");
        minhaTabela.addCell(" 3ª DOSE ");
        minhaTabela.addCell(" REFORÇO ");
        try {
            pstmt = conn.prepareStatement(qry);
            pstmt.setInt(1, idPaciente);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                minhaTabela.addCell(this.criarDoses(rs.getString("dose1"), rs.getString("lote1"), rs.getString("validade1")));
                minhaTabela.addCell(this.criarDoses(rs.getString("dose2"), rs.getString("lote2"), rs.getString("validade2")));
                minhaTabela.addCell(this.criarDoses(rs.getString("dose3"), rs.getString("lote3"), rs.getString("validade3")));
                minhaTabela.addCell(this.criarDoses(rs.getString("reforco"), rs.getString("loteReforco"), rs.getString("validadeReforco")));
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro: " + ex.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
        }
        documento.add(cabecalho);
        documento.add(minhaTabela);
    }

    public void influenza(int idPaciente) throws DocumentException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String qry = "SELECT * FROM cartaovacina WHERE idPaciente= ?";

        PdfPCell cabecalho = new PdfPCell(new Paragraph(" INFLUENZA (CONTRA GRIPE) "));
        cabecalho.setHorizontalAlignment(Element.ALIGN_CENTER);
        cabecalho.setBackgroundColor(BaseColor.LIGHT_GRAY);
        cabecalho.setColspan(4);
        PdfPTable minhaTabela = new PdfPTable(4);
        minhaTabela.setHorizontalAlignment(Element.ALIGN_CENTER);
        minhaTabela.addCell(cabecalho);
        try {
            pstmt = conn.prepareStatement(qry);
            pstmt.setInt(1, idPaciente);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                minhaTabela.addCell(this.criarDoses(rs.getString("influenza1"), rs.getString("loteInfluenza1"), rs.getString("valInfluenza1")));
                minhaTabela.addCell(this.criarDoses(rs.getString("influenza2"), rs.getString("loteInfluenza2"), rs.getString("valInfluenza2")));
                minhaTabela.addCell(this.criarDoses(rs.getString("influenza3"), rs.getString("loteInfluenza3"), rs.getString("valInfluenza3")));
                minhaTabela.addCell(this.criarDoses(rs.getString("influenza4"), rs.getString("loteInfluenza4"), rs.getString("valInfluenza4")));
                minhaTabela.addCell(this.criarDoses(rs.getString("influenza5"), rs.getString("loteInfluenza5"), rs.getString("valInfluenza5")));
                minhaTabela.addCell(this.criarDoses(rs.getString("influenza6"), rs.getString("loteInfluenza6"), rs.getString("valInfluenza6")));
                minhaTabela.addCell(this.criarDoses(rs.getString("influenza7"), rs.getString("loteInfluenza7"), rs.getString("valInfluenza7")));
                minhaTabela.addCell(this.criarDoses(rs.getString("influenza8"), rs.getString("loteInfluenza8"), rs.getString("valInfluenza8")));
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro: " + ex.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
        }
        documento.add(cabecalho);
        documento.add(minhaTabela);
    }

    public void febreAmarela(int idPaciente) throws DocumentException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String qry = "SELECT * FROM cartaovacina WHERE idPaciente= ?";

        PdfPCell cabecalho = new PdfPCell(new Paragraph("FEBRE AMARELA"));
        cabecalho.setHorizontalAlignment(Element.ALIGN_CENTER);
        cabecalho.setBackgroundColor(BaseColor.LIGHT_GRAY);
        cabecalho.setColspan(4);
        PdfPTable minhaTabela = new PdfPTable(4);
        minhaTabela.setHorizontalAlignment(Element.ALIGN_CENTER);
        minhaTabela.addCell(cabecalho);

        try {
            pstmt = conn.prepareStatement(qry);
            pstmt.setInt(1, idPaciente);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                minhaTabela.addCell(this.criarDoses(rs.getString("febre1"), rs.getString("loteFebre1"), rs.getString("valFebre1")));
                minhaTabela.addCell(this.criarDoses(rs.getString("febre2"), rs.getString("loteFebre2"), rs.getString("valFebre2")));
                minhaTabela.addCell(this.criarDoses(rs.getString("febre3"), rs.getString("loteFebre3"), rs.getString("valFebre3")));
                minhaTabela.addCell(this.criarDoses(rs.getString("febre4"), rs.getString("loteFebre4"), rs.getString("valFebre4")));
                minhaTabela.addCell(this.criarDoses(rs.getString("febre5"), rs.getString("loteFebre5"), rs.getString("valFebre5")));
                minhaTabela.addCell(this.criarDoses(rs.getString("febre6"), rs.getString("loteFebre6"), rs.getString("valFebre6")));
                minhaTabela.addCell(this.criarDoses(rs.getString("febre7"), rs.getString("loteFebre7"), rs.getString("valFebre7")));
                minhaTabela.addCell(this.criarDoses(rs.getString("febre8"), rs.getString("loteFebre8"), rs.getString("valFebre8")));
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro: " + ex.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
        }
        documento.add(cabecalho);
        documento.add(minhaTabela);
    }

    public void duplaViral(int idPaciente) throws DocumentException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String qry = "SELECT * FROM cartaovacina WHERE idPaciente= ?";

        PdfPCell cabecalho = new PdfPCell(new Paragraph("DUPLA VIRAL (SARAMPO + RUBÉOLA"));
        cabecalho.setHorizontalAlignment(Element.ALIGN_CENTER);
        cabecalho.setBackgroundColor(BaseColor.LIGHT_GRAY);
        cabecalho.setColspan(2);
        PdfPTable minhaTabela = new PdfPTable(2);
        minhaTabela.setHorizontalAlignment(Element.ALIGN_CENTER);
        minhaTabela.addCell(cabecalho);
        minhaTabela.addCell("1ª DOSE");
        minhaTabela.addCell("2ª DOSE");

        try {
            pstmt = conn.prepareStatement(qry);
            pstmt.setInt(1, idPaciente);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                minhaTabela.addCell(this.criarDoses(rs.getString("duplaViral1"), rs.getString("loteDupla1"), rs.getString("valDupla1")));
                minhaTabela.addCell(this.criarDoses(rs.getString("duplaViral2"), rs.getString("loteDupla2"), rs.getString("valDupla2")));
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro: " + ex.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
        }
        documento.add(cabecalho);
        documento.add(minhaTabela);
    }

    public void tripliceViral(int idPaciente) throws DocumentException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String qry = "SELECT * FROM cartaovacina WHERE idPaciente= ?";

        PdfPCell cabecalho = new PdfPCell(new Paragraph(" TRÍPLICE VIRAL (SARAMPO+RUBÉOLA+CAXUMBA)"));
        cabecalho.setHorizontalAlignment(Element.ALIGN_CENTER);
        cabecalho.setBackgroundColor(BaseColor.LIGHT_GRAY);
        cabecalho.setColspan(2);
        PdfPTable minhaTabela = new PdfPTable(2);
        minhaTabela.setHorizontalAlignment(Element.ALIGN_CENTER);
        minhaTabela.addCell(cabecalho);
        minhaTabela.addCell(" 1ª DOSE ");
        minhaTabela.addCell(" 2ª DOSE ");

        try {
            pstmt = conn.prepareStatement(qry);
            pstmt.setInt(1, idPaciente);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                minhaTabela.addCell(this.criarDoses(rs.getString("triplice1"), rs.getString("loteTriplice1"), rs.getString("valTriplice1")));
                minhaTabela.addCell(this.criarDoses(rs.getString("triplice2"), rs.getString("loteTriplice2"), rs.getString("valTriplice2")));
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro: " + ex.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
        }
        documento.add(cabecalho);
        documento.add(minhaTabela);
    }

    public void outrasVacinas1(int idPaciente) throws DocumentException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String qry = "SELECT * FROM cartaovacina WHERE idPaciente= ?";

        PdfPCell cabecalho = new PdfPCell(new Paragraph("OUTRAS VACINAS"));
        cabecalho.setHorizontalAlignment(Element.ALIGN_CENTER);
        cabecalho.setBackgroundColor(BaseColor.LIGHT_GRAY);
        cabecalho.setColspan(2);
        PdfPTable minhaTabela = new PdfPTable(2);
        minhaTabela.setHorizontalAlignment(Element.ALIGN_CENTER);
        minhaTabela.addCell(cabecalho);
        minhaTabela.addCell("1ª DOSE");
        minhaTabela.addCell("2ª DOSE");

        try {
            pstmt = conn.prepareStatement(qry);
            pstmt.setInt(1, idPaciente);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                minhaTabela.addCell(this.criarDosesOutras(rs.getString("outra1"), rs.getString("dtOutra1"), rs.getString("loteOutra1"), rs.getString("valOutra1")));
                minhaTabela.addCell(this.criarDosesOutras(rs.getString("outra1d2"), rs.getString("dtOutra1d2"), rs.getString("loteOutra1d2"), rs.getString("valOutra1d2")));
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro: " + ex.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
        }
        documento.add(cabecalho);
        documento.add(minhaTabela);
    }

    public void outrasVacinas2(int idPaciente) throws DocumentException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String qry = "SELECT * FROM cartaovacina WHERE idPaciente= ?";

        PdfPCell cabecalho = new PdfPCell(new Paragraph("OUTRAS VACINAS"));
        cabecalho.setHorizontalAlignment(Element.ALIGN_CENTER);
        cabecalho.setBackgroundColor(BaseColor.LIGHT_GRAY);
        cabecalho.setColspan(2);
        PdfPTable minhaTabela = new PdfPTable(2);
        minhaTabela.setHorizontalAlignment(Element.ALIGN_CENTER);
        minhaTabela.addCell(cabecalho);
        minhaTabela.addCell("1ª DOSE");
        minhaTabela.addCell("2ª DOSE");

        try {
            pstmt = conn.prepareStatement(qry);
            pstmt.setInt(1, idPaciente);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                minhaTabela.addCell(this.criarDosesOutras(rs.getString("outra2"), rs.getString("dtOutra2"), rs.getString("loteOutra2"), rs.getString("valOutra2")));
                minhaTabela.addCell(this.criarDosesOutras(rs.getString("outra2d2"), rs.getString("dtOutra2d2"), rs.getString("loteOutra2d2"), rs.getString("valOutra2d2")));
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro: " + ex.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
        }
        documento.add(cabecalho);
        documento.add(minhaTabela);
    }

    public void pdfCartao(int idPaciente) throws FileNotFoundException, DocumentException, BadElementException, IOException {
        this.conectar();
        try {
            PdfWriter.getInstance(documento, new FileOutputStream("PDF_Cartão_Vacinação.pdf"));
            documento.setPageSize(PageSize.A4);
            documento.open();

            Image logoSecretaria = Image.getInstance(getClass().getResource("/imagens/Logo SES MG pb.jpg"));
            logoSecretaria.scaleToFit(200, 50);
            logoSecretaria.setAlignment(Image.ALIGN_CENTER);
            documento.add(logoSecretaria);

            this.identificacao(idPaciente);
            documento.add(new Paragraph("\n"));
            this.duplaAdulto(idPaciente);
            documento.add(new Paragraph("\n"));
            this.influenza(idPaciente);
            documento.add(new Paragraph("\n"));
            this.febreAmarela(idPaciente);
            documento.add(new Paragraph("\n"));
            this.duplaViral(idPaciente);
            documento.add(new Paragraph("\n"));
            this.tripliceViral(idPaciente);
            documento.add(new Paragraph("\n"));
            this.outrasVacinas1(idPaciente);
            documento.add(new Paragraph("\n"));
            this.outrasVacinas2(idPaciente);

            documento.close();
        } catch (FileNotFoundException | DocumentException ex) {
            Logger.getLogger(PesquisaPaciente.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            documento.close();
        }
        try {
            Desktop.getDesktop().open(new File("PDF_Cartão_Vacinação.pdf"));
        } catch (IOException ex) {
            Logger.getLogger(PesquisaPaciente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
