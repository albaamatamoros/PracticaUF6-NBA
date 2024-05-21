package Controlador;
import Model.Jugador.Jugador;
import Vista.Vista;
import Model.Equip.EquipDAO;
import java.util.List;
import java.util.Scanner;

public class Controlador {
    static Scanner scan = new Scanner(System.in);
    //Opcio Menu
    public static String opcio = "";

    //DAO
    static EquipDAO equipDAO = new EquipDAO();

    //Variable per rebre el nom de l'equip.
    static String equipNom;

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
                        System.out.println("De quin equip vols llistar els seus jugadors?");
                        equipNom = scan.nextLine();
                        List<Jugador> jugadors = equipDAO.obtenirJugadors(equipNom);
                        Vista.llistarJugadorsEquip(jugadors);
                        break;
                    case "2":
                        break;
                    case "3":
                        break;
                    case "4":
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
