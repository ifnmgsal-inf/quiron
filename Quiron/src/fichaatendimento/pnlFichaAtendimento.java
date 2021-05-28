/*
 * Tela para registro do atendimento médico
 */
package fichaatendimento;

import bancodedados.MysqlConnect;
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
import javax.swing.table.TableCellEditor;
import principal.PesquisaPaciente;
import principal.TelaPrincipal;

/**
 *
 * @author Franciele Alves Barbosa e Rogério Costa Negro Rocha
 */
public class pnlFichaAtendimento extends javax.swing.JPanel {

    Connection conn;
    /**
     * Creates new form pnlFichaAtendimento
     */
    public pnlFichaAtendimento() {
        initComponents();
        try {
            conn = MysqlConnect.connectDB();
            this.preencherCampos();
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
                if("Servidor".equals(rs.getString("curso"))){
                    lblCurso.setVisible(false);
                    tfCurso.setVisible(false);
                    sSeparador5.setVisible(false);
                }
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
        btnSair = new javax.swing.JButton();
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

        setBackground(new java.awt.Color(255, 255, 255));
        setMaximumSize(new java.awt.Dimension(1000, 550));
        setMinimumSize(new java.awt.Dimension(1000, 550));

        pnlFichas.setBackground(new java.awt.Color(255, 255, 255));

        lblNome.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        lblNome.setForeground(new java.awt.Color(102, 102, 102));
        lblNome.setText("NOME");

        tfNome.setEditable(false);
        tfNome.setBackground(new java.awt.Color(255, 255, 255));
        tfNome.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        tfNome.setBorder(null);

        lblCurso.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        lblCurso.setForeground(new java.awt.Color(102, 102, 102));
        lblCurso.setText("CURSO");

        tfCurso.setEditable(false);
        tfCurso.setBackground(new java.awt.Color(255, 255, 255));
        tfCurso.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        tfCurso.setBorder(null);

        jLabel2.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(102, 102, 102));
        jLabel2.setText("DATA NASCIMENTO");

        tfDtNasc.setEditable(false);
        tfDtNasc.setBackground(new java.awt.Color(255, 255, 255));
        tfDtNasc.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        tfDtNasc.setBorder(null);

        jLabel4.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(102, 102, 102));
        jLabel4.setText("Nº CARTÃO SUS");

        tfNSus.setEditable(false);
        tfNSus.setBackground(new java.awt.Color(255, 255, 255));
        tfNSus.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        tfNSus.setBorder(null);

        jLabel1.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(102, 102, 102));
        jLabel1.setText("CPF");

        tfCpf.setEditable(false);
        tfCpf.setBackground(new java.awt.Color(255, 255, 255));
        tfCpf.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        tfCpf.setBorder(null);

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

        btnAtualizar.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btnAtualizar.setForeground(new java.awt.Color(102, 102, 102));
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

        btnAdicionarLinha.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btnAdicionarLinha.setForeground(new java.awt.Color(102, 102, 102));
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

        btnRemover.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btnRemover.setForeground(new java.awt.Color(102, 102, 102));
        btnRemover.setText("DELETAR LINHA");
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

        lblIdPaciente.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        lblIdPaciente.setForeground(new java.awt.Color(102, 102, 102));
        lblIdPaciente.setText("ID");
        lblIdPaciente.setVisible(false);

        tfIdPaciente.setEditable(false);
        tfIdPaciente.setBackground(new java.awt.Color(255, 255, 255));
        tfIdPaciente.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        tfIdPaciente.setBorder(null);
        tfIdPaciente.setVisible(false);

        lblRua.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        lblRua.setForeground(new java.awt.Color(102, 102, 102));
        lblRua.setText("RUA");

        tfRua.setEditable(false);
        tfRua.setBackground(new java.awt.Color(255, 255, 255));
        tfRua.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        tfRua.setBorder(null);
        tfRua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfRuaActionPerformed(evt);
            }
        });

        lblNumero.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        lblNumero.setForeground(new java.awt.Color(102, 102, 102));
        lblNumero.setText("Nº");

        tfNCasa.setEditable(false);
        tfNCasa.setBackground(new java.awt.Color(255, 255, 255));
        tfNCasa.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        tfNCasa.setBorder(null);

        lblBairro.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        lblBairro.setForeground(new java.awt.Color(102, 102, 102));
        lblBairro.setText("BAIRRO");

        tfBairro.setEditable(false);
        tfBairro.setBackground(new java.awt.Color(255, 255, 255));
        tfBairro.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        tfBairro.setBorder(null);

        lblCidade.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        lblCidade.setForeground(new java.awt.Color(102, 102, 102));
        lblCidade.setText("CIDADE");

        tfCidade.setEditable(false);
        tfCidade.setBackground(new java.awt.Color(255, 255, 255));
        tfCidade.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        tfCidade.setBorder(null);

        lblUf.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        lblUf.setForeground(new java.awt.Color(102, 102, 102));
        lblUf.setText("UF");

        tfUf.setEditable(false);
        tfUf.setBackground(new java.awt.Color(255, 255, 255));
        tfUf.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        tfUf.setBorder(null);

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

        javax.swing.GroupLayout pnlFichasLayout = new javax.swing.GroupLayout(pnlFichas);
        pnlFichas.setLayout(pnlFichasLayout);
        pnlFichasLayout.setHorizontalGroup(
            pnlFichasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFichasLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlFichasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(pnlFichasLayout.createSequentialGroup()
                        .addGroup(pnlFichasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlFichasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(lblNome, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(pnlFichasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(tfNome, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(sSeparador1, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(lblCurso))
                        .addGap(18, 18, 18)
                        .addGroup(pnlFichasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlFichasLayout.createSequentialGroup()
                                .addGroup(pnlFichasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(tfCpf)
                                    .addComponent(sSeparador3)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addGroup(pnlFichasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(tfDtNasc)
                                    .addComponent(sSeparador4)))
                            .addComponent(lblIdPaciente, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(pnlFichasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(tfNSus)
                            .addComponent(sSeparador2)))
                    .addGroup(pnlFichasLayout.createSequentialGroup()
                        .addComponent(tfCurso, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(tfIdPaciente, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(sSeparador5, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlFichasLayout.createSequentialGroup()
                        .addGroup(pnlFichasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlFichasLayout.createSequentialGroup()
                                .addComponent(sSeparador6, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(sSeparador7, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlFichasLayout.createSequentialGroup()
                                .addGroup(pnlFichasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(tfRua)
                                    .addComponent(lblRua, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addGroup(pnlFichasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(tfNCasa)
                                    .addComponent(lblNumero, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE))))
                        .addGap(18, 18, 18)
                        .addGroup(pnlFichasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(tfBairro)
                            .addComponent(sSeparador8)
                            .addComponent(lblBairro, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(pnlFichasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(tfCidade)
                            .addComponent(lblCidade, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                            .addComponent(sSeparador9))
                        .addGap(18, 18, 18)
                        .addGroup(pnlFichasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(tfUf)
                            .addComponent(lblUf, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
                            .addComponent(sSeparador10)))
                    .addComponent(jScrollPane1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlFichasLayout.createSequentialGroup()
                .addContainerGap(118, Short.MAX_VALUE)
                .addComponent(btnSair)
                .addGap(18, 18, 18)
                .addComponent(btnRemover)
                .addGap(18, 18, 18)
                .addComponent(btnAtualizar)
                .addGap(18, 18, 18)
                .addComponent(btnAdicionarLinha)
                .addGap(104, 104, 104))
        );
        pnlFichasLayout.setVerticalGroup(
            pnlFichasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFichasLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlFichasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNome, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlFichasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfCpf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfDtNasc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfNSus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlFichasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(sSeparador1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sSeparador3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sSeparador4, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sSeparador2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnlFichasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblCurso, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblIdPaciente, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlFichasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfCurso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfIdPaciente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sSeparador5, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(pnlFichasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblRua, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblNumero, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblBairro, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblCidade, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblUf, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlFichasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfRua, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfNCasa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfBairro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfCidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfUf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlFichasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlFichasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(sSeparador7)
                        .addComponent(sSeparador6, javax.swing.GroupLayout.DEFAULT_SIZE, 10, Short.MAX_VALUE))
                    .addComponent(sSeparador8, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sSeparador10, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sSeparador9, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(pnlFichasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAdicionarLinha)
                    .addComponent(btnAtualizar)
                    .addComponent(btnRemover)
                    .addComponent(btnSair))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pnlFichas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pnlFichas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 58, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tblFichaMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblFichaMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_tblFichaMouseExited

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

    private void btnRemoverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoverActionPerformed
        // TODO add your handling code here:
        try {
            DefaultTableModel tabelaFichas = (DefaultTableModel) tblFicha.getModel();
            tabelaFichas.removeRow(tblFicha.getSelectedRow());
        } catch (ArrayIndexOutOfBoundsException ex) {
            JOptionPane.showMessageDialog(null, "Selecione uma linha!", "ERRO", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnRemoverActionPerformed

    private void tfRuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfRuaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfRuaActionPerformed

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

    private void btnRemoverMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnRemoverMouseEntered
        // TODO add your handling code here:
        this.entraMouse(btnRemover);
    }//GEN-LAST:event_btnRemoverMouseEntered

    private void btnRemoverMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnRemoverMouseExited
        // TODO add your handling code here:
        this.saiMouse(btnRemover);
    }//GEN-LAST:event_btnRemoverMouseExited


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdicionarLinha;
    private javax.swing.JButton btnAtualizar;
    private javax.swing.JButton btnRemover;
    private javax.swing.JButton btnSair;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblBairro;
    private javax.swing.JLabel lblCidade;
    private javax.swing.JLabel lblCurso;
    private javax.swing.JLabel lblIdPaciente;
    private javax.swing.JLabel lblNome;
    private javax.swing.JLabel lblNumero;
    private javax.swing.JLabel lblRua;
    private javax.swing.JLabel lblUf;
    private javax.swing.JPanel pnlFichas;
    private javax.swing.JSeparator sSeparador1;
    private javax.swing.JSeparator sSeparador10;
    private javax.swing.JSeparator sSeparador2;
    private javax.swing.JSeparator sSeparador3;
    private javax.swing.JSeparator sSeparador4;
    private javax.swing.JSeparator sSeparador5;
    private javax.swing.JSeparator sSeparador6;
    private javax.swing.JSeparator sSeparador7;
    private javax.swing.JSeparator sSeparador8;
    private javax.swing.JSeparator sSeparador9;
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
