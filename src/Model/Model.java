package Model;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Model {

    //10.94.255.99 Santi PhpMyAdmin 3336
    //nba_2023-24
    //root mas

    private static final String URL = "jdbc:mysql://localhost:3336/nba_2023-24";
    private static final String USUARI = "root";
    private static final String PASSWORD = "mas";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL,USUARI,PASSWORD);
    }



    /*
    public static final String URL = "jdbc:mysql://10.94.255.99:3336/nba_2023-24";
    public static final String USER = "root";
    public static final String PASSWORD = "mas";


    public static void bd() {
        try {
            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);

            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("SELECT nom,equip_id FROM equips");

            while (resultSet.next()) {
                System.out.print("Nom: ");
                System.out.print(resultSet.getString("nom"));
                System.out.print(", Equip_id: ");
                System.out.println(resultSet.getString("equip_id"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    */
}
