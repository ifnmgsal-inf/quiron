/*
 * Classe que faz a conexão com o banco de dados.
 */
package bancodedados;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Franciele Alves Barbosa e Rogério Costa Negro Rocha
 */
public class MysqlConnect {

    private static final String URL = "jdbc:mysql://localhost:3306/bdenfermaria";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    public static Connection connectDB() throws SQLException {
        Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
        return conn;
    }
}
