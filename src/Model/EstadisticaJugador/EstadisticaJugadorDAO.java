package Model.EstadisticaJugador;
import Model.DAO;
import Model.Connexio;
import Vista.Vista;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
//asa

public class EstadisticaJugadorDAO implements DAO<EstadisticaJugador>{

    //MÈTODES D'INTERFÍCIE DAO GENERALS
    @Override
    //Insertar un EstadisticaJugador.
    public boolean insertar(EstadisticaJugador estadisticaJugador, Connection connexio) throws SQLException {
        PreparedStatement sentencia = connexio.prepareStatement(
                "INSERT INTO estadistiques_jugadors (jugador_id,equip_id,partit_id,minuts_jugats,punts,tirs_anotats,tirs_tirats,tirs_triples_anotats,tirs_triples_tirats,tirs_lliures_anotats,tirs_lliures_tirats,rebots_ofensius,rebots_defensius,assistencies,robades,bloqueigs) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)"
        );


        sentencia.setInt(1,estadisticaJugador.getJugadorId());
        sentencia.setInt(2,estadisticaJugador.getEquipId());
        sentencia.setInt(3,estadisticaJugador.getPartitId());
        sentencia.setFloat(4,estadisticaJugador.getMinutsJugats());
        sentencia.setInt(5,estadisticaJugador.getPunts());
        sentencia.setInt(6,estadisticaJugador.getTirsAnotats());
        sentencia.setInt(7,estadisticaJugador.getTirsTirats());
        sentencia.setInt(8,estadisticaJugador.getTirsTriplesAnotats());
        sentencia.setInt(9,estadisticaJugador.getTirsTriplesTirats());
        sentencia.setInt(10,estadisticaJugador.getTirsLliuresAnotats());
        sentencia.setInt(11,estadisticaJugador.getTirsLliuresTirats());
        sentencia.setInt(12,estadisticaJugador.getRebotsOfensius());
        sentencia.setInt(13,estadisticaJugador.getRebotsDefensius());
        sentencia.setInt(14,estadisticaJugador.getAssistencies());
        sentencia.setInt(15,estadisticaJugador.getRobades());
        sentencia.setInt(16,estadisticaJugador.getBloqueigs());

        return sentencia.executeUpdate() > 0;
    }

    @Override
    //Actualitzar un EstadisticaJugador.
    public boolean actualitzar(EstadisticaJugador estadisticaJugador, Connection connexio) throws SQLException {
        PreparedStatement sentencia = connexio.prepareStatement(
                "UPDATE estadistiques_jugadors SET minuts_jugats=?,punts=?,tirs_anotats=?,tirs_tirats=?,tirs_triples_anotats=?,tirs_triples_tirats=?,tirs_lliures_anotats=?,tirs_lliures_tirats=?,rebots_ofensius=?,rebots_defensius=?,assistencies=?,robades=?,bloqueigs=? WHERE jugador_id=?,equip_id=?,partit_id=?"
        );

        sentencia.setFloat(1,estadisticaJugador.getMinutsJugats());
        sentencia.setInt(2,estadisticaJugador.getPunts());
        sentencia.setInt(3,estadisticaJugador.getTirsAnotats());
        sentencia.setInt(4,estadisticaJugador.getTirsTirats());
        sentencia.setInt(5,estadisticaJugador.getTirsTriplesAnotats());
        sentencia.setInt(6,estadisticaJugador.getTirsTriplesTirats());
        sentencia.setInt(7,estadisticaJugador.getTirsLliuresAnotats());
        sentencia.setInt(8,estadisticaJugador.getTirsLliuresTirats());
        sentencia.setInt(9,estadisticaJugador.getRebotsOfensius());
        sentencia.setInt(10,estadisticaJugador.getRebotsDefensius());
        sentencia.setInt(11,estadisticaJugador.getAssistencies());
        sentencia.setInt(12,estadisticaJugador.getRobades());
        sentencia.setInt(13,estadisticaJugador.getBloqueigs());
        sentencia.setInt(14,estadisticaJugador.getJugadorId());
        sentencia.setInt(15,estadisticaJugador.getEquipId());
        sentencia.setInt(16,estadisticaJugador.getPartitId());

        return sentencia.executeUpdate() > 0;
    }

    @Override
    //Esborrar EstadisticaJugador
    public boolean esborrar(EstadisticaJugador estadisticaJugador, Connection connexio) throws SQLException {
        PreparedStatement sentencia = connexio.prepareStatement(
                "DELETE FROM estadistiques_jugadors WHERE jugador_id = ? AND equip_id = ? AND partit_id = ?"
        );

        sentencia.setInt(1,estadisticaJugador.getJugadorId());
        sentencia.setInt(2,estadisticaJugador.getEquipId());
        sentencia.setInt(3,estadisticaJugador.getPartitId());

        return sentencia.executeUpdate() > 0;
    }

    @Override
    //Cercar un EstadisticaJugador
    public EstadisticaJugador cercar(int id, Connection connexio) throws SQLException {
        PreparedStatement sentencia;
        sentencia = connexio.prepareStatement(
                "SELECT * FROM estadistiques_jugadors WHERE jugador_id = ? LIMIT 1"
        );

        sentencia.setInt(1, id);

        ResultSet rsEstadisticaJugador = sentencia.executeQuery();

        if (rsEstadisticaJugador.next()) {
            return new EstadisticaJugador(
                    rsEstadisticaJugador.getInt("jugador_id"),
                    rsEstadisticaJugador.getInt("equip_id"),
                    rsEstadisticaJugador.getInt("partit_id"),
                    rsEstadisticaJugador.getFloat("minuts_jugats"),
                    rsEstadisticaJugador.getInt("punts"),
                    rsEstadisticaJugador.getInt("tirs_anotats"),
                    rsEstadisticaJugador.getInt("tirs_tirats"),
                    rsEstadisticaJugador.getInt("tirs_triples_anotats"),
                    rsEstadisticaJugador.getInt("tirs_triples_tirats"),
                    rsEstadisticaJugador.getInt("tirs_lliures_anotats"),
                    rsEstadisticaJugador.getInt("tirs_lliures_tirats"),
                    rsEstadisticaJugador.getInt("rebots_ofensius"),
                    rsEstadisticaJugador.getInt("rebots_defensius"),
                    rsEstadisticaJugador.getInt("assistencies"),
                    rsEstadisticaJugador.getInt("robades"),
                    rsEstadisticaJugador.getInt("bloqueigs"));
        } else {
            return null;
        }
    }

    @Override
    //Contar quantes estadistiques tenim.
    public int count(Connection connexio) throws SQLException {
        PreparedStatement sentencia = connexio.prepareStatement(
                "SELECT COUNT(*) FROM estadistiques_jugadors"
        );

        ResultSet rsEstadisticaJugador = sentencia.executeQuery();
        rsEstadisticaJugador.next();

        return rsEstadisticaJugador.getInt(1);
    }

    //MÈTODES D'INTERFÍCIE ESPECÍFICS EXERCICIS

    //6 Actualitzar les dades de jugadors o equips després d'un partit
    public boolean actualitzarEnMassa(List<EstadisticaJugador> estadistiquesJugador, Connection connexio) throws SQLException {
        Vista.mostrarMissatge("Actualitzant dades...");
        PreparedStatement sentencia = connexio.prepareStatement(
                "UPDATE estadistiques_jugadors SET minuts_jugats=?,punts=?,tirs_anotats=?,tirs_tirats=?,tirs_triples_anotats=?,tirs_triples_tirats=?,tirs_lliures_anotats=?,tirs_lliures_tirats=?,rebots_ofensius=?,rebots_defensius=?,assistencies=?,robades=?,bloqueigs=? WHERE jugador_id=? AND equip_id=? AND partit_id=?"
        );

        for (EstadisticaJugador estadisticaJugador : estadistiquesJugador) {
            sentencia.setFloat(1, estadisticaJugador.getMinutsJugats());
            sentencia.setInt(2, estadisticaJugador.getPunts());
            sentencia.setInt(3, estadisticaJugador.getTirsAnotats());
            sentencia.setInt(4, estadisticaJugador.getTirsTirats());
            sentencia.setInt(5, estadisticaJugador.getTirsTriplesAnotats());
            sentencia.setInt(6, estadisticaJugador.getTirsTriplesTirats());
            sentencia.setInt(7, estadisticaJugador.getTirsLliuresAnotats());
            sentencia.setInt(8, estadisticaJugador.getTirsLliuresTirats());
            sentencia.setInt(9, estadisticaJugador.getRebotsOfensius());
            sentencia.setInt(10, estadisticaJugador.getRebotsDefensius());
            sentencia.setInt(11, estadisticaJugador.getAssistencies());
            sentencia.setInt(12, estadisticaJugador.getRobades());
            sentencia.setInt(13, estadisticaJugador.getBloqueigs());
            sentencia.setInt(14, estadisticaJugador.getJugadorId());
            sentencia.setInt(15, estadisticaJugador.getEquipId());
            sentencia.setInt(16, estadisticaJugador.getPartitId());

            boolean correcte = sentencia.executeUpdate() > 0;

            if (!correcte) {
                return false;
            }
        }
        return true;
    }

    //8 Obtenir les estadístiques d'un jugador concret
    public List<EstadisticaJugador> obtenirEstadistiques(int jugadorId, Connection connexio) throws SQLException {
        PreparedStatement sentencia = connexio.prepareStatement(
                "SELECT * FROM estadistiques_jugadors WHERE jugador_id = ?"
        );

        sentencia.setInt(1,jugadorId);
        ResultSet rsEstadisticaJugador = sentencia.executeQuery();

        List<EstadisticaJugador> llistaEstadistiques = new ArrayList<>();

        while (rsEstadisticaJugador.next()) {
            EstadisticaJugador e = new EstadisticaJugador(
                    rsEstadisticaJugador.getInt("jugador_id"),
                    rsEstadisticaJugador.getInt("equip_id"),
                    rsEstadisticaJugador.getInt("partit_id"),
                    rsEstadisticaJugador.getFloat("minuts_jugats"),
                    rsEstadisticaJugador.getInt("punts"),
                    rsEstadisticaJugador.getInt("tirs_anotats"),
                    rsEstadisticaJugador.getInt("tirs_tirats"),
                    rsEstadisticaJugador.getInt("tirs_triples_anotats"),
                    rsEstadisticaJugador.getInt("tirs_triples_tirats"),
                    rsEstadisticaJugador.getInt("tirs_lliures_anotats"),
                    rsEstadisticaJugador.getInt("tirs_lliures_tirats"),
                    rsEstadisticaJugador.getInt("rebots_ofensius"),
                    rsEstadisticaJugador.getInt("rebots_defensius"),
                    rsEstadisticaJugador.getInt("assistencies"),
                    rsEstadisticaJugador.getInt("robades"),
                    rsEstadisticaJugador.getInt("bloqueigs"));
            e.setJugadorId(rsEstadisticaJugador.getInt("jugador_id"));

            llistaEstadistiques.add(e);
        }

        return llistaEstadistiques;
    }

    //7 Buscar el partit inserit amb el jugador per mirar si el jugador a jugat aquest partit
    public int cercarPartitJugat(int partitID, int jugadorId, Connection connexio) throws SQLException {
        PreparedStatement sentencia = connexio.prepareStatement(
                "SELECT * FROM estadistiques_jugadors WHERE jugador_id=? AND partit_id=?"
        );

        sentencia.setInt(1,jugadorId);
        sentencia.setInt(2,partitID);
        ResultSet rsResultat = sentencia.executeQuery();

        if (rsResultat.next()) {
            return rsResultat.getInt("partit_id");
        }
        return 0;
    }

    //7 Obtenir les estadístiques d'un jugador concret i un partit concret per poder modificar-les
    public EstadisticaJugador obtenirEstadistiquesModificables (int jugadorId, int partitID, Connection connexio) throws SQLException {
        PreparedStatement sentencia = connexio.prepareStatement(
                "SELECT * FROM estadistiques_jugadors WHERE jugador_id = ? AND partit_id = ?"
        );

        sentencia.setInt(1, jugadorId);
        sentencia.setInt(2, partitID);
        ResultSet rsEstadisticaJugador = sentencia.executeQuery();

        EstadisticaJugador eJugador = null;

        if (rsEstadisticaJugador.next()) {
            eJugador = new EstadisticaJugador(
                    rsEstadisticaJugador.getInt("jugador_id"),
                    rsEstadisticaJugador.getInt("equip_id"),
                    rsEstadisticaJugador.getInt("partit_id"),
                    rsEstadisticaJugador.getFloat("minuts_jugats"),
                    rsEstadisticaJugador.getInt("punts"),
                    rsEstadisticaJugador.getInt("tirs_anotats"),
                    rsEstadisticaJugador.getInt("tirs_tirats"),
                    rsEstadisticaJugador.getInt("tirs_triples_anotats"),
                    rsEstadisticaJugador.getInt("tirs_triples_tirats"),
                    rsEstadisticaJugador.getInt("tirs_lliures_anotats"),
                    rsEstadisticaJugador.getInt("tirs_lliures_tirats"),
                    rsEstadisticaJugador.getInt("rebots_ofensius"),
                    rsEstadisticaJugador.getInt("rebots_defensius"),
                    rsEstadisticaJugador.getInt("assistencies"),
                    rsEstadisticaJugador.getInt("robades"),
                    rsEstadisticaJugador.getInt("bloqueigs")
            );
        }
        return eJugador;
    }

    //7 Actualitzar les dades modificades d'un jugador i partit concret.
    public boolean actualitzarModificacions(EstadisticaJugador estadisticaJugador, Connection connexio) throws SQLException {
        PreparedStatement sentencia = connexio.prepareStatement(
                "UPDATE estadistiques_jugadors SET minuts_jugats=?,punts=?,tirs_anotats=?,tirs_tirats=?,tirs_triples_anotats=?,tirs_triples_tirats=?,tirs_lliures_anotats=?,tirs_lliures_tirats=?,rebots_ofensius=?,rebots_defensius=?,assistencies=?,robades=?,bloqueigs=? WHERE jugador_id=? AND partit_id=?"
        );

        sentencia.setFloat(1,estadisticaJugador.getMinutsJugats());
        sentencia.setInt(2,estadisticaJugador.getPunts());
        sentencia.setInt(3,estadisticaJugador.getTirsAnotats());
        sentencia.setInt(4,estadisticaJugador.getTirsTirats());
        sentencia.setInt(5,estadisticaJugador.getTirsTriplesAnotats());
        sentencia.setInt(6,estadisticaJugador.getTirsTriplesTirats());
        sentencia.setInt(7,estadisticaJugador.getTirsLliuresAnotats());
        sentencia.setInt(8,estadisticaJugador.getTirsLliuresTirats());
        sentencia.setInt(9,estadisticaJugador.getRebotsOfensius());
        sentencia.setInt(10,estadisticaJugador.getRebotsDefensius());
        sentencia.setInt(11,estadisticaJugador.getAssistencies());
        sentencia.setInt(12,estadisticaJugador.getRobades());
        sentencia.setInt(13,estadisticaJugador.getBloqueigs());
        sentencia.setInt(14,estadisticaJugador.getJugadorId());
        sentencia.setInt(15,estadisticaJugador.getPartitId());

        return sentencia.executeUpdate() > 0;
    }
}
