/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gerenciarpacientes;

import java.awt.Color;
import java.util.Arrays;
import javax.swing.JButton;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author leonardosilva
 */
public class PnlCadastroPacienteTest {
    
    public PnlCadastroPacienteTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Test of sair method, of class pnlCadastroPaciente.
     */
    /*
    @Test
    public void testSair() {
        System.out.println("sair");
        PnlCadastroPaciente instance = new PnlCadastroPaciente();
        instance.sair();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    */

    /**
     * Test of entraMouse method, of class PnlCadastroPaciente.
     */
    @Test
    public void testEntraMouse() {
        System.out.println("entraMouse");
        JButton botao = new JButton();
        PnlCadastroPaciente instance = new PnlCadastroPaciente();
        boolean[] prop = new boolean[4];
        boolean[] result = {true, true, true, true};
        instance.entraMouse(botao);
        prop[0] = botao.isOpaque();
        prop[1] = botao.isContentAreaFilled();
        prop[2] = botao.isBorderPainted();
        prop[3] = (botao.getBackground() == Color.LIGHT_GRAY);
        
        assertEquals(true, Arrays.equals(prop, result));
    }

    /**
     * Test of saiMouse method, of class PnlCadastroPaciente.
     */
    @Test
    public void testSaiMouse() {
        System.out.println("saiMouse");
        JButton botao = new JButton();
        PnlCadastroPaciente instance = new PnlCadastroPaciente();
        boolean[] prop = new boolean[3];
        boolean[] result = {false, false, false};
        instance.saiMouse(botao);
        
        prop[0] = botao.isOpaque();
        prop[1] = botao.isContentAreaFilled();
        prop[2] = botao.isBorderPainted();
        
        assertEquals(true, Arrays.equals(prop, result));
    }

    /**
     * Test of cursos method, of class pnlCadastroPaciente.
     */
    /*
    @Test
    public void testCursos() {
        System.out.println("cursos");
        PnlCadastroPaciente instance = new PnlCadastroPaciente();
        instance.cursos();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    */
    /**
     * Test of botaoFinalizar method, of class pnlCadastroPaciente.
     */
    /*
    @Test
    public void testBotaoFinalizar() {
        System.out.println("botaoFinalizar");
        PnlCadastroPaciente instance = new PnlCadastroPaciente();
        instance.botaoFinalizar();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    */

    /**
     * Test of inserirIdentificacao method, of class pnlCadastroPaciente.
     */
    /*
    @Test
    public void testInserirIdentificacao() {
        System.out.println("inserirIdentificacao");
        PnlCadastroPaciente instance = new PnlCadastroPaciente();
        instance.inserirIdentificacao();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    */
    /**
     * Test of atualizarIdentificacao method, of class pnlCadastroPaciente.
     */
    /*
    @Test
    public void testAtualizarIdentificacao() {
        System.out.println("atualizarIdentificacao");
        PnlCadastroPaciente instance = new PnlCadastroPaciente();
        instance.atualizarIdentificacao();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    */
    /**
     * Test of inserirHistoriaPregressa method, of class pnlCadastroPaciente.
     */
    /*
    @Test
    public void testInserirHistoriaPregressa() {
        System.out.println("inserirHistoriaPregressa");
        PnlCadastroPaciente instance = new PnlCadastroPaciente();
        instance.inserirHistoriaPregressa();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    */
    /**
     * Test of inserirHistoriaFamiliar method, of class pnlCadastroPaciente.
     */
    /*
    @Test
    public void testInserirHistoriaFamiliar() {
        System.out.println("inserirHistoriaFamiliar");
        PnlCadastroPaciente instance = new PnlCadastroPaciente();
        instance.inserirHistoriaFamiliar();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    */
    /**
     * Test of inserirHistoriaDoencaAtual method, of class pnlCadastroPaciente.
     */
    /*
    @Test
    public void testInserirHistoriaDoencaAtual() {
        System.out.println("inserirHistoriaDoencaAtual");
        PnlCadastroPaciente instance = new PnlCadastroPaciente();
        instance.inserirHistoriaDoencaAtual();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    */
    /**
     * Test of inserirQuestionario1 method, of class pnlCadastroPaciente.
     */
    /*
    @Test
    public void testInserirQuestionario1() {
        System.out.println("inserirQuestionario1");
        PnlCadastroPaciente instance = new PnlCadastroPaciente();
        instance.inserirQuestionario1();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    */

    /**
     * Test of inserirQuestionario2 method, of class pnlCadastroPaciente.
     */
    /*
    @Test
    public void testInserirQuestionario2() {
        System.out.println("inserirQuestionario2");
        PnlCadastroPaciente instance = new PnlCadastroPaciente();
        instance.inserirQuestionario2();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    */
    /**
     * Test of inserirCartao method, of class pnlCadastroPaciente.
     */
    /*
    @Test
    public void testInserirCartao() {
        System.out.println("inserirCartao");
        PnlCadastroPaciente instance = new PnlCadastroPaciente();
        instance.inserirCartao();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    */
    /**
     * Test of atualizarCartao method, of class pnlCadastroPaciente.
     */
    /*
    @Test
    public void testAtualizarCartao() {
        System.out.println("atualizarCartao");
        PnlCadastroPaciente instance = new PnlCadastroPaciente();
        instance.atualizarCartao();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    */
    
}
