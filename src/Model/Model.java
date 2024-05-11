package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Model {
    private static final String URL = "jdbc:mysql://localhost:3336/nba_2023-24";
    private static final String USUARI = "root";
    private static final String PASSWORD = "mas";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL,USUARI,PASSWORD);
    }
}
