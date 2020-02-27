/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fichaatendimento;

import bancodedados.MysqlConnect;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import principal.PesquisaPaciente;

/**
 *
 * @author Franciele Alves Barbosa e Rogério Costa Negro Rocha
 */
public class FichasAtendimento extends javax.swing.JFrame {

    Connection conn;

    /**
     * Creates new form FichasAtendimento
     */
    public FichasAtendimento() {
        initComponents();

        try {
            conn = MysqlConnect.connectDB();
            this.preencherCampos();
            this.preencheTabela();
        } catch (SQLException sqle) {
            JOptionPane.showMessageDialog(null, "Erro ao conectar com o Banco de Dados " /*+ ex.getMessage()*/, "ERRO", JOptionPane.ERROR_MESSAGE);
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

    public void preencherCampos() {
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        String qry = "SELECT idPaciente, cpf, nome, dtNascimento, curso, rua,"
                + " numero, bairro, municipio, uf, cartaoSus"
                + " FROM pacientes WHERE idPaciente= ?";

        try {
            pstmt = conn.prepareStatement(qry);

            pstmt.setString(1, PesquisaPaciente.id);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                tfIdPaciente.setText(rs.getString("idPaciente"));
                tfCpf.setText(rs.getString("cpf"));
                tfNome.setText(rs.getString("nome"));
                tfDtNasc.setText(rs.getString("dtNascimento"));
                tfCurso.setText(rs.getString("curso"));
                tfRua.setText(rs.getString("rua"));
                tfNCasa.setText(rs.getString("numero"));
                tfBairro.setText(rs.getString("bairro"));
                tfCidade.setText(rs.getString("municipio"));
                tfUf.setText(rs.getString("uf"));
                tfNSus.setText(rs.getString("cartaoSus"));
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro: " + ex.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void preencheTabela() {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        DefaultTableModel tabelaFichas = (DefaultTableModel) tblFicha.getModel();
        tabelaFichas.setNumRows(0);
        String qry = "SELECT * FROM fichas where idPaciente= ?";

        try {
            pstmt = conn.prepareStatement(qry);
            pstmt.setString(1, tfIdPaciente.getText());
            rs = pstmt.executeQuery();

            while (rs.next()) {
                tabelaFichas.addRow(new String[]{getDatDate(rs.getString("data")), rs.getString("anamnese"), rs.getString("prescricao"), rs.getString("atendente")});
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro: " + ex.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static String getDatString(String dataDMA) {
        String reportDate = "";
        // Converte a data no formato dd/MM/yyyy
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        DateFormat dfDMA = new SimpleDateFormat("dd/MM/yyyy");
        java.util.Date enteredDate;
        try {
            enteredDate = dfDMA.parse(dataDMA);
            reportDate = df.format(enteredDate);
        } catch (ParseException ex) {
            Logger.getLogger(FichasAtendimento.class.getName()).log(Level.SEVERE, null, ex);
        }
        //String reportDate = df.format(enteredDate);
        return reportDate;
    }

    public static String getDatDate(String dataDMA) {
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
            Logger.getLogger(FichasAtendimento.class.getName()).log(Level.SEVERE, null, ex);
        }
        //String reportDate = df.format(enteredDate);
        return reportDate;
    }

    public void inserirBD() {
        this.controleRepeticaoBD();
        PreparedStatement pstmt = null;

        String qry = "INSERT INTO fichas(idPaciente, data, anamnese, prescricao, atendente) VALUES(?,?,?,?,?)";

        try {

            for (int linha = 0; linha < tblFicha.getRowCount(); linha++) {
                pstmt = conn.prepareStatement(qry);
                pstmt.setString(1, tfIdPaciente.getText());

                pstmt.setString(2, getDatString(tblFicha.getValueAt(linha, 0).toString()));
                pstmt.setString(3, tblFicha.getValueAt(linha, 1).toString());
                pstmt.setString(4, tblFicha.getValueAt(linha, 2).toString());
                pstmt.setString(5, tblFicha.getValueAt(linha, 3).toString());

                pstmt.executeUpdate();

            }
            JOptionPane.showMessageDialog(null, "Salvo com sucesso!");
            this.preencheTabela();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar: Data Inválida" /*+ ex.getMessage()*/, "ERRO", JOptionPane.ERROR_MESSAGE);
        } catch (NullPointerException npx) {
            JOptionPane.showMessageDialog(null, "Preencha toda a linha: " + npx.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void controleRepeticaoBD() {
        PreparedStatement pstmt = null;
        String qry = "DELETE FROM fichas WHERE idPaciente= ?";

        try {
            pstmt = conn.prepareStatement(qry);
            pstmt.setString(1, tfIdPaciente.getText());
            pstmt.executeUpdate();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro: " + ex.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
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

        pnlFichas = new javax.swing.JPanel();
        lblNome = new javax.swing.JLabel();
        tfNome = new javax.swing.JTextField();
        lblCurso = new javax.swing.JLabel();
        tfCurso = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        tfDtNasc = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        tfNSus = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        tfCpf = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblFicha = new javax.swing.JTable();
        btnAtualizar = new javax.swing.JButton();
        btnAdicionarLinha = new javax.swing.JButton();
        btnRemover = new javax.swing.JButton();
        lblIdPaciente = new javax.swing.JLabel();
        tfIdPaciente = new javax.swing.JTextField();
        lblRua = new javax.swing.JLabel();
        tfRua = new javax.swing.JTextField();
        lblNumero = new javax.swing.JLabel();
        tfNCasa = new javax.swing.JTextField();
        lblBairro = new javax.swing.JLabel();
        tfBairro = new javax.swing.JTextField();
        lblCidade = new javax.swing.JLabel();
        tfCidade = new javax.swing.JTextField();
        lblUf = new javax.swing.JLabel();
        tfUf = new javax.swing.JTextField();
        lblFundo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Fichas de Atendimento");
        setIconImage(new javax.swing.ImageIcon(getClass().getResource("/imagens/Quiron.png")).getImage());
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        pnlFichas.setLayout(null);

        lblNome.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblNome.setForeground(new java.awt.Color(255, 255, 255));
        lblNome.setText("NOME");
        pnlFichas.add(lblNome);
        lblNome.setBounds(20, 60, 50, 17);

        tfNome.setEditable(false);
        tfNome.setBackground(new java.awt.Color(153, 153, 153));
        tfNome.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        tfNome.setForeground(new java.awt.Color(255, 255, 255));
        pnlFichas.add(tfNome);
        tfNome.setBounds(80, 60, 340, 23);

        lblCurso.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblCurso.setForeground(new java.awt.Color(255, 255, 255));
        lblCurso.setText("CURSO");
        pnlFichas.add(lblCurso);
        lblCurso.setBounds(440, 100, 50, 17);

        tfCurso.setEditable(false);
        tfCurso.setBackground(new java.awt.Color(153, 153, 153));
        tfCurso.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        tfCurso.setForeground(new java.awt.Color(255, 255, 255));
        pnlFichas.add(tfCurso);
        tfCurso.setBounds(500, 100, 340, 23);

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("DATA NASCIMENTO");
        pnlFichas.add(jLabel2);
        jLabel2.setBounds(200, 100, 133, 17);

        tfDtNasc.setEditable(false);
        tfDtNasc.setBackground(new java.awt.Color(153, 153, 153));
        tfDtNasc.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        tfDtNasc.setForeground(new java.awt.Color(255, 255, 255));
        pnlFichas.add(tfDtNasc);
        tfDtNasc.setBounds(340, 100, 80, 23);

        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Nº CARTÃO SUS");
        pnlFichas.add(jLabel4);
        jLabel4.setBounds(440, 60, 106, 17);

        tfNSus.setEditable(false);
        tfNSus.setBackground(new java.awt.Color(153, 153, 153));
        tfNSus.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        tfNSus.setForeground(new java.awt.Color(255, 255, 255));
        pnlFichas.add(tfNSus);
        tfNSus.setBounds(560, 60, 100, 23);

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("CPF");
        pnlFichas.add(jLabel1);
        jLabel1.setBounds(20, 100, 26, 17);

        tfCpf.setEditable(false);
        tfCpf.setBackground(new java.awt.Color(153, 153, 153));
        tfCpf.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        tfCpf.setForeground(new java.awt.Color(255, 255, 255));
        pnlFichas.add(tfCpf);
        tfCpf.setBounds(80, 100, 110, 23);

        tblFicha.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        tblFicha.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "DATA", "ANAMNESE E EXAME FÍSICO", "PRESCRIÇÃO/PROCEDIMENTO", "ATENDENTE"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tblFicha.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                tblFichaMouseExited(evt);
            }
        });
        jScrollPane1.setViewportView(tblFicha);

        pnlFichas.add(jScrollPane1);
        jScrollPane1.setBounds(10, 180, 830, 310);

        btnAtualizar.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btnAtualizar.setForeground(new java.awt.Color(255, 255, 255));
        btnAtualizar.setText("ATUALIZAR");
        btnAtualizar.setBorderPainted(false);
        btnAtualizar.setContentAreaFilled(false);
        btnAtualizar.setFocusPainted(false);
        btnAtualizar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnAtualizarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnAtualizarMouseExited(evt);
            }
        });
        btnAtualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAtualizarActionPerformed(evt);
            }
        });
        pnlFichas.add(btnAtualizar);
        btnAtualizar.setBounds(570, 500, 230, 30);

        btnAdicionarLinha.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btnAdicionarLinha.setForeground(new java.awt.Color(255, 255, 255));
        btnAdicionarLinha.setText("ADICIONAR LINHA");
        btnAdicionarLinha.setBorderPainted(false);
        btnAdicionarLinha.setContentAreaFilled(false);
        btnAdicionarLinha.setFocusPainted(false);
        btnAdicionarLinha.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnAdicionarLinhaMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnAdicionarLinhaMouseExited(evt);
            }
        });
        btnAdicionarLinha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdicionarLinhaActionPerformed(evt);
            }
        });
        pnlFichas.add(btnAdicionarLinha);
        btnAdicionarLinha.setBounds(320, 500, 230, 30);

        btnRemover.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btnRemover.setForeground(new java.awt.Color(255, 255, 255));
        btnRemover.setText("DELETAR LINHA");
        btnRemover.setBorderPainted(false);
        btnRemover.setContentAreaFilled(false);
        btnRemover.setFocusPainted(false);
        btnRemover.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoverActionPerformed(evt);
            }
        });
        pnlFichas.add(btnRemover);
        btnRemover.setBounds(70, 500, 230, 30);

        lblIdPaciente.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblIdPaciente.setForeground(new java.awt.Color(255, 255, 255));
        lblIdPaciente.setText("ID");
        pnlFichas.add(lblIdPaciente);
        lblIdPaciente.setBounds(690, 60, 40, 17);

        tfIdPaciente.setEditable(false);
        tfIdPaciente.setBackground(new java.awt.Color(153, 153, 153));
        tfIdPaciente.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        tfIdPaciente.setForeground(new java.awt.Color(255, 255, 255));
        pnlFichas.add(tfIdPaciente);
        tfIdPaciente.setBounds(740, 60, 100, 23);

        lblRua.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblRua.setForeground(new java.awt.Color(255, 255, 255));
        lblRua.setText("RUA");
        pnlFichas.add(lblRua);
        lblRua.setBounds(20, 140, 40, 17);

        tfRua.setBackground(new java.awt.Color(153, 153, 153));
        tfRua.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        tfRua.setForeground(new java.awt.Color(255, 255, 255));
        tfRua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfRuaActionPerformed(evt);
            }
        });
        pnlFichas.add(tfRua);
        tfRua.setBounds(80, 140, 150, 23);

        lblNumero.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblNumero.setForeground(new java.awt.Color(255, 255, 255));
        lblNumero.setText("Nº");
        pnlFichas.add(lblNumero);
        lblNumero.setBounds(240, 140, 20, 17);

        tfNCasa.setBackground(new java.awt.Color(153, 153, 153));
        tfNCasa.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        tfNCasa.setForeground(new java.awt.Color(255, 255, 255));
        pnlFichas.add(tfNCasa);
        tfNCasa.setBounds(260, 140, 40, 23);

        lblBairro.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblBairro.setForeground(new java.awt.Color(255, 255, 255));
        lblBairro.setText("BAIRRO");
        pnlFichas.add(lblBairro);
        lblBairro.setBounds(320, 140, 70, 17);

        tfBairro.setBackground(new java.awt.Color(153, 153, 153));
        tfBairro.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        tfBairro.setForeground(new java.awt.Color(255, 255, 255));
        pnlFichas.add(tfBairro);
        tfBairro.setBounds(380, 140, 150, 23);

        lblCidade.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblCidade.setForeground(new java.awt.Color(255, 255, 255));
        lblCidade.setText("CIDADE");
        pnlFichas.add(lblCidade);
        lblCidade.setBounds(540, 140, 70, 17);

        tfCidade.setBackground(new java.awt.Color(153, 153, 153));
        tfCidade.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        tfCidade.setForeground(new java.awt.Color(255, 255, 255));
        pnlFichas.add(tfCidade);
        tfCidade.setBounds(600, 140, 100, 23);

        lblUf.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblUf.setForeground(new java.awt.Color(255, 255, 255));
        lblUf.setText("UF");
        pnlFichas.add(lblUf);
        lblUf.setBounds(710, 140, 30, 17);

        tfUf.setBackground(new java.awt.Color(153, 153, 153));
        tfUf.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        tfUf.setForeground(new java.awt.Color(255, 255, 255));
        pnlFichas.add(tfUf);
        tfUf.setBounds(740, 140, 40, 23);

        lblFundo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Fundo Geral.png"))); // NOI18N
        pnlFichas.add(lblFundo);
        lblFundo.setBounds(0, 0, 850, 570);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlFichas, javax.swing.GroupLayout.DEFAULT_SIZE, 850, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlFichas, javax.swing.GroupLayout.DEFAULT_SIZE, 550, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnAtualizarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAtualizarMouseEntered
        // TODO add your handling code here:
        this.entraMouse(btnAtualizar);
    }//GEN-LAST:event_btnAtualizarMouseEntered

    private void btnAtualizarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAtualizarMouseExited
        // TODO add your handling code here:
        this.saiMouse(btnAtualizar);
    }//GEN-LAST:event_btnAtualizarMouseExited

    private void btnAtualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAtualizarActionPerformed
        // TODO add your handling code here:
        TableCellEditor editor = tblFicha.getCellEditor();
        if (editor != null) {
            editor.stopCellEditing();
        }
        this.inserirBD();
    }//GEN-LAST:event_btnAtualizarActionPerformed

    private void btnAdicionarLinhaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAdicionarLinhaMouseEntered
        // TODO add your handling code here:
        this.entraMouse(btnAdicionarLinha);
    }//GEN-LAST:event_btnAdicionarLinhaMouseEntered

    private void btnAdicionarLinhaMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAdicionarLinhaMouseExited
        // TODO add your handling code here:
        this.saiMouse(btnAdicionarLinha);
    }//GEN-LAST:event_btnAdicionarLinhaMouseExited

    private void btnAdicionarLinhaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdicionarLinhaActionPerformed
        // TODO add your handling code here:
        DefaultTableModel tabelaFichas = (DefaultTableModel) tblFicha.getModel();
        tabelaFichas.addRow(new String[]{});
    }//GEN-LAST:event_btnAdicionarLinhaActionPerformed

    private void tblFichaMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblFichaMouseExited
        // TODO add your handling code here:
        this.dispatchEvent(new WindowEvent(this, KeyEvent.VK_ENTER));
    }//GEN-LAST:event_tblFichaMouseExited

    private void btnRemoverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoverActionPerformed
        // TODO add your handling code here:
        try {
            DefaultTableModel tabelaFichas = (DefaultTableModel) tblFicha.getModel();
            tabelaFichas.removeRow(tblFicha.getSelectedRow());
        } catch (ArrayIndexOutOfBoundsException ex) {
            JOptionPane.showMessageDialog(null, "Selecione uma linha!", "ERRO", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnRemoverActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        // TODO add your handling code here:
        int op = JOptionPane.showConfirmDialog(null, "Deseja salvar antes de sair?", "ATENÇÃO", JOptionPane.YES_OPTION);
        if (op == JOptionPane.YES_OPTION) {
            TableCellEditor editor = tblFicha.getCellEditor();
            if (editor != null) {
                editor.stopCellEditing();
            }
            this.inserirBD();
        }

    }//GEN-LAST:event_formWindowClosing

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
            java.util.logging.Logger.getLogger(FichasAtendimento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FichasAtendimento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FichasAtendimento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FichasAtendimento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FichasAtendimento().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdicionarLinha;
    private javax.swing.JButton btnAtualizar;
    private javax.swing.JButton btnRemover;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblBairro;
    private javax.swing.JLabel lblCidade;
    private javax.swing.JLabel lblCurso;
    private javax.swing.JLabel lblFundo;
    private javax.swing.JLabel lblIdPaciente;
    private javax.swing.JLabel lblNome;
    private javax.swing.JLabel lblNumero;
    private javax.swing.JLabel lblRua;
    private javax.swing.JLabel lblUf;
    private javax.swing.JPanel pnlFichas;
    private javax.swing.JTable tblFicha;
    private javax.swing.JTextField tfBairro;
    private javax.swing.JTextField tfCidade;
    private javax.swing.JTextField tfCpf;
    private javax.swing.JTextField tfCurso;
    private javax.swing.JTextField tfDtNasc;
    private javax.swing.JTextField tfIdPaciente;
    private javax.swing.JTextField tfNCasa;
    private javax.swing.JTextField tfNSus;
    private javax.swing.JTextField tfNome;
    private javax.swing.JTextField tfRua;
    private javax.swing.JTextField tfUf;
    // End of variables declaration//GEN-END:variables

}
