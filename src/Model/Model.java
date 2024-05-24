package Model;
import Model.Equip.Equip;
import Model.Equip.EquipDAO;
import Model.Jugador.Jugador;
import Model.Jugador.JugadorDAO;
import Vista.Vista;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.*;

public class Model {
    static Scanner scan = new Scanner(System.in);

    //DAO
    static EquipDAO equipDAO = new EquipDAO();
    static JugadorDAO jugadorDAO = new JugadorDAO();

    //1.- Llistar tots els jugadors d'un equip
    public static void exercici1(String equipNom) throws Exception {
        List<Jugador> jugadors = equipDAO.obtenirJugadors(equipNom);
        Vista.llistarJugadorsEquip(jugadors);
    }

    //2.- Calcular la mitjana de punts, rebots, assistències, ... d'un jugador
    public static void exercici2(String jugadorNom) throws Exception {
        LinkedHashMap<String,Float> mitjanes = jugadorDAO.calcularMitjana(jugadorNom);
        Vista.llistarMitjanes(mitjanes);
    }

    //3.- Llistar tots els partits jugats per un equip amb el seu resultat.
    public static void exercici3(String equipNom) throws Exception {
        List<Set<Map.Entry<String,Integer>>> llista = equipDAO.obtenirResultatPartits(equipNom);
        Vista.llistarPartitsIResultats(llista);
    }

    //4.- Inserir un nou jugador a un equip.
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
        } else { return true; }
        return false;
    }

    //5.- Traspassar un judador a un altra equip
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

    //6.- Actualitzar les dades de jugadors o equips després d'un partit.
    public static void exercici6(){

    }

    //7.- Modificar les estadístiques d’un jugador
    public static void exercici7(String jugadorNom) throws Exception {

    }

    //8.- Retirar (Eliminar) un jugador.
    public static void exercici8(String jugadorNom) throws Exception {
        int jugadorId = jugadorDAO.cercarIdPerNom(jugadorNom);

        if (jugadorId != 0) {

        }
    }

    //9.- Canviar nom franquícia d’un equip
    public static void exercici9(String equipNom, String franquiciaNom) throws Exception {
        int equipId = equipDAO.cercarIdPerNom(equipNom);
        if (equipId != 0){
            Equip equip = equipDAO.cercar(equipId);
            equip.setCiutat(franquiciaNom);

            boolean correcte = equipDAO.actualitzar(equip);

            if (correcte) {
                Vista.mostrarMissatge("S'ha actualitzat correctament");
            } else {
                Vista.mostrarMissatge("No s'ha actualitzat la franquícia");
            }
        } else Vista.mostrarMissatge("L'equip no existeix");
    }
}
