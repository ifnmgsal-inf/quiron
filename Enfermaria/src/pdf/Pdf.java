/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pdf;

import bancodedados.MysqlConnect;
import java.awt.Desktop;
import java.io.File;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

/**
 *
 * @author Franciele Alves Barbosa e Rogério Costa Negro Rocha
 */
public class Pdf {

    Connection conn = null;

    public void conectar() {
        try {
            conn = MysqlConnect.connectDB();
        } catch (SQLException sqle) {
            JOptionPane.showMessageDialog(null, "Erro ao conectar com o Banco de Dados " /*+ ex.getMessage()*/, "ERRO", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void PdfAnamnese(String idPaciente) {
        this.conectar();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String qry = "SELECT * FROM pacientes WHERE idPaciente= ?";
        try {
            pstmt = conn.prepareStatement(qry);
            pstmt.setString(1, idPaciente);
            rs = pstmt.executeQuery();
            JRResultSetDataSource jrRS = new JRResultSetDataSource(rs);

            //caminho do relatório
            //System.getProperty("user.dir")
            InputStream caminhoRelatorio = this.getClass().getClassLoader().getResourceAsStream("pdf/FichaAnamnese1.jasper");
            JasperPrint jasperPrint = JasperFillManager.fillReport(caminhoRelatorio, new HashMap(), jrRS);
            JasperExportManager.exportReportToPdfFile(jasperPrint, System.getProperty("user.dir")+"/PDFs/Anamnese1.pdf");
            File pdf = new File(System.getProperty("user.dir")+"/PDFs/Anamnese1.pdf");

            try {
                Desktop.getDesktop().open(pdf);
            } catch (Exception e) {
                JOptionPane.showConfirmDialog(null, e);
            }
            pdf.deleteOnExit();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro: " + ex.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void PdfAnamnese2(String idPaciente) {
        this.conectar();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String qry = "SELECT * FROM pacientes WHERE idPaciente= ?";
        try {
            pstmt = conn.prepareStatement(qry);
            pstmt.setString(1, idPaciente);
            rs = pstmt.executeQuery();
            JRResultSetDataSource jrRS = new JRResultSetDataSource(rs);

            //caminho do relatório
            //System.getProperty("user.dir")
            InputStream caminhoRelatorio = this.getClass().getClassLoader().getResourceAsStream("pdf/FichaAnamnese2.jasper");
            JasperPrint jasperPrint = JasperFillManager.fillReport(caminhoRelatorio, new HashMap(), jrRS);
            JasperExportManager.exportReportToPdfFile(jasperPrint, System.getProperty("user.dir")+"/PDFs/Anamnese2.pdf");
            File pdf = new File(System.getProperty("user.dir")+"/PDFs/Anamnese2.pdf");

            try {
                Desktop.getDesktop().open(pdf);
            } catch (Exception e) {
                JOptionPane.showConfirmDialog(null, e);
            }
            pdf.deleteOnExit();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro: " + ex.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void PdfAnamnese3(String idPaciente) {
        this.conectar();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String qry = "SELECT * FROM pacientes WHERE idPaciente= ?";
        try {
            pstmt = conn.prepareStatement(qry);
            pstmt.setString(1, idPaciente);
            rs = pstmt.executeQuery();
            JRResultSetDataSource jrRS = new JRResultSetDataSource(rs);

            //caminho do relatório
            //System.getProperty("user.dir")
            InputStream caminhoRelatorio = this.getClass().getClassLoader().getResourceAsStream("pdf/FichaAnamnese3.jasper");
            JasperPrint jasperPrint = JasperFillManager.fillReport(caminhoRelatorio, new HashMap(), jrRS);
            JasperExportManager.exportReportToPdfFile(jasperPrint, System.getProperty("user.dir")+"/PDFs/Anamnese3.pdf");
            File pdf = new File(System.getProperty("user.dir")+"/PDFs/Anamnese3.pdf");

            try {
                Desktop.getDesktop().open(pdf);
            } catch (Exception e) {
                JOptionPane.showConfirmDialog(null, e);
            }
            pdf.deleteOnExit();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro: " + ex.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void PdfCartaoVacina(String idPaciente) {
        this.conectar();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String qry = "SELECT * FROM cartaovacina WHERE idPaciente= ?";
        try {
            pstmt = conn.prepareStatement(qry);
            pstmt.setString(1, idPaciente);
            rs = pstmt.executeQuery();
            JRResultSetDataSource jrRS = new JRResultSetDataSource(rs);

            //caminho do relatório
            InputStream caminhoRelatorio = this.getClass().getClassLoader().getResourceAsStream("pdf/CartaoVacina.jasper");
            JasperPrint jasperPrint = JasperFillManager.fillReport(caminhoRelatorio, new HashMap(), jrRS);
            JasperExportManager.exportReportToPdfFile(jasperPrint, System.getProperty("user.dir")+"/PDFs/CartaoVacinas.pdf");
            File pdf = new File(System.getProperty("user.dir")+"/PDFs/CartaoVacinas.pdf");
            try {
                Desktop.getDesktop().open(pdf);
            } catch (Exception e) {
                JOptionPane.showConfirmDialog(null, e);
            }
            pdf.deleteOnExit();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro: " + ex.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void PdfFicha(String idPaciente) {
        this.conectar();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        //String qry = "select * from fichas where cpfPaciente= ? order by fichas.data";

        String qry = "SELECT nome, cpf, curso, cartaoSus, matricula, rua, numero, bairro, municipio, uf,"
                + " dtNascimento, fichas.* FROM pacientes INNER JOIN fichas"
                + " ON pacientes.idPaciente = fichas.idPaciente "
                + "WHERE pacientes.idPaciente= ? order by fichas.data";

        try {
            pstmt = conn.prepareStatement(qry);
            pstmt.setString(1, idPaciente);
            rs = pstmt.executeQuery();
            JRResultSetDataSource jrRS = new JRResultSetDataSource(rs);

            //caminho
            InputStream caminhoRelatorio = this.getClass().getClassLoader().getResourceAsStream("pdf/Fichas.jasper");
            JasperPrint jasperPrint = JasperFillManager.fillReport(caminhoRelatorio, new HashMap(), jrRS);
            JasperExportManager.exportReportToPdfFile(jasperPrint, System.getProperty("user.dir")+"/PDFs/Fichas.pdf");
            File pdf = new File(System.getProperty("user.dir")+"/PDFs/Fichas.pdf");
            try {
                Desktop.getDesktop().open(pdf);
            } catch (Exception e) {
                JOptionPane.showConfirmDialog(null, e);
            }
            pdf.deleteOnExit();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro: " + ex.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
