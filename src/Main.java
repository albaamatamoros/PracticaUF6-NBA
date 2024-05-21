import Model.Equip.EquipDAO;
import java.util.List;
import java.util.Map;
import java.util.Set;
import Controlador.Controlador;

public class Main {
    public static void main(String[] args) throws Exception {

        Controlador.consultas();

        /*
        EquipDAO equipDAO = new EquipDAO();
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