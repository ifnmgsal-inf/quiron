/*
 * Tela para o cadastro de Cartão de Vacina do paciente
 */
package cartaovacina;

import bancodedados.MysqlConnect;
import java.awt.Color;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import menssagensalerta.MinhasMenssagens;
import principal.PesquisaPaciente;
import principal.TelaPrincipal;

/**
 *
 * @author Franciele Alves Barbosa e Rogério Costa Negro Rocha
 */
public class PnlAlterarCartao extends javax.swing.JPanel {

    Connection conn = null;
    /**
     * Creates new form pnlAlterarCartao
     */
    public PnlAlterarCartao() {
        initComponents();
        try {
            conn = MysqlConnect.connectDB();
            this.imprimir();
        } catch (SQLException ex) {
            //JOptionPane.showMessageDialog(null, "Erro ao conectar com o Banco de Dados " /*+ ex.getMessage()*/, "ERRO", JOptionPane.ERROR_MESSAGE);
            MinhasMenssagens.chamarMenssagemErro("Erro ao conectar com o Banco de Dados ");
        }
    }
    
    public void imprimir() {
        this.imprimirIdentificacao();
        this.imprimirDuplaAdulto();
        this.imprimirInfluenza();
        this.imprimirFebre();
        this.imprimirDuplaViral();
        this.imprimirTripliceViral();
        this.imprimirOutras();
    }
    
    public void sair() {
        //int op = JOptionPane.showConfirmDialog(null, "Deseja realmente sair?", "ATENÇÃO", JOptionPane.YES_OPTION);
        int op = MinhasMenssagens.chamarMenssagemOpcaoSair();
        if (op == JOptionPane.YES_OPTION) {
            TelaPrincipal.voltarHome();
        }
    }

    public void botaoFinalizar() {
        this.atualizarCartao();
        this.atualizarDuplaAdulto();
        this.atualizarInfluenza();
        this.atualizarFebreAmarela();
        this.atualizarDuplaViral();
        this.atualizarTripliceViral();
        this.atualizarOutras();
        MinhasMenssagens.chamarMenssagemSucesso("Cartão atualizado com sucesso");
        TelaPrincipal.voltarHome();
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

    public void imprimirIdentificacao() {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String qry = "SELECT idPaciente, cpf, nome, dn, tipoSanguineo, rua,"
                + " numero, bairro, municipio, uf, telefone, grs"
                + " FROM cartaovacina WHERE idPaciente= ?";

        try {
            pstmt = conn.prepareStatement(qry);
            pstmt.setString(1, PesquisaPaciente.id);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                tfNome.setText(rs.getString("nome"));
                tfCpf.setText(rs.getString("cpf"));
                tfIdPaciente.setText(rs.getString("idPaciente"));
                tfDN.setText(rs.getString("dn"));
                tfTipoSanguineo.setText(rs.getString("tipoSanguineo"));
                tfRua.setText(rs.getString("rua"));
                tfNCasa.setText(rs.getString("numero"));
                tfBairro.setText(rs.getString("bairro"));
                tfMunicipio.setText(rs.getString("municipio"));
                tfUF.setText(rs.getString("uf"));
                tfTelefone.setText(rs.getString("telefone"));
                tfGRS.setText(rs.getString("grs"));
            }
        } catch (SQLException ex) {
            //JOptionPane.showMessageDialog(null, "Erro: " + ex.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
            MinhasMenssagens.chamarMenssagemErro(ex.getMessage());
        }
    }

    public void imprimirDuplaAdulto() {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String qry = "SELECT dose1, lote1, validade1, dose2, lote2, validade2,"
                + " dose3, lote3, validade3, reforco, loteReforco,"
                + " validadeReforco FROM cartaovacina WHERE idPaciente= ?";

        try {
            pstmt = conn.prepareStatement(qry);
            pstmt.setString(1, tfIdPaciente.getText());
            rs = pstmt.executeQuery();

            while (rs.next()) {
                tfDose1.setText(rs.getString("dose1"));
                tfLote.setText(rs.getString("lote1"));
                tfVal1.setText(rs.getString("validade1"));
                tfDose2.setText(rs.getString("dose2"));
                tfLote2.setText(rs.getString("lote2"));
                tfVal2.setText(rs.getString("validade2"));
                tfDose3.setText(rs.getString("dose3"));
                tfLote3.setText(rs.getString("lote3"));
                tfVal3.setText(rs.getString("validade3"));
                tfReforco.setText(rs.getString("reforco"));
                tfLoteReforco.setText(rs.getString("loteReforco"));
                tfValReforco.setText(rs.getString("validadeReforco"));
            }
        } catch (SQLException ex) {
            //JOptionPane.showMessageDialog(null, "Erro: " + ex.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
            MinhasMenssagens.chamarMenssagemErro(ex.getMessage());
        }
    }

    public void imprimirInfluenza() {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String qry = "SELECT influenza1, loteInfluenza1, valInfluenza1,"
                + " influenza2, loteInfluenza2, valInfluenza2,"
                + " influenza3, loteInfluenza3, valInfluenza3,"
                + " influenza4, loteInfluenza4, valInfluenza4,"
                + " influenza5, loteInfluenza5, valInfluenza5,"
                + " influenza6, loteInfluenza6, valInfluenza6,"
                + " influenza7, loteInfluenza7, valInfluenza7,"
                + " influenza8, loteInfluenza8, valInfluenza8"
                + " FROM cartaovacina WHERE idPaciente= ?";

        try {
            pstmt = conn.prepareStatement(qry);
            pstmt.setString(1, tfIdPaciente.getText());
            rs = pstmt.executeQuery();

            while (rs.next()) {
                tfInfluenza1.setText(rs.getString("influenza1"));
                tfLoteInfluenza1.setText(rs.getString("loteInfluenza1"));
                tfValInfluenza1.setText(rs.getString("valInfluenza1"));
                tfInfluenza2.setText(rs.getString("influenza2"));
                tfLoteInfluenza2.setText(rs.getString("loteInfluenza2"));
                tfValInfluenza2.setText(rs.getString("valInfluenza2"));
                tfInfluenza3.setText(rs.getString("influenza3"));
                tfLoteInfluenza3.setText(rs.getString("loteInfluenza3"));
                tfValInfluenza3.setText(rs.getString("valInfluenza3"));
                tfInfluenza4.setText(rs.getString("influenza4"));
                tfLoteInfluenza4.setText(rs.getString("loteInfluenza4"));
                tfValInfluenza4.setText(rs.getString("valInfluenza4"));
                tfInfluenza5.setText(rs.getString("influenza5"));
                tfLoteInfluenza5.setText(rs.getString("loteInfluenza5"));
                tfValInfluenza5.setText(rs.getString("valInfluenza5"));
                tfInfluenza6.setText(rs.getString("influenza6"));
                tfLoteInfluenza6.setText(rs.getString("loteInfluenza6"));
                tfValInfluenza6.setText(rs.getString("valInfluenza6"));
                tfInfluenza7.setText(rs.getString("influenza7"));
                tfLoteInfluenza7.setText(rs.getString("loteInfluenza7"));
                tfValInfluenza7.setText(rs.getString("valInfluenza7"));
                tfInfluenza8.setText(rs.getString("influenza8"));
                tfLoteInfluenza8.setText(rs.getString("loteInfluenza8"));
                tfValInfluenza8.setText(rs.getString("valInfluenza8"));

            }
        } catch (SQLException ex) {
            //JOptionPane.showMessageDialog(null, "Erro: " + ex.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
            MinhasMenssagens.chamarMenssagemErro(ex.getMessage());
        }
    }

    public void imprimirFebre() {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String qry = "SELECT febre1, loteFebre1, valFebre1,"
                + " febre2, loteFebre2, valFebre2,"
                + " febre3, loteFebre3, valFebre3,"
                + " febre4, loteFebre4, valFebre4,"
                + " febre5, loteFebre5, valFebre5,"
                + " febre6, loteFebre6, valFebre6,"
                + " febre7, loteFebre7, valFebre7,"
                + " febre8, loteFebre8, valFebre8"
                + " FROM cartaovacina WHERE idPaciente= ?";

        try {
            pstmt = conn.prepareStatement(qry);
            pstmt.setString(1, tfIdPaciente.getText());
            rs = pstmt.executeQuery();

            while (rs.next()) {
                tfFebre1.setText(rs.getString("febre1"));
                tfLoteFebre1.setText(rs.getString("loteFebre1"));
                tfValFebre1.setText(rs.getString("valFebre1"));
                tfFebre2.setText(rs.getString("febre2"));
                tfLoteFebre2.setText(rs.getString("loteFebre2"));
                tfValFebre2.setText(rs.getString("valFebre2"));
                tfFebre3.setText(rs.getString("febre3"));
                tfLoteFebre3.setText(rs.getString("loteFebre3"));
                tfValFebre3.setText(rs.getString("valFebre3"));
                tfFebre4.setText(rs.getString("febre4"));
                tfLoteFebre4.setText(rs.getString("loteFebre4"));
                tfValFebre4.setText(rs.getString("valFebre4"));
                tfFebre5.setText(rs.getString("febre5"));
                tfLoteFebre5.setText(rs.getString("loteFebre5"));
                tfValFebre5.setText(rs.getString("valFebre5"));
                tfFebre6.setText(rs.getString("febre6"));
                tfLoteFebre6.setText(rs.getString("loteFebre6"));
                tfValFebre6.setText(rs.getString("valFebre6"));
                tfFebre7.setText(rs.getString("febre7"));
                tfLoteFebre7.setText(rs.getString("loteFebre7"));
                tfValFebre7.setText(rs.getString("valFebre7"));
                tfFebre8.setText(rs.getString("febre8"));
                tfLoteFebre8.setText(rs.getString("loteFebre8"));
                tfValFebre8.setText(rs.getString("valFebre8"));
            }
        } catch (SQLException ex) {
            //JOptionPane.showMessageDialog(null, "Erro: " + ex.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
            MinhasMenssagens.chamarMenssagemErro(ex.getMessage());
        }
    }

    public void imprimirDuplaViral() {
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        String qry = "SELECT duplaViral1, loteDupla1, valDupla1,"
                + " duplaViral2, loteDupla2, valDupla2"
                + " FROM cartaovacina WHERE idPaciente= ?";

        try {
            pstmt = conn.prepareStatement(qry);
            pstmt.setString(1, tfIdPaciente.getText());
            rs = pstmt.executeQuery();

            while (rs.next()) {
                tfDuplaViral1.setText(rs.getString("duplaViral1"));
                tfLoteDupla1.setText(rs.getString("loteDupla1"));
                tfValDupla1.setText(rs.getString("valDupla1"));
                tfDuplaViral2.setText(rs.getString("duplaViral2"));
                tfLoteDuplaViral2.setText(rs.getString("loteDupla2"));
                tfValDupla2.setText(rs.getString("valDupla2"));
            }
        } catch (SQLException ex) {
            //JOptionPane.showMessageDialog(null, "Erro: " + ex.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
            MinhasMenssagens.chamarMenssagemErro(ex.getMessage());
        }
    }

    public void imprimirTripliceViral() {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String qry = "SELECT triplice1, loteTriplice1, valTriplice1,"
                + " triplice2, loteTriplice2, valTriplice2"
                + " FROM cartaovacina WHERE idPaciente= ?";

        try {
            pstmt = conn.prepareStatement(qry);
            pstmt.setString(1, tfIdPaciente.getText());
            rs = pstmt.executeQuery();

            while (rs.next()) {
                tfTriplice1.setText(rs.getString("triplice1"));
                tfLoteTriplice1.setText(rs.getString("loteTriplice1"));
                tfValTriplice1.setText(rs.getString("valTriplice1"));
                tfTriplice2.setText(rs.getString("triplice2"));
                tfLoteTriplice2.setText(rs.getString("loteTriplice2"));
                tfValTriplice2.setText(rs.getString("valTriplice2"));
            }
        } catch (SQLException ex) {
            //JOptionPane.showMessageDialog(null, "Erro: " + ex.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
            MinhasMenssagens.chamarMenssagemErro(ex.getMessage());
        }
    }

    public void imprimirOutras() {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String qry = "SELECT outra1, dtOutra1, loteOutra1, valOutra1, outra1d2,"
                + " dtOutra1d2, loteOutra1d2, valOutra1d2, outra2, dtOutra2,"
                + " loteOutra2, valOutra2, outra2d2, dtOutra2d2, loteOutra2d2, valOutra2d2"
                + " FROM cartaovacina WHERE idPaciente= ?";

        try {
            pstmt = conn.prepareStatement(qry);
            pstmt.setString(1, tfIdPaciente.getText());
            rs = pstmt.executeQuery();

            while (rs.next()) {
                tfOutra1.setText(rs.getString("outra1"));
                tfDtOutra1.setText(rs.getString("dtOutra1"));
                tfLoteOutra1.setText(rs.getString("loteOutra1"));
                tfValOutra1.setText(rs.getString("valOutra1"));
                tfOutra1d2.setText(rs.getString("outra1d2"));
                tfDtOutra1d2.setText(rs.getString("dtOutra1d2"));
                tfLoteOutra1d2.setText(rs.getString("loteOutra1d2"));
                tfValOutra1d2.setText(rs.getString("valOutra1d2"));

                tfOutra2.setText(rs.getString("outra2"));
                tfDtOutra2.setText(rs.getString("dtOutra2"));
                tfLoteOutra2.setText(rs.getString("loteOutra2"));
                tfValOutra2.setText(rs.getString("valOutra2"));
                tfOutra2d2.setText(rs.getString("outra2d2"));
                tfDtOutra2d2.setText(rs.getString("dtOutra2d2"));
                tfLoteOutra2d2.setText(rs.getString("loteOutra2d2"));
                tfValOutra2d2.setText(rs.getString("valOutra2d2"));

            }
        } catch (SQLException ex) {
            //JOptionPane.showMessageDialog(null, "Erro: " + ex.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
            MinhasMenssagens.chamarMenssagemErro(ex.getMessage());
        }
    }

    public void atualizarCartao() {
        PreparedStatement pstmt = null;
        String qry = "UPDATE CartaoVacina SET grs= ? WHERE idPaciente= ?";

        try {
            pstmt = conn.prepareStatement(qry);

            pstmt.setString(1, tfGRS.getText());
            pstmt.setString(2, tfIdPaciente.getText());

            pstmt.executeUpdate();

            //JOptionPane.showMessageDialog(null, "Salvo com sucesso!");

        } catch (SQLException ex) {
            //JOptionPane.showMessageDialog(null, "Erro ao salvar: " + ex.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
            MinhasMenssagens.chamarMenssagemErro(ex.getMessage());
        }
    }

    public void atualizarDuplaAdulto() {
        PreparedStatement pstmt = null;
        String qry = "UPDATE CartaoVacina SET dose1= ?, lote1= ?, validade1= ?,"
                + " dose2= ?, lote2= ?, validade2= ?, dose3= ?, lote3= ?,"
                + " validade3= ?, reforco= ?, loteReforco= ?, validadeReforco= ?"
                + " WHERE idPaciente= ? ";

        try {
            pstmt = conn.prepareStatement(qry);
            pstmt.setString(1, tfDose1.getText());
            pstmt.setString(2, tfLote.getText());
            pstmt.setString(3, tfVal1.getText());
            pstmt.setString(4, tfDose2.getText());
            pstmt.setString(5, tfLote2.getText());
            pstmt.setString(6, tfVal2.getText());
            pstmt.setString(7, tfDose3.getText());
            pstmt.setString(8, tfLote3.getText());
            pstmt.setString(9, tfVal3.getText());
            pstmt.setString(10, tfReforco.getText());
            pstmt.setString(11, tfLoteReforco.getText());
            pstmt.setString(12, tfValReforco.getText());
            pstmt.setString(13, tfIdPaciente.getText());

            pstmt.executeUpdate();

            //JOptionPane.showMessageDialog(null, "Salvo com sucesso!");

        } catch (SQLException ex) {
            //JOptionPane.showMessageDialog(null, "Erro ao salvar: " + ex.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
            MinhasMenssagens.chamarMenssagemErro(ex.getMessage());
        }
    }

    public void atualizarInfluenza() {
        PreparedStatement pstmt = null;
        String qry = "UPDATE CartaoVacina SET influenza1= ?, loteInfluenza1= ?, valInfluenza1= ?, influenza2= ?, loteInfluenza2= ?, valInfluenza2= ?,"
                + " influenza3= ?, loteInfluenza3= ?, valInfluenza3= ?, influenza4= ?, loteInfluenza4= ?, valInfluenza4= ?,"
                + " influenza5= ?, loteInfluenza5= ?, valInfluenza5= ?, influenza6= ?, loteInfluenza6= ?, valInfluenza6= ?,"
                + " influenza7= ?, loteInfluenza7= ?, valInfluenza7= ?, influenza8= ?, loteInfluenza8= ?, valInfluenza8=?"
                + " WHERE idPaciente= ?";

        try {
            pstmt = conn.prepareStatement(qry);
            pstmt.setString(1, tfInfluenza1.getText());
            pstmt.setString(2, tfLoteInfluenza1.getText());
            pstmt.setString(3, tfValInfluenza1.getText());
            pstmt.setString(4, tfInfluenza2.getText());
            pstmt.setString(5, tfLoteInfluenza2.getText());
            pstmt.setString(6, tfValInfluenza2.getText());
            pstmt.setString(7, tfInfluenza3.getText());
            pstmt.setString(8, tfLoteInfluenza3.getText());
            pstmt.setString(9, tfValInfluenza3.getText());
            pstmt.setString(10, tfInfluenza4.getText());
            pstmt.setString(11, tfLoteInfluenza4.getText());
            pstmt.setString(12, tfValInfluenza4.getText());
            pstmt.setString(13, tfInfluenza5.getText());
            pstmt.setString(14, tfLoteInfluenza5.getText());
            pstmt.setString(15, tfValInfluenza5.getText());
            pstmt.setString(16, tfInfluenza6.getText());
            pstmt.setString(17, tfLoteInfluenza6.getText());
            pstmt.setString(18, tfValInfluenza6.getText());
            pstmt.setString(19, tfInfluenza7.getText());
            pstmt.setString(20, tfLoteInfluenza7.getText());
            pstmt.setString(21, tfValInfluenza7.getText());
            pstmt.setString(22, tfInfluenza8.getText());
            pstmt.setString(23, tfLoteInfluenza8.getText());
            pstmt.setString(24, tfValInfluenza8.getText());
            pstmt.setString(25, tfIdPaciente.getText());

            pstmt.executeUpdate();

            //JOptionPane.showMessageDialog(null, "Salvo com sucesso!");

        } catch (SQLException ex) {
            //JOptionPane.showMessageDialog(null, "Erro ao salvar: " + ex.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
            MinhasMenssagens.chamarMenssagemErro(ex.getMessage());
        }
    }

    public void atualizarFebreAmarela() {
        PreparedStatement pstmt = null;
        String qry = "UPDATE cartaovacina SET "
                + "febre1= ?, loteFebre1= ?, valFebre1= ?, febre2= ?, loteFebre2= ?, valFebre2= ?, "
                + "febre3= ?, loteFebre3= ?, valFebre3= ?, febre4= ?, loteFebre4= ?, valFebre4= ?, "
                + "febre5= ?, loteFebre5= ?, valFebre5= ?, febre6= ?, loteFebre6= ?, valFebre6= ?, "
                + "febre7= ?, loteFebre7= ?, valFebre7= ?, febre8= ?, loteFebre8= ?, valFebre8= ? "
                + "WHERE idPaciente= ?";

        try {
            pstmt = conn.prepareStatement(qry);
            pstmt.setString(1, tfFebre1.getText());
            pstmt.setString(2, tfLoteFebre1.getText());
            pstmt.setString(3, tfValFebre1.getText());
            pstmt.setString(4, tfFebre2.getText());
            pstmt.setString(5, tfLoteFebre2.getText());
            pstmt.setString(6, tfValFebre2.getText());
            pstmt.setString(7, tfFebre3.getText());
            pstmt.setString(8, tfLoteFebre3.getText());
            pstmt.setString(9, tfValFebre3.getText());
            pstmt.setString(10, tfFebre4.getText());
            pstmt.setString(11, tfLoteFebre4.getText());
            pstmt.setString(12, tfValFebre4.getText());
            pstmt.setString(13, tfFebre5.getText());
            pstmt.setString(14, tfLoteFebre5.getText());
            pstmt.setString(15, tfValFebre5.getText());
            pstmt.setString(16, tfFebre6.getText());
            pstmt.setString(17, tfLoteFebre6.getText());
            pstmt.setString(18, tfValFebre6.getText());
            pstmt.setString(19, tfFebre7.getText());
            pstmt.setString(20, tfLoteFebre7.getText());
            pstmt.setString(21, tfValFebre7.getText());
            pstmt.setString(22, tfFebre8.getText());
            pstmt.setString(23, tfLoteFebre8.getText());
            pstmt.setString(24, tfValFebre8.getText());
            pstmt.setString(25, tfIdPaciente.getText());

            pstmt.executeUpdate();

            //JOptionPane.showMessageDialog(null, "Salvo com sucesso!");

        } catch (SQLException ex) {
            //JOptionPane.showMessageDialog(null, "Erro ao salvar: " + ex.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
            MinhasMenssagens.chamarMenssagemErro(ex.getMessage());
        }
    }

    public void atualizarDuplaViral() {
        PreparedStatement pstmt = null;
        String qry = "UPDATE CartaoVacina SET duplaViral1= ?, loteDupla1= ?,"
                + " valDupla1= ?, duplaViral2= ?, loteDupla2= ?, valDupla2= ?"
                + " WHERE idPaciente= ?";

        try {
            pstmt = conn.prepareStatement(qry);
            pstmt.setString(1, tfDuplaViral1.getText());
            pstmt.setString(2, tfLoteDupla1.getText());
            pstmt.setString(3, tfValDupla1.getText());
            pstmt.setString(4, tfDuplaViral2.getText());
            pstmt.setString(5, tfLoteDuplaViral2.getText());
            pstmt.setString(6, tfValDupla2.getText());
            pstmt.setString(7, tfIdPaciente.getText());

            pstmt.executeUpdate();

            //JOptionPane.showMessageDialog(null, "Salvo com sucesso!");

        } catch (SQLException ex) {
            //JOptionPane.showMessageDialog(null, "Erro ao salvar: " + ex.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
            MinhasMenssagens.chamarMenssagemErro(ex.getMessage());
        }
    }

    public void atualizarTripliceViral() {
        PreparedStatement pstmt = null;
        String qry = "UPDATE CartaoVacina SET triplice1= ?, loteTriplice1= ?,"
                + " valTriplice1= ?, triplice2= ?, loteTriplice2= ?,"
                + " valTriplice2= ? WHERE idPaciente= ?";

        try {
            pstmt = conn.prepareStatement(qry);
            pstmt.setString(1, tfTriplice1.getText());
            pstmt.setString(2, tfLoteTriplice1.getText());
            pstmt.setString(3, tfValTriplice1.getText());
            pstmt.setString(4, tfTriplice2.getText());
            pstmt.setString(5, tfLoteTriplice2.getText());
            pstmt.setString(6, tfValTriplice2.getText());
            pstmt.setString(7, tfIdPaciente.getText());

            pstmt.executeUpdate();

            //JOptionPane.showMessageDialog(null, "Salvo com sucesso!");

        } catch (SQLException ex) {
            //JOptionPane.showMessageDialog(null, "Erro ao salvar: " + ex.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
            MinhasMenssagens.chamarMenssagemErro(ex.getMessage());
        }
    }

    public void atualizarOutras() {
        PreparedStatement pstmt = null;
        String qry = "UPDATE cartaovacina SET outra1= ?, dtOutra1= ?,"
                + " loteOutra1= ?, valOutra1= ?, outra1d2= ?, dtOutra1d2= ?,"
                + " loteOutra1d2= ?, valOutra1d2= ?,"
                + " outra2= ?, dtOutra2= ?, loteOutra2= ?, valOutra2= ?,"
                + " outra2d2= ?, dtOutra2d2= ?, loteOutra2d2= ?, valOutra2d2= ?"
                + " WHERE idPaciente= ?";

        try {
            pstmt = conn.prepareStatement(qry);
            pstmt.setString(1, tfOutra1.getText());
            pstmt.setString(2, tfDtOutra1.getText());
            pstmt.setString(3, tfLoteOutra1.getText());
            pstmt.setString(4, tfValOutra1.getText());
            pstmt.setString(5, tfOutra1d2.getText());
            pstmt.setString(6, tfDtOutra1d2.getText());
            pstmt.setString(7, tfLoteOutra1d2.getText());
            pstmt.setString(8, tfValOutra1d2.getText());

            pstmt.setString(9, tfOutra2.getText());
            pstmt.setString(10, tfDtOutra2.getText());
            pstmt.setString(11, tfLoteOutra2.getText());
            pstmt.setString(12, tfValOutra2.getText());
            pstmt.setString(13, tfOutra2d2.getText());
            pstmt.setString(14, tfDtOutra2d2.getText());
            pstmt.setString(15, tfLoteOutra2d2.getText());
            pstmt.setString(16, tfValOutra2d2.getText());
            pstmt.setString(17, tfIdPaciente.getText());

            pstmt.executeUpdate();

            //JOptionPane.showMessageDialog(null, "Salvo com sucesso!");

        } catch (SQLException ex) {
            //JOptionPane.showMessageDialog(null, "Erro ao salvar: " + ex.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
            MinhasMenssagens.chamarMenssagemErro(ex.getMessage());
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

        pnlCartao = new javax.swing.JTabbedPane();
        pnlDados = new javax.swing.JPanel();
        pnlDadosInterno = new javax.swing.JPanel();
        lblGRS = new javax.swing.JLabel();
        lblTipoSanguineo = new javax.swing.JLabel();
        tfGRS = new javax.swing.JTextField();
        tfTipoSanguineo = new javax.swing.JTextField();
        btnContinuar1 = new javax.swing.JButton();
        lblCpf = new javax.swing.JLabel();
        lblMunicipio = new javax.swing.JLabel();
        tfCpf = new javax.swing.JFormattedTextField();
        tfMunicipio = new javax.swing.JTextField();
        lblUF = new javax.swing.JLabel();
        lblNome = new javax.swing.JLabel();
        tfUF = new javax.swing.JTextField();
        tfNome = new javax.swing.JTextField();
        lblTelefone = new javax.swing.JLabel();
        lblDN = new javax.swing.JLabel();
        tfTelefone = new javax.swing.JTextField();
        tfDN = new javax.swing.JFormattedTextField();
        lblRua = new javax.swing.JLabel();
        tfRua = new javax.swing.JTextField();
        lblNumero = new javax.swing.JLabel();
        tfNCasa = new javax.swing.JTextField();
        lblBairro = new javax.swing.JLabel();
        tfBairro = new javax.swing.JTextField();
        lblIdPaciente = new javax.swing.JLabel();
        tfIdPaciente = new javax.swing.JTextField();
        sSeparador1 = new javax.swing.JSeparator();
        sSeparador2 = new javax.swing.JSeparator();
        sSeparador3 = new javax.swing.JSeparator();
        sSeparador4 = new javax.swing.JSeparator();
        sSeparador5 = new javax.swing.JSeparator();
        sSeparador6 = new javax.swing.JSeparator();
        sSeparador7 = new javax.swing.JSeparator();
        sSeparador8 = new javax.swing.JSeparator();
        sSeparador9 = new javax.swing.JSeparator();
        sSeparador10 = new javax.swing.JSeparator();
        sSeparador11 = new javax.swing.JSeparator();
        sSeparador12 = new javax.swing.JSeparator();
        btnCancelar1 = new javax.swing.JButton();
        btnFinalizar1 = new javax.swing.JButton();
        pnlDuplaAdulta = new javax.swing.JPanel();
        pnlDuplaAdultoInterno = new javax.swing.JPanel();
        tfVal3 = new javax.swing.JFormattedTextField();
        lblVal3 = new javax.swing.JLabel();
        lblLote3 = new javax.swing.JLabel();
        tfLote3 = new javax.swing.JTextField();
        tfDose3 = new javax.swing.JFormattedTextField();
        lblDose3 = new javax.swing.JLabel();
        tfValReforco = new javax.swing.JFormattedTextField();
        lblValReforco = new javax.swing.JLabel();
        lblLoteReforco = new javax.swing.JLabel();
        lblTitulo = new javax.swing.JLabel();
        tfLoteReforco = new javax.swing.JTextField();
        lblDose = new javax.swing.JLabel();
        tfDose1 = new javax.swing.JFormattedTextField();
        tfReforco = new javax.swing.JFormattedTextField();
        lblReforco = new javax.swing.JLabel();
        lblLote = new javax.swing.JLabel();
        tfLote = new javax.swing.JTextField();
        lblVal = new javax.swing.JLabel();
        tfVal1 = new javax.swing.JFormattedTextField();
        tfVal2 = new javax.swing.JFormattedTextField();
        lblVal2 = new javax.swing.JLabel();
        lblLote2 = new javax.swing.JLabel();
        tfLote2 = new javax.swing.JTextField();
        tfDose2 = new javax.swing.JFormattedTextField();
        lblDose2 = new javax.swing.JLabel();
        btnContinuar2 = new javax.swing.JButton();
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
        sSeparador23 = new javax.swing.JSeparator();
        sSeparador24 = new javax.swing.JSeparator();
        btnFinalizar2 = new javax.swing.JButton();
        btnCancelar2 = new javax.swing.JButton();
        pnlInfluenza = new javax.swing.JPanel();
        pnlInfluenzaInterno = new javax.swing.JPanel();
        lblLoteInfluenza1 = new javax.swing.JLabel();
        tfInfluenza7 = new javax.swing.JFormattedTextField();
        lblValInfluenza1 = new javax.swing.JLabel();
        tfLoteInfluenza7 = new javax.swing.JTextField();
        tfInfluenza3 = new javax.swing.JFormattedTextField();
        tfLoteInfluenza3 = new javax.swing.JTextField();
        tfValInfluenza7 = new javax.swing.JFormattedTextField();
        lblLoteInfluenza6 = new javax.swing.JLabel();
        tfValInfluenza3 = new javax.swing.JFormattedTextField();
        lblValInfluenza6 = new javax.swing.JLabel();
        lblLoteInfluenza2 = new javax.swing.JLabel();
        tfInfluenza8 = new javax.swing.JFormattedTextField();
        lblValInfluenza2 = new javax.swing.JLabel();
        lblLoteInfluenza7 = new javax.swing.JLabel();
        tfInfluenza4 = new javax.swing.JFormattedTextField();
        tfLoteInfluenza8 = new javax.swing.JTextField();
        tfLoteInfluenza4 = new javax.swing.JTextField();
        lblValInfluenza7 = new javax.swing.JLabel();
        tfValInfluenza4 = new javax.swing.JFormattedTextField();
        tfValInfluenza8 = new javax.swing.JFormattedTextField();
        lblLoteInfluenza3 = new javax.swing.JLabel();
        btnContinuar3 = new javax.swing.JButton();
        lblValInfluenza3 = new javax.swing.JLabel();
        tfInfluenza5 = new javax.swing.JFormattedTextField();
        lblInfluenza = new javax.swing.JLabel();
        tfLoteInfluenza5 = new javax.swing.JTextField();
        tfInfluenza1 = new javax.swing.JFormattedTextField();
        lblLoteInfluenza = new javax.swing.JLabel();
        tfValInfluenza5 = new javax.swing.JFormattedTextField();
        lblLoteInfluenza4 = new javax.swing.JLabel();
        tfLoteInfluenza1 = new javax.swing.JTextField();
        lblValInfluenza4 = new javax.swing.JLabel();
        lblValInfluenza = new javax.swing.JLabel();
        tfInfluenza6 = new javax.swing.JFormattedTextField();
        tfValInfluenza1 = new javax.swing.JFormattedTextField();
        tfLoteInfluenza6 = new javax.swing.JTextField();
        tfInfluenza2 = new javax.swing.JFormattedTextField();
        tfValInfluenza6 = new javax.swing.JFormattedTextField();
        tfLoteInfluenza2 = new javax.swing.JTextField();
        lblLoteInfluenza5 = new javax.swing.JLabel();
        tfValInfluenza2 = new javax.swing.JFormattedTextField();
        lblValInfluenza5 = new javax.swing.JLabel();
        sSeparador25 = new javax.swing.JSeparator();
        sSeparador26 = new javax.swing.JSeparator();
        sSeparador27 = new javax.swing.JSeparator();
        sSeparador28 = new javax.swing.JSeparator();
        sSeparador29 = new javax.swing.JSeparator();
        sSeparador30 = new javax.swing.JSeparator();
        sSeparador31 = new javax.swing.JSeparator();
        sSeparador32 = new javax.swing.JSeparator();
        sSeparador33 = new javax.swing.JSeparator();
        sSeparador34 = new javax.swing.JSeparator();
        sSeparador35 = new javax.swing.JSeparator();
        sSeparador36 = new javax.swing.JSeparator();
        sSeparador37 = new javax.swing.JSeparator();
        sSeparador38 = new javax.swing.JSeparator();
        sSeparador39 = new javax.swing.JSeparator();
        sSeparador40 = new javax.swing.JSeparator();
        sSeparador41 = new javax.swing.JSeparator();
        sSeparador42 = new javax.swing.JSeparator();
        sSeparador43 = new javax.swing.JSeparator();
        sSeparador44 = new javax.swing.JSeparator();
        sSeparador45 = new javax.swing.JSeparator();
        sSeparador46 = new javax.swing.JSeparator();
        sSeparador47 = new javax.swing.JSeparator();
        sSeparador48 = new javax.swing.JSeparator();
        btnFinalizar3 = new javax.swing.JButton();
        btnCancelar3 = new javax.swing.JButton();
        pnlFebre = new javax.swing.JPanel();
        pnlFebreAmarelaInterno = new javax.swing.JPanel();
        lblValHepatite5 = new javax.swing.JLabel();
        tfValFebre6 = new javax.swing.JFormattedTextField();
        lblValHepatite1 = new javax.swing.JLabel();
        tfFebre7 = new javax.swing.JFormattedTextField();
        tfValFebre2 = new javax.swing.JFormattedTextField();
        lblLoteHepatite6 = new javax.swing.JLabel();
        tfFebre3 = new javax.swing.JFormattedTextField();
        tfLoteFebre7 = new javax.swing.JTextField();
        lblLoteHepatite2 = new javax.swing.JLabel();
        lblValHepatite6 = new javax.swing.JLabel();
        tfLoteFebre3 = new javax.swing.JTextField();
        tfValFebre7 = new javax.swing.JFormattedTextField();
        lblValHepatite2 = new javax.swing.JLabel();
        tfFebre8 = new javax.swing.JFormattedTextField();
        tfValFebre3 = new javax.swing.JFormattedTextField();
        lblLoteHepatite7 = new javax.swing.JLabel();
        tfFebre4 = new javax.swing.JFormattedTextField();
        tfLoteFebre8 = new javax.swing.JTextField();
        lblLoteHepatite3 = new javax.swing.JLabel();
        tfLoteFebre4 = new javax.swing.JTextField();
        lblValHepatite7 = new javax.swing.JLabel();
        tfValFebre8 = new javax.swing.JFormattedTextField();
        lblValHepatite3 = new javax.swing.JLabel();
        btnContinuar4 = new javax.swing.JButton();
        tfValFebre4 = new javax.swing.JFormattedTextField();
        tfFebre5 = new javax.swing.JFormattedTextField();
        lblFebreAmarela = new javax.swing.JLabel();
        lblLoteHepatite4 = new javax.swing.JLabel();
        tfFebre1 = new javax.swing.JFormattedTextField();
        tfLoteFebre5 = new javax.swing.JTextField();
        lblLoteHepatite = new javax.swing.JLabel();
        lblValHepatite4 = new javax.swing.JLabel();
        tfLoteFebre1 = new javax.swing.JTextField();
        tfValFebre5 = new javax.swing.JFormattedTextField();
        lblValHepatite = new javax.swing.JLabel();
        tfFebre6 = new javax.swing.JFormattedTextField();
        tfValFebre1 = new javax.swing.JFormattedTextField();
        lblLoteHepatite5 = new javax.swing.JLabel();
        tfFebre2 = new javax.swing.JFormattedTextField();
        tfLoteFebre6 = new javax.swing.JTextField();
        lblLoteHepatite1 = new javax.swing.JLabel();
        tfLoteFebre2 = new javax.swing.JTextField();
        btnCancelar4 = new javax.swing.JButton();
        btnFinalizar4 = new javax.swing.JButton();
        sSeparador49 = new javax.swing.JSeparator();
        sSeparador50 = new javax.swing.JSeparator();
        sSeparador51 = new javax.swing.JSeparator();
        sSeparador52 = new javax.swing.JSeparator();
        sSeparador53 = new javax.swing.JSeparator();
        sSeparador54 = new javax.swing.JSeparator();
        sSeparador55 = new javax.swing.JSeparator();
        sSeparador56 = new javax.swing.JSeparator();
        sSeparador57 = new javax.swing.JSeparator();
        sSeparador58 = new javax.swing.JSeparator();
        sSeparador59 = new javax.swing.JSeparator();
        sSeparador60 = new javax.swing.JSeparator();
        sSeparador61 = new javax.swing.JSeparator();
        sSeparador62 = new javax.swing.JSeparator();
        sSeparador63 = new javax.swing.JSeparator();
        sSeparador64 = new javax.swing.JSeparator();
        sSeparador65 = new javax.swing.JSeparator();
        sSeparador66 = new javax.swing.JSeparator();
        sSeparador67 = new javax.swing.JSeparator();
        sSeparador68 = new javax.swing.JSeparator();
        sSeparador69 = new javax.swing.JSeparator();
        sSeparador70 = new javax.swing.JSeparator();
        sSeparador71 = new javax.swing.JSeparator();
        sSeparador72 = new javax.swing.JSeparator();
        pnlFebreAmarela = new javax.swing.JPanel();
        pnlFebreInterno = new javax.swing.JPanel();
        lblLoteRevacinacao = new javax.swing.JLabel();
        lblDupla1 = new javax.swing.JLabel();
        lblValRevacinacao = new javax.swing.JLabel();
        tfDuplaViral1 = new javax.swing.JFormattedTextField();
        btnContinuar5 = new javax.swing.JButton();
        tfLoteDupla1 = new javax.swing.JTextField();
        tfValDupla1 = new javax.swing.JFormattedTextField();
        lblLoteFebre = new javax.swing.JLabel();
        lblValFebre = new javax.swing.JLabel();
        lblDupla2 = new javax.swing.JLabel();
        tfDuplaViral2 = new javax.swing.JFormattedTextField();
        tfLoteDuplaViral2 = new javax.swing.JTextField();
        tfValDupla2 = new javax.swing.JFormattedTextField();
        lblDuplaViral = new javax.swing.JLabel();
        btnFinalizar5 = new javax.swing.JButton();
        btnCancelar5 = new javax.swing.JButton();
        sSeparador73 = new javax.swing.JSeparator();
        sSeparador74 = new javax.swing.JSeparator();
        sSeparador75 = new javax.swing.JSeparator();
        sSeparador76 = new javax.swing.JSeparator();
        sSeparador77 = new javax.swing.JSeparator();
        sSeparador78 = new javax.swing.JSeparator();
        pnlTripliceViral = new javax.swing.JPanel();
        pnlTripliceInterno = new javax.swing.JPanel();
        lblVal1 = new javax.swing.JLabel();
        tfValTriplice1 = new javax.swing.JFormattedTextField();
        lblDose4 = new javax.swing.JLabel();
        tfTriplice2 = new javax.swing.JFormattedTextField();
        tfLoteTriplice2 = new javax.swing.JTextField();
        lblLote4 = new javax.swing.JLabel();
        lblTriplice = new javax.swing.JLabel();
        lblVal4 = new javax.swing.JLabel();
        lblDose1 = new javax.swing.JLabel();
        tfValTriplice2 = new javax.swing.JFormattedTextField();
        tfTriplice1 = new javax.swing.JFormattedTextField();
        tfLoteTriplice1 = new javax.swing.JTextField();
        lblLote1 = new javax.swing.JLabel();
        btnContinuar6 = new javax.swing.JButton();
        sSeparador79 = new javax.swing.JSeparator();
        sSeparador80 = new javax.swing.JSeparator();
        sSeparador81 = new javax.swing.JSeparator();
        sSeparador82 = new javax.swing.JSeparator();
        sSeparador83 = new javax.swing.JSeparator();
        sSeparador84 = new javax.swing.JSeparator();
        btnFinalizar6 = new javax.swing.JButton();
        btnCancelar6 = new javax.swing.JButton();
        pnlOutras = new javax.swing.JPanel();
        pnlTripliceInterno1 = new javax.swing.JPanel();
        lblOutras1 = new javax.swing.JLabel();
        lblDose5 = new javax.swing.JLabel();
        lblDescricao1 = new javax.swing.JLabel();
        tfOutra1 = new javax.swing.JTextField();
        tfDtOutra1 = new javax.swing.JFormattedTextField();
        lblLote5 = new javax.swing.JLabel();
        tfLoteOutra1 = new javax.swing.JTextField();
        lblVal5 = new javax.swing.JLabel();
        tfValOutra1 = new javax.swing.JFormattedTextField();
        lblDose6 = new javax.swing.JLabel();
        lblDescricao2 = new javax.swing.JLabel();
        tfOutra1d2 = new javax.swing.JTextField();
        tfDtOutra1d2 = new javax.swing.JFormattedTextField();
        lblLote6 = new javax.swing.JLabel();
        tfLoteOutra1d2 = new javax.swing.JTextField();
        lblVal6 = new javax.swing.JLabel();
        tfValOutra1d2 = new javax.swing.JFormattedTextField();
        lblOutras2 = new javax.swing.JLabel();
        lblDose7 = new javax.swing.JLabel();
        lblDescricao3 = new javax.swing.JLabel();
        tfOutra2 = new javax.swing.JTextField();
        tfDtOutra2 = new javax.swing.JFormattedTextField();
        lblLote7 = new javax.swing.JLabel();
        tfLoteOutra2 = new javax.swing.JTextField();
        lblVal7 = new javax.swing.JLabel();
        tfValOutra2 = new javax.swing.JFormattedTextField();
        lblDose8 = new javax.swing.JLabel();
        lblDescricao4 = new javax.swing.JLabel();
        tfOutra2d2 = new javax.swing.JTextField();
        tfDtOutra2d2 = new javax.swing.JFormattedTextField();
        lblLote8 = new javax.swing.JLabel();
        tfLoteOutra2d2 = new javax.swing.JTextField();
        lblVal8 = new javax.swing.JLabel();
        tfValOutra2d2 = new javax.swing.JFormattedTextField();
        btnContinuar7 = new javax.swing.JButton();
        btnFinalizar7 = new javax.swing.JButton();
        btnCancelar7 = new javax.swing.JButton();
        sSeparador85 = new javax.swing.JSeparator();
        sSeparador86 = new javax.swing.JSeparator();
        sSeparador87 = new javax.swing.JSeparator();
        sSeparador88 = new javax.swing.JSeparator();
        sSeparador89 = new javax.swing.JSeparator();
        sSeparador90 = new javax.swing.JSeparator();
        sSeparador91 = new javax.swing.JSeparator();
        sSeparador92 = new javax.swing.JSeparator();
        sSeparador93 = new javax.swing.JSeparator();
        sSeparador94 = new javax.swing.JSeparator();
        sSeparador95 = new javax.swing.JSeparator();
        sSeparador96 = new javax.swing.JSeparator();
        sSeparador97 = new javax.swing.JSeparator();
        sSeparador98 = new javax.swing.JSeparator();
        sSeparador99 = new javax.swing.JSeparator();
        sSeparador100 = new javax.swing.JSeparator();

        setBackground(new java.awt.Color(255, 255, 255));
        setMaximumSize(new java.awt.Dimension(1000, 550));
        setMinimumSize(new java.awt.Dimension(1000, 550));

        pnlCartao.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N

        pnlDados.setBackground(new java.awt.Color(255, 255, 255));

        pnlDadosInterno.setBackground(new java.awt.Color(255, 255, 255));

        lblGRS.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        lblGRS.setForeground(new java.awt.Color(102, 102, 102));
        lblGRS.setText("GRS");

        lblTipoSanguineo.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        lblTipoSanguineo.setForeground(new java.awt.Color(255, 255, 255));
        lblTipoSanguineo.setText("TIPO SANGUÍNEO");
        lblTipoSanguineo.setEnabled(false);

        tfGRS.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        tfGRS.setForeground(new java.awt.Color(102, 102, 102));
        tfGRS.setBorder(null);

        tfTipoSanguineo.setEditable(false);
        tfTipoSanguineo.setBackground(new java.awt.Color(255, 255, 255));
        tfTipoSanguineo.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        tfTipoSanguineo.setForeground(new java.awt.Color(102, 102, 102));
        tfTipoSanguineo.setBorder(null);

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

        lblCpf.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        lblCpf.setForeground(new java.awt.Color(255, 255, 255));
        lblCpf.setText("CPF");
        lblCpf.setEnabled(false);

        lblMunicipio.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        lblMunicipio.setForeground(new java.awt.Color(255, 255, 255));
        lblMunicipio.setText("MUNICÍPIO");
        lblMunicipio.setEnabled(false);

        tfCpf.setEditable(false);
        tfCpf.setBackground(new java.awt.Color(255, 255, 255));
        tfCpf.setBorder(null);
        tfCpf.setForeground(new java.awt.Color(102, 102, 102));
        try {
            tfCpf.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###.###.###-##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        tfCpf.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N

        tfMunicipio.setEditable(false);
        tfMunicipio.setBackground(new java.awt.Color(255, 255, 255));
        tfMunicipio.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        tfMunicipio.setForeground(new java.awt.Color(102, 102, 102));
        tfMunicipio.setBorder(null);

        lblUF.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        lblUF.setForeground(new java.awt.Color(255, 255, 255));
        lblUF.setText("UF");
        lblUF.setEnabled(false);

        lblNome.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        lblNome.setForeground(new java.awt.Color(255, 255, 255));
        lblNome.setText("NOME");
        lblNome.setEnabled(false);

        tfUF.setEditable(false);
        tfUF.setBackground(new java.awt.Color(255, 255, 255));
        tfUF.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        tfUF.setForeground(new java.awt.Color(102, 102, 102));
        tfUF.setBorder(null);

        tfNome.setEditable(false);
        tfNome.setBackground(new java.awt.Color(255, 255, 255));
        tfNome.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        tfNome.setForeground(new java.awt.Color(102, 102, 102));
        tfNome.setBorder(null);

        lblTelefone.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        lblTelefone.setForeground(new java.awt.Color(255, 255, 255));
        lblTelefone.setText("TELEFONE");
        lblTelefone.setEnabled(false);

        lblDN.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        lblDN.setForeground(new java.awt.Color(255, 255, 255));
        lblDN.setText("NASCIMENTO");
        lblDN.setEnabled(false);

        tfTelefone.setEditable(false);
        tfTelefone.setBackground(new java.awt.Color(255, 255, 255));
        tfTelefone.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        tfTelefone.setForeground(new java.awt.Color(102, 102, 102));
        tfTelefone.setBorder(null);

        tfDN.setEditable(false);
        tfDN.setBackground(new java.awt.Color(255, 255, 255));
        tfDN.setBorder(null);
        tfDN.setForeground(new java.awt.Color(102, 102, 102));
        try {
            tfDN.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        tfDN.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N

        lblRua.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        lblRua.setForeground(new java.awt.Color(255, 255, 255));
        lblRua.setText("RUA");
        lblRua.setEnabled(false);

        tfRua.setEditable(false);
        tfRua.setBackground(new java.awt.Color(255, 255, 255));
        tfRua.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        tfRua.setForeground(new java.awt.Color(102, 102, 102));
        tfRua.setBorder(null);
        tfRua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfRuaActionPerformed(evt);
            }
        });

        lblNumero.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        lblNumero.setForeground(new java.awt.Color(255, 255, 255));
        lblNumero.setText("Nº");
        lblNumero.setEnabled(false);

        tfNCasa.setEditable(false);
        tfNCasa.setBackground(new java.awt.Color(255, 255, 255));
        tfNCasa.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        tfNCasa.setForeground(new java.awt.Color(102, 102, 102));
        tfNCasa.setBorder(null);

        lblBairro.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        lblBairro.setForeground(new java.awt.Color(255, 255, 255));
        lblBairro.setText("BAIRRO");
        lblBairro.setEnabled(false);

        tfBairro.setEditable(false);
        tfBairro.setBackground(new java.awt.Color(255, 255, 255));
        tfBairro.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        tfBairro.setForeground(new java.awt.Color(102, 102, 102));
        tfBairro.setBorder(null);

        lblIdPaciente.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        lblIdPaciente.setForeground(new java.awt.Color(102, 102, 102));
        lblIdPaciente.setText("ID PACIENTE");

        tfIdPaciente.setEditable(false);
        tfIdPaciente.setBackground(new java.awt.Color(255, 255, 255));
        tfIdPaciente.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        tfIdPaciente.setForeground(new java.awt.Color(102, 102, 102));
        tfIdPaciente.setBorder(null);
        tfIdPaciente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfIdPacienteActionPerformed(evt);
            }
        });

        sSeparador1.setForeground(new java.awt.Color(51, 51, 51));

        sSeparador2.setForeground(new java.awt.Color(51, 51, 51));

        sSeparador3.setForeground(new java.awt.Color(51, 51, 51));

        sSeparador4.setForeground(new java.awt.Color(51, 51, 51));

        sSeparador5.setForeground(new java.awt.Color(51, 51, 51));

        sSeparador6.setForeground(new java.awt.Color(51, 51, 51));

        sSeparador7.setForeground(new java.awt.Color(51, 51, 51));

        sSeparador8.setForeground(new java.awt.Color(51, 51, 51));

        sSeparador9.setForeground(new java.awt.Color(51, 51, 51));

        sSeparador10.setForeground(new java.awt.Color(51, 51, 51));

        sSeparador11.setForeground(new java.awt.Color(51, 51, 51));

        sSeparador12.setForeground(new java.awt.Color(51, 51, 51));

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

        javax.swing.GroupLayout pnlDadosInternoLayout = new javax.swing.GroupLayout(pnlDadosInterno);
        pnlDadosInterno.setLayout(pnlDadosInternoLayout);
        pnlDadosInternoLayout.setHorizontalGroup(
            pnlDadosInternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDadosInternoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlDadosInternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlDadosInternoLayout.createSequentialGroup()
                        .addComponent(lblRua)
                        .addGap(18, 18, 18)
                        .addGroup(pnlDadosInternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(sSeparador6)
                            .addComponent(tfRua, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addComponent(lblNumero, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(pnlDadosInternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(sSeparador7)
                            .addComponent(tfNCasa, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addComponent(lblBairro, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(pnlDadosInternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(sSeparador8, javax.swing.GroupLayout.DEFAULT_SIZE, 260, Short.MAX_VALUE)
                            .addComponent(tfBairro)))
                    .addGroup(pnlDadosInternoLayout.createSequentialGroup()
                        .addComponent(lblNome)
                        .addGap(18, 18, 18)
                        .addGroup(pnlDadosInternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(sSeparador1)
                            .addComponent(tfNome, javax.swing.GroupLayout.DEFAULT_SIZE, 357, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addComponent(lblDN)
                        .addGap(18, 18, 18)
                        .addGroup(pnlDadosInternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(tfDN, javax.swing.GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE)
                            .addComponent(sSeparador2)))
                    .addGroup(pnlDadosInternoLayout.createSequentialGroup()
                        .addComponent(lblCpf)
                        .addGap(18, 18, 18)
                        .addGroup(pnlDadosInternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(sSeparador3)
                            .addComponent(tfCpf, javax.swing.GroupLayout.DEFAULT_SIZE, 117, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addComponent(lblTipoSanguineo, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(pnlDadosInternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(sSeparador4)
                            .addComponent(tfTipoSanguineo, javax.swing.GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addComponent(lblIdPaciente)
                        .addGap(18, 18, 18)
                        .addGroup(pnlDadosInternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(sSeparador5)
                            .addComponent(tfIdPaciente, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)))
                    .addGroup(pnlDadosInternoLayout.createSequentialGroup()
                        .addComponent(lblMunicipio)
                        .addGap(18, 18, 18)
                        .addGroup(pnlDadosInternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(sSeparador9)
                            .addComponent(tfMunicipio, javax.swing.GroupLayout.DEFAULT_SIZE, 334, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addComponent(lblUF)
                        .addGap(18, 18, 18)
                        .addGroup(pnlDadosInternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(sSeparador10)
                            .addComponent(tfUF, javax.swing.GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlDadosInternoLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(lblTelefone)
                        .addGap(18, 18, 18)
                        .addGroup(pnlDadosInternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(sSeparador11)
                            .addComponent(tfTelefone, javax.swing.GroupLayout.DEFAULT_SIZE, 111, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addComponent(lblGRS)
                        .addGap(18, 18, 18)
                        .addGroup(pnlDadosInternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(sSeparador12)
                            .addComponent(tfGRS, javax.swing.GroupLayout.DEFAULT_SIZE, 76, Short.MAX_VALUE))
                        .addGap(597, 597, 597))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlDadosInternoLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnCancelar1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnContinuar1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnFinalizar1)
                .addContainerGap())
        );
        pnlDadosInternoLayout.setVerticalGroup(
            pnlDadosInternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDadosInternoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlDadosInternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNome)
                    .addComponent(tfNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblDN)
                    .addComponent(tfDN, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlDadosInternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(sSeparador1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sSeparador2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnlDadosInternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfCpf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblCpf)
                    .addComponent(lblTipoSanguineo)
                    .addComponent(tfTipoSanguineo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblIdPaciente)
                    .addComponent(tfIdPaciente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlDadosInternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(sSeparador3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sSeparador4, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sSeparador5, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnlDadosInternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfRua, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblRua)
                    .addComponent(lblNumero)
                    .addComponent(tfNCasa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblBairro)
                    .addComponent(tfBairro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlDadosInternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(sSeparador6, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sSeparador7, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sSeparador8, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnlDadosInternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblMunicipio)
                    .addComponent(tfMunicipio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblUF)
                    .addComponent(tfUF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlDadosInternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(sSeparador9, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sSeparador10, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnlDadosInternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTelefone)
                    .addComponent(tfTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblGRS)
                    .addComponent(tfGRS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlDadosInternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(sSeparador12, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sSeparador11, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 185, Short.MAX_VALUE)
                .addGroup(pnlDadosInternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnContinuar1)
                    .addComponent(btnCancelar1)
                    .addComponent(btnFinalizar1))
                .addContainerGap())
        );

        lblIdPaciente.setVisible(false);
        tfIdPaciente.setVisible(false);

        javax.swing.GroupLayout pnlDadosLayout = new javax.swing.GroupLayout(pnlDados);
        pnlDados.setLayout(pnlDadosLayout);
        pnlDadosLayout.setHorizontalGroup(
            pnlDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDadosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlDadosInterno, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnlDadosLayout.setVerticalGroup(
            pnlDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDadosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlDadosInterno, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pnlCartao.addTab("Cartão de Vacinação", pnlDados);

        pnlDuplaAdulta.setBackground(new java.awt.Color(255, 255, 255));

        pnlDuplaAdultoInterno.setBackground(new java.awt.Color(255, 255, 255));

        tfVal3.setBorder(null);
        tfVal3.setForeground(new java.awt.Color(102, 102, 102));
        try {
            tfVal3.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        tfVal3.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N

        lblVal3.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        lblVal3.setForeground(new java.awt.Color(102, 102, 102));
        lblVal3.setText("Val:");

        lblLote3.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        lblLote3.setForeground(new java.awt.Color(102, 102, 102));
        lblLote3.setText("Lote:");

        tfLote3.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        tfLote3.setForeground(new java.awt.Color(102, 102, 102));
        tfLote3.setBorder(null);

        tfDose3.setBorder(null);
        tfDose3.setForeground(new java.awt.Color(102, 102, 102));
        try {
            tfDose3.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        tfDose3.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N

        lblDose3.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        lblDose3.setForeground(new java.awt.Color(102, 102, 102));
        lblDose3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDose3.setText("3º DOSE");

        tfValReforco.setBorder(null);
        tfValReforco.setForeground(new java.awt.Color(102, 102, 102));
        try {
            tfValReforco.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        tfValReforco.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N

        lblValReforco.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        lblValReforco.setForeground(new java.awt.Color(102, 102, 102));
        lblValReforco.setText("Val:");

        lblLoteReforco.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        lblLoteReforco.setForeground(new java.awt.Color(102, 102, 102));
        lblLoteReforco.setText("Lote:");

        lblTitulo.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        lblTitulo.setForeground(new java.awt.Color(102, 102, 102));
        lblTitulo.setText("DUPLA ADULTO (CONTRA TÉTANO E DIFTERIA)");

        tfLoteReforco.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        tfLoteReforco.setForeground(new java.awt.Color(102, 102, 102));
        tfLoteReforco.setBorder(null);

        lblDose.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        lblDose.setForeground(new java.awt.Color(102, 102, 102));
        lblDose.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDose.setText("1º DOSE");

        tfDose1.setBorder(null);
        tfDose1.setForeground(new java.awt.Color(102, 102, 102));
        try {
            tfDose1.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        tfDose1.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N

        tfReforco.setBorder(null);
        tfReforco.setForeground(new java.awt.Color(102, 102, 102));
        try {
            tfReforco.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        tfReforco.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N

        lblReforco.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        lblReforco.setForeground(new java.awt.Color(102, 102, 102));
        lblReforco.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblReforco.setText("REFORÇO");

        lblLote.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        lblLote.setForeground(new java.awt.Color(102, 102, 102));
        lblLote.setText("Lote:");

        tfLote.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        tfLote.setForeground(new java.awt.Color(102, 102, 102));
        tfLote.setBorder(null);

        lblVal.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        lblVal.setForeground(new java.awt.Color(102, 102, 102));
        lblVal.setText("Val:");

        tfVal1.setBorder(null);
        tfVal1.setForeground(new java.awt.Color(102, 102, 102));
        try {
            tfVal1.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        tfVal1.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N

        tfVal2.setBorder(null);
        tfVal2.setForeground(new java.awt.Color(102, 102, 102));
        try {
            tfVal2.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        tfVal2.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N

        lblVal2.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        lblVal2.setForeground(new java.awt.Color(102, 102, 102));
        lblVal2.setText("Val:");

        lblLote2.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        lblLote2.setForeground(new java.awt.Color(102, 102, 102));
        lblLote2.setText("Lote:");

        tfLote2.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        tfLote2.setForeground(new java.awt.Color(102, 102, 102));
        tfLote2.setBorder(null);

        tfDose2.setBorder(null);
        tfDose2.setForeground(new java.awt.Color(102, 102, 102));
        try {
            tfDose2.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        tfDose2.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N

        lblDose2.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        lblDose2.setForeground(new java.awt.Color(102, 102, 102));
        lblDose2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDose2.setText("2º DOSE");

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

        sSeparador23.setForeground(new java.awt.Color(51, 51, 51));

        sSeparador24.setForeground(new java.awt.Color(51, 51, 51));

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

        btnCancelar2.setBackground(new java.awt.Color(102, 102, 102));
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

        javax.swing.GroupLayout pnlDuplaAdultoInternoLayout = new javax.swing.GroupLayout(pnlDuplaAdultoInterno);
        pnlDuplaAdultoInterno.setLayout(pnlDuplaAdultoInternoLayout);
        pnlDuplaAdultoInternoLayout.setHorizontalGroup(
            pnlDuplaAdultoInternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDuplaAdultoInternoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlDuplaAdultoInternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlDuplaAdultoInternoLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnCancelar2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnContinuar2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnFinalizar2))
                    .addGroup(pnlDuplaAdultoInternoLayout.createSequentialGroup()
                        .addGroup(pnlDuplaAdultoInternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblTitulo)
                            .addGroup(pnlDuplaAdultoInternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(pnlDuplaAdultoInternoLayout.createSequentialGroup()
                                    .addGroup(pnlDuplaAdultoInternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(sSeparador21, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlDuplaAdultoInternoLayout.createSequentialGroup()
                                            .addComponent(lblVal, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(0, 0, 0)
                                            .addComponent(tfVal1, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGap(20, 20, 20)
                                    .addGroup(pnlDuplaAdultoInternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(pnlDuplaAdultoInternoLayout.createSequentialGroup()
                                            .addComponent(lblVal2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(0, 0, 0)
                                            .addComponent(tfVal2, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(sSeparador22, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(30, 30, 30)
                                    .addGroup(pnlDuplaAdultoInternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(pnlDuplaAdultoInternoLayout.createSequentialGroup()
                                            .addComponent(lblVal3, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(0, 0, 0)
                                            .addComponent(tfVal3, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(sSeparador23, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(30, 30, 30)
                                    .addComponent(lblValReforco, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(pnlDuplaAdultoInternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(tfValReforco)
                                        .addComponent(sSeparador24)))
                                .addGroup(pnlDuplaAdultoInternoLayout.createSequentialGroup()
                                    .addGroup(pnlDuplaAdultoInternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(sSeparador13, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(tfDose1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(70, 70, 70)
                                    .addGroup(pnlDuplaAdultoInternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(sSeparador14)
                                        .addComponent(tfDose2, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(80, 80, 80)
                                    .addGroup(pnlDuplaAdultoInternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(tfDose3)
                                        .addComponent(sSeparador15, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(80, 80, 80)
                                    .addGroup(pnlDuplaAdultoInternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(tfReforco)
                                        .addComponent(sSeparador16, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlDuplaAdultoInternoLayout.createSequentialGroup()
                                    .addComponent(lblDose, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(70, 70, 70)
                                    .addComponent(lblDose2, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(80, 80, 80)
                                    .addComponent(lblDose3, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(80, 80, 80)
                                    .addComponent(lblReforco))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlDuplaAdultoInternoLayout.createSequentialGroup()
                                    .addGroup(pnlDuplaAdultoInternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addGroup(pnlDuplaAdultoInternoLayout.createSequentialGroup()
                                            .addComponent(lblLote, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(0, 0, 0)
                                            .addComponent(tfLote, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(sSeparador17, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(pnlDuplaAdultoInternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addGroup(pnlDuplaAdultoInternoLayout.createSequentialGroup()
                                            .addGap(20, 20, 20)
                                            .addComponent(lblLote2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(0, 0, 0)
                                            .addComponent(tfLote2, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(pnlDuplaAdultoInternoLayout.createSequentialGroup()
                                            .addGap(70, 70, 70)
                                            .addComponent(sSeparador18, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(pnlDuplaAdultoInternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addGroup(pnlDuplaAdultoInternoLayout.createSequentialGroup()
                                            .addGap(30, 30, 30)
                                            .addComponent(lblLote3, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(0, 0, 0)
                                            .addComponent(tfLote3, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(pnlDuplaAdultoInternoLayout.createSequentialGroup()
                                            .addGap(80, 80, 80)
                                            .addComponent(sSeparador19, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGap(30, 30, 30)
                                    .addComponent(lblLoteReforco, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(pnlDuplaAdultoInternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(sSeparador20, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(tfLoteReforco, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGap(0, 351, Short.MAX_VALUE)))
                .addContainerGap())
        );
        pnlDuplaAdultoInternoLayout.setVerticalGroup(
            pnlDuplaAdultoInternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDuplaAdultoInternoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTitulo)
                .addGap(18, 18, 18)
                .addGroup(pnlDuplaAdultoInternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblDose)
                    .addComponent(lblDose2)
                    .addComponent(lblDose3)
                    .addComponent(lblReforco))
                .addGap(3, 3, 3)
                .addGroup(pnlDuplaAdultoInternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tfDose1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfDose2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfDose3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfReforco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlDuplaAdultoInternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(sSeparador13, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sSeparador14, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sSeparador15, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sSeparador16, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlDuplaAdultoInternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblLote)
                    .addComponent(tfLote, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblLote2)
                    .addComponent(tfLote2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblLote3)
                    .addComponent(tfLote3, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblLoteReforco)
                    .addComponent(tfLoteReforco, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlDuplaAdultoInternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(sSeparador17, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sSeparador18, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sSeparador19, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sSeparador20, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlDuplaAdultoInternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblVal)
                    .addComponent(tfVal1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblVal2)
                    .addComponent(tfVal2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblVal3)
                    .addComponent(tfVal3, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblValReforco)
                    .addComponent(tfValReforco, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlDuplaAdultoInternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(sSeparador21, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sSeparador23, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sSeparador22, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sSeparador24, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 256, Short.MAX_VALUE)
                .addGroup(pnlDuplaAdultoInternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnContinuar2)
                    .addComponent(btnFinalizar2)
                    .addComponent(btnCancelar2))
                .addContainerGap())
        );

        javax.swing.GroupLayout pnlDuplaAdultaLayout = new javax.swing.GroupLayout(pnlDuplaAdulta);
        pnlDuplaAdulta.setLayout(pnlDuplaAdultaLayout);
        pnlDuplaAdultaLayout.setHorizontalGroup(
            pnlDuplaAdultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlDuplaAdultaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlDuplaAdultoInterno, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnlDuplaAdultaLayout.setVerticalGroup(
            pnlDuplaAdultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlDuplaAdultaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlDuplaAdultoInterno, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pnlCartao.addTab("Dupla Adulto ", pnlDuplaAdulta);

        pnlInfluenza.setBackground(new java.awt.Color(255, 255, 255));

        pnlInfluenzaInterno.setBackground(new java.awt.Color(255, 255, 255));

        lblLoteInfluenza1.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        lblLoteInfluenza1.setForeground(new java.awt.Color(102, 102, 102));
        lblLoteInfluenza1.setText("Lote:");

        tfInfluenza7.setBorder(null);
        tfInfluenza7.setForeground(new java.awt.Color(102, 102, 102));
        try {
            tfInfluenza7.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        tfInfluenza7.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N

        lblValInfluenza1.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        lblValInfluenza1.setForeground(new java.awt.Color(102, 102, 102));
        lblValInfluenza1.setText("Val:");

        tfLoteInfluenza7.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        tfLoteInfluenza7.setForeground(new java.awt.Color(102, 102, 102));
        tfLoteInfluenza7.setBorder(null);

        tfInfluenza3.setBorder(null);
        tfInfluenza3.setForeground(new java.awt.Color(102, 102, 102));
        try {
            tfInfluenza3.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        tfInfluenza3.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N

        tfLoteInfluenza3.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        tfLoteInfluenza3.setForeground(new java.awt.Color(102, 102, 102));
        tfLoteInfluenza3.setBorder(null);
        tfLoteInfluenza3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfLoteInfluenza3ActionPerformed(evt);
            }
        });

        tfValInfluenza7.setBorder(null);
        tfValInfluenza7.setForeground(new java.awt.Color(102, 102, 102));
        try {
            tfValInfluenza7.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        tfValInfluenza7.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N

        lblLoteInfluenza6.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        lblLoteInfluenza6.setForeground(new java.awt.Color(102, 102, 102));
        lblLoteInfluenza6.setText("Lote:");

        tfValInfluenza3.setBorder(null);
        tfValInfluenza3.setForeground(new java.awt.Color(102, 102, 102));
        try {
            tfValInfluenza3.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        tfValInfluenza3.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N

        lblValInfluenza6.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        lblValInfluenza6.setForeground(new java.awt.Color(102, 102, 102));
        lblValInfluenza6.setText("Val:");

        lblLoteInfluenza2.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        lblLoteInfluenza2.setForeground(new java.awt.Color(102, 102, 102));
        lblLoteInfluenza2.setText("Lote:");

        tfInfluenza8.setBorder(null);
        tfInfluenza8.setForeground(new java.awt.Color(102, 102, 102));
        try {
            tfInfluenza8.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        tfInfluenza8.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N

        lblValInfluenza2.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        lblValInfluenza2.setForeground(new java.awt.Color(102, 102, 102));
        lblValInfluenza2.setText("Val:");

        lblLoteInfluenza7.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        lblLoteInfluenza7.setForeground(new java.awt.Color(102, 102, 102));
        lblLoteInfluenza7.setText("Lote:");

        tfInfluenza4.setBorder(null);
        tfInfluenza4.setForeground(new java.awt.Color(102, 102, 102));
        try {
            tfInfluenza4.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        tfInfluenza4.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N

        tfLoteInfluenza8.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        tfLoteInfluenza8.setForeground(new java.awt.Color(102, 102, 102));
        tfLoteInfluenza8.setBorder(null);

        tfLoteInfluenza4.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        tfLoteInfluenza4.setForeground(new java.awt.Color(102, 102, 102));
        tfLoteInfluenza4.setBorder(null);

        lblValInfluenza7.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        lblValInfluenza7.setForeground(new java.awt.Color(102, 102, 102));
        lblValInfluenza7.setText("Val:");

        tfValInfluenza4.setBorder(null);
        tfValInfluenza4.setForeground(new java.awt.Color(102, 102, 102));
        try {
            tfValInfluenza4.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        tfValInfluenza4.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N

        tfValInfluenza8.setBorder(null);
        tfValInfluenza8.setForeground(new java.awt.Color(102, 102, 102));
        try {
            tfValInfluenza8.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        tfValInfluenza8.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N

        lblLoteInfluenza3.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        lblLoteInfluenza3.setForeground(new java.awt.Color(102, 102, 102));
        lblLoteInfluenza3.setText("Lote:");

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

        lblValInfluenza3.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        lblValInfluenza3.setForeground(new java.awt.Color(102, 102, 102));
        lblValInfluenza3.setText("Val:");

        tfInfluenza5.setBorder(null);
        tfInfluenza5.setForeground(new java.awt.Color(102, 102, 102));
        try {
            tfInfluenza5.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        tfInfluenza5.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N

        lblInfluenza.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        lblInfluenza.setForeground(new java.awt.Color(102, 102, 102));
        lblInfluenza.setText("INFLUENZA (CONTRA GRIPE)");

        tfLoteInfluenza5.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        tfLoteInfluenza5.setForeground(new java.awt.Color(102, 102, 102));
        tfLoteInfluenza5.setBorder(null);

        tfInfluenza1.setBorder(null);
        tfInfluenza1.setForeground(new java.awt.Color(102, 102, 102));
        try {
            tfInfluenza1.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        tfInfluenza1.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N

        lblLoteInfluenza.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        lblLoteInfluenza.setForeground(new java.awt.Color(102, 102, 102));
        lblLoteInfluenza.setText("Lote:");

        tfValInfluenza5.setBorder(null);
        tfValInfluenza5.setForeground(new java.awt.Color(102, 102, 102));
        try {
            tfValInfluenza5.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        tfValInfluenza5.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N

        lblLoteInfluenza4.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        lblLoteInfluenza4.setForeground(new java.awt.Color(102, 102, 102));
        lblLoteInfluenza4.setText("Lote:");

        tfLoteInfluenza1.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        tfLoteInfluenza1.setForeground(new java.awt.Color(102, 102, 102));
        tfLoteInfluenza1.setBorder(null);

        lblValInfluenza4.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        lblValInfluenza4.setForeground(new java.awt.Color(102, 102, 102));
        lblValInfluenza4.setText("Val:");

        lblValInfluenza.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        lblValInfluenza.setForeground(new java.awt.Color(102, 102, 102));
        lblValInfluenza.setText("Val:");

        tfInfluenza6.setBorder(null);
        tfInfluenza6.setForeground(new java.awt.Color(102, 102, 102));
        try {
            tfInfluenza6.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        tfInfluenza6.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N

        tfValInfluenza1.setBorder(null);
        tfValInfluenza1.setForeground(new java.awt.Color(102, 102, 102));
        try {
            tfValInfluenza1.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        tfValInfluenza1.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N

        tfLoteInfluenza6.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        tfLoteInfluenza6.setForeground(new java.awt.Color(102, 102, 102));
        tfLoteInfluenza6.setBorder(null);

        tfInfluenza2.setBorder(null);
        tfInfluenza2.setForeground(new java.awt.Color(102, 102, 102));
        try {
            tfInfluenza2.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        tfInfluenza2.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N

        tfValInfluenza6.setBorder(null);
        tfValInfluenza6.setForeground(new java.awt.Color(102, 102, 102));
        try {
            tfValInfluenza6.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        tfValInfluenza6.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N

        tfLoteInfluenza2.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        tfLoteInfluenza2.setForeground(new java.awt.Color(102, 102, 102));
        tfLoteInfluenza2.setBorder(null);

        lblLoteInfluenza5.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        lblLoteInfluenza5.setForeground(new java.awt.Color(102, 102, 102));
        lblLoteInfluenza5.setText("Lote:");

        tfValInfluenza2.setBorder(null);
        tfValInfluenza2.setForeground(new java.awt.Color(102, 102, 102));
        try {
            tfValInfluenza2.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        tfValInfluenza2.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N

        lblValInfluenza5.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        lblValInfluenza5.setForeground(new java.awt.Color(102, 102, 102));
        lblValInfluenza5.setText("Val:");

        sSeparador25.setForeground(new java.awt.Color(51, 51, 51));

        sSeparador26.setForeground(new java.awt.Color(51, 51, 51));

        sSeparador27.setForeground(new java.awt.Color(51, 51, 51));

        sSeparador28.setForeground(new java.awt.Color(51, 51, 51));

        sSeparador29.setForeground(new java.awt.Color(51, 51, 51));

        sSeparador30.setForeground(new java.awt.Color(51, 51, 51));

        sSeparador31.setForeground(new java.awt.Color(51, 51, 51));

        sSeparador32.setForeground(new java.awt.Color(51, 51, 51));

        sSeparador33.setForeground(new java.awt.Color(51, 51, 51));

        sSeparador34.setForeground(new java.awt.Color(51, 51, 51));

        sSeparador35.setForeground(new java.awt.Color(51, 51, 51));

        sSeparador36.setForeground(new java.awt.Color(51, 51, 51));

        sSeparador37.setForeground(new java.awt.Color(51, 51, 51));

        sSeparador38.setForeground(new java.awt.Color(51, 51, 51));

        sSeparador39.setForeground(new java.awt.Color(51, 51, 51));

        sSeparador40.setForeground(new java.awt.Color(51, 51, 51));

        sSeparador41.setForeground(new java.awt.Color(51, 51, 51));

        sSeparador42.setForeground(new java.awt.Color(51, 51, 51));

        sSeparador43.setForeground(new java.awt.Color(51, 51, 51));

        sSeparador44.setForeground(new java.awt.Color(51, 51, 51));

        sSeparador45.setForeground(new java.awt.Color(51, 51, 51));

        sSeparador46.setForeground(new java.awt.Color(51, 51, 51));

        sSeparador47.setForeground(new java.awt.Color(51, 51, 51));

        sSeparador48.setForeground(new java.awt.Color(51, 51, 51));

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

        btnCancelar3.setBackground(new java.awt.Color(102, 102, 102));
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

        javax.swing.GroupLayout pnlInfluenzaInternoLayout = new javax.swing.GroupLayout(pnlInfluenzaInterno);
        pnlInfluenzaInterno.setLayout(pnlInfluenzaInternoLayout);
        pnlInfluenzaInternoLayout.setHorizontalGroup(
            pnlInfluenzaInternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlInfluenzaInternoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlInfluenzaInternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblInfluenza)
                    .addGroup(pnlInfluenzaInternoLayout.createSequentialGroup()
                        .addComponent(lblLoteInfluenza4, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addGroup(pnlInfluenzaInternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(sSeparador42, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(pnlInfluenzaInternoLayout.createSequentialGroup()
                                .addComponent(tfLoteInfluenza5, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(20, 20, 20)
                                .addComponent(lblLoteInfluenza5, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addGroup(pnlInfluenzaInternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(tfLoteInfluenza6)
                                    .addComponent(sSeparador39, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(pnlInfluenzaInternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(pnlInfluenzaInternoLayout.createSequentialGroup()
                                        .addGap(30, 30, 30)
                                        .addComponent(lblLoteInfluenza6, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, 0)
                                        .addComponent(tfLoteInfluenza7, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlInfluenzaInternoLayout.createSequentialGroup()
                                        .addGap(80, 80, 80)
                                        .addComponent(sSeparador38, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(20, 20, 20)
                                .addComponent(lblLoteInfluenza7, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addGroup(pnlInfluenzaInternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(sSeparador37)
                                    .addComponent(tfLoteInfluenza8, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(pnlInfluenzaInternoLayout.createSequentialGroup()
                        .addComponent(lblValInfluenza, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addGroup(pnlInfluenzaInternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(sSeparador33)
                            .addComponent(tfValInfluenza1, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(20, 20, 20)
                        .addComponent(lblValInfluenza1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addGroup(pnlInfluenzaInternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(tfValInfluenza2)
                            .addComponent(sSeparador31, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(pnlInfluenzaInternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlInfluenzaInternoLayout.createSequentialGroup()
                                .addGap(30, 30, 30)
                                .addComponent(lblValInfluenza2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(tfValInfluenza3, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlInfluenzaInternoLayout.createSequentialGroup()
                                .addGap(80, 80, 80)
                                .addComponent(sSeparador30, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(20, 20, 20)
                        .addComponent(lblValInfluenza3, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addGroup(pnlInfluenzaInternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(sSeparador29)
                            .addComponent(tfValInfluenza4, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(pnlInfluenzaInternoLayout.createSequentialGroup()
                        .addComponent(lblValInfluenza4, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addGroup(pnlInfluenzaInternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(sSeparador47)
                            .addComponent(tfValInfluenza5, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(20, 20, 20)
                        .addComponent(lblValInfluenza5, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addGroup(pnlInfluenzaInternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(sSeparador46)
                            .addComponent(tfValInfluenza6)
                            .addComponent(sSeparador43, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(30, 30, 30)
                        .addComponent(lblValInfluenza6, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addGroup(pnlInfluenzaInternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(sSeparador45)
                            .addComponent(tfValInfluenza7)
                            .addComponent(sSeparador44, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(pnlInfluenzaInternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlInfluenzaInternoLayout.createSequentialGroup()
                                .addGap(70, 70, 70)
                                .addComponent(sSeparador41, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pnlInfluenzaInternoLayout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(lblValInfluenza7, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addGroup(pnlInfluenzaInternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(sSeparador48)
                                    .addComponent(tfValInfluenza8, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(pnlInfluenzaInternoLayout.createSequentialGroup()
                        .addComponent(lblLoteInfluenza, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addGroup(pnlInfluenzaInternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(sSeparador32, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(pnlInfluenzaInternoLayout.createSequentialGroup()
                                .addComponent(tfLoteInfluenza1, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(20, 20, 20)
                                .addComponent(lblLoteInfluenza1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(tfLoteInfluenza2, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(30, 30, 30)
                                .addComponent(lblLoteInfluenza2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(tfLoteInfluenza3, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(20, 20, 20)
                                .addComponent(lblLoteInfluenza3, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(tfLoteInfluenza4, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(pnlInfluenzaInternoLayout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addGroup(pnlInfluenzaInternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(sSeparador40, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(pnlInfluenzaInternoLayout.createSequentialGroup()
                                .addComponent(tfInfluenza5, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(70, 70, 70)
                                .addGroup(pnlInfluenzaInternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(tfInfluenza6, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(sSeparador34, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(80, 80, 80)
                                .addGroup(pnlInfluenzaInternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(tfInfluenza7, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(sSeparador35, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(70, 70, 70)
                                .addGroup(pnlInfluenzaInternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(tfInfluenza8, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(sSeparador36, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(pnlInfluenzaInternoLayout.createSequentialGroup()
                                .addGroup(pnlInfluenzaInternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(sSeparador25)
                                    .addComponent(tfInfluenza1, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(70, 70, 70)
                                .addGroup(pnlInfluenzaInternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(sSeparador26)
                                    .addComponent(tfInfluenza2, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(80, 80, 80)
                                .addGroup(pnlInfluenzaInternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(tfInfluenza3)
                                    .addComponent(sSeparador27, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(70, 70, 70)
                                .addGroup(pnlInfluenzaInternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(tfInfluenza4)
                                    .addComponent(sSeparador28, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlInfluenzaInternoLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnCancelar3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnContinuar3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnFinalizar3)
                .addContainerGap())
        );
        pnlInfluenzaInternoLayout.setVerticalGroup(
            pnlInfluenzaInternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlInfluenzaInternoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblInfluenza, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(pnlInfluenzaInternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tfInfluenza1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfInfluenza2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfInfluenza3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfInfluenza4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlInfluenzaInternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(sSeparador25, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sSeparador26, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sSeparador28, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sSeparador27, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlInfluenzaInternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblLoteInfluenza)
                    .addComponent(tfLoteInfluenza1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblLoteInfluenza1)
                    .addComponent(tfLoteInfluenza2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblLoteInfluenza2)
                    .addComponent(tfLoteInfluenza3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblLoteInfluenza3)
                    .addComponent(tfLoteInfluenza4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlInfluenzaInternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(sSeparador32, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sSeparador31, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sSeparador29, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sSeparador30, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlInfluenzaInternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblValInfluenza)
                    .addComponent(tfValInfluenza1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblValInfluenza1)
                    .addComponent(tfValInfluenza2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblValInfluenza2)
                    .addComponent(tfValInfluenza3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblValInfluenza3)
                    .addComponent(tfValInfluenza4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlInfluenzaInternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(sSeparador33, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sSeparador34, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sSeparador36, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sSeparador35, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnlInfluenzaInternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tfInfluenza5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfInfluenza6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfInfluenza7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfInfluenza8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlInfluenzaInternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(sSeparador40, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sSeparador39, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sSeparador37, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sSeparador38, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlInfluenzaInternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblLoteInfluenza4)
                    .addComponent(tfLoteInfluenza5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblLoteInfluenza5)
                    .addComponent(tfLoteInfluenza6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblLoteInfluenza6)
                    .addComponent(tfLoteInfluenza7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblLoteInfluenza7)
                    .addComponent(tfLoteInfluenza8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlInfluenzaInternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(sSeparador42, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sSeparador43, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sSeparador41, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sSeparador44, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlInfluenzaInternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblValInfluenza4)
                    .addComponent(tfValInfluenza5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblValInfluenza5)
                    .addComponent(tfValInfluenza6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblValInfluenza6)
                    .addComponent(tfValInfluenza7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblValInfluenza7)
                    .addComponent(tfValInfluenza8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlInfluenzaInternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(sSeparador47, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sSeparador46, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sSeparador48, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sSeparador45, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 148, Short.MAX_VALUE)
                .addGroup(pnlInfluenzaInternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnContinuar3)
                    .addComponent(btnFinalizar3)
                    .addComponent(btnCancelar3))
                .addContainerGap())
        );

        javax.swing.GroupLayout pnlInfluenzaLayout = new javax.swing.GroupLayout(pnlInfluenza);
        pnlInfluenza.setLayout(pnlInfluenzaLayout);
        pnlInfluenzaLayout.setHorizontalGroup(
            pnlInfluenzaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlInfluenzaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlInfluenzaInterno, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnlInfluenzaLayout.setVerticalGroup(
            pnlInfluenzaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlInfluenzaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlInfluenzaInterno, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pnlCartao.addTab("Influenza", pnlInfluenza);

        pnlFebre.setBackground(new java.awt.Color(255, 255, 255));

        pnlFebreAmarelaInterno.setBackground(new java.awt.Color(255, 255, 255));

        lblValHepatite5.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        lblValHepatite5.setForeground(new java.awt.Color(102, 102, 102));
        lblValHepatite5.setText("Val:");

        tfValFebre6.setBorder(null);
        tfValFebre6.setForeground(new java.awt.Color(102, 102, 102));
        try {
            tfValFebre6.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        tfValFebre6.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N

        lblValHepatite1.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        lblValHepatite1.setForeground(new java.awt.Color(102, 102, 102));
        lblValHepatite1.setText("Val:");

        tfFebre7.setBorder(null);
        tfFebre7.setForeground(new java.awt.Color(102, 102, 102));
        try {
            tfFebre7.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        tfFebre7.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N

        tfValFebre2.setBorder(null);
        tfValFebre2.setForeground(new java.awt.Color(102, 102, 102));
        try {
            tfValFebre2.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        tfValFebre2.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N

        lblLoteHepatite6.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        lblLoteHepatite6.setForeground(new java.awt.Color(102, 102, 102));
        lblLoteHepatite6.setText("Lote:");

        tfFebre3.setBorder(null);
        tfFebre3.setForeground(new java.awt.Color(102, 102, 102));
        try {
            tfFebre3.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        tfFebre3.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N

        tfLoteFebre7.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        tfLoteFebre7.setForeground(new java.awt.Color(102, 102, 102));
        tfLoteFebre7.setBorder(null);

        lblLoteHepatite2.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        lblLoteHepatite2.setForeground(new java.awt.Color(102, 102, 102));
        lblLoteHepatite2.setText("Lote:");

        lblValHepatite6.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        lblValHepatite6.setForeground(new java.awt.Color(102, 102, 102));
        lblValHepatite6.setText("Val:");

        tfLoteFebre3.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        tfLoteFebre3.setForeground(new java.awt.Color(102, 102, 102));
        tfLoteFebre3.setBorder(null);

        tfValFebre7.setBorder(null);
        tfValFebre7.setForeground(new java.awt.Color(102, 102, 102));
        try {
            tfValFebre7.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        tfValFebre7.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N

        lblValHepatite2.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        lblValHepatite2.setForeground(new java.awt.Color(102, 102, 102));
        lblValHepatite2.setText("Val:");

        tfFebre8.setBorder(null);
        tfFebre8.setForeground(new java.awt.Color(102, 102, 102));
        try {
            tfFebre8.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        tfFebre8.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N

        tfValFebre3.setBorder(null);
        tfValFebre3.setForeground(new java.awt.Color(102, 102, 102));
        try {
            tfValFebre3.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        tfValFebre3.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N

        lblLoteHepatite7.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        lblLoteHepatite7.setForeground(new java.awt.Color(102, 102, 102));
        lblLoteHepatite7.setText("Lote:");

        tfFebre4.setBorder(null);
        tfFebre4.setForeground(new java.awt.Color(102, 102, 102));
        try {
            tfFebre4.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        tfFebre4.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N

        tfLoteFebre8.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        tfLoteFebre8.setForeground(new java.awt.Color(102, 102, 102));
        tfLoteFebre8.setBorder(null);

        lblLoteHepatite3.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        lblLoteHepatite3.setForeground(new java.awt.Color(102, 102, 102));
        lblLoteHepatite3.setText("Lote:");

        tfLoteFebre4.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        tfLoteFebre4.setForeground(new java.awt.Color(102, 102, 102));
        tfLoteFebre4.setBorder(null);

        lblValHepatite7.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        lblValHepatite7.setForeground(new java.awt.Color(102, 102, 102));
        lblValHepatite7.setText("Val:");

        tfValFebre8.setBorder(null);
        tfValFebre8.setForeground(new java.awt.Color(102, 102, 102));
        try {
            tfValFebre8.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        tfValFebre8.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N

        lblValHepatite3.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        lblValHepatite3.setForeground(new java.awt.Color(102, 102, 102));
        lblValHepatite3.setText("Val:");

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

        tfValFebre4.setBorder(null);
        tfValFebre4.setForeground(new java.awt.Color(102, 102, 102));
        try {
            tfValFebre4.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        tfValFebre4.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N

        tfFebre5.setBorder(null);
        tfFebre5.setForeground(new java.awt.Color(102, 102, 102));
        try {
            tfFebre5.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        tfFebre5.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N

        lblFebreAmarela.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        lblFebreAmarela.setForeground(new java.awt.Color(102, 102, 102));
        lblFebreAmarela.setText("FEBRE AMARELA");

        lblLoteHepatite4.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        lblLoteHepatite4.setForeground(new java.awt.Color(102, 102, 102));
        lblLoteHepatite4.setText("Lote:");

        tfFebre1.setBorder(null);
        tfFebre1.setForeground(new java.awt.Color(102, 102, 102));
        try {
            tfFebre1.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        tfFebre1.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N

        tfLoteFebre5.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        tfLoteFebre5.setForeground(new java.awt.Color(102, 102, 102));
        tfLoteFebre5.setBorder(null);

        lblLoteHepatite.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        lblLoteHepatite.setForeground(new java.awt.Color(102, 102, 102));
        lblLoteHepatite.setText("Lote:");

        lblValHepatite4.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        lblValHepatite4.setForeground(new java.awt.Color(102, 102, 102));
        lblValHepatite4.setText("Val:");

        tfLoteFebre1.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        tfLoteFebre1.setForeground(new java.awt.Color(102, 102, 102));
        tfLoteFebre1.setBorder(null);

        tfValFebre5.setBorder(null);
        tfValFebre5.setForeground(new java.awt.Color(102, 102, 102));
        try {
            tfValFebre5.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        tfValFebre5.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N

        lblValHepatite.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        lblValHepatite.setForeground(new java.awt.Color(102, 102, 102));
        lblValHepatite.setText("Val:");

        tfFebre6.setBorder(null);
        tfFebre6.setForeground(new java.awt.Color(102, 102, 102));
        try {
            tfFebre6.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        tfFebre6.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N

        tfValFebre1.setBorder(null);
        tfValFebre1.setForeground(new java.awt.Color(102, 102, 102));
        try {
            tfValFebre1.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        tfValFebre1.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N

        lblLoteHepatite5.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        lblLoteHepatite5.setForeground(new java.awt.Color(102, 102, 102));
        lblLoteHepatite5.setText("Lote:");

        tfFebre2.setBorder(null);
        tfFebre2.setForeground(new java.awt.Color(102, 102, 102));
        try {
            tfFebre2.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        tfFebre2.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N

        tfLoteFebre6.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        tfLoteFebre6.setForeground(new java.awt.Color(102, 102, 102));
        tfLoteFebre6.setBorder(null);

        lblLoteHepatite1.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        lblLoteHepatite1.setForeground(new java.awt.Color(102, 102, 102));
        lblLoteHepatite1.setText("Lote:");

        tfLoteFebre2.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        tfLoteFebre2.setForeground(new java.awt.Color(102, 102, 102));
        tfLoteFebre2.setBorder(null);

        btnCancelar4.setBackground(new java.awt.Color(102, 102, 102));
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

        sSeparador49.setForeground(new java.awt.Color(51, 51, 51));

        sSeparador50.setForeground(new java.awt.Color(51, 51, 51));

        sSeparador51.setForeground(new java.awt.Color(51, 51, 51));

        sSeparador52.setForeground(new java.awt.Color(51, 51, 51));

        sSeparador53.setForeground(new java.awt.Color(51, 51, 51));

        sSeparador54.setForeground(new java.awt.Color(51, 51, 51));

        sSeparador55.setForeground(new java.awt.Color(51, 51, 51));

        sSeparador56.setForeground(new java.awt.Color(51, 51, 51));

        sSeparador57.setForeground(new java.awt.Color(51, 51, 51));

        sSeparador58.setForeground(new java.awt.Color(51, 51, 51));

        sSeparador59.setForeground(new java.awt.Color(51, 51, 51));

        sSeparador60.setForeground(new java.awt.Color(51, 51, 51));

        sSeparador61.setForeground(new java.awt.Color(51, 51, 51));

        sSeparador62.setForeground(new java.awt.Color(51, 51, 51));

        sSeparador63.setForeground(new java.awt.Color(51, 51, 51));

        sSeparador64.setForeground(new java.awt.Color(51, 51, 51));

        sSeparador65.setForeground(new java.awt.Color(51, 51, 51));

        sSeparador66.setForeground(new java.awt.Color(51, 51, 51));

        sSeparador67.setForeground(new java.awt.Color(51, 51, 51));

        sSeparador68.setForeground(new java.awt.Color(51, 51, 51));

        sSeparador69.setForeground(new java.awt.Color(51, 51, 51));

        sSeparador70.setForeground(new java.awt.Color(51, 51, 51));

        sSeparador71.setForeground(new java.awt.Color(51, 51, 51));

        sSeparador72.setForeground(new java.awt.Color(51, 51, 51));

        javax.swing.GroupLayout pnlFebreAmarelaInternoLayout = new javax.swing.GroupLayout(pnlFebreAmarelaInterno);
        pnlFebreAmarelaInterno.setLayout(pnlFebreAmarelaInternoLayout);
        pnlFebreAmarelaInternoLayout.setHorizontalGroup(
            pnlFebreAmarelaInternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFebreAmarelaInternoLayout.createSequentialGroup()
                .addGroup(pnlFebreAmarelaInternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlFebreAmarelaInternoLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnCancelar4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnContinuar4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnFinalizar4))
                    .addGroup(pnlFebreAmarelaInternoLayout.createSequentialGroup()
                        .addGap(60, 60, 60)
                        .addGroup(pnlFebreAmarelaInternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(sSeparador49)
                            .addComponent(tfFebre1, javax.swing.GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE))
                        .addGap(70, 70, 70)
                        .addGroup(pnlFebreAmarelaInternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(tfFebre2, javax.swing.GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE)
                            .addComponent(sSeparador50))
                        .addGap(80, 80, 80)
                        .addGroup(pnlFebreAmarelaInternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(tfFebre3, javax.swing.GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE)
                            .addComponent(sSeparador51))
                        .addGap(70, 70, 70)
                        .addGroup(pnlFebreAmarelaInternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(tfFebre4, javax.swing.GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE)
                            .addComponent(sSeparador52))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(pnlFebreAmarelaInternoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlFebreAmarelaInternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlFebreAmarelaInternoLayout.createSequentialGroup()
                        .addGroup(pnlFebreAmarelaInternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlFebreAmarelaInternoLayout.createSequentialGroup()
                                .addGroup(pnlFebreAmarelaInternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(pnlFebreAmarelaInternoLayout.createSequentialGroup()
                                        .addComponent(lblLoteHepatite, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, 0)
                                        .addComponent(tfLoteFebre1, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(sSeparador56, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(pnlFebreAmarelaInternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(pnlFebreAmarelaInternoLayout.createSequentialGroup()
                                        .addGap(20, 20, 20)
                                        .addComponent(lblLoteHepatite1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, 0)
                                        .addComponent(tfLoteFebre2, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(pnlFebreAmarelaInternoLayout.createSequentialGroup()
                                        .addGap(70, 70, 70)
                                        .addComponent(sSeparador55)))
                                .addGap(30, 30, 30)
                                .addGroup(pnlFebreAmarelaInternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlFebreAmarelaInternoLayout.createSequentialGroup()
                                        .addComponent(lblLoteHepatite2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, 0)
                                        .addComponent(tfLoteFebre3, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(sSeparador54, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(20, 20, 20)
                                .addComponent(lblLoteHepatite3, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(tfLoteFebre4, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlFebreAmarelaInternoLayout.createSequentialGroup()
                                .addGroup(pnlFebreAmarelaInternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(pnlFebreAmarelaInternoLayout.createSequentialGroup()
                                        .addComponent(lblValHepatite, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, 0)
                                        .addComponent(tfValFebre1, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(sSeparador57, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(20, 20, 20)
                                .addGroup(pnlFebreAmarelaInternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(pnlFebreAmarelaInternoLayout.createSequentialGroup()
                                        .addComponent(lblValHepatite1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, 0)
                                        .addComponent(tfValFebre2, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(sSeparador58, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(30, 30, 30)
                                .addGroup(pnlFebreAmarelaInternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(pnlFebreAmarelaInternoLayout.createSequentialGroup()
                                        .addComponent(lblValHepatite2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, 0)
                                        .addComponent(tfValFebre3, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(sSeparador59, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(20, 20, 20)
                                .addGroup(pnlFebreAmarelaInternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(pnlFebreAmarelaInternoLayout.createSequentialGroup()
                                        .addComponent(lblValHepatite3, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(pnlFebreAmarelaInternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(sSeparador53)
                                            .addComponent(tfValFebre4, javax.swing.GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE)))
                                    .addComponent(sSeparador60, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlFebreAmarelaInternoLayout.createSequentialGroup()
                                .addGap(50, 50, 50)
                                .addGroup(pnlFebreAmarelaInternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(tfFebre5, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(sSeparador64, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(70, 70, 70)
                                .addGroup(pnlFebreAmarelaInternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(tfFebre6, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(sSeparador63, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(80, 80, 80)
                                .addGroup(pnlFebreAmarelaInternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(pnlFebreAmarelaInternoLayout.createSequentialGroup()
                                        .addComponent(sSeparador62, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(sSeparador61, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(pnlFebreAmarelaInternoLayout.createSequentialGroup()
                                        .addComponent(tfFebre7, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(70, 70, 70)
                                        .addComponent(tfFebre8, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlFebreAmarelaInternoLayout.createSequentialGroup()
                                .addGroup(pnlFebreAmarelaInternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(pnlFebreAmarelaInternoLayout.createSequentialGroup()
                                        .addComponent(lblLoteHepatite4, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, 0)
                                        .addComponent(tfLoteFebre5, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(sSeparador65, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(20, 20, 20)
                                .addGroup(pnlFebreAmarelaInternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlFebreAmarelaInternoLayout.createSequentialGroup()
                                        .addComponent(lblLoteHepatite5, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, 0)
                                        .addComponent(tfLoteFebre6, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(sSeparador66, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(30, 30, 30)
                                .addGroup(pnlFebreAmarelaInternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlFebreAmarelaInternoLayout.createSequentialGroup()
                                        .addComponent(lblLoteHepatite6, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, 0)
                                        .addComponent(tfLoteFebre7, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(sSeparador67, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(pnlFebreAmarelaInternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(pnlFebreAmarelaInternoLayout.createSequentialGroup()
                                        .addGap(20, 20, 20)
                                        .addComponent(lblLoteHepatite7, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, 0)
                                        .addComponent(tfLoteFebre8, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(pnlFebreAmarelaInternoLayout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(sSeparador68, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlFebreAmarelaInternoLayout.createSequentialGroup()
                                .addGroup(pnlFebreAmarelaInternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(pnlFebreAmarelaInternoLayout.createSequentialGroup()
                                        .addComponent(lblValHepatite4, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, 0)
                                        .addComponent(tfValFebre5, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(sSeparador72, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(20, 20, 20)
                                .addGroup(pnlFebreAmarelaInternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlFebreAmarelaInternoLayout.createSequentialGroup()
                                        .addComponent(lblValHepatite5, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, 0)
                                        .addComponent(tfValFebre6, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(sSeparador70, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(30, 30, 30)
                                .addGroup(pnlFebreAmarelaInternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlFebreAmarelaInternoLayout.createSequentialGroup()
                                        .addComponent(lblValHepatite6, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, 0)
                                        .addComponent(tfValFebre7, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(sSeparador69, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(pnlFebreAmarelaInternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(pnlFebreAmarelaInternoLayout.createSequentialGroup()
                                        .addGap(20, 20, 20)
                                        .addComponent(lblValHepatite7, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, 0)
                                        .addComponent(tfValFebre8, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(pnlFebreAmarelaInternoLayout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(sSeparador71, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addContainerGap(375, Short.MAX_VALUE))
                    .addGroup(pnlFebreAmarelaInternoLayout.createSequentialGroup()
                        .addComponent(lblFebreAmarela)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        pnlFebreAmarelaInternoLayout.setVerticalGroup(
            pnlFebreAmarelaInternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFebreAmarelaInternoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblFebreAmarela)
                .addGap(18, 18, 18)
                .addGroup(pnlFebreAmarelaInternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tfFebre1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfFebre2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfFebre3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfFebre4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlFebreAmarelaInternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(sSeparador49, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sSeparador51, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sSeparador52, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sSeparador50, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlFebreAmarelaInternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblLoteHepatite)
                    .addComponent(tfLoteFebre1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblLoteHepatite1)
                    .addComponent(tfLoteFebre2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblLoteHepatite2)
                    .addComponent(tfLoteFebre3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblLoteHepatite3)
                    .addComponent(tfLoteFebre4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlFebreAmarelaInternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(sSeparador54, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sSeparador53, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sSeparador55, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sSeparador56, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlFebreAmarelaInternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblValHepatite)
                    .addComponent(tfValFebre1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblValHepatite1)
                    .addComponent(tfValFebre2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblValHepatite2)
                    .addComponent(tfValFebre3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblValHepatite3)
                    .addComponent(tfValFebre4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlFebreAmarelaInternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(sSeparador57, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sSeparador59, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sSeparador60, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sSeparador58, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(38, 38, 38)
                .addGroup(pnlFebreAmarelaInternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tfFebre5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfFebre6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfFebre7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfFebre8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlFebreAmarelaInternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(sSeparador64, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sSeparador62, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sSeparador61, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sSeparador63, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlFebreAmarelaInternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblLoteHepatite4)
                    .addComponent(tfLoteFebre5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblLoteHepatite5)
                    .addComponent(tfLoteFebre6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblLoteHepatite6)
                    .addComponent(tfLoteFebre7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblLoteHepatite7)
                    .addComponent(tfLoteFebre8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlFebreAmarelaInternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(sSeparador65, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sSeparador67, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sSeparador68, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sSeparador66, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlFebreAmarelaInternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblValHepatite4)
                    .addComponent(tfValFebre5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblValHepatite5)
                    .addComponent(tfValFebre6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblValHepatite6)
                    .addComponent(tfValFebre7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblValHepatite7)
                    .addComponent(tfValFebre8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlFebreAmarelaInternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(sSeparador72, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sSeparador69, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sSeparador71, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sSeparador70, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 121, Short.MAX_VALUE)
                .addGroup(pnlFebreAmarelaInternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnContinuar4, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnFinalizar4)
                    .addComponent(btnCancelar4))
                .addContainerGap())
        );

        javax.swing.GroupLayout pnlFebreLayout = new javax.swing.GroupLayout(pnlFebre);
        pnlFebre.setLayout(pnlFebreLayout);
        pnlFebreLayout.setHorizontalGroup(
            pnlFebreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFebreLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlFebreAmarelaInterno, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnlFebreLayout.setVerticalGroup(
            pnlFebreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFebreLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlFebreAmarelaInterno, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pnlCartao.addTab("Febre Amarela", pnlFebre);

        pnlFebreAmarela.setBackground(new java.awt.Color(255, 255, 255));

        pnlFebreInterno.setBackground(new java.awt.Color(255, 255, 255));

        lblLoteRevacinacao.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        lblLoteRevacinacao.setForeground(new java.awt.Color(102, 102, 102));
        lblLoteRevacinacao.setText("Lote:");

        lblDupla1.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        lblDupla1.setForeground(new java.awt.Color(102, 102, 102));
        lblDupla1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDupla1.setText("1º DOSE");

        lblValRevacinacao.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        lblValRevacinacao.setForeground(new java.awt.Color(102, 102, 102));
        lblValRevacinacao.setText("Val:");

        tfDuplaViral1.setBorder(null);
        tfDuplaViral1.setForeground(new java.awt.Color(102, 102, 102));
        try {
            tfDuplaViral1.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        tfDuplaViral1.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        tfDuplaViral1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfDuplaViral1ActionPerformed(evt);
            }
        });

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

        tfLoteDupla1.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        tfLoteDupla1.setForeground(new java.awt.Color(102, 102, 102));
        tfLoteDupla1.setBorder(null);

        tfValDupla1.setBorder(null);
        tfValDupla1.setForeground(new java.awt.Color(102, 102, 102));
        try {
            tfValDupla1.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        tfValDupla1.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N

        lblLoteFebre.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        lblLoteFebre.setForeground(new java.awt.Color(102, 102, 102));
        lblLoteFebre.setText("Lote:");

        lblValFebre.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        lblValFebre.setForeground(new java.awt.Color(102, 102, 102));
        lblValFebre.setText("Val:");

        lblDupla2.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        lblDupla2.setForeground(new java.awt.Color(102, 102, 102));
        lblDupla2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDupla2.setText("2º DOSE");

        tfDuplaViral2.setBorder(null);
        tfDuplaViral2.setForeground(new java.awt.Color(102, 102, 102));
        try {
            tfDuplaViral2.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        tfDuplaViral2.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N

        tfLoteDuplaViral2.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        tfLoteDuplaViral2.setForeground(new java.awt.Color(102, 102, 102));
        tfLoteDuplaViral2.setBorder(null);

        tfValDupla2.setBorder(null);
        tfValDupla2.setForeground(new java.awt.Color(102, 102, 102));
        try {
            tfValDupla2.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        tfValDupla2.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N

        lblDuplaViral.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        lblDuplaViral.setForeground(new java.awt.Color(102, 102, 102));
        lblDuplaViral.setText("DUPLA VIRAL (SARAMPO + RUBÉOLA)");

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

        btnCancelar5.setBackground(new java.awt.Color(102, 102, 102));
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

        sSeparador73.setForeground(new java.awt.Color(51, 51, 51));

        sSeparador74.setForeground(new java.awt.Color(51, 51, 51));

        sSeparador75.setForeground(new java.awt.Color(51, 51, 51));

        sSeparador76.setForeground(new java.awt.Color(51, 51, 51));

        sSeparador77.setForeground(new java.awt.Color(51, 51, 51));

        sSeparador78.setForeground(new java.awt.Color(51, 51, 51));

        javax.swing.GroupLayout pnlFebreInternoLayout = new javax.swing.GroupLayout(pnlFebreInterno);
        pnlFebreInterno.setLayout(pnlFebreInternoLayout);
        pnlFebreInternoLayout.setHorizontalGroup(
            pnlFebreInternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFebreInternoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlFebreInternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblDuplaViral)
                    .addGroup(pnlFebreInternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(pnlFebreInternoLayout.createSequentialGroup()
                            .addComponent(sSeparador76, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(60, 60, 60)
                            .addComponent(sSeparador75, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(pnlFebreInternoLayout.createSequentialGroup()
                            .addComponent(lblLoteFebre, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(0, 0, 0)
                            .addComponent(tfLoteDupla1, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(10, 10, 10)
                            .addComponent(lblLoteRevacinacao, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(0, 0, 0)
                            .addComponent(tfLoteDuplaViral2, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(pnlFebreInternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(pnlFebreInternoLayout.createSequentialGroup()
                            .addComponent(sSeparador77, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(60, 60, 60)
                            .addComponent(sSeparador78, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(pnlFebreInternoLayout.createSequentialGroup()
                            .addComponent(lblValFebre, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(0, 0, 0)
                            .addComponent(tfValDupla1, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(10, 10, 10)
                            .addComponent(lblValRevacinacao, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(0, 0, 0)
                            .addComponent(tfValDupla2, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(pnlFebreInternoLayout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addGroup(pnlFebreInternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblDupla1, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(pnlFebreInternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(sSeparador73)
                                .addComponent(tfDuplaViral1, javax.swing.GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE)))
                        .addGap(60, 60, 60)
                        .addGroup(pnlFebreInternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(sSeparador74, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tfDuplaViral2, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblDupla2, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(534, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlFebreInternoLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnCancelar5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnContinuar5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnFinalizar5)
                .addContainerGap())
        );
        pnlFebreInternoLayout.setVerticalGroup(
            pnlFebreInternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFebreInternoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblDuplaViral)
                .addGap(18, 18, 18)
                .addGroup(pnlFebreInternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDupla1)
                    .addComponent(lblDupla2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlFebreInternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlFebreInternoLayout.createSequentialGroup()
                        .addComponent(tfDuplaViral1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(sSeparador73, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlFebreInternoLayout.createSequentialGroup()
                        .addComponent(tfDuplaViral2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(sSeparador74, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlFebreInternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblLoteFebre)
                    .addComponent(tfLoteDupla1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblLoteRevacinacao)
                    .addComponent(tfLoteDuplaViral2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlFebreInternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(sSeparador76, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sSeparador75, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlFebreInternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblValFebre)
                    .addComponent(tfValDupla1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblValRevacinacao)
                    .addComponent(tfValDupla2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlFebreInternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(sSeparador77, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sSeparador78, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 248, Short.MAX_VALUE)
                .addGroup(pnlFebreInternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnFinalizar5)
                    .addComponent(btnContinuar5)
                    .addComponent(btnCancelar5))
                .addContainerGap())
        );

        javax.swing.GroupLayout pnlFebreAmarelaLayout = new javax.swing.GroupLayout(pnlFebreAmarela);
        pnlFebreAmarela.setLayout(pnlFebreAmarelaLayout);
        pnlFebreAmarelaLayout.setHorizontalGroup(
            pnlFebreAmarelaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFebreAmarelaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlFebreInterno, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnlFebreAmarelaLayout.setVerticalGroup(
            pnlFebreAmarelaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFebreAmarelaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlFebreInterno, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pnlCartao.addTab("Dupla Viral", pnlFebreAmarela);

        pnlTripliceViral.setBackground(new java.awt.Color(255, 255, 255));

        pnlTripliceInterno.setBackground(new java.awt.Color(255, 255, 255));

        lblVal1.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        lblVal1.setForeground(new java.awt.Color(102, 102, 102));
        lblVal1.setText("Val:");

        tfValTriplice1.setBorder(null);
        tfValTriplice1.setForeground(new java.awt.Color(102, 102, 102));
        try {
            tfValTriplice1.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        tfValTriplice1.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N

        lblDose4.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        lblDose4.setForeground(new java.awt.Color(102, 102, 102));
        lblDose4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDose4.setText("2º DOSE");

        tfTriplice2.setBorder(null);
        tfTriplice2.setForeground(new java.awt.Color(102, 102, 102));
        try {
            tfTriplice2.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        tfTriplice2.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N

        tfLoteTriplice2.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        tfLoteTriplice2.setForeground(new java.awt.Color(102, 102, 102));
        tfLoteTriplice2.setBorder(null);

        lblLote4.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        lblLote4.setForeground(new java.awt.Color(102, 102, 102));
        lblLote4.setText("Lote:");

        lblTriplice.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        lblTriplice.setForeground(new java.awt.Color(102, 102, 102));
        lblTriplice.setText("TRÍPLICE VIRAL (SARAMPO + RUBÉOLA + CAXUMBA)");

        lblVal4.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        lblVal4.setForeground(new java.awt.Color(102, 102, 102));
        lblVal4.setText("Val:");

        lblDose1.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        lblDose1.setForeground(new java.awt.Color(102, 102, 102));
        lblDose1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDose1.setText("1º DOSE");

        tfValTriplice2.setBorder(null);
        tfValTriplice2.setForeground(new java.awt.Color(102, 102, 102));
        try {
            tfValTriplice2.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        tfValTriplice2.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N

        tfTriplice1.setBorder(null);
        tfTriplice1.setForeground(new java.awt.Color(102, 102, 102));
        try {
            tfTriplice1.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        tfTriplice1.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N

        tfLoteTriplice1.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        tfLoteTriplice1.setForeground(new java.awt.Color(102, 102, 102));
        tfLoteTriplice1.setBorder(null);

        lblLote1.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        lblLote1.setForeground(new java.awt.Color(102, 102, 102));
        lblLote1.setText("Lote:");

        btnContinuar6.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btnContinuar6.setForeground(new java.awt.Color(102, 102, 102));
        btnContinuar6.setText("CONTINUAR");
        btnContinuar6.setBorderPainted(false);
        btnContinuar6.setContentAreaFilled(false);
        btnContinuar6.setDefaultCapable(false);
        btnContinuar6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnContinuar6MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnContinuar6MouseExited(evt);
            }
        });
        btnContinuar6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnContinuar6ActionPerformed(evt);
            }
        });

        sSeparador79.setForeground(new java.awt.Color(51, 51, 51));

        sSeparador80.setForeground(new java.awt.Color(51, 51, 51));

        sSeparador81.setForeground(new java.awt.Color(51, 51, 51));

        sSeparador82.setForeground(new java.awt.Color(51, 51, 51));

        sSeparador83.setForeground(new java.awt.Color(51, 51, 51));

        sSeparador84.setForeground(new java.awt.Color(51, 51, 51));

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

        btnCancelar6.setBackground(new java.awt.Color(102, 102, 102));
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

        javax.swing.GroupLayout pnlTripliceInternoLayout = new javax.swing.GroupLayout(pnlTripliceInterno);
        pnlTripliceInterno.setLayout(pnlTripliceInternoLayout);
        pnlTripliceInternoLayout.setHorizontalGroup(
            pnlTripliceInternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTripliceInternoLayout.createSequentialGroup()
                .addGroup(pnlTripliceInternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlTripliceInternoLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(pnlTripliceInternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblTriplice)
                            .addGroup(pnlTripliceInternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(pnlTripliceInternoLayout.createSequentialGroup()
                                    .addComponent(sSeparador82, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(60, 60, 60)
                                    .addComponent(sSeparador81, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(pnlTripliceInternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(pnlTripliceInternoLayout.createSequentialGroup()
                                        .addComponent(lblLote1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, 0)
                                        .addComponent(tfLoteTriplice1, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(10, 10, 10)
                                        .addComponent(lblLote4, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, 0)
                                        .addComponent(tfLoteTriplice2, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(pnlTripliceInternoLayout.createSequentialGroup()
                                        .addComponent(lblVal1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, 0)
                                        .addComponent(tfValTriplice1, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(10, 10, 10)
                                        .addComponent(lblVal4, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, 0)
                                        .addComponent(tfValTriplice2, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(pnlTripliceInternoLayout.createSequentialGroup()
                                        .addGap(50, 50, 50)
                                        .addGroup(pnlTripliceInternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(lblDose1, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(tfTriplice1, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(60, 60, 60)
                                        .addGroup(pnlTripliceInternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(tfTriplice2)
                                            .addComponent(lblDose4, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlTripliceInternoLayout.createSequentialGroup()
                                        .addComponent(sSeparador80, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(60, 60, 60)
                                        .addComponent(sSeparador79, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(pnlTripliceInternoLayout.createSequentialGroup()
                                    .addComponent(sSeparador83, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(60, 60, 60)
                                    .addComponent(sSeparador84, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 351, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlTripliceInternoLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnCancelar6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnContinuar6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnFinalizar6)))
                .addContainerGap())
        );
        pnlTripliceInternoLayout.setVerticalGroup(
            pnlTripliceInternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTripliceInternoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTriplice)
                .addGap(18, 18, 18)
                .addGroup(pnlTripliceInternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblDose1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblDose4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlTripliceInternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tfTriplice1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfTriplice2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlTripliceInternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(sSeparador80, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sSeparador79, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlTripliceInternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblLote1)
                    .addComponent(tfLoteTriplice1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblLote4)
                    .addComponent(tfLoteTriplice2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlTripliceInternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(sSeparador82, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sSeparador81, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlTripliceInternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblVal1)
                    .addComponent(tfValTriplice1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblVal4)
                    .addComponent(tfValTriplice2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlTripliceInternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(sSeparador83, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sSeparador84, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 247, Short.MAX_VALUE)
                .addGroup(pnlTripliceInternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnContinuar6)
                    .addComponent(btnFinalizar6)
                    .addComponent(btnCancelar6))
                .addContainerGap())
        );

        javax.swing.GroupLayout pnlTripliceViralLayout = new javax.swing.GroupLayout(pnlTripliceViral);
        pnlTripliceViral.setLayout(pnlTripliceViralLayout);
        pnlTripliceViralLayout.setHorizontalGroup(
            pnlTripliceViralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTripliceViralLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlTripliceInterno, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnlTripliceViralLayout.setVerticalGroup(
            pnlTripliceViralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTripliceViralLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlTripliceInterno, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pnlCartao.addTab("Tríplice Viral", pnlTripliceViral);

        pnlOutras.setBackground(new java.awt.Color(255, 255, 255));

        pnlTripliceInterno1.setBackground(new java.awt.Color(255, 255, 255));
        pnlTripliceInterno1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblOutras1.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        lblOutras1.setForeground(new java.awt.Color(102, 102, 102));
        lblOutras1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblOutras1.setText("OUTRAS VACINAS");
        pnlTripliceInterno1.add(lblOutras1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 11, 290, -1));

        lblDose5.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        lblDose5.setForeground(new java.awt.Color(102, 102, 102));
        lblDose5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDose5.setText("1º DOSE");
        pnlTripliceInterno1.add(lblDose5, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 57, 80, -1));

        lblDescricao1.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        lblDescricao1.setForeground(new java.awt.Color(102, 102, 102));
        lblDescricao1.setText("Desc.:");
        pnlTripliceInterno1.add(lblDescricao1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 102, 50, -1));

        tfOutra1.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        tfOutra1.setForeground(new java.awt.Color(102, 102, 102));
        tfOutra1.setBorder(null);
        pnlTripliceInterno1.add(tfOutra1, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 102, 80, -1));

        tfDtOutra1.setBorder(null);
        tfDtOutra1.setForeground(new java.awt.Color(102, 102, 102));
        try {
            tfDtOutra1.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        tfDtOutra1.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        pnlTripliceInterno1.add(tfDtOutra1, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 140, 80, -1));

        lblLote5.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        lblLote5.setForeground(new java.awt.Color(102, 102, 102));
        lblLote5.setText("Lote:");
        pnlTripliceInterno1.add(lblLote5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 180, 50, -1));

        tfLoteOutra1.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        tfLoteOutra1.setForeground(new java.awt.Color(102, 102, 102));
        tfLoteOutra1.setBorder(null);
        pnlTripliceInterno1.add(tfLoteOutra1, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 180, 80, -1));

        lblVal5.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        lblVal5.setForeground(new java.awt.Color(102, 102, 102));
        lblVal5.setText("Val:");
        pnlTripliceInterno1.add(lblVal5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 220, 50, -1));

        tfValOutra1.setBorder(null);
        tfValOutra1.setForeground(new java.awt.Color(102, 102, 102));
        try {
            tfValOutra1.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        tfValOutra1.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        pnlTripliceInterno1.add(tfValOutra1, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 220, 80, -1));

        lblDose6.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        lblDose6.setForeground(new java.awt.Color(102, 102, 102));
        lblDose6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDose6.setText("2º DOSE");
        pnlTripliceInterno1.add(lblDose6, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 57, 80, -1));

        lblDescricao2.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        lblDescricao2.setForeground(new java.awt.Color(102, 102, 102));
        lblDescricao2.setText("Desc.:");
        pnlTripliceInterno1.add(lblDescricao2, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 102, 50, -1));

        tfOutra1d2.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        tfOutra1d2.setForeground(new java.awt.Color(102, 102, 102));
        tfOutra1d2.setBorder(null);
        pnlTripliceInterno1.add(tfOutra1d2, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 102, 80, -1));

        tfDtOutra1d2.setBorder(null);
        tfDtOutra1d2.setForeground(new java.awt.Color(102, 102, 102));
        try {
            tfDtOutra1d2.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        tfDtOutra1d2.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        pnlTripliceInterno1.add(tfDtOutra1d2, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 140, 80, -1));

        lblLote6.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        lblLote6.setForeground(new java.awt.Color(102, 102, 102));
        lblLote6.setText("Lote:");
        pnlTripliceInterno1.add(lblLote6, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 180, 50, -1));

        tfLoteOutra1d2.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        tfLoteOutra1d2.setForeground(new java.awt.Color(102, 102, 102));
        tfLoteOutra1d2.setBorder(null);
        pnlTripliceInterno1.add(tfLoteOutra1d2, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 180, 80, -1));

        lblVal6.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        lblVal6.setForeground(new java.awt.Color(102, 102, 102));
        lblVal6.setText("Val:");
        pnlTripliceInterno1.add(lblVal6, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 220, 50, -1));

        tfValOutra1d2.setBorder(null);
        tfValOutra1d2.setForeground(new java.awt.Color(102, 102, 102));
        try {
            tfValOutra1d2.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        tfValOutra1d2.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        pnlTripliceInterno1.add(tfValOutra1d2, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 220, 80, -1));

        lblOutras2.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        lblOutras2.setForeground(new java.awt.Color(102, 102, 102));
        lblOutras2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblOutras2.setText("OUTRAS VACINAS");
        pnlTripliceInterno1.add(lblOutras2, new org.netbeans.lib.awtextra.AbsoluteConstraints(376, 11, 290, -1));

        lblDose7.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        lblDose7.setForeground(new java.awt.Color(102, 102, 102));
        lblDose7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDose7.setText("1º DOSE");
        pnlTripliceInterno1.add(lblDose7, new org.netbeans.lib.awtextra.AbsoluteConstraints(426, 57, 80, -1));

        lblDescricao3.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        lblDescricao3.setForeground(new java.awt.Color(102, 102, 102));
        lblDescricao3.setText("Desc.:");
        pnlTripliceInterno1.add(lblDescricao3, new org.netbeans.lib.awtextra.AbsoluteConstraints(376, 102, 50, -1));

        tfOutra2.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        tfOutra2.setForeground(new java.awt.Color(102, 102, 102));
        tfOutra2.setBorder(null);
        pnlTripliceInterno1.add(tfOutra2, new org.netbeans.lib.awtextra.AbsoluteConstraints(426, 102, 80, -1));

        tfDtOutra2.setBorder(null);
        tfDtOutra2.setForeground(new java.awt.Color(102, 102, 102));
        try {
            tfDtOutra2.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        tfDtOutra2.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        pnlTripliceInterno1.add(tfDtOutra2, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 140, 80, -1));

        lblLote7.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        lblLote7.setForeground(new java.awt.Color(102, 102, 102));
        lblLote7.setText("Lote:");
        pnlTripliceInterno1.add(lblLote7, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 180, 50, -1));

        tfLoteOutra2.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        tfLoteOutra2.setForeground(new java.awt.Color(102, 102, 102));
        tfLoteOutra2.setBorder(null);
        pnlTripliceInterno1.add(tfLoteOutra2, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 180, 80, -1));

        lblVal7.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        lblVal7.setForeground(new java.awt.Color(102, 102, 102));
        lblVal7.setText("Val:");
        pnlTripliceInterno1.add(lblVal7, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 220, 50, -1));

        tfValOutra2.setBorder(null);
        tfValOutra2.setForeground(new java.awt.Color(102, 102, 102));
        try {
            tfValOutra2.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        tfValOutra2.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        pnlTripliceInterno1.add(tfValOutra2, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 220, 80, -1));

        lblDose8.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        lblDose8.setForeground(new java.awt.Color(102, 102, 102));
        lblDose8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDose8.setText("2º DOSE");
        pnlTripliceInterno1.add(lblDose8, new org.netbeans.lib.awtextra.AbsoluteConstraints(586, 57, 80, -1));

        lblDescricao4.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        lblDescricao4.setForeground(new java.awt.Color(102, 102, 102));
        lblDescricao4.setText("Desc.:");
        pnlTripliceInterno1.add(lblDescricao4, new org.netbeans.lib.awtextra.AbsoluteConstraints(536, 102, 50, -1));

        tfOutra2d2.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        tfOutra2d2.setForeground(new java.awt.Color(102, 102, 102));
        tfOutra2d2.setBorder(null);
        pnlTripliceInterno1.add(tfOutra2d2, new org.netbeans.lib.awtextra.AbsoluteConstraints(586, 102, 80, -1));

        tfDtOutra2d2.setBorder(null);
        tfDtOutra2d2.setForeground(new java.awt.Color(102, 102, 102));
        try {
            tfDtOutra2d2.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        tfDtOutra2d2.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        pnlTripliceInterno1.add(tfDtOutra2d2, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 140, 80, -1));

        lblLote8.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        lblLote8.setForeground(new java.awt.Color(102, 102, 102));
        lblLote8.setText("Lote:");
        pnlTripliceInterno1.add(lblLote8, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 180, 50, -1));

        tfLoteOutra2d2.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        tfLoteOutra2d2.setForeground(new java.awt.Color(102, 102, 102));
        tfLoteOutra2d2.setBorder(null);
        pnlTripliceInterno1.add(tfLoteOutra2d2, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 180, 80, -1));

        lblVal8.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        lblVal8.setForeground(new java.awt.Color(102, 102, 102));
        lblVal8.setText("Val:");
        pnlTripliceInterno1.add(lblVal8, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 220, 50, -1));

        tfValOutra2d2.setBorder(null);
        tfValOutra2d2.setForeground(new java.awt.Color(102, 102, 102));
        try {
            tfValOutra2d2.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        tfValOutra2d2.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        pnlTripliceInterno1.add(tfValOutra2d2, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 220, 80, -1));

        btnContinuar7.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btnContinuar7.setForeground(new java.awt.Color(102, 102, 102));
        btnContinuar7.setText("CONTINUAR");
        btnContinuar7.setBorderPainted(false);
        btnContinuar7.setContentAreaFilled(false);
        btnContinuar7.setDefaultCapable(false);
        btnContinuar7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnContinuar7MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnContinuar7MouseExited(evt);
            }
        });
        btnContinuar7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnContinuar7ActionPerformed(evt);
            }
        });
        pnlTripliceInterno1.add(btnContinuar7, new org.netbeans.lib.awtextra.AbsoluteConstraints(685, 458, -1, -1));

        btnFinalizar7.setBackground(new java.awt.Color(102, 102, 102));
        btnFinalizar7.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btnFinalizar7.setForeground(new java.awt.Color(102, 102, 102));
        btnFinalizar7.setText("FINALIZAR");
        btnFinalizar7.setBorderPainted(false);
        btnFinalizar7.setContentAreaFilled(false);
        btnFinalizar7.setFocusPainted(false);
        btnFinalizar7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnFinalizar7MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnFinalizar7MouseExited(evt);
            }
        });
        btnFinalizar7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFinalizar7ActionPerformed(evt);
            }
        });
        pnlTripliceInterno1.add(btnFinalizar7, new org.netbeans.lib.awtextra.AbsoluteConstraints(834, 458, -1, -1));

        btnCancelar7.setBackground(new java.awt.Color(102, 102, 102));
        btnCancelar7.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btnCancelar7.setForeground(new java.awt.Color(102, 102, 102));
        btnCancelar7.setText("CANCELAR");
        btnCancelar7.setBorderPainted(false);
        btnCancelar7.setContentAreaFilled(false);
        btnCancelar7.setFocusPainted(false);
        btnCancelar7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnCancelar7MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnCancelar7MouseExited(evt);
            }
        });
        btnCancelar7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelar7ActionPerformed(evt);
            }
        });
        pnlTripliceInterno1.add(btnCancelar7, new org.netbeans.lib.awtextra.AbsoluteConstraints(546, 458, -1, -1));

        sSeparador85.setForeground(new java.awt.Color(51, 51, 51));
        pnlTripliceInterno1.add(sSeparador85, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 125, 80, 10));

        sSeparador86.setForeground(new java.awt.Color(51, 51, 51));
        pnlTripliceInterno1.add(sSeparador86, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 124, 80, 10));

        sSeparador87.setForeground(new java.awt.Color(51, 51, 51));
        pnlTripliceInterno1.add(sSeparador87, new org.netbeans.lib.awtextra.AbsoluteConstraints(426, 124, 80, 10));

        sSeparador88.setForeground(new java.awt.Color(51, 51, 51));
        pnlTripliceInterno1.add(sSeparador88, new org.netbeans.lib.awtextra.AbsoluteConstraints(586, 124, 80, 10));

        sSeparador89.setForeground(new java.awt.Color(51, 51, 51));
        pnlTripliceInterno1.add(sSeparador89, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 160, 80, 10));

        sSeparador90.setForeground(new java.awt.Color(51, 51, 51));
        pnlTripliceInterno1.add(sSeparador90, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 160, 80, 10));

        sSeparador91.setForeground(new java.awt.Color(51, 51, 51));
        pnlTripliceInterno1.add(sSeparador91, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 160, 80, 10));

        sSeparador92.setForeground(new java.awt.Color(51, 51, 51));
        pnlTripliceInterno1.add(sSeparador92, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 160, 80, 10));

        sSeparador93.setForeground(new java.awt.Color(51, 51, 51));
        pnlTripliceInterno1.add(sSeparador93, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 200, 80, 10));

        sSeparador94.setForeground(new java.awt.Color(51, 51, 51));
        pnlTripliceInterno1.add(sSeparador94, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 200, 80, 10));

        sSeparador95.setForeground(new java.awt.Color(51, 51, 51));
        pnlTripliceInterno1.add(sSeparador95, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 200, 80, 10));

        sSeparador96.setForeground(new java.awt.Color(51, 51, 51));
        pnlTripliceInterno1.add(sSeparador96, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 200, 80, 10));

        sSeparador97.setForeground(new java.awt.Color(51, 51, 51));
        pnlTripliceInterno1.add(sSeparador97, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 240, 80, 10));

        sSeparador98.setForeground(new java.awt.Color(51, 51, 51));
        pnlTripliceInterno1.add(sSeparador98, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 240, 80, 10));

        sSeparador99.setForeground(new java.awt.Color(51, 51, 51));
        pnlTripliceInterno1.add(sSeparador99, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 240, 80, 10));

        sSeparador100.setForeground(new java.awt.Color(51, 51, 51));
        pnlTripliceInterno1.add(sSeparador100, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 240, 80, 10));

        javax.swing.GroupLayout pnlOutrasLayout = new javax.swing.GroupLayout(pnlOutras);
        pnlOutras.setLayout(pnlOutrasLayout);
        pnlOutrasLayout.setHorizontalGroup(
            pnlOutrasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlOutrasLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlTripliceInterno1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnlOutrasLayout.setVerticalGroup(
            pnlOutrasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlOutrasLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlTripliceInterno1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pnlCartao.addTab("Outras", pnlOutras);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlCartao)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlCartao)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnContinuar1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnContinuar1MouseEntered
        // TODO add your handling code here:
        this.entraMouse(btnContinuar1);
    }//GEN-LAST:event_btnContinuar1MouseEntered

    private void btnContinuar1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnContinuar1MouseExited
        // TODO add your handling code here:
        this.saiMouse(btnContinuar1);
    }//GEN-LAST:event_btnContinuar1MouseExited

    private void btnContinuar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnContinuar1ActionPerformed
        //this.atualizarCartao();
        pnlCartao.setSelectedIndex(1);
    }//GEN-LAST:event_btnContinuar1ActionPerformed

    private void tfRuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfRuaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfRuaActionPerformed

    private void tfIdPacienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfIdPacienteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfIdPacienteActionPerformed

    private void tfLoteInfluenza3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfLoteInfluenza3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfLoteInfluenza3ActionPerformed

    private void btnContinuar3MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnContinuar3MouseEntered
        // TODO add your handling code here:
        this.entraMouse(btnContinuar3);
    }//GEN-LAST:event_btnContinuar3MouseEntered

    private void btnContinuar3MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnContinuar3MouseExited
        // TODO add your handling code here:
        this.saiMouse(btnContinuar3);
    }//GEN-LAST:event_btnContinuar3MouseExited

    private void btnContinuar3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnContinuar3ActionPerformed
        //this.atualizarInfluenza();
        pnlCartao.setSelectedIndex(3);
    }//GEN-LAST:event_btnContinuar3ActionPerformed

    private void btnContinuar4MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnContinuar4MouseEntered
        // TODO add your handling code here:
        this.entraMouse(btnContinuar4);
    }//GEN-LAST:event_btnContinuar4MouseEntered

    private void btnContinuar4MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnContinuar4MouseExited
        // TODO add your handling code here:
        this.saiMouse(btnContinuar4);
    }//GEN-LAST:event_btnContinuar4MouseExited

    private void btnContinuar4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnContinuar4ActionPerformed
        //this.atualizarFebreAmarela();
        pnlCartao.setSelectedIndex(4);
    }//GEN-LAST:event_btnContinuar4ActionPerformed

    private void tfDuplaViral1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfDuplaViral1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfDuplaViral1ActionPerformed

    private void btnContinuar5MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnContinuar5MouseEntered
        // TODO add your handling code here:
        this.entraMouse(btnContinuar5);
    }//GEN-LAST:event_btnContinuar5MouseEntered

    private void btnContinuar5MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnContinuar5MouseExited
        // TODO add your handling code here:
        this.saiMouse(btnContinuar5);
    }//GEN-LAST:event_btnContinuar5MouseExited

    private void btnContinuar5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnContinuar5ActionPerformed
        //this.atualizarDuplaViral();
        pnlCartao.setSelectedIndex(5);
    }//GEN-LAST:event_btnContinuar5ActionPerformed

    private void btnContinuar6MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnContinuar6MouseEntered
        // TODO add your handling code here:
        this.entraMouse(btnContinuar6);
    }//GEN-LAST:event_btnContinuar6MouseEntered

    private void btnContinuar6MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnContinuar6MouseExited
        // TODO add your handling code here:
        this.saiMouse(btnContinuar6);
    }//GEN-LAST:event_btnContinuar6MouseExited

    private void btnContinuar6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnContinuar6ActionPerformed
        //this.atualizarTripliceViral();
        pnlCartao.setSelectedIndex(6);
    }//GEN-LAST:event_btnContinuar6ActionPerformed

    private void btnContinuar7MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnContinuar7MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_btnContinuar7MouseEntered

    private void btnContinuar7MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnContinuar7MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_btnContinuar7MouseExited

    private void btnContinuar7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnContinuar7ActionPerformed
        // TODO add your handling code here:
        //this.atualizarOutras();
    }//GEN-LAST:event_btnContinuar7ActionPerformed

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
        pnlCartao.setSelectedIndex(2);
    }//GEN-LAST:event_btnContinuar2ActionPerformed

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
        this.sair();
    }//GEN-LAST:event_btnFinalizar6ActionPerformed

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
        this.botaoFinalizar();
    }//GEN-LAST:event_btnCancelar6ActionPerformed

    private void btnFinalizar7MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnFinalizar7MouseEntered
        // TODO add your handling code here:
        this.entraMouse(btnFinalizar7);
    }//GEN-LAST:event_btnFinalizar7MouseEntered

    private void btnFinalizar7MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnFinalizar7MouseExited
        // TODO add your handling code here:
        this.saiMouse(btnFinalizar7);
    }//GEN-LAST:event_btnFinalizar7MouseExited

    private void btnFinalizar7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFinalizar7ActionPerformed
        // TODO add your handling code here:
        this.botaoFinalizar();
    }//GEN-LAST:event_btnFinalizar7ActionPerformed

    private void btnCancelar7MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCancelar7MouseEntered
        // TODO add your handling code here:
        this.entraMouse(btnCancelar7);
    }//GEN-LAST:event_btnCancelar7MouseEntered

    private void btnCancelar7MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCancelar7MouseExited
        // TODO add your handling code here:
        this.saiMouse(btnCancelar7);
    }//GEN-LAST:event_btnCancelar7MouseExited

    private void btnCancelar7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelar7ActionPerformed
        // TODO add your handling code here:
        this.sair();
    }//GEN-LAST:event_btnCancelar7ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar1;
    private javax.swing.JButton btnCancelar2;
    private javax.swing.JButton btnCancelar3;
    private javax.swing.JButton btnCancelar4;
    private javax.swing.JButton btnCancelar5;
    private javax.swing.JButton btnCancelar6;
    private javax.swing.JButton btnCancelar7;
    private javax.swing.JButton btnContinuar1;
    private javax.swing.JButton btnContinuar2;
    private javax.swing.JButton btnContinuar3;
    private javax.swing.JButton btnContinuar4;
    private javax.swing.JButton btnContinuar5;
    private javax.swing.JButton btnContinuar6;
    private javax.swing.JButton btnContinuar7;
    private javax.swing.JButton btnFinalizar1;
    private javax.swing.JButton btnFinalizar2;
    private javax.swing.JButton btnFinalizar3;
    private javax.swing.JButton btnFinalizar4;
    private javax.swing.JButton btnFinalizar5;
    private javax.swing.JButton btnFinalizar6;
    private javax.swing.JButton btnFinalizar7;
    private javax.swing.JLabel lblBairro;
    private javax.swing.JLabel lblCpf;
    private javax.swing.JLabel lblDN;
    private javax.swing.JLabel lblDescricao1;
    private javax.swing.JLabel lblDescricao2;
    private javax.swing.JLabel lblDescricao3;
    private javax.swing.JLabel lblDescricao4;
    private javax.swing.JLabel lblDose;
    private javax.swing.JLabel lblDose1;
    private javax.swing.JLabel lblDose2;
    private javax.swing.JLabel lblDose3;
    private javax.swing.JLabel lblDose4;
    private javax.swing.JLabel lblDose5;
    private javax.swing.JLabel lblDose6;
    private javax.swing.JLabel lblDose7;
    private javax.swing.JLabel lblDose8;
    private javax.swing.JLabel lblDupla1;
    private javax.swing.JLabel lblDupla2;
    private javax.swing.JLabel lblDuplaViral;
    private javax.swing.JLabel lblFebreAmarela;
    private javax.swing.JLabel lblGRS;
    private javax.swing.JLabel lblIdPaciente;
    private javax.swing.JLabel lblInfluenza;
    private javax.swing.JLabel lblLote;
    private javax.swing.JLabel lblLote1;
    private javax.swing.JLabel lblLote2;
    private javax.swing.JLabel lblLote3;
    private javax.swing.JLabel lblLote4;
    private javax.swing.JLabel lblLote5;
    private javax.swing.JLabel lblLote6;
    private javax.swing.JLabel lblLote7;
    private javax.swing.JLabel lblLote8;
    private javax.swing.JLabel lblLoteFebre;
    private javax.swing.JLabel lblLoteHepatite;
    private javax.swing.JLabel lblLoteHepatite1;
    private javax.swing.JLabel lblLoteHepatite2;
    private javax.swing.JLabel lblLoteHepatite3;
    private javax.swing.JLabel lblLoteHepatite4;
    private javax.swing.JLabel lblLoteHepatite5;
    private javax.swing.JLabel lblLoteHepatite6;
    private javax.swing.JLabel lblLoteHepatite7;
    private javax.swing.JLabel lblLoteInfluenza;
    private javax.swing.JLabel lblLoteInfluenza1;
    private javax.swing.JLabel lblLoteInfluenza2;
    private javax.swing.JLabel lblLoteInfluenza3;
    private javax.swing.JLabel lblLoteInfluenza4;
    private javax.swing.JLabel lblLoteInfluenza5;
    private javax.swing.JLabel lblLoteInfluenza6;
    private javax.swing.JLabel lblLoteInfluenza7;
    private javax.swing.JLabel lblLoteReforco;
    private javax.swing.JLabel lblLoteRevacinacao;
    private javax.swing.JLabel lblMunicipio;
    private javax.swing.JLabel lblNome;
    private javax.swing.JLabel lblNumero;
    private javax.swing.JLabel lblOutras1;
    private javax.swing.JLabel lblOutras2;
    private javax.swing.JLabel lblReforco;
    private javax.swing.JLabel lblRua;
    private javax.swing.JLabel lblTelefone;
    private javax.swing.JLabel lblTipoSanguineo;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JLabel lblTriplice;
    private javax.swing.JLabel lblUF;
    private javax.swing.JLabel lblVal;
    private javax.swing.JLabel lblVal1;
    private javax.swing.JLabel lblVal2;
    private javax.swing.JLabel lblVal3;
    private javax.swing.JLabel lblVal4;
    private javax.swing.JLabel lblVal5;
    private javax.swing.JLabel lblVal6;
    private javax.swing.JLabel lblVal7;
    private javax.swing.JLabel lblVal8;
    private javax.swing.JLabel lblValFebre;
    private javax.swing.JLabel lblValHepatite;
    private javax.swing.JLabel lblValHepatite1;
    private javax.swing.JLabel lblValHepatite2;
    private javax.swing.JLabel lblValHepatite3;
    private javax.swing.JLabel lblValHepatite4;
    private javax.swing.JLabel lblValHepatite5;
    private javax.swing.JLabel lblValHepatite6;
    private javax.swing.JLabel lblValHepatite7;
    private javax.swing.JLabel lblValInfluenza;
    private javax.swing.JLabel lblValInfluenza1;
    private javax.swing.JLabel lblValInfluenza2;
    private javax.swing.JLabel lblValInfluenza3;
    private javax.swing.JLabel lblValInfluenza4;
    private javax.swing.JLabel lblValInfluenza5;
    private javax.swing.JLabel lblValInfluenza6;
    private javax.swing.JLabel lblValInfluenza7;
    private javax.swing.JLabel lblValReforco;
    private javax.swing.JLabel lblValRevacinacao;
    private javax.swing.JTabbedPane pnlCartao;
    private javax.swing.JPanel pnlDados;
    private javax.swing.JPanel pnlDadosInterno;
    private javax.swing.JPanel pnlDuplaAdulta;
    private javax.swing.JPanel pnlDuplaAdultoInterno;
    private javax.swing.JPanel pnlFebre;
    private javax.swing.JPanel pnlFebreAmarela;
    private javax.swing.JPanel pnlFebreAmarelaInterno;
    private javax.swing.JPanel pnlFebreInterno;
    private javax.swing.JPanel pnlInfluenza;
    private javax.swing.JPanel pnlInfluenzaInterno;
    private javax.swing.JPanel pnlOutras;
    private javax.swing.JPanel pnlTripliceInterno;
    private javax.swing.JPanel pnlTripliceInterno1;
    private javax.swing.JPanel pnlTripliceViral;
    private javax.swing.JSeparator sSeparador1;
    private javax.swing.JSeparator sSeparador10;
    private javax.swing.JSeparator sSeparador100;
    private javax.swing.JSeparator sSeparador11;
    private javax.swing.JSeparator sSeparador12;
    private javax.swing.JSeparator sSeparador13;
    private javax.swing.JSeparator sSeparador14;
    private javax.swing.JSeparator sSeparador15;
    private javax.swing.JSeparator sSeparador16;
    private javax.swing.JSeparator sSeparador17;
    private javax.swing.JSeparator sSeparador18;
    private javax.swing.JSeparator sSeparador19;
    private javax.swing.JSeparator sSeparador2;
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
    private javax.swing.JSeparator sSeparador3;
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
    private javax.swing.JSeparator sSeparador4;
    private javax.swing.JSeparator sSeparador40;
    private javax.swing.JSeparator sSeparador41;
    private javax.swing.JSeparator sSeparador42;
    private javax.swing.JSeparator sSeparador43;
    private javax.swing.JSeparator sSeparador44;
    private javax.swing.JSeparator sSeparador45;
    private javax.swing.JSeparator sSeparador46;
    private javax.swing.JSeparator sSeparador47;
    private javax.swing.JSeparator sSeparador48;
    private javax.swing.JSeparator sSeparador49;
    private javax.swing.JSeparator sSeparador5;
    private javax.swing.JSeparator sSeparador50;
    private javax.swing.JSeparator sSeparador51;
    private javax.swing.JSeparator sSeparador52;
    private javax.swing.JSeparator sSeparador53;
    private javax.swing.JSeparator sSeparador54;
    private javax.swing.JSeparator sSeparador55;
    private javax.swing.JSeparator sSeparador56;
    private javax.swing.JSeparator sSeparador57;
    private javax.swing.JSeparator sSeparador58;
    private javax.swing.JSeparator sSeparador59;
    private javax.swing.JSeparator sSeparador6;
    private javax.swing.JSeparator sSeparador60;
    private javax.swing.JSeparator sSeparador61;
    private javax.swing.JSeparator sSeparador62;
    private javax.swing.JSeparator sSeparador63;
    private javax.swing.JSeparator sSeparador64;
    private javax.swing.JSeparator sSeparador65;
    private javax.swing.JSeparator sSeparador66;
    private javax.swing.JSeparator sSeparador67;
    private javax.swing.JSeparator sSeparador68;
    private javax.swing.JSeparator sSeparador69;
    private javax.swing.JSeparator sSeparador7;
    private javax.swing.JSeparator sSeparador70;
    private javax.swing.JSeparator sSeparador71;
    private javax.swing.JSeparator sSeparador72;
    private javax.swing.JSeparator sSeparador73;
    private javax.swing.JSeparator sSeparador74;
    private javax.swing.JSeparator sSeparador75;
    private javax.swing.JSeparator sSeparador76;
    private javax.swing.JSeparator sSeparador77;
    private javax.swing.JSeparator sSeparador78;
    private javax.swing.JSeparator sSeparador79;
    private javax.swing.JSeparator sSeparador8;
    private javax.swing.JSeparator sSeparador80;
    private javax.swing.JSeparator sSeparador81;
    private javax.swing.JSeparator sSeparador82;
    private javax.swing.JSeparator sSeparador83;
    private javax.swing.JSeparator sSeparador84;
    private javax.swing.JSeparator sSeparador85;
    private javax.swing.JSeparator sSeparador86;
    private javax.swing.JSeparator sSeparador87;
    private javax.swing.JSeparator sSeparador88;
    private javax.swing.JSeparator sSeparador89;
    private javax.swing.JSeparator sSeparador9;
    private javax.swing.JSeparator sSeparador90;
    private javax.swing.JSeparator sSeparador91;
    private javax.swing.JSeparator sSeparador92;
    private javax.swing.JSeparator sSeparador93;
    private javax.swing.JSeparator sSeparador94;
    private javax.swing.JSeparator sSeparador95;
    private javax.swing.JSeparator sSeparador96;
    private javax.swing.JSeparator sSeparador97;
    private javax.swing.JSeparator sSeparador98;
    private javax.swing.JSeparator sSeparador99;
    private javax.swing.JTextField tfBairro;
    private javax.swing.JFormattedTextField tfCpf;
    private javax.swing.JFormattedTextField tfDN;
    private javax.swing.JFormattedTextField tfDose1;
    private javax.swing.JFormattedTextField tfDose2;
    private javax.swing.JFormattedTextField tfDose3;
    private javax.swing.JFormattedTextField tfDtOutra1;
    private javax.swing.JFormattedTextField tfDtOutra1d2;
    private javax.swing.JFormattedTextField tfDtOutra2;
    private javax.swing.JFormattedTextField tfDtOutra2d2;
    private javax.swing.JFormattedTextField tfDuplaViral1;
    private javax.swing.JFormattedTextField tfDuplaViral2;
    private javax.swing.JFormattedTextField tfFebre1;
    private javax.swing.JFormattedTextField tfFebre2;
    private javax.swing.JFormattedTextField tfFebre3;
    private javax.swing.JFormattedTextField tfFebre4;
    private javax.swing.JFormattedTextField tfFebre5;
    private javax.swing.JFormattedTextField tfFebre6;
    private javax.swing.JFormattedTextField tfFebre7;
    private javax.swing.JFormattedTextField tfFebre8;
    private javax.swing.JTextField tfGRS;
    private javax.swing.JTextField tfIdPaciente;
    private javax.swing.JFormattedTextField tfInfluenza1;
    private javax.swing.JFormattedTextField tfInfluenza2;
    private javax.swing.JFormattedTextField tfInfluenza3;
    private javax.swing.JFormattedTextField tfInfluenza4;
    private javax.swing.JFormattedTextField tfInfluenza5;
    private javax.swing.JFormattedTextField tfInfluenza6;
    private javax.swing.JFormattedTextField tfInfluenza7;
    private javax.swing.JFormattedTextField tfInfluenza8;
    private javax.swing.JTextField tfLote;
    private javax.swing.JTextField tfLote2;
    private javax.swing.JTextField tfLote3;
    private javax.swing.JTextField tfLoteDupla1;
    private javax.swing.JTextField tfLoteDuplaViral2;
    private javax.swing.JTextField tfLoteFebre1;
    private javax.swing.JTextField tfLoteFebre2;
    private javax.swing.JTextField tfLoteFebre3;
    private javax.swing.JTextField tfLoteFebre4;
    private javax.swing.JTextField tfLoteFebre5;
    private javax.swing.JTextField tfLoteFebre6;
    private javax.swing.JTextField tfLoteFebre7;
    private javax.swing.JTextField tfLoteFebre8;
    private javax.swing.JTextField tfLoteInfluenza1;
    private javax.swing.JTextField tfLoteInfluenza2;
    private javax.swing.JTextField tfLoteInfluenza3;
    private javax.swing.JTextField tfLoteInfluenza4;
    private javax.swing.JTextField tfLoteInfluenza5;
    private javax.swing.JTextField tfLoteInfluenza6;
    private javax.swing.JTextField tfLoteInfluenza7;
    private javax.swing.JTextField tfLoteInfluenza8;
    private javax.swing.JTextField tfLoteOutra1;
    private javax.swing.JTextField tfLoteOutra1d2;
    private javax.swing.JTextField tfLoteOutra2;
    private javax.swing.JTextField tfLoteOutra2d2;
    private javax.swing.JTextField tfLoteReforco;
    private javax.swing.JTextField tfLoteTriplice1;
    private javax.swing.JTextField tfLoteTriplice2;
    private javax.swing.JTextField tfMunicipio;
    private javax.swing.JTextField tfNCasa;
    private javax.swing.JTextField tfNome;
    private javax.swing.JTextField tfOutra1;
    private javax.swing.JTextField tfOutra1d2;
    private javax.swing.JTextField tfOutra2;
    private javax.swing.JTextField tfOutra2d2;
    private javax.swing.JFormattedTextField tfReforco;
    private javax.swing.JTextField tfRua;
    private javax.swing.JTextField tfTelefone;
    private javax.swing.JTextField tfTipoSanguineo;
    private javax.swing.JFormattedTextField tfTriplice1;
    private javax.swing.JFormattedTextField tfTriplice2;
    private javax.swing.JTextField tfUF;
    private javax.swing.JFormattedTextField tfVal1;
    private javax.swing.JFormattedTextField tfVal2;
    private javax.swing.JFormattedTextField tfVal3;
    private javax.swing.JFormattedTextField tfValDupla1;
    private javax.swing.JFormattedTextField tfValDupla2;
    private javax.swing.JFormattedTextField tfValFebre1;
    private javax.swing.JFormattedTextField tfValFebre2;
    private javax.swing.JFormattedTextField tfValFebre3;
    private javax.swing.JFormattedTextField tfValFebre4;
    private javax.swing.JFormattedTextField tfValFebre5;
    private javax.swing.JFormattedTextField tfValFebre6;
    private javax.swing.JFormattedTextField tfValFebre7;
    private javax.swing.JFormattedTextField tfValFebre8;
    private javax.swing.JFormattedTextField tfValInfluenza1;
    private javax.swing.JFormattedTextField tfValInfluenza2;
    private javax.swing.JFormattedTextField tfValInfluenza3;
    private javax.swing.JFormattedTextField tfValInfluenza4;
    private javax.swing.JFormattedTextField tfValInfluenza5;
    private javax.swing.JFormattedTextField tfValInfluenza6;
    private javax.swing.JFormattedTextField tfValInfluenza7;
    private javax.swing.JFormattedTextField tfValInfluenza8;
    private javax.swing.JFormattedTextField tfValOutra1;
    private javax.swing.JFormattedTextField tfValOutra1d2;
    private javax.swing.JFormattedTextField tfValOutra2;
    private javax.swing.JFormattedTextField tfValOutra2d2;
    private javax.swing.JFormattedTextField tfValReforco;
    private javax.swing.JFormattedTextField tfValTriplice1;
    private javax.swing.JFormattedTextField tfValTriplice2;
    // End of variables declaration//GEN-END:variables
}
