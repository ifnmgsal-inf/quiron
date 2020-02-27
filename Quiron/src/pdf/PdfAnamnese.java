/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pdf;

import bancodedados.MysqlConnect;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
import principal.PesquisaPaciente;

import java.io.FileOutputStream;
import java.io.IOException;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author roger
 */
public class PdfAnamnese {

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

    public void criarParagrafo(String pergunta, String resposta) throws DocumentException {
        Paragraph novoP = new Paragraph();
        novoP.add(new Chunk(pergunta, fontePergunta));
        novoP.add(new Chunk(resposta, fonteResposta));
        documento.add(novoP);
    }

    public void criarParagrafoMultiplaEscolha(String pergunta, String alternativas, String resposta) throws DocumentException {
        Paragraph novoP = new Paragraph();
        novoP.add(new Chunk(pergunta, fontePergunta));
        novoP.add(new Chunk(alternativas, fonteAlternativa));
        novoP.add(new Chunk(resposta, fonteResposta));
        documento.add(novoP);
    }

    public void pdfIdentificacao(int idPaciente) throws DocumentException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String qry = "SELECT * FROM pacientes WHERE idPaciente= ?";
        try {
            pstmt = conn.prepareStatement(qry);
            pstmt.setInt(1, idPaciente);
            rs = pstmt.executeQuery();

            //IDENTIFICAÇÃO
            while (rs.next()) {
                this.criarParagrafo("NOME: ", rs.getString("nome"));
                this.criarParagrafo("DATA NASC.: ", rs.getString("dtNascimento"));
                this.criarParagrafo("CURSO: ", rs.getString("curso"));
                this.criarParagrafo("TURMA/PERÍODO: ", rs.getString("turma"));
                //Endereço
                //String ederecoP = (" Rua: "+rs.getString("rua") +" Nº "+rs.getString("numero") +" Bairro: "+rs.getString("bairro") +" Municipio: "+rs.getString("municipio") +" UF: "+ rs.getString("uf"));
                this.criarParagrafo("ENDEREÇO: ", (" Rua: " + rs.getString("rua") + ", Nº " + rs.getString("numero") + ", Bairro: " + rs.getString("bairro") + ", Municipio: " + rs.getString("municipio") + ", UF: " + rs.getString("uf")));
                this.criarParagrafo("NOME DA MÃE: ", rs.getString("nomeMae"));
                this.criarParagrafo("TELEFONE: ", rs.getString("telefoneMae"));
                this.criarParagrafo("NOME DO PAI: ", rs.getString("nomePai"));
                this.criarParagrafo("TELEFONE: ", rs.getString("telefonePai"));
                this.criarParagrafo("RESPONSÁVEL EM SALINAS: ", rs.getString("nomeResponsavel"));
                this.criarParagrafo("TELEFONE: ", rs.getString("telefoneResponsavel"));
                //sexo
                switch (rs.getString("sexo")) {
                    case "M":
                        this.criarParagrafoMultiplaEscolha("SEXO: ", "(X)M ( )F", "");
                        break;
                    case "F":
                        this.criarParagrafoMultiplaEscolha("SEXO: ", "( )M (X)F", "");
                        break;
                    default:
                        this.criarParagrafoMultiplaEscolha("SEXO: ", "( )M ( )F", "");
                        break;
                }
                this.criarParagrafo("PESO: ", rs.getString("peso"));
                this.criarParagrafo("ALTURA: ", rs.getString("altura"));
                this.criarParagrafo("TIPO SANQUÍNEO: ", rs.getString("tipoSanguineo"));
                //Moradia
                switch (rs.getString("mora")) {
                    case "Pais":
                        this.criarParagrafoMultiplaEscolha("VOCÊ MORA: ", "(X)PAIS ( )ESCOLA ( )PARENTES ( )REPÚBLICA ( )SOZINHO(A) ", "");
                        break;
                    case "Escola":
                        this.criarParagrafoMultiplaEscolha("VOCÊ MORA: ", "( )PAIS (X)ESCOLA ( )PARENTES ( )REPÚBLICA ( )SOZINHO(A) ", "");
                        break;
                    case "Parentes":
                        this.criarParagrafoMultiplaEscolha("VOCÊ MORA: ", "( )PAIS ()ESCOLA (X)PARENTES ( )REPÚBLICA ( )SOZINHO(A) ", "");
                        break;
                    case "República":
                        this.criarParagrafoMultiplaEscolha("VOCÊ MORA: ", "( )PAIS ( )ESCOLA ( )PARENTES (X)REPÚBLICA ( )SOZINHO(A) ", "");
                        break;
                    case "Sozinho":
                        this.criarParagrafoMultiplaEscolha("VOCÊ MORA: ", "( )PAIS ( )ESCOLA ( )PARENTES ( )REPÚBLICA (X)SOZINHO(A) ", "");
                        break;
                    default:
                        this.criarParagrafoMultiplaEscolha("VOCÊ MORA: ", "( )PAIS ( )ESCOLA ( )PARENTES ( )REPÚBLICA ( )SOZINHO(A) ", "");
                        break;
                }
                //Regime
                switch (rs.getString("regime")) {
                    case "Interno":
                        this.criarParagrafoMultiplaEscolha("REGIME: ", "( )EXTERNO (X)RESIDENTE ( )SEMI-RESIDENTE ", "");
                        break;
                    case "Externo":
                        this.criarParagrafoMultiplaEscolha("REGIME: ", "(X)EXTERNO ( )RESIDENTE ( )SEMI-RESIDENTE ", "");
                        break;
                    case "Semi-Interno":
                        this.criarParagrafoMultiplaEscolha("REGIME: ", "( )EXTERNO ( )RESIDENTE (X)SEMI-RESIDENTE ", "");
                        break;
                    default:
                        this.criarParagrafoMultiplaEscolha("REGIME: ", "( )EXTERNO ( )RESIDENTE ( )SEMI-RESIDENTE ", "");
                        break;
                }
                //Plano de Saúde
                switch (rs.getString("planoSaude")) {
                    case "null":
                        this.criarParagrafoMultiplaEscolha("VOCÊ TEM PLANO DE SAÚDE? ", "( )NÃO ( )SIM QUAL? ", "");
                        break;
                    case "Não":
                        this.criarParagrafoMultiplaEscolha("VOCÊ TEM PLANO DE SAÚDE? ", "(X)NÃO ( )SIM ", "");
                        break;
                    default:
                        this.criarParagrafoMultiplaEscolha("VOCÊ TEM PLANO DE SAÚDE? ", "( )NÃO (X)SIM QUAL? ", rs.getString("planoSaude"));
                        break;
                }
                //Cartão SUS
                switch (rs.getString("cartaoSus")) {
                    case "null":
                        this.criarParagrafoMultiplaEscolha("VOCÊ TEM CARTÃO SUS? ", "( )NÃO ( )SIM ", "");
                        break;
                    case "Não possui":
                        this.criarParagrafoMultiplaEscolha("VOCÊ TEM CARTÃO SUS? ", "(X)NÃO ( )SIM ", "");
                        break;
                    default:
                        this.criarParagrafoMultiplaEscolha("VOCÊ TEM CARTÃO SUS? ", "( )NÃO (X)SIM Número: ", rs.getString("cartaoSus"));
                        break;
                }

                // FIM IDENTIFICAÇÃO
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro: " + ex.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void pdfHistoriaPregressa(int idPaciente) throws DocumentException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String qry = "SELECT * FROM pacientes WHERE idPaciente= ?";
        try {
            pstmt = conn.prepareStatement(qry);
            pstmt.setInt(1, idPaciente);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                // HISTÓRIA PREGESSA
                //Vacinas infância
                if (null == rs.getString("vacinasInfancia")) {
                    this.criarParagrafoMultiplaEscolha("VACINAS NA INFÂNCIA: ", "( )CARTÃO COMPLETO ( )CARTÃO INCOMPLETO", "");
                } else {
                    switch (rs.getString("vacinasInfancia")) {
                        case "Completo":
                            this.criarParagrafoMultiplaEscolha("VACINAS NA INFÂNCIA: ", "(X)CARTÃO COMPLETO ( )CARTÃO INCOMPLETO", "");
                            break;
                        case "Incompleto":
                            this.criarParagrafoMultiplaEscolha("VACINAS NA INFÂNCIA: ", "( )CARTÃO COMPLETO (X)CARTÃO INCOMPLETO", "");
                            break;
                        case "null":
                            this.criarParagrafoMultiplaEscolha("VACINAS NA INFÂNCIA: ", "( )CARTÃO COMPLETO ( )CARTÃO INCOMPLETO", "");
                        default:
                            this.criarParagrafoMultiplaEscolha("VACINAS NA INFÂNCIA: ", "( )CARTÃO COMPLETO ( )CARTÃO INCOMPLETO", "");
                            break;
                    }
                }

                //Vacinas na Adolescência
                if (null == rs.getString("vacinasAdolescencia")) {
                    this.criarParagrafoMultiplaEscolha("VACINAS NA ADOLESCÊNCIA: ", "( )CARTÃO COMPLETO ( )CARTÃO INCOMPLETO", "");
                } else {
                    switch (rs.getString("vacinasAdolescencia")) {
                        case "Completo":
                            this.criarParagrafoMultiplaEscolha("VACINAS NA ADOLESCÊNCIA: ", "(X)CARTÃO COMPLETO ( )CARTÃO INCOMPLETO", "");
                            break;
                        case "Incompleto":
                            this.criarParagrafoMultiplaEscolha("VACINAS NA ADOLESCÊNCIA: ", "( )CARTÃO COMPLETO (X)CARTÃO INCOMPLETO", "");
                            this.criarParagrafo("Qual vacina falta? ", rs.getString("vacinaFaltando"));
                            break;
                        default:
                            this.criarParagrafoMultiplaEscolha("VACINAS NA ADOLESCÊNCIA: ", "( )CARTÃO COMPLETO ( )CARTÃO INCOMPLETO", "");
                            break;
                    }
                }

                //Doenças na infância e adolescência
                this.criarParagrafo("DOENÇAS NA INFÂNCIA E ADOLESCÊNCIA:", "");
                String doencasIA = "";
                if ("Sim".equals(rs.getString("catapora"))) {
                    doencasIA += "(X)CATAPORA";
                } else {
                    doencasIA += "( )CATAPORA";
                }
                if ("Sim".equals(rs.getString("caxumba"))) {
                    doencasIA += " (X)CAXUMBA";
                } else {
                    doencasIA += " ( )CAXUMBA";
                }
                if ("Sim".equals(rs.getString("dengue"))) {
                    doencasIA += " (X)DENGUE";
                } else {
                    doencasIA += " ( )DENGUE";
                }
                if ("Sim".equals(rs.getString("hepatite"))) {
                    doencasIA += " (X)HEPATITE";
                } else {
                    doencasIA += " ( )HEPATITE";
                }
                if ("Sim".equals(rs.getString("meningite"))) {
                    doencasIA += " (X)MENINGITE";
                } else {
                    doencasIA += " ( )MENINGITE";
                }
                if ("Sim".equals(rs.getString("pneumonia"))) {
                    doencasIA += " (X)PNEUMONIA";
                } else {
                    doencasIA += " ( )PNEUMONIA";
                }
                if ("Sim".equals(rs.getString("rubeola"))) {
                    doencasIA += " (X)RUBÉOLA";
                } else {
                    doencasIA += " ( )RUBÉOLA";
                }
                if ("Sim".equals(rs.getString("sarampo"))) {
                    doencasIA += " (X)SARAMPO";
                } else {
                    doencasIA += " ( )SARAMPO";
                }
                this.criarParagrafoMultiplaEscolha("DOENÇAS CONTRAÍDAS: ", doencasIA, "");

                //Cirurgias realizadas
                if (null == rs.getString("cirurgiaRealizada")) {
                    this.criarParagrafoMultiplaEscolha("CIRURGIAS REALIZADAS: ", "( )NÃO ( )SIM ", "");
                } else {
                    switch (rs.getString("cirurgiaRealizada")) {
                        case "Não":
                            this.criarParagrafoMultiplaEscolha("CIRURGIAS REALIZADAS: ", "(X)NÃO ( )SIM", "");
                            break;
                        default:
                            this.criarParagrafoMultiplaEscolha("CIRURGIAS REALIZADAS: ", "( )NÃO (X)SIM ", rs.getString("CirurgiaRealizada"));
                            break;
                    }
                }

                //Alergia medicamentosa
                if (null == rs.getString("alergiaMedicamentosa")) {
                    this.criarParagrafoMultiplaEscolha("ALERGIAS MEDICAMENTOSAS: ", "( )NÃO ( )SIM", "");
                } else {
                    switch (rs.getString("alergiaMedicamentosa")) {
                        case "Não":
                            this.criarParagrafoMultiplaEscolha("ALERGIAS MEDICAMENTOSAS: ", "(X)NÃO ( )SIM", "");
                            break;
                        default:
                            this.criarParagrafoMultiplaEscolha("ALERGIAS MEDICAMENTOSAS: ", "( )NÃO (X)SIM ", rs.getString("alergiaMedicamentosa"));
                            break;
                    }
                }

                //Alergia alimentar
                if (null == rs.getString("alergiaAlimentar")) {
                    this.criarParagrafoMultiplaEscolha("ALERGIAS ALIMENTARES: ", "( )NÃO ( )SIM", "");
                } else {
                    switch (rs.getString("alergiaAlimentar")) {
                        case "Não":
                            this.criarParagrafoMultiplaEscolha("ALERGIAS ALIMENTARES: ", "(X)NÃO ( )SIM", "");
                            break;
                        default:
                            this.criarParagrafoMultiplaEscolha("ALERGIAS ALIMENTARES: ", "( )NÃO (X)SIM ", rs.getString("alergiaAlimentar"));
                            break;
                    }
                }

            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro: " + ex.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void pdfHistoricoFamiliar(int idPaciente) throws DocumentException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String qry = "SELECT * FROM pacientes WHERE idPaciente= ?";
        try {
            pstmt = conn.prepareStatement(qry);
            pstmt.setInt(1, idPaciente);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                String doencasFamilia = "";
                if ("Sim".equals(rs.getString("cancer"))) {
                    doencasFamilia += "(X)CÂNCER ";
                } else {
                    doencasFamilia += "( )CÂNCER ";
                }
                if ("Sim".equals(rs.getString("cardiopatias"))) {
                    doencasFamilia += "(X)CARDIOPATIAS ";
                } else {
                    doencasFamilia += "( )CARDIOPATIAS ";
                }
                if ("Sim".equals(rs.getString("diabetes"))) {
                    doencasFamilia += "(X)DIABETES ";
                } else {
                    doencasFamilia += "( )DIABETES ";
                }
                if ("Sim".equals(rs.getString("hipertensaoArterial"))) {
                    doencasFamilia += "(X)HIPERTENSÃO ARTERIAL ";
                } else {
                    doencasFamilia += "( )HIPERTENSÃO ARTERIAL ";
                }
                if ("Sim".equals(rs.getString("oftalmologico"))) {
                    doencasFamilia += "(X)PROBLEMAS OFTALMOLÓGICOS ";
                } else {
                    doencasFamilia += "( )PROBLEMAS OFTALMOLÓGICOS ";
                }
                if ("Sim".equals(rs.getString("renal"))) {
                    doencasFamilia += "(X)PROBLEMAS RENAIS ";
                } else {
                    doencasFamilia += "( )PROBLEMAS RENAIS ";
                }
                if ("Sim".equals(rs.getString("mental"))) {
                    doencasFamilia += "(X)PROBLEMAS MENTAIS ";
                } else {
                    doencasFamilia += "( )PROBLEMAS MENTAIS ";
                }
                if ("Não".equals(rs.getString("doencaEspecifica"))) {
                    doencasFamilia += "( )OUTRAS ";
                } else {
                    doencasFamilia += "(X)OUTRAS. ESPECIFICAR: " + rs.getString("doencaEspecifica");
                }
                this.criarParagrafoMultiplaEscolha("", doencasFamilia, "");

            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro: " + ex.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void pdfHistoricoDoencaAtual(int idPaciente) throws DocumentException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String qry = "SELECT * FROM pacientes WHERE idPaciente= ?";
        try {
            pstmt = conn.prepareStatement(qry);
            pstmt.setInt(1, idPaciente);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                documento.add(new Paragraph("VOCÊ APRESENTA ALGUMA NECESSIDADE ESPECÍFICA OU DIFICULDADE RELACIONADAS ABAIXO", fontePergunta));
                String necessidadeEspecifica = "";

                if ("Sim".equals(rs.getString("deficienciaAuditiva"))) {
                    necessidadeEspecifica += "(X)DEFICIÊNCIA AUDITIVA ";
                } else {
                    necessidadeEspecifica += "( )DEFICIÊNCIA AUDITIVA ";
                }
                if ("Sim".equals(rs.getString("deficienciaFisica"))) {
                    necessidadeEspecifica += "(X)DEFICIÊNCIA FÍSICA ";
                } else {
                    necessidadeEspecifica += "( )DEFICIÊNCIA FÍSICA ";
                }
                if ("Sim".equals(rs.getString("deficienciaVisual"))) {
                    necessidadeEspecifica += "(X)DEFICIÊNCIA VISUAL ";
                } else {
                    necessidadeEspecifica += "( )DEFICIÊNCIA VISUAL ";
                }
                if ("Sim".equals(rs.getString("dificuldadeConcentracao"))) {
                    necessidadeEspecifica += "(X)DIFICULDADE DE CONCENTRAÇÃO ";
                } else {
                    necessidadeEspecifica += "( )DIFICULDADE DE CONCENTRAÇÃO ";
                }
                if ("Sim".equals(rs.getString("dificuldadeEscrita"))) {
                    necessidadeEspecifica += "(X)DIFICULDADE NA ESCRITA ";
                } else {
                    necessidadeEspecifica += "( )DIFICULDADE NA ESCRITA ";
                }
                if ("Sim".equals(rs.getString("dificuldadeLeitura"))) {
                    necessidadeEspecifica += "(X)DIFICULDADE NA LEITURA ";
                } else {
                    necessidadeEspecifica += "( )DIFICULDADE NA LEITURA ";
                }
                if ("Sim".equals(rs.getString("superdotacao"))) {
                    necessidadeEspecifica += "(X)SUPERDOTAÇÃO/ALTAS HABILIDADES ";
                } else {
                    necessidadeEspecifica += "( )SUPERDOTAÇÃO/ALTAS HABILIDADES ";
                }
                if ("Sim".equals(rs.getString("transtornoDesenvolvimento"))) {
                    necessidadeEspecifica += "(X)TRANSTORNO GLOBAL DO DESENVOLVIMENTO ";
                } else {
                    necessidadeEspecifica += "( )TRANSTORNO GLOBAL DO DESENVOLVIMENTO ";
                }
                if ("Sim".equals(rs.getString("nenhumaEspecifica"))) {
                    necessidadeEspecifica += "(X)NENHUMA NECESSIDADE ESPECÍFICA DESCRITA ACIMA ";
                } else {
                    necessidadeEspecifica += "( )NENHUMA NECESSIDADE ESPECÍFICA DESCRITA ACIMA ";
                }
                this.criarParagrafoMultiplaEscolha("", necessidadeEspecifica, "");

                //Protese
                if (null == rs.getString("protese")) {
                    this.criarParagrafoMultiplaEscolha("USA PRÓTESE? ", "( )NÃO ( )SIM", "");
                } else {
                    switch (rs.getString("protese")) {
                        case "Sim":
                            this.criarParagrafoMultiplaEscolha("USA PRÓTESE? ", "( )NÃO (X)SIM", "");
                            break;
                        case "Não":
                            this.criarParagrafoMultiplaEscolha("USA PRÓTESE? ", "(X)NÃO ( )SIM", "");
                            break;
                        default:
                            this.criarParagrafoMultiplaEscolha("USA PRÓTESE? ", "( )NÃO ( )SIM", "");
                            break;
                    }
                }

                //Doença crônica
                documento.add(new Paragraph("ALGUMA DOENÇA CRÔNICA? ", fontePergunta));
                String doencaCronica = "";
                if ("Sim".equals(rs.getString("asma"))) {
                    doencaCronica += "(X)ASMA";
                } else {
                    doencaCronica += "( )ASMA";
                }
                if ("Sim".equals(rs.getString("bronquite"))) {
                    doencaCronica += "(X)BRONQUITE";
                } else {
                    doencaCronica += "( )BRONQUITE";
                }
                if ("Sim".equals(rs.getString("cronicaDiabetes"))) {
                    doencaCronica += "(X)DIABETES";
                } else {
                    doencaCronica += "( )DIABETES";
                }
                if ("Sim".equals(rs.getString("pressaoAlta"))) {
                    doencaCronica += "(X)PRESSÃO ALTA";
                } else {
                    doencaCronica += "( )PRESSÃO ALTA";
                }
                if ("Sim".equals(rs.getString("problemaCardiaco"))) {
                    doencaCronica += "(X)PROBLEMAS CARDÍACOS";
                } else {
                    doencaCronica += "( )PROBLEMAS CARDÍACOS";
                }
                if ("Sim".equals(rs.getString("problemaRenal"))) {
                    doencaCronica += "(X)PROBLEMAS RENAIS";
                } else {
                    doencaCronica += "( )PROBLEMAS RENAIS";
                }
                if ("Sim".equals(rs.getString("rinite"))) {
                    doencaCronica += "(X)RINITE";
                } else {
                    doencaCronica += "( )RINITE";
                }
                if ("Não".equals(rs.getString("doencaCronicaOutros"))) {
                    doencaCronica += "( )OUTRAS ";
                    this.criarParagrafoMultiplaEscolha("", doencaCronica, "");
                } else if (null == rs.getString("doencaCronicaOutros")) {
                    doencaCronica += "( )OUTRAS ";
                    this.criarParagrafoMultiplaEscolha("", doencaCronica, "");
                } else {
                    doencaCronica += "(X)OUTRAS ";
                    this.criarParagrafoMultiplaEscolha("", doencaCronica, rs.getString("doencaCronicaOutros"));
                }

                //Acompanhamento
                if (null == rs.getString("acompanhamentoProblema")) {
                    this.criarParagrafoMultiplaEscolha("FAZ ACOMPANHAMENTO DESSE PROBLEMA? ", "( )NÃO ( )SIM", "");
                } else {
                    switch (rs.getString("acompanhamentoProblema")) {
                        case "Sim":
                            this.criarParagrafoMultiplaEscolha("FAZ ACOMPANHAMENTO DESSE PROBLEMA? ", "( )NÃO (X)SIM", rs.getString("acompanhamentoProblema"));
                            break;
                        case "Não":
                            this.criarParagrafoMultiplaEscolha("FAZ ACOMPANHAMENTO DESSE PROBLEMA? ", "(X)NÃO ( )SIM", "");
                            break;
                        default:
                            this.criarParagrafoMultiplaEscolha("FAZ ACOMPANHAMENTO DESSE PROBLEMA? ", "( )NÃO ( )SIM", "");
                            break;
                    }
                }

                //Medicamentos contínuos
                this.criarParagrafo("MEDICAMENTOS DE USO CONTÍNUO: ", rs.getString("medicamentoContinuo"));
                this.criarParagrafo("JÁ SOFREU DE DESMAIOS OU CONVULSÕES? COM QUE FREQUÊNCIA? POR QUAIS MOTIVOS? ", rs.getString("desmaios"));
                this.criarParagrafo("JÁ SOFREU DE EPISTAXE (SANGRAMENTO NASAL)? COM QUE FREQUÊNCIA? ", rs.getString("epistaxe"));
                this.criarParagrafo("QUANTO COSTUMA SER A PRESSÃO ARTERIAL? ", rs.getString("pressaoArterial"));
                this.criarParagrafo("COSTUMA SENTIR CEFALÉIA (DOR DE CABEÇA)? SE SIM, QUAL MEDICAÇÃO COSTUMA USAR PARA ALIVIÁ-LA? ", rs.getString("cefaleia"));

                if (null == rs.getString("diarreia")) {
                    this.criarParagrafo("SENTE COM FREQUÊNCIA DIARRÉIA, NÁUSEAS, DOR DE ESTÔMAGO OU CONSTIPAÇÃO INTESTINAL? ", "");
                } else {
                    this.criarParagrafo("SENTE COM FREQUÊNCIA DIARRÉIA, NÁUSEAS, DOR DE ESTÔMAGO OU CONSTIPAÇÃO INTESTINAL? ", rs.getString("diarreia"));
                }

                if (null == rs.getString("sexo")) {
                    this.criarParagrafo("(MENINAS) COSTUMA SENTIR CÓLICA MENSTRUAL? SE SIM, O QUE USA PARA ALIVIÁ-LA? ", "");
                }
                if ("F".equals(rs.getString("sexo"))) {
                    this.criarParagrafo("(MENINAS) COSTUMA SENTIR CÓLICA MENSTRUAL? SE SIM, O QUE USA PARA ALIVIÁ-LA? ", rs.getString("colica"));
                }

                //Acompanhamento Especializado
                String acompanhamentoEspecializado = "";
                documento.add(new Paragraph("FAZ ACOMPANHAMENTO ESPECIALIZADO?", fontePergunta));
                if ("Sim".equals(rs.getString("fisioterapia"))) {
                    acompanhamentoEspecializado += "(X)FISIOTERAPIA ";
                } else {
                    acompanhamentoEspecializado += "( )FISITERAPIA ";
                }
                if ("Sim".equals(rs.getString("fonaudiologo"))) {
                    acompanhamentoEspecializado += "(X)FONAUDIOLÓGICO ";
                } else {
                    acompanhamentoEspecializado += "( )FONAUDIOLÓGICO ";
                }
                if ("Sim".equals(rs.getString("psicologico"))) {
                    acompanhamentoEspecializado += "(X)PSICOLÓGICO ";
                } else {
                    acompanhamentoEspecializado += "( )PSICOLÓGICO ";
                }
                if ("Sim".equals(rs.getString("terapiaOcupacional"))) {
                    acompanhamentoEspecializado += "(X)TERAPIA OCUPACIONAL ";
                } else {
                    acompanhamentoEspecializado += "( )TERAPIA OCUPACIONAL ";
                }
                if (null == rs.getString("acompanhamentoEspecializadoOutro")) {
                    acompanhamentoEspecializado += "( )OUTRO ";
                    this.criarParagrafoMultiplaEscolha("", acompanhamentoEspecializado, "");
                } else {
                    switch (rs.getString("acompanhamentoEspecializadoOutro")) {
                        case "Não":
                            acompanhamentoEspecializado += "( )OUTRO ";
                            this.criarParagrafoMultiplaEscolha("", acompanhamentoEspecializado, "");
                            break;
                        default:
                            acompanhamentoEspecializado += "(X)OUTRO ";
                            this.criarParagrafoMultiplaEscolha("", acompanhamentoEspecializado, rs.getString("acompanhamentoEspecializadoOutro"));
                            break;
                    }
                }

                this.criarParagrafo("INFORMAÇÃO RELEVANTE EM RELAÇÃO A SAÚDE DO PACIENTE QUE NÃAO CONSTA NESTA FICHA: ", rs.getString("anotacaoRelevante"));
                this.criarParagrafo("CONTATO DE EMERGÊNCIA: ", rs.getString("contatoEmergencia"));
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro: " + ex.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void pdfAnamnese(int idPaciente) {
        try {
            PdfWriter.getInstance(documento, new FileOutputStream("PDF_Anamnese.pdf"));
            documento.setPageSize(PageSize.A4);

            //Preparando o documento para receber dados
            documento.open();

            //Imagem - Logo IFNMG - Campus Salinas
            Image logoIF;
            try {
                logoIF = Image.getInstance(getClass().getResource("/imagens/Logo IF G.jpg"));
                logoIF.scaleToFit(200, 67);
                logoIF.setAlignment(Image.ALIGN_CENTER);
                documento.add(logoIF);
            } catch (BadElementException | IOException ex) {
                Logger.getLogger(PdfAnamnese.class.getName()).log(Level.SEVERE, null, ex);
            }
            //FIM Imagem - Logo IFNMG - Campus Salinas

            //Declarondo os títulos
            Paragraph titulo = new Paragraph("CGAE - COORDENAÇÃO GERAL DE ASSISTÊNCIA AO EDUCANDO", fonteTitulo);
            Paragraph titulo2 = new Paragraph("FAE - FICHA DE ASSISTÊNCIA AO EDUCANDO", fonteTitulo);
            titulo.setAlignment(Element.ALIGN_CENTER);
            titulo2.setAlignment(Element.ALIGN_CENTER);
            documento.add(titulo);
            documento.add(titulo2);

            this.conectar();
            documento.add(new Paragraph("IDENTIFICAÇÃO", fonteTitulo));
            this.pdfIdentificacao(idPaciente);
            documento.add(new Paragraph("HISTÓRIA PREGRESSA", fonteTitulo));
            this.pdfHistoriaPregressa(idPaciente);
            documento.add(new Paragraph("HISTÓRICO FAMILIAR", fonteTitulo));
            this.pdfHistoricoFamiliar(idPaciente);
            documento.add(new Paragraph("HISTÓRIA DE DOENÇA ATUAL", fonteTitulo));
            this.pdfHistoricoDoencaAtual(idPaciente);

            documento.add(new Paragraph("OBSERVAÇÕES: CASO NÃO QUERIA RELATAR"
                    + " O PROBLEMA DE SAÚDE NESTE FORMULÁRIO, FAVOR PROCURAR"
                    + " O SETOR DE ENFERMAGEM OU DE PSICOLOGIA PARA INFORMAR"
                    + " SOBRE ALGUM PROCEDIMENTO OU CUIDADO DE QUE TENHA"
                    + " NECESSIDADE.", fontePergunta));
            documento.add(new Paragraph("-EM CASO DE USO CONTÍNUO DE MEDICAÇÃO"
                    + " E A MESMA PRECISA SER ADMINISTRADA NA ESCOLA, FAVOR"
                    + " TRAZER A RECEITA MÉDICA. NENHUMA MEDICAÇÃO SERÁ"
                    + " ADMINISTRADA NA ESCOLA SEM A APRESENTAÇÃO DE RECEITA"
                    + " MÉDICA.", fontePergunta));
            documento.add(new Paragraph("-É DE SUMA IMPORTÂNCIA QUE TRAGAM"
                    + " SEMPRE PARA A ESCOLA OS DOCUMENTOS (RG, CARTÃO SUS,"
                    + " CARTÃO DO PLANO DE SAÚDE), ORIGINAL OU CÓPIA, CASO"
                    + " NECESSITEM DE ATENDIMENTO DE URGÊNCIA E EMERGÊNCIA"
                    + " NO HOSPITAL.", fontePergunta));

            documento.add(new Paragraph("SALINAS, ____ DE __________ DE 201__.", fontePergunta));
            Paragraph campoAssinatura = new Paragraph("_____________________________________", fontePergunta);
            Paragraph assinatura = new Paragraph("ASSINATURA DO ALUNO OU RESPONSÁVEL", fontePergunta);
            campoAssinatura.setAlignment(Element.ALIGN_CENTER);
            assinatura.setAlignment(Element.ALIGN_CENTER);
            documento.add(campoAssinatura);
            documento.add(assinatura);

            documento.close();
        } catch (FileNotFoundException | DocumentException ex) {
            System.out.println("Aqui");
            Logger.getLogger(PesquisaPaciente.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            documento.close();
        }
        try {
            Desktop.getDesktop().open(new File("PDF_Anamnese.pdf"));
        } catch (IOException ex) {
            Logger.getLogger(PesquisaPaciente.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
