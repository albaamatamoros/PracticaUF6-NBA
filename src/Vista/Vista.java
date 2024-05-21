package Vista;
import Model.Jugador.Jugador;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Vista {

    public static void menuInicial(){
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
    }

    //1.- Llistar tots els jugadors d'un equip
    public static void llistarJugadorsEquip(List<Jugador> jugadors){
            for (Jugador jugador : jugadors) {
                    System.out.println(jugador.getNom());
            }
    }

    //3.- Llistar tots els partits jugats per un equip amb el seu resultat.
    public static void llistarPartitsIResultats(List<Set<Map.Entry<String,Integer>>> llista){
        for (Set<Map.Entry<String,Integer>> set : llista) {
            for (Map.Entry<String,Integer> entry : set) {
                System.out.println(entry.getKey() + ": " + entry.getValue());
            }
            System.out.println();
        }
    }

    public static void mostrarMissatge(String missatge){
        System.out.println(missatge);
    }
}
