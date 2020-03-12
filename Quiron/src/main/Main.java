/*
 * Classe principal, onde é ativada a Tela de Login do usuário (servidor)
 */
package main;

import login.TelaLogin;

/**
 *
 * @author Franciele Alves Barbosa e Rogério Costa Negro Rocha
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        TelaLogin meuLogin = new TelaLogin();
        meuLogin.setVisible(true);

        //TelaPrincipal telaPrincipal= new TelaPrincipal();
        //telaPrincipal.setVisible(true);
    }
}
