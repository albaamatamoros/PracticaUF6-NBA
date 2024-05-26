package Model;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.DriverManager;

public class Connexio {

    //PHPMYADMIN/10.94.255.99 Santi PhpMyAdmin 3336
    //nba_2023-24
    //root mas

    private static final String URL = "jdbc:mysql://10.94.255.99:3306/nba_2023-24";
    private static final String URL2 = "jdbc:mysql://localhost:3306/nba_2023-24";
    private static final String USUARI = "root";
    private static final String PASSWORD = "mas";

    //MAQUINA BD/192.168.56.103:3306
    //nba_2023_24
    //perepi pastanaga

    private static final String URL3 = "jdbc:mysql://192.168.56.103:3306/nba_2023_24";
    private static final String USUARI2 = "perepi";
    private static final String PASSWORD2 = "pastanaga";


    public static Connection getConnection() throws SQLException {
        try {
            return DriverManager.getConnection(URL,USUARI,PASSWORD);
        } catch (SQLException e) {
            try {
                return DriverManager.getConnection(URL2,USUARI,PASSWORD);
            } catch (SQLException e1) {
                return DriverManager.getConnection(URL3,USUARI2,PASSWORD2);
            }
        }
    }

}
