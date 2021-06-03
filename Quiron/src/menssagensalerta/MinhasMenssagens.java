/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package menssagensalerta;


import javax.swing.JOptionPane;

/**
 *
 * @author roger
 */
public class MinhasMenssagens {
    
    public static void main(String [] args){
        
    }
    
    public static void chamarMenssagemErro(String menssagem){
        JOptionPane.showMessageDialog(null, menssagem, "ALERTA DE ERRO", JOptionPane.ERROR_MESSAGE);
    }
    public static void chamarMenssagemSucesso(String menssagem){
        JOptionPane.showMessageDialog(null, menssagem, "", JOptionPane.INFORMATION_MESSAGE);
    }
    public static int chamarMenssagemOpcao(String menssagem){
        int opcao = JOptionPane.showConfirmDialog(null, menssagem, "ATENÇÃO", JOptionPane.YES_OPTION);
        return opcao;
    }
    public static int chamarMenssagemOpcaoSair(){
        int opcao = JOptionPane.showConfirmDialog(null, "Deseja realmente deixar essa tela?", "ATENÇÃO", JOptionPane.YES_OPTION);
        return opcao;
    }
    public static void chamarMenssagemCamposObrigatorios(){
        JOptionPane.showMessageDialog(null, "Preencha todos os campos obrigatórios*!", "ALERTA DE ERRO", JOptionPane.ERROR_MESSAGE);
    }
    
}
