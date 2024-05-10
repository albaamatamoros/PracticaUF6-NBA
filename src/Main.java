import Controlador.Controlador;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import Controlador.Controlador;

public class Main {

    //10.94.255.99 Santi PhpMyAdmin 3336
    //nba_2023-24
    //root mas

    public static final String URL = "jdbc:mysql://10.94.255.99:3336/nba_2023-24";
    public static final String USER = "root";
    public static final String PASSWORD = "mas";
    public static void main(String[] args) {
        Controlador.consultas();

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
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}