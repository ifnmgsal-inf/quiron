/*
 * Tela para pesquisar pacientes já cadastrados.
 */
package principal;

import bancodedados.MysqlConnect;
import cartaovacina.pnlAlterarCartao;
import com.itextpdf.text.DocumentException;
import fichaatendimento.pnlFichaAtendimento;
import gerenciarpacientes.pnlAlterarPaciente;
import hospital.pnlEncaminhamentoHospital;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import pdf.PdfAnamnese;
import pdf.PdfCartaoVacina;
import pdf.PdfFichaAtendimento;

/**
 *
 * @author Franciele Alves Barbosa e Rogério Costa Negro Rocha
 */
public class PesquisaPaciente extends javax.swing.JDialog {

    Connection conn;
    public static String id;

    /**
     * Creates new form PesquisaPaciente
     * @param parent
     * @param modal
     */
    public PesquisaPaciente(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        try {
            conn = MysqlConnect.connectDB();
            this.preencheTabela();
            this.controlePesquisa();
        } catch (SQLException sqle) {
            JOptionPane.showMessageDialog(null, "Erro ao conectar com o Banco de Dados " /*+ ex.getMessage()*/, "ERRO", JOptionPane.ERROR_MESSAGE);
        }
    }
    public PesquisaPaciente(boolean modal) {
        initComponents();
        try {
            conn = MysqlConnect.connectDB();
            this.preencheTabela();
            this.controlePesquisa();
        } catch (SQLException sqle) {
            JOptionPane.showMessageDialog(null, "Erro ao conectar com o Banco de Dados " /*+ ex.getMessage()*/, "ERRO", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void controlePesquisa() {
        if (TelaPrincipal.controlePesquisa == 3 || TelaPrincipal.controlePesquisa == 1) {
            btnSelecionar.setVisible(true);
            btnAlterar.setVisible(false);
            btnPdf.setVisible(true);
            btnRemover.setVisible(false);
        } else if (TelaPrincipal.controlePesquisa != 0) {
            btnSelecionar.setVisible(true);
            btnAlterar.setVisible(false);
            btnPdf.setVisible(false);
            btnRemover.setVisible(false);
        }
    }

    public void entraMouse(JButton botao) {
        botao.setOpaque(true);
        botao.setContentAreaFilled(true);
        botao.setBorderPainted(true);
        //botao.setBackground(new Color( 255, 255, 255, 150 )); 
        botao.setBackground(Color.LIGHT_GRAY);
    }

    public void saiMouse(JButton botao) {

        botao.setOpaque(false);
        botao.setContentAreaFilled(false);
        botao.setBorderPainted(false);
    }

    public void preencheTabela() {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        DefaultTableModel tabelaPacientes = (DefaultTableModel) tblPacientes.getModel();
        tabelaPacientes.setNumRows(0);
        String qry = "SELECT idPaciente, nome, cpf, dtNascimento FROM pacientes order by nome";

        try {
            pstmt = conn.prepareStatement(qry);
            rs = pstmt.executeQuery(qry);

            while (rs.next()) {

                tabelaPacientes.addRow(new String[]{rs.getString("nome"), rs.getString("cpf"), rs.getString("dtNascimento"), rs.getString("idPaciente")});

            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro: " + ex.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void deletaDasFichas() {
        String qry = "DELETE from fichas WHERE idPaciente= ?";
        id = tblPacientes.getValueAt(tblPacientes.getSelectedRow(), 3).toString();
        PreparedStatement pstmt = null;
        try {
            pstmt = conn.prepareStatement(qry);
            pstmt.setString(1, id);
            pstmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Excluido com sucesso das fichas");
            this.preencheTabela();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir: " + ex.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void deletaDoBD() {

        PreparedStatement pstmt = null;
        if (tblPacientes.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Não há pacientes registrados");
        } else {
            id = tblPacientes.getValueAt(tblPacientes.getSelectedRow(), 3).toString();

            String qry = "DELETE from pacientes WHERE idPaciente= ?";

            try {
                pstmt = conn.prepareStatement(qry);
                pstmt.setString(1, id);
                int op = JOptionPane.showConfirmDialog(null, "Deseja realmente deletar?\nIsso implicara na exclusão da ficha e do cartão de vacinas do paciente", "ATENÇÃO", JOptionPane.YES_OPTION);
                if (op == JOptionPane.YES_OPTION) {
                    //this.deletaDasFichas();
                    pstmt.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Excluido com sucesso");
                }
                this.preencheTabela();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Erro ao excluir: " + ex.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    /*
    public void PesquisaTabela() {
        boolean encontrar = false;
        int tamanhoTabela = tblPacientes.getRowCount();
        if (tamanhoTabela == 0) {
            JOptionPane.showMessageDialog(null, "Não há registros!", "ERRO", JOptionPane.ERROR_MESSAGE);
        } else {
            for (int i = 0; i < tamanhoTabela; i++) {
                if (tblPacientes.getValueAt(i, 1).equals(ftfCpf.getText())) {
                    tblPacientes.setRowSelectionInterval(i, i);
                    encontrar = true;
                }
            }
            if (encontrar == false) {
                JOptionPane.showMessageDialog(null, "Paciente não encontrado", "ERRO", JOptionPane.ERROR_MESSAGE);
                tblPacientes.clearSelection();
            }
        }
    }*/
    public void pesquisaNome() {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        DefaultTableModel tabelaPacientes = (DefaultTableModel) tblPacientes.getModel();
        tabelaPacientes.setNumRows(0);
        String qry = "SELECT idPaciente, nome, cpf, dtNascimento FROM pacientes WHERE nome LIKE ? ";
        try {
            pstmt = conn.prepareStatement(qry);
            pstmt.setString(1, "%" + tfNome.getText() + "%");
            rs = pstmt.executeQuery();
            while (rs.next()) {
                tabelaPacientes.addRow(new String[]{rs.getString("nome"), rs.getString("cpf"), rs.getString("dtNascimento"), rs.getString("idPaciente")});
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro: " + ex.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void pesquisaDtNascimento() {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        DefaultTableModel tabelaPacientes = (DefaultTableModel) tblPacientes.getModel();
        tabelaPacientes.setNumRows(0);
        String qry = "SELECT idPaciente, nome, cpf, dtNascimento FROM pacientes WHERE dtNascimento LIKE ? ";
        try {
            pstmt = conn.prepareStatement(qry);
            pstmt.setString(1, "%" + tfDtNascimento.getText() + "%");
            rs = pstmt.executeQuery();
            while (rs.next()) {
                tabelaPacientes.addRow(new String[]{rs.getString("nome"), rs.getString("cpf"), rs.getString("dtNascimento"), rs.getString("idPaciente")});
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

        pnlPesquisa = new javax.swing.JPanel();
        btnAlterar = new javax.swing.JButton();
        tfDtNascimento = new javax.swing.JTextField();
        tfNome = new javax.swing.JTextField();
        lblDtNascimento = new javax.swing.JLabel();
        lblNome = new javax.swing.JLabel();
        btnPdf = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblPacientes = new javax.swing.JTable();
        btnRemover = new javax.swing.JButton();
        btnSelecionar = new javax.swing.JButton();
        lblImagemFundo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Pesquisas");
        setIconImage(new javax.swing.ImageIcon(getClass().getResource("/imagens/Quiron.png")).getImage());
        setSize(new java.awt.Dimension(0, 0));
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                formKeyPressed(evt);
            }
        });

        pnlPesquisa.setLayout(null);

        btnAlterar.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btnAlterar.setForeground(new java.awt.Color(255, 255, 255));
        btnAlterar.setText("ALTERAR");
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
        pnlPesquisa.add(btnAlterar);
        btnAlterar.setBounds(400, 370, 150, 30);

        tfDtNascimento.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        tfDtNascimento.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tfDtNascimentoKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tfDtNascimentoKeyReleased(evt);
            }
        });
        pnlPesquisa.add(tfDtNascimento);
        tfDtNascimento.setBounds(460, 100, 80, 20);

        tfNome.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        tfNome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfNomeActionPerformed(evt);
            }
        });
        tfNome.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tfNomeKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tfNomeKeyReleased(evt);
            }
        });
        pnlPesquisa.add(tfNome);
        tfNome.setBounds(150, 100, 150, 20);

        lblDtNascimento.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblDtNascimento.setForeground(new java.awt.Color(255, 255, 255));
        lblDtNascimento.setText("DT. NASCIMENTO:");
        pnlPesquisa.add(lblDtNascimento);
        lblDtNascimento.setBounds(330, 100, 130, 17);

        lblNome.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblNome.setForeground(new java.awt.Color(255, 255, 255));
        lblNome.setText("NOME:");
        pnlPesquisa.add(lblNome);
        lblNome.setBounds(90, 100, 60, 17);

        btnPdf.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btnPdf.setForeground(new java.awt.Color(255, 255, 255));
        btnPdf.setText("GERAR PDF");
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
        pnlPesquisa.add(btnPdf);
        btnPdf.setBounds(80, 370, 150, 30);

        tblPacientes.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        tblPacientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nome", "CPF", "Data de Nascimento", "ID_PACIENTE"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblPacientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblPacientesMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblPacientes);
        if (tblPacientes.getColumnModel().getColumnCount() > 0) {
            tblPacientes.getColumnModel().getColumn(3).setMinWidth(0);
            tblPacientes.getColumnModel().getColumn(3).setPreferredWidth(0);
            tblPacientes.getColumnModel().getColumn(3).setMaxWidth(0);
        }

        pnlPesquisa.add(jScrollPane1);
        jScrollPane1.setBounds(80, 150, 470, 150);

        btnRemover.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btnRemover.setForeground(new java.awt.Color(255, 255, 255));
        btnRemover.setText("REMOVER");
        btnRemover.setBorderPainted(false);
        btnRemover.setContentAreaFilled(false);
        btnRemover.setFocusPainted(false);
        btnRemover.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnRemoverMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnRemoverMouseExited(evt);
            }
        });
        btnRemover.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoverActionPerformed(evt);
            }
        });
        pnlPesquisa.add(btnRemover);
        btnRemover.setBounds(240, 370, 150, 30);

        btnSelecionar.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btnSelecionar.setForeground(new java.awt.Color(255, 255, 255));
        btnSelecionar.setText("SELECIONAR");
        btnSelecionar.setBorderPainted(false);
        btnSelecionar.setContentAreaFilled(false);
        btnSelecionar.setFocusPainted(false);
        btnSelecionar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnSelecionarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnSelecionarMouseExited(evt);
            }
        });
        btnSelecionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSelecionarActionPerformed(evt);
            }
        });
        btnSelecionar.setVisible(false);
        pnlPesquisa.add(btnSelecionar);
        btnSelecionar.setBounds(230, 320, 170, 30);

        lblImagemFundo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Fundo Pesquisa.png"))); // NOI18N
        pnlPesquisa.add(lblImagemFundo);
        lblImagemFundo.setBounds(-10, 0, 650, 462);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, 640, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, 460, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnAlterarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAlterarMouseEntered
        // TODO add your handling code here:
        this.entraMouse(btnAlterar);
    }//GEN-LAST:event_btnAlterarMouseEntered

    private void btnAlterarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAlterarMouseExited
        // TODO add your handling code here:
        this.saiMouse(btnAlterar);
    }//GEN-LAST:event_btnAlterarMouseExited

    private void btnRemoverMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnRemoverMouseEntered
        // TODO add your handling code here:
        this.entraMouse(btnRemover);
    }//GEN-LAST:event_btnRemoverMouseEntered

    private void btnRemoverMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnRemoverMouseExited
        // TODO add your handling code here:
        this.saiMouse(btnRemover);
    }//GEN-LAST:event_btnRemoverMouseExited

    private void btnPdfMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnPdfMouseEntered
        // TODO add your handling code here:
        this.entraMouse(btnPdf);
    }//GEN-LAST:event_btnPdfMouseEntered

    private void btnPdfMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnPdfMouseExited
        // TODO add your handling code here:
        this.saiMouse(btnPdf);
    }//GEN-LAST:event_btnPdfMouseExited

    private void btnRemoverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoverActionPerformed
        // TODO add your handling code here:
        try {
            this.deletaDoBD();
        } catch (ArrayIndexOutOfBoundsException ex) {
            JOptionPane.showMessageDialog(null, "Selecione um paciente!", "ERRO", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnRemoverActionPerformed

    private void formKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            tblPacientes.clearSelection();
        }
    }//GEN-LAST:event_formKeyPressed

    private void tblPacientesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblPacientesMouseClicked
        // TODO add your handling code here:
        //ftfCpf.setText(tblPacientes.getValueAt(tblPacientes.getSelectedRow(), 1).toString());
    }//GEN-LAST:event_tblPacientesMouseClicked

    private void btnAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAlterarActionPerformed
        // TODO add your handling code here:
        try {
            if (tblPacientes.getRowCount() == 0) {
                JOptionPane.showMessageDialog(null, "Não há pacientes registrados");
            } else {
                id = tblPacientes.getValueAt(tblPacientes.getSelectedRow(), 3).toString();
                TelaPrincipal.lblTextoAtual.setText("Alterar Paciente");
                pnlAlterarPaciente alterarPaciente = new pnlAlterarPaciente();
                TelaPrincipal.abrirJPainel(alterarPaciente);
                this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
                //TelaAlterarPaciente alteraPaciente = new TelaAlterarPaciente();
                //alteraPaciente.setVisible(true);

            }
        } catch (ArrayIndexOutOfBoundsException ex) {
            JOptionPane.showMessageDialog(null, "Selecione um paciente!", "ERRO", JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_btnAlterarActionPerformed

    private void btnSelecionarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSelecionarMouseEntered
        // TODO add your handling code here:
        this.entraMouse(btnSelecionar);
    }//GEN-LAST:event_btnSelecionarMouseEntered

    private void btnSelecionarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSelecionarMouseExited
        // TODO add your handling code here:
        this.saiMouse(btnSelecionar);
    }//GEN-LAST:event_btnSelecionarMouseExited

    private void btnSelecionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSelecionarActionPerformed
        // TODO add your handling code here:
        try {
            if (tblPacientes.getRowCount() == 0) {
                JOptionPane.showMessageDialog(null, "Não há pacientes registrados");
            } else {
                this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
                id = tblPacientes.getValueAt(tblPacientes.getSelectedRow(), 3).toString();
                switch (TelaPrincipal.controlePesquisa) {
                    case 1:
                        TelaPrincipal.lblTextoAtual.setText("Ficha de Atendimento");
                        pnlFichaAtendimento pnlFichaAtendimento = new pnlFichaAtendimento();
                        TelaPrincipal.abrirJPainel(pnlFichaAtendimento);
                        break;
                    case 2:
                        pnlEncaminhamentoHospital.tfPaciente.setText(tblPacientes.getValueAt(tblPacientes.getSelectedRow(), 0).toString());
                        pnlEncaminhamentoHospital.tfCpf.setText(tblPacientes.getValueAt(tblPacientes.getSelectedRow(), 1).toString());
                        pnlEncaminhamentoHospital.tfIdPaciente.setText(id);
                        this.dispose();
                        break;
                    case 3:
                        TelaPrincipal.lblTextoAtual.setText("Gerenciar Cartão");
                        pnlAlterarCartao alterarCartao = new pnlAlterarCartao();
                        TelaPrincipal.abrirJPainel(alterarCartao);
                        break;
                    default:
                        JOptionPane.showMessageDialog(null, "ERRO", "ERRO", JOptionPane.ERROR_MESSAGE);
                        break;
                }
            }
        } catch (ArrayIndexOutOfBoundsException ex) {
            JOptionPane.showMessageDialog(null, "Selecione um paciente!", "ERRO", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnSelecionarActionPerformed

    private void btnPdfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPdfActionPerformed
        // TODO add your handling code here:

        try {
            if (tblPacientes.getRowCount() == 0) {
                JOptionPane.showMessageDialog(null, "Não há pacientes registrados");
            } else {
                switch (TelaPrincipal.controlePesquisa) {
                    case 0:
                        int idSelecionado0 = Integer.parseInt(tblPacientes.getValueAt(tblPacientes.getSelectedRow(), 3).toString());
                        PdfAnamnese meuPdf = new PdfAnamnese();
                        meuPdf.pdfAnamnese(idSelecionado0);
                        break;
                    case 1:
                        //int idSelecionado1 = Integer.parseInt(tblPacientes.getValueAt(tblPacientes.getSelectedRow(), 3).toString());
                        PdfFichaAtendimento meuPdfFicha = new PdfFichaAtendimento();
                        meuPdfFicha.pdfFichaAtendimento(tblPacientes.getValueAt(tblPacientes.getSelectedRow(), 3).toString());
                        //meuPdf.pdfFicha(tblPacientes.getValueAt(tblPacientes.getSelectedRow(), 3).toString());
                        break;
                    case 3:
                        int idSelecionado3 = Integer.parseInt(tblPacientes.getValueAt(tblPacientes.getSelectedRow(), 3).toString());
                        PdfCartaoVacina meuCartaoVacina = new PdfCartaoVacina();
                        meuCartaoVacina.pdfCartao(idSelecionado3);
                        break;
                    default:
                        JOptionPane.showMessageDialog(null, "Erro no PDF", "ERRO", JOptionPane.ERROR_MESSAGE);
                        break;
                }
            }
        } catch (ArrayIndexOutOfBoundsException ex) {
            JOptionPane.showMessageDialog(null, "Selecione um paciente!", "ERRO", JOptionPane.ERROR_MESSAGE);
        } catch (DocumentException ex) {
            Logger.getLogger(PesquisaPaciente.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(PesquisaPaciente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnPdfActionPerformed

    private void tfNomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfNomeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfNomeActionPerformed

    private void tfNomeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfNomeKeyPressed
        // TODO add your handling code here:

    }//GEN-LAST:event_tfNomeKeyPressed

    private void tfDtNascimentoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfDtNascimentoKeyPressed
        // TODO add your handling code here:

    }//GEN-LAST:event_tfDtNascimentoKeyPressed

    private void tfDtNascimentoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfDtNascimentoKeyReleased
        // TODO add your handling code here:
        this.pesquisaDtNascimento();
    }//GEN-LAST:event_tfDtNascimentoKeyReleased

    private void tfNomeKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfNomeKeyReleased
        // TODO add your handling code here:
        this.pesquisaNome();
    }//GEN-LAST:event_tfNomeKeyReleased

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
            java.util.logging.Logger.getLogger(PesquisaPaciente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PesquisaPaciente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PesquisaPaciente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PesquisaPaciente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                PesquisaPaciente dialog = new PesquisaPaciente(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAlterar;
    private javax.swing.JButton btnPdf;
    private javax.swing.JButton btnRemover;
    private javax.swing.JButton btnSelecionar;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblDtNascimento;
    private javax.swing.JLabel lblImagemFundo;
    private javax.swing.JLabel lblNome;
    private javax.swing.JPanel pnlPesquisa;
    private javax.swing.JTable tblPacientes;
    private javax.swing.JTextField tfDtNascimento;
    private javax.swing.JTextField tfNome;
    // End of variables declaration//GEN-END:variables
}
