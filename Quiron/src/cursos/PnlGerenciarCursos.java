/*
 * Tela para cadastro de Cursos ofertados pelo IFNMG
 */
package cursos;

import bancodedados.MysqlConnect;
import java.awt.Color;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import menssagensalerta.MinhasMenssagens;
import principal.TelaPrincipal;

/**
 *
 * @author Franciele Alves Barbosa e Rogério Costa Negro Rocha
 */
public final class PnlGerenciarCursos extends javax.swing.JPanel {

    Connection conn = null;

    /**
     * Creates new form pnlGerenciarCursos
     */
    public PnlGerenciarCursos() {
        initComponents();
        try {
            conn = MysqlConnect.connectDB();
            this.preencheTabela();
        } catch (SQLException sqle) {
            //JOptionPane.showMessageDialog(null, "Erro ao conectar com o Banco de Dados " /*+ ex.getMessage()*/, "ERRO", JOptionPane.ERROR_MESSAGE);
            MinhasMenssagens.chamarMenssagemErro("Erro ao conectar com o banco de dados");
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

    public void preencheTabela() {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        DefaultTableModel lista = (DefaultTableModel) tblCursos.getModel();
        lista.setNumRows(0);
        String qry = "SELECT idCursos, curso, nivel FROM cursos order by curso";
        try {
            pstmt = conn.prepareStatement(qry);
            rs = pstmt.executeQuery(qry);

            while (rs.next()) {
                if (!"Servidor".equals(rs.getString("curso"))) {
                    lista.addRow(new String[]{"" + rs.getInt(1), rs.getString("curso"), rs.getString("nivel")});
                }
            }
        } catch (SQLException ex) {
            //JOptionPane.showMessageDialog(null, "Erro: " + ex.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
            MinhasMenssagens.chamarMenssagemErro(ex.getMessage());
        }
    }

    public void liberarCampos() {
        tfCurso.setEnabled(true);
        rbTecnico.setEnabled(true);
        rbSuperior.setEnabled(true);
        rbMestrado.setEnabled(true);
    }

    public void travarCampos() {
        tfCurso.setEnabled(false);
        rbTecnico.setEnabled(false);
        rbSuperior.setEnabled(false);
        rbMestrado.setEnabled(false);
    }

    public void preencherCampos() {
        tfCurso.setText(tblCursos.getValueAt(tblCursos.getSelectedRow(), 1).toString());

        switch (tblCursos.getValueAt(tblCursos.getSelectedRow(), 2).toString()) {
            case "Técnico Integrado":
                rbTecnico.setSelected(true);
                break;
            case "Superior":
                rbSuperior.setSelected(true);
                break;
            case "Mestrado":
                rbMestrado.setSelected(true);
                break;
            default:
                break;
        }
    }

    public void limpaCampos() {
        tfCurso.setText("");
        rbTecnico.setSelected(false);
        rbSuperior.setSelected(false);
        rbMestrado.setSelected(false);
        tblCursos.clearSelection();
    }

    public void resetBotoes() {
        btnCancelar.setVisible(false);
        btnSalvar.setVisible(false);
        btnAdicionar.setVisible(false);

        btnRemover.setVisible(true);
        btnAlterar.setVisible(true);
        btnNovoCurso.setVisible(true);
        tblCursos.setEnabled(true);

        this.travarCampos();
        this.limpaCampos();
    }

    public void sair() {
        //int op = JOptionPane.showConfirmDialog(null, "Deseja realmente sair?", "ATENÇÃO", JOptionPane.YES_OPTION);
        int op = MinhasMenssagens.chamarMenssagemOpcaoSair();
        if (op == JOptionPane.YES_OPTION) {
            TelaPrincipal.voltarHome();
        }
    }

    public void adicionarCurso() {
        PreparedStatement pstmt = null;
        String qry = "INSERT INTO cursos(curso, nivel) VALUES(?,?)";

        try {
            pstmt = conn.prepareStatement(qry);
            pstmt.setString(1, tfCurso.getText());
            if (rbTecnico.isSelected()) {
                pstmt.setString(2, "Técnico Integrado");
            } else if (rbSuperior.isSelected()) {
                pstmt.setString(2, "Superior");
            } else if (rbMestrado.isSelected()) {
                pstmt.setString(2, "Mestrado");
            } else {
                pstmt.setString(2, null);
            }
            pstmt.executeUpdate();

        } catch (SQLException ex) {
            //JOptionPane.showMessageDialog(null, "Erro ao inserir: " + ex.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
            MinhasMenssagens.chamarMenssagemErro(ex.getMessage());
        }
        this.preencheTabela();
        this.travarCampos();
        this.resetBotoes();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bgNivel = new javax.swing.ButtonGroup();
        pnlCursos = new javax.swing.JPanel();
        rbTecnico = new javax.swing.JRadioButton();
        lblCurso = new javax.swing.JLabel();
        lblNivel = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblCursos = new javax.swing.JTable();
        btnAdicionar = new javax.swing.JButton();
        btnRemover = new javax.swing.JButton();
        btnAlterar = new javax.swing.JButton();
        btnNovoCurso = new javax.swing.JButton();
        btnSalvar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        tfCurso = new javax.swing.JTextField();
        rbMestrado = new javax.swing.JRadioButton();
        rbSuperior = new javax.swing.JRadioButton();
        jSeparator1 = new javax.swing.JSeparator();
        btnSair = new javax.swing.JButton();
        pnlLateral = new javax.swing.JPanel();
        lblLateral = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setMaximumSize(new java.awt.Dimension(1000, 550));
        setMinimumSize(new java.awt.Dimension(1000, 550));
        setPreferredSize(new java.awt.Dimension(1000, 550));

        pnlCursos.setBackground(new java.awt.Color(255, 255, 255));

        rbTecnico.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        rbTecnico.setForeground(new java.awt.Color(102, 102, 102));
        rbTecnico.setText("Técnico Integrado");
        rbTecnico.setContentAreaFilled(false);
        rbTecnico.setFocusPainted(false);

        lblCurso.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        lblCurso.setForeground(new java.awt.Color(102, 102, 102));
        lblCurso.setText("Curso *");

        lblNivel.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        lblNivel.setForeground(new java.awt.Color(102, 102, 102));
        lblNivel.setText("Nível *");

        tblCursos.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        tblCursos.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        tblCursos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Curso", "Nível"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblCursos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblCursosMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblCursos);
        if (tblCursos.getColumnModel().getColumnCount() > 0) {
            tblCursos.getColumnModel().getColumn(0).setMinWidth(0);
            tblCursos.getColumnModel().getColumn(0).setPreferredWidth(0);
            tblCursos.getColumnModel().getColumn(0).setMaxWidth(0);
            tblCursos.getColumnModel().getColumn(2).setPreferredWidth(10);
        }

        btnAdicionar.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btnAdicionar.setForeground(new java.awt.Color(102, 102, 102));
        btnAdicionar.setText("Adicionar");
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
        btnRemover.setText("Remover");
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

        btnNovoCurso.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btnNovoCurso.setForeground(new java.awt.Color(102, 102, 102));
        btnNovoCurso.setText("Novo Curso");
        btnNovoCurso.setBorderPainted(false);
        btnNovoCurso.setContentAreaFilled(false);
        btnNovoCurso.setFocusPainted(false);
        btnNovoCurso.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnNovoCursoMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnNovoCursoMouseExited(evt);
            }
        });
        btnNovoCurso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNovoCursoActionPerformed(evt);
            }
        });

        btnSalvar.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btnSalvar.setForeground(new java.awt.Color(102, 102, 102));
        btnSalvar.setText("Salvar");
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

        tfCurso.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        tfCurso.setBorder(null);

        rbMestrado.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        rbMestrado.setForeground(new java.awt.Color(102, 102, 102));
        rbMestrado.setText("Mestrado");
        rbMestrado.setContentAreaFilled(false);
        rbMestrado.setFocusPainted(false);

        rbSuperior.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        rbSuperior.setForeground(new java.awt.Color(102, 102, 102));
        rbSuperior.setText("Superior");
        rbSuperior.setContentAreaFilled(false);
        rbSuperior.setFocusPainted(false);
        rbSuperior.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbSuperiorActionPerformed(evt);
            }
        });

        jSeparator1.setForeground(new java.awt.Color(51, 51, 51));

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
                .addGroup(pnlCursosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlCursosLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(pnlCursosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlCursosLayout.createSequentialGroup()
                                .addComponent(rbTecnico)
                                .addGap(18, 18, 18)
                                .addComponent(rbSuperior)
                                .addGap(18, 18, 18)
                                .addComponent(rbMestrado))
                            .addGroup(pnlCursosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 470, Short.MAX_VALUE)
                                .addComponent(lblCurso)
                                .addComponent(tfCurso)
                                .addComponent(jSeparator1))
                            .addComponent(lblNivel, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(pnlCursosLayout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addGroup(pnlCursosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlCursosLayout.createSequentialGroup()
                                .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(btnSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(btnAdicionar, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pnlCursosLayout.createSequentialGroup()
                                .addComponent(btnRemover, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addGroup(pnlCursosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(btnSair, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnAlterar, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, 0)
                                .addComponent(btnNovoCurso, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlCursosLayout.setVerticalGroup(
            pnlCursosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlCursosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblCurso)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tfCurso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblNivel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlCursosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rbTecnico)
                    .addComponent(rbSuperior)
                    .addComponent(rbMestrado, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnlCursosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnCancelar)
                    .addComponent(btnSalvar)
                    .addComponent(btnAdicionar))
                .addGap(10, 10, 10)
                .addGroup(pnlCursosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnRemover)
                    .addComponent(btnAlterar)
                    .addComponent(btnNovoCurso))
                .addGap(26, 26, 26)
                .addComponent(btnSair)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        rbTecnico.setEnabled(false);
        btnAdicionar.setVisible(false);
        btnSalvar.setVisible(false);
        btnCancelar.setVisible(false);
        tfCurso.setEnabled(false);
        rbMestrado.setEnabled(false);
        rbSuperior.setEnabled(false);

        lblLateral.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblLateral.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Quiron_lateral.png"))); // NOI18N

        javax.swing.GroupLayout pnlLateralLayout = new javax.swing.GroupLayout(pnlLateral);
        pnlLateral.setLayout(pnlLateralLayout);
        pnlLateralLayout.setHorizontalGroup(
            pnlLateralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlLateralLayout.createSequentialGroup()
                .addComponent(lblLateral, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
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
                .addComponent(pnlCursos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(154, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(pnlLateral, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlCursos, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 103, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tblCursosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblCursosMouseClicked
        // TODO add your handling code here:
        this.preencherCampos();
    }//GEN-LAST:event_tblCursosMouseClicked

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
        if (tfCurso.getText().equals("") || !rbTecnico.isSelected() && !rbSuperior.isSelected() && !rbMestrado.isSelected()) {
            //JOptionPane.showMessageDialog(null, "Preencha os campos obrigatórios", "ERRO", JOptionPane.ERROR_MESSAGE);
            
            MinhasMenssagens.chamarMenssagemCamposObrigatorios();
        } else {
            this.adicionarCurso();
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

        //int op = JOptionPane.showConfirmDialog(null, "Deseja realmente remover este curso?", "ATENÇÃO", JOptionPane.YES_OPTION);
        int op = MinhasMenssagens.chamarMenssagemOpcao("Deseja realmente remover esse curso?");
        if (op == JOptionPane.YES_OPTION) {
            PreparedStatement pstmt = null;
            String qry = "DELETE FROM cursos WHERE idCursos= ?";

            try {
                pstmt = conn.prepareStatement(qry);
                pstmt.setString(1, (tblCursos.getValueAt(tblCursos.getSelectedRow(), 0).toString()));

                pstmt.executeUpdate();
                //JOptionPane.showMessageDialog(null, "Excluido com sucesso!");
                MinhasMenssagens.chamarMenssagemSucesso("Excluido com sucesso.");

            } catch (SQLException ex) {
                //JOptionPane.showMessageDialog(null, "Erro ao excluir: " + ex.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
                MinhasMenssagens.chamarMenssagemErro(ex.getMessage());
            } catch (ArrayIndexOutOfBoundsException ex) {
                //JOptionPane.showMessageDialog(null, "Selecione um curso!", "ERRO", JOptionPane.ERROR_MESSAGE);
                MinhasMenssagens.chamarMenssagemErro("Selecione um curso!");
            }
            this.preencheTabela();
            this.limpaCampos();
        }
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

        btnRemover.setVisible(false);
        btnAlterar.setVisible(false);
        btnNovoCurso.setVisible(false);
    }//GEN-LAST:event_btnAlterarActionPerformed

    private void btnNovoCursoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnNovoCursoMouseEntered
        // TODO add your handling code here:
        this.entraMouse(btnNovoCurso);
    }//GEN-LAST:event_btnNovoCursoMouseEntered

    private void btnNovoCursoMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnNovoCursoMouseExited
        // TODO add your handling code here:
        this.saiMouse(btnNovoCurso);
    }//GEN-LAST:event_btnNovoCursoMouseExited

    private void btnNovoCursoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNovoCursoActionPerformed
        // TODO add your handling code here:
        this.limpaCampos();
        this.liberarCampos();

        btnCancelar.setVisible(true);
        btnSalvar.setVisible(false);
        btnAdicionar.setVisible(true);

        btnRemover.setVisible(false);
        btnAlterar.setVisible(false);
        btnNovoCurso.setVisible(false);

        tblCursos.setEnabled(false);
    }//GEN-LAST:event_btnNovoCursoActionPerformed

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
        PreparedStatement pstmt = null;
        String qry = "UPDATE cursos SET curso= ?, nivel= ? WHERE idCursos= ?";

        try {
            pstmt = conn.prepareStatement(qry);
            pstmt.setString(1, tfCurso.getText());
            if (rbTecnico.isSelected()) {
                pstmt.setString(2, "Técnico Integrado");
            } else if (rbSuperior.isSelected()) {
                pstmt.setString(2, "Superior");
            } else if (rbMestrado.isSelected()) {
                pstmt.setString(2, "Mestrado");
            } else {
                pstmt.setString(2, null);
            }
            pstmt.setString(3, (tblCursos.getValueAt(tblCursos.getSelectedRow(), 0).toString()));

            pstmt.executeUpdate();
            //JOptionPane.showMessageDialog(null, "Atualizado com sucesso!");
            MinhasMenssagens.chamarMenssagemSucesso("Atualizado com sucesso");

        } catch (SQLException ex) {
            //JOptionPane.showMessageDialog(null, "Erro ao salvar: " + ex.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
            MinhasMenssagens.chamarMenssagemErro(ex.getMessage());
        }
        this.preencheTabela();
        this.travarCampos();
        this.limpaCampos();
        this.resetBotoes();
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
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void rbSuperiorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbSuperiorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rbSuperiorActionPerformed

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
    private javax.swing.ButtonGroup bgNivel;
    private javax.swing.JButton btnAdicionar;
    private javax.swing.JButton btnAlterar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnNovoCurso;
    private javax.swing.JButton btnRemover;
    private javax.swing.JButton btnSair;
    private javax.swing.JButton btnSalvar;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lblCurso;
    private javax.swing.JLabel lblLateral;
    private javax.swing.JLabel lblNivel;
    private javax.swing.JPanel pnlCursos;
    private javax.swing.JPanel pnlLateral;
    private javax.swing.JRadioButton rbMestrado;
    private javax.swing.JRadioButton rbSuperior;
    private javax.swing.JRadioButton rbTecnico;
    private javax.swing.JTable tblCursos;
    private javax.swing.JTextField tfCurso;
    // End of variables declaration//GEN-END:variables
}
