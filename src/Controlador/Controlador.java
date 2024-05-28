package Controlador;
import Model.EstadisticaJugador.EstadisticaJugador;
import Vista.Vista;
import Model.Model;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;
import Model.Connexio;

//Classe controlador on demanem les dades a l'usuari.
public class Controlador {
    static Scanner scan = new Scanner(System.in);

    //VARIABLES:
    //Opcio Menu
    public static String opcio = "";

    //Variable per rebre el nom de l'equip.
    static String equipNom;

    //Variable per rebre el nom del jugador.
    public static String jugadorNom;

    //Variable per rebre la nova franquícia.
    public static String franquiciaNom;

    //Variable per rebre un partit_id
    public static int partitID;

    //Opcio2 MenuModificacions
    public static String opcio2 = "";

    //Variable ex7 per saber si s'ha modificat o no un valor
    public static boolean modificacio = false;

    public static void consultas() throws SQLException {
        Vista.mostrarMissatge("------ BENVINGUT ------");
        //Cridem a la connexió per connectar-nos a una BD.
        Connection connexio = Connexio.getConnection();
        try {
            do {
                //Canviem el limitador de scan perquè agafi espais. (Per evitar problemes amb els espais dels noms)
                scan.useDelimiter("\\n");
                //Cridem al menú
                Vista.menuInicial();
                opcio = scan.nextLine();
                switch (opcio) {
                    case "1":
                        Vista.mostrarMissatge("Introdueix el nom d'un equip per llistar els seus jugadors: (Ex: Denver Nuggets)");
                        equipNom = scan.nextLine();
                        //REGEX amb excepció per no permetre inserir números en l'equip.
                        if (equipNom.matches(".*\\d.*")) throw new Exception("El nom d'un equip no pot contenir números.");

                        //Cridem l'excercici 1
                        Model.exercici1(equipNom,connexio);
                        break;
                    case "2":
                        Vista.mostrarMissatge("Introdueix un jugador per calcular la seva mitjana de punts: (Ex: LeBron James)");
                        jugadorNom = scan.nextLine();
                        //REGEX amb excepció per no permetre inserir números en el nom.
                        if (jugadorNom.matches(".*\\d.*")) throw new Exception("Un nom només pot contenir lletres.");

                        //Cridem l'excercici 2
                        Model.exercici2(jugadorNom,connexio);
                        break;
                    case "3":
                        Vista.mostrarMissatge("Introdueix el nom d'un equip per llistar tots els partits i els seus resultats: (Ex: Denver Nuggets)");
                        equipNom = scan.nextLine();
                        if (equipNom.matches(".*\\d.*")) throw new Exception("El nom d'un equip no pot contenir números.");

                        //Cridem l'excercici 3
                        Model.exercici3(equipNom,connexio);
                        break;
                    case "4":
                        Vista.mostrarMissatge("Introdueix un jugador per inserir a la taula: (Ex: LeBron James)");
                        jugadorNom = scan.nextLine();
                        if (jugadorNom.matches(".*\\d.*")) throw new Exception("Un nom només pot contenir lletres.");
                        Vista.mostrarMissatge("Introdueix un equip on unir a aquest jugador: (Ex: Denver Nuggets)");
                        equipNom = scan.nextLine();
                        if (equipNom.matches(".*\\d.*")) throw new Exception("El nom d'un equip no pot contenir números.");

                        //Cridem l'excercici 4
                        boolean jaExisteix = Model.exercici4(jugadorNom,equipNom,connexio);

                        //Si el jugador ja existeix, preguntem a l'usuari si vol traspassar-lo o no.
                        if (jaExisteix) {
                            Vista.mostrarMissatge("El jugador ja existeix, vols traspassar-lo a un altre equip? (S/n)");
                            //Variable Si/No per preguntar al usuari.
                            String opcioSiNo;
                            opcioSiNo = scan.nextLine().toUpperCase();
                            if (opcioSiNo.equals("S") || opcioSiNo.isEmpty()) {
                                Model.exercici5(jugadorNom,equipNom,connexio);
                            }
                        }
                        break;
                    case "5":
                        Vista.mostrarMissatge("Introdueix un jugador per trasspasar a un altre equip: (Ex: LeBron James)");
                        jugadorNom = scan.nextLine();
                        if (jugadorNom.matches(".*\\d.*")) throw new Exception("Un nom només pot contenir lletres.");
                        Vista.mostrarMissatge("Introdueix un equip on trasspasar aquest jugador (Ex: Denver Nuggets)");
                        equipNom = scan.nextLine();
                        if (equipNom.matches(".*\\d.*")) throw new Exception("El nom d'un equip no pot contenir números.");

                        //Cridem l'excercici 5
                        Model.exercici5(jugadorNom,equipNom,connexio);
                        break;
                    case "6":
                        //Cridem l'excercici 6
                        Model.exercici6(connexio);
                        break;
                    case "7":
                        Vista.mostrarMissatge("Introdueix un jugador per modificar les seves estadístiques: (Ex: LeBron James)");
                        jugadorNom = scan.nextLine();
                        if (jugadorNom.matches(".*\\d.*")) throw new Exception("Un nom només pot contenir lletres.");
                        Vista.mostrarMissatge("Introdueix l'ID d'un partit per modificar les seves estadístiques: (Ex: 22300015)");
                        partitID = scan.nextInt();
                        scan.nextLine();

                        //Cridem exercici 7.
                        EstadisticaJugador eJugador = Model.exercici7(jugadorNom, partitID,connexio);
                        try {
                            do {
                                //Cridem al menú per veure quines opcions es poden modificar
                                Vista.menúModificarEstadistiques();
                                opcio2 = scan.nextLine();

                                switch (opcio2) {
                                    case "1":
                                        //NOU minuts jugats
                                        Vista.mostrarMissatge("Introdueix el nou valor per la columna (Minuts jugats):");
                                        float nouMinutsJugats = scan.nextFloat();
                                        scan.nextLine();
                                        eJugador.setMinutsJugats(nouMinutsJugats);
                                        modificacio = true;
                                        break;
                                    case "2":
                                        //NOU punts
                                        Vista.mostrarMissatge("Introdueix el nou valor per la columna (Punts):");
                                        int nouPunts = scan.nextInt();
                                        scan.nextLine();
                                        eJugador.setPunts(nouPunts);
                                        modificacio = true;
                                        break;
                                    case "3":
                                        //NOU tirs anotats
                                        Vista.mostrarMissatge("Introdueix el nou valor per la columna (Tirs anotats):");
                                        int nouTirsAnotats = scan.nextInt();
                                        scan.nextLine();
                                        eJugador.setTirsAnotats(nouTirsAnotats);
                                        modificacio = true;
                                        break;
                                    case "4":
                                        //NOU tirs tirats
                                        Vista.mostrarMissatge("Introdueix el nou valor per la columna (Tirs tirats):");
                                        int nouTirsTirats = scan.nextInt();
                                        scan.nextLine();
                                        eJugador.setTirsTirats(nouTirsTirats);
                                        modificacio = true;
                                        break;
                                    case "5":
                                        //NOU tirs triples anotats
                                        Vista.mostrarMissatge("Introdueix el nou valor per la columna (Tirs triples anotats):");
                                        int nouTirsTriplesAnotats = scan.nextInt();
                                        scan.nextLine();
                                        eJugador.setTirsTriplesAnotats(nouTirsTriplesAnotats);
                                        modificacio = true;
                                        break;
                                    case "6":
                                        //NOU tirs triples tirats
                                        Vista.mostrarMissatge("Introdueix el nou valor per la columna (Tirs triples tirats):");
                                        int nouTirsTriplesTirats = scan.nextInt();
                                        scan.nextLine();
                                        eJugador.setTirsTriplesTirats(nouTirsTriplesTirats);
                                        modificacio = true;
                                        break;
                                    case "7":
                                        //NOU tirs lliures anotats
                                        Vista.mostrarMissatge("Introdueix el nou valor per la columna (Tirs lliures anotats):");
                                        int nouTirsLliuresAnotats = scan.nextInt();
                                        scan.nextLine();
                                        eJugador.setTirsLliuresAnotats(nouTirsLliuresAnotats);
                                        modificacio = true;
                                        break;
                                    case "8":
                                        //NOU tirs triples tirats
                                        Vista.mostrarMissatge("Introdueix el nou valor per la columna (Tirs lliures tirats):");
                                        int nouTirsLliuresTirats = scan.nextInt();
                                        scan.nextLine();
                                        eJugador.setTirsLliuresTirats(nouTirsLliuresTirats);
                                        modificacio = true;
                                        break;
                                    case "9":
                                        //NOU Rebots ofensius
                                        Vista.mostrarMissatge("Introdueix el nou valor per la columna (Rebots ofensius):");
                                        int nouRebotsOfensius = scan.nextInt();
                                        scan.nextLine();
                                        eJugador.setRebotsOfensius(nouRebotsOfensius);
                                        modificacio = true;
                                        break;
                                    case "10":
                                        //NOU rebots defensius
                                        Vista.mostrarMissatge("Introdueix el nou valor per la columna (Rebots defensius):");
                                        int nouRebotsDefensius = scan.nextInt();
                                        scan.nextLine();
                                        eJugador.setRebotsDefensius(nouRebotsDefensius);
                                        modificacio = true;
                                        break;
                                    case "11":
                                        //NOU assistencies
                                        Vista.mostrarMissatge("Introdueix el nou valor per la columna (Assistencies):");
                                        int nouAssistencies = scan.nextInt();
                                        scan.nextLine();
                                        eJugador.setAssistencies(nouAssistencies);
                                        modificacio = true;
                                        break;
                                    case "12":
                                        //NOU robades
                                        Vista.mostrarMissatge("Introdueix el nou valor per la columna (Robades):");
                                        int nouRobades = scan.nextInt();
                                        scan.nextLine();
                                        eJugador.setRobades(nouRobades);
                                        modificacio = true;
                                        break;
                                    case "13":
                                        //NOU bloqueig
                                        Vista.mostrarMissatge("Introdueix el nou valor per la columna (Bloqueig):");
                                        int nouBloqueig = scan.nextInt();
                                        scan.nextLine();
                                        eJugador.setBloqueigs(nouBloqueig);
                                        modificacio = true;
                                        break;
                                    case "0":
                                        //Actualitzar les dades modificades.
                                        Model.exercici7P2(eJugador, modificacio, connexio);
                                        break;
                                    default:
                                        Vista.mostrarMissatge("-------------------------------");
                                        Vista.mostrarMissatge("ATENCIÓ! Ha de ser entre 0 i 13");
                                        Vista.mostrarMissatge("-------------------------------");
                                }
                            } while (!(opcio2.equals("0")));
                        } catch (InputMismatchException e){
                            Vista.mostrarMissatge("EXCEPCIÓ, ves amb compte: Dades incorrectes, recorda que només poden ser números");
                        } catch (Exception e) {
                            Vista.mostrarMissatge("EXCEPCIÓ, ves amb compte: " + e.getMessage());
                        }
                        break;
                    case "8":
                        Vista.mostrarMissatge("Introdueix un jugador per eliminar-lo: (Ex: LeBron James)");
                        jugadorNom = scan.nextLine();
                        if (jugadorNom.matches(".*\\d.*")) throw new Exception("Un nom només pot contenir lletres.");

                        //Cridem l'excercici 8
                        Model.exercici8(jugadorNom,connexio);
                        break;
                    case "9":
                        Vista.mostrarMissatge("Introdueix un equip per modificar la seva franquícia: (Ex: Denver Nuggets)");
                        equipNom = scan.nextLine();
                        //REGEX amb excepció per no permetre inserir números en la franquícia.
                        if (equipNom.matches(".*\\d.*")) throw new Exception("El nom d'un equip no pot contenir números.");
                        Vista.mostrarMissatge("Introdueix la nova franquícia:");
                        franquiciaNom = scan.nextLine();
                        if (jugadorNom.matches(".*\\d.*")) throw new Exception("Una franquícia no pot contenir números.");

                        //Cridem l'excercici 9
                        Model.exercici9(equipNom,franquiciaNom,connexio);
                        break;
                    case "0":
                        break;
                    default:
                        Vista.mostrarMissatge("-------------------------------");
                        Vista.mostrarMissatge("ATENCIÓ! Ha de ser entre 0 i 9");
                        Vista.mostrarMissatge("-------------------------------");
                }
            } while (!(opcio.equals("0")));
        } catch (Exception e){
            Vista.mostrarMissatge("EXCEPCIÓ, ves amb compte: " + e.getMessage());
        }
    }
}
