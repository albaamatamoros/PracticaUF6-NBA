import Model.Jugador.Jugador;
import Model.Jugador.JugadorDAO;
import Controlador.Controlador;

import java.sql.Date;

public class Main {
    public static void main(String[] args) {

        Controlador.consultas();

        Jugador jugador = new Jugador("Santi","Onieva", Date.valueOf("2003-12-03"),190.56f,110.25f, "05","Forward" , 1610612737);

        JugadorDAO dao = new JugadorDAO();
        boolean correcte = dao.insertar(jugador);

        if (correcte){
            System.out.println("El jugador se ha registrado correctamente");
        } else {
            System.out.println("El jugador no se ha registrado correctamente");
        }
    }
}