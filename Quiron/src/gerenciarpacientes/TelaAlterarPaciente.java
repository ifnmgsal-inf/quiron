/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gerenciarpacientes;

import principal.PesquisaPaciente;
import bancodedados.MysqlConnect;
import java.awt.Color;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JOptionPane;

/**
 *
 * @author Franciele Alves Barbosa e Rogério Costa Negro Rocha
 */
public class TelaAlterarPaciente extends javax.swing.JFrame {

    Connection conn = null;
    //public String controleID = "";

    /**
     * Creates new form TelaCadastroPaciente
     */
    public TelaAlterarPaciente() {
        initComponents();
        try {
            conn = MysqlConnect.connectDB();
            this.cursos();
            this.imprimir();
            //JOptionPane.showMessageDialog(null, "Conexao bem sucedida");
        } catch (SQLException sqle) {
            JOptionPane.showMessageDialog(null, "Erro ao conectar com o Banco de Dados " /*+ ex.getMessage()*/, "ERRO", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void sair() {
        int op = JOptionPane.showConfirmDialog(null, "Deseja realmente sair?", "ATENÇÃO", JOptionPane.YES_OPTION);
        if (op == JOptionPane.YES_OPTION) {
            this.dispose();
        }
    }

    public void entraMouse(JButton botao) {
        botao.setOpaque(true);
        botao.setContentAreaFilled(true);
        botao.setBorderPainted(true);
        botao.setBackground(Color.LIGHT_GRAY);
    }

    public void saiMouse(JButton botao) {

        botao.setOpaque(false);
        botao.setContentAreaFilled(false);
        botao.setBorderPainted(false);
    }

    public void cursos() {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String qry = "SELECT curso FROM cursos order by curso";

        try {
            pstmt = conn.prepareStatement(qry);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                cbCurso.addItem(rs.getString("curso"));
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro: " + ex.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void imprimir() {
        this.imprimirIdentificacao();
        this.imprimirHistoriaPregressa();
        this.imprimirHistoricoFamiliar();
        this.imprimirDoencaAtual();
        this.imprimirQuestionario1();
        this.imprimirQuestionario2();
    }

    public void imprimirIdentificacao() {
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        String qry = "SELECT idPaciente, cpf, nome, dtNascimento, matricula, curso, turma, rua, numero, bairro, municipio, uf,"
                + " telefone, nomeMae, telefoneMae, nomePai, telefonePai, nomeResponsavel, telefoneResponsavel, sexo, peso, altura,"
                + "tipoSanguineo, mora, regime, planoSaude, cartaoSus"
                + " FROM pacientes WHERE idPaciente= ?";

        try {
            pstmt = conn.prepareStatement(qry);
            pstmt.setString(1, PesquisaPaciente.id);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                tfIdPaciente.setText(rs.getString("idPaciente"));
                tfCpf.setText(rs.getString("cpf"));
                //this.controleID = rs.getString("idPaciente");
                tfNome.setText(rs.getString("nome"));
                tfDtNasc.setText(rs.getString("dtNascimento"));
                tfMatricula.setText(rs.getString("matricula"));
                //cbCurso.setSelectedIndex(rs.getString("curso"));//Resolver
                //Curso
                for (int i = 0; i < cbCurso.getItemCount(); i++) {
                    if ((cbCurso.getItemAt(i)).equals(rs.getString("curso"))) {
                        cbCurso.setSelectedIndex(i);
                        break;
                    }
                }

                tfTurma.setText(rs.getString("turma"));
                tfRua.setText(rs.getString("rua"));
                tfNCasa.setText(rs.getString("numero"));
                tfBairro.setText(rs.getString("bairro"));
                tfCidade.setText(rs.getString("municipio"));
                tfUf.setText(rs.getString("uf"));
                tfTelefone.setText(rs.getString("telefone"));
                tfMae.setText(rs.getString("nomeMae"));
                tfTelMae.setText(rs.getString("telefoneMae"));
                tfPai.setText(rs.getString("nomePai"));
                tfTelPai.setText(rs.getString("telefonePai"));
                tfResponsavel.setText(rs.getString("nomeResponsavel"));
                tfTelResponsavel.setText(rs.getString("telefoneResponsavel"));
                //sexo
                if (null == rs.getString("sexo")) {
                    bgSexo.clearSelection();
                } else {
                    switch (rs.getString("sexo")) {
                        case "M":
                            rbMasculino.setSelected(true);
                            break;
                        case "F":
                            rbFeminino.setSelected(true);
                            break;
                        default:
                            bgSexo.clearSelection();
                            break;
                    }
                }
                tfPeso.setText(rs.getString("peso"));
                tfAltura.setText(rs.getString("altura"));
                tfSangue.setText(rs.getString("tipoSanguineo"));

                //Moradia
                if (null == rs.getString("mora")) {
                    bgMoradia.clearSelection();
                } else {
                    switch (rs.getString("mora")) {
                        case "Pais":
                            rbPais.setSelected(true);
                            break;
                        case "Escola":
                            rbEscola.setSelected(true);
                            break;
                        case "Parentes":
                            rbParentes.setSelected(true);
                            break;
                        case "República":
                            rbRepublica.setSelected(true);
                            break;
                        case "Sozinho":
                            rbSozinho.setSelected(true);
                            break;
                        case "Outro":
                            rbOutro.setSelected(true);
                            break;
                        default:
                            bgMoradia.clearSelection();
                            break;
                    }
                }
                //Regime
                if (null == rs.getString("regime")) {
                    bgRegime.clearSelection();
                } else {
                    switch (rs.getString("regime")) {
                        case "Interno":
                            rbInterno.setSelected(true);
                            break;
                        case "Externo":
                            rbExterno.setSelected(true);
                            break;
                        case "Semi-Interno":
                            rbSemiInterno.setSelected(true);
                            break;
                        default:
                            bgRegime.clearSelection();
                            break;
                    }
                }
                //Plano de saude
                if (null == rs.getString("planoSaude")) {
                    bgPlanoSaude.clearSelection();
                } else {
                    switch (rs.getString("planoSaude")) {
                        case "Não":
                            rbPlanoSaudeNao.setSelected(true);
                            tfPlanoSaude.setEnabled(false);
                            lblQualPlanoSaude.setEnabled(false);
                            break;
                        default:
                            rbPlanoSaudeSim.setSelected(true);
                            tfPlanoSaude.setEnabled(true);
                            lblQualPlanoSaude.setEnabled(true);
                            tfPlanoSaude.setText(rs.getString("planoSaude"));
                            break;
                    }
                }

                //Cartão SUS                
                if (null == rs.getString("cartaoSus")) {
                    bgSus.clearSelection();
                } else {
                    switch (rs.getString("cartaoSus")) {
                        case "Não possui":
                            rbSusNao.setSelected(true);
                            tfNSus.setEnabled(false);
                            break;
                        default:
                            rbSusSim.setSelected(true);
                            tfNSus.setEnabled(true);
                            tfNSus.setText(rs.getString("cartaoSus"));
                            break;
                    }
                }

            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro: " + ex.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void imprimirHistoriaPregressa() {
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        String qry = "SELECT vacinasInfancia, vacinasAdolescencia, vacinaFaltando,"
                + " doencaInfanciaAdolescencia, catapora, caxumba, dengue,"
                + " hepatite, meningite, pneumonia, rubeola, sarampo,"
                + " cirurgiaRealizada, alergiaMedicamentosa, alergiaAlimentar"
                + " FROM pacientes WHERE idPaciente= ?";

        try {
            pstmt = conn.prepareStatement(qry);
            pstmt.setString(1, PesquisaPaciente.id);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                //Vacinas Infância
                if (null == rs.getString("vacinasInfancia")) {
                    bgVacinasInfancia.clearSelection();
                } else {
                    switch (rs.getString("vacinasInfancia")) {
                        case "Completo":
                            rbCartaoInfanciaCompleto.setSelected(true);
                            break;
                        case "Incompleto":
                            rbCartaoInfanciaIncompleto.setSelected(true);
                            break;
                        default:
                            bgVacinasInfancia.clearSelection();
                            break;
                    }
                }
                //Vacinas na Adolescência
                if (null == rs.getString("vacinasAdolescencia")) {
                    bgVacinasAdolescencia.clearSelection();
                } else {
                    switch (rs.getString("vacinasAdolescencia")) {
                        case "Completo":
                            rbCartaoAdolescenciaCompleto.setSelected(true);
                            break;
                        case "Incompleto":
                            rbCartaoAdolescenciaIncompleto.setSelected(true);
                            break;
                        default:
                            bgVacinasAdolescencia.clearSelection();
                            break;
                    }
                }

                tfVacinaFaltando.setText(rs.getString("vacinaFaltando"));
                tfDoencas.setText(rs.getString("doencaInfanciaAdolescencia"));

                //Já contrariu alguma dessas doenças            
                if ("Sim".equals(rs.getString("catapora"))) {
                    rbCatapora.setSelected(true);
                }
                if ("Sim".equals(rs.getString("caxumba"))) {
                    rbCaxumba.setSelected(true);
                }
                if ("Sim".equals(rs.getString("dengue"))) {
                    rbDengue.setSelected(true);
                }
                if ("Sim".equals(rs.getString("hepatite"))) {
                    rbHepatite.setSelected(true);
                }
                if ("Sim".equals(rs.getString("meningite"))) {
                    rbMeningite.setSelected(true);
                }
                if ("Sim".equals(rs.getString("pneumonia"))) {
                    rbPneumonia.setSelected(true);
                }
                if ("Sim".equals(rs.getString("rubeola"))) {
                    rbRubeola.setSelected(true);
                }
                if ("Sim".equals(rs.getString("sarampo"))) {
                    rbSarampo.setSelected(true);
                }

                //Cirurgias Realizadas
                if (null == rs.getString("cirurgiaRealizada")) {
                    bgCirurgiasRealizadas.clearSelection();
                } else {
                    switch (rs.getString("cirurgiaRealizada")) {
                        case "Não":
                            rbCirurgiasNao.setSelected(true);
                            tfCirurgiasRealizadas.setEnabled(false);
                            break;
                        default:
                            rbCirurgiasSim.setSelected(true);
                            tfCirurgiasRealizadas.setEnabled(true);
                            tfCirurgiasRealizadas.setText(rs.getString("CirurgiaRealizada"));
                            break;
                    }
                }

                //Alergias Medicamentosas
                if (null == rs.getString("alergiaMedicamentosa")) {
                    bgCirurgiasRealizadas.clearSelection();
                } else {
                    switch (rs.getString("alergiaMedicamentosa")) {
                        case "Não":
                            rbAlergiaMedicamentosaNao.setSelected(true);
                            tfAlergiaMedicamentosa.setEnabled(false);
                            break;
                        default:
                            rbAlergiaMedicamentosaSim.setSelected(true);
                            tfAlergiaMedicamentosa.setEnabled(true);
                            tfAlergiaMedicamentosa.setText(rs.getString("alergiaMedicamentosa"));
                            break;
                    }
                }

                //Alergia alimentar
                if (null == rs.getString("alergiaAlimentar")) {
                    bgCirurgiasRealizadas.clearSelection();
                } else {
                    switch (rs.getString("alergiaAlimentar")) {
                        case "Não":
                            rbAlergiaAlimentarNao.setSelected(true);
                            tfAlergiaAlimentar.setEnabled(false);
                            break;
                        default:
                            rbAlergiaAlimentarSim.setSelected(true);
                            tfAlergiaAlimentar.setEnabled(true);
                            tfAlergiaAlimentar.setText(rs.getString("alergiaAlimentar"));
                            break;
                    }
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro: " + ex.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void imprimirHistoricoFamiliar() {
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        String qry = "SELECT cancer, cardiopatias, diabetes,"
                + " hipertensaoArterial, oftalmologico, renal, mental,"
                + " doencaEspecifica FROM pacientes"
                + " WHERE idPaciente= ?";

        try {
            pstmt = conn.prepareStatement(qry);
            pstmt.setString(1, PesquisaPaciente.id);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                if ("Sim".equals(rs.getString("cancer"))) {
                    rbCancer.setSelected(true);
                }
                if ("Sim".equals(rs.getString("cardiopatias"))) {
                    rbCardiopatia.setSelected(true);
                }
                if ("Sim".equals(rs.getString("diabetes"))) {
                    rbDiabetes.setSelected(true);
                }
                if ("Sim".equals(rs.getString("hipertensaoArterial"))) {
                    rbHipertensao.setSelected(true);
                }
                if ("Sim".equals(rs.getString("oftalmologico"))) {
                    rbOftalmologico.setSelected(true);
                }
                if ("Sim".equals(rs.getString("renal"))) {
                    rbProblemaRenal.setSelected(true);
                }
                if ("Sim".equals(rs.getString("mental"))) {
                    rbMental.setSelected(true);
                }
                if ("Não".equals(rs.getString("doencaEspecifica")) || rs.getString("doencaEspecifica")==null) {
                    rbHistFamiliarOutro.setSelected(false);
                    tfEspecificar.setEnabled(false);
                }else{
                    rbHistFamiliarOutro.setSelected(true);
                    tfEspecificar.setEnabled(true);
                    tfEspecificar.setText(rs.getString("doencaEspecifica"));
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro: " + ex.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void imprimirDoencaAtual() {
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        String qry = "SELECT deficienciaAuditiva, deficienciaFisica,"
                + " deficienciaVisual, dificuldadeConcentracao, dificuldadeEscrita,"
                + " dificuldadeLeitura, superdotacao, transtornoDesenvolvimento,"
                + " nenhumaEspecifica, protese, asma, bronquite, cronicaDiabetes,"
                + " pressaoAlta, problemaCardiaco, problemaRenal, rinite,"
                + " doencaCronicaOutros, acompanhamentoProblema FROM pacientes"
                + " WHERE idPaciente= ?";

        try {
            pstmt = conn.prepareStatement(qry);
            pstmt.setString(1, PesquisaPaciente.id);
            rs = pstmt.executeQuery();

            while (rs.next()) {

                if ("Sim".equals(rs.getString("deficienciaAuditiva"))) {
                    rbAuditiva.setSelected(true);
                }
                if ("Sim".equals(rs.getString("deficienciaFisica"))) {
                    rbFisica.setSelected(true);
                }
                if ("Sim".equals(rs.getString("deficienciaVisual"))) {
                    rbVisual.setSelected(true);
                }
                if ("Sim".equals(rs.getString("dificuldadeConcentracao"))) {
                    rbConcentracao.setSelected(true);
                }
                if ("Sim".equals(rs.getString("dificuldadeEscrita"))) {
                    rbEscrita.setSelected(true);
                }
                if ("Sim".equals(rs.getString("dificuldadeLeitura"))) {
                    rbLeitura.setSelected(true);
                }
                if ("Sim".equals(rs.getString("superdotacao"))) {
                    rbSuperdotacao.setSelected(true);
                }
                if ("Sim".equals(rs.getString("transtornoDesenvolvimento"))) {
                    rbDesenvolvimento.setSelected(true);
                }
                if ("Sim".equals(rs.getString("nenhumaEspecifica"))) {
                    rbNenhumaNecessidade.setSelected(true);
                }

                //Protese
                if (null == rs.getString("protese")) {
                    bgProtese.clearSelection();
                } else {
                    switch (rs.getString("protese")) {
                        case "Sim":
                            rbProteseSim.setSelected(true);
                            break;
                        case "Não":
                            rbProteseNao.setSelected(true);
                            break;
                        default:
                            bgProtese.clearSelection();
                            break;
                    }
                }
                //Doença Crônica
                if ("Sim".equals(rs.getString("asma"))) {
                    rbAsma.setSelected(true);
                }
                if ("Sim".equals(rs.getString("bronquite"))) {
                    rbBronquite.setSelected(true);
                }
                if ("Sim".equals(rs.getString("cronicaDiabetes"))) {
                    rbCronicaDiabetes.setSelected(true);
                }
                if ("Sim".equals(rs.getString("pressaoAlta"))) {
                    rbPressaoAlta.setSelected(true);
                }
                if ("Sim".equals(rs.getString("problemaCardiaco"))) {
                    rbCardiaco.setSelected(true);
                }
                if ("Sim".equals(rs.getString("problemaRenal"))) {
                    rbRenal.setSelected(true);
                }
                if ("Sim".equals(rs.getString("rinite"))) {
                    rbRinite.setSelected(true);
                }
                if (!"Não".equals(rs.getString("doencaCronicaOutros"))) {
                    rbCronicaOutros.setSelected(true);
                    tfDoencaCronica.setEnabled(true);
                    tfDoencaCronica.setText(rs.getString("doencaCronicaOutros"));
                }

                if (null == rs.getString("acompanhamentoProblema")) {
                    bgProtese.clearSelection();
                } else {
                    switch (rs.getString("acompanhamentoProblema")) {
                        case "Sim":
                            rbAcompanhamentoSim.setSelected(true);
                            break;
                        case "Não":
                            rbAcompanhamentoNao.setSelected(true);
                            break;
                        default:
                            bgAcompanhamento.clearSelection();
                            break;
                    }
                }

            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro: " + ex.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void imprimirQuestionario1() {
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        String qry = "SELECT medicamentoContinuo, desmaios, epistaxe,"
                + " pressaoArterial, cefaleia, diarreia FROM pacientes"
                + " WHERE idPaciente= ?";

        try {
            pstmt = conn.prepareStatement(qry);
            pstmt.setString(1, PesquisaPaciente.id);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                tfMedicamentoContinuo.setText(rs.getString("medicamentoContinuo"));
                tfDesmaios.setText(rs.getString("desmaios"));
                tfEpistaxe.setText(rs.getString("epistaxe"));
                tfPressaoArterial.setText(rs.getString("pressaoArterial"));
                tfCefaleia.setText(rs.getString("cefaleia"));
                tfDiarreia.setText(rs.getString("diarreia"));
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro: " + ex.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void imprimirQuestionario2() {
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        String qry = "SELECT colica, psicologico, fonaudiologo, fisioterapia,"
                + " terapiaOcupacional, acompanhamentoEspecializadoOutro,"
                + " anotacaoRelevante, contatoEmergencia FROM pacientes"
                + " WHERE idPaciente= ?";

        try {
            pstmt = conn.prepareStatement(qry);
            pstmt.setString(1, PesquisaPaciente.id);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                tfColica.setText(rs.getString("colica"));

                //Acompanhamento Especializado
                if ("Sim".equals(rs.getString("psicologico"))) {
                    rbPsicologico.setSelected(true);
                }
                if ("Sim".equals(rs.getString("fonaudiologo"))) {
                    rbFonaudiologo.setSelected(true);
                }
                if ("Sim".equals(rs.getString("fisioterapia"))) {
                    rbFisioterapia.setSelected(true);
                }
                if ("Sim".equals(rs.getString("terapiaOcupacional"))) {
                    rbTerapiaOcupacional.setSelected(true);
                }
                if (!"Não".equals(rs.getString("acompanhamentoEspecializadoOutro"))) {
                    rbAcompanhamentoEspecializadoOutro.setSelected(true);
                    tfAcompanhamentoEspecializado.setEnabled(true);
                    tfAcompanhamentoEspecializado.setText(rs.getString("acompanhamentoEspecializadoOutro"));
                }

                tfAnotacoesRelevantes.setText(rs.getString("anotacaoRelevante"));
                tfContatoEmergencia.setText(rs.getString("contatoEmergencia"));
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro: " + ex.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void atualizarCartao() {
        PreparedStatement pstmt = null;
        String qry = "UPDATE CartaoVacina SET cpf= ?, nome= ?, dn= ?,"
                + " tipoSanguineo= ?, rua= ?, numero= ?, bairro= ?, municipio= ?,"
                + " uf= ?, telefone= ? WHERE idPaciente= ?";

        try {
            pstmt = conn.prepareStatement(qry);
            pstmt.setString(1, tfCpf.getText());
            pstmt.setString(2, tfNome.getText());
            pstmt.setString(3, tfDtNasc.getText());
            pstmt.setString(4, tfSangue.getText());
            pstmt.setString(5, tfRua.getText());
            pstmt.setString(6, tfNCasa.getText());
            pstmt.setString(7, tfBairro.getText());
            pstmt.setString(8, tfCidade.getText());
            pstmt.setString(9, tfUf.getText());
            pstmt.setString(10, tfTelefone.getText());
            pstmt.setString(11, tfIdPaciente.getText());

            pstmt.executeUpdate();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar cartão: " + ex.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void atualizarIdentificacao() {
        PreparedStatement pstmt = null;
        String qry = "UPDATE pacientes SET cpf= ?, nome= ?, dtNascimento= ?, matricula= ?, curso= ?, turma= ?,"
                + " rua= ?, numero= ?, bairro= ?, municipio= ?, uf= ?, telefone= ?,"
                + "nomeMae= ?, telefoneMae= ?, nomePai= ?, telefonePai= ?, nomeResponsavel= ?, telefoneResponsavel= ?, sexo= ?, peso= ?, altura= ?,"
                + "tipoSanguineo= ?, mora= ?, regime= ?, planoSaude= ?, cartaoSus= ? WHERE idPaciente= ?";

        try {
            pstmt = conn.prepareStatement(qry);
            pstmt.setString(1, tfCpf.getText());
            pstmt.setString(2, tfNome.getText());
            pstmt.setString(3, tfDtNasc.getText());
            pstmt.setString(4, tfMatricula.getText());
            pstmt.setString(5, cbCurso.getSelectedItem().toString());
            pstmt.setString(6, tfTurma.getText());
            pstmt.setString(7, tfRua.getText());
            pstmt.setString(8, tfNCasa.getText());
            pstmt.setString(9, tfBairro.getText());
            pstmt.setString(10, tfCidade.getText());
            pstmt.setString(11, tfUf.getText());
            pstmt.setString(12, tfTelefone.getText());
            pstmt.setString(13, tfMae.getText());
            pstmt.setString(14, tfTelMae.getText());
            pstmt.setString(15, tfPai.getText());
            pstmt.setString(16, tfTelPai.getText());
            pstmt.setString(17, tfResponsavel.getText());
            pstmt.setString(18, tfTelResponsavel.getText());
            //null
            if (rbMasculino.isSelected()) {
                pstmt.setString(19, "M");
            } else if (rbFeminino.isSelected()) {
                pstmt.setString(19, "F");
            } else {
                pstmt.setString(19, null);
            }
            pstmt.setString(20, tfPeso.getText());
            pstmt.setString(21, tfAltura.getText());
            pstmt.setString(22, tfSangue.getText());
            if (rbPais.isSelected()) {
                pstmt.setString(23, "Pais");//Moradia
            } else if (rbEscola.isSelected()) {
                pstmt.setString(23, "Escola");
            } else if (rbParentes.isSelected()) {
                pstmt.setString(23, "Parentes");
            } else if (rbRepublica.isSelected()) {
                pstmt.setString(23, "República");
            } else if (rbSozinho.isSelected()) {
                pstmt.setString(23, "Sozinho");
            } else if (rbOutro.isSelected()) {
                pstmt.setString(23, "Outro");
            } else {
                pstmt.setString(23, null);
            }
            //Regime
            if (rbInterno.isSelected()) {
                pstmt.setString(24, "Interno");
            } else if (rbExterno.isSelected()) {
                pstmt.setString(24, "Externo");
            } else if (rbSemiInterno.isSelected()) {
                pstmt.setString(24, "Semi-Interno");
            } else {
                pstmt.setString(24, null);
            }
            //Plano de saude
            if (rbPlanoSaudeSim.isSelected()) {
                pstmt.setString(25, tfPlanoSaude.getText());
            } else if (rbPlanoSaudeNao.isSelected()) {
                pstmt.setString(25, "Não");
            } else {
                pstmt.setString(25, null);
            }
            //Cartão SUS
            if (rbSusSim.isSelected()) {
                pstmt.setString(26, tfNSus.getText());
            } else if (rbSusNao.isSelected()) {
                pstmt.setString(26, "Não possui");
            } else {
                pstmt.setString(26, null);
            }

            //id
            pstmt.setString(27, tfIdPaciente.getText());

            pstmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Atualizado com sucesso!");

        } catch (SQLException ex) {
            //JOptionPane.showMessageDialog(null, "Erro ao salvar: " + ex.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
            JOptionPane.showMessageDialog(null, "CPF existente localizado", "ERRO", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void atualizarHistoriaPregressa() {

        PreparedStatement pstmt = null;
        String qry = "UPDATE pacientes SET vacinasInfancia= ?, vacinasAdolescencia= ?,"
                + " vacinaFaltando= ?, doencaInfanciaAdolescencia= ?, catapora= ?, caxumba= ?, dengue= ?,"
                + " hepatite= ?, meningite= ?, pneumonia= ?, rubeola= ?, sarampo= ?,"
                + " cirurgiaRealizada= ?, alergiaMedicamentosa= ?, alergiaAlimentar= ?"
                + "WHERE idPaciente= ?";

        try {
            pstmt = conn.prepareStatement(qry);
            //Vacinas na infância
            if (rbCartaoInfanciaCompleto.isSelected()) {
                pstmt.setString(1, "Completo");
            } else if (rbCartaoInfanciaIncompleto.isSelected()) {
                pstmt.setString(1, "Incompleto");
            } else {
                pstmt.setString(1, null);
            }
            //Vacinas na Adolescência
            if (rbCartaoAdolescenciaCompleto.isSelected()) {
                pstmt.setString(2, "Completo");
            } else if (rbCartaoAdolescenciaIncompleto.isSelected()) {
                pstmt.setString(2, "Incompleto");
            } else {
                pstmt.setString(2, null);
            }

            pstmt.setString(3, tfVacinaFaltando.getText());
            pstmt.setString(4, tfDoencas.getText());
            //Já contrariu alguma dessas doenças
            if (rbCatapora.isSelected()) {
                pstmt.setString(5, "Sim");
            } else {
                pstmt.setString(5, null);
            }
            if (rbCaxumba.isSelected()) {
                pstmt.setString(6, "Sim");
            } else {
                pstmt.setString(6, null);
            }
            if (rbDengue.isSelected()) {
                pstmt.setString(7, "Sim");
            } else {
                pstmt.setString(7, null);
            }
            if (rbHepatite.isSelected()) {
                pstmt.setString(8, "Sim");
            } else {
                pstmt.setString(8, null);
            }
            if (rbMeningite.isSelected()) {
                pstmt.setString(9, "Sim");
            } else {
                pstmt.setString(9, null);
            }
            if (rbPneumonia.isSelected()) {
                pstmt.setString(10, "Sim");
            } else {
                pstmt.setString(10, null);
            }
            if (rbRubeola.isSelected()) {
                pstmt.setString(11, "Sim");
            } else {
                pstmt.setString(11, null);
            }
            if (rbSarampo.isSelected()) {
                pstmt.setString(12, "Sim");
            } else {
                pstmt.setString(12, null);
            }
            //Cirurgias Realizadas
            if (rbCirurgiasSim.isSelected()) {
                pstmt.setString(13, tfCirurgiasRealizadas.getText());
            } else if (rbCirurgiasNao.isSelected()) {
                pstmt.setString(13, "Não");
            } else {
                pstmt.setString(13, null);
            }
            //Alergias Medicamentosas
            if (rbAlergiaMedicamentosaSim.isSelected()) {
                pstmt.setString(14, tfAlergiaMedicamentosa.getText());
            } else if (rbAlergiaMedicamentosaNao.isSelected()) {
                pstmt.setString(14, "Não");
            } else {
                pstmt.setString(14, null);
            }
            //Alergia alimentar acho que tem um erro aqui
            if (rbAlergiaAlimentarSim.isSelected()) {
                pstmt.setString(15, tfAlergiaAlimentar.getText());
            } else if (rbAlergiaAlimentarNao.isSelected()) {
                pstmt.setString(15, "Não");
            } else {
                pstmt.setString(15, "");
            }

            pstmt.setString(16, tfIdPaciente.getText());
            pstmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Salvo com sucesso!");

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar: " + ex.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void atualizarHistoriaFamiliar() {

        PreparedStatement pstmt = null;
        String qry = "UPDATE pacientes SET cancer= ?, cardiopatias= ?, diabetes= ?,"
                + " hipertensaoArterial= ?, oftalmologico= ?, renal= ?, mental= ?,"
                + " doencaEspecifica= ?"
                + " WHERE idPaciente= ?";

        try {
            pstmt = conn.prepareStatement(qry);
            if (rbCancer.isSelected()) {
                pstmt.setString(1, "Sim");
            } else {
                pstmt.setString(1, "Não");
            }
            if (rbCardiopatia.isSelected()) {
                pstmt.setString(2, "Sim");
            } else {
                pstmt.setString(2, "Não");
            }
            if (rbDiabetes.isSelected()) {
                pstmt.setString(3, "Sim");
            } else {
                pstmt.setString(3, "Não");
            }
            if (rbHipertensao.isSelected()) {
                pstmt.setString(4, "Sim");
            } else {
                pstmt.setString(4, "Não");
            }
            if (rbOftalmologico.isSelected()) {
                pstmt.setString(5, "Sim");
            } else {
                pstmt.setString(5, "Não");
            }
            if (rbProblemaRenal.isSelected()) {
                pstmt.setString(6, "Sim");
            } else {
                pstmt.setString(6, "Não");
            }
            if (rbMental.isSelected()) {
                pstmt.setString(7, "Sim");
            } else {
                pstmt.setString(7, "Não");
            }
            if (rbHistFamiliarOutro.isSelected()) {
                pstmt.setString(8, tfEspecificar.getText());
            } else {
                pstmt.setString(8, "Não");
            }
            pstmt.setString(9, tfIdPaciente.getText());
            pstmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Salvo com sucesso!");

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar: " + ex.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void atualizarHistoriaDoencaAtual() {

        PreparedStatement pstmt = null;
        String qry = "UPDATE pacientes SET deficienciaAuditiva= ?, deficienciaFisica= ?,"
                + " deficienciaVisual= ?, dificuldadeConcentracao= ?, dificuldadeEscrita= ?,"
                + " dificuldadeLeitura= ?, superdotacao= ?, transtornoDesenvolvimento= ?,"
                + " nenhumaEspecifica= ?, protese= ?, asma= ?, bronquite= ?, cronicaDiabetes= ?,"
                + " pressaoAlta= ?, problemaCardiaco= ?, problemaRenal= ?, rinite= ?,"
                + " doencaCronicaOutros= ?, acompanhamentoProblema= ?"
                + " WHERE idPaciente= ?";

        try {
            pstmt = conn.prepareStatement(qry);
            //Necessidade específica
            if (rbAuditiva.isSelected()) {
                pstmt.setString(1, "Sim");
            } else {
                pstmt.setString(1, "Não");
            }
            if (rbFisica.isSelected()) {
                pstmt.setString(2, "Sim");
            } else {
                pstmt.setString(2, "Não");
            }
            if (rbVisual.isSelected()) {
                pstmt.setString(3, "Sim");
            } else {
                pstmt.setString(3, "Não");
            }
            if (rbConcentracao.isSelected()) {
                pstmt.setString(4, "Sim");
            } else {
                pstmt.setString(4, "Não");
            }
            if (rbEscrita.isSelected()) {
                pstmt.setString(5, "Sim");
            } else {
                pstmt.setString(5, "Não");
            }
            if (rbLeitura.isSelected()) {
                pstmt.setString(6, "Sim");
            } else {
                pstmt.setString(6, "Não");
            }
            if (rbSuperdotacao.isSelected()) {
                pstmt.setString(7, "Sim");
            } else {
                pstmt.setString(7, "Não");
            }
            if (rbDesenvolvimento.isSelected()) {
                pstmt.setString(8, "Sim");
            } else {
                pstmt.setString(8, "Não");
            }
            if (rbNenhumaNecessidade.isSelected()) {
                pstmt.setString(9, "Sim");
            } else {
                pstmt.setString(9, "Não");
            }
            //Protese
            if (rbProteseSim.isSelected()) {
                pstmt.setString(10, "Sim");
            } else if (rbProteseNao.isSelected()) {
                pstmt.setString(10, "Não");
            } else {
                pstmt.setString(10, null);
            }
            //Doença Crônica
            if (rbAsma.isSelected()) {
                pstmt.setString(11, "Sim");
            } else {
                pstmt.setString(11, "Não");
            }
            if (rbBronquite.isSelected()) {
                pstmt.setString(12, "Sim");
            } else {
                pstmt.setString(12, "Não");
            }
            if (rbCronicaDiabetes.isSelected()) {
                pstmt.setString(13, "Sim");
            } else {
                pstmt.setString(13, "Não");
            }
            if (rbPressaoAlta.isSelected()) {
                pstmt.setString(14, "Sim");
            } else {
                pstmt.setString(14, "Não");
            }
            if (rbCardiaco.isSelected()) {
                pstmt.setString(15, "Sim");
            } else {
                pstmt.setString(15, "Não");
            }
            if (rbRenal.isSelected()) {
                pstmt.setString(16, "Sim");
            } else {
                pstmt.setString(16, "Não");
            }
            if (rbRinite.isSelected()) {
                pstmt.setString(17, "Sim");
            } else {
                pstmt.setString(17, "Não");
            }
            if (rbCronicaOutros.isSelected()) {
                pstmt.setString(18, tfDoencaCronica.getText());
            } else {
                pstmt.setString(18, "Não");
            }

            if (rbAcompanhamentoSim.isSelected()) {
                pstmt.setString(19, "Sim");
            } else if (rbAcompanhamentoNao.isSelected()) {
                pstmt.setString(19, "Não");
            } else {
                pstmt.setString(19, null);
            }

            pstmt.setString(20, tfIdPaciente.getText());
            pstmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Salvo com sucesso!");

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar: " + ex.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void atualizarQuestionario1() {

        PreparedStatement pstmt = null;
        String qry = "UPDATE pacientes SET medicamentoContinuo=?,"
                + " desmaios= ?, epistaxe= ?, pressaoArterial= ?, cefaleia= ?, diarreia= ?"
                + "WHERE idPaciente= ?";

        try {
            pstmt = conn.prepareStatement(qry);
            pstmt.setString(1, tfMedicamentoContinuo.getText());
            pstmt.setString(2, tfDesmaios.getText());
            pstmt.setString(3, tfEpistaxe.getText());
            pstmt.setString(4, tfPressaoArterial.getText());
            pstmt.setString(5, tfCefaleia.getText());
            pstmt.setString(6, tfDiarreia.getText());
            pstmt.setString(7, tfIdPaciente.getText());
            pstmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Salvo com sucesso!");

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar: " + ex.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void atualizarQuestionario2() {

        PreparedStatement pstmt = null;
        String qry = "UPDATE pacientes SET colica= ?, psicologico= ?, fonaudiologo= ?, fisioterapia= ?,"
                + " terapiaOcupacional= ?, acompanhamentoEspecializadoOutro= ?,"
                + " anotacaoRelevante= ?, contatoEmergencia= ?"
                + " WHERE idPaciente= ?";

        try {
            pstmt = conn.prepareStatement(qry);
            pstmt.setString(1, tfColica.getText());
            //Acompanhamento especializado
            if (rbPsicologico.isSelected()) {
                pstmt.setString(2, "Sim");
            } else {
                pstmt.setString(2, "Não");
            }
            if (rbFonaudiologo.isSelected()) {
                pstmt.setString(3, "Sim");
            } else {
                pstmt.setString(3, "Não");
            }
            if (rbFisioterapia.isSelected()) {
                pstmt.setString(4, "Sim");
            } else {
                pstmt.setString(4, "Não");
            }
            if (rbTerapiaOcupacional.isSelected()) {
                pstmt.setString(5, "Sim");
            } else {
                pstmt.setString(5, "Não");
            }
            if (rbAcompanhamentoEspecializadoOutro.isSelected()) {
                pstmt.setString(6, "Sim");
            } else {
                pstmt.setString(6, "Não");
            }
            pstmt.setString(7, tfAnotacoesRelevantes.getText());
            pstmt.setString(8, tfContatoEmergencia.getText());
            pstmt.setString(9, tfIdPaciente.getText());
            pstmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Salvo com sucesso!");

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar: " + ex.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bgMoradia = new javax.swing.ButtonGroup();
        bgSexo = new javax.swing.ButtonGroup();
        bgRegime = new javax.swing.ButtonGroup();
        bgVacinasInfancia = new javax.swing.ButtonGroup();
        bgVacinasAdolescencia = new javax.swing.ButtonGroup();
        bgCirurgiasRealizadas = new javax.swing.ButtonGroup();
        bgAlergiaMedicamentosa = new javax.swing.ButtonGroup();
        bgAlergiaAlimentar = new javax.swing.ButtonGroup();
        bgProtese = new javax.swing.ButtonGroup();
        bgAcompanhamento = new javax.swing.ButtonGroup();
        bgPlanoSaude = new javax.swing.ButtonGroup();
        bgSus = new javax.swing.ButtonGroup();
        tpCadastroPaciente = new javax.swing.JTabbedPane();
        pnlIdentificacao = new javax.swing.JPanel();
        pnlIdentInterno = new javax.swing.JPanel();
        lblResponsavel = new javax.swing.JLabel();
        tfPai = new javax.swing.JTextField();
        tfResponsavel = new javax.swing.JTextField();
        lblTelPai = new javax.swing.JLabel();
        lblTelResponsavel = new javax.swing.JLabel();
        tfTelResponsavel = new javax.swing.JTextField();
        tfTelPai = new javax.swing.JTextField();
        lblSexo = new javax.swing.JLabel();
        rbFeminino = new javax.swing.JRadioButton();
        rbMasculino = new javax.swing.JRadioButton();
        lblPeso = new javax.swing.JLabel();
        tfPeso = new javax.swing.JTextField();
        lblAltura = new javax.swing.JLabel();
        tfAltura = new javax.swing.JTextField();
        lblSangue = new javax.swing.JLabel();
        tfSangue = new javax.swing.JTextField();
        lblMoradia = new javax.swing.JLabel();
        rbPais = new javax.swing.JRadioButton();
        rbEscola = new javax.swing.JRadioButton();
        rbParentes = new javax.swing.JRadioButton();
        rbRepublica = new javax.swing.JRadioButton();
        rbSozinho = new javax.swing.JRadioButton();
        rbOutro = new javax.swing.JRadioButton();
        lblRegime = new javax.swing.JLabel();
        rbExterno = new javax.swing.JRadioButton();
        rbInterno = new javax.swing.JRadioButton();
        rbSemiInterno = new javax.swing.JRadioButton();
        lblPlanoSaude = new javax.swing.JLabel();
        rbPlanoSaudeNao = new javax.swing.JRadioButton();
        rbPlanoSaudeSim = new javax.swing.JRadioButton();
        lblQualPlanoSaude = new javax.swing.JLabel();
        tfPlanoSaude = new javax.swing.JTextField();
        lblSus = new javax.swing.JLabel();
        rbSusNao = new javax.swing.JRadioButton();
        lblCurso = new javax.swing.JLabel();
        rbSusSim = new javax.swing.JRadioButton();
        lblTurma = new javax.swing.JLabel();
        btnCancelar1 = new javax.swing.JButton();
        tfTurma = new javax.swing.JTextField();
        btnContinuar1 = new javax.swing.JButton();
        cbCurso = new javax.swing.JComboBox<>();
        lblMae = new javax.swing.JLabel();
        tfMae = new javax.swing.JTextField();
        lblTelMae = new javax.swing.JLabel();
        tfTelMae = new javax.swing.JTextField();
        lblPai = new javax.swing.JLabel();
        lblNome = new javax.swing.JLabel();
        tfNome = new javax.swing.JTextField();
        lblDtNasc = new javax.swing.JLabel();
        tfDtNasc = new javax.swing.JFormattedTextField();
        tfMatricula = new javax.swing.JTextField();
        lblMatricula = new javax.swing.JLabel();
        tfCpf = new javax.swing.JFormattedTextField();
        lblCpf = new javax.swing.JLabel();
        lblNSus = new javax.swing.JLabel();
        tfNSus = new javax.swing.JTextField();
        lblIdPaciente = new javax.swing.JLabel();
        tfIdPaciente = new javax.swing.JTextField();
        tfTelefone = new javax.swing.JTextField();
        lblTelefone = new javax.swing.JLabel();
        tfUf = new javax.swing.JTextField();
        lblUf = new javax.swing.JLabel();
        tfCidade = new javax.swing.JTextField();
        lblCidade = new javax.swing.JLabel();
        tfBairro = new javax.swing.JTextField();
        lblBairro = new javax.swing.JLabel();
        tfNCasa = new javax.swing.JTextField();
        lblNumero = new javax.swing.JLabel();
        tfRua = new javax.swing.JTextField();
        lblRua = new javax.swing.JLabel();
        lblFundo1 = new javax.swing.JLabel();
        pnlPregressa = new javax.swing.JPanel();
        pnlPregressaInterno = new javax.swing.JPanel();
        rbCartaoInfanciaCompleto = new javax.swing.JRadioButton();
        lblAlergiaMedicamentosa = new javax.swing.JLabel();
        rbCartaoInfanciaIncompleto = new javax.swing.JRadioButton();
        rbAlergiaMedicamentosaNao = new javax.swing.JRadioButton();
        lblVacinasAdolescencia = new javax.swing.JLabel();
        rbCartaoAdolescenciaCompleto = new javax.swing.JRadioButton();
        rbAlergiaMedicamentosaSim = new javax.swing.JRadioButton();
        tfAlergiaMedicamentosa = new javax.swing.JTextField();
        rbCartaoAdolescenciaIncompleto = new javax.swing.JRadioButton();
        lblAlergiaAlimentar = new javax.swing.JLabel();
        lblVacinaFaltando = new javax.swing.JLabel();
        rbAlergiaAlimentarNao = new javax.swing.JRadioButton();
        tfVacinaFaltando = new javax.swing.JTextField();
        rbAlergiaAlimentarSim = new javax.swing.JRadioButton();
        lblDoencas = new javax.swing.JLabel();
        tfAlergiaAlimentar = new javax.swing.JTextField();
        tfDoencas = new javax.swing.JTextField();
        btnCancelar2 = new javax.swing.JButton();
        lblDoencasContraidas = new javax.swing.JLabel();
        btnContinuar2 = new javax.swing.JButton();
        rbCaxumba = new javax.swing.JRadioButton();
        rbHepatite = new javax.swing.JRadioButton();
        rbMeningite = new javax.swing.JRadioButton();
        rbSarampo = new javax.swing.JRadioButton();
        rbRubeola = new javax.swing.JRadioButton();
        rbDengue = new javax.swing.JRadioButton();
        rbCatapora = new javax.swing.JRadioButton();
        rbPneumonia = new javax.swing.JRadioButton();
        lblCirurgias = new javax.swing.JLabel();
        rbCirurgiasNao = new javax.swing.JRadioButton();
        rbCirurgiasSim = new javax.swing.JRadioButton();
        lblVacinasInfancia = new javax.swing.JLabel();
        tfCirurgiasRealizadas = new javax.swing.JTextField();
        lblFundo2 = new javax.swing.JLabel();
        pnlFamiliar = new javax.swing.JPanel();
        pnlFamiliarInterno = new javax.swing.JPanel();
        tfEspecificar = new javax.swing.JTextField();
        btnCancelar3 = new javax.swing.JButton();
        rbHipertensao = new javax.swing.JRadioButton();
        btnContinuar3 = new javax.swing.JButton();
        rbOftalmologico = new javax.swing.JRadioButton();
        rbDiabetes = new javax.swing.JRadioButton();
        rbMental = new javax.swing.JRadioButton();
        rbCancer = new javax.swing.JRadioButton();
        rbCardiopatia = new javax.swing.JRadioButton();
        rbProblemaRenal = new javax.swing.JRadioButton();
        rbHistFamiliarOutro = new javax.swing.JRadioButton();
        lblEspecificar = new javax.swing.JLabel();
        lblFundo3 = new javax.swing.JLabel();
        pnlDoenca = new javax.swing.JPanel();
        pnlDoencaAtualInterno = new javax.swing.JPanel();
        rbProteseSim = new javax.swing.JRadioButton();
        lblProtese = new javax.swing.JLabel();
        rbNenhumaNecessidade = new javax.swing.JRadioButton();
        rbAuditiva = new javax.swing.JRadioButton();
        rbConcentracao = new javax.swing.JRadioButton();
        rbSuperdotacao = new javax.swing.JRadioButton();
        btnContinuar4 = new javax.swing.JButton();
        rbDesenvolvimento = new javax.swing.JRadioButton();
        btnCancelar4 = new javax.swing.JButton();
        rbVisual = new javax.swing.JRadioButton();
        lblAcompanhamento = new javax.swing.JLabel();
        rbLeitura = new javax.swing.JRadioButton();
        rbAcompanhamentoSim = new javax.swing.JRadioButton();
        rbFisica = new javax.swing.JRadioButton();
        rbAcompanhamentoNao = new javax.swing.JRadioButton();
        tfDoencaCronica = new javax.swing.JTextField();
        rbEscrita = new javax.swing.JRadioButton();
        lblNecessidadeEspecifica = new javax.swing.JLabel();
        rbCronicaOutros = new javax.swing.JRadioButton();
        rbRinite = new javax.swing.JRadioButton();
        rbRenal = new javax.swing.JRadioButton();
        rbAsma = new javax.swing.JRadioButton();
        rbBronquite = new javax.swing.JRadioButton();
        rbCronicaDiabetes = new javax.swing.JRadioButton();
        rbPressaoAlta = new javax.swing.JRadioButton();
        rbCardiaco = new javax.swing.JRadioButton();
        lblDoencaCronica = new javax.swing.JLabel();
        rbProteseNao = new javax.swing.JRadioButton();
        lblFundo4 = new javax.swing.JLabel();
        pnlQuestionario1 = new javax.swing.JPanel();
        pnlQuestionario1Interno = new javax.swing.JPanel();
        btnContinuar5 = new javax.swing.JButton();
        lblEpistaxe = new javax.swing.JLabel();
        lblMedicamentoContinuo = new javax.swing.JLabel();
        lblPressaoArterial = new javax.swing.JLabel();
        tfCefaleia = new javax.swing.JTextField();
        tfDesmaios = new javax.swing.JTextField();
        tfDiarreia = new javax.swing.JTextField();
        tfEpistaxe = new javax.swing.JTextField();
        tfMedicamentoContinuo = new javax.swing.JTextField();
        lblCefaleia = new javax.swing.JLabel();
        tfPressaoArterial = new javax.swing.JTextField();
        lblDesmaios = new javax.swing.JLabel();
        btnCancelar5 = new javax.swing.JButton();
        lblDiarreia = new javax.swing.JLabel();
        lblFundo5 = new javax.swing.JLabel();
        pnlQuestionario2 = new javax.swing.JPanel();
        pnlQuestionario2Interno = new javax.swing.JPanel();
        lblAnotacoesRelevantes = new javax.swing.JLabel();
        lblColica = new javax.swing.JLabel();
        lblContatoEmergencia = new javax.swing.JLabel();
        tfAcompanhamentoEspecializado = new javax.swing.JTextField();
        tfAnotacoesRelevantes = new javax.swing.JTextField();
        rbAcompanhamentoEspecializadoOutro = new javax.swing.JRadioButton();
        tfColica = new javax.swing.JTextField();
        rbFisioterapia = new javax.swing.JRadioButton();
        tfContatoEmergencia = new javax.swing.JTextField();
        rbFonaudiologo = new javax.swing.JRadioButton();
        btnCancelar6 = new javax.swing.JButton();
        rbPsicologico = new javax.swing.JRadioButton();
        btnFinalizar6 = new javax.swing.JButton();
        rbTerapiaOcupacional = new javax.swing.JRadioButton();
        lblAcompanhamentoEspecializado = new javax.swing.JLabel();
        lblFundo6 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Alteração de Pacientes");
        setBackground(new java.awt.Color(255, 255, 255));
        setIconImage(new javax.swing.ImageIcon(getClass().getResource("/imagens/Quiron.png")).getImage());
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        tpCadastroPaciente.setBackground(new java.awt.Color(255, 255, 255));
        tpCadastroPaciente.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N

        pnlIdentificacao.setBackground(new java.awt.Color(255, 255, 255));

        pnlIdentInterno.setLayout(null);

        lblResponsavel.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblResponsavel.setForeground(new java.awt.Color(255, 255, 255));
        lblResponsavel.setText("RESPONSÁVEL EM SALINAS");
        pnlIdentInterno.add(lblResponsavel);
        lblResponsavel.setBounds(20, 300, 187, 17);

        tfPai.setBackground(new java.awt.Color(153, 153, 153));
        tfPai.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        tfPai.setForeground(new java.awt.Color(255, 255, 255));
        pnlIdentInterno.add(tfPai);
        tfPai.setBounds(130, 260, 360, 23);

        tfResponsavel.setBackground(new java.awt.Color(153, 153, 153));
        tfResponsavel.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        tfResponsavel.setForeground(new java.awt.Color(255, 255, 255));
        pnlIdentInterno.add(tfResponsavel);
        tfResponsavel.setBounds(240, 300, 250, 23);

        lblTelPai.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblTelPai.setForeground(new java.awt.Color(255, 255, 255));
        lblTelPai.setText("TELEFONE");
        pnlIdentInterno.add(lblTelPai);
        lblTelPai.setBounds(510, 260, 69, 17);

        lblTelResponsavel.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblTelResponsavel.setForeground(new java.awt.Color(255, 255, 255));
        lblTelResponsavel.setText("TELEFONE");
        pnlIdentInterno.add(lblTelResponsavel);
        lblTelResponsavel.setBounds(510, 300, 69, 17);

        tfTelResponsavel.setBackground(new java.awt.Color(153, 153, 153));
        tfTelResponsavel.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        tfTelResponsavel.setForeground(new java.awt.Color(255, 255, 255));
        pnlIdentInterno.add(tfTelResponsavel);
        tfTelResponsavel.setBounds(600, 300, 100, 23);

        tfTelPai.setBackground(new java.awt.Color(153, 153, 153));
        tfTelPai.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        tfTelPai.setForeground(new java.awt.Color(255, 255, 255));
        pnlIdentInterno.add(tfTelPai);
        tfTelPai.setBounds(600, 260, 100, 23);

        lblSexo.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblSexo.setForeground(new java.awt.Color(255, 255, 255));
        lblSexo.setText("SEXO");
        pnlIdentInterno.add(lblSexo);
        lblSexo.setBounds(20, 340, 37, 17);

        rbFeminino.setBackground(new java.awt.Color(255, 255, 255));
        bgSexo.add(rbFeminino);
        rbFeminino.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        rbFeminino.setForeground(new java.awt.Color(255, 255, 255));
        rbFeminino.setText("F");
        rbFeminino.setContentAreaFilled(false);
        rbFeminino.setFocusPainted(false);
        pnlIdentInterno.add(rbFeminino);
        rbFeminino.setBounds(70, 340, 33, 23);

        rbMasculino.setBackground(new java.awt.Color(255, 255, 255));
        bgSexo.add(rbMasculino);
        rbMasculino.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        rbMasculino.setForeground(new java.awt.Color(255, 255, 255));
        rbMasculino.setText("M");
        rbMasculino.setContentAreaFilled(false);
        rbMasculino.setFocusPainted(false);
        pnlIdentInterno.add(rbMasculino);
        rbMasculino.setBounds(110, 340, 37, 25);

        lblPeso.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblPeso.setForeground(new java.awt.Color(255, 255, 255));
        lblPeso.setText("PESO");
        pnlIdentInterno.add(lblPeso);
        lblPeso.setBounds(180, 340, 35, 17);

        tfPeso.setBackground(new java.awt.Color(153, 153, 153));
        tfPeso.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        tfPeso.setForeground(new java.awt.Color(255, 255, 255));
        pnlIdentInterno.add(tfPeso);
        tfPeso.setBounds(230, 340, 50, 23);

        lblAltura.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblAltura.setForeground(new java.awt.Color(255, 255, 255));
        lblAltura.setText("ALTURA");
        pnlIdentInterno.add(lblAltura);
        lblAltura.setBounds(320, 340, 56, 17);

        tfAltura.setBackground(new java.awt.Color(153, 153, 153));
        tfAltura.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        tfAltura.setForeground(new java.awt.Color(255, 255, 255));
        pnlIdentInterno.add(tfAltura);
        tfAltura.setBounds(390, 340, 50, 23);

        lblSangue.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblSangue.setForeground(new java.awt.Color(255, 255, 255));
        lblSangue.setText("TIPO SANGUÍNEO");
        pnlIdentInterno.add(lblSangue);
        lblSangue.setBounds(480, 340, 117, 17);

        tfSangue.setBackground(new java.awt.Color(153, 153, 153));
        tfSangue.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        tfSangue.setForeground(new java.awt.Color(255, 255, 255));
        pnlIdentInterno.add(tfSangue);
        tfSangue.setBounds(600, 340, 100, 23);

        lblMoradia.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblMoradia.setForeground(new java.awt.Color(255, 255, 255));
        lblMoradia.setText("MORADIA");
        pnlIdentInterno.add(lblMoradia);
        lblMoradia.setBounds(20, 380, 69, 17);

        rbPais.setBackground(new java.awt.Color(255, 255, 255));
        bgMoradia.add(rbPais);
        rbPais.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        rbPais.setForeground(new java.awt.Color(255, 255, 255));
        rbPais.setText("COM OS PAIS");
        rbPais.setContentAreaFilled(false);
        rbPais.setFocusPainted(false);
        pnlIdentInterno.add(rbPais);
        rbPais.setBounds(110, 380, 117, 25);

        rbEscola.setBackground(new java.awt.Color(255, 255, 255));
        bgMoradia.add(rbEscola);
        rbEscola.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        rbEscola.setForeground(new java.awt.Color(255, 255, 255));
        rbEscola.setText("ESCOLA");
        rbEscola.setContentAreaFilled(false);
        rbEscola.setFocusPainted(false);
        pnlIdentInterno.add(rbEscola);
        rbEscola.setBounds(240, 380, 81, 25);

        rbParentes.setBackground(new java.awt.Color(255, 255, 255));
        bgMoradia.add(rbParentes);
        rbParentes.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        rbParentes.setForeground(new java.awt.Color(255, 255, 255));
        rbParentes.setText("PARENTES");
        rbParentes.setContentAreaFilled(false);
        rbParentes.setFocusPainted(false);
        rbParentes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbParentesActionPerformed(evt);
            }
        });
        pnlIdentInterno.add(rbParentes);
        rbParentes.setBounds(330, 380, 94, 25);

        rbRepublica.setBackground(new java.awt.Color(255, 255, 255));
        bgMoradia.add(rbRepublica);
        rbRepublica.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        rbRepublica.setForeground(new java.awt.Color(255, 255, 255));
        rbRepublica.setText("REPÚBLICA");
        rbRepublica.setContentAreaFilled(false);
        rbRepublica.setFocusPainted(false);
        pnlIdentInterno.add(rbRepublica);
        rbRepublica.setBounds(440, 380, 104, 25);

        rbSozinho.setBackground(new java.awt.Color(255, 255, 255));
        bgMoradia.add(rbSozinho);
        rbSozinho.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        rbSozinho.setForeground(new java.awt.Color(255, 255, 255));
        rbSozinho.setText("SOZINHO");
        rbSozinho.setContentAreaFilled(false);
        rbSozinho.setFocusPainted(false);
        pnlIdentInterno.add(rbSozinho);
        rbSozinho.setBounds(560, 380, 88, 25);

        rbOutro.setBackground(new java.awt.Color(255, 255, 255));
        bgMoradia.add(rbOutro);
        rbOutro.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        rbOutro.setForeground(new java.awt.Color(255, 255, 255));
        rbOutro.setText("OUTRO");
        rbOutro.setContentAreaFilled(false);
        rbOutro.setFocusPainted(false);
        pnlIdentInterno.add(rbOutro);
        rbOutro.setBounds(660, 380, 74, 25);

        lblRegime.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblRegime.setForeground(new java.awt.Color(255, 255, 255));
        lblRegime.setText("REGIME");
        pnlIdentInterno.add(lblRegime);
        lblRegime.setBounds(20, 420, 54, 17);

        rbExterno.setBackground(new java.awt.Color(255, 255, 255));
        bgRegime.add(rbExterno);
        rbExterno.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        rbExterno.setForeground(new java.awt.Color(255, 255, 255));
        rbExterno.setText("EXTERNO");
        rbExterno.setContentAreaFilled(false);
        rbExterno.setFocusPainted(false);
        pnlIdentInterno.add(rbExterno);
        rbExterno.setBounds(110, 420, 89, 25);

        rbInterno.setBackground(new java.awt.Color(255, 255, 255));
        bgRegime.add(rbInterno);
        rbInterno.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        rbInterno.setForeground(new java.awt.Color(255, 255, 255));
        rbInterno.setText("INTERNO");
        rbInterno.setContentAreaFilled(false);
        rbInterno.setFocusPainted(false);
        pnlIdentInterno.add(rbInterno);
        rbInterno.setBounds(210, 420, 86, 25);

        rbSemiInterno.setBackground(new java.awt.Color(255, 255, 255));
        bgRegime.add(rbSemiInterno);
        rbSemiInterno.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        rbSemiInterno.setForeground(new java.awt.Color(255, 255, 255));
        rbSemiInterno.setText("SEMI-INTERNO");
        rbSemiInterno.setContentAreaFilled(false);
        rbSemiInterno.setFocusPainted(false);
        pnlIdentInterno.add(rbSemiInterno);
        rbSemiInterno.setBounds(310, 420, 125, 25);

        lblPlanoSaude.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblPlanoSaude.setForeground(new java.awt.Color(255, 255, 255));
        lblPlanoSaude.setText("TEM PLANO DE SAÚDE");
        pnlIdentInterno.add(lblPlanoSaude);
        lblPlanoSaude.setBounds(20, 460, 150, 17);

        rbPlanoSaudeNao.setBackground(new java.awt.Color(255, 255, 255));
        bgPlanoSaude.add(rbPlanoSaudeNao);
        rbPlanoSaudeNao.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        rbPlanoSaudeNao.setForeground(new java.awt.Color(255, 255, 255));
        rbPlanoSaudeNao.setText("NÃO");
        rbPlanoSaudeNao.setContentAreaFilled(false);
        rbPlanoSaudeNao.setFocusPainted(false);
        rbPlanoSaudeNao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbPlanoSaudeNaoActionPerformed(evt);
            }
        });
        pnlIdentInterno.add(rbPlanoSaudeNao);
        rbPlanoSaudeNao.setBounds(180, 460, 55, 25);

        rbPlanoSaudeSim.setBackground(new java.awt.Color(255, 255, 255));
        bgPlanoSaude.add(rbPlanoSaudeSim);
        rbPlanoSaudeSim.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        rbPlanoSaudeSim.setForeground(new java.awt.Color(255, 255, 255));
        rbPlanoSaudeSim.setText("SIM");
        rbPlanoSaudeSim.setContentAreaFilled(false);
        rbPlanoSaudeSim.setFocusPainted(false);
        rbPlanoSaudeSim.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbPlanoSaudeSimActionPerformed(evt);
            }
        });
        pnlIdentInterno.add(rbPlanoSaudeSim);
        rbPlanoSaudeSim.setBounds(250, 460, 51, 25);

        lblQualPlanoSaude.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblQualPlanoSaude.setForeground(new java.awt.Color(255, 255, 255));
        lblQualPlanoSaude.setText("QUAL ?");
        pnlIdentInterno.add(lblQualPlanoSaude);
        lblQualPlanoSaude.setBounds(320, 460, 50, 17);
        lblQualPlanoSaude.setEnabled(false);

        tfPlanoSaude.setBackground(new java.awt.Color(153, 153, 153));
        tfPlanoSaude.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        tfPlanoSaude.setForeground(new java.awt.Color(255, 255, 255));
        pnlIdentInterno.add(tfPlanoSaude);
        tfPlanoSaude.setBounds(380, 460, 130, 23);
        tfPlanoSaude.setEnabled(false);

        lblSus.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblSus.setForeground(new java.awt.Color(255, 255, 255));
        lblSus.setText("TEM CARTÃO SUS");
        pnlIdentInterno.add(lblSus);
        lblSus.setBounds(20, 500, 120, 17);

        rbSusNao.setBackground(new java.awt.Color(255, 255, 255));
        bgSus.add(rbSusNao);
        rbSusNao.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        rbSusNao.setForeground(new java.awt.Color(255, 255, 255));
        rbSusNao.setText("NÃO");
        rbSusNao.setContentAreaFilled(false);
        rbSusNao.setFocusPainted(false);
        rbSusNao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbSusNaoActionPerformed(evt);
            }
        });
        pnlIdentInterno.add(rbSusNao);
        rbSusNao.setBounds(160, 500, 55, 25);

        lblCurso.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblCurso.setForeground(new java.awt.Color(255, 255, 255));
        lblCurso.setText("CURSO");
        pnlIdentInterno.add(lblCurso);
        lblCurso.setBounds(20, 140, 48, 17);

        rbSusSim.setBackground(new java.awt.Color(255, 255, 255));
        bgSus.add(rbSusSim);
        rbSusSim.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        rbSusSim.setForeground(new java.awt.Color(255, 255, 255));
        rbSusSim.setText("SIM");
        rbSusSim.setContentAreaFilled(false);
        rbSusSim.setFocusPainted(false);
        rbSusSim.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbSusSimActionPerformed(evt);
            }
        });
        pnlIdentInterno.add(rbSusSim);
        rbSusSim.setBounds(230, 500, 51, 25);

        lblTurma.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblTurma.setForeground(new java.awt.Color(255, 255, 255));
        lblTurma.setText("TURMA/ ANO");
        pnlIdentInterno.add(lblTurma);
        lblTurma.setBounds(540, 140, 87, 17);

        btnCancelar1.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnCancelar1.setForeground(new java.awt.Color(255, 255, 255));
        btnCancelar1.setText("CANCELAR");
        btnCancelar1.setBorderPainted(false);
        btnCancelar1.setContentAreaFilled(false);
        btnCancelar1.setFocusPainted(false);
        btnCancelar1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnCancelar1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnCancelar1MouseExited(evt);
            }
        });
        btnCancelar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelar1ActionPerformed(evt);
            }
        });
        pnlIdentInterno.add(btnCancelar1);
        btnCancelar1.setBounds(498, 500, 120, 25);

        tfTurma.setBackground(new java.awt.Color(153, 153, 153));
        tfTurma.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        tfTurma.setForeground(new java.awt.Color(255, 255, 255));
        pnlIdentInterno.add(tfTurma);
        tfTurma.setBounds(680, 140, 100, 23);

        btnContinuar1.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnContinuar1.setForeground(new java.awt.Color(255, 255, 255));
        btnContinuar1.setText("ALTERAR E CONTINUAR");
        btnContinuar1.setBorderPainted(false);
        btnContinuar1.setContentAreaFilled(false);
        btnContinuar1.setFocusPainted(false);
        btnContinuar1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnContinuar1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnContinuar1MouseExited(evt);
            }
        });
        btnContinuar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnContinuar1ActionPerformed(evt);
            }
        });
        pnlIdentInterno.add(btnContinuar1);
        btnContinuar1.setBounds(625, 500, 210, 25);

        cbCurso.setBackground(new java.awt.Color(153, 153, 153));
        cbCurso.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        cbCurso.setForeground(new java.awt.Color(255, 255, 255));
        pnlIdentInterno.add(cbCurso);
        cbCurso.setBounds(80, 140, 374, 23);

        lblMae.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblMae.setForeground(new java.awt.Color(255, 255, 255));
        lblMae.setText("NOME DA MÃE");
        pnlIdentInterno.add(lblMae);
        lblMae.setBounds(20, 220, 98, 17);

        tfMae.setBackground(new java.awt.Color(153, 153, 153));
        tfMae.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        tfMae.setForeground(new java.awt.Color(255, 255, 255));
        pnlIdentInterno.add(tfMae);
        tfMae.setBounds(130, 220, 360, 23);

        lblTelMae.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblTelMae.setForeground(new java.awt.Color(255, 255, 255));
        lblTelMae.setText("TELEFONE");
        pnlIdentInterno.add(lblTelMae);
        lblTelMae.setBounds(510, 220, 69, 17);

        tfTelMae.setBackground(new java.awt.Color(153, 153, 153));
        tfTelMae.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        tfTelMae.setForeground(new java.awt.Color(255, 255, 255));
        pnlIdentInterno.add(tfTelMae);
        tfTelMae.setBounds(600, 220, 100, 23);

        lblPai.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblPai.setForeground(new java.awt.Color(255, 255, 255));
        lblPai.setText("NOME DO PAI");
        pnlIdentInterno.add(lblPai);
        lblPai.setBounds(20, 260, 93, 17);

        lblNome.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblNome.setForeground(new java.awt.Color(255, 255, 255));
        lblNome.setText("NOME *");
        pnlIdentInterno.add(lblNome);
        lblNome.setBounds(20, 60, 60, 17);

        tfNome.setBackground(new java.awt.Color(153, 153, 153));
        tfNome.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        tfNome.setForeground(new java.awt.Color(255, 255, 255));
        pnlIdentInterno.add(tfNome);
        tfNome.setBounds(80, 60, 380, 23);

        lblDtNasc.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblDtNasc.setForeground(new java.awt.Color(255, 255, 255));
        lblDtNasc.setText("DATA NASCIMENTO *");
        pnlIdentInterno.add(lblDtNasc);
        lblDtNasc.setBounds(530, 60, 150, 17);

        tfDtNasc.setBackground(new java.awt.Color(153, 153, 153));
        tfDtNasc.setForeground(new java.awt.Color(255, 255, 255));
        try {
            tfDtNasc.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        tfDtNasc.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        pnlIdentInterno.add(tfDtNasc);
        tfDtNasc.setBounds(680, 60, 100, 23);

        tfMatricula.setBackground(new java.awt.Color(153, 153, 153));
        tfMatricula.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        tfMatricula.setForeground(new java.awt.Color(255, 255, 255));
        pnlIdentInterno.add(tfMatricula);
        tfMatricula.setBounds(680, 100, 100, 23);

        lblMatricula.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblMatricula.setForeground(new java.awt.Color(255, 255, 255));
        lblMatricula.setText("Nº MATRÍCULA");
        pnlIdentInterno.add(lblMatricula);
        lblMatricula.setBounds(540, 100, 102, 17);

        tfCpf.setBackground(new java.awt.Color(153, 153, 153));
        tfCpf.setForeground(new java.awt.Color(255, 255, 255));
        try {
            tfCpf.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###.###.###-##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        tfCpf.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        pnlIdentInterno.add(tfCpf);
        tfCpf.setBounds(80, 100, 101, 23);

        lblCpf.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblCpf.setForeground(new java.awt.Color(255, 255, 255));
        lblCpf.setText("CPF ");
        pnlIdentInterno.add(lblCpf);
        lblCpf.setBounds(20, 100, 30, 17);

        lblNSus.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblNSus.setForeground(new java.awt.Color(255, 255, 255));
        lblNSus.setText("Nº");
        pnlIdentInterno.add(lblNSus);
        lblNSus.setBounds(300, 500, 20, 14);

        tfNSus.setBackground(new java.awt.Color(153, 153, 153));
        tfNSus.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        tfNSus.setForeground(new java.awt.Color(255, 255, 255));
        pnlIdentInterno.add(tfNSus);
        tfNSus.setBounds(320, 500, 100, 23);

        lblIdPaciente.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblIdPaciente.setForeground(new java.awt.Color(255, 255, 255));
        lblIdPaciente.setText("ID Paciente");
        pnlIdentInterno.add(lblIdPaciente);
        lblIdPaciente.setBounds(10, 10, 90, 17);
        lblIdPaciente.setVisible(false);

        tfIdPaciente.setEditable(false);
        tfIdPaciente.setBackground(new java.awt.Color(153, 153, 153));
        tfIdPaciente.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        tfIdPaciente.setForeground(new java.awt.Color(255, 255, 255));
        pnlIdentInterno.add(tfIdPaciente);
        tfIdPaciente.setBounds(110, 10, 100, 23);
        tfIdPaciente.setVisible(false);

        tfTelefone.setBackground(new java.awt.Color(153, 153, 153));
        tfTelefone.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        tfTelefone.setForeground(new java.awt.Color(255, 255, 255));
        pnlIdentInterno.add(tfTelefone);
        tfTelefone.setBounds(340, 100, 120, 23);

        lblTelefone.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblTelefone.setForeground(new java.awt.Color(255, 255, 255));
        lblTelefone.setText("TELEFONE");
        pnlIdentInterno.add(lblTelefone);
        lblTelefone.setBounds(260, 100, 80, 17);

        tfUf.setBackground(new java.awt.Color(153, 153, 153));
        tfUf.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        tfUf.setForeground(new java.awt.Color(255, 255, 255));
        pnlIdentInterno.add(tfUf);
        tfUf.setBounds(740, 180, 40, 23);

        lblUf.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblUf.setForeground(new java.awt.Color(255, 255, 255));
        lblUf.setText("UF");
        pnlIdentInterno.add(lblUf);
        lblUf.setBounds(710, 180, 30, 17);

        tfCidade.setBackground(new java.awt.Color(153, 153, 153));
        tfCidade.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        tfCidade.setForeground(new java.awt.Color(255, 255, 255));
        pnlIdentInterno.add(tfCidade);
        tfCidade.setBounds(600, 180, 100, 23);

        lblCidade.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblCidade.setForeground(new java.awt.Color(255, 255, 255));
        lblCidade.setText("CIDADE");
        pnlIdentInterno.add(lblCidade);
        lblCidade.setBounds(540, 180, 70, 17);

        tfBairro.setBackground(new java.awt.Color(153, 153, 153));
        tfBairro.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        tfBairro.setForeground(new java.awt.Color(255, 255, 255));
        pnlIdentInterno.add(tfBairro);
        tfBairro.setBounds(380, 180, 150, 23);

        lblBairro.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblBairro.setForeground(new java.awt.Color(255, 255, 255));
        lblBairro.setText("BAIRRO");
        pnlIdentInterno.add(lblBairro);
        lblBairro.setBounds(320, 180, 70, 17);

        tfNCasa.setBackground(new java.awt.Color(153, 153, 153));
        tfNCasa.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        tfNCasa.setForeground(new java.awt.Color(255, 255, 255));
        pnlIdentInterno.add(tfNCasa);
        tfNCasa.setBounds(260, 180, 40, 23);

        lblNumero.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblNumero.setForeground(new java.awt.Color(255, 255, 255));
        lblNumero.setText("Nº");
        pnlIdentInterno.add(lblNumero);
        lblNumero.setBounds(240, 180, 20, 17);

        tfRua.setBackground(new java.awt.Color(153, 153, 153));
        tfRua.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        tfRua.setForeground(new java.awt.Color(255, 255, 255));
        tfRua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfRuaActionPerformed(evt);
            }
        });
        pnlIdentInterno.add(tfRua);
        tfRua.setBounds(80, 180, 150, 23);

        lblRua.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblRua.setForeground(new java.awt.Color(255, 255, 255));
        lblRua.setText("RUA");
        pnlIdentInterno.add(lblRua);
        lblRua.setBounds(20, 180, 40, 17);

        lblFundo1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Fundo Geral.png"))); // NOI18N
        pnlIdentInterno.add(lblFundo1);
        lblFundo1.setBounds(0, 0, 850, 570);

        javax.swing.GroupLayout pnlIdentificacaoLayout = new javax.swing.GroupLayout(pnlIdentificacao);
        pnlIdentificacao.setLayout(pnlIdentificacaoLayout);
        pnlIdentificacaoLayout.setHorizontalGroup(
            pnlIdentificacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlIdentificacaoLayout.createSequentialGroup()
                .addComponent(pnlIdentInterno, javax.swing.GroupLayout.PREFERRED_SIZE, 850, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        pnlIdentificacaoLayout.setVerticalGroup(
            pnlIdentificacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlIdentificacaoLayout.createSequentialGroup()
                .addComponent(pnlIdentInterno, javax.swing.GroupLayout.PREFERRED_SIZE, 570, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        tpCadastroPaciente.addTab("Identificação", pnlIdentificacao);

        pnlPregressa.setBackground(new java.awt.Color(255, 255, 255));

        pnlPregressaInterno.setLayout(null);

        rbCartaoInfanciaCompleto.setBackground(new java.awt.Color(255, 255, 255));
        bgVacinasInfancia.add(rbCartaoInfanciaCompleto);
        rbCartaoInfanciaCompleto.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        rbCartaoInfanciaCompleto.setForeground(new java.awt.Color(255, 255, 255));
        rbCartaoInfanciaCompleto.setText("CARTÃO COMPLETO");
        rbCartaoInfanciaCompleto.setContentAreaFilled(false);
        rbCartaoInfanciaCompleto.setFocusPainted(false);
        pnlPregressaInterno.add(rbCartaoInfanciaCompleto);
        rbCartaoInfanciaCompleto.setBounds(210, 60, 170, 25);

        lblAlergiaMedicamentosa.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblAlergiaMedicamentosa.setForeground(new java.awt.Color(255, 255, 255));
        lblAlergiaMedicamentosa.setText("ALERGIA MEDICAMENTOSA");
        pnlPregressaInterno.add(lblAlergiaMedicamentosa);
        lblAlergiaMedicamentosa.setBounds(20, 340, 189, 17);

        rbCartaoInfanciaIncompleto.setBackground(new java.awt.Color(255, 255, 255));
        bgVacinasInfancia.add(rbCartaoInfanciaIncompleto);
        rbCartaoInfanciaIncompleto.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        rbCartaoInfanciaIncompleto.setForeground(new java.awt.Color(255, 255, 255));
        rbCartaoInfanciaIncompleto.setText("CARTÃO INCOMPLETO");
        rbCartaoInfanciaIncompleto.setContentAreaFilled(false);
        rbCartaoInfanciaIncompleto.setFocusPainted(false);
        pnlPregressaInterno.add(rbCartaoInfanciaIncompleto);
        rbCartaoInfanciaIncompleto.setBounds(390, 60, 180, 25);

        rbAlergiaMedicamentosaNao.setBackground(new java.awt.Color(255, 255, 255));
        bgAlergiaMedicamentosa.add(rbAlergiaMedicamentosaNao);
        rbAlergiaMedicamentosaNao.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        rbAlergiaMedicamentosaNao.setForeground(new java.awt.Color(255, 255, 255));
        rbAlergiaMedicamentosaNao.setText("NÃO");
        rbAlergiaMedicamentosaNao.setContentAreaFilled(false);
        rbAlergiaMedicamentosaNao.setFocusPainted(false);
        rbAlergiaMedicamentosaNao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbAlergiaMedicamentosaNaoActionPerformed(evt);
            }
        });
        pnlPregressaInterno.add(rbAlergiaMedicamentosaNao);
        rbAlergiaMedicamentosaNao.setBounds(220, 340, 70, 25);

        lblVacinasAdolescencia.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblVacinasAdolescencia.setForeground(new java.awt.Color(255, 255, 255));
        lblVacinasAdolescencia.setText("VACINAS NA ADOLESCÊNCIA");
        pnlPregressaInterno.add(lblVacinasAdolescencia);
        lblVacinasAdolescencia.setBounds(20, 100, 199, 17);

        rbCartaoAdolescenciaCompleto.setBackground(new java.awt.Color(255, 255, 255));
        bgVacinasAdolescencia.add(rbCartaoAdolescenciaCompleto);
        rbCartaoAdolescenciaCompleto.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        rbCartaoAdolescenciaCompleto.setForeground(new java.awt.Color(255, 255, 255));
        rbCartaoAdolescenciaCompleto.setText("CARTÃO COMPLETO");
        rbCartaoAdolescenciaCompleto.setContentAreaFilled(false);
        rbCartaoAdolescenciaCompleto.setFocusPainted(false);
        pnlPregressaInterno.add(rbCartaoAdolescenciaCompleto);
        rbCartaoAdolescenciaCompleto.setBounds(240, 100, 170, 25);

        rbAlergiaMedicamentosaSim.setBackground(new java.awt.Color(255, 255, 255));
        bgAlergiaMedicamentosa.add(rbAlergiaMedicamentosaSim);
        rbAlergiaMedicamentosaSim.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        rbAlergiaMedicamentosaSim.setForeground(new java.awt.Color(255, 255, 255));
        rbAlergiaMedicamentosaSim.setText("SIM");
        rbAlergiaMedicamentosaSim.setContentAreaFilled(false);
        rbAlergiaMedicamentosaSim.setFocusPainted(false);
        rbAlergiaMedicamentosaSim.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbAlergiaMedicamentosaSimActionPerformed(evt);
            }
        });
        pnlPregressaInterno.add(rbAlergiaMedicamentosaSim);
        rbAlergiaMedicamentosaSim.setBounds(300, 340, 70, 25);

        tfAlergiaMedicamentosa.setBackground(new java.awt.Color(153, 153, 153));
        tfAlergiaMedicamentosa.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        tfAlergiaMedicamentosa.setForeground(new java.awt.Color(255, 255, 255));
        pnlPregressaInterno.add(tfAlergiaMedicamentosa);
        tfAlergiaMedicamentosa.setBounds(400, 340, 430, 23);
        tfAlergiaMedicamentosa.setEnabled(false);

        rbCartaoAdolescenciaIncompleto.setBackground(new java.awt.Color(255, 255, 255));
        bgVacinasAdolescencia.add(rbCartaoAdolescenciaIncompleto);
        rbCartaoAdolescenciaIncompleto.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        rbCartaoAdolescenciaIncompleto.setForeground(new java.awt.Color(255, 255, 255));
        rbCartaoAdolescenciaIncompleto.setText("CARTÃO INCOMPLETO");
        rbCartaoAdolescenciaIncompleto.setContentAreaFilled(false);
        rbCartaoAdolescenciaIncompleto.setFocusPainted(false);
        pnlPregressaInterno.add(rbCartaoAdolescenciaIncompleto);
        rbCartaoAdolescenciaIncompleto.setBounds(430, 100, 180, 25);

        lblAlergiaAlimentar.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblAlergiaAlimentar.setForeground(new java.awt.Color(255, 255, 255));
        lblAlergiaAlimentar.setText("ALERGIA ALIMENTAR");
        pnlPregressaInterno.add(lblAlergiaAlimentar);
        lblAlergiaAlimentar.setBounds(20, 380, 149, 17);

        lblVacinaFaltando.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblVacinaFaltando.setForeground(new java.awt.Color(255, 255, 255));
        lblVacinaFaltando.setText("QUAL VACINA FALTA?");
        pnlPregressaInterno.add(lblVacinaFaltando);
        lblVacinaFaltando.setBounds(20, 140, 154, 17);

        rbAlergiaAlimentarNao.setBackground(new java.awt.Color(255, 255, 255));
        bgAlergiaAlimentar.add(rbAlergiaAlimentarNao);
        rbAlergiaAlimentarNao.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        rbAlergiaAlimentarNao.setForeground(new java.awt.Color(255, 255, 255));
        rbAlergiaAlimentarNao.setText("NÃO");
        rbAlergiaAlimentarNao.setContentAreaFilled(false);
        rbAlergiaAlimentarNao.setFocusPainted(false);
        rbAlergiaAlimentarNao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbAlergiaAlimentarNaoActionPerformed(evt);
            }
        });
        pnlPregressaInterno.add(rbAlergiaAlimentarNao);
        rbAlergiaAlimentarNao.setBounds(220, 380, 70, 25);

        tfVacinaFaltando.setBackground(new java.awt.Color(153, 153, 153));
        tfVacinaFaltando.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        tfVacinaFaltando.setForeground(new java.awt.Color(255, 255, 255));
        pnlPregressaInterno.add(tfVacinaFaltando);
        tfVacinaFaltando.setBounds(200, 140, 572, 23);

        rbAlergiaAlimentarSim.setBackground(new java.awt.Color(255, 255, 255));
        bgAlergiaAlimentar.add(rbAlergiaAlimentarSim);
        rbAlergiaAlimentarSim.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        rbAlergiaAlimentarSim.setForeground(new java.awt.Color(255, 255, 255));
        rbAlergiaAlimentarSim.setText("SIM");
        rbAlergiaAlimentarSim.setContentAreaFilled(false);
        rbAlergiaAlimentarSim.setFocusPainted(false);
        rbAlergiaAlimentarSim.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbAlergiaAlimentarSimActionPerformed(evt);
            }
        });
        pnlPregressaInterno.add(rbAlergiaAlimentarSim);
        rbAlergiaAlimentarSim.setBounds(300, 380, 70, 25);

        lblDoencas.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblDoencas.setForeground(new java.awt.Color(255, 255, 255));
        lblDoencas.setText("DOENÇAS NA INFÂNCA E ADOLESCÊNCIA");
        pnlPregressaInterno.add(lblDoencas);
        lblDoencas.setBounds(20, 180, 280, 17);

        tfAlergiaAlimentar.setBackground(new java.awt.Color(153, 153, 153));
        tfAlergiaAlimentar.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        tfAlergiaAlimentar.setForeground(new java.awt.Color(255, 255, 255));
        pnlPregressaInterno.add(tfAlergiaAlimentar);
        tfAlergiaAlimentar.setBounds(400, 380, 430, 23);
        tfAlergiaAlimentar.setEnabled(false);

        tfDoencas.setBackground(new java.awt.Color(153, 153, 153));
        tfDoencas.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        tfDoencas.setForeground(new java.awt.Color(255, 255, 255));
        pnlPregressaInterno.add(tfDoencas);
        tfDoencas.setBounds(330, 180, 456, 23);

        btnCancelar2.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnCancelar2.setForeground(new java.awt.Color(255, 255, 255));
        btnCancelar2.setText("CANCELAR");
        btnCancelar2.setBorderPainted(false);
        btnCancelar2.setContentAreaFilled(false);
        btnCancelar2.setFocusPainted(false);
        btnCancelar2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnCancelar2MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnCancelar2MouseExited(evt);
            }
        });
        btnCancelar2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelar2ActionPerformed(evt);
            }
        });
        pnlPregressaInterno.add(btnCancelar2);
        btnCancelar2.setBounds(498, 500, 120, 25);

        lblDoencasContraidas.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblDoencasContraidas.setForeground(new java.awt.Color(255, 255, 255));
        lblDoencasContraidas.setText("JÁ CONTRAIU ALGUMA DESSAS DOENÇAS");
        pnlPregressaInterno.add(lblDoencasContraidas);
        lblDoencasContraidas.setBounds(20, 220, 284, 17);

        btnContinuar2.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnContinuar2.setForeground(new java.awt.Color(255, 255, 255));
        btnContinuar2.setText("ALTERAR E CONTINUAR");
        btnContinuar2.setBorderPainted(false);
        btnContinuar2.setContentAreaFilled(false);
        btnContinuar2.setFocusPainted(false);
        btnContinuar2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnContinuar2MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnContinuar2MouseExited(evt);
            }
        });
        btnContinuar2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnContinuar2ActionPerformed(evt);
            }
        });
        pnlPregressaInterno.add(btnContinuar2);
        btnContinuar2.setBounds(625, 500, 210, 25);

        rbCaxumba.setBackground(new java.awt.Color(255, 255, 255));
        rbCaxumba.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        rbCaxumba.setForeground(new java.awt.Color(255, 255, 255));
        rbCaxumba.setText("CAXUMBA");
        rbCaxumba.setContentAreaFilled(false);
        rbCaxumba.setFocusPainted(false);
        pnlPregressaInterno.add(rbCaxumba);
        rbCaxumba.setBounds(440, 220, 100, 25);

        rbHepatite.setBackground(new java.awt.Color(255, 255, 255));
        rbHepatite.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        rbHepatite.setForeground(new java.awt.Color(255, 255, 255));
        rbHepatite.setText("HEPATITE");
        rbHepatite.setContentAreaFilled(false);
        rbHepatite.setFocusPainted(false);
        pnlPregressaInterno.add(rbHepatite);
        rbHepatite.setBounds(660, 220, 100, 25);

        rbMeningite.setBackground(new java.awt.Color(255, 255, 255));
        rbMeningite.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        rbMeningite.setForeground(new java.awt.Color(255, 255, 255));
        rbMeningite.setText("MENINGITE");
        rbMeningite.setContentAreaFilled(false);
        rbMeningite.setFocusPainted(false);
        pnlPregressaInterno.add(rbMeningite);
        rbMeningite.setBounds(320, 250, 110, 25);

        rbSarampo.setBackground(new java.awt.Color(255, 255, 255));
        rbSarampo.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        rbSarampo.setForeground(new java.awt.Color(255, 255, 255));
        rbSarampo.setText("SARAMPO");
        rbSarampo.setContentAreaFilled(false);
        rbSarampo.setFocusPainted(false);
        pnlPregressaInterno.add(rbSarampo);
        rbSarampo.setBounds(680, 250, 100, 25);

        rbRubeola.setBackground(new java.awt.Color(255, 255, 255));
        rbRubeola.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        rbRubeola.setForeground(new java.awt.Color(255, 255, 255));
        rbRubeola.setText("RUBÉOLA");
        rbRubeola.setContentAreaFilled(false);
        rbRubeola.setFocusPainted(false);
        pnlPregressaInterno.add(rbRubeola);
        rbRubeola.setBounds(570, 250, 100, 25);

        rbDengue.setBackground(new java.awt.Color(255, 255, 255));
        rbDengue.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        rbDengue.setForeground(new java.awt.Color(255, 255, 255));
        rbDengue.setText("DENGUE");
        rbDengue.setContentAreaFilled(false);
        rbDengue.setFocusPainted(false);
        pnlPregressaInterno.add(rbDengue);
        rbDengue.setBounds(550, 220, 100, 25);

        rbCatapora.setBackground(new java.awt.Color(255, 255, 255));
        rbCatapora.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        rbCatapora.setForeground(new java.awt.Color(255, 255, 255));
        rbCatapora.setText("CATAPORA");
        rbCatapora.setContentAreaFilled(false);
        rbCatapora.setFocusPainted(false);
        pnlPregressaInterno.add(rbCatapora);
        rbCatapora.setBounds(320, 220, 110, 25);

        rbPneumonia.setBackground(new java.awt.Color(255, 255, 255));
        rbPneumonia.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        rbPneumonia.setForeground(new java.awt.Color(255, 255, 255));
        rbPneumonia.setText("PNEUMONIA");
        rbPneumonia.setContentAreaFilled(false);
        rbPneumonia.setFocusPainted(false);
        pnlPregressaInterno.add(rbPneumonia);
        rbPneumonia.setBounds(440, 250, 120, 25);

        lblCirurgias.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblCirurgias.setForeground(new java.awt.Color(255, 255, 255));
        lblCirurgias.setText("CIRURGIAS REALIZADAS");
        pnlPregressaInterno.add(lblCirurgias);
        lblCirurgias.setBounds(20, 300, 172, 17);

        rbCirurgiasNao.setBackground(new java.awt.Color(255, 255, 255));
        bgCirurgiasRealizadas.add(rbCirurgiasNao);
        rbCirurgiasNao.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        rbCirurgiasNao.setForeground(new java.awt.Color(255, 255, 255));
        rbCirurgiasNao.setText("NÃO");
        rbCirurgiasNao.setContentAreaFilled(false);
        rbCirurgiasNao.setFocusPainted(false);
        rbCirurgiasNao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbCirurgiasNaoActionPerformed(evt);
            }
        });
        pnlPregressaInterno.add(rbCirurgiasNao);
        rbCirurgiasNao.setBounds(220, 300, 70, 25);

        rbCirurgiasSim.setBackground(new java.awt.Color(255, 255, 255));
        bgCirurgiasRealizadas.add(rbCirurgiasSim);
        rbCirurgiasSim.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        rbCirurgiasSim.setForeground(new java.awt.Color(255, 255, 255));
        rbCirurgiasSim.setText("SIM");
        rbCirurgiasSim.setContentAreaFilled(false);
        rbCirurgiasSim.setFocusPainted(false);
        rbCirurgiasSim.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbCirurgiasSimActionPerformed(evt);
            }
        });
        pnlPregressaInterno.add(rbCirurgiasSim);
        rbCirurgiasSim.setBounds(300, 300, 70, 25);

        lblVacinasInfancia.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblVacinasInfancia.setForeground(new java.awt.Color(255, 255, 255));
        lblVacinasInfancia.setText("VACINAS NA INFÂNCIA");
        pnlPregressaInterno.add(lblVacinasInfancia);
        lblVacinasInfancia.setBounds(20, 60, 158, 17);

        tfCirurgiasRealizadas.setBackground(new java.awt.Color(153, 153, 153));
        tfCirurgiasRealizadas.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        tfCirurgiasRealizadas.setForeground(new java.awt.Color(255, 255, 255));
        pnlPregressaInterno.add(tfCirurgiasRealizadas);
        tfCirurgiasRealizadas.setBounds(400, 300, 430, 23);
        tfCirurgiasRealizadas.setEnabled(false);

        lblFundo2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Fundo Geral.png"))); // NOI18N
        pnlPregressaInterno.add(lblFundo2);
        lblFundo2.setBounds(0, 0, 850, 570);

        javax.swing.GroupLayout pnlPregressaLayout = new javax.swing.GroupLayout(pnlPregressa);
        pnlPregressa.setLayout(pnlPregressaLayout);
        pnlPregressaLayout.setHorizontalGroup(
            pnlPregressaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPregressaLayout.createSequentialGroup()
                .addComponent(pnlPregressaInterno, javax.swing.GroupLayout.PREFERRED_SIZE, 850, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        pnlPregressaLayout.setVerticalGroup(
            pnlPregressaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPregressaLayout.createSequentialGroup()
                .addComponent(pnlPregressaInterno, javax.swing.GroupLayout.PREFERRED_SIZE, 570, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        tpCadastroPaciente.addTab("História Pregressa", pnlPregressa);

        pnlFamiliar.setBackground(new java.awt.Color(255, 255, 255));

        pnlFamiliarInterno.setLayout(null);

        tfEspecificar.setBackground(new java.awt.Color(153, 153, 153));
        tfEspecificar.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        tfEspecificar.setForeground(new java.awt.Color(255, 255, 255));
        pnlFamiliarInterno.add(tfEspecificar);
        tfEspecificar.setBounds(160, 190, 490, 23);
        tfEspecificar.setEnabled(false);

        btnCancelar3.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnCancelar3.setForeground(new java.awt.Color(255, 255, 255));
        btnCancelar3.setText("CANCELAR");
        btnCancelar3.setBorderPainted(false);
        btnCancelar3.setContentAreaFilled(false);
        btnCancelar3.setFocusPainted(false);
        btnCancelar3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnCancelar3MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnCancelar3MouseExited(evt);
            }
        });
        btnCancelar3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelar3ActionPerformed(evt);
            }
        });
        pnlFamiliarInterno.add(btnCancelar3);
        btnCancelar3.setBounds(498, 500, 120, 25);

        rbHipertensao.setBackground(new java.awt.Color(255, 255, 255));
        rbHipertensao.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        rbHipertensao.setForeground(new java.awt.Color(255, 255, 255));
        rbHipertensao.setText("HIPERTENSÃO ARTERIAL");
        rbHipertensao.setContentAreaFilled(false);
        rbHipertensao.setFocusPainted(false);
        pnlFamiliarInterno.add(rbHipertensao);
        rbHipertensao.setBounds(20, 150, 196, 25);

        btnContinuar3.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnContinuar3.setForeground(new java.awt.Color(255, 255, 255));
        btnContinuar3.setText("ALTERAR E CONTINUAR");
        btnContinuar3.setBorderPainted(false);
        btnContinuar3.setContentAreaFilled(false);
        btnContinuar3.setFocusPainted(false);
        btnContinuar3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnContinuar3MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnContinuar3MouseExited(evt);
            }
        });
        btnContinuar3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnContinuar3ActionPerformed(evt);
            }
        });
        pnlFamiliarInterno.add(btnContinuar3);
        btnContinuar3.setBounds(625, 500, 210, 25);

        rbOftalmologico.setBackground(new java.awt.Color(255, 255, 255));
        rbOftalmologico.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        rbOftalmologico.setForeground(new java.awt.Color(255, 255, 255));
        rbOftalmologico.setText("PROBLEMAS OFTALMOLÓGICOS");
        rbOftalmologico.setContentAreaFilled(false);
        rbOftalmologico.setFocusPainted(false);
        pnlFamiliarInterno.add(rbOftalmologico);
        rbOftalmologico.setBounds(260, 60, 248, 25);

        rbDiabetes.setBackground(new java.awt.Color(255, 255, 255));
        rbDiabetes.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        rbDiabetes.setForeground(new java.awt.Color(255, 255, 255));
        rbDiabetes.setText("DIABETES");
        rbDiabetes.setContentAreaFilled(false);
        rbDiabetes.setFocusPainted(false);
        rbDiabetes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbDiabetesActionPerformed(evt);
            }
        });
        pnlFamiliarInterno.add(rbDiabetes);
        rbDiabetes.setBounds(20, 120, 92, 25);

        rbMental.setBackground(new java.awt.Color(255, 255, 255));
        rbMental.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        rbMental.setForeground(new java.awt.Color(255, 255, 255));
        rbMental.setText("TRANSTORNOS MENTAIS");
        rbMental.setContentAreaFilled(false);
        rbMental.setFocusPainted(false);
        pnlFamiliarInterno.add(rbMental);
        rbMental.setBounds(260, 120, 210, 23);

        rbCancer.setBackground(new java.awt.Color(255, 255, 255));
        rbCancer.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        rbCancer.setForeground(new java.awt.Color(255, 255, 255));
        rbCancer.setText("CÂNCER");
        rbCancer.setContentAreaFilled(false);
        rbCancer.setFocusPainted(false);
        pnlFamiliarInterno.add(rbCancer);
        rbCancer.setBounds(20, 60, 82, 25);

        rbCardiopatia.setBackground(new java.awt.Color(255, 255, 255));
        rbCardiopatia.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        rbCardiopatia.setForeground(new java.awt.Color(255, 255, 255));
        rbCardiopatia.setText("CARDIOPATIAS");
        rbCardiopatia.setContentAreaFilled(false);
        rbCardiopatia.setFocusPainted(false);
        pnlFamiliarInterno.add(rbCardiopatia);
        rbCardiopatia.setBounds(20, 90, 132, 25);

        rbProblemaRenal.setBackground(new java.awt.Color(255, 255, 255));
        rbProblemaRenal.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        rbProblemaRenal.setForeground(new java.awt.Color(255, 255, 255));
        rbProblemaRenal.setText("PROBLEMAS RENAIS");
        rbProblemaRenal.setContentAreaFilled(false);
        rbProblemaRenal.setFocusPainted(false);
        pnlFamiliarInterno.add(rbProblemaRenal);
        rbProblemaRenal.setBounds(260, 90, 165, 25);

        rbHistFamiliarOutro.setBackground(new java.awt.Color(255, 255, 255));
        rbHistFamiliarOutro.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        rbHistFamiliarOutro.setForeground(new java.awt.Color(255, 255, 255));
        rbHistFamiliarOutro.setText("OUTRAS");
        rbHistFamiliarOutro.setContentAreaFilled(false);
        rbHistFamiliarOutro.setFocusPainted(false);
        rbHistFamiliarOutro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbHistFamiliarOutroActionPerformed(evt);
            }
        });
        pnlFamiliarInterno.add(rbHistFamiliarOutro);
        rbHistFamiliarOutro.setBounds(260, 150, 81, 25);

        lblEspecificar.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblEspecificar.setForeground(new java.awt.Color(255, 255, 255));
        lblEspecificar.setText("ESPECIFICAR");
        pnlFamiliarInterno.add(lblEspecificar);
        lblEspecificar.setBounds(20, 190, 92, 17);

        lblFundo3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Fundo Geral.png"))); // NOI18N
        pnlFamiliarInterno.add(lblFundo3);
        lblFundo3.setBounds(0, 0, 850, 570);

        javax.swing.GroupLayout pnlFamiliarLayout = new javax.swing.GroupLayout(pnlFamiliar);
        pnlFamiliar.setLayout(pnlFamiliarLayout);
        pnlFamiliarLayout.setHorizontalGroup(
            pnlFamiliarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFamiliarLayout.createSequentialGroup()
                .addComponent(pnlFamiliarInterno, javax.swing.GroupLayout.PREFERRED_SIZE, 850, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        pnlFamiliarLayout.setVerticalGroup(
            pnlFamiliarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFamiliarLayout.createSequentialGroup()
                .addComponent(pnlFamiliarInterno, javax.swing.GroupLayout.PREFERRED_SIZE, 570, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        tpCadastroPaciente.addTab("História Familiar", pnlFamiliar);

        pnlDoenca.setBackground(new java.awt.Color(255, 255, 255));

        pnlDoencaAtualInterno.setLayout(null);

        rbProteseSim.setBackground(new java.awt.Color(255, 255, 255));
        bgProtese.add(rbProteseSim);
        rbProteseSim.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        rbProteseSim.setForeground(new java.awt.Color(255, 255, 255));
        rbProteseSim.setText("SIM");
        rbProteseSim.setContentAreaFilled(false);
        rbProteseSim.setFocusPainted(false);
        pnlDoencaAtualInterno.add(rbProteseSim);
        rbProteseSim.setBounds(140, 270, 51, 25);

        lblProtese.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblProtese.setForeground(new java.awt.Color(255, 255, 255));
        lblProtese.setText("USA PRÓTESE");
        pnlDoencaAtualInterno.add(lblProtese);
        lblProtese.setBounds(30, 270, 100, 14);

        rbNenhumaNecessidade.setBackground(new java.awt.Color(255, 255, 255));
        rbNenhumaNecessidade.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        rbNenhumaNecessidade.setForeground(new java.awt.Color(255, 255, 255));
        rbNenhumaNecessidade.setText("NENHUMA NECESSIDADE ESPECÍFICA DESCRITA ACIMA");
        rbNenhumaNecessidade.setContentAreaFilled(false);
        rbNenhumaNecessidade.setFocusPainted(false);
        pnlDoencaAtualInterno.add(rbNenhumaNecessidade);
        rbNenhumaNecessidade.setBounds(30, 230, 403, 25);

        rbAuditiva.setBackground(new java.awt.Color(255, 255, 255));
        rbAuditiva.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        rbAuditiva.setForeground(new java.awt.Color(255, 255, 255));
        rbAuditiva.setText("DEFICIÊNCIA AUDITIVA");
        rbAuditiva.setContentAreaFilled(false);
        rbAuditiva.setFocusPainted(false);
        pnlDoencaAtualInterno.add(rbAuditiva);
        rbAuditiva.setBounds(30, 100, 210, 23);

        rbConcentracao.setBackground(new java.awt.Color(255, 255, 255));
        rbConcentracao.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        rbConcentracao.setForeground(new java.awt.Color(255, 255, 255));
        rbConcentracao.setText("DIFICULDADE DE CONCENTRAÇÃO");
        rbConcentracao.setContentAreaFilled(false);
        rbConcentracao.setFocusPainted(false);
        pnlDoencaAtualInterno.add(rbConcentracao);
        rbConcentracao.setBounds(30, 190, 263, 25);

        rbSuperdotacao.setBackground(new java.awt.Color(255, 255, 255));
        rbSuperdotacao.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        rbSuperdotacao.setForeground(new java.awt.Color(255, 255, 255));
        rbSuperdotacao.setText("SUPERDOTAÇÃO/ ALTAS HABILIDADES");
        rbSuperdotacao.setContentAreaFilled(false);
        rbSuperdotacao.setFocusPainted(false);
        rbSuperdotacao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbSuperdotacaojRadioButton42ActionPerformed(evt);
            }
        });
        pnlDoencaAtualInterno.add(rbSuperdotacao);
        rbSuperdotacao.setBounds(360, 160, 291, 25);

        btnContinuar4.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnContinuar4.setForeground(new java.awt.Color(255, 255, 255));
        btnContinuar4.setText("ALTERAR E CONTINUAR");
        btnContinuar4.setBorderPainted(false);
        btnContinuar4.setContentAreaFilled(false);
        btnContinuar4.setFocusPainted(false);
        btnContinuar4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnContinuar4MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnContinuar4MouseExited(evt);
            }
        });
        btnContinuar4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnContinuar4ActionPerformed(evt);
            }
        });
        pnlDoencaAtualInterno.add(btnContinuar4);
        btnContinuar4.setBounds(625, 500, 210, 25);

        rbDesenvolvimento.setBackground(new java.awt.Color(255, 255, 255));
        rbDesenvolvimento.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        rbDesenvolvimento.setForeground(new java.awt.Color(255, 255, 255));
        rbDesenvolvimento.setText("TRANSTORNO GLOBAL DE DESENVOLVIMENTO");
        rbDesenvolvimento.setContentAreaFilled(false);
        rbDesenvolvimento.setFocusPainted(false);
        pnlDoencaAtualInterno.add(rbDesenvolvimento);
        rbDesenvolvimento.setBounds(360, 190, 344, 25);

        btnCancelar4.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnCancelar4.setForeground(new java.awt.Color(255, 255, 255));
        btnCancelar4.setText("CANCELAR");
        btnCancelar4.setBorderPainted(false);
        btnCancelar4.setContentAreaFilled(false);
        btnCancelar4.setFocusPainted(false);
        btnCancelar4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnCancelar4MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnCancelar4MouseExited(evt);
            }
        });
        btnCancelar4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelar4ActionPerformed(evt);
            }
        });
        pnlDoencaAtualInterno.add(btnCancelar4);
        btnCancelar4.setBounds(498, 500, 120, 25);

        rbVisual.setBackground(new java.awt.Color(255, 255, 255));
        rbVisual.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        rbVisual.setForeground(new java.awt.Color(255, 255, 255));
        rbVisual.setText("DEFICIÊNCIA VISUAL");
        rbVisual.setContentAreaFilled(false);
        rbVisual.setFocusPainted(false);
        pnlDoencaAtualInterno.add(rbVisual);
        rbVisual.setBounds(30, 160, 172, 25);

        lblAcompanhamento.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblAcompanhamento.setForeground(new java.awt.Color(255, 255, 255));
        lblAcompanhamento.setText("FAZ ACOMPANHAMENTO DESSE PROBLEMA");
        pnlDoencaAtualInterno.add(lblAcompanhamento);
        lblAcompanhamento.setBounds(30, 420, 310, 14);

        rbLeitura.setBackground(new java.awt.Color(255, 255, 255));
        rbLeitura.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        rbLeitura.setForeground(new java.awt.Color(255, 255, 255));
        rbLeitura.setText("DIFICULDADE DE LEITURA");
        rbLeitura.setContentAreaFilled(false);
        rbLeitura.setFocusPainted(false);
        pnlDoencaAtualInterno.add(rbLeitura);
        rbLeitura.setBounds(360, 130, 207, 25);

        rbAcompanhamentoSim.setBackground(new java.awt.Color(255, 255, 255));
        bgAcompanhamento.add(rbAcompanhamentoSim);
        rbAcompanhamentoSim.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        rbAcompanhamentoSim.setForeground(new java.awt.Color(255, 255, 255));
        rbAcompanhamentoSim.setText("SIM");
        rbAcompanhamentoSim.setContentAreaFilled(false);
        rbAcompanhamentoSim.setFocusPainted(false);
        pnlDoencaAtualInterno.add(rbAcompanhamentoSim);
        rbAcompanhamentoSim.setBounds(340, 420, 51, 25);

        rbFisica.setBackground(new java.awt.Color(255, 255, 255));
        rbFisica.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        rbFisica.setForeground(new java.awt.Color(255, 255, 255));
        rbFisica.setText("DEFICIÊNCIA FÍSICA");
        rbFisica.setContentAreaFilled(false);
        rbFisica.setFocusPainted(false);
        pnlDoencaAtualInterno.add(rbFisica);
        rbFisica.setBounds(30, 130, 168, 25);

        rbAcompanhamentoNao.setBackground(new java.awt.Color(255, 255, 255));
        bgAcompanhamento.add(rbAcompanhamentoNao);
        rbAcompanhamentoNao.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        rbAcompanhamentoNao.setForeground(new java.awt.Color(255, 255, 255));
        rbAcompanhamentoNao.setText("NÃO");
        rbAcompanhamentoNao.setContentAreaFilled(false);
        rbAcompanhamentoNao.setFocusPainted(false);
        pnlDoencaAtualInterno.add(rbAcompanhamentoNao);
        rbAcompanhamentoNao.setBounds(400, 420, 55, 25);

        tfDoencaCronica.setBackground(new java.awt.Color(153, 153, 153));
        tfDoencaCronica.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        tfDoencaCronica.setForeground(new java.awt.Color(255, 255, 255));
        pnlDoencaAtualInterno.add(tfDoencaCronica);
        tfDoencaCronica.setBounds(190, 370, 600, 20);
        tfDoencaCronica.setEnabled(false);

        rbEscrita.setBackground(new java.awt.Color(255, 255, 255));
        rbEscrita.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        rbEscrita.setForeground(new java.awt.Color(255, 255, 255));
        rbEscrita.setText("DIFICULDADE DE ESCRITA");
        rbEscrita.setContentAreaFilled(false);
        rbEscrita.setFocusPainted(false);
        pnlDoencaAtualInterno.add(rbEscrita);
        rbEscrita.setBounds(360, 100, 207, 25);

        lblNecessidadeEspecifica.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblNecessidadeEspecifica.setForeground(new java.awt.Color(255, 255, 255));
        lblNecessidadeEspecifica.setText("APRESENTA ALGUMA NECESSIDADE ESPECÍFICA OU DIFICULDADE RELACIONADA ABAIXO ?");
        pnlDoencaAtualInterno.add(lblNecessidadeEspecifica);
        lblNecessidadeEspecifica.setBounds(30, 60, 650, 14);

        rbCronicaOutros.setBackground(new java.awt.Color(255, 255, 255));
        rbCronicaOutros.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        rbCronicaOutros.setForeground(new java.awt.Color(255, 255, 255));
        rbCronicaOutros.setText("OUTROS");
        rbCronicaOutros.setContentAreaFilled(false);
        rbCronicaOutros.setFocusPainted(false);
        rbCronicaOutros.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbCronicaOutrosActionPerformed(evt);
            }
        });
        pnlDoencaAtualInterno.add(rbCronicaOutros);
        rbCronicaOutros.setBounds(100, 370, 82, 25);

        rbRinite.setBackground(new java.awt.Color(255, 255, 255));
        rbRinite.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        rbRinite.setForeground(new java.awt.Color(255, 255, 255));
        rbRinite.setText("RINITE");
        rbRinite.setContentAreaFilled(false);
        rbRinite.setFocusPainted(false);
        pnlDoencaAtualInterno.add(rbRinite);
        rbRinite.setBounds(20, 370, 72, 25);

        rbRenal.setBackground(new java.awt.Color(255, 255, 255));
        rbRenal.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        rbRenal.setForeground(new java.awt.Color(255, 255, 255));
        rbRenal.setText("PROBLEMAS RENAIS");
        rbRenal.setContentAreaFilled(false);
        rbRenal.setFocusPainted(false);
        pnlDoencaAtualInterno.add(rbRenal);
        rbRenal.setBounds(650, 340, 165, 25);

        rbAsma.setBackground(new java.awt.Color(255, 255, 255));
        rbAsma.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        rbAsma.setForeground(new java.awt.Color(255, 255, 255));
        rbAsma.setText("ASMA");
        rbAsma.setContentAreaFilled(false);
        rbAsma.setFocusPainted(false);
        pnlDoencaAtualInterno.add(rbAsma);
        rbAsma.setBounds(20, 340, 65, 25);

        rbBronquite.setBackground(new java.awt.Color(255, 255, 255));
        rbBronquite.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        rbBronquite.setForeground(new java.awt.Color(255, 255, 255));
        rbBronquite.setText("BRONQUITE");
        rbBronquite.setContentAreaFilled(false);
        rbBronquite.setFocusPainted(false);
        pnlDoencaAtualInterno.add(rbBronquite);
        rbBronquite.setBounds(90, 340, 106, 25);

        rbCronicaDiabetes.setBackground(new java.awt.Color(255, 255, 255));
        rbCronicaDiabetes.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        rbCronicaDiabetes.setForeground(new java.awt.Color(255, 255, 255));
        rbCronicaDiabetes.setText("DIABETES");
        rbCronicaDiabetes.setContentAreaFilled(false);
        rbCronicaDiabetes.setFocusPainted(false);
        pnlDoencaAtualInterno.add(rbCronicaDiabetes);
        rbCronicaDiabetes.setBounds(200, 340, 92, 25);

        rbPressaoAlta.setBackground(new java.awt.Color(255, 255, 255));
        rbPressaoAlta.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        rbPressaoAlta.setForeground(new java.awt.Color(255, 255, 255));
        rbPressaoAlta.setText("PRESSÃO ALTA");
        rbPressaoAlta.setContentAreaFilled(false);
        rbPressaoAlta.setFocusPainted(false);
        rbPressaoAlta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbPressaoAltajRadioButton59ActionPerformed(evt);
            }
        });
        pnlDoencaAtualInterno.add(rbPressaoAlta);
        rbPressaoAlta.setBounds(300, 340, 129, 25);

        rbCardiaco.setBackground(new java.awt.Color(255, 255, 255));
        rbCardiaco.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        rbCardiaco.setForeground(new java.awt.Color(255, 255, 255));
        rbCardiaco.setText("PROBLEMAS CARDÍACOS");
        rbCardiaco.setContentAreaFilled(false);
        rbCardiaco.setFocusPainted(false);
        pnlDoencaAtualInterno.add(rbCardiaco);
        rbCardiaco.setBounds(440, 340, 199, 25);

        lblDoencaCronica.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblDoencaCronica.setForeground(new java.awt.Color(255, 255, 255));
        lblDoencaCronica.setText("ALGUMA DOENÇA CRÔNICA ?");
        pnlDoencaAtualInterno.add(lblDoencaCronica);
        lblDoencaCronica.setBounds(20, 310, 230, 14);

        rbProteseNao.setBackground(new java.awt.Color(255, 255, 255));
        bgProtese.add(rbProteseNao);
        rbProteseNao.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        rbProteseNao.setForeground(new java.awt.Color(255, 255, 255));
        rbProteseNao.setText("NÃO");
        rbProteseNao.setContentAreaFilled(false);
        rbProteseNao.setFocusPainted(false);
        pnlDoencaAtualInterno.add(rbProteseNao);
        rbProteseNao.setBounds(200, 270, 55, 25);

        lblFundo4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Fundo Geral.png"))); // NOI18N
        pnlDoencaAtualInterno.add(lblFundo4);
        lblFundo4.setBounds(0, 0, 850, 570);

        javax.swing.GroupLayout pnlDoencaLayout = new javax.swing.GroupLayout(pnlDoenca);
        pnlDoenca.setLayout(pnlDoencaLayout);
        pnlDoencaLayout.setHorizontalGroup(
            pnlDoencaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDoencaLayout.createSequentialGroup()
                .addComponent(pnlDoencaAtualInterno, javax.swing.GroupLayout.PREFERRED_SIZE, 850, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        pnlDoencaLayout.setVerticalGroup(
            pnlDoencaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDoencaLayout.createSequentialGroup()
                .addComponent(pnlDoencaAtualInterno, javax.swing.GroupLayout.PREFERRED_SIZE, 570, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        tpCadastroPaciente.addTab("Histórico de Doença Atual", pnlDoenca);

        pnlQuestionario1.setBackground(new java.awt.Color(255, 255, 255));

        pnlQuestionario1Interno.setLayout(null);

        btnContinuar5.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnContinuar5.setForeground(new java.awt.Color(255, 255, 255));
        btnContinuar5.setText("ALTERAR E CONTINUAR");
        btnContinuar5.setBorderPainted(false);
        btnContinuar5.setContentAreaFilled(false);
        btnContinuar5.setFocusPainted(false);
        btnContinuar5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnContinuar5MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnContinuar5MouseExited(evt);
            }
        });
        btnContinuar5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnContinuar5ActionPerformed(evt);
            }
        });
        pnlQuestionario1Interno.add(btnContinuar5);
        btnContinuar5.setBounds(625, 500, 210, 25);

        lblEpistaxe.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblEpistaxe.setForeground(new java.awt.Color(255, 255, 255));
        lblEpistaxe.setText("JÁ SOFREU EPISTAXE (SANGRAMENTO NASAL)? COM QUE FREQUÊNCIA?");
        pnlQuestionario1Interno.add(lblEpistaxe);
        lblEpistaxe.setBounds(30, 160, 490, 17);

        lblMedicamentoContinuo.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblMedicamentoContinuo.setForeground(new java.awt.Color(255, 255, 255));
        lblMedicamentoContinuo.setText("MEDICAMENTOS DE USO CONTÍNUO");
        pnlQuestionario1Interno.add(lblMedicamentoContinuo);
        lblMedicamentoContinuo.setBounds(30, 60, 243, 17);

        lblPressaoArterial.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblPressaoArterial.setForeground(new java.awt.Color(255, 255, 255));
        lblPressaoArterial.setText("QUANTO COSTUMA SER A PRESSÃO ARTERIAL? ");
        pnlQuestionario1Interno.add(lblPressaoArterial);
        lblPressaoArterial.setBounds(30, 220, 327, 17);

        tfCefaleia.setBackground(new java.awt.Color(153, 153, 153));
        tfCefaleia.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        tfCefaleia.setForeground(new java.awt.Color(255, 255, 255));
        pnlQuestionario1Interno.add(tfCefaleia);
        tfCefaleia.setBounds(30, 300, 610, 23);

        tfDesmaios.setBackground(new java.awt.Color(153, 153, 153));
        tfDesmaios.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        tfDesmaios.setForeground(new java.awt.Color(255, 255, 255));
        pnlQuestionario1Interno.add(tfDesmaios);
        tfDesmaios.setBounds(30, 120, 610, 23);

        tfDiarreia.setBackground(new java.awt.Color(153, 153, 153));
        tfDiarreia.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        tfDiarreia.setForeground(new java.awt.Color(255, 255, 255));
        pnlQuestionario1Interno.add(tfDiarreia);
        tfDiarreia.setBounds(30, 360, 610, 23);

        tfEpistaxe.setBackground(new java.awt.Color(153, 153, 153));
        tfEpistaxe.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        tfEpistaxe.setForeground(new java.awt.Color(255, 255, 255));
        pnlQuestionario1Interno.add(tfEpistaxe);
        tfEpistaxe.setBounds(30, 180, 610, 23);

        tfMedicamentoContinuo.setBackground(new java.awt.Color(153, 153, 153));
        tfMedicamentoContinuo.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        tfMedicamentoContinuo.setForeground(new java.awt.Color(255, 255, 255));
        pnlQuestionario1Interno.add(tfMedicamentoContinuo);
        tfMedicamentoContinuo.setBounds(290, 60, 350, 23);

        lblCefaleia.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblCefaleia.setForeground(new java.awt.Color(255, 255, 255));
        lblCefaleia.setText("VOCÊ COSTUMA SENTIR CEFALÉIA (DOR DE CABEÇA)? SE SIM, QUE MEDICAÇÃO USA PARA ALIVIÁ-LA?");
        pnlQuestionario1Interno.add(lblCefaleia);
        lblCefaleia.setBounds(30, 280, 708, 17);

        tfPressaoArterial.setBackground(new java.awt.Color(153, 153, 153));
        tfPressaoArterial.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        tfPressaoArterial.setForeground(new java.awt.Color(255, 255, 255));
        pnlQuestionario1Interno.add(tfPressaoArterial);
        tfPressaoArterial.setBounds(30, 240, 610, 23);

        lblDesmaios.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblDesmaios.setForeground(new java.awt.Color(255, 255, 255));
        lblDesmaios.setText("JÁ SOFREU DESMAIOS OU CONVULSÕES? COM QUE FREQUÊNCIA? POR QUAIS MOTIVOS?");
        pnlQuestionario1Interno.add(lblDesmaios);
        lblDesmaios.setBounds(30, 100, 607, 17);

        btnCancelar5.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnCancelar5.setForeground(new java.awt.Color(255, 255, 255));
        btnCancelar5.setText("CANCELAR");
        btnCancelar5.setBorderPainted(false);
        btnCancelar5.setContentAreaFilled(false);
        btnCancelar5.setFocusPainted(false);
        btnCancelar5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnCancelar5MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnCancelar5MouseExited(evt);
            }
        });
        btnCancelar5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelar5ActionPerformed(evt);
            }
        });
        pnlQuestionario1Interno.add(btnCancelar5);
        btnCancelar5.setBounds(498, 500, 120, 25);

        lblDiarreia.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblDiarreia.setForeground(new java.awt.Color(255, 255, 255));
        lblDiarreia.setText("SENTE COM FREQUÊNCIA DIARRÉIA, NÁUSEAS, DOR DE ESTÔMAGO OU CONSTIPAÇÃO INTESTINAL? QUAIS?");
        pnlQuestionario1Interno.add(lblDiarreia);
        lblDiarreia.setBounds(30, 340, 734, 17);

        lblFundo5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Fundo Geral.png"))); // NOI18N
        pnlQuestionario1Interno.add(lblFundo5);
        lblFundo5.setBounds(0, 0, 850, 570);

        javax.swing.GroupLayout pnlQuestionario1Layout = new javax.swing.GroupLayout(pnlQuestionario1);
        pnlQuestionario1.setLayout(pnlQuestionario1Layout);
        pnlQuestionario1Layout.setHorizontalGroup(
            pnlQuestionario1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlQuestionario1Layout.createSequentialGroup()
                .addComponent(pnlQuestionario1Interno, javax.swing.GroupLayout.PREFERRED_SIZE, 850, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        pnlQuestionario1Layout.setVerticalGroup(
            pnlQuestionario1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlQuestionario1Layout.createSequentialGroup()
                .addComponent(pnlQuestionario1Interno, javax.swing.GroupLayout.PREFERRED_SIZE, 570, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        tpCadastroPaciente.addTab("Questionário 1", pnlQuestionario1);

        pnlQuestionario2.setBackground(new java.awt.Color(255, 255, 255));

        pnlQuestionario2Interno.setLayout(null);

        lblAnotacoesRelevantes.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblAnotacoesRelevantes.setForeground(new java.awt.Color(255, 255, 255));
        lblAnotacoesRelevantes.setText("ANOTAÇÕES RELEVANTES SOBRE A SAÚDE DO PACIENTE QUE NÃO CONSTA NA FICHA");
        pnlQuestionario2Interno.add(lblAnotacoesRelevantes);
        lblAnotacoesRelevantes.setBounds(30, 250, 600, 17);

        lblColica.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblColica.setForeground(new java.awt.Color(255, 255, 255));
        lblColica.setText("COSTUMA SENTIR CÓLICA MENSTRUAL? SE SIM, O QUE USA PARA ALIVIÁ-LA? (MENINAS)");
        pnlQuestionario2Interno.add(lblColica);
        lblColica.setBounds(30, 60, 660, 17);

        lblContatoEmergencia.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblContatoEmergencia.setForeground(new java.awt.Color(255, 255, 255));
        lblContatoEmergencia.setText("CONTATO DE EMERGÊNCIA (NOME E TELEFONE)");
        pnlQuestionario2Interno.add(lblContatoEmergencia);
        lblContatoEmergencia.setBounds(30, 330, 370, 17);

        tfAcompanhamentoEspecializado.setBackground(new java.awt.Color(153, 153, 153));
        tfAcompanhamentoEspecializado.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        tfAcompanhamentoEspecializado.setForeground(new java.awt.Color(255, 255, 255));
        pnlQuestionario2Interno.add(tfAcompanhamentoEspecializado);
        tfAcompanhamentoEspecializado.setBounds(120, 200, 540, 23);
        tfAcompanhamentoEspecializado.setEnabled(false);

        tfAnotacoesRelevantes.setBackground(new java.awt.Color(153, 153, 153));
        tfAnotacoesRelevantes.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        tfAnotacoesRelevantes.setForeground(new java.awt.Color(255, 255, 255));
        pnlQuestionario2Interno.add(tfAnotacoesRelevantes);
        tfAnotacoesRelevantes.setBounds(30, 280, 620, 23);

        rbAcompanhamentoEspecializadoOutro.setBackground(new java.awt.Color(255, 255, 255));
        rbAcompanhamentoEspecializadoOutro.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        rbAcompanhamentoEspecializadoOutro.setForeground(new java.awt.Color(255, 255, 255));
        rbAcompanhamentoEspecializadoOutro.setText("OUTRO");
        rbAcompanhamentoEspecializadoOutro.setContentAreaFilled(false);
        rbAcompanhamentoEspecializadoOutro.setFocusPainted(false);
        rbAcompanhamentoEspecializadoOutro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbAcompanhamentoEspecializadoOutroActionPerformed(evt);
            }
        });
        pnlQuestionario2Interno.add(rbAcompanhamentoEspecializadoOutro);
        rbAcompanhamentoEspecializadoOutro.setBounds(30, 200, 74, 25);

        tfColica.setBackground(new java.awt.Color(153, 153, 153));
        tfColica.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        tfColica.setForeground(new java.awt.Color(255, 255, 255));
        pnlQuestionario2Interno.add(tfColica);
        tfColica.setBounds(30, 90, 616, 23);

        rbFisioterapia.setBackground(new java.awt.Color(255, 255, 255));
        rbFisioterapia.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        rbFisioterapia.setForeground(new java.awt.Color(255, 255, 255));
        rbFisioterapia.setText("FISIOTERAPIA");
        rbFisioterapia.setContentAreaFilled(false);
        rbFisioterapia.setFocusPainted(false);
        pnlQuestionario2Interno.add(rbFisioterapia);
        rbFisioterapia.setBounds(320, 170, 124, 25);

        tfContatoEmergencia.setBackground(new java.awt.Color(153, 153, 153));
        tfContatoEmergencia.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        tfContatoEmergencia.setForeground(new java.awt.Color(255, 255, 255));
        pnlQuestionario2Interno.add(tfContatoEmergencia);
        tfContatoEmergencia.setBounds(30, 360, 620, 23);

        rbFonaudiologo.setBackground(new java.awt.Color(255, 255, 255));
        rbFonaudiologo.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        rbFonaudiologo.setForeground(new java.awt.Color(255, 255, 255));
        rbFonaudiologo.setText("FONAUDIÓLOGO");
        rbFonaudiologo.setContentAreaFilled(false);
        rbFonaudiologo.setFocusPainted(false);
        pnlQuestionario2Interno.add(rbFonaudiologo);
        rbFonaudiologo.setBounds(170, 170, 140, 25);

        btnCancelar6.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnCancelar6.setForeground(new java.awt.Color(255, 255, 255));
        btnCancelar6.setText("CANCELAR");
        btnCancelar6.setBorderPainted(false);
        btnCancelar6.setContentAreaFilled(false);
        btnCancelar6.setFocusPainted(false);
        btnCancelar6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnCancelar6MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnCancelar6MouseExited(evt);
            }
        });
        btnCancelar6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelar6ActionPerformed(evt);
            }
        });
        pnlQuestionario2Interno.add(btnCancelar6);
        btnCancelar6.setBounds(498, 500, 120, 25);

        rbPsicologico.setBackground(new java.awt.Color(255, 255, 255));
        rbPsicologico.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        rbPsicologico.setForeground(new java.awt.Color(255, 255, 255));
        rbPsicologico.setText("PSICOLÓGICO");
        rbPsicologico.setContentAreaFilled(false);
        rbPsicologico.setFocusPainted(false);
        pnlQuestionario2Interno.add(rbPsicologico);
        rbPsicologico.setBounds(30, 170, 125, 25);

        btnFinalizar6.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnFinalizar6.setForeground(new java.awt.Color(255, 255, 255));
        btnFinalizar6.setText("ALTERAR E FINALIZAR");
        btnFinalizar6.setBorderPainted(false);
        btnFinalizar6.setContentAreaFilled(false);
        btnFinalizar6.setFocusPainted(false);
        btnFinalizar6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnFinalizar6MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnFinalizar6MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnFinalizar6MousePressed(evt);
            }
        });
        btnFinalizar6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFinalizar6ActionPerformed(evt);
            }
        });
        pnlQuestionario2Interno.add(btnFinalizar6);
        btnFinalizar6.setBounds(629, 500, 200, 25);

        rbTerapiaOcupacional.setBackground(new java.awt.Color(255, 255, 255));
        rbTerapiaOcupacional.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        rbTerapiaOcupacional.setForeground(new java.awt.Color(255, 255, 255));
        rbTerapiaOcupacional.setText("TERAPIA OCUPACIONAL");
        rbTerapiaOcupacional.setContentAreaFilled(false);
        rbTerapiaOcupacional.setFocusPainted(false);
        pnlQuestionario2Interno.add(rbTerapiaOcupacional);
        rbTerapiaOcupacional.setBounds(460, 170, 192, 25);

        lblAcompanhamentoEspecializado.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblAcompanhamentoEspecializado.setForeground(new java.awt.Color(255, 255, 255));
        lblAcompanhamentoEspecializado.setText("FAZ ACOMPANHAMENTO ESPECIALIZADO?");
        pnlQuestionario2Interno.add(lblAcompanhamentoEspecializado);
        lblAcompanhamentoEspecializado.setBounds(30, 140, 340, 17);

        lblFundo6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Fundo Geral.png"))); // NOI18N
        pnlQuestionario2Interno.add(lblFundo6);
        lblFundo6.setBounds(0, 0, 850, 570);

        javax.swing.GroupLayout pnlQuestionario2Layout = new javax.swing.GroupLayout(pnlQuestionario2);
        pnlQuestionario2.setLayout(pnlQuestionario2Layout);
        pnlQuestionario2Layout.setHorizontalGroup(
            pnlQuestionario2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlQuestionario2Layout.createSequentialGroup()
                .addComponent(pnlQuestionario2Interno, javax.swing.GroupLayout.PREFERRED_SIZE, 850, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        pnlQuestionario2Layout.setVerticalGroup(
            pnlQuestionario2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlQuestionario2Layout.createSequentialGroup()
                .addComponent(pnlQuestionario2Interno, javax.swing.GroupLayout.PREFERRED_SIZE, 570, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        tpCadastroPaciente.addTab("Questionário 2", pnlQuestionario2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(tpCadastroPaciente))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tpCadastroPaciente)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void rbSuperdotacaojRadioButton42ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbSuperdotacaojRadioButton42ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rbSuperdotacaojRadioButton42ActionPerformed

    private void rbPressaoAltajRadioButton59ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbPressaoAltajRadioButton59ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rbPressaoAltajRadioButton59ActionPerformed

    private void rbDiabetesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbDiabetesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rbDiabetesActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        // TODO add your handling code here:
        this.sair();
    }//GEN-LAST:event_formWindowClosing

    private void btnContinuar2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnContinuar2ActionPerformed
        // TODO add your handling code here:
        this.atualizarHistoriaPregressa();
        tpCadastroPaciente.setSelectedIndex(2);
    }//GEN-LAST:event_btnContinuar2ActionPerformed

    private void btnContinuar3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnContinuar3ActionPerformed
        // TODO add your handling code here: 
        this.atualizarHistoriaFamiliar();
        tpCadastroPaciente.setSelectedIndex(3);
    }//GEN-LAST:event_btnContinuar3ActionPerformed

    private void btnContinuar4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnContinuar4ActionPerformed
        // TODO add your handling code here:       
        this.atualizarHistoriaDoencaAtual();
        tpCadastroPaciente.setSelectedIndex(4);
    }//GEN-LAST:event_btnContinuar4ActionPerformed

    private void btnContinuar5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnContinuar5ActionPerformed
        // TODO add your handling code here:
        this.atualizarQuestionario1();
        tpCadastroPaciente.setSelectedIndex(5);
    }//GEN-LAST:event_btnContinuar5ActionPerformed

    private void btnFinalizar6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFinalizar6ActionPerformed
        // TODO add your handling code here:
        this.atualizarQuestionario2();
        this.dispose();
    }//GEN-LAST:event_btnFinalizar6ActionPerformed

    private void btnCancelar2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelar2ActionPerformed
        // TODO add your handling code here:
        this.sair();
    }//GEN-LAST:event_btnCancelar2ActionPerformed

    private void btnCancelar3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelar3ActionPerformed
        // TODO add your handling code here:
        this.sair();
    }//GEN-LAST:event_btnCancelar3ActionPerformed

    private void btnCancelar4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelar4ActionPerformed
        // TODO add your handling code here:
        this.sair();
    }//GEN-LAST:event_btnCancelar4ActionPerformed

    private void btnCancelar5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelar5ActionPerformed
        // TODO add your handling code here:
        this.sair();
    }//GEN-LAST:event_btnCancelar5ActionPerformed

    private void btnCancelar6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelar6ActionPerformed
        // TODO add your handling code here:
        this.sair();
    }//GEN-LAST:event_btnCancelar6ActionPerformed

    private void rbCirurgiasNaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbCirurgiasNaoActionPerformed
        // TODO add your handling code here:
        tfCirurgiasRealizadas.setEnabled(false);
    }//GEN-LAST:event_rbCirurgiasNaoActionPerformed

    private void rbCirurgiasSimActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbCirurgiasSimActionPerformed
        // TODO add your handling code here:
        tfCirurgiasRealizadas.setEnabled(true);
    }//GEN-LAST:event_rbCirurgiasSimActionPerformed

    private void rbAlergiaMedicamentosaNaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbAlergiaMedicamentosaNaoActionPerformed
        // TODO add your handling code here:
        tfAlergiaMedicamentosa.setEnabled(false);
    }//GEN-LAST:event_rbAlergiaMedicamentosaNaoActionPerformed

    private void rbAlergiaMedicamentosaSimActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbAlergiaMedicamentosaSimActionPerformed
        // TODO add your handling code here:
        tfAlergiaMedicamentosa.setEnabled(true);
    }//GEN-LAST:event_rbAlergiaMedicamentosaSimActionPerformed

    private void rbAlergiaAlimentarNaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbAlergiaAlimentarNaoActionPerformed
        // TODO add your handling code here:
        tfAlergiaAlimentar.setEnabled(false);
    }//GEN-LAST:event_rbAlergiaAlimentarNaoActionPerformed

    private void rbAlergiaAlimentarSimActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbAlergiaAlimentarSimActionPerformed
        // TODO add your handling code here:
        tfAlergiaAlimentar.setEnabled(true);
    }//GEN-LAST:event_rbAlergiaAlimentarSimActionPerformed

    private void btnContinuar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnContinuar1ActionPerformed
        // TODO add your handling code here:
        if (tfNome.getText().equals("") || tfDtNasc.getText().replace("/", "").trim().length() == 0) {
            JOptionPane.showMessageDialog(null, "Preencha os campos obrigatórios (campos com *)", "ERRO", JOptionPane.ERROR_MESSAGE);
        } else {
            this.atualizarIdentificacao();
            this.atualizarCartao();
            tpCadastroPaciente.setSelectedIndex(1);
        }
    }//GEN-LAST:event_btnContinuar1ActionPerformed

    private void btnCancelar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelar1ActionPerformed
        // TODO add your handling code here:
        this.sair();
    }//GEN-LAST:event_btnCancelar1ActionPerformed

    private void rbPlanoSaudeSimActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbPlanoSaudeSimActionPerformed
        // TODO add your handling code here:
        lblQualPlanoSaude.setEnabled(true);
        tfPlanoSaude.setEnabled(true);
        tfPlanoSaude.setText("");
    }//GEN-LAST:event_rbPlanoSaudeSimActionPerformed

    private void rbPlanoSaudeNaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbPlanoSaudeNaoActionPerformed
        // TODO add your handling code here:
        lblQualPlanoSaude.setEnabled(false);
        tfPlanoSaude.setEnabled(false);
        tfPlanoSaude.setText("Não possui");
    }//GEN-LAST:event_rbPlanoSaudeNaoActionPerformed

    private void rbParentesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbParentesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rbParentesActionPerformed

    private void rbHistFamiliarOutroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbHistFamiliarOutroActionPerformed
        // TODO add your handling code here:
        if (rbHistFamiliarOutro.isSelected()) {
            lblEspecificar.setEnabled(true);
            tfEspecificar.setEnabled(true);
        } else {
            lblEspecificar.setEnabled(false);
            tfEspecificar.setEnabled(false);
        }
    }//GEN-LAST:event_rbHistFamiliarOutroActionPerformed

    private void rbCronicaOutrosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbCronicaOutrosActionPerformed
        // TODO add your handling code here:
        if (rbCronicaOutros.isSelected()) {
            tfDoencaCronica.setEnabled(true);
        } else {
            tfDoencaCronica.setEnabled(false);
        }
    }//GEN-LAST:event_rbCronicaOutrosActionPerformed

    private void rbAcompanhamentoEspecializadoOutroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbAcompanhamentoEspecializadoOutroActionPerformed
        // TODO add your handling code here:
        if (rbAcompanhamentoEspecializadoOutro.isSelected()) {
            tfAcompanhamentoEspecializado.setEnabled(true);
        } else {
            tfAcompanhamentoEspecializado.setEnabled(false);
        }
    }//GEN-LAST:event_rbAcompanhamentoEspecializadoOutroActionPerformed

    private void btnContinuar1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnContinuar1MouseEntered
        // TODO add your handling code here:
        this.entraMouse(btnContinuar1);
    }//GEN-LAST:event_btnContinuar1MouseEntered

    private void btnContinuar1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnContinuar1MouseExited
        // TODO add your handling code here:
        this.saiMouse(btnContinuar1);
    }//GEN-LAST:event_btnContinuar1MouseExited

    private void btnCancelar1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCancelar1MouseEntered
        // TODO add your handling code here:
        this.entraMouse(btnCancelar1);
    }//GEN-LAST:event_btnCancelar1MouseEntered

    private void btnCancelar1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCancelar1MouseExited
        // TODO add your handling code here:
        this.saiMouse(btnCancelar1);
    }//GEN-LAST:event_btnCancelar1MouseExited

    private void btnContinuar2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnContinuar2MouseEntered
        // TODO add your handling code here:
        this.entraMouse(btnContinuar2);
    }//GEN-LAST:event_btnContinuar2MouseEntered

    private void btnContinuar2MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnContinuar2MouseExited
        // TODO add your handling code here:
        this.saiMouse(btnContinuar2);
    }//GEN-LAST:event_btnContinuar2MouseExited

    private void btnCancelar2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCancelar2MouseEntered
        // TODO add your handling code here:
        this.entraMouse(btnCancelar2);
    }//GEN-LAST:event_btnCancelar2MouseEntered

    private void btnCancelar2MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCancelar2MouseExited
        // TODO add your handling code here:
        this.saiMouse(btnCancelar2);
    }//GEN-LAST:event_btnCancelar2MouseExited

    private void btnContinuar3MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnContinuar3MouseEntered
        // TODO add your handling code here:
        this.entraMouse(btnContinuar3);
    }//GEN-LAST:event_btnContinuar3MouseEntered

    private void btnContinuar3MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnContinuar3MouseExited
        // TODO add your handling code here:
        this.saiMouse(btnContinuar3);
    }//GEN-LAST:event_btnContinuar3MouseExited

    private void btnCancelar3MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCancelar3MouseEntered
        // TODO add your handling code here:
        this.entraMouse(btnCancelar3);
    }//GEN-LAST:event_btnCancelar3MouseEntered

    private void btnCancelar3MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCancelar3MouseExited
        // TODO add your handling code here:
        this.saiMouse(btnCancelar3);
    }//GEN-LAST:event_btnCancelar3MouseExited

    private void btnContinuar4MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnContinuar4MouseEntered
        // TODO add your handling code here:
        this.entraMouse(btnContinuar4);
    }//GEN-LAST:event_btnContinuar4MouseEntered

    private void btnContinuar4MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnContinuar4MouseExited
        // TODO add your handling code here:
        this.saiMouse(btnContinuar4);
    }//GEN-LAST:event_btnContinuar4MouseExited

    private void btnCancelar4MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCancelar4MouseEntered
        // TODO add your handling code here:
        this.entraMouse(btnCancelar4);
    }//GEN-LAST:event_btnCancelar4MouseEntered

    private void btnCancelar4MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCancelar4MouseExited
        // TODO add your handling code here:
        this.saiMouse(btnCancelar4);
    }//GEN-LAST:event_btnCancelar4MouseExited

    private void btnContinuar5MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnContinuar5MouseEntered
        // TODO add your handling code here:
        this.entraMouse(btnContinuar5);
    }//GEN-LAST:event_btnContinuar5MouseEntered

    private void btnContinuar5MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnContinuar5MouseExited
        // TODO add your handling code here:
        this.saiMouse(btnContinuar5);
    }//GEN-LAST:event_btnContinuar5MouseExited

    private void btnCancelar5MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCancelar5MouseEntered
        // TODO add your handling code here:
        this.entraMouse(btnCancelar5);
    }//GEN-LAST:event_btnCancelar5MouseEntered

    private void btnCancelar5MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCancelar5MouseExited
        // TODO add your handling code here:
        this.saiMouse(btnCancelar5);
    }//GEN-LAST:event_btnCancelar5MouseExited

    private void btnFinalizar6MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnFinalizar6MouseEntered
        // TODO add your handling code here:
        this.entraMouse(btnFinalizar6);
    }//GEN-LAST:event_btnFinalizar6MouseEntered

    private void btnFinalizar6MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnFinalizar6MousePressed
        // TODO add your handling code here:

    }//GEN-LAST:event_btnFinalizar6MousePressed

    private void btnCancelar6MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCancelar6MouseEntered
        // TODO add your handling code here:
        this.entraMouse(btnCancelar6);
    }//GEN-LAST:event_btnCancelar6MouseEntered

    private void btnCancelar6MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCancelar6MouseExited
        // TODO add your handling code here:
        this.saiMouse(btnCancelar6);
    }//GEN-LAST:event_btnCancelar6MouseExited

    private void btnFinalizar6MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnFinalizar6MouseExited
        // TODO add your handling code here:
        this.saiMouse(btnFinalizar6);
    }//GEN-LAST:event_btnFinalizar6MouseExited

    private void rbSusNaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbSusNaoActionPerformed
        // TODO add your handling code here:
        tfNSus.setText("Nao possui");
        tfNSus.setEnabled(false);
    }//GEN-LAST:event_rbSusNaoActionPerformed

    private void rbSusSimActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbSusSimActionPerformed
        // TODO add your handling code here:
        tfNSus.setText("");
        tfNSus.setEnabled(true);
    }//GEN-LAST:event_rbSusSimActionPerformed

    private void tfRuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfRuaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfRuaActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TelaAlterarPaciente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaAlterarPaciente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaAlterarPaciente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaAlterarPaciente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaAlterarPaciente().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup bgAcompanhamento;
    private javax.swing.ButtonGroup bgAlergiaAlimentar;
    private javax.swing.ButtonGroup bgAlergiaMedicamentosa;
    private javax.swing.ButtonGroup bgCirurgiasRealizadas;
    private javax.swing.ButtonGroup bgMoradia;
    private javax.swing.ButtonGroup bgPlanoSaude;
    private javax.swing.ButtonGroup bgProtese;
    private javax.swing.ButtonGroup bgRegime;
    private javax.swing.ButtonGroup bgSexo;
    private javax.swing.ButtonGroup bgSus;
    private javax.swing.ButtonGroup bgVacinasAdolescencia;
    private javax.swing.ButtonGroup bgVacinasInfancia;
    private javax.swing.JButton btnCancelar1;
    private javax.swing.JButton btnCancelar2;
    private javax.swing.JButton btnCancelar3;
    private javax.swing.JButton btnCancelar4;
    private javax.swing.JButton btnCancelar5;
    private javax.swing.JButton btnCancelar6;
    private javax.swing.JButton btnContinuar1;
    private javax.swing.JButton btnContinuar2;
    private javax.swing.JButton btnContinuar3;
    private javax.swing.JButton btnContinuar4;
    private javax.swing.JButton btnContinuar5;
    private javax.swing.JButton btnFinalizar6;
    private javax.swing.JComboBox<String> cbCurso;
    private javax.swing.JLabel lblAcompanhamento;
    private javax.swing.JLabel lblAcompanhamentoEspecializado;
    private javax.swing.JLabel lblAlergiaAlimentar;
    private javax.swing.JLabel lblAlergiaMedicamentosa;
    private javax.swing.JLabel lblAltura;
    private javax.swing.JLabel lblAnotacoesRelevantes;
    private javax.swing.JLabel lblBairro;
    private javax.swing.JLabel lblCefaleia;
    private javax.swing.JLabel lblCidade;
    private javax.swing.JLabel lblCirurgias;
    private javax.swing.JLabel lblColica;
    private javax.swing.JLabel lblContatoEmergencia;
    private javax.swing.JLabel lblCpf;
    private javax.swing.JLabel lblCurso;
    private javax.swing.JLabel lblDesmaios;
    private javax.swing.JLabel lblDiarreia;
    private javax.swing.JLabel lblDoencaCronica;
    private javax.swing.JLabel lblDoencas;
    private javax.swing.JLabel lblDoencasContraidas;
    private javax.swing.JLabel lblDtNasc;
    private javax.swing.JLabel lblEpistaxe;
    private javax.swing.JLabel lblEspecificar;
    private javax.swing.JLabel lblFundo1;
    private javax.swing.JLabel lblFundo2;
    private javax.swing.JLabel lblFundo3;
    private javax.swing.JLabel lblFundo4;
    private javax.swing.JLabel lblFundo5;
    private javax.swing.JLabel lblFundo6;
    private javax.swing.JLabel lblIdPaciente;
    private javax.swing.JLabel lblMae;
    private javax.swing.JLabel lblMatricula;
    private javax.swing.JLabel lblMedicamentoContinuo;
    private javax.swing.JLabel lblMoradia;
    private javax.swing.JLabel lblNSus;
    private javax.swing.JLabel lblNecessidadeEspecifica;
    private javax.swing.JLabel lblNome;
    private javax.swing.JLabel lblNumero;
    private javax.swing.JLabel lblPai;
    private javax.swing.JLabel lblPeso;
    private javax.swing.JLabel lblPlanoSaude;
    private javax.swing.JLabel lblPressaoArterial;
    private javax.swing.JLabel lblProtese;
    private javax.swing.JLabel lblQualPlanoSaude;
    private javax.swing.JLabel lblRegime;
    private javax.swing.JLabel lblResponsavel;
    private javax.swing.JLabel lblRua;
    private javax.swing.JLabel lblSangue;
    private javax.swing.JLabel lblSexo;
    private javax.swing.JLabel lblSus;
    private javax.swing.JLabel lblTelMae;
    private javax.swing.JLabel lblTelPai;
    private javax.swing.JLabel lblTelResponsavel;
    private javax.swing.JLabel lblTelefone;
    private javax.swing.JLabel lblTurma;
    private javax.swing.JLabel lblUf;
    private javax.swing.JLabel lblVacinaFaltando;
    private javax.swing.JLabel lblVacinasAdolescencia;
    private javax.swing.JLabel lblVacinasInfancia;
    private javax.swing.JPanel pnlDoenca;
    private javax.swing.JPanel pnlDoencaAtualInterno;
    private javax.swing.JPanel pnlFamiliar;
    private javax.swing.JPanel pnlFamiliarInterno;
    private javax.swing.JPanel pnlIdentInterno;
    private javax.swing.JPanel pnlIdentificacao;
    private javax.swing.JPanel pnlPregressa;
    private javax.swing.JPanel pnlPregressaInterno;
    private javax.swing.JPanel pnlQuestionario1;
    private javax.swing.JPanel pnlQuestionario1Interno;
    private javax.swing.JPanel pnlQuestionario2;
    private javax.swing.JPanel pnlQuestionario2Interno;
    private javax.swing.JRadioButton rbAcompanhamentoEspecializadoOutro;
    private javax.swing.JRadioButton rbAcompanhamentoNao;
    private javax.swing.JRadioButton rbAcompanhamentoSim;
    private javax.swing.JRadioButton rbAlergiaAlimentarNao;
    private javax.swing.JRadioButton rbAlergiaAlimentarSim;
    private javax.swing.JRadioButton rbAlergiaMedicamentosaNao;
    private javax.swing.JRadioButton rbAlergiaMedicamentosaSim;
    private javax.swing.JRadioButton rbAsma;
    private javax.swing.JRadioButton rbAuditiva;
    private javax.swing.JRadioButton rbBronquite;
    private javax.swing.JRadioButton rbCancer;
    private javax.swing.JRadioButton rbCardiaco;
    private javax.swing.JRadioButton rbCardiopatia;
    private javax.swing.JRadioButton rbCartaoAdolescenciaCompleto;
    private javax.swing.JRadioButton rbCartaoAdolescenciaIncompleto;
    private javax.swing.JRadioButton rbCartaoInfanciaCompleto;
    private javax.swing.JRadioButton rbCartaoInfanciaIncompleto;
    private javax.swing.JRadioButton rbCatapora;
    private javax.swing.JRadioButton rbCaxumba;
    private javax.swing.JRadioButton rbCirurgiasNao;
    private javax.swing.JRadioButton rbCirurgiasSim;
    private javax.swing.JRadioButton rbConcentracao;
    private javax.swing.JRadioButton rbCronicaDiabetes;
    private javax.swing.JRadioButton rbCronicaOutros;
    private javax.swing.JRadioButton rbDengue;
    private javax.swing.JRadioButton rbDesenvolvimento;
    private javax.swing.JRadioButton rbDiabetes;
    private javax.swing.JRadioButton rbEscola;
    private javax.swing.JRadioButton rbEscrita;
    private javax.swing.JRadioButton rbExterno;
    private javax.swing.JRadioButton rbFeminino;
    private javax.swing.JRadioButton rbFisica;
    private javax.swing.JRadioButton rbFisioterapia;
    private javax.swing.JRadioButton rbFonaudiologo;
    private javax.swing.JRadioButton rbHepatite;
    private javax.swing.JRadioButton rbHipertensao;
    private javax.swing.JRadioButton rbHistFamiliarOutro;
    private javax.swing.JRadioButton rbInterno;
    private javax.swing.JRadioButton rbLeitura;
    private javax.swing.JRadioButton rbMasculino;
    private javax.swing.JRadioButton rbMeningite;
    private javax.swing.JRadioButton rbMental;
    private javax.swing.JRadioButton rbNenhumaNecessidade;
    private javax.swing.JRadioButton rbOftalmologico;
    private javax.swing.JRadioButton rbOutro;
    private javax.swing.JRadioButton rbPais;
    private javax.swing.JRadioButton rbParentes;
    private javax.swing.JRadioButton rbPlanoSaudeNao;
    private javax.swing.JRadioButton rbPlanoSaudeSim;
    private javax.swing.JRadioButton rbPneumonia;
    private javax.swing.JRadioButton rbPressaoAlta;
    private javax.swing.JRadioButton rbProblemaRenal;
    private javax.swing.JRadioButton rbProteseNao;
    private javax.swing.JRadioButton rbProteseSim;
    private javax.swing.JRadioButton rbPsicologico;
    private javax.swing.JRadioButton rbRenal;
    private javax.swing.JRadioButton rbRepublica;
    private javax.swing.JRadioButton rbRinite;
    private javax.swing.JRadioButton rbRubeola;
    private javax.swing.JRadioButton rbSarampo;
    private javax.swing.JRadioButton rbSemiInterno;
    private javax.swing.JRadioButton rbSozinho;
    private javax.swing.JRadioButton rbSuperdotacao;
    private javax.swing.JRadioButton rbSusNao;
    private javax.swing.JRadioButton rbSusSim;
    private javax.swing.JRadioButton rbTerapiaOcupacional;
    private javax.swing.JRadioButton rbVisual;
    private javax.swing.JTextField tfAcompanhamentoEspecializado;
    private javax.swing.JTextField tfAlergiaAlimentar;
    private javax.swing.JTextField tfAlergiaMedicamentosa;
    private javax.swing.JTextField tfAltura;
    private javax.swing.JTextField tfAnotacoesRelevantes;
    private javax.swing.JTextField tfBairro;
    private javax.swing.JTextField tfCefaleia;
    private javax.swing.JTextField tfCidade;
    private javax.swing.JTextField tfCirurgiasRealizadas;
    private javax.swing.JTextField tfColica;
    private javax.swing.JTextField tfContatoEmergencia;
    private javax.swing.JFormattedTextField tfCpf;
    private javax.swing.JTextField tfDesmaios;
    private javax.swing.JTextField tfDiarreia;
    private javax.swing.JTextField tfDoencaCronica;
    private javax.swing.JTextField tfDoencas;
    private javax.swing.JFormattedTextField tfDtNasc;
    private javax.swing.JTextField tfEpistaxe;
    private javax.swing.JTextField tfEspecificar;
    private javax.swing.JTextField tfIdPaciente;
    private javax.swing.JTextField tfMae;
    private javax.swing.JTextField tfMatricula;
    private javax.swing.JTextField tfMedicamentoContinuo;
    private javax.swing.JTextField tfNCasa;
    private javax.swing.JTextField tfNSus;
    private javax.swing.JTextField tfNome;
    private javax.swing.JTextField tfPai;
    private javax.swing.JTextField tfPeso;
    private javax.swing.JTextField tfPlanoSaude;
    private javax.swing.JTextField tfPressaoArterial;
    private javax.swing.JTextField tfResponsavel;
    private javax.swing.JTextField tfRua;
    private javax.swing.JTextField tfSangue;
    private javax.swing.JTextField tfTelMae;
    private javax.swing.JTextField tfTelPai;
    private javax.swing.JTextField tfTelResponsavel;
    private javax.swing.JTextField tfTelefone;
    private javax.swing.JTextField tfTurma;
    private javax.swing.JTextField tfUf;
    private javax.swing.JTextField tfVacinaFaltando;
    private javax.swing.JTabbedPane tpCadastroPaciente;
    // End of variables declaration//GEN-END:variables
}
