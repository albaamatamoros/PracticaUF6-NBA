package Controlador;
import Model.Equip.Equip;
import Model.Jugador.Jugador;
import Model.Jugador.JugadorDAO;
import Vista.Vista;
import Model.Equip.EquipDAO;

import java.security.PublicKey;
import java.sql.Date;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Controlador {
    static Scanner scan = new Scanner(System.in);
    //DAO
    static EquipDAO equipDAO = new EquipDAO();

    //VARIABLES:
    //Opcio Menu
    public static String opcio = "";

    //Variable per rebre el nom de l'equip.
    static String equipNom;

    //Variable per rebre el nom del jugador.
    public static String jugadorNom;

    public static void consultas() throws Exception {
        try {
            do {
                //Canviem el limitador de scan perquè agafi espais. (Per evitar problemes amb els espais dels noms)
                scan.useDelimiter("\n");
                //Cridem al menú
                Vista.menuInicial();
                opcio = scan.nextLine();
                switch (opcio) {
                    case "1":
                        Vista.mostrarMissatge("Introdueix el nom d'un equip per llistar els seus jugadors: (Ex: Denver Nuggets)");
                        equipNom = scan.nextLine();
                        List<Jugador> jugadors = equipDAO.obtenirJugadors(equipNom);
                        Vista.llistarJugadorsEquip(jugadors);
                        break;
                    case "2":
                        Vista.mostrarMissatge("Introdueix un jugador per calcular la seva mitjana de punts: (Ex: LeBron James)");
                        break;
                    case "3":
                        Vista.mostrarMissatge("Introdueix el nom d'un equip per llistar tots els partits i els seus resultats: (Ex: Denver Nuggets)");
                        equipNom = scan.nextLine();
                        //
                        //EquipDAO equipDAO = new EquipDAO();
                        List<Set<Map.Entry<String,Integer>>> llista = equipDAO.obtenirResultatPartits(equipNom);
                        Vista.llistarPartitsIResultats(llista);
                        break;
                    case "4":
                        //Insertar dades.
                        Vista.mostrarMissatge("Introdueix un jugador per inserir a la taula: (Ex: LeBron James)");
                        jugadorNom = scan.nextLine();
                        String[] nomCognom = jugadorNom.split(" ");
                        Vista.mostrarMissatge("Introdueix un equip on unir a aquest jugador: (Ex: Denver Nuggets)");
                        equipNom = scan.nextLine();

                        //Crear el jugador i insertar-lo
                        Jugador jugador = new Jugador(nomCognom[0],nomCognom[1],Date.valueOf("2003-12-03"),190.56f,110.25f, "05","Forward" , equipDAO.cercarIdPerNom(equipNom));
                        JugadorDAO dao = new JugadorDAO();
                        boolean correcte = dao.insertar(jugador);

                        //Comprovar si s'ha creat correctament.
                        if (correcte){
                            Vista.mostrarMissatge("El jugador s'ha registrat correctament");
                        } else {
                            Vista.mostrarMissatge("El jugador no s'ha registrat correctament");
                        }
                        break;
                    case "5":
                        break;
                    case "6":
                        break;
                    case "7":
                        break;
                    case "8":
                        break;
                    case "9":
                        break;
                    case "0":
                        break;
                    default:
                        System.out.println("-------------------------------");
                        System.out.println("ATENCIÓ! Ha de ser entre 0 i 3");
                        System.out.println("-------------------------------");
                }
            } while (!(opcio.equals("0")));
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
