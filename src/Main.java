import Model.Equip.EquipDAO;
import java.util.List;
import java.util.Map;
import java.util.Set;
import Controlador.Controlador;

public class Main {
    public static void main(String[] args) throws Exception {

        Controlador.consultas();

        /*
        Jugador jugador = new Jugador("Santi","Onieva", Date.valueOf("2003-12-03"),190.56f,110.25f, "05","Forward" , 1610612737);

        JugadorDAO dao = new JugadorDAO();
        boolean correcte = dao.insertar(jugador);

        if (correcte){
            System.out.println("El jugador se ha registrado correctamente");
        } else {
            System.out.println("El jugador no se ha registrado correctamente");
        } */


        /*
        List<Set<Map.Entry<String,Integer>>> llista = equipDAO.obtenirResultatPartits("Atlanta Hawks");
        for (Set<Map.Entry<String,Integer>> set : llista) {
            for (Map.Entry<String,Integer> entry : set) {
                System.out.println(entry.getKey() + ": " + entry.getValue());
            }
            System.out.println();
        }

         */
    }
}