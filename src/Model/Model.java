package Model;
import Model.Equip.Equip;
import Model.Equip.EquipDAO;
import Model.EstadisticaJugador.EstadisticaJugador;
import Model.EstadisticaJugador.EstadisticaJugadorDAO;
import Model.EstadisticsJugadorsHistorics.EstadisticaJugadorHistoric;
import Model.Jugador.Jugador;
import Model.Jugador.JugadorDAO;
import Model.Partit.Partit;
import Model.Partit.PartitDAO;
import Vista.Vista;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
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
    static PartitDAO partitDAO = new PartitDAO();
    static EstadisticaJugadorDAO estadisticaJugadorDAO = new EstadisticaJugadorDAO();

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

                Jugador jugador = new Jugador(nom,cognom,
                        Date.valueOf("2003-12-03"),
                        190.56f,
                        110.25f,
                        "05",
                        "Forward" ,
                        equipDAO.cercarIdPerNom(equipNom));

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
                    Vista.mostrarMissatge("No s'ha pogut traspassar el jugador");
                }
            } else { Vista.mostrarMissatge("L'equip no existeix"); }
        } else { Vista.mostrarMissatge("El jugador no existeix."); }
    }

    //6.- Actualitzar les dades de jugadors o equips després d'un partit.
    public static void exercici6() throws Exception {
        String rutaPython = "obtenirActualitzacions.py";

        String rutaCsvPartits = "partits.csv";
        String rutaCsvEstadistiques = "estadistiques_jugadors.csv";

        File csvPartits = new File(rutaCsvPartits);
        File csvEstadistiques = new File(rutaCsvEstadistiques);

        if (!(csvPartits.exists() && csvEstadistiques.exists())) {
            ProcessBuilder pb = new ProcessBuilder("python",rutaPython);
            Process proces = pb.start();
            int codiSortida = proces.waitFor();

            if (codiSortida != 0) {
                throw new Exception("Ha ocurregut un error en l'execució del script");
            }
        }

        BufferedReader brCsvPartits = new BufferedReader(new FileReader(csvPartits));
        BufferedReader brCsvEstadistiques = new BufferedReader(new FileReader(csvEstadistiques));
        String linia;

        List<Partit> partits = new ArrayList<>();
        List<EstadisticaJugador> estadistiquesJugadors = new ArrayList<>();

        brCsvPartits.readLine();
        while ((linia = brCsvPartits.readLine()) != null) {
            String[] camps = linia.split(";");

            Partit partit = new Partit(Integer.parseInt(camps[0]),
                    Integer.parseInt(camps[1]),
                    Date.valueOf(camps[2]),
                    camps[3],
                    camps[4]);

            partits.add(partit);
        }

        brCsvEstadistiques.readLine();
        while ((linia = brCsvEstadistiques.readLine()) != null) {
            String[] camps = linia.split(";");

            EstadisticaJugador estadisticaJugador = new EstadisticaJugador(Integer.parseInt(camps[0]),
                    Integer.parseInt(camps[1]),
                    Integer.parseInt(camps[2]),
                    Float.parseFloat(camps[3]),
                    Integer.parseInt(camps[4]),
                    Integer.parseInt(camps[5]),
                    Integer.parseInt(camps[6]),
                    Integer.parseInt(camps[7]),
                    Integer.parseInt(camps[8]),
                    Integer.parseInt(camps[9]),
                    Integer.parseInt(camps[10]),
                    Integer.parseInt(camps[11]),
                    Integer.parseInt(camps[12]),
                    Integer.parseInt(camps[13]),
                    Integer.parseInt(camps[14]),
                    Integer.parseInt(camps[15]));

            estadistiquesJugadors.add(estadisticaJugador);
        }

        boolean correctePartits = partitDAO.actualitzarEnMassa(partits);
        boolean correcteEstadistiques = estadisticaJugadorDAO.actualitzarEnMassa(estadistiquesJugadors);

        if (correctePartits && correcteEstadistiques) {
            Vista.mostrarMissatge("S'han actualitzat les dades correctament");
        } else {
            Vista.mostrarMissatge("No s'han pogut actualitzar les dades");
        }
    }

    //7.- Modificar les estadístiques d’un jugador
    public static void exercici7(String jugadorNom) throws Exception {

    }

    //8.- Retirar (Eliminar) un jugador.
    public static void exercici8(String jugadorNom) throws Exception {
        int jugadorId = jugadorDAO.cercarIdPerNom(jugadorNom);

        if (jugadorId != 0) {
            estadisticaJugadorDAO.traspasarEstadistiques(jugadorId);
            //EstadisticaJugadorHistoric estadisticaJugadorHistoric = new EstadisticaJugadorHistoric();
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
