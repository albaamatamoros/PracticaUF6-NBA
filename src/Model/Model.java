package Model;
import Model.Equip.EquipDAO;
import Model.Jugador.Jugador;
import Model.Jugador.JugadorDAO;
import Vista.Vista;
import java.sql.Date;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Model {
    static Scanner scan = new Scanner(System.in);

    //DAO
    static EquipDAO equipDAO = new EquipDAO();
    static JugadorDAO jugadorDAO = new JugadorDAO();

    public static void exercici1(String equipNom) throws Exception {
        List<Jugador> jugadors = equipDAO.obtenirJugadors(equipNom);
        Vista.llistarJugadorsEquip(jugadors);
    }

    public static void exercici2() throws Exception{

    }

    public static void exercici3(String equipNom) throws Exception {
        List<Set<Map.Entry<String,Integer>>> llista = equipDAO.obtenirResultatPartits(equipNom);
        Vista.llistarPartitsIResultats(llista);
    }

    public static boolean exercici4(String jugadorNom, String equipNom) throws Exception {
        if (jugadorDAO.cercarIdPerNom(jugadorNom) == 0) {
            if (equipDAO.cercarIdPerNom(equipNom) != 0) {
                //Crear el jugador i insertar-lo

                String[] nomCognom = jugadorNom.split(" ");
                String nom;
                String cognom;

                if (nomCognom.length > 2) {
                    nom = nomCognom[0] + " " + nomCognom[1];
                    cognom = nomCognom[2];
                } else {
                    nom = nomCognom[0];
                    cognom = nomCognom[1];
                }
                Jugador jugador = new Jugador(nom,cognom,Date.valueOf("2003-12-03"),190.56f,110.25f, "05","Forward" , equipDAO.cercarIdPerNom(equipNom));

                boolean correcte = jugadorDAO.insertar(jugador);

                //Comprovar si s'ha creat correctament.
                if (correcte) Vista.mostrarMissatge("El jugador s'ha registrat correctament");
                else Vista.mostrarMissatge("El jugador no s'ha registrat correctament");
            } else {
                Vista.mostrarMissatge("L'equip no existeix.");
            }
        } else { return true;

        }
        return false;
    }

    public static void exercici5(String jugadorNom, String equipNom) throws Exception {
        int jugadorId = jugadorDAO.cercarIdPerNom(jugadorNom);

        if (jugadorId != 0) {
            int equipId = equipDAO.cercarIdPerNom(equipNom);

            if (equipId != 0) {
                Jugador jugador = jugadorDAO.cercar(jugadorId);
                jugador.setEquipId(equipId);

                boolean correcte = jugadorDAO.actualitzar(jugador);

                if (correcte) {
                    Vista.mostrarMissatge("S'ha traspassat correctament");
                } else {
                    Vista.mostrarMissatge("No s'ha traspassat el jugador");
                }
            } else { Vista.mostrarMissatge("L'equip no existeix"); }
        } else { Vista.mostrarMissatge("El jugador no existeix."); }
    }

    public static void exercici6(){

    }

    public static void exercici7(){

    }

    public static void exercici8(){

    }

    public static void exercici9(){

    }
}
