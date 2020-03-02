/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
import principal.PesquisaPaciente;

/**
 *
 * @author Franciele Alves Barbosa e Rogério Costa Negro Rocha
 */
public class AlterarCartao extends javax.swing.JFrame {

    Connection conn = null;

    /**
     * Creates new form AdicionarCartao
     */
    public AlterarCartao() {
        initComponents();
        try {
            conn = MysqlConnect.connectDB();
            this.imprimir();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao conectar com o Banco de Dados " /*+ ex.getMessage()*/, "ERRO", JOptionPane.ERROR_MESSAGE);
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
    //Connection conn= ConectionFactory.getConnection();

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
            JOptionPane.showMessageDialog(null, "Erro: " + ex.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
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
            JOptionPane.showMessageDialog(null, "Erro: " + ex.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
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
            JOptionPane.showMessageDialog(null, "Erro: " + ex.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
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
            JOptionPane.showMessageDialog(null, "Erro: " + ex.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
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
            JOptionPane.showMessageDialog(null, "Erro: " + ex.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
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
            JOptionPane.showMessageDialog(null, "Erro: " + ex.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
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
            JOptionPane.showMessageDialog(null, "Erro: " + ex.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
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

            JOptionPane.showMessageDialog(null, "Salvo com sucesso!");

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar: " + ex.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
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

            JOptionPane.showMessageDialog(null, "Salvo com sucesso!");

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar: " + ex.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
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

            JOptionPane.showMessageDialog(null, "Salvo com sucesso!");

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar: " + ex.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
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

            JOptionPane.showMessageDialog(null, "Salvo com sucesso!");

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar: " + ex.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
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

            JOptionPane.showMessageDialog(null, "Salvo com sucesso!");

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar: " + ex.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
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

            JOptionPane.showMessageDialog(null, "Salvo com sucesso!");

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar: " + ex.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
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

        pnlCartao = new javax.swing.JTabbedPane();
        pnlDados = new javax.swing.JPanel();
        pnlDadosInterno = new javax.swing.JPanel();
        lblGRS = new javax.swing.JLabel();
        lblTipoSanguineo = new javax.swing.JLabel();
        tfGRS = new javax.swing.JTextField();
        tfTipoSanguineo = new javax.swing.JTextField();
        btnGravar = new javax.swing.JButton();
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
        lblFundo1 = new javax.swing.JLabel();
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
        btnSalvar = new javax.swing.JButton();
        tfLote = new javax.swing.JTextField();
        lblVal = new javax.swing.JLabel();
        tfVal1 = new javax.swing.JFormattedTextField();
        tfVal2 = new javax.swing.JFormattedTextField();
        lblVal2 = new javax.swing.JLabel();
        lblLote2 = new javax.swing.JLabel();
        tfLote2 = new javax.swing.JTextField();
        tfDose2 = new javax.swing.JFormattedTextField();
        lblDose2 = new javax.swing.JLabel();
        lblFundo2 = new javax.swing.JLabel();
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
        btnSalvarInfluenza = new javax.swing.JButton();
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
        lblFundo3 = new javax.swing.JLabel();
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
        btnSalvarFebre = new javax.swing.JButton();
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
        lblFundo4 = new javax.swing.JLabel();
        pnlFebreAmarela = new javax.swing.JPanel();
        pnlFebreInterno = new javax.swing.JPanel();
        lblLoteRevacinacao = new javax.swing.JLabel();
        lblDupla1 = new javax.swing.JLabel();
        lblValRevacinacao = new javax.swing.JLabel();
        tfDuplaViral1 = new javax.swing.JFormattedTextField();
        btnSalvarDuplaViral = new javax.swing.JButton();
        tfLoteDupla1 = new javax.swing.JTextField();
        tfValDupla1 = new javax.swing.JFormattedTextField();
        lblLoteFebre = new javax.swing.JLabel();
        lblValFebre = new javax.swing.JLabel();
        lblDupla2 = new javax.swing.JLabel();
        tfDuplaViral2 = new javax.swing.JFormattedTextField();
        tfLoteDuplaViral2 = new javax.swing.JTextField();
        tfValDupla2 = new javax.swing.JFormattedTextField();
        lblDuplaViral = new javax.swing.JLabel();
        lblFundo5 = new javax.swing.JLabel();
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
        btnSalvarTriplice = new javax.swing.JButton();
        lblFundo6 = new javax.swing.JLabel();
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
        btnSalvarOutras = new javax.swing.JButton();
        lblFundo7 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Cartão de Vacinação");
        setIconImage(new javax.swing.ImageIcon(getClass().getResource("/imagens/Quiron.png")).getImage());
        setResizable(false);

        pnlCartao.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N

        pnlDadosInterno.setLayout(null);

        lblGRS.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblGRS.setForeground(new java.awt.Color(255, 255, 255));
        lblGRS.setText("GRS");
        pnlDadosInterno.add(lblGRS);
        lblGRS.setBounds(280, 280, 50, 17);

        lblTipoSanguineo.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblTipoSanguineo.setForeground(new java.awt.Color(255, 255, 255));
        lblTipoSanguineo.setText("TIPO SANGUÍNEO");
        lblTipoSanguineo.setEnabled(false);
        pnlDadosInterno.add(lblTipoSanguineo);
        lblTipoSanguineo.setBounds(420, 120, 130, 17);

        tfGRS.setBackground(new java.awt.Color(153, 153, 153));
        tfGRS.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        tfGRS.setForeground(new java.awt.Color(255, 255, 255));
        pnlDadosInterno.add(tfGRS);
        tfGRS.setBounds(340, 280, 76, 23);

        tfTipoSanguineo.setEditable(false);
        tfTipoSanguineo.setBackground(new java.awt.Color(153, 153, 153));
        tfTipoSanguineo.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        tfTipoSanguineo.setForeground(new java.awt.Color(255, 255, 255));
        pnlDadosInterno.add(tfTipoSanguineo);
        tfTipoSanguineo.setBounds(550, 120, 110, 23);

        btnGravar.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btnGravar.setForeground(new java.awt.Color(255, 255, 255));
        btnGravar.setText("SALVAR");
        btnGravar.setBorderPainted(false);
        btnGravar.setContentAreaFilled(false);
        btnGravar.setFocusPainted(false);
        btnGravar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnGravarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnGravarMouseExited(evt);
            }
        });
        btnGravar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGravarActionPerformed(evt);
            }
        });
        pnlDadosInterno.add(btnGravar);
        btnGravar.setBounds(590, 360, 120, 30);

        lblCpf.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblCpf.setForeground(new java.awt.Color(255, 255, 255));
        lblCpf.setText("CPF");
        lblCpf.setEnabled(false);
        pnlDadosInterno.add(lblCpf);
        lblCpf.setBounds(20, 120, 60, 17);

        lblMunicipio.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblMunicipio.setForeground(new java.awt.Color(255, 255, 255));
        lblMunicipio.setText("MUNICÍPIO");
        lblMunicipio.setEnabled(false);
        pnlDadosInterno.add(lblMunicipio);
        lblMunicipio.setBounds(20, 220, 90, 17);

        tfCpf.setEditable(false);
        tfCpf.setBackground(new java.awt.Color(153, 153, 153));
        tfCpf.setForeground(new java.awt.Color(255, 255, 255));
        try {
            tfCpf.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###.###.###-##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        tfCpf.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        pnlDadosInterno.add(tfCpf);
        tfCpf.setBounds(110, 120, 117, 23);

        tfMunicipio.setEditable(false);
        tfMunicipio.setBackground(new java.awt.Color(153, 153, 153));
        tfMunicipio.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        tfMunicipio.setForeground(new java.awt.Color(255, 255, 255));
        pnlDadosInterno.add(tfMunicipio);
        tfMunicipio.setBounds(110, 220, 334, 23);

        lblUF.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblUF.setForeground(new java.awt.Color(255, 255, 255));
        lblUF.setText("UF");
        lblUF.setEnabled(false);
        pnlDadosInterno.add(lblUF);
        lblUF.setBounds(470, 220, 40, 17);

        lblNome.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblNome.setForeground(new java.awt.Color(255, 255, 255));
        lblNome.setText("NOME");
        lblNome.setEnabled(false);
        pnlDadosInterno.add(lblNome);
        lblNome.setBounds(20, 70, 60, 17);

        tfUF.setEditable(false);
        tfUF.setBackground(new java.awt.Color(153, 153, 153));
        tfUF.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        tfUF.setForeground(new java.awt.Color(255, 255, 255));
        pnlDadosInterno.add(tfUF);
        tfUF.setBounds(510, 220, 70, 23);

        tfNome.setEditable(false);
        tfNome.setBackground(new java.awt.Color(153, 153, 153));
        tfNome.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        tfNome.setForeground(new java.awt.Color(255, 255, 255));
        pnlDadosInterno.add(tfNome);
        tfNome.setBounds(110, 70, 357, 23);

        lblTelefone.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblTelefone.setForeground(new java.awt.Color(255, 255, 255));
        lblTelefone.setText("TELEFONE");
        lblTelefone.setEnabled(false);
        pnlDadosInterno.add(lblTelefone);
        lblTelefone.setBounds(20, 280, 90, 17);

        lblDN.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblDN.setForeground(new java.awt.Color(255, 255, 255));
        lblDN.setText("NASCIMENTO");
        lblDN.setEnabled(false);
        pnlDadosInterno.add(lblDN);
        lblDN.setBounds(480, 70, 110, 17);

        tfTelefone.setEditable(false);
        tfTelefone.setBackground(new java.awt.Color(153, 153, 153));
        tfTelefone.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        tfTelefone.setForeground(new java.awt.Color(255, 255, 255));
        pnlDadosInterno.add(tfTelefone);
        tfTelefone.setBounds(110, 280, 111, 23);

        tfDN.setEditable(false);
        tfDN.setBackground(new java.awt.Color(153, 153, 153));
        tfDN.setForeground(new java.awt.Color(255, 255, 255));
        try {
            tfDN.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        tfDN.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        pnlDadosInterno.add(tfDN);
        tfDN.setBounds(580, 70, 80, 20);

        lblRua.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblRua.setForeground(new java.awt.Color(255, 255, 255));
        lblRua.setText("RUA");
        lblRua.setEnabled(false);
        pnlDadosInterno.add(lblRua);
        lblRua.setBounds(20, 170, 40, 17);

        tfRua.setEditable(false);
        tfRua.setBackground(new java.awt.Color(153, 153, 153));
        tfRua.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        tfRua.setForeground(new java.awt.Color(255, 255, 255));
        tfRua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfRuaActionPerformed(evt);
            }
        });
        pnlDadosInterno.add(tfRua);
        tfRua.setBounds(110, 170, 150, 23);

        lblNumero.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblNumero.setForeground(new java.awt.Color(255, 255, 255));
        lblNumero.setText("Nº");
        lblNumero.setEnabled(false);
        pnlDadosInterno.add(lblNumero);
        lblNumero.setBounds(310, 170, 20, 17);

        tfNCasa.setEditable(false);
        tfNCasa.setBackground(new java.awt.Color(153, 153, 153));
        tfNCasa.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        tfNCasa.setForeground(new java.awt.Color(255, 255, 255));
        pnlDadosInterno.add(tfNCasa);
        tfNCasa.setBounds(340, 170, 40, 23);

        lblBairro.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblBairro.setForeground(new java.awt.Color(255, 255, 255));
        lblBairro.setText("BAIRRO");
        lblBairro.setEnabled(false);
        pnlDadosInterno.add(lblBairro);
        lblBairro.setBounds(430, 170, 70, 17);

        tfBairro.setEditable(false);
        tfBairro.setBackground(new java.awt.Color(153, 153, 153));
        tfBairro.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        tfBairro.setForeground(new java.awt.Color(255, 255, 255));
        pnlDadosInterno.add(tfBairro);
        tfBairro.setBounds(510, 170, 150, 23);

        lblIdPaciente.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblIdPaciente.setForeground(new java.awt.Color(255, 255, 255));
        lblIdPaciente.setText("ID PACIENTE");
        pnlDadosInterno.add(lblIdPaciente);
        lblIdPaciente.setBounds(10, 340, 110, 17);
        lblIdPaciente.setVisible(false);

        tfIdPaciente.setEditable(false);
        tfIdPaciente.setBackground(new java.awt.Color(153, 153, 153));
        tfIdPaciente.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        tfIdPaciente.setForeground(new java.awt.Color(255, 255, 255));
        tfIdPaciente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfIdPacienteActionPerformed(evt);
            }
        });
        pnlDadosInterno.add(tfIdPaciente);
        tfIdPaciente.setBounds(110, 340, 100, 23);
        tfIdPaciente.setVisible(false);

        lblFundo1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Fundo Geral Vacina.png"))); // NOI18N
        pnlDadosInterno.add(lblFundo1);
        lblFundo1.setBounds(0, 0, 720, 450);

        javax.swing.GroupLayout pnlDadosLayout = new javax.swing.GroupLayout(pnlDados);
        pnlDados.setLayout(pnlDadosLayout);
        pnlDadosLayout.setHorizontalGroup(
            pnlDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlDadosInterno, javax.swing.GroupLayout.DEFAULT_SIZE, 714, Short.MAX_VALUE)
        );
        pnlDadosLayout.setVerticalGroup(
            pnlDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDadosLayout.createSequentialGroup()
                .addComponent(pnlDadosInterno, javax.swing.GroupLayout.PREFERRED_SIZE, 449, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pnlCartao.addTab("Cartão de Vacinação", pnlDados);

        pnlDuplaAdultoInterno.setLayout(null);

        tfVal3.setBackground(new java.awt.Color(153, 153, 153));
        tfVal3.setForeground(new java.awt.Color(255, 255, 255));
        try {
            tfVal3.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        tfVal3.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        pnlDuplaAdultoInterno.add(tfVal3);
        tfVal3.setBounds(400, 250, 80, 20);

        lblVal3.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblVal3.setForeground(new java.awt.Color(255, 255, 255));
        lblVal3.setText("Val:");
        pnlDuplaAdultoInterno.add(lblVal3);
        lblVal3.setBounds(350, 250, 50, 17);

        lblLote3.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblLote3.setForeground(new java.awt.Color(255, 255, 255));
        lblLote3.setText("Lote:");
        pnlDuplaAdultoInterno.add(lblLote3);
        lblLote3.setBounds(350, 210, 50, 17);

        tfLote3.setBackground(new java.awt.Color(153, 153, 153));
        tfLote3.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        tfLote3.setForeground(new java.awt.Color(255, 255, 255));
        pnlDuplaAdultoInterno.add(tfLote3);
        tfLote3.setBounds(400, 210, 80, 20);

        tfDose3.setBackground(new java.awt.Color(153, 153, 153));
        tfDose3.setForeground(new java.awt.Color(255, 255, 255));
        try {
            tfDose3.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        tfDose3.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        pnlDuplaAdultoInterno.add(tfDose3);
        tfDose3.setBounds(400, 160, 80, 23);

        lblDose3.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblDose3.setForeground(new java.awt.Color(255, 255, 255));
        lblDose3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDose3.setText("3º DOSE");
        pnlDuplaAdultoInterno.add(lblDose3);
        lblDose3.setBounds(400, 140, 80, 17);

        tfValReforco.setBackground(new java.awt.Color(153, 153, 153));
        tfValReforco.setForeground(new java.awt.Color(255, 255, 255));
        try {
            tfValReforco.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        tfValReforco.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        pnlDuplaAdultoInterno.add(tfValReforco);
        tfValReforco.setBounds(560, 250, 80, 20);

        lblValReforco.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblValReforco.setForeground(new java.awt.Color(255, 255, 255));
        lblValReforco.setText("Val:");
        pnlDuplaAdultoInterno.add(lblValReforco);
        lblValReforco.setBounds(510, 250, 50, 17);

        lblLoteReforco.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblLoteReforco.setForeground(new java.awt.Color(255, 255, 255));
        lblLoteReforco.setText("Lote:");
        pnlDuplaAdultoInterno.add(lblLoteReforco);
        lblLoteReforco.setBounds(510, 210, 50, 17);

        lblTitulo.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        lblTitulo.setForeground(new java.awt.Color(255, 255, 255));
        lblTitulo.setText("DUPLA ADULTO (CONTRA TÉTANO E DIFTERIA)");
        pnlDuplaAdultoInterno.add(lblTitulo);
        lblTitulo.setBounds(30, 70, 440, 22);

        tfLoteReforco.setBackground(new java.awt.Color(153, 153, 153));
        tfLoteReforco.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        tfLoteReforco.setForeground(new java.awt.Color(255, 255, 255));
        pnlDuplaAdultoInterno.add(tfLoteReforco);
        tfLoteReforco.setBounds(560, 210, 80, 20);

        lblDose.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblDose.setForeground(new java.awt.Color(255, 255, 255));
        lblDose.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDose.setText("1º DOSE");
        pnlDuplaAdultoInterno.add(lblDose);
        lblDose.setBounds(90, 140, 80, 17);

        tfDose1.setBackground(new java.awt.Color(153, 153, 153));
        tfDose1.setForeground(new java.awt.Color(255, 255, 255));
        try {
            tfDose1.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        tfDose1.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        pnlDuplaAdultoInterno.add(tfDose1);
        tfDose1.setBounds(90, 160, 80, 23);

        tfReforco.setBackground(new java.awt.Color(153, 153, 153));
        tfReforco.setForeground(new java.awt.Color(255, 255, 255));
        try {
            tfReforco.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        tfReforco.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        pnlDuplaAdultoInterno.add(tfReforco);
        tfReforco.setBounds(560, 160, 80, 23);

        lblReforco.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblReforco.setForeground(new java.awt.Color(255, 255, 255));
        lblReforco.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblReforco.setText("REFORÇO");
        pnlDuplaAdultoInterno.add(lblReforco);
        lblReforco.setBounds(560, 140, 80, 17);

        lblLote.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblLote.setForeground(new java.awt.Color(255, 255, 255));
        lblLote.setText("Lote:");
        pnlDuplaAdultoInterno.add(lblLote);
        lblLote.setBounds(40, 210, 50, 17);

        btnSalvar.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btnSalvar.setForeground(new java.awt.Color(255, 255, 255));
        btnSalvar.setText("SALVAR");
        btnSalvar.setBorderPainted(false);
        btnSalvar.setContentAreaFilled(false);
        btnSalvar.setFocusPainted(false);
        btnSalvar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnSalvarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnSalvarMouseExited(evt);
            }
        });
        btnSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarActionPerformed(evt);
            }
        });
        pnlDuplaAdultoInterno.add(btnSalvar);
        btnSalvar.setBounds(590, 360, 120, 30);

        tfLote.setBackground(new java.awt.Color(153, 153, 153));
        tfLote.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        tfLote.setForeground(new java.awt.Color(255, 255, 255));
        pnlDuplaAdultoInterno.add(tfLote);
        tfLote.setBounds(90, 210, 80, 20);

        lblVal.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblVal.setForeground(new java.awt.Color(255, 255, 255));
        lblVal.setText("Val:");
        pnlDuplaAdultoInterno.add(lblVal);
        lblVal.setBounds(40, 250, 50, 17);

        tfVal1.setBackground(new java.awt.Color(153, 153, 153));
        tfVal1.setForeground(new java.awt.Color(255, 255, 255));
        try {
            tfVal1.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        tfVal1.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        pnlDuplaAdultoInterno.add(tfVal1);
        tfVal1.setBounds(90, 250, 80, 20);

        tfVal2.setBackground(new java.awt.Color(153, 153, 153));
        tfVal2.setForeground(new java.awt.Color(255, 255, 255));
        try {
            tfVal2.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        tfVal2.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        pnlDuplaAdultoInterno.add(tfVal2);
        tfVal2.setBounds(240, 250, 80, 20);

        lblVal2.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblVal2.setForeground(new java.awt.Color(255, 255, 255));
        lblVal2.setText("Val:");
        pnlDuplaAdultoInterno.add(lblVal2);
        lblVal2.setBounds(190, 250, 50, 17);

        lblLote2.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblLote2.setForeground(new java.awt.Color(255, 255, 255));
        lblLote2.setText("Lote:");
        pnlDuplaAdultoInterno.add(lblLote2);
        lblLote2.setBounds(190, 210, 50, 17);

        tfLote2.setBackground(new java.awt.Color(153, 153, 153));
        tfLote2.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        tfLote2.setForeground(new java.awt.Color(255, 255, 255));
        pnlDuplaAdultoInterno.add(tfLote2);
        tfLote2.setBounds(240, 210, 80, 20);

        tfDose2.setBackground(new java.awt.Color(153, 153, 153));
        tfDose2.setForeground(new java.awt.Color(255, 255, 255));
        try {
            tfDose2.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        tfDose2.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        pnlDuplaAdultoInterno.add(tfDose2);
        tfDose2.setBounds(240, 160, 80, 23);

        lblDose2.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblDose2.setForeground(new java.awt.Color(255, 255, 255));
        lblDose2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDose2.setText("2º DOSE");
        pnlDuplaAdultoInterno.add(lblDose2);
        lblDose2.setBounds(240, 140, 80, 17);

        lblFundo2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Fundo Geral Vacina.png"))); // NOI18N
        pnlDuplaAdultoInterno.add(lblFundo2);
        lblFundo2.setBounds(0, 0, 720, 449);

        javax.swing.GroupLayout pnlDuplaAdultaLayout = new javax.swing.GroupLayout(pnlDuplaAdulta);
        pnlDuplaAdulta.setLayout(pnlDuplaAdultaLayout);
        pnlDuplaAdultaLayout.setHorizontalGroup(
            pnlDuplaAdultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlDuplaAdultoInterno, javax.swing.GroupLayout.DEFAULT_SIZE, 714, Short.MAX_VALUE)
        );
        pnlDuplaAdultaLayout.setVerticalGroup(
            pnlDuplaAdultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDuplaAdultaLayout.createSequentialGroup()
                .addComponent(pnlDuplaAdultoInterno, javax.swing.GroupLayout.PREFERRED_SIZE, 448, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 1, Short.MAX_VALUE))
        );

        pnlCartao.addTab("Dupla Adulto ", pnlDuplaAdulta);

        pnlInfluenzaInterno.setLayout(null);

        lblLoteInfluenza1.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblLoteInfluenza1.setForeground(new java.awt.Color(255, 255, 255));
        lblLoteInfluenza1.setText("Lote:");
        pnlInfluenzaInterno.add(lblLoteInfluenza1);
        lblLoteInfluenza1.setBounds(190, 140, 50, 17);

        tfInfluenza7.setBackground(new java.awt.Color(153, 153, 153));
        tfInfluenza7.setForeground(new java.awt.Color(255, 255, 255));
        try {
            tfInfluenza7.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        tfInfluenza7.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        pnlInfluenzaInterno.add(tfInfluenza7);
        tfInfluenza7.setBounds(400, 240, 80, 23);

        lblValInfluenza1.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblValInfluenza1.setForeground(new java.awt.Color(255, 255, 255));
        lblValInfluenza1.setText("Val:");
        pnlInfluenzaInterno.add(lblValInfluenza1);
        lblValInfluenza1.setBounds(190, 180, 50, 17);

        tfLoteInfluenza7.setBackground(new java.awt.Color(153, 153, 153));
        tfLoteInfluenza7.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        tfLoteInfluenza7.setForeground(new java.awt.Color(255, 255, 255));
        pnlInfluenzaInterno.add(tfLoteInfluenza7);
        tfLoteInfluenza7.setBounds(400, 280, 80, 23);

        tfInfluenza3.setBackground(new java.awt.Color(153, 153, 153));
        tfInfluenza3.setForeground(new java.awt.Color(255, 255, 255));
        try {
            tfInfluenza3.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        tfInfluenza3.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        pnlInfluenzaInterno.add(tfInfluenza3);
        tfInfluenza3.setBounds(400, 100, 80, 23);

        tfLoteInfluenza3.setBackground(new java.awt.Color(153, 153, 153));
        tfLoteInfluenza3.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        tfLoteInfluenza3.setForeground(new java.awt.Color(255, 255, 255));
        tfLoteInfluenza3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfLoteInfluenza3ActionPerformed(evt);
            }
        });
        pnlInfluenzaInterno.add(tfLoteInfluenza3);
        tfLoteInfluenza3.setBounds(400, 140, 80, 23);

        tfValInfluenza7.setBackground(new java.awt.Color(153, 153, 153));
        tfValInfluenza7.setForeground(new java.awt.Color(255, 255, 255));
        try {
            tfValInfluenza7.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        tfValInfluenza7.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        pnlInfluenzaInterno.add(tfValInfluenza7);
        tfValInfluenza7.setBounds(400, 320, 80, 23);

        lblLoteInfluenza6.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblLoteInfluenza6.setForeground(new java.awt.Color(255, 255, 255));
        lblLoteInfluenza6.setText("Lote:");
        pnlInfluenzaInterno.add(lblLoteInfluenza6);
        lblLoteInfluenza6.setBounds(350, 280, 50, 17);

        tfValInfluenza3.setBackground(new java.awt.Color(153, 153, 153));
        tfValInfluenza3.setForeground(new java.awt.Color(255, 255, 255));
        try {
            tfValInfluenza3.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        tfValInfluenza3.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        pnlInfluenzaInterno.add(tfValInfluenza3);
        tfValInfluenza3.setBounds(400, 180, 80, 23);

        lblValInfluenza6.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblValInfluenza6.setForeground(new java.awt.Color(255, 255, 255));
        lblValInfluenza6.setText("Val:");
        pnlInfluenzaInterno.add(lblValInfluenza6);
        lblValInfluenza6.setBounds(350, 320, 50, 17);

        lblLoteInfluenza2.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblLoteInfluenza2.setForeground(new java.awt.Color(255, 255, 255));
        lblLoteInfluenza2.setText("Lote:");
        pnlInfluenzaInterno.add(lblLoteInfluenza2);
        lblLoteInfluenza2.setBounds(350, 140, 50, 17);

        tfInfluenza8.setBackground(new java.awt.Color(153, 153, 153));
        tfInfluenza8.setForeground(new java.awt.Color(255, 255, 255));
        try {
            tfInfluenza8.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        tfInfluenza8.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        pnlInfluenzaInterno.add(tfInfluenza8);
        tfInfluenza8.setBounds(550, 240, 80, 23);

        lblValInfluenza2.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblValInfluenza2.setForeground(new java.awt.Color(255, 255, 255));
        lblValInfluenza2.setText("Val:");
        pnlInfluenzaInterno.add(lblValInfluenza2);
        lblValInfluenza2.setBounds(350, 180, 50, 17);

        lblLoteInfluenza7.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblLoteInfluenza7.setForeground(new java.awt.Color(255, 255, 255));
        lblLoteInfluenza7.setText("Lote:");
        pnlInfluenzaInterno.add(lblLoteInfluenza7);
        lblLoteInfluenza7.setBounds(500, 280, 50, 17);

        tfInfluenza4.setBackground(new java.awt.Color(153, 153, 153));
        tfInfluenza4.setForeground(new java.awt.Color(255, 255, 255));
        try {
            tfInfluenza4.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        tfInfluenza4.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        pnlInfluenzaInterno.add(tfInfluenza4);
        tfInfluenza4.setBounds(550, 100, 80, 23);

        tfLoteInfluenza8.setBackground(new java.awt.Color(153, 153, 153));
        tfLoteInfluenza8.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        tfLoteInfluenza8.setForeground(new java.awt.Color(255, 255, 255));
        pnlInfluenzaInterno.add(tfLoteInfluenza8);
        tfLoteInfluenza8.setBounds(550, 280, 80, 23);

        tfLoteInfluenza4.setBackground(new java.awt.Color(153, 153, 153));
        tfLoteInfluenza4.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        tfLoteInfluenza4.setForeground(new java.awt.Color(255, 255, 255));
        pnlInfluenzaInterno.add(tfLoteInfluenza4);
        tfLoteInfluenza4.setBounds(550, 140, 80, 23);

        lblValInfluenza7.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblValInfluenza7.setForeground(new java.awt.Color(255, 255, 255));
        lblValInfluenza7.setText("Val:");
        pnlInfluenzaInterno.add(lblValInfluenza7);
        lblValInfluenza7.setBounds(500, 320, 50, 17);

        tfValInfluenza4.setBackground(new java.awt.Color(153, 153, 153));
        tfValInfluenza4.setForeground(new java.awt.Color(255, 255, 255));
        try {
            tfValInfluenza4.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        tfValInfluenza4.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        pnlInfluenzaInterno.add(tfValInfluenza4);
        tfValInfluenza4.setBounds(550, 180, 80, 23);

        tfValInfluenza8.setBackground(new java.awt.Color(153, 153, 153));
        tfValInfluenza8.setForeground(new java.awt.Color(255, 255, 255));
        try {
            tfValInfluenza8.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        tfValInfluenza8.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        pnlInfluenzaInterno.add(tfValInfluenza8);
        tfValInfluenza8.setBounds(550, 320, 80, 23);

        lblLoteInfluenza3.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblLoteInfluenza3.setForeground(new java.awt.Color(255, 255, 255));
        lblLoteInfluenza3.setText("Lote:");
        pnlInfluenzaInterno.add(lblLoteInfluenza3);
        lblLoteInfluenza3.setBounds(500, 140, 50, 17);

        btnSalvarInfluenza.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btnSalvarInfluenza.setForeground(new java.awt.Color(255, 255, 255));
        btnSalvarInfluenza.setText("SALVAR");
        btnSalvarInfluenza.setBorderPainted(false);
        btnSalvarInfluenza.setContentAreaFilled(false);
        btnSalvarInfluenza.setFocusPainted(false);
        btnSalvarInfluenza.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnSalvarInfluenzaMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnSalvarInfluenzaMouseExited(evt);
            }
        });
        btnSalvarInfluenza.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarInfluenzaActionPerformed(evt);
            }
        });
        pnlInfluenzaInterno.add(btnSalvarInfluenza);
        btnSalvarInfluenza.setBounds(590, 360, 120, 30);

        lblValInfluenza3.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblValInfluenza3.setForeground(new java.awt.Color(255, 255, 255));
        lblValInfluenza3.setText("Val:");
        pnlInfluenzaInterno.add(lblValInfluenza3);
        lblValInfluenza3.setBounds(500, 180, 50, 17);

        tfInfluenza5.setBackground(new java.awt.Color(153, 153, 153));
        tfInfluenza5.setForeground(new java.awt.Color(255, 255, 255));
        try {
            tfInfluenza5.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        tfInfluenza5.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        pnlInfluenzaInterno.add(tfInfluenza5);
        tfInfluenza5.setBounds(90, 240, 80, 23);

        lblInfluenza.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        lblInfluenza.setForeground(new java.awt.Color(255, 255, 255));
        lblInfluenza.setText("INFLUENZA (CONTRA GRIPE)");
        pnlInfluenzaInterno.add(lblInfluenza);
        lblInfluenza.setBounds(30, 70, 310, 22);

        tfLoteInfluenza5.setBackground(new java.awt.Color(153, 153, 153));
        tfLoteInfluenza5.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        tfLoteInfluenza5.setForeground(new java.awt.Color(255, 255, 255));
        pnlInfluenzaInterno.add(tfLoteInfluenza5);
        tfLoteInfluenza5.setBounds(90, 280, 80, 23);

        tfInfluenza1.setBackground(new java.awt.Color(153, 153, 153));
        tfInfluenza1.setForeground(new java.awt.Color(255, 255, 255));
        try {
            tfInfluenza1.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        tfInfluenza1.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        pnlInfluenzaInterno.add(tfInfluenza1);
        tfInfluenza1.setBounds(90, 100, 80, 23);

        lblLoteInfluenza.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblLoteInfluenza.setForeground(new java.awt.Color(255, 255, 255));
        lblLoteInfluenza.setText("Lote:");
        pnlInfluenzaInterno.add(lblLoteInfluenza);
        lblLoteInfluenza.setBounds(40, 140, 50, 17);

        tfValInfluenza5.setBackground(new java.awt.Color(153, 153, 153));
        tfValInfluenza5.setForeground(new java.awt.Color(255, 255, 255));
        try {
            tfValInfluenza5.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        tfValInfluenza5.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        pnlInfluenzaInterno.add(tfValInfluenza5);
        tfValInfluenza5.setBounds(90, 320, 80, 23);

        lblLoteInfluenza4.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblLoteInfluenza4.setForeground(new java.awt.Color(255, 255, 255));
        lblLoteInfluenza4.setText("Lote:");
        pnlInfluenzaInterno.add(lblLoteInfluenza4);
        lblLoteInfluenza4.setBounds(40, 280, 50, 17);

        tfLoteInfluenza1.setBackground(new java.awt.Color(153, 153, 153));
        tfLoteInfluenza1.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        tfLoteInfluenza1.setForeground(new java.awt.Color(255, 255, 255));
        pnlInfluenzaInterno.add(tfLoteInfluenza1);
        tfLoteInfluenza1.setBounds(90, 140, 80, 23);

        lblValInfluenza4.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblValInfluenza4.setForeground(new java.awt.Color(255, 255, 255));
        lblValInfluenza4.setText("Val:");
        pnlInfluenzaInterno.add(lblValInfluenza4);
        lblValInfluenza4.setBounds(40, 320, 50, 17);

        lblValInfluenza.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblValInfluenza.setForeground(new java.awt.Color(255, 255, 255));
        lblValInfluenza.setText("Val:");
        pnlInfluenzaInterno.add(lblValInfluenza);
        lblValInfluenza.setBounds(40, 180, 50, 17);

        tfInfluenza6.setBackground(new java.awt.Color(153, 153, 153));
        tfInfluenza6.setForeground(new java.awt.Color(255, 255, 255));
        try {
            tfInfluenza6.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        tfInfluenza6.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        pnlInfluenzaInterno.add(tfInfluenza6);
        tfInfluenza6.setBounds(240, 240, 80, 23);

        tfValInfluenza1.setBackground(new java.awt.Color(153, 153, 153));
        tfValInfluenza1.setForeground(new java.awt.Color(255, 255, 255));
        try {
            tfValInfluenza1.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        tfValInfluenza1.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        pnlInfluenzaInterno.add(tfValInfluenza1);
        tfValInfluenza1.setBounds(90, 180, 80, 23);

        tfLoteInfluenza6.setBackground(new java.awt.Color(153, 153, 153));
        tfLoteInfluenza6.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        tfLoteInfluenza6.setForeground(new java.awt.Color(255, 255, 255));
        pnlInfluenzaInterno.add(tfLoteInfluenza6);
        tfLoteInfluenza6.setBounds(240, 280, 80, 23);

        tfInfluenza2.setBackground(new java.awt.Color(153, 153, 153));
        tfInfluenza2.setForeground(new java.awt.Color(255, 255, 255));
        try {
            tfInfluenza2.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        tfInfluenza2.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        pnlInfluenzaInterno.add(tfInfluenza2);
        tfInfluenza2.setBounds(240, 100, 80, 23);

        tfValInfluenza6.setBackground(new java.awt.Color(153, 153, 153));
        tfValInfluenza6.setForeground(new java.awt.Color(255, 255, 255));
        try {
            tfValInfluenza6.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        tfValInfluenza6.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        pnlInfluenzaInterno.add(tfValInfluenza6);
        tfValInfluenza6.setBounds(240, 320, 80, 23);

        tfLoteInfluenza2.setBackground(new java.awt.Color(153, 153, 153));
        tfLoteInfluenza2.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        tfLoteInfluenza2.setForeground(new java.awt.Color(255, 255, 255));
        pnlInfluenzaInterno.add(tfLoteInfluenza2);
        tfLoteInfluenza2.setBounds(240, 140, 80, 23);

        lblLoteInfluenza5.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblLoteInfluenza5.setForeground(new java.awt.Color(255, 255, 255));
        lblLoteInfluenza5.setText("Lote:");
        pnlInfluenzaInterno.add(lblLoteInfluenza5);
        lblLoteInfluenza5.setBounds(190, 280, 50, 17);

        tfValInfluenza2.setBackground(new java.awt.Color(153, 153, 153));
        tfValInfluenza2.setForeground(new java.awt.Color(255, 255, 255));
        try {
            tfValInfluenza2.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        tfValInfluenza2.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        pnlInfluenzaInterno.add(tfValInfluenza2);
        tfValInfluenza2.setBounds(240, 180, 80, 23);

        lblValInfluenza5.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblValInfluenza5.setForeground(new java.awt.Color(255, 255, 255));
        lblValInfluenza5.setText("Val:");
        pnlInfluenzaInterno.add(lblValInfluenza5);
        lblValInfluenza5.setBounds(190, 320, 50, 17);

        lblFundo3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Fundo Geral Vacina.png"))); // NOI18N
        pnlInfluenzaInterno.add(lblFundo3);
        lblFundo3.setBounds(0, 0, 720, 449);

        javax.swing.GroupLayout pnlInfluenzaLayout = new javax.swing.GroupLayout(pnlInfluenza);
        pnlInfluenza.setLayout(pnlInfluenzaLayout);
        pnlInfluenzaLayout.setHorizontalGroup(
            pnlInfluenzaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlInfluenzaInterno, javax.swing.GroupLayout.DEFAULT_SIZE, 714, Short.MAX_VALUE)
        );
        pnlInfluenzaLayout.setVerticalGroup(
            pnlInfluenzaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlInfluenzaLayout.createSequentialGroup()
                .addComponent(pnlInfluenzaInterno, javax.swing.GroupLayout.PREFERRED_SIZE, 448, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 1, Short.MAX_VALUE))
        );

        pnlCartao.addTab("Influenza", pnlInfluenza);

        pnlFebreAmarelaInterno.setLayout(null);

        lblValHepatite5.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblValHepatite5.setForeground(new java.awt.Color(255, 255, 255));
        lblValHepatite5.setText("Val:");
        pnlFebreAmarelaInterno.add(lblValHepatite5);
        lblValHepatite5.setBounds(190, 320, 50, 17);

        tfValFebre6.setBackground(new java.awt.Color(153, 153, 153));
        tfValFebre6.setForeground(new java.awt.Color(255, 255, 255));
        try {
            tfValFebre6.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        tfValFebre6.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        pnlFebreAmarelaInterno.add(tfValFebre6);
        tfValFebre6.setBounds(240, 320, 80, 23);

        lblValHepatite1.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblValHepatite1.setForeground(new java.awt.Color(255, 255, 255));
        lblValHepatite1.setText("Val:");
        pnlFebreAmarelaInterno.add(lblValHepatite1);
        lblValHepatite1.setBounds(190, 180, 50, 17);

        tfFebre7.setBackground(new java.awt.Color(153, 153, 153));
        tfFebre7.setForeground(new java.awt.Color(255, 255, 255));
        try {
            tfFebre7.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        tfFebre7.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        pnlFebreAmarelaInterno.add(tfFebre7);
        tfFebre7.setBounds(400, 240, 80, 23);

        tfValFebre2.setBackground(new java.awt.Color(153, 153, 153));
        tfValFebre2.setForeground(new java.awt.Color(255, 255, 255));
        try {
            tfValFebre2.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        tfValFebre2.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        pnlFebreAmarelaInterno.add(tfValFebre2);
        tfValFebre2.setBounds(240, 180, 80, 23);

        lblLoteHepatite6.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblLoteHepatite6.setForeground(new java.awt.Color(255, 255, 255));
        lblLoteHepatite6.setText("Lote:");
        pnlFebreAmarelaInterno.add(lblLoteHepatite6);
        lblLoteHepatite6.setBounds(350, 280, 50, 17);

        tfFebre3.setBackground(new java.awt.Color(153, 153, 153));
        tfFebre3.setForeground(new java.awt.Color(255, 255, 255));
        try {
            tfFebre3.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        tfFebre3.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        pnlFebreAmarelaInterno.add(tfFebre3);
        tfFebre3.setBounds(400, 100, 80, 23);

        tfLoteFebre7.setBackground(new java.awt.Color(153, 153, 153));
        tfLoteFebre7.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        tfLoteFebre7.setForeground(new java.awt.Color(255, 255, 255));
        pnlFebreAmarelaInterno.add(tfLoteFebre7);
        tfLoteFebre7.setBounds(400, 280, 80, 23);

        lblLoteHepatite2.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblLoteHepatite2.setForeground(new java.awt.Color(255, 255, 255));
        lblLoteHepatite2.setText("Lote:");
        pnlFebreAmarelaInterno.add(lblLoteHepatite2);
        lblLoteHepatite2.setBounds(350, 140, 50, 17);

        lblValHepatite6.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblValHepatite6.setForeground(new java.awt.Color(255, 255, 255));
        lblValHepatite6.setText("Val:");
        pnlFebreAmarelaInterno.add(lblValHepatite6);
        lblValHepatite6.setBounds(350, 320, 50, 17);

        tfLoteFebre3.setBackground(new java.awt.Color(153, 153, 153));
        tfLoteFebre3.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        tfLoteFebre3.setForeground(new java.awt.Color(255, 255, 255));
        pnlFebreAmarelaInterno.add(tfLoteFebre3);
        tfLoteFebre3.setBounds(400, 140, 80, 23);

        tfValFebre7.setBackground(new java.awt.Color(153, 153, 153));
        tfValFebre7.setForeground(new java.awt.Color(255, 255, 255));
        try {
            tfValFebre7.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        tfValFebre7.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        pnlFebreAmarelaInterno.add(tfValFebre7);
        tfValFebre7.setBounds(400, 320, 80, 23);

        lblValHepatite2.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblValHepatite2.setForeground(new java.awt.Color(255, 255, 255));
        lblValHepatite2.setText("Val:");
        pnlFebreAmarelaInterno.add(lblValHepatite2);
        lblValHepatite2.setBounds(350, 180, 50, 17);

        tfFebre8.setBackground(new java.awt.Color(153, 153, 153));
        tfFebre8.setForeground(new java.awt.Color(255, 255, 255));
        try {
            tfFebre8.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        tfFebre8.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        pnlFebreAmarelaInterno.add(tfFebre8);
        tfFebre8.setBounds(550, 240, 80, 23);

        tfValFebre3.setBackground(new java.awt.Color(153, 153, 153));
        tfValFebre3.setForeground(new java.awt.Color(255, 255, 255));
        try {
            tfValFebre3.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        tfValFebre3.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        pnlFebreAmarelaInterno.add(tfValFebre3);
        tfValFebre3.setBounds(400, 180, 80, 23);

        lblLoteHepatite7.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblLoteHepatite7.setForeground(new java.awt.Color(255, 255, 255));
        lblLoteHepatite7.setText("Lote:");
        pnlFebreAmarelaInterno.add(lblLoteHepatite7);
        lblLoteHepatite7.setBounds(500, 280, 50, 17);

        tfFebre4.setBackground(new java.awt.Color(153, 153, 153));
        tfFebre4.setForeground(new java.awt.Color(255, 255, 255));
        try {
            tfFebre4.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        tfFebre4.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        pnlFebreAmarelaInterno.add(tfFebre4);
        tfFebre4.setBounds(550, 100, 80, 23);

        tfLoteFebre8.setBackground(new java.awt.Color(153, 153, 153));
        tfLoteFebre8.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        tfLoteFebre8.setForeground(new java.awt.Color(255, 255, 255));
        pnlFebreAmarelaInterno.add(tfLoteFebre8);
        tfLoteFebre8.setBounds(550, 280, 80, 23);

        lblLoteHepatite3.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblLoteHepatite3.setForeground(new java.awt.Color(255, 255, 255));
        lblLoteHepatite3.setText("Lote:");
        pnlFebreAmarelaInterno.add(lblLoteHepatite3);
        lblLoteHepatite3.setBounds(500, 140, 50, 17);

        tfLoteFebre4.setBackground(new java.awt.Color(153, 153, 153));
        tfLoteFebre4.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        tfLoteFebre4.setForeground(new java.awt.Color(255, 255, 255));
        pnlFebreAmarelaInterno.add(tfLoteFebre4);
        tfLoteFebre4.setBounds(550, 140, 80, 23);

        lblValHepatite7.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblValHepatite7.setForeground(new java.awt.Color(255, 255, 255));
        lblValHepatite7.setText("Val:");
        pnlFebreAmarelaInterno.add(lblValHepatite7);
        lblValHepatite7.setBounds(500, 320, 50, 17);

        tfValFebre8.setBackground(new java.awt.Color(153, 153, 153));
        tfValFebre8.setForeground(new java.awt.Color(255, 255, 255));
        try {
            tfValFebre8.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        tfValFebre8.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        pnlFebreAmarelaInterno.add(tfValFebre8);
        tfValFebre8.setBounds(550, 320, 80, 23);

        lblValHepatite3.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblValHepatite3.setForeground(new java.awt.Color(255, 255, 255));
        lblValHepatite3.setText("Val:");
        pnlFebreAmarelaInterno.add(lblValHepatite3);
        lblValHepatite3.setBounds(500, 180, 50, 17);

        btnSalvarFebre.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btnSalvarFebre.setForeground(new java.awt.Color(255, 255, 255));
        btnSalvarFebre.setText("SALVAR");
        btnSalvarFebre.setBorderPainted(false);
        btnSalvarFebre.setContentAreaFilled(false);
        btnSalvarFebre.setFocusPainted(false);
        btnSalvarFebre.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnSalvarFebreMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnSalvarFebreMouseExited(evt);
            }
        });
        btnSalvarFebre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarFebreActionPerformed(evt);
            }
        });
        pnlFebreAmarelaInterno.add(btnSalvarFebre);
        btnSalvarFebre.setBounds(590, 360, 120, 31);

        tfValFebre4.setBackground(new java.awt.Color(153, 153, 153));
        tfValFebre4.setForeground(new java.awt.Color(255, 255, 255));
        try {
            tfValFebre4.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        tfValFebre4.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        pnlFebreAmarelaInterno.add(tfValFebre4);
        tfValFebre4.setBounds(550, 180, 80, 23);

        tfFebre5.setBackground(new java.awt.Color(153, 153, 153));
        tfFebre5.setForeground(new java.awt.Color(255, 255, 255));
        try {
            tfFebre5.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        tfFebre5.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        pnlFebreAmarelaInterno.add(tfFebre5);
        tfFebre5.setBounds(90, 240, 80, 23);

        lblFebreAmarela.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        lblFebreAmarela.setForeground(new java.awt.Color(255, 255, 255));
        lblFebreAmarela.setText("FEBRE AMARELA");
        pnlFebreAmarelaInterno.add(lblFebreAmarela);
        lblFebreAmarela.setBounds(30, 70, 270, 22);

        lblLoteHepatite4.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblLoteHepatite4.setForeground(new java.awt.Color(255, 255, 255));
        lblLoteHepatite4.setText("Lote:");
        pnlFebreAmarelaInterno.add(lblLoteHepatite4);
        lblLoteHepatite4.setBounds(40, 280, 50, 17);

        tfFebre1.setBackground(new java.awt.Color(153, 153, 153));
        tfFebre1.setForeground(new java.awt.Color(255, 255, 255));
        try {
            tfFebre1.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        tfFebre1.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        pnlFebreAmarelaInterno.add(tfFebre1);
        tfFebre1.setBounds(90, 100, 80, 23);

        tfLoteFebre5.setBackground(new java.awt.Color(153, 153, 153));
        tfLoteFebre5.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        tfLoteFebre5.setForeground(new java.awt.Color(255, 255, 255));
        pnlFebreAmarelaInterno.add(tfLoteFebre5);
        tfLoteFebre5.setBounds(90, 280, 80, 23);

        lblLoteHepatite.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblLoteHepatite.setForeground(new java.awt.Color(255, 255, 255));
        lblLoteHepatite.setText("Lote:");
        pnlFebreAmarelaInterno.add(lblLoteHepatite);
        lblLoteHepatite.setBounds(40, 140, 50, 17);

        lblValHepatite4.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblValHepatite4.setForeground(new java.awt.Color(255, 255, 255));
        lblValHepatite4.setText("Val:");
        pnlFebreAmarelaInterno.add(lblValHepatite4);
        lblValHepatite4.setBounds(40, 320, 50, 17);

        tfLoteFebre1.setBackground(new java.awt.Color(153, 153, 153));
        tfLoteFebre1.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        tfLoteFebre1.setForeground(new java.awt.Color(255, 255, 255));
        pnlFebreAmarelaInterno.add(tfLoteFebre1);
        tfLoteFebre1.setBounds(90, 140, 80, 23);

        tfValFebre5.setBackground(new java.awt.Color(153, 153, 153));
        tfValFebre5.setForeground(new java.awt.Color(255, 255, 255));
        try {
            tfValFebre5.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        tfValFebre5.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        pnlFebreAmarelaInterno.add(tfValFebre5);
        tfValFebre5.setBounds(90, 320, 80, 23);

        lblValHepatite.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblValHepatite.setForeground(new java.awt.Color(255, 255, 255));
        lblValHepatite.setText("Val:");
        pnlFebreAmarelaInterno.add(lblValHepatite);
        lblValHepatite.setBounds(40, 180, 50, 17);

        tfFebre6.setBackground(new java.awt.Color(153, 153, 153));
        tfFebre6.setForeground(new java.awt.Color(255, 255, 255));
        try {
            tfFebre6.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        tfFebre6.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        pnlFebreAmarelaInterno.add(tfFebre6);
        tfFebre6.setBounds(240, 240, 80, 23);

        tfValFebre1.setBackground(new java.awt.Color(153, 153, 153));
        tfValFebre1.setForeground(new java.awt.Color(255, 255, 255));
        try {
            tfValFebre1.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        tfValFebre1.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        pnlFebreAmarelaInterno.add(tfValFebre1);
        tfValFebre1.setBounds(90, 180, 80, 23);

        lblLoteHepatite5.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblLoteHepatite5.setForeground(new java.awt.Color(255, 255, 255));
        lblLoteHepatite5.setText("Lote:");
        pnlFebreAmarelaInterno.add(lblLoteHepatite5);
        lblLoteHepatite5.setBounds(190, 280, 50, 17);

        tfFebre2.setBackground(new java.awt.Color(153, 153, 153));
        tfFebre2.setForeground(new java.awt.Color(255, 255, 255));
        try {
            tfFebre2.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        tfFebre2.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        pnlFebreAmarelaInterno.add(tfFebre2);
        tfFebre2.setBounds(240, 100, 80, 23);

        tfLoteFebre6.setBackground(new java.awt.Color(153, 153, 153));
        tfLoteFebre6.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        tfLoteFebre6.setForeground(new java.awt.Color(255, 255, 255));
        pnlFebreAmarelaInterno.add(tfLoteFebre6);
        tfLoteFebre6.setBounds(240, 280, 80, 23);

        lblLoteHepatite1.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblLoteHepatite1.setForeground(new java.awt.Color(255, 255, 255));
        lblLoteHepatite1.setText("Lote:");
        pnlFebreAmarelaInterno.add(lblLoteHepatite1);
        lblLoteHepatite1.setBounds(190, 140, 50, 17);

        tfLoteFebre2.setBackground(new java.awt.Color(153, 153, 153));
        tfLoteFebre2.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        tfLoteFebre2.setForeground(new java.awt.Color(255, 255, 255));
        pnlFebreAmarelaInterno.add(tfLoteFebre2);
        tfLoteFebre2.setBounds(240, 140, 80, 23);

        lblFundo4.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblFundo4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Fundo Geral Vacina.png"))); // NOI18N
        pnlFebreAmarelaInterno.add(lblFundo4);
        lblFundo4.setBounds(0, 0, 720, 449);

        javax.swing.GroupLayout pnlFebreLayout = new javax.swing.GroupLayout(pnlFebre);
        pnlFebre.setLayout(pnlFebreLayout);
        pnlFebreLayout.setHorizontalGroup(
            pnlFebreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlFebreAmarelaInterno, javax.swing.GroupLayout.DEFAULT_SIZE, 714, Short.MAX_VALUE)
        );
        pnlFebreLayout.setVerticalGroup(
            pnlFebreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFebreLayout.createSequentialGroup()
                .addComponent(pnlFebreAmarelaInterno, javax.swing.GroupLayout.PREFERRED_SIZE, 448, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 1, Short.MAX_VALUE))
        );

        pnlCartao.addTab("Febre Amarela", pnlFebre);

        pnlFebreInterno.setLayout(null);

        lblLoteRevacinacao.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblLoteRevacinacao.setForeground(new java.awt.Color(255, 255, 255));
        lblLoteRevacinacao.setText("Lote:");
        pnlFebreInterno.add(lblLoteRevacinacao);
        lblLoteRevacinacao.setBounds(190, 210, 50, 17);

        lblDupla1.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblDupla1.setForeground(new java.awt.Color(255, 255, 255));
        lblDupla1.setText("1º DOSE");
        pnlFebreInterno.add(lblDupla1);
        lblDupla1.setBounds(110, 150, 53, 17);

        lblValRevacinacao.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblValRevacinacao.setForeground(new java.awt.Color(255, 255, 255));
        lblValRevacinacao.setText("Val:");
        pnlFebreInterno.add(lblValRevacinacao);
        lblValRevacinacao.setBounds(190, 250, 50, 17);

        tfDuplaViral1.setBackground(new java.awt.Color(153, 153, 153));
        tfDuplaViral1.setForeground(new java.awt.Color(255, 255, 255));
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
        pnlFebreInterno.add(tfDuplaViral1);
        tfDuplaViral1.setBounds(100, 170, 80, 23);

        btnSalvarDuplaViral.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btnSalvarDuplaViral.setForeground(new java.awt.Color(255, 255, 255));
        btnSalvarDuplaViral.setText("SALVAR");
        btnSalvarDuplaViral.setBorderPainted(false);
        btnSalvarDuplaViral.setContentAreaFilled(false);
        btnSalvarDuplaViral.setFocusPainted(false);
        btnSalvarDuplaViral.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnSalvarDuplaViralMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnSalvarDuplaViralMouseExited(evt);
            }
        });
        btnSalvarDuplaViral.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarDuplaViralActionPerformed(evt);
            }
        });
        pnlFebreInterno.add(btnSalvarDuplaViral);
        btnSalvarDuplaViral.setBounds(590, 360, 120, 30);

        tfLoteDupla1.setBackground(new java.awt.Color(153, 153, 153));
        tfLoteDupla1.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        tfLoteDupla1.setForeground(new java.awt.Color(255, 255, 255));
        pnlFebreInterno.add(tfLoteDupla1);
        tfLoteDupla1.setBounds(100, 210, 80, 23);

        tfValDupla1.setBackground(new java.awt.Color(153, 153, 153));
        tfValDupla1.setForeground(new java.awt.Color(255, 255, 255));
        try {
            tfValDupla1.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        tfValDupla1.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        pnlFebreInterno.add(tfValDupla1);
        tfValDupla1.setBounds(100, 250, 80, 23);

        lblLoteFebre.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblLoteFebre.setForeground(new java.awt.Color(255, 255, 255));
        lblLoteFebre.setText("Lote:");
        pnlFebreInterno.add(lblLoteFebre);
        lblLoteFebre.setBounds(50, 210, 50, 17);

        lblValFebre.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblValFebre.setForeground(new java.awt.Color(255, 255, 255));
        lblValFebre.setText("Val:");
        pnlFebreInterno.add(lblValFebre);
        lblValFebre.setBounds(50, 250, 50, 17);

        lblDupla2.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblDupla2.setForeground(new java.awt.Color(255, 255, 255));
        lblDupla2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDupla2.setText("2º DOSE");
        pnlFebreInterno.add(lblDupla2);
        lblDupla2.setBounds(240, 150, 70, 20);

        tfDuplaViral2.setBackground(new java.awt.Color(153, 153, 153));
        tfDuplaViral2.setForeground(new java.awt.Color(255, 255, 255));
        try {
            tfDuplaViral2.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        tfDuplaViral2.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        pnlFebreInterno.add(tfDuplaViral2);
        tfDuplaViral2.setBounds(240, 170, 80, 23);

        tfLoteDuplaViral2.setBackground(new java.awt.Color(153, 153, 153));
        tfLoteDuplaViral2.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        tfLoteDuplaViral2.setForeground(new java.awt.Color(255, 255, 255));
        pnlFebreInterno.add(tfLoteDuplaViral2);
        tfLoteDuplaViral2.setBounds(240, 210, 80, 23);

        tfValDupla2.setBackground(new java.awt.Color(153, 153, 153));
        tfValDupla2.setForeground(new java.awt.Color(255, 255, 255));
        try {
            tfValDupla2.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        tfValDupla2.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        pnlFebreInterno.add(tfValDupla2);
        tfValDupla2.setBounds(240, 250, 80, 23);

        lblDuplaViral.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        lblDuplaViral.setForeground(new java.awt.Color(255, 255, 255));
        lblDuplaViral.setText("DUPLA VIRAL (SARAMPO + RUBÉOLA)");
        pnlFebreInterno.add(lblDuplaViral);
        lblDuplaViral.setBounds(30, 70, 420, 22);

        lblFundo5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Fundo Geral Vacina.png"))); // NOI18N
        pnlFebreInterno.add(lblFundo5);
        lblFundo5.setBounds(0, 0, 720, 450);

        javax.swing.GroupLayout pnlFebreAmarelaLayout = new javax.swing.GroupLayout(pnlFebreAmarela);
        pnlFebreAmarela.setLayout(pnlFebreAmarelaLayout);
        pnlFebreAmarelaLayout.setHorizontalGroup(
            pnlFebreAmarelaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlFebreInterno, javax.swing.GroupLayout.DEFAULT_SIZE, 714, Short.MAX_VALUE)
        );
        pnlFebreAmarelaLayout.setVerticalGroup(
            pnlFebreAmarelaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFebreAmarelaLayout.createSequentialGroup()
                .addComponent(pnlFebreInterno, javax.swing.GroupLayout.PREFERRED_SIZE, 448, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 1, Short.MAX_VALUE))
        );

        pnlCartao.addTab("Dupla Viral", pnlFebreAmarela);

        pnlTripliceInterno.setLayout(null);

        lblVal1.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblVal1.setForeground(new java.awt.Color(255, 255, 255));
        lblVal1.setText("Val:");
        pnlTripliceInterno.add(lblVal1);
        lblVal1.setBounds(50, 250, 50, 17);

        tfValTriplice1.setBackground(new java.awt.Color(153, 153, 153));
        tfValTriplice1.setForeground(new java.awt.Color(255, 255, 255));
        try {
            tfValTriplice1.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        tfValTriplice1.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        pnlTripliceInterno.add(tfValTriplice1);
        tfValTriplice1.setBounds(100, 250, 80, 23);

        lblDose4.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblDose4.setForeground(new java.awt.Color(255, 255, 255));
        lblDose4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDose4.setText("2º DOSE");
        pnlTripliceInterno.add(lblDose4);
        lblDose4.setBounds(240, 150, 80, 17);

        tfTriplice2.setBackground(new java.awt.Color(153, 153, 153));
        tfTriplice2.setForeground(new java.awt.Color(255, 255, 255));
        try {
            tfTriplice2.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        tfTriplice2.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        pnlTripliceInterno.add(tfTriplice2);
        tfTriplice2.setBounds(240, 170, 80, 23);

        tfLoteTriplice2.setBackground(new java.awt.Color(153, 153, 153));
        tfLoteTriplice2.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        tfLoteTriplice2.setForeground(new java.awt.Color(255, 255, 255));
        pnlTripliceInterno.add(tfLoteTriplice2);
        tfLoteTriplice2.setBounds(240, 210, 80, 23);

        lblLote4.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblLote4.setForeground(new java.awt.Color(255, 255, 255));
        lblLote4.setText("Lote:");
        pnlTripliceInterno.add(lblLote4);
        lblLote4.setBounds(190, 210, 50, 17);

        lblTriplice.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        lblTriplice.setForeground(new java.awt.Color(255, 255, 255));
        lblTriplice.setText("TRÍPLICE VIRAL (SARAMPO + RUBÉOLA + CAXUMBA)");
        pnlTripliceInterno.add(lblTriplice);
        lblTriplice.setBounds(30, 70, 520, 22);

        lblVal4.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblVal4.setForeground(new java.awt.Color(255, 255, 255));
        lblVal4.setText("Val:");
        pnlTripliceInterno.add(lblVal4);
        lblVal4.setBounds(190, 250, 50, 17);

        lblDose1.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblDose1.setForeground(new java.awt.Color(255, 255, 255));
        lblDose1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDose1.setText("1º DOSE");
        pnlTripliceInterno.add(lblDose1);
        lblDose1.setBounds(100, 150, 80, 20);

        tfValTriplice2.setBackground(new java.awt.Color(153, 153, 153));
        tfValTriplice2.setForeground(new java.awt.Color(255, 255, 255));
        try {
            tfValTriplice2.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        tfValTriplice2.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        pnlTripliceInterno.add(tfValTriplice2);
        tfValTriplice2.setBounds(240, 250, 80, 23);

        tfTriplice1.setBackground(new java.awt.Color(153, 153, 153));
        tfTriplice1.setForeground(new java.awt.Color(255, 255, 255));
        try {
            tfTriplice1.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        tfTriplice1.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        pnlTripliceInterno.add(tfTriplice1);
        tfTriplice1.setBounds(100, 170, 80, 23);

        tfLoteTriplice1.setBackground(new java.awt.Color(153, 153, 153));
        tfLoteTriplice1.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        tfLoteTriplice1.setForeground(new java.awt.Color(255, 255, 255));
        pnlTripliceInterno.add(tfLoteTriplice1);
        tfLoteTriplice1.setBounds(100, 210, 80, 23);

        lblLote1.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblLote1.setForeground(new java.awt.Color(255, 255, 255));
        lblLote1.setText("Lote:");
        pnlTripliceInterno.add(lblLote1);
        lblLote1.setBounds(50, 210, 50, 17);

        btnSalvarTriplice.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btnSalvarTriplice.setForeground(new java.awt.Color(255, 255, 255));
        btnSalvarTriplice.setText("SALVAR");
        btnSalvarTriplice.setBorderPainted(false);
        btnSalvarTriplice.setContentAreaFilled(false);
        btnSalvarTriplice.setDefaultCapable(false);
        btnSalvarTriplice.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnSalvarTripliceMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnSalvarTripliceMouseExited(evt);
            }
        });
        btnSalvarTriplice.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarTripliceActionPerformed(evt);
            }
        });
        pnlTripliceInterno.add(btnSalvarTriplice);
        btnSalvarTriplice.setBounds(590, 360, 120, 31);

        lblFundo6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Fundo Geral Vacina.png"))); // NOI18N
        pnlTripliceInterno.add(lblFundo6);
        lblFundo6.setBounds(0, 0, 720, 450);

        javax.swing.GroupLayout pnlTripliceViralLayout = new javax.swing.GroupLayout(pnlTripliceViral);
        pnlTripliceViral.setLayout(pnlTripliceViralLayout);
        pnlTripliceViralLayout.setHorizontalGroup(
            pnlTripliceViralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlTripliceInterno, javax.swing.GroupLayout.DEFAULT_SIZE, 714, Short.MAX_VALUE)
        );
        pnlTripliceViralLayout.setVerticalGroup(
            pnlTripliceViralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTripliceViralLayout.createSequentialGroup()
                .addComponent(pnlTripliceInterno, javax.swing.GroupLayout.PREFERRED_SIZE, 450, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pnlCartao.addTab("Tríplice Viral", pnlTripliceViral);

        pnlTripliceInterno1.setLayout(null);

        lblOutras1.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        lblOutras1.setForeground(new java.awt.Color(255, 255, 255));
        lblOutras1.setText("OUTRAS VACINAS");
        pnlTripliceInterno1.add(lblOutras1);
        lblOutras1.setBounds(60, 60, 180, 22);

        lblDose5.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblDose5.setForeground(new java.awt.Color(255, 255, 255));
        lblDose5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDose5.setText("1º DOSE");
        pnlTripliceInterno1.add(lblDose5);
        lblDose5.setBounds(70, 150, 80, 17);

        lblDescricao1.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblDescricao1.setForeground(new java.awt.Color(255, 255, 255));
        lblDescricao1.setText("Desc.:");
        pnlTripliceInterno1.add(lblDescricao1);
        lblDescricao1.setBounds(20, 190, 50, 17);

        tfOutra1.setBackground(new java.awt.Color(153, 153, 153));
        tfOutra1.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        tfOutra1.setForeground(new java.awt.Color(255, 255, 255));
        pnlTripliceInterno1.add(tfOutra1);
        tfOutra1.setBounds(70, 190, 80, 23);

        tfDtOutra1.setBackground(new java.awt.Color(153, 153, 153));
        tfDtOutra1.setForeground(new java.awt.Color(255, 255, 255));
        try {
            tfDtOutra1.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        tfDtOutra1.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        pnlTripliceInterno1.add(tfDtOutra1);
        tfDtOutra1.setBounds(70, 230, 80, 23);

        lblLote5.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblLote5.setForeground(new java.awt.Color(255, 255, 255));
        lblLote5.setText("Lote:");
        pnlTripliceInterno1.add(lblLote5);
        lblLote5.setBounds(20, 270, 50, 17);

        tfLoteOutra1.setBackground(new java.awt.Color(153, 153, 153));
        tfLoteOutra1.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        tfLoteOutra1.setForeground(new java.awt.Color(255, 255, 255));
        pnlTripliceInterno1.add(tfLoteOutra1);
        tfLoteOutra1.setBounds(70, 270, 80, 23);

        lblVal5.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblVal5.setForeground(new java.awt.Color(255, 255, 255));
        lblVal5.setText("Val:");
        pnlTripliceInterno1.add(lblVal5);
        lblVal5.setBounds(20, 310, 50, 17);

        tfValOutra1.setBackground(new java.awt.Color(153, 153, 153));
        tfValOutra1.setForeground(new java.awt.Color(255, 255, 255));
        try {
            tfValOutra1.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        tfValOutra1.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        pnlTripliceInterno1.add(tfValOutra1);
        tfValOutra1.setBounds(70, 310, 80, 23);

        lblDose6.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblDose6.setForeground(new java.awt.Color(255, 255, 255));
        lblDose6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDose6.setText("2º DOSE");
        pnlTripliceInterno1.add(lblDose6);
        lblDose6.setBounds(230, 150, 80, 17);

        lblDescricao2.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblDescricao2.setForeground(new java.awt.Color(255, 255, 255));
        lblDescricao2.setText("Desc.:");
        pnlTripliceInterno1.add(lblDescricao2);
        lblDescricao2.setBounds(180, 190, 50, 17);

        tfOutra1d2.setBackground(new java.awt.Color(153, 153, 153));
        tfOutra1d2.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        tfOutra1d2.setForeground(new java.awt.Color(255, 255, 255));
        pnlTripliceInterno1.add(tfOutra1d2);
        tfOutra1d2.setBounds(230, 190, 80, 23);

        tfDtOutra1d2.setBackground(new java.awt.Color(153, 153, 153));
        tfDtOutra1d2.setForeground(new java.awt.Color(255, 255, 255));
        try {
            tfDtOutra1d2.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        tfDtOutra1d2.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        pnlTripliceInterno1.add(tfDtOutra1d2);
        tfDtOutra1d2.setBounds(230, 230, 80, 23);

        lblLote6.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblLote6.setForeground(new java.awt.Color(255, 255, 255));
        lblLote6.setText("Lote:");
        pnlTripliceInterno1.add(lblLote6);
        lblLote6.setBounds(180, 270, 50, 17);

        tfLoteOutra1d2.setBackground(new java.awt.Color(153, 153, 153));
        tfLoteOutra1d2.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        tfLoteOutra1d2.setForeground(new java.awt.Color(255, 255, 255));
        pnlTripliceInterno1.add(tfLoteOutra1d2);
        tfLoteOutra1d2.setBounds(230, 270, 80, 23);

        lblVal6.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblVal6.setForeground(new java.awt.Color(255, 255, 255));
        lblVal6.setText("Val:");
        pnlTripliceInterno1.add(lblVal6);
        lblVal6.setBounds(180, 310, 50, 17);

        tfValOutra1d2.setBackground(new java.awt.Color(153, 153, 153));
        tfValOutra1d2.setForeground(new java.awt.Color(255, 255, 255));
        try {
            tfValOutra1d2.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        tfValOutra1d2.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        pnlTripliceInterno1.add(tfValOutra1d2);
        tfValOutra1d2.setBounds(230, 310, 80, 23);

        lblOutras2.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        lblOutras2.setForeground(new java.awt.Color(255, 255, 255));
        lblOutras2.setText("OUTRAS VACINAS");
        pnlTripliceInterno1.add(lblOutras2);
        lblOutras2.setBounds(420, 60, 180, 22);

        lblDose7.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblDose7.setForeground(new java.awt.Color(255, 255, 255));
        lblDose7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDose7.setText("1º DOSE");
        pnlTripliceInterno1.add(lblDose7);
        lblDose7.setBounds(410, 150, 80, 17);

        lblDescricao3.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblDescricao3.setForeground(new java.awt.Color(255, 255, 255));
        lblDescricao3.setText("Desc.:");
        pnlTripliceInterno1.add(lblDescricao3);
        lblDescricao3.setBounds(360, 190, 50, 17);

        tfOutra2.setBackground(new java.awt.Color(153, 153, 153));
        tfOutra2.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        tfOutra2.setForeground(new java.awt.Color(255, 255, 255));
        pnlTripliceInterno1.add(tfOutra2);
        tfOutra2.setBounds(410, 190, 80, 23);

        tfDtOutra2.setBackground(new java.awt.Color(153, 153, 153));
        tfDtOutra2.setForeground(new java.awt.Color(255, 255, 255));
        try {
            tfDtOutra2.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        tfDtOutra2.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        pnlTripliceInterno1.add(tfDtOutra2);
        tfDtOutra2.setBounds(410, 230, 80, 23);

        lblLote7.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblLote7.setForeground(new java.awt.Color(255, 255, 255));
        lblLote7.setText("Lote:");
        pnlTripliceInterno1.add(lblLote7);
        lblLote7.setBounds(360, 270, 50, 17);

        tfLoteOutra2.setBackground(new java.awt.Color(153, 153, 153));
        tfLoteOutra2.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        tfLoteOutra2.setForeground(new java.awt.Color(255, 255, 255));
        pnlTripliceInterno1.add(tfLoteOutra2);
        tfLoteOutra2.setBounds(410, 270, 80, 23);

        lblVal7.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblVal7.setForeground(new java.awt.Color(255, 255, 255));
        lblVal7.setText("Val:");
        pnlTripliceInterno1.add(lblVal7);
        lblVal7.setBounds(360, 310, 50, 17);

        tfValOutra2.setBackground(new java.awt.Color(153, 153, 153));
        tfValOutra2.setForeground(new java.awt.Color(255, 255, 255));
        try {
            tfValOutra2.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        tfValOutra2.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        pnlTripliceInterno1.add(tfValOutra2);
        tfValOutra2.setBounds(410, 310, 80, 23);

        lblDose8.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblDose8.setForeground(new java.awt.Color(255, 255, 255));
        lblDose8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDose8.setText("2º DOSE");
        pnlTripliceInterno1.add(lblDose8);
        lblDose8.setBounds(570, 150, 80, 17);

        lblDescricao4.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblDescricao4.setForeground(new java.awt.Color(255, 255, 255));
        lblDescricao4.setText("Desc.:");
        pnlTripliceInterno1.add(lblDescricao4);
        lblDescricao4.setBounds(520, 190, 50, 17);

        tfOutra2d2.setBackground(new java.awt.Color(153, 153, 153));
        tfOutra2d2.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        tfOutra2d2.setForeground(new java.awt.Color(255, 255, 255));
        pnlTripliceInterno1.add(tfOutra2d2);
        tfOutra2d2.setBounds(570, 190, 80, 23);

        tfDtOutra2d2.setBackground(new java.awt.Color(153, 153, 153));
        tfDtOutra2d2.setForeground(new java.awt.Color(255, 255, 255));
        try {
            tfDtOutra2d2.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        tfDtOutra2d2.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        pnlTripliceInterno1.add(tfDtOutra2d2);
        tfDtOutra2d2.setBounds(570, 230, 80, 23);

        lblLote8.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblLote8.setForeground(new java.awt.Color(255, 255, 255));
        lblLote8.setText("Lote:");
        pnlTripliceInterno1.add(lblLote8);
        lblLote8.setBounds(520, 270, 50, 17);

        tfLoteOutra2d2.setBackground(new java.awt.Color(153, 153, 153));
        tfLoteOutra2d2.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        tfLoteOutra2d2.setForeground(new java.awt.Color(255, 255, 255));
        pnlTripliceInterno1.add(tfLoteOutra2d2);
        tfLoteOutra2d2.setBounds(570, 270, 80, 23);

        lblVal8.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblVal8.setForeground(new java.awt.Color(255, 255, 255));
        lblVal8.setText("Val:");
        pnlTripliceInterno1.add(lblVal8);
        lblVal8.setBounds(520, 310, 50, 17);

        tfValOutra2d2.setBackground(new java.awt.Color(153, 153, 153));
        tfValOutra2d2.setForeground(new java.awt.Color(255, 255, 255));
        try {
            tfValOutra2d2.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        tfValOutra2d2.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        pnlTripliceInterno1.add(tfValOutra2d2);
        tfValOutra2d2.setBounds(570, 310, 80, 23);

        btnSalvarOutras.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btnSalvarOutras.setForeground(new java.awt.Color(255, 255, 255));
        btnSalvarOutras.setText("SALVAR");
        btnSalvarOutras.setBorderPainted(false);
        btnSalvarOutras.setContentAreaFilled(false);
        btnSalvarOutras.setDefaultCapable(false);
        btnSalvarOutras.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnSalvarOutrasMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnSalvarOutrasMouseExited(evt);
            }
        });
        btnSalvarOutras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarOutrasActionPerformed(evt);
            }
        });
        pnlTripliceInterno1.add(btnSalvarOutras);
        btnSalvarOutras.setBounds(590, 360, 120, 31);

        lblFundo7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Fundo Geral Vacina.png"))); // NOI18N
        pnlTripliceInterno1.add(lblFundo7);
        lblFundo7.setBounds(0, 0, 720, 450);

        javax.swing.GroupLayout pnlOutrasLayout = new javax.swing.GroupLayout(pnlOutras);
        pnlOutras.setLayout(pnlOutrasLayout);
        pnlOutrasLayout.setHorizontalGroup(
            pnlOutrasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlTripliceInterno1, javax.swing.GroupLayout.DEFAULT_SIZE, 714, Short.MAX_VALUE)
        );
        pnlOutrasLayout.setVerticalGroup(
            pnlOutrasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlOutrasLayout.createSequentialGroup()
                .addComponent(pnlTripliceInterno1, javax.swing.GroupLayout.PREFERRED_SIZE, 450, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pnlCartao.addTab("Outras", pnlOutras);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlCartao)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlCartao, javax.swing.GroupLayout.PREFERRED_SIZE, 480, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnGravarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGravarActionPerformed
        this.atualizarCartao();
        //pnlCartao.setSelectedIndex(1);
    }//GEN-LAST:event_btnGravarActionPerformed

    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarActionPerformed
        this.atualizarDuplaAdulto();
        //pnlCartao.setSelectedIndex(1);
    }//GEN-LAST:event_btnSalvarActionPerformed

    private void tfLoteInfluenza3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfLoteInfluenza3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfLoteInfluenza3ActionPerformed

    private void btnGravarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnGravarMouseEntered
        // TODO add your handling code here:
        this.entraMouse(btnGravar);
    }//GEN-LAST:event_btnGravarMouseEntered

    private void btnGravarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnGravarMouseExited
        // TODO add your handling code here:
        this.saiMouse(btnGravar);
    }//GEN-LAST:event_btnGravarMouseExited

    private void btnSalvarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSalvarMouseEntered
        // TODO add your handling code here:
        this.entraMouse(btnSalvar);
    }//GEN-LAST:event_btnSalvarMouseEntered

    private void btnSalvarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSalvarMouseExited
        // TODO add your handling code here:
        this.saiMouse(btnSalvar);
    }//GEN-LAST:event_btnSalvarMouseExited

    private void btnSalvarInfluenzaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSalvarInfluenzaMouseEntered
        // TODO add your handling code here:
        this.entraMouse(btnSalvarInfluenza);
    }//GEN-LAST:event_btnSalvarInfluenzaMouseEntered

    private void btnSalvarInfluenzaMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSalvarInfluenzaMouseExited
        // TODO add your handling code here:
        this.saiMouse(btnSalvarInfluenza);
    }//GEN-LAST:event_btnSalvarInfluenzaMouseExited

    private void btnSalvarFebreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarFebreActionPerformed
        this.atualizarFebreAmarela();
        // pnlCartao.setSelectedIndex(1);
    }//GEN-LAST:event_btnSalvarFebreActionPerformed

    private void btnSalvarFebreMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSalvarFebreMouseEntered
        // TODO add your handling code here:
        this.entraMouse(btnSalvarFebre);
    }//GEN-LAST:event_btnSalvarFebreMouseEntered

    private void btnSalvarFebreMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSalvarFebreMouseExited
        // TODO add your handling code here:
        this.saiMouse(btnSalvarFebre);
    }//GEN-LAST:event_btnSalvarFebreMouseExited

    private void btnSalvarDuplaViralMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSalvarDuplaViralMouseEntered
        // TODO add your handling code here:
        this.entraMouse(btnSalvarDuplaViral);
    }//GEN-LAST:event_btnSalvarDuplaViralMouseEntered

    private void btnSalvarDuplaViralMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSalvarDuplaViralMouseExited
        // TODO add your handling code here:
        this.saiMouse(btnSalvarDuplaViral);
    }//GEN-LAST:event_btnSalvarDuplaViralMouseExited

    private void btnSalvarInfluenzaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarInfluenzaActionPerformed
        this.atualizarInfluenza();
        //pnlCartao.setSelectedIndex(1);
    }//GEN-LAST:event_btnSalvarInfluenzaActionPerformed

    private void btnSalvarDuplaViralActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarDuplaViralActionPerformed
        this.atualizarDuplaViral();
        //pnlCartao.setSelectedIndex(1);
    }//GEN-LAST:event_btnSalvarDuplaViralActionPerformed

    private void btnSalvarTripliceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarTripliceActionPerformed
        this.atualizarTripliceViral();
        //pnlCartao.setSelectedIndex(1);
    }//GEN-LAST:event_btnSalvarTripliceActionPerformed

    private void btnSalvarTripliceMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSalvarTripliceMouseEntered
        // TODO add your handling code here:
        this.entraMouse(btnSalvarTriplice);
    }//GEN-LAST:event_btnSalvarTripliceMouseEntered

    private void btnSalvarTripliceMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSalvarTripliceMouseExited
        // TODO add your handling code here:
        this.saiMouse(btnSalvarTriplice);
    }//GEN-LAST:event_btnSalvarTripliceMouseExited

    private void tfRuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfRuaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfRuaActionPerformed

    private void tfIdPacienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfIdPacienteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfIdPacienteActionPerformed

    private void tfDuplaViral1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfDuplaViral1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfDuplaViral1ActionPerformed

    private void btnSalvarOutrasMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSalvarOutrasMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_btnSalvarOutrasMouseEntered

    private void btnSalvarOutrasMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSalvarOutrasMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_btnSalvarOutrasMouseExited

    private void btnSalvarOutrasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarOutrasActionPerformed
        // TODO add your handling code here:
        this.atualizarOutras();
    }//GEN-LAST:event_btnSalvarOutrasActionPerformed

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
            java.util.logging.Logger.getLogger(AlterarCartao.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AlterarCartao.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AlterarCartao.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AlterarCartao.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AlterarCartao().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnGravar;
    private javax.swing.JButton btnSalvar;
    private javax.swing.JButton btnSalvarDuplaViral;
    private javax.swing.JButton btnSalvarFebre;
    private javax.swing.JButton btnSalvarInfluenza;
    private javax.swing.JButton btnSalvarOutras;
    private javax.swing.JButton btnSalvarTriplice;
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
    private javax.swing.JLabel lblFundo1;
    private javax.swing.JLabel lblFundo2;
    private javax.swing.JLabel lblFundo3;
    private javax.swing.JLabel lblFundo4;
    private javax.swing.JLabel lblFundo5;
    private javax.swing.JLabel lblFundo6;
    private javax.swing.JLabel lblFundo7;
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
