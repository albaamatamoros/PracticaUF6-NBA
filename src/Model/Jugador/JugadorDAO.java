package Model.Jugador;

import Model.DAO;
import Model.Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class JugadorDAO implements DAO<Jugador> {

    @Override
    public boolean insertar(Jugador jugador) {
        Connection connexio = null;
        PreparedStatement sentencia = null;

        try {
            connexio = Model.getConnection();

            String sql = "INSERT INTO jugadors (nom,cognom,data_naixement,alcada,pes,dorsal,posicio,equip_id) VALUES (?,?,?,?,?,?,?,?)";
            sentencia = connexio.prepareStatement(sql);

            sentencia.setString(1,jugador.getNom());
            sentencia.setString(2,jugador.getCognom());
            sentencia.setDate(3,jugador.getDataNaixement());
            sentencia.setFloat(4,jugador.getAlcada());
            sentencia.setFloat(5,jugador.getPes());
            sentencia.setString(6,jugador.getDorsal());
            sentencia.setString(7,jugador.getPosicio());
            sentencia.setInt(8,jugador.getEquipId());

            return sentencia.executeUpdate() > 0;
        } catch (SQLException e) {
            return false;
        } finally {
            try {
                if (sentencia != null) {
                    sentencia.close();
                }
                if (connexio != null) {
                    connexio.close();
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    @Override
    public boolean actualitzar(Jugador obj) {
        return false;
    }

    @Override
    public boolean esborrar(Jugador obj) {
        return false;
    }

    @Override
    public Jugador cercar(int id) {
        return null;
    }

}
