/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bancodedados;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author roger
 */
public class MysqlConnectTest {
    
    //private MysqlConnect conexao;
    
    public MysqlConnectTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of connectDB method, of class MysqlConnect.
     */
    @Test
    public void testConnectDB() {
        try {
            Connection conn = MysqlConnect.connectDB();
            assertNotEquals(null, conn);
        } catch (SQLException sqle) {
            System.out.println("Erro ao conectar com o Banco de Dados " + sqle.getMessage());
        }    
    }
    
}
