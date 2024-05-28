package Vista;
import Model.Jugador.Jugador;

import java.util.*;

public class Vista {

    public static void menuInicial(){
        System.out.println("\n------ MENÚ ------");
        System.out.println("1. Llistar tots els jugadors d'un equip");
        System.out.println("2. Calcular la mitjana de punts, rebots, assistències, ...d'un jugador");
        System.out.println("3. Llistar tots els partits jugats per un equip amb el seu resultat");
        System.out.println("4. Insert un nou jugador a un equip");
        System.out.println("5. Traspassar un jugador a un altra equip");
        System.out.println("6. Actualitzar les dades de jugadors o equips després d'un partit");
        System.out.println("7. Modificar les estadístiques d'un jugador");
        System.out.println("8. Retirar (Eliminar) un jugador");
        System.out.println("9. Canviar nom franquícia d'un equip");
        System.out.println("0. Sortir");
    }

    //1 Llistar tots els jugadors d'un equip
    public static void llistarJugadorsEquip(List<Jugador> jugadors){

            for (Jugador jugador : jugadors) {
                    System.out.println(jugador.getNom() + " " + jugador.getCognom());
            }
    }

    //2 Calcular la mitjana de punts, rebots, assistències, ... d'un jugador
    public static void llistarMitjanes(LinkedHashMap<String, Float> mitjanes){
        for (Map.Entry<String, Float> entry : mitjanes.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }

    //3 Llistar tots els partits jugats per un equip amb el seu resultat.
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

    public static void menúModificarEstadistiques(){
        System.out.println("\n------ MODIFICAR ESTADISTIQUES ------");
        System.out.println("1. Minuts jugats");
        System.out.println("2. Punts");
        System.out.println("3. Tirs anotats");
        System.out.println("4. Tirs tirats");
        System.out.println("5. Tirs triples anotats");
        System.out.println("6. Tirs triples tirats");
        System.out.println("7. Tirs lliures anotats");
        System.out.println("8. Tirs lliures tirats");
        System.out.println("9. Rebots ofensius");
        System.out.println("10. Rebots defensius");
        System.out.println("11. Assistencies");
        System.out.println("12. Robades");
        System.out.println("13. Bloqueig");
        System.out.println("0. sortir");
    }
}
