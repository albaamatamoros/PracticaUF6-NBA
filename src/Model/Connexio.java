package Model;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.DriverManager;

public class Connexio {

    //10.94.255.99 Santi PhpMyAdmin 3336
    //nba_2023-24
    //root mas

    private static final String URL = "jdbc:mysql://10.94.255.99:3336/nba_2023-24";
    private static final String USUARI = "root";
    private static final String PASSWORD = "mas";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL,USUARI,PASSWORD);
    }

}
