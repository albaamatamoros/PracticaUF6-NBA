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
            sentencia = connexio.prepareStatement(
                    "INSERT INTO jugadors (nom,cognom,data_naixement,alcada,pes,dorsal,posicio,equip_id) VALUES (?,?,?,?,?,?,?,?)"
            );

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
        Connection connexio = null;
        PreparedStatement sentencia = null;

        try {
            connexio = Model.getConnection();
            sentencia = connexio.prepareStatement(
                    "UPDATE jugadors SET nom=?,cognom=?,data_naixement=?,alcada=?,pes=?,dorsal=?,posicio=?,equip_id=? WHERE id=?"
            );

            sentencia.setString(1,obj.getNom());
            sentencia.setString(2,obj.getCognom());
            sentencia.setDate(3,obj.getDataNaixement());
            sentencia.setFloat(4,obj.getAlcada());
            sentencia.setFloat(5,obj.getPes());
            sentencia.setString(6,obj.getDorsal());
            sentencia.setString(7,obj.getPosicio());
            sentencia.setInt(8,obj.getEquipId());
            sentencia.setInt(9,obj.getId());
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
    public boolean esborrar(Jugador obj) {
        return false;
    }

    @Override
    public Jugador cercar(int id) {
        return null;
    }

}
