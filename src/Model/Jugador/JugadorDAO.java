package Model.Jugador;
import Model.DAO;
import Model.Connexio;
import Vista.Vista;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class JugadorDAO implements DAO<Jugador> {

    //MÈTODES D'INTERFÍCIE DAO GENERALS
    @Override
    public boolean insertar(Jugador jugador, Connection connexio) throws SQLException {
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
    public boolean actualitzar(Jugador jugador, Connection connexio) throws SQLException {
        PreparedStatement sentencia = connexio.prepareStatement(
                "UPDATE jugadors SET nom=?,cognom=?,data_naixement=?,alcada=?,pes=?,dorsal=?,posicio=?,equip_id=? WHERE jugador_id=?"
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
    public boolean esborrar(Jugador jugador, Connection connexio) throws SQLException {
        PreparedStatement sentencia = connexio.prepareStatement(
                "DELETE FROM jugadors WHERE jugador_id = ?"
        );

        sentencia.setInt(1,jugador.getId());

        return sentencia.executeUpdate() > 0;
    }

    @Override
    public Jugador cercar(int id, Connection connexio) throws SQLException {
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

            jugador.setId(rsJugador.getInt("jugador_id"));
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

    //MÈTODES D'INTERFÍCIE ESPECÍFICS EXERCICIS

    //2 Calcular la mitjana de punts, rebots, assistències, ... d'un jugador
    public LinkedHashMap<String,Float> calcularMitjana(String nomComplet) throws Exception {
        Connection connexio = Connexio.getConnection();
        Vista.mostrarMissatge("Cercant mitjana...");
        PreparedStatement sentencia = connexio.prepareStatement(
                "SELECT ROUND(AVG(punts),2) AS mitjana_punts, " +
                        "ROUND(AVG(rebots_defensius) + AVG(rebots_ofensius),2) AS mitjana_rebots, " +
                        "ROUND(AVG(assistencies),2) AS mitjana_assistencies " +
                        "FROM estadistiques_jugadors " +
                        "WHERE jugador_id = ?"
        );

        int jugadorId = cercarIdPerNom(nomComplet);

        if (jugadorId == 0) {
            throw new Exception("Jugador no trobat");
        }

        sentencia.setInt(1,jugadorId);
        ResultSet rsJugador = sentencia.executeQuery();
        rsJugador.next();

        LinkedHashMap<String, Float> mitjanes = new LinkedHashMap<>(3);

        mitjanes.put("Mitjana Punts", rsJugador.getFloat("mitjana_punts"));
        mitjanes.put("Mitjana Rebots", rsJugador.getFloat("mitjana_rebots"));
        mitjanes.put("Mitjana Assistencies", rsJugador.getFloat("mitjana_assistencies"));

        return mitjanes;
    }

    //4 Inserir un nou jugador a un equip.
    public int cercarIdPerNom(String nomComplet) throws SQLException {
        Connection connexio = Connexio.getConnection();
        PreparedStatement sentencia = connexio.prepareStatement(
                "SELECT jugador_id,CONCAT(nom,' ',cognom) AS nom_complet FROM jugadors HAVING nom_complet = ?"
        );

        sentencia.setString(1,nomComplet);
        ResultSet rsJugador = sentencia.executeQuery();

        if (rsJugador.next()) {
            return rsJugador.getInt("jugador_id");
        } else {
            return 0;
        }
    }
}
