/*
 * Connection Test -> MySQL DB
 */
package bancodedados;

import java.sql.Connection;
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
        Connection conn = null;
        try {
            conn = MysqlConnect.connectDB();
            assertNotEquals(null, conn);
            if (conn != null) conn.close();
        } catch (SQLException sqle) {
            System.out.println("Erro ao conectar com o Banco de Dados " + sqle.getMessage());
            assertNotEquals(null, conn);
        }   
    }
    
}
