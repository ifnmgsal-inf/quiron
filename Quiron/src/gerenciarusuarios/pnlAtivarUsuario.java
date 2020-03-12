/*
 * Tela para ativar usuários (servidores) já cadastrados.
 */
package gerenciarusuarios;

import bancodedados.MysqlConnect;
import java.awt.Color;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import principal.TelaPrincipal;

/**
 *
 * @author Franciele Alves Barbosa e Rogério Costa Negro Rocha
 */
public class pnlAtivarUsuario extends javax.swing.JPanel {

    Connection conn = null;
    public String numMatricula;
    /**
     * Creates new form pnlAtivarUsuario
     */
    public pnlAtivarUsuario() {
        initComponents();
        try {
            conn = MysqlConnect.connectDB();
            this.caixaUsuario();
        } catch (SQLException sqle) {
            JOptionPane.showMessageDialog(null, "Erro ao conectar com o Banco de Dados " /*+ ex.getMessage()*/, "ERRO", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void sair() {
        int op = JOptionPane.showConfirmDialog(null, "Deseja realmente sair?", "ATENÇÃO", JOptionPane.YES_OPTION);
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

    public void usuarioSelecionado() {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        numMatricula = (String) jcbUsuarios.getSelectedItem();
        String qry = "SELECT * FROM usuarios WHERE matricula= ?";

        try {
            pstmt = conn.prepareStatement(qry);
            pstmt.setString(1, numMatricula);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                tfUsuarioSelecionado.setText(rs.getString("nome"));
                if (rs.getString("ativado").equals("1")) {
                    tfStatus.setText("Ativado");
                } else {
                    tfStatus.setText("Inativado");
                }
            }

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

        pnlAtivarUsuario = new javax.swing.JPanel();
        jcbUsuarios = new javax.swing.JComboBox<>();
        btnInativar = new javax.swing.JButton();
        lblImagemUsuario = new javax.swing.JLabel();
        lblMatricula = new javax.swing.JLabel();
        lblUsuarioSelecionado = new javax.swing.JLabel();
        btnAtivar = new javax.swing.JButton();
        lblStatus = new javax.swing.JLabel();
        tfUsuarioSelecionado = new javax.swing.JTextField();
        tfStatus = new javax.swing.JTextField();
        sSeparador1 = new javax.swing.JSeparator();
        sSeparador2 = new javax.swing.JSeparator();
        btnSair = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));
        setMaximumSize(new java.awt.Dimension(1000, 550));
        setMinimumSize(new java.awt.Dimension(1000, 550));
        setPreferredSize(new java.awt.Dimension(1000, 550));

        pnlAtivarUsuario.setBackground(new java.awt.Color(255, 255, 255));

        jcbUsuarios.setBackground(new java.awt.Color(153, 153, 153));
        jcbUsuarios.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jcbUsuarios.setForeground(new java.awt.Color(255, 255, 255));
        jcbUsuarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbUsuariosActionPerformed(evt);
            }
        });

        btnInativar.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btnInativar.setForeground(new java.awt.Color(102, 102, 102));
        btnInativar.setText("Inativar");
        btnInativar.setBorderPainted(false);
        btnInativar.setContentAreaFilled(false);
        btnInativar.setFocusPainted(false);
        btnInativar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnInativarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnInativarMouseExited(evt);
            }
        });
        btnInativar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInativarActionPerformed(evt);
            }
        });

        lblImagemUsuario.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblImagemUsuario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Icon Face Id.png"))); // NOI18N

        lblMatricula.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        lblMatricula.setForeground(new java.awt.Color(102, 102, 102));
        lblMatricula.setText("Selecione a matricula");

        lblUsuarioSelecionado.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        lblUsuarioSelecionado.setForeground(new java.awt.Color(102, 102, 102));
        lblUsuarioSelecionado.setText("Usuário Selecionado");

        btnAtivar.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btnAtivar.setForeground(new java.awt.Color(102, 102, 102));
        btnAtivar.setText("Ativar");
        btnAtivar.setBorderPainted(false);
        btnAtivar.setContentAreaFilled(false);
        btnAtivar.setFocusPainted(false);
        btnAtivar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnAtivarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnAtivarMouseExited(evt);
            }
        });
        btnAtivar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAtivarActionPerformed(evt);
            }
        });

        lblStatus.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        lblStatus.setForeground(new java.awt.Color(102, 102, 102));
        lblStatus.setText("Status");

        tfUsuarioSelecionado.setEditable(false);
        tfUsuarioSelecionado.setBackground(new java.awt.Color(255, 255, 255));
        tfUsuarioSelecionado.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        tfUsuarioSelecionado.setForeground(new java.awt.Color(102, 102, 102));
        tfUsuarioSelecionado.setBorder(null);

        tfStatus.setEditable(false);
        tfStatus.setBackground(new java.awt.Color(255, 255, 255));
        tfStatus.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        tfStatus.setForeground(new java.awt.Color(102, 102, 102));
        tfStatus.setBorder(null);

        sSeparador1.setForeground(new java.awt.Color(51, 51, 51));

        sSeparador2.setForeground(new java.awt.Color(51, 51, 51));

        btnSair.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btnSair.setForeground(new java.awt.Color(102, 102, 102));
        btnSair.setText("Sair");
        btnSair.setBorderPainted(false);
        btnSair.setContentAreaFilled(false);
        btnSair.setFocusPainted(false);
        btnSair.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnSairMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnSairMouseExited(evt);
            }
        });
        btnSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSairActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlAtivarUsuarioLayout = new javax.swing.GroupLayout(pnlAtivarUsuario);
        pnlAtivarUsuario.setLayout(pnlAtivarUsuarioLayout);
        pnlAtivarUsuarioLayout.setHorizontalGroup(
            pnlAtivarUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlAtivarUsuarioLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlAtivarUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblImagemUsuario, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pnlAtivarUsuarioLayout.createSequentialGroup()
                        .addGroup(pnlAtivarUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(pnlAtivarUsuarioLayout.createSequentialGroup()
                                .addGroup(pnlAtivarUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(lblUsuarioSelecionado)
                                    .addComponent(lblMatricula))
                                .addGap(18, 18, 18)
                                .addGroup(pnlAtivarUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jcbUsuarios, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(tfUsuarioSelecionado)
                                    .addComponent(sSeparador1, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(pnlAtivarUsuarioLayout.createSequentialGroup()
                                .addComponent(lblStatus)
                                .addGap(18, 18, 18)
                                .addGroup(pnlAtivarUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(sSeparador2, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(tfStatus, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlAtivarUsuarioLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnSair)
                .addGap(18, 18, 18)
                .addComponent(btnInativar)
                .addGap(18, 18, 18)
                .addComponent(btnAtivar)
                .addGap(73, 73, 73))
        );
        pnlAtivarUsuarioLayout.setVerticalGroup(
            pnlAtivarUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlAtivarUsuarioLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblImagemUsuario)
                .addGap(18, 18, 18)
                .addGroup(pnlAtivarUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblMatricula)
                    .addComponent(jcbUsuarios, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnlAtivarUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblUsuarioSelecionado)
                    .addComponent(tfUsuarioSelecionado, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sSeparador1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(pnlAtivarUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblStatus))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sSeparador2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(pnlAtivarUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnInativar)
                    .addComponent(btnAtivar, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSair))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(290, Short.MAX_VALUE)
                .addComponent(pnlAtivarUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(290, 290, 290))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(113, Short.MAX_VALUE)
                .addComponent(pnlAtivarUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(112, 112, 112))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jcbUsuariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbUsuariosActionPerformed
        // TODO add your handling code here:
        this.usuarioSelecionado();
    }//GEN-LAST:event_jcbUsuariosActionPerformed

    private void btnInativarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnInativarMouseEntered
        // TODO add your handling code here:
        this.entraMouse(btnInativar);
    }//GEN-LAST:event_btnInativarMouseEntered

    private void btnInativarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnInativarMouseExited
        // TODO add your handling code here:
        this.saiMouse(btnInativar);
    }//GEN-LAST:event_btnInativarMouseExited

    private void btnInativarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInativarActionPerformed
        // TODO add your handling code here:
        numMatricula = (String) jcbUsuarios.getSelectedItem();
        if (jcbUsuarios.getItemCount() <= 1) {
            JOptionPane.showMessageDialog(this, "Só há um usuário registrado");
        } else {
            int op = JOptionPane.showConfirmDialog(null, "Deseja realmente inativar?", "ATENÇÃO", JOptionPane.YES_OPTION);
            if (op == JOptionPane.YES_OPTION) {

                PreparedStatement pstmt = null;
                //String qry = "DELETE FROM usuarios WHERE matricula= ?";
                String qry = "UPDATE usuarios SET ativado= ? WHERE matricula= ?";

                try {
                    pstmt = conn.prepareStatement(qry);
                    pstmt.setInt(1, 0);
                    pstmt.setString(2, numMatricula);
                    pstmt.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Inativado com sucesso!");
                    this.caixaUsuario();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Erro ao inativar: " + ex.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }//GEN-LAST:event_btnInativarActionPerformed

    private void btnAtivarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAtivarMouseEntered
        // TODO add your handling code here:
        this.entraMouse(btnAtivar);
    }//GEN-LAST:event_btnAtivarMouseEntered

    private void btnAtivarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAtivarMouseExited
        // TODO add your handling code here:
        this.saiMouse(btnAtivar);
    }//GEN-LAST:event_btnAtivarMouseExited

    private void btnAtivarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAtivarActionPerformed
        // TODO add your handling code here:
        PreparedStatement pstmt = null;
        //String qry = "DELETE FROM usuarios WHERE matricula= ?";
        String qry = "UPDATE usuarios SET ativado= ? WHERE matricula= ?";

        try {
            pstmt = conn.prepareStatement(qry);
            pstmt.setInt(1, 1);
            pstmt.setString(2, numMatricula);
            pstmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Ativado com sucesso!");
            this.caixaUsuario();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao ativar: " + ex);
        }
    }//GEN-LAST:event_btnAtivarActionPerformed

    private void btnSairMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSairMouseEntered
        // TODO add your handling code here:
        this.entraMouse(btnSair);
    }//GEN-LAST:event_btnSairMouseEntered

    private void btnSairMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSairMouseExited
        // TODO add your handling code here:
        this.saiMouse(btnSair);
    }//GEN-LAST:event_btnSairMouseExited

    private void btnSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSairActionPerformed
        // TODO add your handling code here:
        this.sair();
    }//GEN-LAST:event_btnSairActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAtivar;
    private javax.swing.JButton btnInativar;
    private javax.swing.JButton btnSair;
    private javax.swing.JComboBox<String> jcbUsuarios;
    private javax.swing.JLabel lblImagemUsuario;
    private javax.swing.JLabel lblMatricula;
    private javax.swing.JLabel lblStatus;
    private javax.swing.JLabel lblUsuarioSelecionado;
    private javax.swing.JPanel pnlAtivarUsuario;
    private javax.swing.JSeparator sSeparador1;
    private javax.swing.JSeparator sSeparador2;
    private javax.swing.JTextField tfStatus;
    private javax.swing.JTextField tfUsuarioSelecionado;
    // End of variables declaration//GEN-END:variables
}