package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Model {

    //10.94.255.99 Santi PhpMyAdmin 3336
    //nba_2023-24
    //root mas

    public static final String URL = "jdbc:mysql://10.94.255.99:3336/nba_2023-24";
    public static final String USER = "root";
    public static final String PASSWORD = "mas";
    //
    public static void bd(){
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

    //1. Llistar tots els jugadors d'un equip
    public static void llistarJugadorsEquip(){

    }

    //2. Calcular la mitjana de punts, rebots, assistències, ... d'un jugador
    public static void calcularMitjanaJugador(){

    }

    //3. Llistar tots els partits jugats per un equip amb el seu resultat
    public static void llistarPartitsJugats(){

    }

    //4. Inserir un nou jugador a un equip.
    public static void inserirJugadorAEquip(){

    }

    //5. Traspassar un judador a un altra equip
    public static void traspassarJugadorAltreEquip(){

    }

    //6. Actualitzar les dades de jugadors o equips després d'un partit.
    public static void actualitzarDadesJugador(){

    }

    //7. Modificar les estadístiques d’un jugador
    public static void modifciarEstadistiquesJugador(){

    }

    //8. Retirar (Eliminar) un jugador.
    public static void eliminarJugador(){

    }

    //9. Canviar nom franquícia d’un equip
    public static void  canviarNomEquip(){

    }
}
