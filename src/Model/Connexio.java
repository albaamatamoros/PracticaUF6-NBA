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

    //PER FER PROVES CORRECTAMENT I QUE NO TRIGUI MASSA EN CARREGAR,
    //RECOMANEM AFEGIR LA BASE DE DADES QUE SABEM QUE FUNCIONARA A LA PRIMERA EN AQUESTS VARIABLES PERQUÈ S'EXECUTI A LA PRIMERA
    //(URL, USUARI, PASSWORD).

    //Comprovem si la base de dades principals és operativa, si no és el cas donarà una excepció que tractarem intent connectarà amb les següents BD.
    public static Connection getConnection() throws SQLException {
        Vista.mostrarMissatge("Connectant amb la base de dades, tingui paciència...");
        String[] urls = {URL, URL2, URL3}, usuaris = {USUARI, USUARI, USUARI2}, passwords = {PASSWORD, PASSWORD, PASSWORD2};
        Connection connection = null;

        for (int i = 0; i < urls.length; i++) {

            String url = urls[i];
            String usuario = usuaris[i];
            String password = passwords[i];

            try {
                connection = DriverManager.getConnection(url, usuario, password);
                if (connection != null) {
                    Vista.mostrarMissatge("Connexió exitosa amb " + url);
                    break;
                }
            } catch (SQLException e) {
                Vista.mostrarMissatge("Connectant... " + url);
            }
        }
        if (connection == null) {
            Vista.mostrarMissatge("No s'ha pogut connectar amb cap BD");
            System.exit(0);
        }
        return connection;
    }
}
