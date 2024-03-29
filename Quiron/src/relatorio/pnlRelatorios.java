/*
 * Tela para gerar relatório da quantidade de encaminhamentos para o hospital
 * dentro do intervalo de tempo desejado pelo usuário (servidor)
 */
package relatorio;

import bancodedados.MysqlConnect;
import fichaatendimento.PnlFichaAtendimento;
import java.awt.Color;
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
import menssagensalerta.MinhasMenssagens;
import principal.TelaPrincipal;

/**
 *
 * @author Franciele Alves Barbosa e Rogério Costa Negro Rocha
 */
public class pnlRelatorios extends javax.swing.JPanel {

    Connection conn;
    /**
     * Creates new form pnlRelatorios
     */
    public pnlRelatorios() {
        initComponents();
        try {
            conn = MysqlConnect.connectDB();
        } catch (SQLException sqle) {
            //JOptionPane.showMessageDialog(null, "Erro ao conectar com o Banco de Dados " /*+ ex.getMessage()*/, "ERRO", JOptionPane.ERROR_MESSAGE);
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
            Logger.getLogger(PnlFichaAtendimento.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(PnlFichaAtendimento.class.getName()).log(Level.SEVERE, null, ex);
        }
        return reportDate;
    }
    
    public void calculaEncaminhamentos(){
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        String qry = "SELECT COUNT(idEncaminhamentos) FROM encaminhamentosHospital WHERE data >= ? AND data <= ?";

        try {
            pstmt = conn.prepareStatement(qry);
            pstmt.setString(1, this.getDatString(tfDtInicial.getText()));
            pstmt.setString(2, this.getDatString(tfDtFinal.getText()));

            rs = pstmt.executeQuery();

            while (rs.next()) {
                tfEncaminhamentos.setText(rs.getString("COUNT(idEncaminhamentos)"));
            }
        } catch (SQLException ex) {
            //JOptionPane.showMessageDialog(null, "Erro: " + ex.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
            MinhasMenssagens.chamarMenssagemErro(ex.getMessage());
        }
    }

    public void calculaAtendimentos() {
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        String qry = "SELECT COUNT(idFichas) FROM fichas WHERE data >= ? AND data <= ?";

        try {
            pstmt = conn.prepareStatement(qry);
            pstmt.setString(1, this.getDatString(tfDtInicial.getText()));
            pstmt.setString(2, this.getDatString(tfDtFinal.getText()));

            rs = pstmt.executeQuery();

            while (rs.next()) {
                tfConsultas.setText(rs.getString("COUNT(idFichas)"));
            }
        } catch (SQLException ex) {
            //JOptionPane.showMessageDialog(null, "Erro: " + ex.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
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

        pnlRelatorio = new javax.swing.JPanel();
        btnConsultar = new javax.swing.JButton();
        btnPdf = new javax.swing.JButton();
        lblDtInicial = new javax.swing.JLabel();
        lblDtFinal = new javax.swing.JLabel();
        lblNumero = new javax.swing.JLabel();
        tfConsultas = new javax.swing.JTextField();
        tfDtInicial = new javax.swing.JFormattedTextField();
        tfDtFinal = new javax.swing.JFormattedTextField();
        jLabel1 = new javax.swing.JLabel();
        tfEncaminhamentos = new javax.swing.JTextField();
        btnSair = new javax.swing.JButton();
        sSeparador1 = new javax.swing.JSeparator();
        sSeparador2 = new javax.swing.JSeparator();
        sSeparador3 = new javax.swing.JSeparator();
        sSeparador4 = new javax.swing.JSeparator();
        sSeparador5 = new javax.swing.JSeparator();
        pnlLateral = new javax.swing.JPanel();
        lblLateral = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setMaximumSize(new java.awt.Dimension(1000, 550));
        setMinimumSize(new java.awt.Dimension(1000, 550));

        pnlRelatorio.setBackground(new java.awt.Color(255, 255, 255));

        btnConsultar.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btnConsultar.setForeground(new java.awt.Color(102, 102, 102));
        btnConsultar.setText("Consultar");
        btnConsultar.setBorderPainted(false);
        btnConsultar.setContentAreaFilled(false);
        btnConsultar.setFocusPainted(false);
        btnConsultar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnConsultarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnConsultarMouseExited(evt);
            }
        });
        btnConsultar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConsultarActionPerformed(evt);
            }
        });

        btnPdf.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btnPdf.setForeground(new java.awt.Color(102, 102, 102));
        btnPdf.setText("Gerar PDF");
        btnPdf.setBorderPainted(false);
        btnPdf.setContentAreaFilled(false);
        btnPdf.setFocusPainted(false);
        btnPdf.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnPdfMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnPdfMouseExited(evt);
            }
        });
        btnPdf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPdfActionPerformed(evt);
            }
        });

        lblDtInicial.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblDtInicial.setForeground(new java.awt.Color(102, 102, 102));
        lblDtInicial.setText("DATA INICIAL");

        lblDtFinal.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblDtFinal.setForeground(new java.awt.Color(102, 102, 102));
        lblDtFinal.setText("DATA FINAL");

        lblNumero.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblNumero.setForeground(new java.awt.Color(102, 102, 102));
        lblNumero.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblNumero.setText("NÚMERO DE CONSULTAS REALIZADAS");

        tfConsultas.setEditable(false);
        tfConsultas.setBackground(new java.awt.Color(255, 255, 255));
        tfConsultas.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        tfConsultas.setBorder(null);

        tfDtInicial.setBorder(null);
        try {
            tfDtInicial.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        tfDtInicial.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N

        tfDtFinal.setBorder(null);
        try {
            tfDtFinal.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        tfDtFinal.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(102, 102, 102));
        jLabel1.setText("NÚMERO DE ENCAMINHAMENTOS AO HOSPITAL");

        tfEncaminhamentos.setEditable(false);
        tfEncaminhamentos.setBackground(new java.awt.Color(255, 255, 255));
        tfEncaminhamentos.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        tfEncaminhamentos.setBorder(null);

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

        sSeparador1.setForeground(new java.awt.Color(51, 51, 51));

        sSeparador2.setForeground(new java.awt.Color(51, 51, 51));

        sSeparador3.setForeground(new java.awt.Color(204, 204, 204));

        sSeparador4.setForeground(new java.awt.Color(204, 204, 204));

        sSeparador5.setForeground(new java.awt.Color(204, 204, 204));

        javax.swing.GroupLayout pnlRelatorioLayout = new javax.swing.GroupLayout(pnlRelatorio);
        pnlRelatorio.setLayout(pnlRelatorioLayout);
        pnlRelatorioLayout.setHorizontalGroup(
            pnlRelatorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlRelatorioLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlRelatorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlRelatorioLayout.createSequentialGroup()
                        .addGroup(pnlRelatorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlRelatorioLayout.createSequentialGroup()
                                .addGroup(pnlRelatorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(lblDtInicial, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                                    .addComponent(tfDtInicial)
                                    .addComponent(sSeparador1))
                                .addGap(18, 18, 18)
                                .addGroup(pnlRelatorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(tfDtFinal)
                                    .addComponent(lblDtFinal, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                                    .addComponent(sSeparador2)))
                            .addGroup(pnlRelatorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(sSeparador5, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(sSeparador4)
                                .addComponent(tfEncaminhamentos)
                                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 340, Short.MAX_VALUE)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(pnlRelatorioLayout.createSequentialGroup()
                        .addGroup(pnlRelatorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(lblNumero, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 340, Short.MAX_VALUE)
                            .addComponent(tfConsultas, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(sSeparador3, javax.swing.GroupLayout.Alignment.LEADING))
                        .addGap(0, 0, Short.MAX_VALUE))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlRelatorioLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnSair)
                .addGap(18, 18, 18)
                .addComponent(btnPdf)
                .addGap(18, 18, 18)
                .addComponent(btnConsultar)
                .addContainerGap())
        );
        pnlRelatorioLayout.setVerticalGroup(
            pnlRelatorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlRelatorioLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlRelatorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDtInicial, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblDtFinal, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlRelatorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfDtInicial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfDtFinal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlRelatorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(sSeparador1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sSeparador2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(sSeparador5, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblNumero)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tfConsultas, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sSeparador3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(tfEncaminhamentos, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sSeparador4, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(pnlRelatorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnConsultar)
                    .addComponent(btnPdf)
                    .addComponent(btnSair))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnPdf.setVisible(false);

        pnlLateral.setBackground(new java.awt.Color(255, 255, 255));

        lblLateral.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblLateral.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Quiron_lateral.png"))); // NOI18N

        javax.swing.GroupLayout pnlLateralLayout = new javax.swing.GroupLayout(pnlLateral);
        pnlLateral.setLayout(pnlLateralLayout);
        pnlLateralLayout.setHorizontalGroup(
            pnlLateralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblLateral, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        pnlLateralLayout.setVerticalGroup(
            pnlLateralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblLateral, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(pnlLateral, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlRelatorio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(pnlRelatorio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlLateral, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnConsultarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnConsultarMouseEntered
        // TODO add your handling code here:
        this.entraMouse(btnConsultar);
    }//GEN-LAST:event_btnConsultarMouseEntered

    private void btnConsultarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnConsultarMouseExited
        // TODO add your handling code here:
        this.saiMouse(btnConsultar);
    }//GEN-LAST:event_btnConsultarMouseExited

    private void btnConsultarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConsultarActionPerformed
        // TODO add your handling code here:
        if(tfDtInicial.getText().replace("/", "").trim().length()==0 || tfDtFinal.getText().replace("/", "").trim().length()==0){
            //JOptionPane.showMessageDialog(null, "Preencha a data inicial e a final", "ERRO", JOptionPane.ERROR_MESSAGE);
            MinhasMenssagens.chamarMenssagemErro("Preencha os campos Data Inicial e Data Final!");
        }
        else{
            this.calculaAtendimentos();
            this.calculaEncaminhamentos();
        }
    }//GEN-LAST:event_btnConsultarActionPerformed

    private void btnPdfMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnPdfMouseEntered
        // TODO add your handling code here:
        this.entraMouse(btnPdf);
    }//GEN-LAST:event_btnPdfMouseEntered

    private void btnPdfMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnPdfMouseExited
        // TODO add your handling code here:
        this.saiMouse(btnPdf);
    }//GEN-LAST:event_btnPdfMouseExited

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

    private void btnPdfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPdfActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnPdfActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnConsultar;
    private javax.swing.JButton btnPdf;
    private javax.swing.JButton btnSair;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel lblDtFinal;
    private javax.swing.JLabel lblDtInicial;
    private javax.swing.JLabel lblLateral;
    private javax.swing.JLabel lblNumero;
    private javax.swing.JPanel pnlLateral;
    private javax.swing.JPanel pnlRelatorio;
    private javax.swing.JSeparator sSeparador1;
    private javax.swing.JSeparator sSeparador2;
    private javax.swing.JSeparator sSeparador3;
    private javax.swing.JSeparator sSeparador4;
    private javax.swing.JSeparator sSeparador5;
    private javax.swing.JTextField tfConsultas;
    private javax.swing.JFormattedTextField tfDtFinal;
    private javax.swing.JFormattedTextField tfDtInicial;
    private javax.swing.JTextField tfEncaminhamentos;
    // End of variables declaration//GEN-END:variables
}
