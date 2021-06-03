/*
 * Tela para o cadastro de pacientes (anamnese)
 */
package gerenciarpacientes;

import bancodedados.MysqlConnect;
import java.awt.Color;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import menssagensalerta.MinhasMenssagens;
import principal.TelaPrincipal;

/**
 *
 * @author Franciele Alves Barbosa e Rogério Costa Negro Rocha
 */
public final class PnlCadastroPaciente extends javax.swing.JPanel {

    Connection conn = null;
    public int controleInserir = 0;
    public String controleCpf = "";

    /**
     * Creates new form teste
     */
    public PnlCadastroPaciente() {
        initComponents();
        try {
            conn = MysqlConnect.connectDB();
            this.cursos();
        } catch (SQLException sqle) {
            JOptionPane.showMessageDialog(null, "Erro ao conectar com o Banco de Dados " /*+ ex.getMessage()*/, "ERRO", JOptionPane.ERROR_MESSAGE);
            MinhasMenssagens.chamarMenssagemErro(sqle.getMessage());
        }
    }

    public void sair() {
        //int op = JOptionPane.showConfirmDialog(null, "Deseja realmente sair?", "ATENÇÃO", JOptionPane.YES_OPTION);
        int op = MinhasMenssagens.chamarMenssagemOpcaoSair();
        if (op == JOptionPane.YES_OPTION) {
            TelaPrincipal.voltarHome();
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
                if (!"Servidor".equals(rs.getString("curso"))) {
                    cbCurso.addItem(rs.getString("curso"));
                }
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro: " + ex.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void botaoFinalizar() {

        if (tfNome.getText().equals("") || tfDtNasc.getText().replace("/", "").trim().length() == 0) {
            MinhasMenssagens.chamarMenssagemCamposObrigatorios();
        } else {
            this.inserirIdentificacao();
            this.inserirHistoriaPregressa();
            this.inserirHistoriaFamiliar();
            this.inserirHistoriaDoencaAtual();
            this.inserirQuestionario1();
            this.inserirQuestionario2();
            MinhasMenssagens.chamarMenssagemSucesso("Cadastrado com sucesso.");
            TelaPrincipal.voltarHome();
        }
    }

    public void inserirIdentificacao() {
        PreparedStatement pstmt = null;
        ResultSet retornoDoID = null;

        String qry = "INSERT INTO pacientes(cpf, nome, dtNascimento, matricula, curso, turma, rua, numero, bairro, municipio, uf,"
                + " telefone, nomeMae, telefoneMae, nomePai, telefonePai, nomeResponsavel, telefoneResponsavel, sexo, peso, altura,"
                + "tipoSanguineo, mora, regime, planoSaude, cartaoSus) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

        try {
            pstmt = conn.prepareStatement(qry, Statement.RETURN_GENERATED_KEYS);
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

            pstmt.executeUpdate();
            //JOptionPane.showMessageDialog(null, "Salvo com sucesso!");

            //Pegar o ID
            retornoDoID = pstmt.getGeneratedKeys();
            retornoDoID.next();
            Integer idInserido = retornoDoID.getInt(1); //Pronto você tem o ID inserido nesse insert.
            tfIdPaciente.setText(idInserido.toString());

            this.controleInserir++;

            this.inserirCartao();
        } catch (SQLException ex) {
            //JOptionPane.showMessageDialog(null, "Erro ao salvar: " + ex.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
            MinhasMenssagens.chamarMenssagemErro(ex.getMessage());
        } catch (NullPointerException px) {
            //JOptionPane.showMessageDialog(null, "Erro ao salvar: " + ex.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
            MinhasMenssagens.chamarMenssagemErro("Registre cursos no sistema!");
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

            //JOptionPane.showMessageDialog(null, "Atualizado com sucesso!");
        } catch (SQLException ex) {
            //JOptionPane.showMessageDialog(null, "Erro ao salvar: " + ex.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
            //JOptionPane.showMessageDialog(null, "CPF existente localizado", "ERRO", JOptionPane.ERROR_MESSAGE);
            MinhasMenssagens.chamarMenssagemErro("Este CPF já foi cadastrado previamente.");
        }
    }

    public void inserirHistoriaPregressa() {

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

            //JOptionPane.showMessageDialog(null, "Salvo com sucesso!");
        } catch (SQLException ex) {
            //JOptionPane.showMessageDialog(null, "Erro ao salvar aqui: " + ex.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
            MinhasMenssagens.chamarMenssagemErro(ex.getMessage());
        }
    }

    public void inserirHistoriaFamiliar() {

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

            //JOptionPane.showMessageDialog(null, "Salvo com sucesso!");
        } catch (SQLException ex) {
            //JOptionPane.showMessageDialog(null, "Erro ao salvar: " + ex.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
            MinhasMenssagens.chamarMenssagemErro(ex.getMessage());
        }
    }

    public void inserirHistoriaDoencaAtual() {

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

            //JOptionPane.showMessageDialog(null, "Salvo com sucesso!");
        } catch (SQLException ex) {
            //JOptionPane.showMessageDialog(null, "Erro ao salvar: " + ex.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
            MinhasMenssagens.chamarMenssagemErro(ex.getMessage());
        }
    }

    public void inserirQuestionario1() {

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

            //JOptionPane.showMessageDialog(null, "Salvo com sucesso!");
        } catch (SQLException ex) {
            //JOptionPane.showMessageDialog(null, "Erro ao salvar: " + ex.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
            MinhasMenssagens.chamarMenssagemErro(ex.getMessage());
        }
    }

    public void inserirQuestionario2() {

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

            //JOptionPane.showMessageDialog(null, "Salvo com sucesso!");

        } catch (SQLException ex) {
             MinhasMenssagens.chamarMenssagemErro(ex.getMessage());
        }
    }

    public void inserirCartao() {
        PreparedStatement pstmt = null;
        String qry = "INSERT INTO CartaoVacina(idPaciente, cpf, nome, dn, tipoSanguineo,rua, numero, bairro, municipio, uf, telefone)"
                + " VALUES(?,?,?,?,?,?,?,?,?,?,?)";
        try {
            pstmt = conn.prepareStatement(qry);
            pstmt.setString(1, tfIdPaciente.getText());
            pstmt.setString(2, tfCpf.getText());
            pstmt.setString(3, tfNome.getText());
            pstmt.setString(4, tfDtNasc.getText());
            pstmt.setString(5, tfSangue.getText());
            pstmt.setString(6, tfRua.getText());
            pstmt.setString(7, tfNCasa.getText());
            pstmt.setString(8, tfBairro.getText());
            pstmt.setString(9, tfCidade.getText());
            pstmt.setString(10, tfUf.getText());
            pstmt.setString(11, tfTelefone.getText());

            pstmt.executeUpdate();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao criar cartão\nEntre em contato com os desenvolvedores ", "ERRO", JOptionPane.ERROR_MESSAGE);
            MinhasMenssagens.chamarMenssagemErro("Erro ao criar cartão de vacinação. \nEntre em contato com os responsáveis!");
        }
    }

    public void atualizarCartao() {
        PreparedStatement pstmt = null;
        String qry = "UPDATE CartaoVacina SET cpf= ?, nome= ?, dn= ?,"
                + " tipoSanguineo= ?,rua= ?, numero= ?, bairro= ?, municipio= ?,"
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
            //JOptionPane.showMessageDialog(null, "Erro ao atualizar cartão: " + ex.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
            MinhasMenssagens.chamarMenssagemErro("Erro ao atualizar o cartão de vacinação.\n"+ex.getMessage());
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
        lblCidade = new javax.swing.JLabel();
        lblBairro = new javax.swing.JLabel();
        lblNumero = new javax.swing.JLabel();
        lblRua = new javax.swing.JLabel();
        lblUf = new javax.swing.JLabel();
        tfRua = new javax.swing.JTextField();
        tfNCasa = new javax.swing.JTextField();
        tfBairro = new javax.swing.JTextField();
        tfCidade = new javax.swing.JTextField();
        tfUf = new javax.swing.JTextField();
        tfTelefone = new javax.swing.JTextField();
        lblTelefone = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();
        jSeparator5 = new javax.swing.JSeparator();
        jSeparator6 = new javax.swing.JSeparator();
        jSeparator7 = new javax.swing.JSeparator();
        jSeparator8 = new javax.swing.JSeparator();
        jSeparator9 = new javax.swing.JSeparator();
        sSeparador10 = new javax.swing.JSeparator();
        sSeparador11 = new javax.swing.JSeparator();
        jSeparator10 = new javax.swing.JSeparator();
        sSeparador12 = new javax.swing.JSeparator();
        sSeparador13 = new javax.swing.JSeparator();
        sSeparador14 = new javax.swing.JSeparator();
        sSeparador15 = new javax.swing.JSeparator();
        sSeparador16 = new javax.swing.JSeparator();
        sSeparador17 = new javax.swing.JSeparator();
        sSeparador18 = new javax.swing.JSeparator();
        sSeparador19 = new javax.swing.JSeparator();
        sSeparador20 = new javax.swing.JSeparator();
        sSeparador21 = new javax.swing.JSeparator();
        sSeparador22 = new javax.swing.JSeparator();
        btnFinalizar1 = new javax.swing.JButton();
        jSeparator11 = new javax.swing.JSeparator();
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
        sSeparador23 = new javax.swing.JSeparator();
        sSeparador24 = new javax.swing.JSeparator();
        sSeparador25 = new javax.swing.JSeparator();
        sSeparador26 = new javax.swing.JSeparator();
        sSeparador27 = new javax.swing.JSeparator();
        btnFinalizar2 = new javax.swing.JButton();
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
        sSeparador28 = new javax.swing.JSeparator();
        btnFinalizar3 = new javax.swing.JButton();
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
        sSeparador29 = new javax.swing.JSeparator();
        btnFinalizar4 = new javax.swing.JButton();
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
        btnFinalizar5 = new javax.swing.JButton();
        sSeparador30 = new javax.swing.JSeparator();
        sSeparador31 = new javax.swing.JSeparator();
        sSeparador32 = new javax.swing.JSeparator();
        sSeparador33 = new javax.swing.JSeparator();
        sSeparador34 = new javax.swing.JSeparator();
        sSeparador35 = new javax.swing.JSeparator();
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
        rbTerapiaOcupacional = new javax.swing.JRadioButton();
        lblAcompanhamentoEspecializado = new javax.swing.JLabel();
        btnFinalizar6 = new javax.swing.JButton();
        sSeparador36 = new javax.swing.JSeparator();
        sSeparador37 = new javax.swing.JSeparator();
        sSeparador38 = new javax.swing.JSeparator();
        sSeparador39 = new javax.swing.JSeparator();

        setBackground(new java.awt.Color(255, 255, 255));
        setPreferredSize(new java.awt.Dimension(1000, 550));

        tpCadastroPaciente.setBackground(new java.awt.Color(255, 255, 255));
        tpCadastroPaciente.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        tpCadastroPaciente.setPreferredSize(new java.awt.Dimension(1000, 550));

        pnlIdentificacao.setBackground(new java.awt.Color(255, 255, 255));
        pnlIdentificacao.setPreferredSize(new java.awt.Dimension(1000, 519));

        pnlIdentInterno.setBackground(new java.awt.Color(255, 255, 255));
        pnlIdentInterno.setPreferredSize(new java.awt.Dimension(1000, 497));

        lblResponsavel.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        lblResponsavel.setForeground(new java.awt.Color(102, 102, 102));
        lblResponsavel.setText("RESPONSÁVEL EM SALINAS");

        tfPai.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        tfPai.setForeground(new java.awt.Color(102, 102, 102));
        tfPai.setBorder(null);
        tfPai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfPaiActionPerformed(evt);
            }
        });

        tfResponsavel.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        tfResponsavel.setForeground(new java.awt.Color(102, 102, 102));
        tfResponsavel.setBorder(null);

        lblTelPai.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        lblTelPai.setForeground(new java.awt.Color(102, 102, 102));
        lblTelPai.setText("TELEFONE");

        lblTelResponsavel.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        lblTelResponsavel.setForeground(new java.awt.Color(102, 102, 102));
        lblTelResponsavel.setText("TELEFONE");

        tfTelResponsavel.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        tfTelResponsavel.setForeground(new java.awt.Color(102, 102, 102));
        tfTelResponsavel.setBorder(null);

        tfTelPai.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        tfTelPai.setForeground(new java.awt.Color(102, 102, 102));
        tfTelPai.setBorder(null);

        lblSexo.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        lblSexo.setForeground(new java.awt.Color(102, 102, 102));
        lblSexo.setText("SEXO");

        rbFeminino.setBackground(new java.awt.Color(255, 255, 255));
        bgSexo.add(rbFeminino);
        rbFeminino.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        rbFeminino.setForeground(new java.awt.Color(102, 102, 102));
        rbFeminino.setSelected(true);
        rbFeminino.setText("F");
        rbFeminino.setContentAreaFilled(false);
        rbFeminino.setFocusPainted(false);

        rbMasculino.setBackground(new java.awt.Color(255, 255, 255));
        bgSexo.add(rbMasculino);
        rbMasculino.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        rbMasculino.setForeground(new java.awt.Color(102, 102, 102));
        rbMasculino.setText("M");
        rbMasculino.setContentAreaFilled(false);
        rbMasculino.setFocusPainted(false);

        lblPeso.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        lblPeso.setForeground(new java.awt.Color(102, 102, 102));
        lblPeso.setText("PESO");

        tfPeso.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        tfPeso.setForeground(new java.awt.Color(102, 102, 102));
        tfPeso.setBorder(null);

        lblAltura.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        lblAltura.setForeground(new java.awt.Color(102, 102, 102));
        lblAltura.setText("ALTURA");

        tfAltura.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        tfAltura.setForeground(new java.awt.Color(102, 102, 102));
        tfAltura.setBorder(null);

        lblSangue.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        lblSangue.setForeground(new java.awt.Color(102, 102, 102));
        lblSangue.setText("TIPO SANGUÍNEO");

        tfSangue.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        tfSangue.setForeground(new java.awt.Color(102, 102, 102));
        tfSangue.setBorder(null);

        lblMoradia.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        lblMoradia.setForeground(new java.awt.Color(102, 102, 102));
        lblMoradia.setText("MORADIA");

        rbPais.setBackground(new java.awt.Color(255, 255, 255));
        bgMoradia.add(rbPais);
        rbPais.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        rbPais.setForeground(new java.awt.Color(102, 102, 102));
        rbPais.setSelected(true);
        rbPais.setText("COM OS PAIS");
        rbPais.setContentAreaFilled(false);
        rbPais.setFocusPainted(false);

        rbEscola.setBackground(new java.awt.Color(255, 255, 255));
        bgMoradia.add(rbEscola);
        rbEscola.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        rbEscola.setForeground(new java.awt.Color(102, 102, 102));
        rbEscola.setText("ESCOLA");
        rbEscola.setContentAreaFilled(false);
        rbEscola.setFocusPainted(false);

        rbParentes.setBackground(new java.awt.Color(255, 255, 255));
        bgMoradia.add(rbParentes);
        rbParentes.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        rbParentes.setForeground(new java.awt.Color(102, 102, 102));
        rbParentes.setText("PARENTES");
        rbParentes.setContentAreaFilled(false);
        rbParentes.setFocusPainted(false);
        rbParentes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbParentesActionPerformed(evt);
            }
        });

        rbRepublica.setBackground(new java.awt.Color(255, 255, 255));
        bgMoradia.add(rbRepublica);
        rbRepublica.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        rbRepublica.setForeground(new java.awt.Color(102, 102, 102));
        rbRepublica.setText("REPÚBLICA");
        rbRepublica.setContentAreaFilled(false);
        rbRepublica.setFocusPainted(false);

        rbSozinho.setBackground(new java.awt.Color(255, 255, 255));
        bgMoradia.add(rbSozinho);
        rbSozinho.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        rbSozinho.setForeground(new java.awt.Color(102, 102, 102));
        rbSozinho.setText("SOZINHO");
        rbSozinho.setContentAreaFilled(false);
        rbSozinho.setFocusPainted(false);

        rbOutro.setBackground(new java.awt.Color(255, 255, 255));
        bgMoradia.add(rbOutro);
        rbOutro.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        rbOutro.setForeground(new java.awt.Color(102, 102, 102));
        rbOutro.setText("OUTRO");
        rbOutro.setContentAreaFilled(false);
        rbOutro.setFocusPainted(false);

        lblRegime.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        lblRegime.setForeground(new java.awt.Color(102, 102, 102));
        lblRegime.setText("REGIME");

        rbExterno.setBackground(new java.awt.Color(255, 255, 255));
        bgRegime.add(rbExterno);
        rbExterno.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        rbExterno.setForeground(new java.awt.Color(102, 102, 102));
        rbExterno.setSelected(true);
        rbExterno.setText("EXTERNO");
        rbExterno.setContentAreaFilled(false);
        rbExterno.setFocusPainted(false);

        rbInterno.setBackground(new java.awt.Color(255, 255, 255));
        bgRegime.add(rbInterno);
        rbInterno.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        rbInterno.setForeground(new java.awt.Color(102, 102, 102));
        rbInterno.setText("INTERNO");
        rbInterno.setContentAreaFilled(false);
        rbInterno.setFocusPainted(false);

        rbSemiInterno.setBackground(new java.awt.Color(255, 255, 255));
        bgRegime.add(rbSemiInterno);
        rbSemiInterno.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        rbSemiInterno.setForeground(new java.awt.Color(102, 102, 102));
        rbSemiInterno.setText("SEMI-INTERNO");
        rbSemiInterno.setContentAreaFilled(false);
        rbSemiInterno.setFocusPainted(false);

        lblPlanoSaude.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        lblPlanoSaude.setForeground(new java.awt.Color(102, 102, 102));
        lblPlanoSaude.setText("TEM PLANO DE SAÚDE");

        rbPlanoSaudeNao.setBackground(new java.awt.Color(255, 255, 255));
        bgPlanoSaude.add(rbPlanoSaudeNao);
        rbPlanoSaudeNao.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        rbPlanoSaudeNao.setForeground(new java.awt.Color(102, 102, 102));
        rbPlanoSaudeNao.setSelected(true);
        rbPlanoSaudeNao.setText("NÃO");
        rbPlanoSaudeNao.setContentAreaFilled(false);
        rbPlanoSaudeNao.setFocusPainted(false);
        rbPlanoSaudeNao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbPlanoSaudeNaoActionPerformed(evt);
            }
        });

        rbPlanoSaudeSim.setBackground(new java.awt.Color(255, 255, 255));
        bgPlanoSaude.add(rbPlanoSaudeSim);
        rbPlanoSaudeSim.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        rbPlanoSaudeSim.setForeground(new java.awt.Color(102, 102, 102));
        rbPlanoSaudeSim.setText("SIM");
        rbPlanoSaudeSim.setContentAreaFilled(false);
        rbPlanoSaudeSim.setFocusPainted(false);
        rbPlanoSaudeSim.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbPlanoSaudeSimActionPerformed(evt);
            }
        });

        lblQualPlanoSaude.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        lblQualPlanoSaude.setForeground(new java.awt.Color(102, 102, 102));
        lblQualPlanoSaude.setText("QUAL ?");

        tfPlanoSaude.setBackground(new java.awt.Color(153, 153, 153));
        tfPlanoSaude.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        tfPlanoSaude.setForeground(new java.awt.Color(255, 255, 255));

        lblSus.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        lblSus.setForeground(new java.awt.Color(102, 102, 102));
        lblSus.setText("TEM CARTÃO SUS");

        rbSusNao.setBackground(new java.awt.Color(255, 255, 255));
        bgSus.add(rbSusNao);
        rbSusNao.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        rbSusNao.setForeground(new java.awt.Color(102, 102, 102));
        rbSusNao.setSelected(true);
        rbSusNao.setText("NÃO");
        rbSusNao.setContentAreaFilled(false);
        rbSusNao.setFocusPainted(false);
        rbSusNao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbSusNaoActionPerformed(evt);
            }
        });

        lblCurso.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        lblCurso.setForeground(new java.awt.Color(102, 102, 102));
        lblCurso.setText("CURSO");

        rbSusSim.setBackground(new java.awt.Color(255, 255, 255));
        bgSus.add(rbSusSim);
        rbSusSim.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        rbSusSim.setForeground(new java.awt.Color(102, 102, 102));
        rbSusSim.setText("SIM");
        rbSusSim.setContentAreaFilled(false);
        rbSusSim.setFocusPainted(false);
        rbSusSim.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbSusSimActionPerformed(evt);
            }
        });

        lblTurma.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        lblTurma.setForeground(new java.awt.Color(102, 102, 102));
        lblTurma.setText("TURMA");

        btnCancelar1.setBackground(new java.awt.Color(102, 102, 102));
        btnCancelar1.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btnCancelar1.setForeground(new java.awt.Color(102, 102, 102));
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

        tfTurma.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        tfTurma.setForeground(new java.awt.Color(102, 102, 102));
        tfTurma.setBorder(null);

        btnContinuar1.setBackground(new java.awt.Color(102, 102, 102));
        btnContinuar1.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btnContinuar1.setForeground(new java.awt.Color(102, 102, 102));
        btnContinuar1.setText("CONTINUAR");
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

        cbCurso.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        cbCurso.setForeground(new java.awt.Color(102, 102, 102));
        cbCurso.setBorder(null);

        lblMae.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        lblMae.setForeground(new java.awt.Color(102, 102, 102));
        lblMae.setText("NOME DA MÃE");

        tfMae.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        tfMae.setForeground(new java.awt.Color(102, 102, 102));
        tfMae.setBorder(null);

        lblTelMae.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        lblTelMae.setForeground(new java.awt.Color(102, 102, 102));
        lblTelMae.setText("TELEFONE");

        tfTelMae.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        tfTelMae.setForeground(new java.awt.Color(102, 102, 102));
        tfTelMae.setBorder(null);

        lblPai.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        lblPai.setForeground(new java.awt.Color(102, 102, 102));
        lblPai.setText("NOME DO PAI");

        lblNome.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        lblNome.setForeground(new java.awt.Color(102, 102, 102));
        lblNome.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblNome.setText("NOME *");

        tfNome.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        tfNome.setForeground(new java.awt.Color(102, 102, 102));
        tfNome.setBorder(null);
        tfNome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfNomeActionPerformed(evt);
            }
        });

        lblDtNasc.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        lblDtNasc.setForeground(new java.awt.Color(102, 102, 102));
        lblDtNasc.setText("DATA NASCIMENTO *");

        tfDtNasc.setBorder(null);
        tfDtNasc.setForeground(new java.awt.Color(102, 102, 102));
        try {
            tfDtNasc.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        tfDtNasc.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N

        tfMatricula.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        tfMatricula.setForeground(new java.awt.Color(102, 102, 102));
        tfMatricula.setBorder(null);

        lblMatricula.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        lblMatricula.setForeground(new java.awt.Color(102, 102, 102));
        lblMatricula.setText("Nº MATRÍCULA");

        tfCpf.setBorder(null);
        tfCpf.setForeground(new java.awt.Color(102, 102, 102));
        try {
            tfCpf.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###.###.###-##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        tfCpf.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N

        lblCpf.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        lblCpf.setForeground(new java.awt.Color(102, 102, 102));
        lblCpf.setText("CPF ");

        lblNSus.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        lblNSus.setForeground(new java.awt.Color(102, 102, 102));
        lblNSus.setText("Nº");

        tfNSus.setBackground(new java.awt.Color(153, 153, 153));
        tfNSus.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        tfNSus.setForeground(new java.awt.Color(255, 255, 255));

        lblIdPaciente.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblIdPaciente.setForeground(new java.awt.Color(102, 102, 102));
        lblIdPaciente.setText("ID PACIENTE");

        tfIdPaciente.setEditable(false);
        tfIdPaciente.setBackground(new java.awt.Color(153, 153, 153));
        tfIdPaciente.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        tfIdPaciente.setForeground(new java.awt.Color(255, 255, 255));

        lblCidade.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        lblCidade.setForeground(new java.awt.Color(102, 102, 102));
        lblCidade.setText("CIDADE");

        lblBairro.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        lblBairro.setForeground(new java.awt.Color(102, 102, 102));
        lblBairro.setText("BAIRRO");

        lblNumero.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        lblNumero.setForeground(new java.awt.Color(102, 102, 102));
        lblNumero.setText("Nº");

        lblRua.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        lblRua.setForeground(new java.awt.Color(102, 102, 102));
        lblRua.setText("RUA");

        lblUf.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        lblUf.setForeground(new java.awt.Color(102, 102, 102));
        lblUf.setText("UF");

        tfRua.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        tfRua.setForeground(new java.awt.Color(102, 102, 102));
        tfRua.setBorder(null);
        tfRua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfRuaActionPerformed(evt);
            }
        });

        tfNCasa.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        tfNCasa.setForeground(new java.awt.Color(102, 102, 102));
        tfNCasa.setBorder(null);

        tfBairro.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        tfBairro.setForeground(new java.awt.Color(102, 102, 102));
        tfBairro.setBorder(null);

        tfCidade.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        tfCidade.setForeground(new java.awt.Color(102, 102, 102));
        tfCidade.setBorder(null);

        tfUf.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        tfUf.setForeground(new java.awt.Color(102, 102, 102));
        tfUf.setBorder(null);

        tfTelefone.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        tfTelefone.setForeground(new java.awt.Color(102, 102, 102));
        tfTelefone.setBorder(null);

        lblTelefone.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        lblTelefone.setForeground(new java.awt.Color(102, 102, 102));
        lblTelefone.setText("TELEFONE");

        jSeparator1.setForeground(new java.awt.Color(51, 51, 51));

        jSeparator2.setForeground(new java.awt.Color(51, 51, 51));

        jSeparator3.setForeground(new java.awt.Color(51, 51, 51));

        jSeparator5.setForeground(new java.awt.Color(51, 51, 51));

        jSeparator6.setForeground(new java.awt.Color(51, 51, 51));

        jSeparator7.setForeground(new java.awt.Color(51, 51, 51));

        jSeparator8.setForeground(new java.awt.Color(51, 51, 51));

        jSeparator9.setForeground(new java.awt.Color(51, 51, 51));

        sSeparador10.setForeground(new java.awt.Color(51, 51, 51));

        sSeparador11.setForeground(new java.awt.Color(51, 51, 51));

        jSeparator10.setForeground(new java.awt.Color(51, 51, 51));

        sSeparador12.setForeground(new java.awt.Color(51, 51, 51));

        sSeparador13.setForeground(new java.awt.Color(51, 51, 51));

        sSeparador14.setForeground(new java.awt.Color(51, 51, 51));

        sSeparador15.setForeground(new java.awt.Color(51, 51, 51));

        sSeparador16.setForeground(new java.awt.Color(51, 51, 51));

        sSeparador17.setForeground(new java.awt.Color(51, 51, 51));

        sSeparador18.setForeground(new java.awt.Color(51, 51, 51));

        sSeparador19.setForeground(new java.awt.Color(51, 51, 51));

        sSeparador20.setForeground(new java.awt.Color(51, 51, 51));

        sSeparador21.setForeground(new java.awt.Color(51, 51, 51));

        sSeparador22.setForeground(new java.awt.Color(51, 51, 51));

        btnFinalizar1.setBackground(new java.awt.Color(102, 102, 102));
        btnFinalizar1.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btnFinalizar1.setForeground(new java.awt.Color(102, 102, 102));
        btnFinalizar1.setText("FINALIZAR");
        btnFinalizar1.setBorderPainted(false);
        btnFinalizar1.setContentAreaFilled(false);
        btnFinalizar1.setFocusPainted(false);
        btnFinalizar1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnFinalizar1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnFinalizar1MouseExited(evt);
            }
        });
        btnFinalizar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFinalizar1ActionPerformed(evt);
            }
        });

        jSeparator11.setForeground(new java.awt.Color(51, 51, 51));

        javax.swing.GroupLayout pnlIdentInternoLayout = new javax.swing.GroupLayout(pnlIdentInterno);
        pnlIdentInterno.setLayout(pnlIdentInternoLayout);
        pnlIdentInternoLayout.setHorizontalGroup(
            pnlIdentInternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlIdentInternoLayout.createSequentialGroup()
                .addGroup(pnlIdentInternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlIdentInternoLayout.createSequentialGroup()
                        .addGroup(pnlIdentInternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlIdentInternoLayout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addGroup(pnlIdentInternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(lblNome, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(tfNome, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(pnlIdentInternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(lblDtNasc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(tfDtNasc)
                                    .addComponent(jSeparator11))
                                .addGap(18, 18, 18)
                                .addGroup(pnlIdentInternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(tfCpf, javax.swing.GroupLayout.DEFAULT_SIZE, 101, Short.MAX_VALUE)
                                    .addComponent(jSeparator2, javax.swing.GroupLayout.DEFAULT_SIZE, 101, Short.MAX_VALUE)
                                    .addComponent(lblCpf, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addGroup(pnlIdentInternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(lblMatricula, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(tfMatricula)
                                    .addComponent(jSeparator5))
                                .addGap(18, 18, 18)
                                .addGroup(pnlIdentInternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(tfTelefone)
                                    .addComponent(lblTelefone, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
                                    .addComponent(jSeparator3)))
                            .addGroup(pnlIdentInternoLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(lblCurso, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(lblTurma, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pnlIdentInternoLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(pnlIdentInternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(pnlIdentInternoLayout.createSequentialGroup()
                                        .addGroup(pnlIdentInternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(pnlIdentInternoLayout.createSequentialGroup()
                                                .addGroup(pnlIdentInternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                    .addComponent(lblSexo, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlIdentInternoLayout.createSequentialGroup()
                                                        .addComponent(cbCurso, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addGap(18, 18, 18)
                                                        .addComponent(tfTurma, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addGap(18, 18, 18)
                                                        .addComponent(rbFeminino)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                        .addComponent(rbMasculino)))
                                                .addGap(18, 18, 18)
                                                .addGroup(pnlIdentInternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                    .addGroup(pnlIdentInternoLayout.createSequentialGroup()
                                                        .addGroup(pnlIdentInternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                            .addComponent(lblPeso, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                            .addComponent(tfPeso, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE))
                                                        .addGap(18, 18, 18)
                                                        .addGroup(pnlIdentInternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                            .addComponent(lblAltura, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                            .addComponent(tfAltura)))
                                                    .addGroup(pnlIdentInternoLayout.createSequentialGroup()
                                                        .addComponent(sSeparador18, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addGap(18, 18, 18)
                                                        .addComponent(sSeparador19))))
                                            .addGroup(pnlIdentInternoLayout.createSequentialGroup()
                                                .addComponent(jSeparator10, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGap(18, 18, 18)
                                        .addGroup(pnlIdentInternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(lblSangue)
                                            .addComponent(tfSangue, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(sSeparador20, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(pnlIdentInternoLayout.createSequentialGroup()
                                        .addGroup(pnlIdentInternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addGroup(pnlIdentInternoLayout.createSequentialGroup()
                                                .addGroup(pnlIdentInternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                    .addGroup(pnlIdentInternoLayout.createSequentialGroup()
                                                        .addComponent(lblRua, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addGap(18, 18, 18)
                                                        .addComponent(lblNumero, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlIdentInternoLayout.createSequentialGroup()
                                                        .addComponent(tfRua, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addGap(18, 18, 18)
                                                        .addComponent(tfNCasa, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                .addGap(18, 18, 18)
                                                .addComponent(lblBairro, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(lblCidade, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlIdentInternoLayout.createSequentialGroup()
                                                .addComponent(jSeparator7, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGroup(pnlIdentInternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addGroup(pnlIdentInternoLayout.createSequentialGroup()
                                                        .addGap(18, 18, 18)
                                                        .addComponent(jSeparator8, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addGap(18, 18, 18)
                                                        .addComponent(jSeparator9, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addGap(18, 18, 18)
                                                        .addComponent(sSeparador10, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                    .addGroup(pnlIdentInternoLayout.createSequentialGroup()
                                                        .addGap(76, 76, 76)
                                                        .addComponent(tfBairro, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addGap(18, 18, 18)
                                                        .addComponent(tfCidade, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                                        .addGap(18, 18, 18)
                                        .addGroup(pnlIdentInternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(lblUf, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(tfUf, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(sSeparador11, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(20, 20, 20)
                                        .addGroup(pnlIdentInternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(lblPlanoSaude)
                                            .addGroup(pnlIdentInternoLayout.createSequentialGroup()
                                                .addComponent(rbPlanoSaudeNao)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(rbPlanoSaudeSim)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(lblQualPlanoSaude)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addGroup(pnlIdentInternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(sSeparador21, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(tfPlanoSaude, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                                    .addComponent(lblMoradia)
                                    .addGroup(pnlIdentInternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlIdentInternoLayout.createSequentialGroup()
                                            .addComponent(sSeparador14, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(18, 18, 18)
                                            .addComponent(sSeparador17, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(sSeparador22, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlIdentInternoLayout.createSequentialGroup()
                                            .addGroup(pnlIdentInternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addComponent(tfResponsavel, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(lblResponsavel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGap(18, 18, 18)
                                            .addGroup(pnlIdentInternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(tfTelResponsavel, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                                                .addComponent(lblTelResponsavel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                            .addGap(18, 18, 18)
                                            .addGroup(pnlIdentInternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addGroup(pnlIdentInternoLayout.createSequentialGroup()
                                                    .addComponent(rbSusNao)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                    .addComponent(rbSusSim)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                    .addComponent(lblNSus, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                    .addComponent(tfNSus, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addComponent(lblSus, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                                    .addGroup(pnlIdentInternoLayout.createSequentialGroup()
                                        .addGroup(pnlIdentInternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(sSeparador12)
                                            .addComponent(tfMae, javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(lblMae, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 208, Short.MAX_VALUE))
                                        .addGap(18, 18, 18)
                                        .addGroup(pnlIdentInternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(sSeparador13, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                                            .addComponent(tfTelMae, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                                            .addComponent(lblTelMae, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGap(18, 18, 18)
                                        .addGroup(pnlIdentInternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(sSeparador16, javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(tfPai, javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(pnlIdentInternoLayout.createSequentialGroup()
                                                .addComponent(lblPai, javax.swing.GroupLayout.DEFAULT_SIZE, 208, Short.MAX_VALUE)
                                                .addGap(2, 2, 2)))
                                        .addGap(18, 18, 18)
                                        .addGroup(pnlIdentInternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(sSeparador15, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(pnlIdentInternoLayout.createSequentialGroup()
                                                .addGroup(pnlIdentInternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                    .addComponent(lblTelPai, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(tfTelPai, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(18, 18, 18)
                                                .addGroup(pnlIdentInternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(tfIdPaciente, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(lblIdPaciente, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)))))))
                            .addGroup(pnlIdentInternoLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(rbPais)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(rbEscola)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(rbParentes)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(rbRepublica)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(rbSozinho)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(rbOutro))
                            .addGroup(pnlIdentInternoLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(pnlIdentInternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(pnlIdentInternoLayout.createSequentialGroup()
                                        .addComponent(rbExterno)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(rbInterno)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(rbSemiInterno))
                                    .addComponent(lblRegime))))
                        .addGap(0, 57, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlIdentInternoLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnCancelar1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnContinuar1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnFinalizar1)))
                .addContainerGap())
        );
        pnlIdentInternoLayout.setVerticalGroup(
            pnlIdentInternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlIdentInternoLayout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(pnlIdentInternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNome)
                    .addComponent(lblDtNasc)
                    .addComponent(lblCpf)
                    .addComponent(lblMatricula)
                    .addComponent(lblTelefone))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlIdentInternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfDtNasc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfCpf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfMatricula, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlIdentInternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jSeparator11, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlIdentInternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblSexo)
                    .addGroup(pnlIdentInternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblCurso)
                        .addComponent(lblTurma)
                        .addComponent(lblPeso)
                        .addComponent(lblAltura)
                        .addComponent(lblSangue)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlIdentInternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlIdentInternoLayout.createSequentialGroup()
                        .addGroup(pnlIdentInternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbCurso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tfTurma, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(rbFeminino, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(rbMasculino)
                            .addComponent(tfPeso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tfAltura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tfSangue, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlIdentInternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator10, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(sSeparador18, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(sSeparador19, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(sSeparador20, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlIdentInternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblRua)
                    .addComponent(lblNumero)
                    .addComponent(lblBairro)
                    .addComponent(lblCidade)
                    .addComponent(lblUf)
                    .addComponent(lblPlanoSaude))
                .addGroup(pnlIdentInternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlIdentInternoLayout.createSequentialGroup()
                        .addGroup(pnlIdentInternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(rbPlanoSaudeSim)
                            .addComponent(rbPlanoSaudeNao)
                            .addComponent(lblQualPlanoSaude)
                            .addComponent(tfPlanoSaude, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(sSeparador21, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(pnlIdentInternoLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(pnlIdentInternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlIdentInternoLayout.createSequentialGroup()
                                .addGroup(pnlIdentInternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(tfRua, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(tfNCasa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(pnlIdentInternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(tfBairro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(tfCidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(pnlIdentInternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jSeparator7, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jSeparator8, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jSeparator9, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(sSeparador10, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(pnlIdentInternoLayout.createSequentialGroup()
                                .addComponent(tfUf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(sSeparador11, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlIdentInternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblMae)
                            .addComponent(lblTelMae)
                            .addComponent(lblPai)
                            .addComponent(lblTelPai)
                            .addComponent(lblIdPaciente))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlIdentInternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tfMae, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tfTelMae, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tfPai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tfTelPai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tfIdPaciente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlIdentInternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(sSeparador12, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(sSeparador13, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(sSeparador16, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(sSeparador15, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlIdentInternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblResponsavel)
                            .addComponent(lblTelResponsavel)
                            .addComponent(lblSus))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlIdentInternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tfResponsavel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tfTelResponsavel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(rbSusNao)
                            .addComponent(rbSusSim)
                            .addComponent(lblNSus, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tfNSus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlIdentInternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(sSeparador14, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(sSeparador17, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(sSeparador22, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblMoradia)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlIdentInternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(rbPais)
                            .addComponent(rbEscola)
                            .addComponent(rbParentes)
                            .addComponent(rbRepublica)
                            .addComponent(rbSozinho)
                            .addComponent(rbOutro))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblRegime)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlIdentInternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(rbExterno)
                            .addComponent(rbInterno)
                            .addComponent(rbSemiInterno))
                        .addGap(20, 20, 20)))
                .addGroup(pnlIdentInternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnFinalizar1)
                    .addComponent(btnContinuar1)
                    .addComponent(btnCancelar1))
                .addContainerGap())
        );

        lblQualPlanoSaude.setEnabled(false);
        tfPlanoSaude.setEnabled(false);
        lblIdPaciente.setVisible(false);
        tfIdPaciente.setVisible(false);

        javax.swing.GroupLayout pnlIdentificacaoLayout = new javax.swing.GroupLayout(pnlIdentificacao);
        pnlIdentificacao.setLayout(pnlIdentificacaoLayout);
        pnlIdentificacaoLayout.setHorizontalGroup(
            pnlIdentificacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlIdentificacaoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlIdentInterno, javax.swing.GroupLayout.DEFAULT_SIZE, 936, Short.MAX_VALUE)
                .addGap(49, 49, 49))
        );
        pnlIdentificacaoLayout.setVerticalGroup(
            pnlIdentificacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlIdentInterno, javax.swing.GroupLayout.DEFAULT_SIZE, 519, Short.MAX_VALUE)
        );

        tpCadastroPaciente.addTab("Identificação", pnlIdentificacao);

        pnlPregressa.setBackground(new java.awt.Color(255, 255, 255));

        pnlPregressaInterno.setBackground(new java.awt.Color(255, 255, 255));

        rbCartaoInfanciaCompleto.setBackground(new java.awt.Color(255, 255, 255));
        bgVacinasInfancia.add(rbCartaoInfanciaCompleto);
        rbCartaoInfanciaCompleto.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        rbCartaoInfanciaCompleto.setForeground(new java.awt.Color(102, 102, 102));
        rbCartaoInfanciaCompleto.setText("CARTÃO COMPLETO");
        rbCartaoInfanciaCompleto.setContentAreaFilled(false);
        rbCartaoInfanciaCompleto.setFocusPainted(false);

        lblAlergiaMedicamentosa.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        lblAlergiaMedicamentosa.setForeground(new java.awt.Color(102, 102, 102));
        lblAlergiaMedicamentosa.setText("ALERGIA MEDICAMENTOSA");

        rbCartaoInfanciaIncompleto.setBackground(new java.awt.Color(255, 255, 255));
        bgVacinasInfancia.add(rbCartaoInfanciaIncompleto);
        rbCartaoInfanciaIncompleto.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        rbCartaoInfanciaIncompleto.setForeground(new java.awt.Color(102, 102, 102));
        rbCartaoInfanciaIncompleto.setSelected(true);
        rbCartaoInfanciaIncompleto.setText("CARTÃO INCOMPLETO");
        rbCartaoInfanciaIncompleto.setContentAreaFilled(false);
        rbCartaoInfanciaIncompleto.setFocusPainted(false);

        rbAlergiaMedicamentosaNao.setBackground(new java.awt.Color(255, 255, 255));
        bgAlergiaMedicamentosa.add(rbAlergiaMedicamentosaNao);
        rbAlergiaMedicamentosaNao.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        rbAlergiaMedicamentosaNao.setForeground(new java.awt.Color(102, 102, 102));
        rbAlergiaMedicamentosaNao.setSelected(true);
        rbAlergiaMedicamentosaNao.setText("NÃO");
        rbAlergiaMedicamentosaNao.setContentAreaFilled(false);
        rbAlergiaMedicamentosaNao.setFocusPainted(false);
        rbAlergiaMedicamentosaNao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbAlergiaMedicamentosaNaoActionPerformed(evt);
            }
        });

        lblVacinasAdolescencia.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        lblVacinasAdolescencia.setForeground(new java.awt.Color(102, 102, 102));
        lblVacinasAdolescencia.setText("VACINAS NA ADOLESCÊNCIA");

        rbCartaoAdolescenciaCompleto.setBackground(new java.awt.Color(255, 255, 255));
        bgVacinasAdolescencia.add(rbCartaoAdolescenciaCompleto);
        rbCartaoAdolescenciaCompleto.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        rbCartaoAdolescenciaCompleto.setForeground(new java.awt.Color(102, 102, 102));
        rbCartaoAdolescenciaCompleto.setText("CARTÃO COMPLETO");
        rbCartaoAdolescenciaCompleto.setContentAreaFilled(false);
        rbCartaoAdolescenciaCompleto.setFocusPainted(false);

        rbAlergiaMedicamentosaSim.setBackground(new java.awt.Color(255, 255, 255));
        bgAlergiaMedicamentosa.add(rbAlergiaMedicamentosaSim);
        rbAlergiaMedicamentosaSim.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        rbAlergiaMedicamentosaSim.setForeground(new java.awt.Color(102, 102, 102));
        rbAlergiaMedicamentosaSim.setText("SIM");
        rbAlergiaMedicamentosaSim.setContentAreaFilled(false);
        rbAlergiaMedicamentosaSim.setFocusPainted(false);
        rbAlergiaMedicamentosaSim.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbAlergiaMedicamentosaSimActionPerformed(evt);
            }
        });

        tfAlergiaMedicamentosa.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        tfAlergiaMedicamentosa.setForeground(new java.awt.Color(102, 102, 102));
        tfAlergiaMedicamentosa.setBorder(null);

        rbCartaoAdolescenciaIncompleto.setBackground(new java.awt.Color(255, 255, 255));
        bgVacinasAdolescencia.add(rbCartaoAdolescenciaIncompleto);
        rbCartaoAdolescenciaIncompleto.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        rbCartaoAdolescenciaIncompleto.setForeground(new java.awt.Color(102, 102, 102));
        rbCartaoAdolescenciaIncompleto.setSelected(true);
        rbCartaoAdolescenciaIncompleto.setText("CARTÃO INCOMPLETO");
        rbCartaoAdolescenciaIncompleto.setContentAreaFilled(false);
        rbCartaoAdolescenciaIncompleto.setFocusPainted(false);

        lblAlergiaAlimentar.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        lblAlergiaAlimentar.setForeground(new java.awt.Color(102, 102, 102));
        lblAlergiaAlimentar.setText("ALERGIA ALIMENTAR");

        lblVacinaFaltando.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        lblVacinaFaltando.setForeground(new java.awt.Color(102, 102, 102));
        lblVacinaFaltando.setText("QUAL VACINA FALTA?");

        rbAlergiaAlimentarNao.setBackground(new java.awt.Color(255, 255, 255));
        bgAlergiaAlimentar.add(rbAlergiaAlimentarNao);
        rbAlergiaAlimentarNao.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        rbAlergiaAlimentarNao.setForeground(new java.awt.Color(102, 102, 102));
        rbAlergiaAlimentarNao.setSelected(true);
        rbAlergiaAlimentarNao.setText("NÃO");
        rbAlergiaAlimentarNao.setContentAreaFilled(false);
        rbAlergiaAlimentarNao.setFocusPainted(false);
        rbAlergiaAlimentarNao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbAlergiaAlimentarNaoActionPerformed(evt);
            }
        });

        tfVacinaFaltando.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        tfVacinaFaltando.setForeground(new java.awt.Color(102, 102, 102));
        tfVacinaFaltando.setBorder(null);

        rbAlergiaAlimentarSim.setBackground(new java.awt.Color(255, 255, 255));
        bgAlergiaAlimentar.add(rbAlergiaAlimentarSim);
        rbAlergiaAlimentarSim.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        rbAlergiaAlimentarSim.setForeground(new java.awt.Color(102, 102, 102));
        rbAlergiaAlimentarSim.setText("SIM");
        rbAlergiaAlimentarSim.setContentAreaFilled(false);
        rbAlergiaAlimentarSim.setFocusPainted(false);
        rbAlergiaAlimentarSim.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbAlergiaAlimentarSimActionPerformed(evt);
            }
        });

        lblDoencas.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        lblDoencas.setForeground(new java.awt.Color(102, 102, 102));
        lblDoencas.setText("DOENÇAS NA INFÂNCA E ADOLESCÊNCIA");

        tfAlergiaAlimentar.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        tfAlergiaAlimentar.setForeground(new java.awt.Color(102, 102, 102));
        tfAlergiaAlimentar.setBorder(null);

        tfDoencas.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        tfDoencas.setForeground(new java.awt.Color(102, 102, 102));
        tfDoencas.setBorder(null);

        btnCancelar2.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btnCancelar2.setForeground(new java.awt.Color(102, 102, 102));
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

        lblDoencasContraidas.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        lblDoencasContraidas.setForeground(new java.awt.Color(102, 102, 102));
        lblDoencasContraidas.setText("JÁ CONTRAIU ALGUMA DESSAS DOENÇAS");

        btnContinuar2.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btnContinuar2.setForeground(new java.awt.Color(102, 102, 102));
        btnContinuar2.setText("CONTINUAR");
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

        rbCaxumba.setBackground(new java.awt.Color(255, 255, 255));
        rbCaxumba.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        rbCaxumba.setForeground(new java.awt.Color(102, 102, 102));
        rbCaxumba.setText("CAXUMBA");
        rbCaxumba.setContentAreaFilled(false);
        rbCaxumba.setFocusPainted(false);

        rbHepatite.setBackground(new java.awt.Color(255, 255, 255));
        rbHepatite.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        rbHepatite.setForeground(new java.awt.Color(102, 102, 102));
        rbHepatite.setText("HEPATITE");
        rbHepatite.setContentAreaFilled(false);
        rbHepatite.setFocusPainted(false);

        rbMeningite.setBackground(new java.awt.Color(255, 255, 255));
        rbMeningite.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        rbMeningite.setForeground(new java.awt.Color(102, 102, 102));
        rbMeningite.setText("MENINGITE");
        rbMeningite.setContentAreaFilled(false);
        rbMeningite.setFocusPainted(false);

        rbSarampo.setBackground(new java.awt.Color(255, 255, 255));
        rbSarampo.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        rbSarampo.setForeground(new java.awt.Color(102, 102, 102));
        rbSarampo.setText("SARAMPO");
        rbSarampo.setContentAreaFilled(false);
        rbSarampo.setFocusPainted(false);

        rbRubeola.setBackground(new java.awt.Color(255, 255, 255));
        rbRubeola.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        rbRubeola.setForeground(new java.awt.Color(102, 102, 102));
        rbRubeola.setText("RUBÉOLA");
        rbRubeola.setContentAreaFilled(false);
        rbRubeola.setFocusPainted(false);

        rbDengue.setBackground(new java.awt.Color(255, 255, 255));
        rbDengue.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        rbDengue.setForeground(new java.awt.Color(102, 102, 102));
        rbDengue.setText("DENGUE");
        rbDengue.setContentAreaFilled(false);
        rbDengue.setFocusPainted(false);

        rbCatapora.setBackground(new java.awt.Color(255, 255, 255));
        rbCatapora.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        rbCatapora.setForeground(new java.awt.Color(102, 102, 102));
        rbCatapora.setText("CATAPORA");
        rbCatapora.setContentAreaFilled(false);
        rbCatapora.setFocusPainted(false);

        rbPneumonia.setBackground(new java.awt.Color(255, 255, 255));
        rbPneumonia.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        rbPneumonia.setForeground(new java.awt.Color(102, 102, 102));
        rbPneumonia.setText("PNEUMONIA");
        rbPneumonia.setContentAreaFilled(false);
        rbPneumonia.setFocusPainted(false);

        lblCirurgias.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        lblCirurgias.setForeground(new java.awt.Color(102, 102, 102));
        lblCirurgias.setText("CIRURGIAS REALIZADAS");

        rbCirurgiasNao.setBackground(new java.awt.Color(255, 255, 255));
        bgCirurgiasRealizadas.add(rbCirurgiasNao);
        rbCirurgiasNao.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        rbCirurgiasNao.setForeground(new java.awt.Color(102, 102, 102));
        rbCirurgiasNao.setSelected(true);
        rbCirurgiasNao.setText("NÃO");
        rbCirurgiasNao.setContentAreaFilled(false);
        rbCirurgiasNao.setFocusPainted(false);
        rbCirurgiasNao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbCirurgiasNaoActionPerformed(evt);
            }
        });

        rbCirurgiasSim.setBackground(new java.awt.Color(255, 255, 255));
        bgCirurgiasRealizadas.add(rbCirurgiasSim);
        rbCirurgiasSim.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        rbCirurgiasSim.setForeground(new java.awt.Color(102, 102, 102));
        rbCirurgiasSim.setText("SIM");
        rbCirurgiasSim.setContentAreaFilled(false);
        rbCirurgiasSim.setFocusPainted(false);
        rbCirurgiasSim.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbCirurgiasSimActionPerformed(evt);
            }
        });

        lblVacinasInfancia.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        lblVacinasInfancia.setForeground(new java.awt.Color(102, 102, 102));
        lblVacinasInfancia.setText("VACINAS NA INFÂNCIA");

        tfCirurgiasRealizadas.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        tfCirurgiasRealizadas.setForeground(new java.awt.Color(102, 102, 102));
        tfCirurgiasRealizadas.setBorder(null);

        sSeparador23.setForeground(new java.awt.Color(51, 51, 51));

        sSeparador24.setForeground(new java.awt.Color(51, 51, 51));

        sSeparador25.setForeground(new java.awt.Color(51, 51, 51));

        sSeparador26.setForeground(new java.awt.Color(51, 51, 51));

        sSeparador27.setForeground(new java.awt.Color(51, 51, 51));

        btnFinalizar2.setBackground(new java.awt.Color(102, 102, 102));
        btnFinalizar2.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btnFinalizar2.setForeground(new java.awt.Color(102, 102, 102));
        btnFinalizar2.setText("FINALIZAR");
        btnFinalizar2.setBorderPainted(false);
        btnFinalizar2.setContentAreaFilled(false);
        btnFinalizar2.setFocusPainted(false);
        btnFinalizar2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnFinalizar2MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnFinalizar2MouseExited(evt);
            }
        });
        btnFinalizar2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFinalizar2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlPregressaInternoLayout = new javax.swing.GroupLayout(pnlPregressaInterno);
        pnlPregressaInterno.setLayout(pnlPregressaInternoLayout);
        pnlPregressaInternoLayout.setHorizontalGroup(
            pnlPregressaInternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPregressaInternoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlPregressaInternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlPregressaInternoLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnCancelar2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnContinuar2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnFinalizar2))
                    .addGroup(pnlPregressaInternoLayout.createSequentialGroup()
                        .addGroup(pnlPregressaInternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(sSeparador26, javax.swing.GroupLayout.PREFERRED_SIZE, 402, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(pnlPregressaInternoLayout.createSequentialGroup()
                                .addComponent(lblAlergiaMedicamentosa)
                                .addGap(18, 18, 18)
                                .addComponent(rbAlergiaMedicamentosaNao, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(rbAlergiaMedicamentosaSim, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tfAlergiaMedicamentosa))
                            .addGroup(pnlPregressaInternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(sSeparador25, javax.swing.GroupLayout.PREFERRED_SIZE, 429, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlPregressaInternoLayout.createSequentialGroup()
                                    .addComponent(lblVacinasInfancia)
                                    .addGap(18, 18, 18)
                                    .addComponent(rbCartaoInfanciaCompleto, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(rbCartaoInfanciaIncompleto))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlPregressaInternoLayout.createSequentialGroup()
                                    .addComponent(lblVacinasAdolescencia)
                                    .addGap(18, 18, 18)
                                    .addComponent(rbCartaoAdolescenciaCompleto, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(rbCartaoAdolescenciaIncompleto))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlPregressaInternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlPregressaInternoLayout.createSequentialGroup()
                                        .addComponent(lblVacinaFaltando)
                                        .addGap(18, 18, 18)
                                        .addGroup(pnlPregressaInternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(sSeparador23)
                                            .addComponent(tfVacinaFaltando, javax.swing.GroupLayout.DEFAULT_SIZE, 572, Short.MAX_VALUE)))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlPregressaInternoLayout.createSequentialGroup()
                                        .addComponent(lblDoencas)
                                        .addGap(18, 18, 18)
                                        .addGroup(pnlPregressaInternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(sSeparador24)
                                            .addComponent(tfDoencas, javax.swing.GroupLayout.DEFAULT_SIZE, 409, Short.MAX_VALUE))))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlPregressaInternoLayout.createSequentialGroup()
                                    .addGap(10, 10, 10)
                                    .addGroup(pnlPregressaInternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(pnlPregressaInternoLayout.createSequentialGroup()
                                            .addComponent(rbCatapora, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(10, 10, 10)
                                            .addComponent(rbCaxumba, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(10, 10, 10)
                                            .addComponent(rbDengue, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(10, 10, 10)
                                            .addComponent(rbHepatite, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(pnlPregressaInternoLayout.createSequentialGroup()
                                            .addComponent(rbMeningite, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(10, 10, 10)
                                            .addComponent(rbPneumonia, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(10, 10, 10)
                                            .addComponent(rbRubeola, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(10, 10, 10)
                                            .addComponent(rbSarampo, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addComponent(lblDoencasContraidas, javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlPregressaInternoLayout.createSequentialGroup()
                                    .addComponent(lblCirurgias)
                                    .addGap(18, 18, 18)
                                    .addComponent(rbCirurgiasNao, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(rbCirurgiasSim, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(tfCirurgiasRealizadas, javax.swing.GroupLayout.PREFERRED_SIZE, 430, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlPregressaInternoLayout.createSequentialGroup()
                                .addComponent(lblAlergiaAlimentar)
                                .addGap(18, 18, 18)
                                .addComponent(rbAlergiaAlimentarNao, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(rbAlergiaAlimentarSim, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(pnlPregressaInternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(sSeparador27)
                                    .addComponent(tfAlergiaAlimentar))))
                        .addGap(0, 152, Short.MAX_VALUE)))
                .addContainerGap())
        );
        pnlPregressaInternoLayout.setVerticalGroup(
            pnlPregressaInternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPregressaInternoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlPregressaInternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblVacinasInfancia)
                    .addComponent(rbCartaoInfanciaCompleto)
                    .addComponent(rbCartaoInfanciaIncompleto))
                .addGap(18, 18, 18)
                .addGroup(pnlPregressaInternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblVacinasAdolescencia)
                    .addComponent(rbCartaoAdolescenciaCompleto)
                    .addComponent(rbCartaoAdolescenciaIncompleto))
                .addGap(18, 18, 18)
                .addGroup(pnlPregressaInternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblVacinaFaltando)
                    .addComponent(tfVacinaFaltando, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sSeparador23, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addGroup(pnlPregressaInternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDoencas)
                    .addComponent(tfDoencas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sSeparador24, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblDoencasContraidas)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlPregressaInternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rbCatapora)
                    .addComponent(rbCaxumba)
                    .addComponent(rbDengue)
                    .addComponent(rbHepatite))
                .addGap(5, 5, 5)
                .addGroup(pnlPregressaInternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rbMeningite)
                    .addComponent(rbPneumonia)
                    .addComponent(rbRubeola)
                    .addComponent(rbSarampo))
                .addGap(18, 18, 18)
                .addGroup(pnlPregressaInternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCirurgias)
                    .addComponent(rbCirurgiasNao)
                    .addComponent(rbCirurgiasSim)
                    .addComponent(tfCirurgiasRealizadas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sSeparador25, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(pnlPregressaInternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfAlergiaMedicamentosa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblAlergiaMedicamentosa)
                    .addComponent(rbAlergiaMedicamentosaNao)
                    .addComponent(rbAlergiaMedicamentosaSim))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sSeparador26, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(pnlPregressaInternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfAlergiaAlimentar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblAlergiaAlimentar)
                    .addComponent(rbAlergiaAlimentarNao)
                    .addComponent(rbAlergiaAlimentarSim))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sSeparador27, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(61, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlPregressaInternoLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pnlPregressaInternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnFinalizar2)
                    .addComponent(btnContinuar2)
                    .addComponent(btnCancelar2))
                .addContainerGap())
        );

        tfAlergiaMedicamentosa.setEnabled(false);
        tfAlergiaAlimentar.setEnabled(false);
        tfCirurgiasRealizadas.setEnabled(false);

        javax.swing.GroupLayout pnlPregressaLayout = new javax.swing.GroupLayout(pnlPregressa);
        pnlPregressa.setLayout(pnlPregressaLayout);
        pnlPregressaLayout.setHorizontalGroup(
            pnlPregressaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPregressaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlPregressaInterno, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnlPregressaLayout.setVerticalGroup(
            pnlPregressaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPregressaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlPregressaInterno, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        tpCadastroPaciente.addTab("História Pregressa", pnlPregressa);

        pnlFamiliar.setBackground(new java.awt.Color(255, 255, 255));

        pnlFamiliarInterno.setBackground(new java.awt.Color(255, 255, 255));

        tfEspecificar.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        tfEspecificar.setForeground(new java.awt.Color(102, 102, 102));
        tfEspecificar.setBorder(null);

        btnCancelar3.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btnCancelar3.setForeground(new java.awt.Color(102, 102, 102));
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

        rbHipertensao.setBackground(new java.awt.Color(255, 255, 255));
        rbHipertensao.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        rbHipertensao.setForeground(new java.awt.Color(102, 102, 102));
        rbHipertensao.setText("HIPERTENSÃO ARTERIAL");
        rbHipertensao.setContentAreaFilled(false);
        rbHipertensao.setFocusPainted(false);

        btnContinuar3.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btnContinuar3.setForeground(new java.awt.Color(102, 102, 102));
        btnContinuar3.setText("CONTINUAR");
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

        rbOftalmologico.setBackground(new java.awt.Color(255, 255, 255));
        rbOftalmologico.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        rbOftalmologico.setForeground(new java.awt.Color(102, 102, 102));
        rbOftalmologico.setText("PROBLEMAS OFTALMOLÓGICOS");
        rbOftalmologico.setContentAreaFilled(false);
        rbOftalmologico.setFocusPainted(false);

        rbDiabetes.setBackground(new java.awt.Color(255, 255, 255));
        rbDiabetes.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        rbDiabetes.setForeground(new java.awt.Color(102, 102, 102));
        rbDiabetes.setText("DIABETES");
        rbDiabetes.setContentAreaFilled(false);
        rbDiabetes.setFocusPainted(false);
        rbDiabetes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbDiabetesActionPerformed(evt);
            }
        });

        rbMental.setBackground(new java.awt.Color(255, 255, 255));
        rbMental.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        rbMental.setForeground(new java.awt.Color(102, 102, 102));
        rbMental.setText("TRANSTORNOS MENTAIS");
        rbMental.setContentAreaFilled(false);
        rbMental.setFocusPainted(false);

        rbCancer.setBackground(new java.awt.Color(255, 255, 255));
        rbCancer.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        rbCancer.setForeground(new java.awt.Color(102, 102, 102));
        rbCancer.setText("CÂNCER");
        rbCancer.setContentAreaFilled(false);
        rbCancer.setFocusPainted(false);

        rbCardiopatia.setBackground(new java.awt.Color(255, 255, 255));
        rbCardiopatia.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        rbCardiopatia.setForeground(new java.awt.Color(102, 102, 102));
        rbCardiopatia.setText("CARDIOPATIAS");
        rbCardiopatia.setContentAreaFilled(false);
        rbCardiopatia.setFocusPainted(false);

        rbProblemaRenal.setBackground(new java.awt.Color(255, 255, 255));
        rbProblemaRenal.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        rbProblemaRenal.setForeground(new java.awt.Color(102, 102, 102));
        rbProblemaRenal.setText("PROBLEMAS RENAIS");
        rbProblemaRenal.setContentAreaFilled(false);
        rbProblemaRenal.setFocusPainted(false);

        rbHistFamiliarOutro.setBackground(new java.awt.Color(255, 255, 255));
        rbHistFamiliarOutro.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        rbHistFamiliarOutro.setForeground(new java.awt.Color(102, 102, 102));
        rbHistFamiliarOutro.setText("OUTRAS");
        rbHistFamiliarOutro.setContentAreaFilled(false);
        rbHistFamiliarOutro.setFocusPainted(false);
        rbHistFamiliarOutro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbHistFamiliarOutroActionPerformed(evt);
            }
        });

        lblEspecificar.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        lblEspecificar.setForeground(new java.awt.Color(102, 102, 102));
        lblEspecificar.setText("ESPECIFICAR");

        sSeparador28.setForeground(new java.awt.Color(51, 51, 51));

        btnFinalizar3.setBackground(new java.awt.Color(102, 102, 102));
        btnFinalizar3.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btnFinalizar3.setForeground(new java.awt.Color(102, 102, 102));
        btnFinalizar3.setText("FINALIZAR");
        btnFinalizar3.setBorderPainted(false);
        btnFinalizar3.setContentAreaFilled(false);
        btnFinalizar3.setFocusPainted(false);
        btnFinalizar3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnFinalizar3MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnFinalizar3MouseExited(evt);
            }
        });
        btnFinalizar3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFinalizar3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlFamiliarInternoLayout = new javax.swing.GroupLayout(pnlFamiliarInterno);
        pnlFamiliarInterno.setLayout(pnlFamiliarInternoLayout);
        pnlFamiliarInternoLayout.setHorizontalGroup(
            pnlFamiliarInternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFamiliarInternoLayout.createSequentialGroup()
                .addGroup(pnlFamiliarInternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlFamiliarInternoLayout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(pnlFamiliarInternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlFamiliarInternoLayout.createSequentialGroup()
                                .addComponent(rbCancer)
                                .addGap(158, 158, 158)
                                .addComponent(rbOftalmologico))
                            .addGroup(pnlFamiliarInternoLayout.createSequentialGroup()
                                .addComponent(rbCardiopatia)
                                .addGap(108, 108, 108)
                                .addComponent(rbProblemaRenal))
                            .addGroup(pnlFamiliarInternoLayout.createSequentialGroup()
                                .addComponent(rbDiabetes)
                                .addGap(148, 148, 148)
                                .addComponent(rbMental, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pnlFamiliarInternoLayout.createSequentialGroup()
                                .addComponent(rbHipertensao)
                                .addGap(44, 44, 44)
                                .addComponent(rbHistFamiliarOutro))
                            .addGroup(pnlFamiliarInternoLayout.createSequentialGroup()
                                .addComponent(lblEspecificar, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(pnlFamiliarInternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(sSeparador28)
                                    .addComponent(tfEspecificar, javax.swing.GroupLayout.DEFAULT_SIZE, 490, Short.MAX_VALUE))))
                        .addGap(0, 317, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlFamiliarInternoLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnCancelar3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnContinuar3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnFinalizar3)))
                .addContainerGap())
        );
        pnlFamiliarInternoLayout.setVerticalGroup(
            pnlFamiliarInternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFamiliarInternoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlFamiliarInternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rbOftalmologico)
                    .addComponent(rbCancer))
                .addGap(5, 5, 5)
                .addGroup(pnlFamiliarInternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rbCardiopatia)
                    .addComponent(rbProblemaRenal))
                .addGap(5, 5, 5)
                .addGroup(pnlFamiliarInternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rbDiabetes)
                    .addComponent(rbMental, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(pnlFamiliarInternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rbHipertensao)
                    .addComponent(rbHistFamiliarOutro))
                .addGap(18, 18, 18)
                .addGroup(pnlFamiliarInternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblEspecificar)
                    .addComponent(tfEspecificar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sSeparador28, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 278, Short.MAX_VALUE)
                .addGroup(pnlFamiliarInternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnFinalizar3)
                    .addComponent(btnContinuar3)
                    .addComponent(btnCancelar3))
                .addContainerGap())
        );

        tfEspecificar.setEnabled(false);

        javax.swing.GroupLayout pnlFamiliarLayout = new javax.swing.GroupLayout(pnlFamiliar);
        pnlFamiliar.setLayout(pnlFamiliarLayout);
        pnlFamiliarLayout.setHorizontalGroup(
            pnlFamiliarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFamiliarLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlFamiliarInterno, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnlFamiliarLayout.setVerticalGroup(
            pnlFamiliarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlFamiliarLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlFamiliarInterno, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        tpCadastroPaciente.addTab("História Familiar", pnlFamiliar);

        pnlDoenca.setBackground(new java.awt.Color(255, 255, 255));

        pnlDoencaAtualInterno.setBackground(new java.awt.Color(255, 255, 255));

        rbProteseSim.setBackground(new java.awt.Color(255, 255, 255));
        bgProtese.add(rbProteseSim);
        rbProteseSim.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        rbProteseSim.setForeground(new java.awt.Color(102, 102, 102));
        rbProteseSim.setText("SIM");
        rbProteseSim.setContentAreaFilled(false);
        rbProteseSim.setFocusPainted(false);

        lblProtese.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        lblProtese.setForeground(new java.awt.Color(102, 102, 102));
        lblProtese.setText("USA PRÓTESE");

        rbNenhumaNecessidade.setBackground(new java.awt.Color(255, 255, 255));
        rbNenhumaNecessidade.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        rbNenhumaNecessidade.setForeground(new java.awt.Color(102, 102, 102));
        rbNenhumaNecessidade.setText("NENHUMA NECESSIDADE ESPECÍFICA DESCRITA ACIMA");
        rbNenhumaNecessidade.setContentAreaFilled(false);
        rbNenhumaNecessidade.setFocusPainted(false);

        rbAuditiva.setBackground(new java.awt.Color(255, 255, 255));
        rbAuditiva.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        rbAuditiva.setForeground(new java.awt.Color(102, 102, 102));
        rbAuditiva.setText("DEFICIÊNCIA AUDITIVA");
        rbAuditiva.setContentAreaFilled(false);
        rbAuditiva.setFocusPainted(false);

        rbConcentracao.setBackground(new java.awt.Color(255, 255, 255));
        rbConcentracao.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        rbConcentracao.setForeground(new java.awt.Color(102, 102, 102));
        rbConcentracao.setText("DIFICULDADE DE CONCENTRAÇÃO");
        rbConcentracao.setContentAreaFilled(false);
        rbConcentracao.setFocusPainted(false);

        rbSuperdotacao.setBackground(new java.awt.Color(255, 255, 255));
        rbSuperdotacao.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        rbSuperdotacao.setForeground(new java.awt.Color(102, 102, 102));
        rbSuperdotacao.setText("SUPERDOTAÇÃO/ ALTAS HABILIDADES");
        rbSuperdotacao.setContentAreaFilled(false);
        rbSuperdotacao.setFocusPainted(false);
        rbSuperdotacao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbSuperdotacaojRadioButton42ActionPerformed(evt);
            }
        });

        btnContinuar4.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btnContinuar4.setForeground(new java.awt.Color(102, 102, 102));
        btnContinuar4.setText("CONTINUAR");
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

        rbDesenvolvimento.setBackground(new java.awt.Color(255, 255, 255));
        rbDesenvolvimento.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        rbDesenvolvimento.setForeground(new java.awt.Color(102, 102, 102));
        rbDesenvolvimento.setText("TRANSTORNO GLOBAL DE DESENVOLVIMENTO");
        rbDesenvolvimento.setContentAreaFilled(false);
        rbDesenvolvimento.setFocusPainted(false);

        btnCancelar4.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btnCancelar4.setForeground(new java.awt.Color(102, 102, 102));
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

        rbVisual.setBackground(new java.awt.Color(255, 255, 255));
        rbVisual.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        rbVisual.setForeground(new java.awt.Color(102, 102, 102));
        rbVisual.setText("DEFICIÊNCIA VISUAL");
        rbVisual.setContentAreaFilled(false);
        rbVisual.setFocusPainted(false);

        lblAcompanhamento.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        lblAcompanhamento.setForeground(new java.awt.Color(102, 102, 102));
        lblAcompanhamento.setText("FAZ ACOMPANHAMENTO DESSE PROBLEMA");

        rbLeitura.setBackground(new java.awt.Color(255, 255, 255));
        rbLeitura.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        rbLeitura.setForeground(new java.awt.Color(102, 102, 102));
        rbLeitura.setText("DIFICULDADE DE LEITURA");
        rbLeitura.setContentAreaFilled(false);
        rbLeitura.setFocusPainted(false);

        rbAcompanhamentoSim.setBackground(new java.awt.Color(255, 255, 255));
        bgAcompanhamento.add(rbAcompanhamentoSim);
        rbAcompanhamentoSim.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        rbAcompanhamentoSim.setForeground(new java.awt.Color(102, 102, 102));
        rbAcompanhamentoSim.setText("SIM");
        rbAcompanhamentoSim.setContentAreaFilled(false);
        rbAcompanhamentoSim.setFocusPainted(false);

        rbFisica.setBackground(new java.awt.Color(255, 255, 255));
        rbFisica.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        rbFisica.setForeground(new java.awt.Color(102, 102, 102));
        rbFisica.setText("DEFICIÊNCIA FÍSICA");
        rbFisica.setContentAreaFilled(false);
        rbFisica.setFocusPainted(false);

        rbAcompanhamentoNao.setBackground(new java.awt.Color(255, 255, 255));
        bgAcompanhamento.add(rbAcompanhamentoNao);
        rbAcompanhamentoNao.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        rbAcompanhamentoNao.setForeground(new java.awt.Color(102, 102, 102));
        rbAcompanhamentoNao.setSelected(true);
        rbAcompanhamentoNao.setText("NÃO");
        rbAcompanhamentoNao.setContentAreaFilled(false);
        rbAcompanhamentoNao.setFocusPainted(false);

        tfDoencaCronica.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        tfDoencaCronica.setForeground(new java.awt.Color(102, 102, 102));
        tfDoencaCronica.setBorder(null);

        rbEscrita.setBackground(new java.awt.Color(255, 255, 255));
        rbEscrita.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        rbEscrita.setForeground(new java.awt.Color(102, 102, 102));
        rbEscrita.setText("DIFICULDADE DE ESCRITA");
        rbEscrita.setContentAreaFilled(false);
        rbEscrita.setFocusPainted(false);

        lblNecessidadeEspecifica.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        lblNecessidadeEspecifica.setForeground(new java.awt.Color(102, 102, 102));
        lblNecessidadeEspecifica.setText("APRESENTA ALGUMA NECESSIDADE ESPECÍFICA OU DIFICULDADE RELACIONADA ABAIXO ?");

        rbCronicaOutros.setBackground(new java.awt.Color(255, 255, 255));
        rbCronicaOutros.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        rbCronicaOutros.setForeground(new java.awt.Color(102, 102, 102));
        rbCronicaOutros.setText("OUTROS");
        rbCronicaOutros.setContentAreaFilled(false);
        rbCronicaOutros.setFocusPainted(false);
        rbCronicaOutros.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbCronicaOutrosActionPerformed(evt);
            }
        });

        rbRinite.setBackground(new java.awt.Color(255, 255, 255));
        rbRinite.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        rbRinite.setForeground(new java.awt.Color(102, 102, 102));
        rbRinite.setText("RINITE");
        rbRinite.setContentAreaFilled(false);
        rbRinite.setFocusPainted(false);

        rbRenal.setBackground(new java.awt.Color(255, 255, 255));
        rbRenal.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        rbRenal.setForeground(new java.awt.Color(102, 102, 102));
        rbRenal.setText("PROBLEMAS RENAIS");
        rbRenal.setContentAreaFilled(false);
        rbRenal.setFocusPainted(false);

        rbAsma.setBackground(new java.awt.Color(255, 255, 255));
        rbAsma.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        rbAsma.setForeground(new java.awt.Color(102, 102, 102));
        rbAsma.setText("ASMA");
        rbAsma.setContentAreaFilled(false);
        rbAsma.setFocusPainted(false);

        rbBronquite.setBackground(new java.awt.Color(255, 255, 255));
        rbBronquite.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        rbBronquite.setForeground(new java.awt.Color(102, 102, 102));
        rbBronquite.setText("BRONQUITE");
        rbBronquite.setContentAreaFilled(false);
        rbBronquite.setFocusPainted(false);

        rbCronicaDiabetes.setBackground(new java.awt.Color(255, 255, 255));
        rbCronicaDiabetes.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        rbCronicaDiabetes.setForeground(new java.awt.Color(102, 102, 102));
        rbCronicaDiabetes.setText("DIABETES");
        rbCronicaDiabetes.setContentAreaFilled(false);
        rbCronicaDiabetes.setFocusPainted(false);

        rbPressaoAlta.setBackground(new java.awt.Color(255, 255, 255));
        rbPressaoAlta.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        rbPressaoAlta.setForeground(new java.awt.Color(102, 102, 102));
        rbPressaoAlta.setText("PRESSÃO ALTA");
        rbPressaoAlta.setContentAreaFilled(false);
        rbPressaoAlta.setFocusPainted(false);
        rbPressaoAlta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbPressaoAltajRadioButton59ActionPerformed(evt);
            }
        });

        rbCardiaco.setBackground(new java.awt.Color(255, 255, 255));
        rbCardiaco.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        rbCardiaco.setForeground(new java.awt.Color(102, 102, 102));
        rbCardiaco.setText("PROBLEMAS CARDÍACOS");
        rbCardiaco.setContentAreaFilled(false);
        rbCardiaco.setFocusPainted(false);

        lblDoencaCronica.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        lblDoencaCronica.setForeground(new java.awt.Color(102, 102, 102));
        lblDoencaCronica.setText("ALGUMA DOENÇA CRÔNICA ?");

        rbProteseNao.setBackground(new java.awt.Color(255, 255, 255));
        bgProtese.add(rbProteseNao);
        rbProteseNao.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        rbProteseNao.setForeground(new java.awt.Color(102, 102, 102));
        rbProteseNao.setSelected(true);
        rbProteseNao.setText("NÃO");
        rbProteseNao.setContentAreaFilled(false);
        rbProteseNao.setFocusPainted(false);

        sSeparador29.setForeground(new java.awt.Color(51, 51, 51));

        btnFinalizar4.setBackground(new java.awt.Color(102, 102, 102));
        btnFinalizar4.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btnFinalizar4.setForeground(new java.awt.Color(102, 102, 102));
        btnFinalizar4.setText("FINALIZAR");
        btnFinalizar4.setBorderPainted(false);
        btnFinalizar4.setContentAreaFilled(false);
        btnFinalizar4.setFocusPainted(false);
        btnFinalizar4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnFinalizar4MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnFinalizar4MouseExited(evt);
            }
        });
        btnFinalizar4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFinalizar4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlDoencaAtualInternoLayout = new javax.swing.GroupLayout(pnlDoencaAtualInterno);
        pnlDoencaAtualInterno.setLayout(pnlDoencaAtualInternoLayout);
        pnlDoencaAtualInternoLayout.setHorizontalGroup(
            pnlDoencaAtualInternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDoencaAtualInternoLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnCancelar4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnContinuar4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnFinalizar4)
                .addContainerGap())
            .addGroup(pnlDoencaAtualInternoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlDoencaAtualInternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblNecessidadeEspecifica)
                    .addGroup(pnlDoencaAtualInternoLayout.createSequentialGroup()
                        .addComponent(lblProtese)
                        .addGap(18, 18, 18)
                        .addComponent(rbProteseNao)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(rbProteseSim))
                    .addComponent(lblDoencaCronica)
                    .addGroup(pnlDoencaAtualInternoLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(pnlDoencaAtualInternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlDoencaAtualInternoLayout.createSequentialGroup()
                                .addComponent(rbAuditiva, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(120, 120, 120)
                                .addComponent(rbEscrita))
                            .addGroup(pnlDoencaAtualInternoLayout.createSequentialGroup()
                                .addComponent(rbFisica)
                                .addGap(162, 162, 162)
                                .addComponent(rbLeitura))
                            .addGroup(pnlDoencaAtualInternoLayout.createSequentialGroup()
                                .addComponent(rbVisual)
                                .addGap(158, 158, 158)
                                .addComponent(rbSuperdotacao))
                            .addGroup(pnlDoencaAtualInternoLayout.createSequentialGroup()
                                .addComponent(rbConcentracao)
                                .addGap(67, 67, 67)
                                .addComponent(rbDesenvolvimento))
                            .addComponent(rbNenhumaNecessidade)
                            .addGroup(pnlDoencaAtualInternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(pnlDoencaAtualInternoLayout.createSequentialGroup()
                                    .addComponent(rbAsma)
                                    .addGap(5, 5, 5)
                                    .addComponent(rbBronquite)
                                    .addGap(4, 4, 4)
                                    .addComponent(rbCronicaDiabetes)
                                    .addGap(8, 8, 8)
                                    .addComponent(rbPressaoAlta)
                                    .addGap(11, 11, 11)
                                    .addComponent(rbCardiaco)
                                    .addGap(11, 11, 11)
                                    .addComponent(rbRenal))
                                .addGroup(pnlDoencaAtualInternoLayout.createSequentialGroup()
                                    .addComponent(rbRinite)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(rbCronicaOutros)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addGroup(pnlDoencaAtualInternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(sSeparador29)
                                        .addComponent(tfDoencaCronica, javax.swing.GroupLayout.PREFERRED_SIZE, 633, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(pnlDoencaAtualInternoLayout.createSequentialGroup()
                                .addComponent(lblAcompanhamento)
                                .addGap(18, 18, 18)
                                .addComponent(rbAcompanhamentoNao)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(rbAcompanhamentoSim)))))
                .addContainerGap(160, Short.MAX_VALUE))
        );
        pnlDoencaAtualInternoLayout.setVerticalGroup(
            pnlDoencaAtualInternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlDoencaAtualInternoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblNecessidadeEspecifica, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(pnlDoencaAtualInternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rbAuditiva, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rbEscrita))
                .addGap(5, 5, 5)
                .addGroup(pnlDoencaAtualInternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rbFisica)
                    .addComponent(rbLeitura))
                .addGap(5, 5, 5)
                .addGroup(pnlDoencaAtualInternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rbVisual)
                    .addComponent(rbSuperdotacao))
                .addGap(5, 5, 5)
                .addGroup(pnlDoencaAtualInternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rbConcentracao)
                    .addComponent(rbDesenvolvimento))
                .addGap(15, 15, 15)
                .addComponent(rbNenhumaNecessidade)
                .addGap(18, 18, 18)
                .addGroup(pnlDoencaAtualInternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblProtese, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rbProteseNao)
                    .addComponent(rbProteseSim))
                .addGap(18, 18, 18)
                .addComponent(lblDoencaCronica, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(pnlDoencaAtualInternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rbAsma)
                    .addComponent(rbBronquite)
                    .addComponent(rbCronicaDiabetes)
                    .addComponent(rbPressaoAlta)
                    .addComponent(rbCardiaco)
                    .addComponent(rbRenal))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlDoencaAtualInternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rbRinite)
                    .addComponent(rbCronicaOutros)
                    .addComponent(tfDoencaCronica, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sSeparador29, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(pnlDoencaAtualInternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblAcompanhamento, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlDoencaAtualInternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(rbAcompanhamentoNao)
                        .addComponent(rbAcompanhamentoSim)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 54, Short.MAX_VALUE)
                .addGroup(pnlDoencaAtualInternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnFinalizar4)
                    .addComponent(btnContinuar4)
                    .addComponent(btnCancelar4))
                .addContainerGap())
        );

        tfDoencaCronica.setEnabled(false);

        javax.swing.GroupLayout pnlDoencaLayout = new javax.swing.GroupLayout(pnlDoenca);
        pnlDoenca.setLayout(pnlDoencaLayout);
        pnlDoencaLayout.setHorizontalGroup(
            pnlDoencaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDoencaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlDoencaAtualInterno, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnlDoencaLayout.setVerticalGroup(
            pnlDoencaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDoencaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlDoencaAtualInterno, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        tpCadastroPaciente.addTab("Histórico de Doença Atual", pnlDoenca);

        pnlQuestionario1.setBackground(new java.awt.Color(255, 255, 255));

        pnlQuestionario1Interno.setBackground(new java.awt.Color(255, 255, 255));

        btnContinuar5.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btnContinuar5.setForeground(new java.awt.Color(102, 102, 102));
        btnContinuar5.setText("CONTINUAR");
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

        lblEpistaxe.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        lblEpistaxe.setForeground(new java.awt.Color(102, 102, 102));
        lblEpistaxe.setText("JÁ SOFREU EPISTAXE (SANGRAMENTO NASAL)? COM QUE FREQUÊNCIA?");

        lblMedicamentoContinuo.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        lblMedicamentoContinuo.setForeground(new java.awt.Color(102, 102, 102));
        lblMedicamentoContinuo.setText("MEDICAMENTOS DE USO CONTÍNUO");

        lblPressaoArterial.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        lblPressaoArterial.setForeground(new java.awt.Color(102, 102, 102));
        lblPressaoArterial.setText("QUANTO COSTUMA SER A PRESSÃO ARTERIAL? ");

        tfCefaleia.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        tfCefaleia.setForeground(new java.awt.Color(102, 102, 102));
        tfCefaleia.setBorder(null);

        tfDesmaios.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        tfDesmaios.setForeground(new java.awt.Color(102, 102, 102));
        tfDesmaios.setBorder(null);

        tfDiarreia.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        tfDiarreia.setForeground(new java.awt.Color(102, 102, 102));
        tfDiarreia.setBorder(null);

        tfEpistaxe.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        tfEpistaxe.setForeground(new java.awt.Color(102, 102, 102));
        tfEpistaxe.setBorder(null);

        tfMedicamentoContinuo.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        tfMedicamentoContinuo.setForeground(new java.awt.Color(102, 102, 102));
        tfMedicamentoContinuo.setBorder(null);

        lblCefaleia.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        lblCefaleia.setForeground(new java.awt.Color(102, 102, 102));
        lblCefaleia.setText("VOCÊ COSTUMA SENTIR CEFALÉIA (DOR DE CABEÇA)? SE SIM, QUE MEDICAÇÃO USA PARA ALIVIÁ-LA?");

        tfPressaoArterial.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        tfPressaoArterial.setForeground(new java.awt.Color(102, 102, 102));
        tfPressaoArterial.setBorder(null);

        lblDesmaios.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        lblDesmaios.setForeground(new java.awt.Color(102, 102, 102));
        lblDesmaios.setText("JÁ SOFREU DESMAIOS OU CONVULSÕES? COM QUE FREQUÊNCIA? POR QUAIS MOTIVOS?");

        btnCancelar5.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btnCancelar5.setForeground(new java.awt.Color(102, 102, 102));
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

        lblDiarreia.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        lblDiarreia.setForeground(new java.awt.Color(102, 102, 102));
        lblDiarreia.setText("SENTE COM FREQUÊNCIA DIARRÉIA, NÁUSEAS, DOR DE ESTÔMAGO OU CONSTIPAÇÃO INTESTINAL? QUAIS?");

        btnFinalizar5.setBackground(new java.awt.Color(102, 102, 102));
        btnFinalizar5.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btnFinalizar5.setForeground(new java.awt.Color(102, 102, 102));
        btnFinalizar5.setText("FINALIZAR");
        btnFinalizar5.setBorderPainted(false);
        btnFinalizar5.setContentAreaFilled(false);
        btnFinalizar5.setFocusPainted(false);
        btnFinalizar5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnFinalizar5MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnFinalizar5MouseExited(evt);
            }
        });
        btnFinalizar5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFinalizar5ActionPerformed(evt);
            }
        });

        sSeparador30.setForeground(new java.awt.Color(51, 51, 51));

        sSeparador31.setForeground(new java.awt.Color(51, 51, 51));

        sSeparador32.setForeground(new java.awt.Color(51, 51, 51));

        sSeparador33.setForeground(new java.awt.Color(51, 51, 51));

        sSeparador34.setForeground(new java.awt.Color(51, 51, 51));

        sSeparador35.setForeground(new java.awt.Color(51, 51, 51));

        javax.swing.GroupLayout pnlQuestionario1InternoLayout = new javax.swing.GroupLayout(pnlQuestionario1Interno);
        pnlQuestionario1Interno.setLayout(pnlQuestionario1InternoLayout);
        pnlQuestionario1InternoLayout.setHorizontalGroup(
            pnlQuestionario1InternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlQuestionario1InternoLayout.createSequentialGroup()
                .addGroup(pnlQuestionario1InternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlQuestionario1InternoLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnCancelar5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnContinuar5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnFinalizar5))
                    .addGroup(pnlQuestionario1InternoLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(pnlQuestionario1InternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlQuestionario1InternoLayout.createSequentialGroup()
                                .addComponent(lblMedicamentoContinuo)
                                .addGap(368, 663, Short.MAX_VALUE))
                            .addGroup(pnlQuestionario1InternoLayout.createSequentialGroup()
                                .addGroup(pnlQuestionario1InternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(lblDiarreia, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(sSeparador35)
                                    .addComponent(tfDiarreia)
                                    .addComponent(sSeparador34)
                                    .addComponent(tfCefaleia)
                                    .addComponent(lblCefaleia, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(sSeparador33)
                                    .addComponent(tfPressaoArterial)
                                    .addComponent(lblPressaoArterial, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(sSeparador32)
                                    .addComponent(tfEpistaxe)
                                    .addComponent(lblEpistaxe, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(sSeparador31)
                                    .addComponent(tfDesmaios)
                                    .addComponent(lblDesmaios, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(sSeparador30)
                                    .addComponent(tfMedicamentoContinuo))
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        pnlQuestionario1InternoLayout.setVerticalGroup(
            pnlQuestionario1InternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlQuestionario1InternoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblMedicamentoContinuo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tfMedicamentoContinuo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sSeparador30, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblDesmaios)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tfDesmaios, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sSeparador31, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblEpistaxe)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tfEpistaxe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sSeparador32, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblPressaoArterial)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tfPressaoArterial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sSeparador33, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblCefaleia)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tfCefaleia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sSeparador34, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblDiarreia)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tfDiarreia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sSeparador35, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 49, Short.MAX_VALUE)
                .addGroup(pnlQuestionario1InternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnFinalizar5)
                    .addComponent(btnContinuar5)
                    .addComponent(btnCancelar5))
                .addContainerGap())
        );

        javax.swing.GroupLayout pnlQuestionario1Layout = new javax.swing.GroupLayout(pnlQuestionario1);
        pnlQuestionario1.setLayout(pnlQuestionario1Layout);
        pnlQuestionario1Layout.setHorizontalGroup(
            pnlQuestionario1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlQuestionario1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlQuestionario1Interno, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnlQuestionario1Layout.setVerticalGroup(
            pnlQuestionario1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlQuestionario1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlQuestionario1Interno, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        tpCadastroPaciente.addTab("Questionário 1", pnlQuestionario1);

        pnlQuestionario2.setBackground(new java.awt.Color(255, 255, 255));

        pnlQuestionario2Interno.setBackground(new java.awt.Color(255, 255, 255));

        lblAnotacoesRelevantes.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        lblAnotacoesRelevantes.setForeground(new java.awt.Color(102, 102, 102));
        lblAnotacoesRelevantes.setText("ANOTAÇÕES RELEVANTES SOBRE A SAÚDE DO PACIENTE QUE NÃO CONSTA NA FICHA");

        lblColica.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        lblColica.setForeground(new java.awt.Color(102, 102, 102));
        lblColica.setText("COSTUMA SENTIR CÓLICA MENSTRUAL? SE SIM, O QUE USA PARA ALIVIÁ-LA? (MENINAS)");

        lblContatoEmergencia.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        lblContatoEmergencia.setForeground(new java.awt.Color(102, 102, 102));
        lblContatoEmergencia.setText("CONTATO DE EMERGÊNCIA (NOME E TELEFONE)");

        tfAcompanhamentoEspecializado.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        tfAcompanhamentoEspecializado.setForeground(new java.awt.Color(102, 102, 102));
        tfAcompanhamentoEspecializado.setBorder(null);

        tfAnotacoesRelevantes.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        tfAnotacoesRelevantes.setForeground(new java.awt.Color(102, 102, 102));
        tfAnotacoesRelevantes.setBorder(null);

        rbAcompanhamentoEspecializadoOutro.setBackground(new java.awt.Color(255, 255, 255));
        rbAcompanhamentoEspecializadoOutro.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        rbAcompanhamentoEspecializadoOutro.setForeground(new java.awt.Color(102, 102, 102));
        rbAcompanhamentoEspecializadoOutro.setText("OUTRO");
        rbAcompanhamentoEspecializadoOutro.setContentAreaFilled(false);
        rbAcompanhamentoEspecializadoOutro.setFocusPainted(false);
        rbAcompanhamentoEspecializadoOutro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbAcompanhamentoEspecializadoOutroActionPerformed(evt);
            }
        });

        tfColica.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        tfColica.setForeground(new java.awt.Color(102, 102, 102));
        tfColica.setBorder(null);

        rbFisioterapia.setBackground(new java.awt.Color(255, 255, 255));
        rbFisioterapia.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        rbFisioterapia.setForeground(new java.awt.Color(102, 102, 102));
        rbFisioterapia.setText("FISIOTERAPIA");
        rbFisioterapia.setContentAreaFilled(false);
        rbFisioterapia.setFocusPainted(false);

        tfContatoEmergencia.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        tfContatoEmergencia.setForeground(new java.awt.Color(102, 102, 102));
        tfContatoEmergencia.setBorder(null);

        rbFonaudiologo.setBackground(new java.awt.Color(255, 255, 255));
        rbFonaudiologo.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        rbFonaudiologo.setForeground(new java.awt.Color(102, 102, 102));
        rbFonaudiologo.setText("FONAUDIÓLOGO");
        rbFonaudiologo.setContentAreaFilled(false);
        rbFonaudiologo.setFocusPainted(false);

        btnCancelar6.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btnCancelar6.setForeground(new java.awt.Color(102, 102, 102));
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

        rbPsicologico.setBackground(new java.awt.Color(255, 255, 255));
        rbPsicologico.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        rbPsicologico.setForeground(new java.awt.Color(102, 102, 102));
        rbPsicologico.setText("PSICOLÓGICO");
        rbPsicologico.setContentAreaFilled(false);
        rbPsicologico.setFocusPainted(false);

        rbTerapiaOcupacional.setBackground(new java.awt.Color(255, 255, 255));
        rbTerapiaOcupacional.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        rbTerapiaOcupacional.setForeground(new java.awt.Color(102, 102, 102));
        rbTerapiaOcupacional.setText("TERAPIA OCUPACIONAL");
        rbTerapiaOcupacional.setContentAreaFilled(false);
        rbTerapiaOcupacional.setFocusPainted(false);

        lblAcompanhamentoEspecializado.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        lblAcompanhamentoEspecializado.setForeground(new java.awt.Color(102, 102, 102));
        lblAcompanhamentoEspecializado.setText("FAZ ACOMPANHAMENTO ESPECIALIZADO?");

        btnFinalizar6.setBackground(new java.awt.Color(102, 102, 102));
        btnFinalizar6.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btnFinalizar6.setForeground(new java.awt.Color(102, 102, 102));
        btnFinalizar6.setText("FINALIZAR");
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
        });
        btnFinalizar6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFinalizar6ActionPerformed(evt);
            }
        });

        sSeparador36.setForeground(new java.awt.Color(51, 51, 51));

        sSeparador37.setForeground(new java.awt.Color(51, 51, 51));

        sSeparador38.setForeground(new java.awt.Color(51, 51, 51));

        sSeparador39.setForeground(new java.awt.Color(51, 51, 51));

        javax.swing.GroupLayout pnlQuestionario2InternoLayout = new javax.swing.GroupLayout(pnlQuestionario2Interno);
        pnlQuestionario2Interno.setLayout(pnlQuestionario2InternoLayout);
        pnlQuestionario2InternoLayout.setHorizontalGroup(
            pnlQuestionario2InternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlQuestionario2InternoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlQuestionario2InternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlQuestionario2InternoLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnCancelar6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnFinalizar6))
                    .addGroup(pnlQuestionario2InternoLayout.createSequentialGroup()
                        .addGroup(pnlQuestionario2InternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblColica, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblAcompanhamentoEspecializado)
                            .addGroup(pnlQuestionario2InternoLayout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addGroup(pnlQuestionario2InternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(pnlQuestionario2InternoLayout.createSequentialGroup()
                                        .addComponent(rbPsicologico)
                                        .addGap(15, 15, 15)
                                        .addComponent(rbFonaudiologo)
                                        .addGap(10, 10, 10)
                                        .addComponent(rbFisioterapia)
                                        .addGap(16, 16, 16)
                                        .addComponent(rbTerapiaOcupacional)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlQuestionario2InternoLayout.createSequentialGroup()
                                        .addGroup(pnlQuestionario2InternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addGroup(pnlQuestionario2InternoLayout.createSequentialGroup()
                                                .addGap(0, 0, Short.MAX_VALUE)
                                                .addComponent(sSeparador37, javax.swing.GroupLayout.PREFERRED_SIZE, 530, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlQuestionario2InternoLayout.createSequentialGroup()
                                                .addComponent(rbAcompanhamentoEspecializadoOutro)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(tfAcompanhamentoEspecializado)))
                                        .addGap(154, 154, 154))))
                            .addComponent(sSeparador36)
                            .addComponent(tfColica)
                            .addComponent(lblAnotacoesRelevantes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(tfAnotacoesRelevantes)
                            .addComponent(sSeparador38)
                            .addComponent(lblContatoEmergencia, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(tfContatoEmergencia)
                            .addComponent(sSeparador39))
                        .addGap(0, 185, Short.MAX_VALUE)))
                .addContainerGap())
        );
        pnlQuestionario2InternoLayout.setVerticalGroup(
            pnlQuestionario2InternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlQuestionario2InternoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblColica)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tfColica, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sSeparador36, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblAcompanhamentoEspecializado)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlQuestionario2InternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rbPsicologico)
                    .addComponent(rbFonaudiologo)
                    .addComponent(rbFisioterapia)
                    .addComponent(rbTerapiaOcupacional))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlQuestionario2InternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rbAcompanhamentoEspecializadoOutro)
                    .addComponent(tfAcompanhamentoEspecializado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sSeparador37, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblAnotacoesRelevantes)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tfAnotacoesRelevantes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sSeparador38, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblContatoEmergencia)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tfContatoEmergencia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sSeparador39, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 110, Short.MAX_VALUE)
                .addGroup(pnlQuestionario2InternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnFinalizar6)
                    .addComponent(btnCancelar6))
                .addContainerGap())
        );

        tfAcompanhamentoEspecializado.setEnabled(false);

        javax.swing.GroupLayout pnlQuestionario2Layout = new javax.swing.GroupLayout(pnlQuestionario2);
        pnlQuestionario2.setLayout(pnlQuestionario2Layout);
        pnlQuestionario2Layout.setHorizontalGroup(
            pnlQuestionario2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlQuestionario2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlQuestionario2Interno, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnlQuestionario2Layout.setVerticalGroup(
            pnlQuestionario2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlQuestionario2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlQuestionario2Interno, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        tpCadastroPaciente.addTab("Questionário 2", pnlQuestionario2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(tpCadastroPaciente, javax.swing.GroupLayout.PREFERRED_SIZE, 1000, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tpCadastroPaciente, javax.swing.GroupLayout.PREFERRED_SIZE, 550, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void rbParentesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbParentesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rbParentesActionPerformed

    private void rbPlanoSaudeNaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbPlanoSaudeNaoActionPerformed
        // TODO add your handling code here:
        lblQualPlanoSaude.setEnabled(false);
        tfPlanoSaude.setEnabled(false);
        tfPlanoSaude.setText("Não possui");
    }//GEN-LAST:event_rbPlanoSaudeNaoActionPerformed

    private void rbPlanoSaudeSimActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbPlanoSaudeSimActionPerformed
        // TODO add your handling code here:
        lblQualPlanoSaude.setEnabled(true);
        tfPlanoSaude.setEnabled(true);
        tfPlanoSaude.setText("");
    }//GEN-LAST:event_rbPlanoSaudeSimActionPerformed

    private void rbSusNaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbSusNaoActionPerformed
        // TODO add your handling code here:
        tfNSus.setText("Não possui");
        tfNSus.setEnabled(false);
    }//GEN-LAST:event_rbSusNaoActionPerformed

    private void rbSusSimActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbSusSimActionPerformed
        // TODO add your handling code here:
        tfNSus.setText("");
        tfNSus.setEnabled(true);
    }//GEN-LAST:event_rbSusSimActionPerformed

    private void btnCancelar1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCancelar1MouseEntered
        // TODO add your handling code here:
        this.entraMouse(btnCancelar1);
    }//GEN-LAST:event_btnCancelar1MouseEntered

    private void btnCancelar1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCancelar1MouseExited
        // TODO add your handling code here:
        this.saiMouse(btnCancelar1);
    }//GEN-LAST:event_btnCancelar1MouseExited

    private void btnCancelar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelar1ActionPerformed
        // TODO add your handling code here:
        this.sair();
    }//GEN-LAST:event_btnCancelar1ActionPerformed

    private void btnContinuar1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnContinuar1MouseEntered
        // TODO add your handling code here:
        this.entraMouse(btnContinuar1);
    }//GEN-LAST:event_btnContinuar1MouseEntered

    private void btnContinuar1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnContinuar1MouseExited
        // TODO add your handling code here:
        this.saiMouse(btnContinuar1);
    }//GEN-LAST:event_btnContinuar1MouseExited

    private void btnContinuar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnContinuar1ActionPerformed
        // TODO add your handling code here:
        tpCadastroPaciente.setSelectedIndex(1);
    }//GEN-LAST:event_btnContinuar1ActionPerformed

    private void tfRuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfRuaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfRuaActionPerformed

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

    private void btnCancelar2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCancelar2MouseEntered
        // TODO add your handling code here:
        this.entraMouse(btnCancelar2);
    }//GEN-LAST:event_btnCancelar2MouseEntered

    private void btnCancelar2MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCancelar2MouseExited
        // TODO add your handling code here:
        this.saiMouse(btnCancelar2);
    }//GEN-LAST:event_btnCancelar2MouseExited

    private void btnCancelar2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelar2ActionPerformed
        // TODO add your handling code here:
        this.sair();
    }//GEN-LAST:event_btnCancelar2ActionPerformed

    private void btnContinuar2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnContinuar2MouseEntered
        // TODO add your handling code here:
        this.entraMouse(btnContinuar2);
    }//GEN-LAST:event_btnContinuar2MouseEntered

    private void btnContinuar2MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnContinuar2MouseExited
        // TODO add your handling code here:
        this.saiMouse(btnContinuar2);
    }//GEN-LAST:event_btnContinuar2MouseExited

    private void btnContinuar2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnContinuar2ActionPerformed
        // TODO add your handling code here:
        tpCadastroPaciente.setSelectedIndex(2);
    }//GEN-LAST:event_btnContinuar2ActionPerformed

    private void rbCirurgiasNaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbCirurgiasNaoActionPerformed
        // TODO add your handling code here:
        tfCirurgiasRealizadas.setEnabled(false);
    }//GEN-LAST:event_rbCirurgiasNaoActionPerformed

    private void rbCirurgiasSimActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbCirurgiasSimActionPerformed
        // TODO add your handling code here:
        tfCirurgiasRealizadas.setEnabled(true);
    }//GEN-LAST:event_rbCirurgiasSimActionPerformed

    private void btnCancelar3MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCancelar3MouseEntered
        // TODO add your handling code here:
        this.entraMouse(btnCancelar3);
    }//GEN-LAST:event_btnCancelar3MouseEntered

    private void btnCancelar3MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCancelar3MouseExited
        // TODO add your handling code here:
        this.saiMouse(btnCancelar3);
    }//GEN-LAST:event_btnCancelar3MouseExited

    private void btnCancelar3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelar3ActionPerformed
        // TODO add your handling code here:
        this.sair();
    }//GEN-LAST:event_btnCancelar3ActionPerformed

    private void btnContinuar3MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnContinuar3MouseEntered
        // TODO add your handling code here:
        this.entraMouse(btnContinuar3);
    }//GEN-LAST:event_btnContinuar3MouseEntered

    private void btnContinuar3MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnContinuar3MouseExited
        // TODO add your handling code here:
        this.saiMouse(btnContinuar3);
    }//GEN-LAST:event_btnContinuar3MouseExited

    private void btnContinuar3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnContinuar3ActionPerformed
        // TODO add your handling code here:
        tpCadastroPaciente.setSelectedIndex(3);
    }//GEN-LAST:event_btnContinuar3ActionPerformed

    private void rbDiabetesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbDiabetesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rbDiabetesActionPerformed

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

    private void rbSuperdotacaojRadioButton42ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbSuperdotacaojRadioButton42ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rbSuperdotacaojRadioButton42ActionPerformed

    private void btnContinuar4MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnContinuar4MouseEntered
        // TODO add your handling code here:
        this.entraMouse(btnContinuar4);
    }//GEN-LAST:event_btnContinuar4MouseEntered

    private void btnContinuar4MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnContinuar4MouseExited
        // TODO add your handling code here:
        this.saiMouse(btnContinuar4);
    }//GEN-LAST:event_btnContinuar4MouseExited

    private void btnContinuar4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnContinuar4ActionPerformed
        // TODO add your handling code here:
        tpCadastroPaciente.setSelectedIndex(4);
    }//GEN-LAST:event_btnContinuar4ActionPerformed

    private void btnCancelar4MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCancelar4MouseEntered
        // TODO add your handling code here:
        this.entraMouse(btnCancelar4);
    }//GEN-LAST:event_btnCancelar4MouseEntered

    private void btnCancelar4MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCancelar4MouseExited
        // TODO add your handling code here:
        this.saiMouse(btnCancelar4);
    }//GEN-LAST:event_btnCancelar4MouseExited

    private void btnCancelar4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelar4ActionPerformed
        // TODO add your handling code here:
        this.sair();
    }//GEN-LAST:event_btnCancelar4ActionPerformed

    private void rbCronicaOutrosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbCronicaOutrosActionPerformed
        // TODO add your handling code here:
        if (rbCronicaOutros.isSelected()) {
            tfDoencaCronica.setEnabled(true);
        } else {
            tfDoencaCronica.setEnabled(false);
        }
    }//GEN-LAST:event_rbCronicaOutrosActionPerformed

    private void rbPressaoAltajRadioButton59ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbPressaoAltajRadioButton59ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rbPressaoAltajRadioButton59ActionPerformed

    private void btnContinuar5MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnContinuar5MouseEntered
        // TODO add your handling code here:
        this.entraMouse(btnContinuar5);
    }//GEN-LAST:event_btnContinuar5MouseEntered

    private void btnContinuar5MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnContinuar5MouseExited
        // TODO add your handling code here:
        this.saiMouse(btnContinuar5);
    }//GEN-LAST:event_btnContinuar5MouseExited

    private void btnContinuar5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnContinuar5ActionPerformed
        // TODO add your handling code here:
        tpCadastroPaciente.setSelectedIndex(5);
    }//GEN-LAST:event_btnContinuar5ActionPerformed

    private void btnCancelar5MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCancelar5MouseEntered
        // TODO add your handling code here:
        this.entraMouse(btnCancelar5);
    }//GEN-LAST:event_btnCancelar5MouseEntered

    private void btnCancelar5MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCancelar5MouseExited
        // TODO add your handling code here:
        this.saiMouse(btnCancelar5);
    }//GEN-LAST:event_btnCancelar5MouseExited

    private void btnCancelar5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelar5ActionPerformed
        // TODO add your handling code here:
        this.sair();
    }//GEN-LAST:event_btnCancelar5ActionPerformed

    private void rbAcompanhamentoEspecializadoOutroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbAcompanhamentoEspecializadoOutroActionPerformed
        // TODO add your handling code here:
        if (rbAcompanhamentoEspecializadoOutro.isSelected()) {
            tfAcompanhamentoEspecializado.setEnabled(true);
        } else {
            tfAcompanhamentoEspecializado.setEnabled(false);
        }
    }//GEN-LAST:event_rbAcompanhamentoEspecializadoOutroActionPerformed

    private void btnCancelar6MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCancelar6MouseEntered
        // TODO add your handling code here:
        this.entraMouse(btnCancelar6);
    }//GEN-LAST:event_btnCancelar6MouseEntered

    private void btnCancelar6MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCancelar6MouseExited
        // TODO add your handling code here:
        this.saiMouse(btnCancelar6);
    }//GEN-LAST:event_btnCancelar6MouseExited

    private void btnCancelar6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelar6ActionPerformed
        // TODO add your handling code here:
        this.sair();
    }//GEN-LAST:event_btnCancelar6ActionPerformed

    private void tfNomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfNomeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfNomeActionPerformed

    private void tfPaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfPaiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfPaiActionPerformed

    private void btnFinalizar1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnFinalizar1MouseEntered
        // TODO add your handling code here:
        this.entraMouse(btnFinalizar1);
    }//GEN-LAST:event_btnFinalizar1MouseEntered

    private void btnFinalizar1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnFinalizar1MouseExited
        // TODO add your handling code here:
        this.saiMouse(btnFinalizar1);
    }//GEN-LAST:event_btnFinalizar1MouseExited

    private void btnFinalizar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFinalizar1ActionPerformed
        // TODO add your handling code here:
        this.botaoFinalizar();
    }//GEN-LAST:event_btnFinalizar1ActionPerformed

    private void btnFinalizar2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnFinalizar2MouseEntered
        // TODO add your handling code here:
        this.entraMouse(btnFinalizar2);
    }//GEN-LAST:event_btnFinalizar2MouseEntered

    private void btnFinalizar2MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnFinalizar2MouseExited
        // TODO add your handling code here:
        this.saiMouse(btnFinalizar2);
    }//GEN-LAST:event_btnFinalizar2MouseExited

    private void btnFinalizar2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFinalizar2ActionPerformed
        // TODO add your handling code here:
        this.botaoFinalizar();
    }//GEN-LAST:event_btnFinalizar2ActionPerformed

    private void btnFinalizar3MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnFinalizar3MouseEntered
        // TODO add your handling code here:
        this.entraMouse(btnFinalizar3);
    }//GEN-LAST:event_btnFinalizar3MouseEntered

    private void btnFinalizar3MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnFinalizar3MouseExited
        // TODO add your handling code here:
        this.saiMouse(btnFinalizar3);
    }//GEN-LAST:event_btnFinalizar3MouseExited

    private void btnFinalizar3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFinalizar3ActionPerformed
        // TODO add your handling code here:
        this.botaoFinalizar();
    }//GEN-LAST:event_btnFinalizar3ActionPerformed

    private void btnFinalizar4MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnFinalizar4MouseEntered
        // TODO add your handling code here:
        this.entraMouse(btnFinalizar4);
    }//GEN-LAST:event_btnFinalizar4MouseEntered

    private void btnFinalizar4MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnFinalizar4MouseExited
        // TODO add your handling code here:
        this.saiMouse(btnFinalizar4);
    }//GEN-LAST:event_btnFinalizar4MouseExited

    private void btnFinalizar4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFinalizar4ActionPerformed
        // TODO add your handling code here:
        this.botaoFinalizar();
    }//GEN-LAST:event_btnFinalizar4ActionPerformed

    private void btnFinalizar5MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnFinalizar5MouseEntered
        // TODO add your handling code here:
        this.entraMouse(btnFinalizar5);
    }//GEN-LAST:event_btnFinalizar5MouseEntered

    private void btnFinalizar5MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnFinalizar5MouseExited
        // TODO add your handling code here:
        this.saiMouse(btnFinalizar5);
    }//GEN-LAST:event_btnFinalizar5MouseExited

    private void btnFinalizar5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFinalizar5ActionPerformed
        // TODO add your handling code here:
        this.botaoFinalizar();
    }//GEN-LAST:event_btnFinalizar5ActionPerformed

    private void btnFinalizar6MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnFinalizar6MouseEntered
        // TODO add your handling code here:
        this.entraMouse(btnFinalizar6);
    }//GEN-LAST:event_btnFinalizar6MouseEntered

    private void btnFinalizar6MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnFinalizar6MouseExited
        // TODO add your handling code here:
        this.saiMouse(btnFinalizar6);
    }//GEN-LAST:event_btnFinalizar6MouseExited

    private void btnFinalizar6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFinalizar6ActionPerformed
        // TODO add your handling code here:
        this.botaoFinalizar();
    }//GEN-LAST:event_btnFinalizar6ActionPerformed


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
    private javax.swing.JButton btnFinalizar1;
    private javax.swing.JButton btnFinalizar2;
    private javax.swing.JButton btnFinalizar3;
    private javax.swing.JButton btnFinalizar4;
    private javax.swing.JButton btnFinalizar5;
    private javax.swing.JButton btnFinalizar6;
    private javax.swing.JComboBox<String> cbCurso;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator10;
    private javax.swing.JSeparator jSeparator11;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JSeparator jSeparator9;
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
    private javax.swing.JSeparator sSeparador10;
    private javax.swing.JSeparator sSeparador11;
    private javax.swing.JSeparator sSeparador12;
    private javax.swing.JSeparator sSeparador13;
    private javax.swing.JSeparator sSeparador14;
    private javax.swing.JSeparator sSeparador15;
    private javax.swing.JSeparator sSeparador16;
    private javax.swing.JSeparator sSeparador17;
    private javax.swing.JSeparator sSeparador18;
    private javax.swing.JSeparator sSeparador19;
    private javax.swing.JSeparator sSeparador20;
    private javax.swing.JSeparator sSeparador21;
    private javax.swing.JSeparator sSeparador22;
    private javax.swing.JSeparator sSeparador23;
    private javax.swing.JSeparator sSeparador24;
    private javax.swing.JSeparator sSeparador25;
    private javax.swing.JSeparator sSeparador26;
    private javax.swing.JSeparator sSeparador27;
    private javax.swing.JSeparator sSeparador28;
    private javax.swing.JSeparator sSeparador29;
    private javax.swing.JSeparator sSeparador30;
    private javax.swing.JSeparator sSeparador31;
    private javax.swing.JSeparator sSeparador32;
    private javax.swing.JSeparator sSeparador33;
    private javax.swing.JSeparator sSeparador34;
    private javax.swing.JSeparator sSeparador35;
    private javax.swing.JSeparator sSeparador36;
    private javax.swing.JSeparator sSeparador37;
    private javax.swing.JSeparator sSeparador38;
    private javax.swing.JSeparator sSeparador39;
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
