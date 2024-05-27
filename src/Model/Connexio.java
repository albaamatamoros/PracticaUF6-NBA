package Model;
import Controlador.Controlador;
import Vista.Vista;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.DriverManager;

//Classe connexió amb la connexió a la base de dades.
public class Connexio {

    //PHPMYADMIN: 10.94.255.99 Santi PhpMyAdmin 3336
    //nba_2023-24
    //root mas

    private static final String URL = "jdbc:mysql://10.94.255.99:3306/nba_2023-24";
    private static final String URL2 = "jdbc:mysql://localhost:3306/nba_2023-24";
    private static final String USUARI = "root";
    private static final String PASSWORD = "mas";

    //MAQUINA BD: 192.168.56.103:3306
    //nba_2023_24
    //perepi pastanaga

    private static final String URL3 = "jdbc:mysql://192.168.56.103:3306/nba_2023_24";
    private static final String USUARI2 = "perepi";
    private static final String PASSWORD2 = "pastanaga";

    //Comprovem si la base de dades principals és operativa, si no és el cas donarà una excepció que tractarem intent connectarà amb la BD secundària.
    public static Connection getConnection() throws SQLException {
        try {
            return DriverManager.getConnection(URL,USUARI,PASSWORD);
        } catch (SQLException e) {
            try {
                Vista.mostrarMissatge("Connectant... 192.168.56.103:3306");
                return DriverManager.getConnection(URL2,USUARI,PASSWORD);
            } catch (SQLException e1) {
                try {
                    Vista.mostrarMissatge("Connectant.... localhost:3306");
                    return DriverManager.getConnection(URL3,USUARI2,PASSWORD2);
                } catch (SQLException e2) {
                    Vista.mostrarMissatge("No s'ha pogut connectar amb cap BD");
                }
            }
        }
        Controlador.consultas();
        return null;
    }
}
