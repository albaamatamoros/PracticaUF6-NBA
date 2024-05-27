package Controlador;
import Vista.Vista;
import Model.Model;
import java.util.Scanner;

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

    public static void consultas() {
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
                        if (!equipNom.matches("^[a-zA-Z]+$")) throw new Exception("El nom d'un equip no pot contenir números.");
                        //Cridem l'excercici 1
                        Model.exercici1(equipNom);
                        break;
                    case "2":
                        Vista.mostrarMissatge("Introdueix un jugador per calcular la seva mitjana de punts: (Ex: LeBron James)");
                        jugadorNom = scan.nextLine();
                        //REGEX amb excepció per no permetre inserir números en el nom.
                        if (!jugadorNom.matches("^[a-zA-Z]+$")) throw new Exception("Un nom només pot contenir lletres.");
                        Model.exercici2(jugadorNom);
                        break;
                    case "3":
                        Vista.mostrarMissatge("Introdueix el nom d'un equip per llistar tots els partits i els seus resultats: (Ex: Denver Nuggets)");
                        equipNom = scan.nextLine();
                        if (!equipNom.matches("^[a-zA-Z]+$")) throw new Exception("El nom d'un equip no pot contenir números.");
                        //Cridem l'excercici 3
                        Model.exercici3(equipNom);
                        break;
                    case "4":
                        Vista.mostrarMissatge("Introdueix un jugador per inserir a la taula: (Ex: LeBron James)");
                        jugadorNom = scan.nextLine();
                        if (!jugadorNom.matches("^[a-zA-Z]+$")) throw new Exception("Un nom només pot contenir lletres.");
                        Vista.mostrarMissatge("Introdueix un equip on unir a aquest jugador: (Ex: Denver Nuggets)");
                        equipNom = scan.nextLine();
                        if (!equipNom.matches("^[a-zA-Z]+$")) throw new Exception("El nom d'un equip no pot contenir números.");

                        //Cridem l'excercici 4
                        boolean jaExisteix = Model.exercici4(jugadorNom,equipNom);

                        //Si el jugador ja existeix, preguntem a l'usuari si vol traspassar-lo o no.
                        if (jaExisteix) {
                            Vista.mostrarMissatge("El jugador ja existeix, vols traspassar-lo a un altre equip? (S/n)");
                            //Variable Si/No per preguntar al usuari.
                            String opcioSiNo;
                            opcioSiNo = scan.nextLine().toUpperCase();
                            if (opcioSiNo.equals("S") || opcioSiNo.isEmpty()) {
                                Model.exercici5(jugadorNom,equipNom);
                            }
                        }
                        break;
                    case "5":
                        Vista.mostrarMissatge("Introdueix un jugador per trasspasar a un altre equip: (Ex: LeBron James)");
                        jugadorNom = scan.nextLine();
                        if (!jugadorNom.matches("^[a-zA-Z]+$")) throw new Exception("Un nom només pot contenir lletres.");
                        Vista.mostrarMissatge("Introdueix un equip on trasspasar aquest jugador (Ex: Denver Nuggets)");
                        equipNom = scan.nextLine();
                        if (!equipNom.matches("^[a-zA-Z]+$")) throw new Exception("El nom d'un equip no pot contenir números.");
                        //Cridem l'excercici 5
                        Model.exercici5(jugadorNom,equipNom);
                        break;
                    case "6":
                        Vista.mostrarMissatge("Actualitzant dades...");
                        Model.exercici6();
                        break;
                    case "7":
                        Vista.mostrarMissatge("Introdueix un jugador per modificar les seves estadístiques: (Ex: LeBron James)");
                        jugadorNom = scan.nextLine();
                        if (!jugadorNom.matches("^[a-zA-Z]+$")) throw new Exception("Un nom només pot contenir lletres.");
                        Model.exercici7(jugadorNom);
                        break;
                    case "8":
                        Vista.mostrarMissatge("Introdueix un jugador per eliminar-lo: (Ex: LeBron James)");
                        jugadorNom = scan.nextLine();
                        if (!jugadorNom.matches("^[a-zA-Z]+$")) throw new Exception("Un nom només pot contenir lletres.");
                        //Cridem l'excercici 8
                        Model.exercici8(jugadorNom);
                        break;
                    case "9":
                        Vista.mostrarMissatge("Introdueix un equip per modificar la seva franquícia: (Ex: Denver Nuggets)");
                        equipNom = scan.nextLine();
                        //REGEX amb excepció per no permetre inserir números en la franquícia.
                        if (!equipNom.matches("^[a-zA-Z]+$")) throw new Exception("El nom d'un equip no pot contenir números.");
                        Vista.mostrarMissatge("Introdueix la nova franquícia:");
                        franquiciaNom = scan.nextLine();
                        if (!jugadorNom.matches("^[a-zA-Z]+$")) throw new Exception("Una franquícia no pot contenir números.");
                        //Cridem l'excercici 9
                        Model.exercici9(equipNom,franquiciaNom);
                        break;
                    case "0":
                        break;
                    default:
                        System.out.println("-------------------------------");
                        System.out.println("ATENCIÓ! Ha de ser entre 0 i 9");
                        System.out.println("-------------------------------");
                }
            } while (!(opcio.equals("0")));
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
