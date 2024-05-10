package Vista;
import java.util.Scanner;

public class Vista {
    public static String opcio = "";
    static Scanner scan = new Scanner(System.in);
    public static void  menuInicial(){
        do {
            System.out.println("------ MENÚ ------");
            System.out.println("1. Llistar tots els jugadors d'un equip");
            System.out.println("2. Calcular la mitjana de punts, rebots, assistències, ... d'un jugador");
            System.out.println("3. Llistar tots els partits jugats per un equip amb el seu resultat");
            System.out.println("4. Insert un nou jugador a un altra equip");
            System.out.println("5. Traspassar un judado a un altra equip");
            System.out.println("6. Actualitzar les dades de jugadors o equips després d'un partit");
            System.out.println("7. Modificar les estadístiques d'un jugado");
            System.out.println("8. Retirar (Eliminar) un jugador");
            System.out.println("9. Canviar nom franquícia d'un equip");
            System.out.println("0. Sortir");
            opcio = scan.nextLine();
            switch (opcio) {
                case "1":
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
