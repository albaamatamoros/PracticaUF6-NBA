package Model.Jugador;
import Model.DAO;
import Model.Connexio;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JugadorDAO implements DAO<Jugador> {

    @Override
    public boolean insertar(Jugador jugador) throws SQLException {
        Connection connexio = Connexio.getConnection();
        PreparedStatement sentencia = connexio.prepareStatement(
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
    }

    @Override
    public boolean actualitzar(Jugador jugador) throws SQLException {
        Connection connexio = Connexio.getConnection();
        PreparedStatement sentencia = connexio.prepareStatement(
                "UPDATE jugadors SET nom=?,cognom=?,data_naixement=?,alcada=?,pes=?,dorsal=?,posicio=?,equip_id=? WHERE id=?"
        );

        sentencia.setString(1,jugador.getNom());
        sentencia.setString(2,jugador.getCognom());
        sentencia.setDate(3,jugador.getDataNaixement());
        sentencia.setFloat(4,jugador.getAlcada());
        sentencia.setFloat(5,jugador.getPes());
        sentencia.setString(6,jugador.getDorsal());
        sentencia.setString(7,jugador.getPosicio());
        sentencia.setInt(8,jugador.getEquipId());
        sentencia.setInt(9,jugador.getId());

        return sentencia.executeUpdate() > 0;
    }

    @Override
    public boolean esborrar(Jugador jugador) throws SQLException {
        Connection connexio = Connexio.getConnection();
        PreparedStatement sentencia = connexio.prepareStatement(
                "DELETE FROM jugadors WHERE jugador_id = ?"
        );

        sentencia.setInt(1,jugador.getId());

        return sentencia.executeUpdate() > 0;
    }

    @Override
    public Jugador cercar(int id) throws SQLException {
        Connection connexio = Connexio.getConnection();
        PreparedStatement sentencia = connexio.prepareStatement(
                "SELECT * FROM jugadors WHERE jugador_id = ?"
        );

        sentencia.setInt(1,id);
        ResultSet rsJugador = sentencia.executeQuery();

        if (rsJugador.next()) {
            Jugador jugador = new Jugador(
                    rsJugador.getString("nom"),
                    rsJugador.getString("cognom"),
                    rsJugador.getDate("data_naixement"),
                    rsJugador.getFloat("alcada"),
                    rsJugador.getFloat("pes"),
                    rsJugador.getString("dorsal"),
                    rsJugador.getString("posicio"),
                    rsJugador.getInt("equip_id"));

            jugador.setId(rsJugador.getInt("id"));
            return jugador;
        } else {
            return null;
        }
    }

    @Override
    public int count() throws SQLException {
        Connection connexio = Connexio.getConnection();
        PreparedStatement sentencia = connexio.prepareStatement(
                "SELECT COUNT(*) FROM jugadors"
        );

        ResultSet rsNumJugadors = sentencia.executeQuery();
        rsNumJugadors.next();

        return rsNumJugadors.getInt(1);
    }

}
