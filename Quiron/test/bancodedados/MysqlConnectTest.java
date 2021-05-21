/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bancodedados;

import java.sql.Connection;
import java.sql.DriverManager;
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
    
    private MysqlConnect conexao;
    
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
     * @throws java.lang.Exception
     */
    @Test
    public void testConnectDB() throws Exception {
        assertEquals("com.mysql.jdbc.JDBC4Connection@6f79caec", conexao.connectDB());
    }
    
}
