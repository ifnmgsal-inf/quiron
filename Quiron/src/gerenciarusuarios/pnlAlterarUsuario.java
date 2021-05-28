/*
 * Tela para alterar informações de usuários (servidores) já cadastrados.  
 */
package gerenciarusuarios;

import bancodedados.MysqlConnect;
import java.awt.Color;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import login.TelaLogin;
import principal.TelaPrincipal;

/**
 *
 * @author Franciele Alves Barbosa e Rogério Costa Negro Rocha
 */
public class pnlAlterarUsuario extends javax.swing.JPanel {

    private Connection conn = null;
    private String nome, matricula, cpf, telefone, numMatricula;
    private String senha, confirmaSenha;
    private int administrador;
    /**
     * Creates new form pnlAlterarUsuario
     */
    public pnlAlterarUsuario() {
        initComponents();
        try {
            conn = MysqlConnect.connectDB();
            this.verificaAdministrador();
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
                    + " telefone= ?, senha= AES_ENCRYPT(?,?), administrador= ? WHERE matricula= ?";
            try (PreparedStatement pstmt = conn.prepareStatement(qryAtualiza)) {
                pstmt.setString(1, matricula);
                pstmt.setString(2, nome);
                pstmt.setString(3, cpf);
                pstmt.setString(4, telefone);
                pstmt.setString(5, String.valueOf(tfSenha.getPassword()));
                pstmt.setString(6, "Quiron");
                if (rbAdministrador.isSelected()) {
                    pstmt.setInt(7, 1);
                } else {
                    pstmt.setInt(7, 0);
                }
                pstmt.setString(8, jcbUsuarios.getSelectedItem().toString());

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
        btnSair = new javax.swing.JButton();
        lblUsuarios = new javax.swing.JLabel();
        jcbUsuarios = new javax.swing.JComboBox<>();
        lblTipoUsuario = new javax.swing.JLabel();
        rbAdministrador = new javax.swing.JRadioButton();
        rbPadrao = new javax.swing.JRadioButton();
        sSeparador1 = new javax.swing.JSeparator();
        sSeparador2 = new javax.swing.JSeparator();
        sSeparador3 = new javax.swing.JSeparator();
        sSeparador4 = new javax.swing.JSeparator();
        sSeparador5 = new javax.swing.JSeparator();
        sSeparador6 = new javax.swing.JSeparator();
        pnlLateral = new javax.swing.JPanel();
        lblLateral = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setMaximumSize(new java.awt.Dimension(1000, 550));
        setMinimumSize(new java.awt.Dimension(1000, 550));
        setPreferredSize(new java.awt.Dimension(1000, 550));

        pnlAlterarUsuario.setBackground(new java.awt.Color(255, 255, 255));

        lblNome.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        lblNome.setForeground(new java.awt.Color(102, 102, 102));
        lblNome.setText("Nome *");

        lblMatricula.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        lblMatricula.setForeground(new java.awt.Color(102, 102, 102));
        lblMatricula.setText("Nº Matrícula *");

        lblCpf.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        lblCpf.setForeground(new java.awt.Color(102, 102, 102));
        lblCpf.setText("CPF *");

        lblTelefone.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        lblTelefone.setForeground(new java.awt.Color(102, 102, 102));
        lblTelefone.setText("Telefone");

        lblSenha.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        lblSenha.setForeground(new java.awt.Color(102, 102, 102));
        lblSenha.setText("Senha");

        lblConfirmaSenha.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        lblConfirmaSenha.setForeground(new java.awt.Color(102, 102, 102));
        lblConfirmaSenha.setText("Confirme a senha");

        tfNome.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        tfNome.setBorder(null);

        tfMatricula.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        tfMatricula.setBorder(null);

        tfTelefone.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        tfTelefone.setBorder(null);

        tfSenha.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        tfSenha.setBorder(null);

        tfConfirmaSenha.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        tfConfirmaSenha.setBorder(null);

        tfCpf.setBorder(null);
        try {
            tfCpf.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###.###.###-##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        tfCpf.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N

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

        lblUsuarios.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        lblUsuarios.setForeground(new java.awt.Color(102, 102, 102));
        lblUsuarios.setText("Selecione a Matrícula");

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

        lblTipoUsuario.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        lblTipoUsuario.setForeground(new java.awt.Color(102, 102, 102));
        lblTipoUsuario.setText("Usuário");

        rbAdministrador.setBackground(new java.awt.Color(255, 255, 255));
        bgUsuario.add(rbAdministrador);
        rbAdministrador.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        rbAdministrador.setForeground(new java.awt.Color(102, 102, 102));
        rbAdministrador.setText("Administrador");
        rbAdministrador.setContentAreaFilled(false);
        rbAdministrador.setFocusPainted(false);

        rbPadrao.setBackground(new java.awt.Color(255, 255, 255));
        bgUsuario.add(rbPadrao);
        rbPadrao.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        rbPadrao.setForeground(new java.awt.Color(102, 102, 102));
        rbPadrao.setText("Padrão");
        rbPadrao.setContentAreaFilled(false);
        rbPadrao.setFocusPainted(false);

        sSeparador1.setForeground(new java.awt.Color(51, 51, 51));

        sSeparador2.setForeground(new java.awt.Color(51, 51, 51));

        sSeparador3.setForeground(new java.awt.Color(51, 51, 51));

        sSeparador4.setForeground(new java.awt.Color(51, 51, 51));

        sSeparador5.setForeground(new java.awt.Color(51, 51, 51));

        sSeparador6.setForeground(new java.awt.Color(51, 51, 51));

        javax.swing.GroupLayout pnlAlterarUsuarioLayout = new javax.swing.GroupLayout(pnlAlterarUsuario);
        pnlAlterarUsuario.setLayout(pnlAlterarUsuarioLayout);
        pnlAlterarUsuarioLayout.setHorizontalGroup(
            pnlAlterarUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlAlterarUsuarioLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlAlterarUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlAlterarUsuarioLayout.createSequentialGroup()
                        .addGap(268, 268, 268)
                        .addComponent(lblConfirmaSenha))
                    .addGroup(pnlAlterarUsuarioLayout.createSequentialGroup()
                        .addComponent(sSeparador5, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(sSeparador6, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlAlterarUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(pnlAlterarUsuarioLayout.createSequentialGroup()
                            .addGroup(pnlAlterarUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(lblSenha)
                                .addGroup(pnlAlterarUsuarioLayout.createSequentialGroup()
                                    .addComponent(tfSenha, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(tfConfirmaSenha, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGap(8, 8, 8))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlAlterarUsuarioLayout.createSequentialGroup()
                            .addGroup(pnlAlterarUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(pnlAlterarUsuarioLayout.createSequentialGroup()
                                    .addGroup(pnlAlterarUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(lblTipoUsuario)
                                        .addGroup(pnlAlterarUsuarioLayout.createSequentialGroup()
                                            .addComponent(rbAdministrador)
                                            .addGap(18, 18, 18)
                                            .addComponent(rbPadrao, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGap(99, 99, 99))
                                .addGroup(pnlAlterarUsuarioLayout.createSequentialGroup()
                                    .addComponent(btnSair)
                                    .addGap(18, 18, 18)))
                            .addComponent(btnAlterar))))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(pnlAlterarUsuarioLayout.createSequentialGroup()
                .addGroup(pnlAlterarUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlAlterarUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(lblNome, javax.swing.GroupLayout.PREFERRED_SIZE, 418, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(sSeparador1, javax.swing.GroupLayout.PREFERRED_SIZE, 418, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(tfNome, javax.swing.GroupLayout.PREFERRED_SIZE, 418, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(pnlAlterarUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlAlterarUsuarioLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(pnlAlterarUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(pnlAlterarUsuarioLayout.createSequentialGroup()
                                        .addGroup(pnlAlterarUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(tfCpf, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(lblCpf))
                                        .addGap(18, 18, 18)
                                        .addGroup(pnlAlterarUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(tfMatricula, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(lblMatricula)))
                                    .addGroup(pnlAlterarUsuarioLayout.createSequentialGroup()
                                        .addComponent(sSeparador3, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(sSeparador2, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(pnlAlterarUsuarioLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(pnlAlterarUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblUsuarios, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jcbUsuarios, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(pnlAlterarUsuarioLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(pnlAlterarUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(tfTelefone)
                            .addComponent(lblTelefone, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
                            .addComponent(sSeparador4))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlAlterarUsuarioLayout.setVerticalGroup(
            pnlAlterarUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlAlterarUsuarioLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblUsuarios)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jcbUsuarios, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblNome)
                .addGap(18, 18, 18)
                .addComponent(tfNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sSeparador1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(pnlAlterarUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCpf)
                    .addComponent(lblMatricula))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlAlterarUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfCpf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfMatricula, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addGroup(pnlAlterarUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(sSeparador3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sSeparador2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblTelefone)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tfTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sSeparador4, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(pnlAlterarUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblSenha)
                    .addComponent(lblConfirmaSenha))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlAlterarUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfConfirmaSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlAlterarUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(sSeparador5, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sSeparador6, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(lblTipoUsuario)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlAlterarUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rbAdministrador)
                    .addComponent(rbPadrao))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pnlAlterarUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAlterar)
                    .addComponent(btnSair))
                .addContainerGap())
        );

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
            .addComponent(lblLateral, javax.swing.GroupLayout.DEFAULT_SIZE, 550, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(pnlLateral, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlAlterarUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 206, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(pnlLateral, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlAlterarUsuario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, 0))
        );
    }// </editor-fold>//GEN-END:initComponents

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

    private void jcbUsuariosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jcbUsuariosMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jcbUsuariosMouseClicked

    private void jcbUsuariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbUsuariosActionPerformed
        // TODO add your handling code here:
        this.preencheCampos();
    }//GEN-LAST:event_jcbUsuariosActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup bgUsuario;
    private javax.swing.JButton btnAlterar;
    private javax.swing.JButton btnSair;
    private javax.swing.JComboBox<String> jcbUsuarios;
    private javax.swing.JLabel lblConfirmaSenha;
    private javax.swing.JLabel lblCpf;
    private javax.swing.JLabel lblLateral;
    private javax.swing.JLabel lblMatricula;
    private javax.swing.JLabel lblNome;
    private javax.swing.JLabel lblSenha;
    private javax.swing.JLabel lblTelefone;
    private javax.swing.JLabel lblTipoUsuario;
    private javax.swing.JLabel lblUsuarios;
    private javax.swing.JPanel pnlAlterarUsuario;
    private javax.swing.JPanel pnlLateral;
    private javax.swing.JRadioButton rbAdministrador;
    private javax.swing.JRadioButton rbPadrao;
    private javax.swing.JSeparator sSeparador1;
    private javax.swing.JSeparator sSeparador2;
    private javax.swing.JSeparator sSeparador3;
    private javax.swing.JSeparator sSeparador4;
    private javax.swing.JSeparator sSeparador5;
    private javax.swing.JSeparator sSeparador6;
    private javax.swing.JPasswordField tfConfirmaSenha;
    private javax.swing.JFormattedTextField tfCpf;
    private javax.swing.JTextField tfMatricula;
    private javax.swing.JTextField tfNome;
    private javax.swing.JPasswordField tfSenha;
    private javax.swing.JTextField tfTelefone;
    // End of variables declaration//GEN-END:variables
}
