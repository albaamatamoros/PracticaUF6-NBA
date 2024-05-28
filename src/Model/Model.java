package Model;
import Model.Equip.*;
import Model.EstadisticaJugador.*;
import Model.EstadisticsJugadorsHistorics.*;
import Model.Jugador.*;
import Model.Partit.*;
import Vista.Vista;
import com.sun.source.tree.LiteralTree;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.*;

public class Model {
    static Scanner scan = new Scanner(System.in);
    static Random random = new Random();

    //DAO
    //Creem un nou objecte de cada classe dao necessària.
    static EquipDAO equipDAO = new EquipDAO();
    static JugadorDAO jugadorDAO = new JugadorDAO();
    static PartitDAO partitDAO = new PartitDAO();
    static EstadisticaJugadorDAO estadisticaJugadorDAO = new EstadisticaJugadorDAO();
    static EstadisticsJugadorsHistoricsDAO estadisticsJugadorsHistoricsDAO = new EstadisticsJugadorsHistoricsDAO();

    //1.- Llistar tots els jugadors d'un equip
    public static void exercici1(String equipNom) throws Exception {
        //Cridem a la connexió per connectar-nos a una BD.
        Connection connexio = Connexio.getConnection();
        //Fem una llista jugadors per poder guardar i mostrar tots els jugadors de l'equip demanat.
        List<Jugador> jugadors = equipDAO.obtenirJugadors(equipNom,connexio);
        //Mostra la llista de jugadors.
        Vista.llistarJugadorsEquip(jugadors);
    }

    //2.- Calcular la mitjana de punts, rebots, assistències, ... d'un jugador
    public static void exercici2(String jugadorNom) throws Exception {
        Connection connexio = Connexio.getConnection();
        //Utilitzem un LinkedHasMap per mostrar els càlculs de les mitjanes de forma ordenada.
        LinkedHashMap<String,Float> mitjanes = jugadorDAO.calcularMitjana(jugadorNom);
        //Mostrem totes les mitjanes.
        Vista.llistarMitjanes(mitjanes);
    }

    //3.- Llistar tots els partits jugats per un equip amb el seu resultat.
    public static void exercici3(String equipNom) throws Exception {
        Connection connexio = Connexio.getConnection();
        List<Set<Map.Entry<String,Integer>>> llista = equipDAO.obtenirResultatPartits(equipNom);
        Vista.llistarPartitsIResultats(llista);
    }

    //4.- Inserir un nou jugador a un equip.
    public static boolean exercici4(String jugadorNom, String equipNom) throws Exception {
        Connection connexio = Connexio.getConnection();
        if (jugadorDAO.cercarIdPerNom(jugadorNom) == 0) {
            int equipId = equipDAO.cercarIdPerNom(equipNom);
            if (equipId != 0) {
                //Generar el jugador i insertar-lo
                Jugador jugador = generarJugadorAleatori(jugadorNom,equipId);
                boolean correcte = jugadorDAO.insertar(jugador, connexio);

                //Comprovar si s'ha creat correctament.
                if (correcte) Vista.mostrarMissatge("El jugador s'ha registrat correctament");
                else Vista.mostrarMissatge("El jugador no s'ha registrat correctament");
            } else {
                Vista.mostrarMissatge("L'equip no existeix.");
            }
        } else { return true; }
        return false;
    }

    //Generem un jugador aleatoriament per l'exercici 4.
    private static Jugador generarJugadorAleatori(String jugadorNom, int equipId) throws Exception {
        String[] nomComplet = jugadorNom.split(" ");
        String nom;
        String cognom;

        if (nomComplet.length > 2) {
            nom = nomComplet[0] + " " + nomComplet[1];
            cognom = nomComplet[2];
        } else {
            nom = nomComplet[0];
            cognom = nomComplet[1];
        }

        String[] numeros = {"0","1","2","3","4","5","6","7","8","9"};
        String[] posicions = {"Forward","Guard","Center-Forward","Forward-Center","Center","Guard-Forward","Forward-Guard"};

        Date dataNaixementAleatoria = Date.valueOf(LocalDate.of(random.nextInt(3000) + 1,random.nextInt(12) + 1,random.nextInt(28) + 1));
        Float alcadaAleatoria = random.nextFloat(170f,300f);
        Float pesAleatori = random.nextFloat(65f, 150f);
        String dorsalRandom = numeros[random.nextInt(numeros.length)] + numeros[random.nextInt(numeros.length)];
        String posicioAleatoria = posicions[random.nextInt(posicions.length)];

        return new Jugador(nom,cognom,dataNaixementAleatoria,alcadaAleatoria,pesAleatori,dorsalRandom,posicioAleatoria,equipId);
    }

    //5.- Traspassar un judador a un altra equip
    public static void exercici5(String jugadorNom, String equipNom) throws Exception {
        Connection connexio = Connexio.getConnection();
        int jugadorId = jugadorDAO.cercarIdPerNom(jugadorNom);

        if (jugadorId != 0) {
            int equipId = equipDAO.cercarIdPerNom(equipNom);

            if (equipId != 0) {
                Jugador jugador = jugadorDAO.cercar(jugadorId, connexio);
                jugador.setEquipId(equipId);

                boolean correcte = jugadorDAO.actualitzar(jugador, connexio);

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
        Connection connexio = Connexio.getConnection();
        String rutaPython = "./obtenirActualitzacions.py";

        String rutaCsvPartits = "./partits.csv";
        String rutaCsvEstadistiques = "./estadistiques_jugadors.csv";

        File csvPartits = new File(rutaCsvPartits);
        File csvEstadistiques = new File(rutaCsvEstadistiques);

        if (!(csvPartits.exists() && csvEstadistiques.exists())) {
            ProcessBuilder pb = new ProcessBuilder("python",rutaPython);
            Process proces = pb.start();
            int codiSortida = proces.waitFor();

            if (codiSortida != 0) {
                throw new Exception("Ha ocurregut un error en l'execució del script Python");
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
    public static void exercici7(String jugadorNom, int partitID) throws Exception {
        Connection connexio = Connexio.getConnection();
        int jugadorId = jugadorDAO.cercarIdPerNom(jugadorNom);

        if (jugadorId != 0) {
            int partitID = partitDAO.cercarPartit(partitID);
            if (partitID != 0) {
                EstadisticaJugador estadisticaJugador = estadisticaJugadorDAO.obtenirEstadistiquesModificables(jugadorId, partitID, connexio)

                if (estadisticaJugador == null) {
                    EstadisticaJugadorHistoric estadisticaJugador = new estadisticaJugadorEscollit (jugadorId,
                            jugador.getNom(),
                            jugador.getCognom(),
                            estadistica.getEquipId(),
                            estadistica.getPartitId(),
                            estadistica.getMinutsJugats(),
                            estadistica.getPunts(),
                            estadistica.getTirsAnotats(),
                            estadistica.getTirsTirats(),
                            estadistica.getTirsTriplesAnotats(),
                            estadistica.getTirsTriplesTirats(),
                            estadistica.getTirsLliuresAnotats(),
                            estadistica.getTirsLliuresTirats(),
                            estadistica.getRebotsOfensius(),
                            estadistica.getRebotsDefensius(),
                            estadistica.getAssistencies(),
                            estadistica.getRobades(),
                            estadistica.getBloqueigs());

                    eestadisticaJugadorEscollit = estadisticaJugador;
                }
            }
        }
    }

    //8.- Retirar (Eliminar) un jugador.
    public static void exercici8(String jugadorNom) throws Exception {
        Connection connexio = Connexio.getConnection();
        int jugadorId = jugadorDAO.cercarIdPerNom(jugadorNom);

        if (jugadorId != 0) {
            Jugador jugador = jugadorDAO.cercar(jugadorId, connexio);
            List<EstadisticaJugador> estadistiques = estadisticaJugadorDAO.obtenirEstadistiques(jugadorId);
            List<EstadisticaJugadorHistoric> estadistiquesHistoriques = new ArrayList<>();

            for (EstadisticaJugador estadistica : estadistiques) {
                EstadisticaJugadorHistoric eH = new EstadisticaJugadorHistoric(jugadorId,
                        jugador.getNom(),
                        jugador.getCognom(),
                        estadistica.getEquipId(),
                        estadistica.getPartitId(),
                        estadistica.getMinutsJugats(),
                        estadistica.getPunts(),
                        estadistica.getTirsAnotats(),
                        estadistica.getTirsTirats(),
                        estadistica.getTirsTriplesAnotats(),
                        estadistica.getTirsTriplesTirats(),
                        estadistica.getTirsLliuresAnotats(),
                        estadistica.getTirsLliuresTirats(),
                        estadistica.getRebotsOfensius(),
                        estadistica.getRebotsDefensius(),
                        estadistica.getAssistencies(),
                        estadistica.getRobades(),
                        estadistica.getBloqueigs());

                estadistiquesHistoriques.add(eH);
            }

            estadisticsJugadorsHistoricsDAO.traspassarEstadistiques(estadistiquesHistoriques);
        }
    }

    //9.- Canviar nom franquícia d’un equip
    public static void exercici9(String equipNom, String franquiciaNom) throws Exception {
        Connection connexio = Connexio.getConnection();
        int equipId = equipDAO.cercarIdPerNom(equipNom);
        if (equipId != 0){
            Equip equip = equipDAO.cercar(equipId, connexio);
            equip.setCiutat(franquiciaNom);

            boolean correcte = equipDAO.actualitzar(equip, connexio);

            if (correcte) {
                Vista.mostrarMissatge("S'ha actualitzat correctament");
            } else {
                Vista.mostrarMissatge("No s'ha actualitzat la franquícia");
            }
        } else Vista.mostrarMissatge("L'equip no existeix");
    }
}
