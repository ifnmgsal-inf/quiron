/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gerenciarusuarios;

import bancodedados.MysqlConnect;
import java.awt.Color;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import login.TelaLogin;

/**
 *
 * @author Franciele Alves Barbosa e Rogério Costa Negro Rocha
 */
public class TelaAlteraUsuario extends javax.swing.JFrame {

    private Connection conn = null;
    private String nome, matricula, cpf, telefone, numMatricula;
    private String senha, confirmaSenha;
    private int administrador;

    /**
     * Creates new form TelaCadastroUsuario
     */
    public TelaAlteraUsuario() {
        initComponents();

        try {
            conn = MysqlConnect.connectDB();
            this.verificaAdministrador();
            //JOptionPane.showMessageDialog(null, "Conexao bem sucedida");
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

    public void verificaAdministrador() {
        if (TelaLogin.administradorBD != 1) {
            lblTipoUsuario.setEnabled(false);
            rbAdministrador.setEnabled(false);
            rbPadrao.setEnabled(false);
            PreparedStatement pstmt = null;
            ResultSet rs = null;
            String qry = "SELECT matricula FROM usuarios WHERE cpf= ?";

            try {
                pstmt = conn.prepareStatement(qry);
                pstmt.setString(1, TelaLogin.cpfBD);
                rs = pstmt.executeQuery();

                while (rs.next()) {
                    jcbUsuarios.addItem(rs.getString("matricula"));
                }

            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Erro: " + ex.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            this.caixaUsuario();
        }

    }

    public void valorCampos() {
        nome = tfNome.getText();
        matricula = tfMatricula.getText();
        cpf = tfCpf.getText();
        telefone = tfTelefone.getText();
        senha = String.valueOf(tfSenha.getPassword());
        if (rbAdministrador.isSelected()) {
            administrador = 1;
        } else {
            administrador = 0;
        }

    }

    public void preencheCampos() {
        numMatricula = (String) jcbUsuarios.getSelectedItem();

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String qry = "SELECT * FROM usuarios WHERE matricula= ?";

        try {
            pstmt = conn.prepareStatement(qry);
            pstmt.setString(1, numMatricula);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                tfNome.setText(rs.getString("nome"));
                tfMatricula.setText(rs.getString("matricula"));
                tfCpf.setText(rs.getString("cpf"));
                tfTelefone.setText(rs.getString("telefone"));
                tfSenha.setText(rs.getString("senha"));
                tfConfirmaSenha.setText(rs.getString("senha"));
                if (rs.getInt("administrador") == 1) {
                    rbAdministrador.setSelected(true);
                } else {
                    rbPadrao.setSelected(true);
                }
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro: " + ex.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void caixaUsuario() {
        jcbUsuarios.removeAllItems();
        String qryNome = "SELECT * FROM usuarios";

        try (Statement stmt = conn.prepareStatement(qryNome)) {

            try (ResultSet rs = stmt.executeQuery(qryNome)) {

                while (rs.next()) {
                    jcbUsuarios.addItem(rs.getString("matricula"));
                }
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro: " + ex.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void alterarUsuario(){
        this.valorCampos();
        numMatricula = (String) jcbUsuarios.getSelectedItem();
        if (!(Arrays.equals(tfSenha.getPassword(), tfConfirmaSenha.getPassword()))) {
            JOptionPane.showMessageDialog(null, "Senhas não coincidem!", "ERRO", JOptionPane.ERROR_MESSAGE);
        } else {
            String qryAtualiza = "UPDATE usuarios SET matricula= ?, nome= ?, cpf= ?,"
                    + " telefone= ?, senha= ?, administrador= ? WHERE matricula= ?";
            try (PreparedStatement pstmt = conn.prepareStatement(qryAtualiza)) {
                pstmt.setString(1, matricula);
                pstmt.setString(2, nome);
                pstmt.setString(3, cpf);
                pstmt.setString(4, telefone);
                pstmt.setString(5, String.valueOf(tfSenha.getPassword()));
                if (rbAdministrador.isSelected()) {
                    pstmt.setInt(6, 1);
                } else {
                    pstmt.setInt(6, 0);
                }
                pstmt.setString(7, jcbUsuarios.getSelectedItem().toString());

                pstmt.executeUpdate();
                JOptionPane.showMessageDialog(null, "Atualizado com sucesso!");

            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Erro ao salvar: Verifique os campos" ,"ERRO", JOptionPane.ERROR_MESSAGE);
            }
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

        bgUsuario = new javax.swing.ButtonGroup();
        pnlAlterarUsuario = new javax.swing.JPanel();
        lblImagemUsuario = new javax.swing.JLabel();
        lblImagemLapis = new javax.swing.JLabel();
        lblNome = new javax.swing.JLabel();
        lblMatricula = new javax.swing.JLabel();
        lblCpf = new javax.swing.JLabel();
        lblTelefone = new javax.swing.JLabel();
        lblSenha = new javax.swing.JLabel();
        lblConfirmaSenha = new javax.swing.JLabel();
        tfNome = new javax.swing.JTextField();
        tfMatricula = new javax.swing.JTextField();
        tfTelefone = new javax.swing.JTextField();
        tfSenha = new javax.swing.JPasswordField();
        tfConfirmaSenha = new javax.swing.JPasswordField();
        tfCpf = new javax.swing.JFormattedTextField();
        btnAlterar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        lblUsuarios = new javax.swing.JLabel();
        jcbUsuarios = new javax.swing.JComboBox<>();
        lblTipoUsuario = new javax.swing.JLabel();
        rbAdministrador = new javax.swing.JRadioButton();
        rbPadrao = new javax.swing.JRadioButton();
        lblFundo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Alterar Usuário");
        setBackground(new java.awt.Color(255, 255, 255));
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        pnlAlterarUsuario.setBackground(new java.awt.Color(255, 255, 255));
        pnlAlterarUsuario.setLayout(null);

        lblImagemUsuario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Ícone Usuário M.png"))); // NOI18N
        pnlAlterarUsuario.add(lblImagemUsuario);
        lblImagemUsuario.setBounds(150, 60, 110, 110);

        lblImagemLapis.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Ícone Lápis P.png"))); // NOI18N
        pnlAlterarUsuario.add(lblImagemLapis);
        lblImagemLapis.setBounds(260, 110, 50, 50);

        lblNome.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblNome.setForeground(new java.awt.Color(255, 255, 255));
        lblNome.setText("Nome *");
        pnlAlterarUsuario.add(lblNome);
        lblNome.setBounds(90, 220, 46, 17);

        lblMatricula.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblMatricula.setForeground(new java.awt.Color(255, 255, 255));
        lblMatricula.setText("Nº Matrícula *");
        pnlAlterarUsuario.add(lblMatricula);
        lblMatricula.setBounds(50, 260, 88, 17);

        lblCpf.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblCpf.setForeground(new java.awt.Color(255, 255, 255));
        lblCpf.setText("CPF *");
        pnlAlterarUsuario.add(lblCpf);
        lblCpf.setBounds(100, 300, 40, 17);

        lblTelefone.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblTelefone.setForeground(new java.awt.Color(255, 255, 255));
        lblTelefone.setText("Telefone");
        pnlAlterarUsuario.add(lblTelefone);
        lblTelefone.setBounds(83, 340, 60, 17);

        lblSenha.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblSenha.setForeground(new java.awt.Color(255, 255, 255));
        lblSenha.setText("Senha");
        pnlAlterarUsuario.add(lblSenha);
        lblSenha.setBounds(100, 380, 38, 17);

        lblConfirmaSenha.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblConfirmaSenha.setForeground(new java.awt.Color(255, 255, 255));
        lblConfirmaSenha.setText("Confirme a senha");
        pnlAlterarUsuario.add(lblConfirmaSenha);
        lblConfirmaSenha.setBounds(30, 420, 110, 17);

        tfNome.setBackground(new java.awt.Color(153, 153, 153));
        tfNome.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        tfNome.setForeground(new java.awt.Color(255, 255, 255));
        tfNome.setCaretColor(new java.awt.Color(255, 255, 255));
        pnlAlterarUsuario.add(tfNome);
        tfNome.setBounds(150, 220, 250, 23);

        tfMatricula.setBackground(new java.awt.Color(153, 153, 153));
        tfMatricula.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        tfMatricula.setForeground(new java.awt.Color(255, 255, 255));
        tfMatricula.setCaretColor(new java.awt.Color(255, 255, 255));
        pnlAlterarUsuario.add(tfMatricula);
        tfMatricula.setBounds(150, 260, 250, 23);

        tfTelefone.setBackground(new java.awt.Color(153, 153, 153));
        tfTelefone.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        tfTelefone.setForeground(new java.awt.Color(255, 255, 255));
        tfTelefone.setCaretColor(new java.awt.Color(255, 255, 255));
        pnlAlterarUsuario.add(tfTelefone);
        tfTelefone.setBounds(150, 340, 250, 23);

        tfSenha.setBackground(new java.awt.Color(153, 153, 153));
        tfSenha.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        tfSenha.setForeground(new java.awt.Color(255, 255, 255));
        tfSenha.setCaretColor(new java.awt.Color(255, 255, 255));
        pnlAlterarUsuario.add(tfSenha);
        tfSenha.setBounds(150, 380, 250, 23);

        tfConfirmaSenha.setBackground(new java.awt.Color(153, 153, 153));
        tfConfirmaSenha.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        tfConfirmaSenha.setForeground(new java.awt.Color(255, 255, 255));
        tfConfirmaSenha.setCaretColor(new java.awt.Color(255, 255, 255));
        pnlAlterarUsuario.add(tfConfirmaSenha);
        tfConfirmaSenha.setBounds(150, 420, 250, 23);

        tfCpf.setBackground(new java.awt.Color(153, 153, 153));
        tfCpf.setForeground(new java.awt.Color(255, 255, 255));
        try {
            tfCpf.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###.###.###-##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        tfCpf.setCaretColor(new java.awt.Color(255, 255, 255));
        tfCpf.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        pnlAlterarUsuario.add(tfCpf);
        tfCpf.setBounds(150, 300, 250, 23);

        btnAlterar.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btnAlterar.setForeground(new java.awt.Color(255, 255, 255));
        btnAlterar.setText("Alterar");
        btnAlterar.setBorderPainted(false);
        btnAlterar.setContentAreaFilled(false);
        btnAlterar.setFocusPainted(false);
        btnAlterar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnAlterarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnAlterarMouseExited(evt);
            }
        });
        btnAlterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAlterarActionPerformed(evt);
            }
        });
        pnlAlterarUsuario.add(btnAlterar);
        btnAlterar.setBounds(230, 500, 120, 30);

        btnCancelar.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btnCancelar.setForeground(new java.awt.Color(255, 255, 255));
        btnCancelar.setText("Cancelar");
        btnCancelar.setBorderPainted(false);
        btnCancelar.setContentAreaFilled(false);
        btnCancelar.setFocusPainted(false);
        btnCancelar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnCancelarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnCancelarMouseExited(evt);
            }
        });
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });
        pnlAlterarUsuario.add(btnCancelar);
        btnCancelar.setBounds(80, 500, 120, 30);

        lblUsuarios.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblUsuarios.setForeground(new java.awt.Color(255, 255, 255));
        lblUsuarios.setText("Selecione a Matrícula");
        pnlAlterarUsuario.add(lblUsuarios);
        lblUsuarios.setBounds(10, 180, 132, 17);

        jcbUsuarios.setBackground(new java.awt.Color(153, 153, 153));
        jcbUsuarios.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jcbUsuarios.setForeground(new java.awt.Color(255, 255, 255));
        jcbUsuarios.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jcbUsuariosMouseClicked(evt);
            }
        });
        jcbUsuarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbUsuariosActionPerformed(evt);
            }
        });
        pnlAlterarUsuario.add(jcbUsuarios);
        jcbUsuarios.setBounds(150, 180, 250, 23);

        lblTipoUsuario.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblTipoUsuario.setForeground(new java.awt.Color(255, 255, 255));
        lblTipoUsuario.setText("Usuário");
        pnlAlterarUsuario.add(lblTipoUsuario);
        lblTipoUsuario.setBounds(90, 460, 47, 17);

        rbAdministrador.setBackground(new java.awt.Color(255, 255, 255));
        bgUsuario.add(rbAdministrador);
        rbAdministrador.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        rbAdministrador.setForeground(new java.awt.Color(255, 255, 255));
        rbAdministrador.setText("Administrador");
        rbAdministrador.setContentAreaFilled(false);
        rbAdministrador.setFocusPainted(false);
        pnlAlterarUsuario.add(rbAdministrador);
        rbAdministrador.setBounds(150, 460, 140, 25);

        rbPadrao.setBackground(new java.awt.Color(255, 255, 255));
        bgUsuario.add(rbPadrao);
        rbPadrao.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        rbPadrao.setForeground(new java.awt.Color(255, 255, 255));
        rbPadrao.setText("Padrão");
        rbPadrao.setContentAreaFilled(false);
        rbPadrao.setFocusPainted(false);
        pnlAlterarUsuario.add(rbPadrao);
        rbPadrao.setBounds(270, 460, 100, 25);

        lblFundo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Fundo Usuario.png"))); // NOI18N
        pnlAlterarUsuario.add(lblFundo);
        lblFundo.setBounds(0, 0, 420, 557);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlAlterarUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 420, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlAlterarUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 557, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAlterarActionPerformed
        // TODO add your handling code here:
        String cpfVazio= tfCpf.getText();
        cpfVazio= cpfVazio.replace("/", "");
        cpfVazio= cpfVazio.replace(".", "");
        cpfVazio= cpfVazio.replace("-", "");
        System.out.println(cpfVazio);
        if(tfNome.getText().equals("") || tfMatricula.getText().equals("") || cpfVazio.trim().length()==0){
            JOptionPane.showMessageDialog(null, "Preencha os campos obrigatórios (campos com *)", "ERRO", JOptionPane.ERROR_MESSAGE);
        }
        else
            this.alterarUsuario();
    }//GEN-LAST:event_btnAlterarActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        // TODO add your handling code here:
    }//GEN-LAST:event_formWindowClosing

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        // TODO add your handling code here:
        this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void jcbUsuariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbUsuariosActionPerformed
        // TODO add your handling code here:
        this.preencheCampos();
    }//GEN-LAST:event_jcbUsuariosActionPerformed

    private void jcbUsuariosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jcbUsuariosMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jcbUsuariosMouseClicked

    private void btnAlterarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAlterarMouseEntered
        // TODO add your handling code here:
        this.entraMouse(btnAlterar);
    }//GEN-LAST:event_btnAlterarMouseEntered

    private void btnAlterarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAlterarMouseExited
        // TODO add your handling code here:
        this.saiMouse(btnAlterar);
    }//GEN-LAST:event_btnAlterarMouseExited

    private void btnCancelarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCancelarMouseEntered
        // TODO add your handling code here:
        this.entraMouse(btnCancelar);
    }//GEN-LAST:event_btnCancelarMouseEntered

    private void btnCancelarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCancelarMouseExited
        // TODO add your handling code here:
        this.saiMouse(btnCancelar);
    }//GEN-LAST:event_btnCancelarMouseExited

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
            java.util.logging.Logger.getLogger(TelaAlteraUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaAlteraUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaAlteraUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaAlteraUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaAlteraUsuario().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup bgUsuario;
    private javax.swing.JButton btnAlterar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JComboBox<String> jcbUsuarios;
    private javax.swing.JLabel lblConfirmaSenha;
    private javax.swing.JLabel lblCpf;
    private javax.swing.JLabel lblFundo;
    private javax.swing.JLabel lblImagemLapis;
    private javax.swing.JLabel lblImagemUsuario;
    private javax.swing.JLabel lblMatricula;
    private javax.swing.JLabel lblNome;
    private javax.swing.JLabel lblSenha;
    private javax.swing.JLabel lblTelefone;
    private javax.swing.JLabel lblTipoUsuario;
    private javax.swing.JLabel lblUsuarios;
    private javax.swing.JPanel pnlAlterarUsuario;
    private javax.swing.JRadioButton rbAdministrador;
    private javax.swing.JRadioButton rbPadrao;
    private javax.swing.JPasswordField tfConfirmaSenha;
    private javax.swing.JFormattedTextField tfCpf;
    private javax.swing.JTextField tfMatricula;
    private javax.swing.JTextField tfNome;
    private javax.swing.JPasswordField tfSenha;
    private javax.swing.JTextField tfTelefone;
    // End of variables declaration//GEN-END:variables
}
