/*
 * Tela para registrar se algum paciente foi encaminhado para o hospital
 */
package hospital;

import bancodedados.MysqlConnect;
import fichaatendimento.pnlFichaAtendimento;
//import fichaatendimento.FichasAtendimento;
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
import javax.swing.table.DefaultTableModel;
import principal.PesquisaPaciente;
import principal.TelaPrincipal;

/**
 *
 * @author Franciele Alves Barbosa e Rogério Costa Negro Rocha
 */
public class pnlEncaminhamentoHospital extends javax.swing.JPanel {

    Connection conn = null;
    /**
     * Creates new form pnlEncaminhamentoHospital
     */
    public pnlEncaminhamentoHospital() {
        initComponents();
        try {
            conn = MysqlConnect.connectDB();
            this.preencheTabela();
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

    public void preencherCampoNomeCpf() {
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        String qry = "SELECT nome, cpf, idPaciente FROM pacientes WHERE idPaciente= ?";

        try {
            pstmt = conn.prepareStatement(qry);

            pstmt.setString(1, PesquisaPaciente.id);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                tfPaciente.setText(rs.getString("nome"));
                tfCpf.setText(rs.getString("cpf"));
                tfIdPaciente.setText(rs.getString("idPaciente"));
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro: " + ex.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void preencheTabela() {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        DefaultTableModel tabelaFichas = (DefaultTableModel) tblPacientes.getModel();
        tabelaFichas.setNumRows(0);
        String qry = "SELECT data, nomePaciente, cpfPaciente, idPaciente, idEncaminhamentos"
                + " FROM encaminhamentosHospital";

        try {
            pstmt = conn.prepareStatement(qry);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                tabelaFichas.addRow(new String[]{getDatDate((rs.getString("data"))), rs.getString("nomePaciente"), rs.getString("cpfPaciente"), rs.getString("idEncaminhamentos"), rs.getString("idPaciente")});
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro: " + ex.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void liberarCampos() {
        ftfData.setEnabled(true);
    }

    public void travarCampos() {
        ftfData.setEnabled(false);
    }

    public void preencherCampos() {
        tfPaciente.setText(tblPacientes.getValueAt(tblPacientes.getSelectedRow(), 1).toString());
        tfCpf.setText(tblPacientes.getValueAt(tblPacientes.getSelectedRow(), 2).toString());
        tfIdPaciente.setText(tblPacientes.getValueAt(tblPacientes.getSelectedRow(), 3).toString());
        ftfData.setText(tblPacientes.getValueAt(tblPacientes.getSelectedRow(), 0).toString());
    }

    public void limpaCampos() {
        tfPaciente.setText("");
        ftfData.setText("");
        tfCpf.setText("");
        tfIdPaciente.setText("");
        tblPacientes.clearSelection();
    }

    public void resetBotoes() {
        btnCancelar.setVisible(false);
        btnSalvar.setVisible(false);
        btnAdicionar.setVisible(false);

        btnRemover.setVisible(true);
        btnAlterar.setVisible(true);
        btnNovoEncaminhamento.setVisible(true);
        tblPacientes.setEnabled(true);
        btnProcurar.setEnabled(false);

        this.travarCampos();
        this.limpaCampos();
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
            Logger.getLogger(pnlFichaAtendimento.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(pnlFichaAtendimento.class.getName()).log(Level.SEVERE, null, ex);
        }
        //String reportDate = df.format(enteredDate);
        return reportDate;
    }

    public void inserirBD() {
        PreparedStatement pstmt = null;
        String qry = "INSERT INTO encaminhamentosHospital(data, nomePaciente, cpfPaciente, idPaciente) VALUES(?,?,?,?)";

        try {
            pstmt = conn.prepareStatement(qry);
            pstmt.setString(1, getDatString(ftfData.getText()));
            pstmt.setString(2, tfPaciente.getText());
            pstmt.setString(3, tfCpf.getText());
            pstmt.setString(4, tfIdPaciente.getText());

            pstmt.executeUpdate();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar: Data Inválida" /*+ ex.getMessage()*/, "ERRO", JOptionPane.ERROR_MESSAGE);
        }
        this.preencheTabela();
        this.travarCampos();
        this.resetBotoes();
    }
    
    public void alterarEncaminhamento(){
        PreparedStatement pstmt = null;
        /*
        String qry = "UPDATE encaminhamentosHospital SET data= ?, nomePaciente=?,"
                + " cpfPaciente= ? WHERE idEncaminhamentos= ?";
*/
        String qry = "UPDATE encaminhamentosHospital SET data= ? WHERE idEncaminhamentos= ?";
        try {
            pstmt = conn.prepareStatement(qry);
            pstmt.setString(1, this.getDatString(ftfData.getText()));
            //pstmt.setString(2, tfPaciente.getText());
            //pstmt.setString(3, tfCpf.getText());
            pstmt.setString(2, (tblPacientes.getValueAt(tblPacientes.getSelectedRow(), 3).toString()));

            pstmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Atualizado com sucesso!");

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar: " + ex.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
        }
        this.preencheTabela();
        this.travarCampos();
        this.limpaCampos();
        this.resetBotoes();
    }
    
    public void removerEncaminhamento(){
        PreparedStatement pstmt = null;
        String qry = "DELETE FROM encaminhamentosHospital WHERE idEncaminhamentos= ?";

        try {
            pstmt = conn.prepareStatement(qry);
            pstmt.setString(1, (tblPacientes.getValueAt(tblPacientes.getSelectedRow(), 3).toString()));

            pstmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Excluido com sucesso!");
            this.preencheTabela();
            this.resetBotoes();
            this.limpaCampos();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir: " + ex.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
        } catch (ArrayIndexOutOfBoundsException ex) {
            JOptionPane.showMessageDialog(null, "Selecione uma linha!", "ERRO", JOptionPane.ERROR_MESSAGE);
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

        pnlLateral = new javax.swing.JPanel();
        lblLateral = new javax.swing.JLabel();
        pnlCursos = new javax.swing.JPanel();
        lblPaciente = new javax.swing.JLabel();
        lblData = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblPacientes = new javax.swing.JTable();
        btnAdicionar = new javax.swing.JButton();
        btnRemover = new javax.swing.JButton();
        btnAlterar = new javax.swing.JButton();
        btnNovoEncaminhamento = new javax.swing.JButton();
        btnSalvar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        tfPaciente = new javax.swing.JTextField();
        ftfData = new javax.swing.JFormattedTextField();
        btnProcurar = new javax.swing.JButton();
        tfCpf = new javax.swing.JTextField();
        lblCpf = new javax.swing.JLabel();
        lblIdPaciente = new javax.swing.JLabel();
        tfIdPaciente = new javax.swing.JTextField();
        btnSair = new javax.swing.JButton();
        sSeparador1 = new javax.swing.JSeparator();
        sSeparador2 = new javax.swing.JSeparator();
        sSeparador3 = new javax.swing.JSeparator();

        setBackground(new java.awt.Color(255, 255, 255));
        setMaximumSize(new java.awt.Dimension(1000, 550));
        setMinimumSize(new java.awt.Dimension(1000, 550));
        setPreferredSize(new java.awt.Dimension(1000, 550));

        pnlLateral.setBackground(new java.awt.Color(255, 255, 255));

        lblLateral.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblLateral.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Quiron_lateral.png"))); // NOI18N

        javax.swing.GroupLayout pnlLateralLayout = new javax.swing.GroupLayout(pnlLateral);
        pnlLateral.setLayout(pnlLateralLayout);
        pnlLateralLayout.setHorizontalGroup(
            pnlLateralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlLateralLayout.createSequentialGroup()
                .addComponent(lblLateral, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 2, Short.MAX_VALUE))
        );
        pnlLateralLayout.setVerticalGroup(
            pnlLateralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblLateral, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );

        pnlCursos.setBackground(new java.awt.Color(255, 255, 255));

        lblPaciente.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        lblPaciente.setForeground(new java.awt.Color(102, 102, 102));
        lblPaciente.setText("Paciente *");

        lblData.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        lblData.setForeground(new java.awt.Color(102, 102, 102));
        lblData.setText("Data *");

        tblPacientes.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        tblPacientes.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        tblPacientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "DATA", "PACIENTE", "CPF", "ID_FICHA", "ID_PACIENTE"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
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
        jScrollPane2.setViewportView(tblPacientes);
        if (tblPacientes.getColumnModel().getColumnCount() > 0) {
            tblPacientes.getColumnModel().getColumn(3).setMinWidth(0);
            tblPacientes.getColumnModel().getColumn(3).setPreferredWidth(0);
            tblPacientes.getColumnModel().getColumn(3).setMaxWidth(0);
            tblPacientes.getColumnModel().getColumn(4).setMinWidth(0);
            tblPacientes.getColumnModel().getColumn(4).setPreferredWidth(0);
            tblPacientes.getColumnModel().getColumn(4).setMaxWidth(0);
        }

        btnAdicionar.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btnAdicionar.setForeground(new java.awt.Color(102, 102, 102));
        btnAdicionar.setText("ADICIONAR");
        btnAdicionar.setBorderPainted(false);
        btnAdicionar.setContentAreaFilled(false);
        btnAdicionar.setFocusPainted(false);
        btnAdicionar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnAdicionarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnAdicionarMouseExited(evt);
            }
        });
        btnAdicionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdicionarActionPerformed(evt);
            }
        });

        btnRemover.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btnRemover.setForeground(new java.awt.Color(102, 102, 102));
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

        btnAlterar.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btnAlterar.setForeground(new java.awt.Color(102, 102, 102));
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

        btnNovoEncaminhamento.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btnNovoEncaminhamento.setForeground(new java.awt.Color(102, 102, 102));
        btnNovoEncaminhamento.setText("NOVO");
        btnNovoEncaminhamento.setBorderPainted(false);
        btnNovoEncaminhamento.setContentAreaFilled(false);
        btnNovoEncaminhamento.setFocusPainted(false);
        btnNovoEncaminhamento.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnNovoEncaminhamentoMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnNovoEncaminhamentoMouseExited(evt);
            }
        });
        btnNovoEncaminhamento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNovoEncaminhamentoActionPerformed(evt);
            }
        });

        btnSalvar.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btnSalvar.setForeground(new java.awt.Color(102, 102, 102));
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

        btnCancelar.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btnCancelar.setForeground(new java.awt.Color(102, 102, 102));
        btnCancelar.setText("CANCELAR");
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

        tfPaciente.setEditable(false);
        tfPaciente.setBackground(new java.awt.Color(255, 255, 255));
        tfPaciente.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        tfPaciente.setForeground(new java.awt.Color(102, 102, 102));
        tfPaciente.setBorder(null);

        ftfData.setBorder(null);
        ftfData.setForeground(new java.awt.Color(102, 102, 102));
        try {
            ftfData.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        ftfData.setEnabled(false);
        ftfData.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N

        btnProcurar.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btnProcurar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Lupa P.png"))); // NOI18N
        btnProcurar.setBorderPainted(false);
        btnProcurar.setContentAreaFilled(false);
        btnProcurar.setEnabled(false);
        btnProcurar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnProcurarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnProcurarMouseExited(evt);
            }
        });
        btnProcurar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProcurarActionPerformed(evt);
            }
        });

        tfCpf.setEditable(false);
        tfCpf.setBackground(new java.awt.Color(255, 255, 255));
        tfCpf.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        tfCpf.setForeground(new java.awt.Color(102, 102, 102));
        tfCpf.setBorder(null);

        lblCpf.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        lblCpf.setForeground(new java.awt.Color(102, 102, 102));
        lblCpf.setText("CPF");

        lblIdPaciente.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        lblIdPaciente.setForeground(new java.awt.Color(102, 102, 102));
        lblIdPaciente.setText("ID Paciente");

        tfIdPaciente.setEditable(false);
        tfIdPaciente.setBackground(new java.awt.Color(153, 153, 153));
        tfIdPaciente.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        tfIdPaciente.setForeground(new java.awt.Color(255, 255, 255));

        btnSair.setBackground(new java.awt.Color(102, 102, 102));
        btnSair.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btnSair.setForeground(new java.awt.Color(102, 102, 102));
        btnSair.setText("SAIR");
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

        javax.swing.GroupLayout pnlCursosLayout = new javax.swing.GroupLayout(pnlCursos);
        pnlCursos.setLayout(pnlCursosLayout);
        pnlCursosLayout.setHorizontalGroup(
            pnlCursosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCursosLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pnlCursosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 650, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlCursosLayout.createSequentialGroup()
                        .addGroup(pnlCursosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlCursosLayout.createSequentialGroup()
                                .addGroup(pnlCursosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(lblPaciente, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(tfPaciente, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 294, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnProcurar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(sSeparador1, javax.swing.GroupLayout.PREFERRED_SIZE, 294, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(pnlCursosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(sSeparador2)
                            .addComponent(tfCpf)
                            .addComponent(lblCpf, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(pnlCursosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblData)
                            .addComponent(ftfData, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(sSeparador3, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(lblIdPaciente, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfIdPaciente, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlCursosLayout.createSequentialGroup()
                        .addGap(51, 51, 51)
                        .addGroup(pnlCursosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlCursosLayout.createSequentialGroup()
                                .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(40, 40, 40)
                                .addComponent(btnSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(50, 50, 50)
                                .addComponent(btnAdicionar, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pnlCursosLayout.createSequentialGroup()
                                .addComponent(btnRemover, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(40, 40, 40)
                                .addComponent(btnAlterar, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(50, 50, 50)
                                .addComponent(btnNovoEncaminhamento, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pnlCursosLayout.createSequentialGroup()
                                .addGap(226, 226, 226)
                                .addComponent(btnSair))))))
        );
        pnlCursosLayout.setVerticalGroup(
            pnlCursosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlCursosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlCursosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pnlCursosLayout.createSequentialGroup()
                        .addGroup(pnlCursosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(pnlCursosLayout.createSequentialGroup()
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(pnlCursosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(pnlCursosLayout.createSequentialGroup()
                                        .addComponent(lblPaciente)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(tfPaciente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(pnlCursosLayout.createSequentialGroup()
                                        .addGroup(pnlCursosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(lblCpf)
                                            .addComponent(lblData))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(pnlCursosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(tfCpf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(ftfData, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                            .addComponent(btnProcurar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlCursosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(sSeparador1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(sSeparador2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(sSeparador3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(lblIdPaciente)
                .addGap(18, 18, 18)
                .addComponent(tfIdPaciente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(pnlCursosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnCancelar)
                    .addComponent(btnSalvar)
                    .addComponent(btnAdicionar))
                .addGap(10, 10, 10)
                .addGroup(pnlCursosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnRemover)
                    .addComponent(btnAlterar)
                    .addComponent(btnNovoEncaminhamento))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSair)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnAdicionar.setVisible(false);
        btnSalvar.setVisible(false);
        btnCancelar.setVisible(false);
        tfPaciente.setEnabled(false);
        lblIdPaciente.setVisible(false);
        tfIdPaciente.setVisible(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(pnlLateral, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlCursos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(pnlCursos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlLateral, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tblPacientesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblPacientesMouseClicked
        // TODO add your handling code here:
        this.preencherCampos();
    }//GEN-LAST:event_tblPacientesMouseClicked

    private void btnAdicionarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAdicionarMouseEntered
        // TODO add your handling code here:
        this.entraMouse(btnAdicionar);
    }//GEN-LAST:event_btnAdicionarMouseEntered

    private void btnAdicionarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAdicionarMouseExited
        // TODO add your handling code here:
        this.saiMouse(btnAdicionar);
    }//GEN-LAST:event_btnAdicionarMouseExited

    private void btnAdicionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdicionarActionPerformed
        // TODO add your handling code here:
        if(ftfData.getText().equals("  /  /    ") || tfPaciente.getText().equals("")){
            JOptionPane.showMessageDialog(null, "PREENCHA OS CAMPOS COM *", "ERRO", JOptionPane.ERROR_MESSAGE);
        }
        else{
            this.inserirBD();
        }
    }//GEN-LAST:event_btnAdicionarActionPerformed

    private void btnRemoverMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnRemoverMouseEntered
        // TODO add your handling code here:
        this.entraMouse(btnRemover);
    }//GEN-LAST:event_btnRemoverMouseEntered

    private void btnRemoverMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnRemoverMouseExited
        // TODO add your handling code here:
        this.saiMouse(btnRemover);
    }//GEN-LAST:event_btnRemoverMouseExited

    private void btnRemoverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoverActionPerformed
        // TODO add your handling code here:
        this.removerEncaminhamento();
    }//GEN-LAST:event_btnRemoverActionPerformed

    private void btnAlterarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAlterarMouseEntered
        // TODO add your handling code here:
        this.entraMouse(btnAlterar);
    }//GEN-LAST:event_btnAlterarMouseEntered

    private void btnAlterarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAlterarMouseExited
        // TODO add your handling code here:
        this.saiMouse(btnAlterar);
    }//GEN-LAST:event_btnAlterarMouseExited

    private void btnAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAlterarActionPerformed
        // TODO add your handling code here:
        this.liberarCampos();

        btnCancelar.setVisible(true);
        btnSalvar.setVisible(true);
        btnAdicionar.setVisible(false);
        btnProcurar.setEnabled(false);

        btnRemover.setVisible(false);
        btnAlterar.setVisible(false);
        btnNovoEncaminhamento.setVisible(false);
    }//GEN-LAST:event_btnAlterarActionPerformed

    private void btnNovoEncaminhamentoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnNovoEncaminhamentoMouseEntered
        // TODO add your handling code here:
        this.entraMouse(btnNovoEncaminhamento);
    }//GEN-LAST:event_btnNovoEncaminhamentoMouseEntered

    private void btnNovoEncaminhamentoMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnNovoEncaminhamentoMouseExited
        // TODO add your handling code here:
        this.saiMouse(btnNovoEncaminhamento);
    }//GEN-LAST:event_btnNovoEncaminhamentoMouseExited

    private void btnNovoEncaminhamentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNovoEncaminhamentoActionPerformed
        // TODO add your handling code here:
        this.limpaCampos();
        this.liberarCampos();

        btnCancelar.setVisible(true);
        btnSalvar.setVisible(false);
        btnAdicionar.setVisible(true);
        btnProcurar.setEnabled(true);

        btnRemover.setVisible(false);
        btnAlterar.setVisible(false);
        btnNovoEncaminhamento.setVisible(false);

        tblPacientes.setEnabled(false);
    }//GEN-LAST:event_btnNovoEncaminhamentoActionPerformed

    private void btnSalvarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSalvarMouseEntered
        // TODO add your handling code here:
        this.entraMouse(btnSalvar);
    }//GEN-LAST:event_btnSalvarMouseEntered

    private void btnSalvarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSalvarMouseExited
        // TODO add your handling code here:
        this.saiMouse(btnSalvar);
    }//GEN-LAST:event_btnSalvarMouseExited

    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarActionPerformed
        // TODO add your handling code here:
        if(ftfData.getText().equals("  /  /    ") || tfPaciente.getText().equals("")){
            JOptionPane.showMessageDialog(null, "PREENCHA OS CAMPOS COM *", "ERRO", JOptionPane.ERROR_MESSAGE);
        }
        else
        this.alterarEncaminhamento();
    }//GEN-LAST:event_btnSalvarActionPerformed

    private void btnCancelarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCancelarMouseEntered
        // TODO add your handling code here:
        this.entraMouse(btnCancelar);
    }//GEN-LAST:event_btnCancelarMouseEntered

    private void btnCancelarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCancelarMouseExited
        // TODO add your handling code here:
        this.saiMouse(btnCancelar);
    }//GEN-LAST:event_btnCancelarMouseExited

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        // TODO add your handling code here:
        this.resetBotoes();
        this.limpaCampos();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnProcurarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnProcurarMouseEntered
        // TODO add your handling code here:
        this.entraMouse(btnProcurar);
    }//GEN-LAST:event_btnProcurarMouseEntered

    private void btnProcurarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnProcurarMouseExited
        // TODO add your handling code here:
        this.saiMouse(btnProcurar);
    }//GEN-LAST:event_btnProcurarMouseExited

    private void btnProcurarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProcurarActionPerformed
        // TODO add your handling code here:
        TelaPrincipal.controlePesquisa = 2;
        PesquisaPaciente pesquisaPaciente;
        pesquisaPaciente = new PesquisaPaciente(true);
        pesquisaPaciente.setVisible(true);
        //this.preencherCampoNomeCpf();
    }//GEN-LAST:event_btnProcurarActionPerformed

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
    private javax.swing.JButton btnAdicionar;
    private javax.swing.JButton btnAlterar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnNovoEncaminhamento;
    private javax.swing.JButton btnProcurar;
    private javax.swing.JButton btnRemover;
    private javax.swing.JButton btnSair;
    private javax.swing.JButton btnSalvar;
    private javax.swing.JFormattedTextField ftfData;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblCpf;
    private javax.swing.JLabel lblData;
    private javax.swing.JLabel lblIdPaciente;
    private javax.swing.JLabel lblLateral;
    private javax.swing.JLabel lblPaciente;
    private javax.swing.JPanel pnlCursos;
    private javax.swing.JPanel pnlLateral;
    private javax.swing.JSeparator sSeparador1;
    private javax.swing.JSeparator sSeparador2;
    private javax.swing.JSeparator sSeparador3;
    private javax.swing.JTable tblPacientes;
    public static javax.swing.JTextField tfCpf;
    public static javax.swing.JTextField tfIdPaciente;
    public static javax.swing.JTextField tfPaciente;
    // End of variables declaration//GEN-END:variables
}
