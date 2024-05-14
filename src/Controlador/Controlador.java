package Controlador;
import Vista.Vista;

import java.util.Scanner;

public class Controlador {
    static Scanner scan = new Scanner(System.in);
    //Opcio Menu
    public static String opcio = "";

    public static void consultas() {
        do {
            Vista.menuInicial();
            opcio = scan.nextLine();
            switch (opcio) {
                case "1":
                    System.out.println("De quin equip vols llistar els seus jugadors?");

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
    }
}
