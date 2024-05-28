package Model.EstadisticsJugadorsHistorics;
import Model.Connexio;
import Model.DAO;
import Model.EstadisticaJugador.EstadisticaJugador;
import Vista.Vista;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class EstadisticsJugadorsHistoricsDAO implements DAO<EstadisticaJugadorHistoric> {

    //MÈTODES D'INTERFÍCIE DAO GENERALS
    @Override
    //Insertar un EstadisticaJugadorHistoric.
    public boolean insertar(EstadisticaJugadorHistoric estadisticaJugadorHistoric, Connection connexio) throws SQLException {
        PreparedStatement sentencia = connexio.prepareStatement("INSERT INTO estadistiques_jugadors_historics (jugador_id,nom,cognom,equip_id,partit_id,minuts_jugats,punts,tirs_anotats,tirs_tirats,tirs_triples_anotats,tirs_triples_tirats,tirs_lliures_anotats,tirs_lliures_tirats,rebots_ofensius,rebots_defensius,assistencies,robades,bloqueigs) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");

        sentencia.setInt(1, estadisticaJugadorHistoric.getJugadorId());
        sentencia.setString(2, estadisticaJugadorHistoric.getNom());
        sentencia.setString(3, estadisticaJugadorHistoric.getCognom());
        sentencia.setInt(4, estadisticaJugadorHistoric.getEquipId());
        sentencia.setInt(5, estadisticaJugadorHistoric.getPartitId());
        sentencia.setFloat(6, estadisticaJugadorHistoric.getMinutsJugats());
        sentencia.setInt(7, estadisticaJugadorHistoric.getPunts());
        sentencia.setInt(8, estadisticaJugadorHistoric.getTirsAnotats());
        sentencia.setInt(9, estadisticaJugadorHistoric.getTirsTirats());
        sentencia.setInt(10, estadisticaJugadorHistoric.getTirsTriplesAnotats());
        sentencia.setInt(11, estadisticaJugadorHistoric.getTirsTriplesTirats());
        sentencia.setInt(12, estadisticaJugadorHistoric.getTirsLliuresAnotats());
        sentencia.setInt(13, estadisticaJugadorHistoric.getTirsLliuresTirats());
        sentencia.setInt(14, estadisticaJugadorHistoric.getRebotsOfensius());
        sentencia.setInt(15, estadisticaJugadorHistoric.getRebotsDefensius());
        sentencia.setInt(16, estadisticaJugadorHistoric.getAssistencies());
        sentencia.setInt(17, estadisticaJugadorHistoric.getRobades());
        sentencia.setInt(18, estadisticaJugadorHistoric.getBloqueigs());

        return sentencia.executeUpdate() > 0;
    }

    @Override
    //Actualitzar un EstadisticaJugadorHistoric.
    public boolean actualitzar(EstadisticaJugadorHistoric estadisticaJugadorHistoric, Connection connexio) throws SQLException {
        PreparedStatement sentencia = connexio.prepareStatement(
                "UPDATE estadistiques_jugadors_historics SET nom=?,cognom=?,minuts_jugats=?,punts=?,tirs_anotats=?,tirs_tirats=?,tirs_triples_anotats=?,tirs_triples_tirats=?,tirs_lliures_anotats=?,tirs_lliures_tirats=?,rebots_ofensius=?,rebots_defensius=?,assistencies=?,robades=?,bloqueigs=? WHERE jugador_id=?,equip_id=?,partit_id=?"
        );

        sentencia.setFloat(1,estadisticaJugadorHistoric.getMinutsJugats());
        sentencia.setInt(2,estadisticaJugadorHistoric.getPunts());
        sentencia.setInt(3,estadisticaJugadorHistoric.getTirsAnotats());
        sentencia.setInt(4,estadisticaJugadorHistoric.getTirsTirats());
        sentencia.setInt(5,estadisticaJugadorHistoric.getTirsTriplesAnotats());
        sentencia.setInt(6,estadisticaJugadorHistoric.getTirsTriplesTirats());
        sentencia.setInt(7,estadisticaJugadorHistoric.getTirsLliuresAnotats());
        sentencia.setInt(8,estadisticaJugadorHistoric.getTirsLliuresTirats());
        sentencia.setInt(9,estadisticaJugadorHistoric.getRebotsOfensius());
        sentencia.setInt(10,estadisticaJugadorHistoric.getRebotsDefensius());
        sentencia.setInt(11,estadisticaJugadorHistoric.getAssistencies());
        sentencia.setInt(12,estadisticaJugadorHistoric.getRobades());
        sentencia.setInt(13,estadisticaJugadorHistoric.getBloqueigs());
        sentencia.setInt(14,estadisticaJugadorHistoric.getJugadorId());
        sentencia.setInt(15,estadisticaJugadorHistoric.getEquipId());
        sentencia.setInt(16,estadisticaJugadorHistoric.getPartitId());

        return sentencia.executeUpdate() > 0;
    }

    @Override
    //Esborrar un EstadisticaJugadorHistoric.
    public boolean esborrar(EstadisticaJugadorHistoric estadisticaJugadorHistoric, Connection connexio) throws SQLException {
        PreparedStatement sentencia = connexio.prepareStatement(
                "DELETE FROM estadistiques_jugadors_historics WHERE jugador_id = ? AND equip_id = ? AND partit_id = ?"
        );

        sentencia.setInt(1,estadisticaJugadorHistoric.getJugadorId());
        sentencia.setInt(2,estadisticaJugadorHistoric.getEquipId());
        sentencia.setInt(3,estadisticaJugadorHistoric.getPartitId());

        return sentencia.executeUpdate() > 0;
    }

    @Override
    //Cercar un EstadisticaJugadorHistoric.
    public EstadisticaJugadorHistoric cercar(int id, Connection connexio) throws SQLException {
        PreparedStatement sentencia;
        sentencia = connexio.prepareStatement(
                "SELECT * FROM estadistiques_jugadors_historics WHERE jugador_id = ? LIMIT 1"
        );

        sentencia.setInt(1, id);

        ResultSet rsEstadisticaJugador = sentencia.executeQuery();

        if (rsEstadisticaJugador.next()) {
            return new EstadisticaJugadorHistoric(
                    rsEstadisticaJugador.getInt("jugador_id"),
                    rsEstadisticaJugador.getString("nom"),
                    rsEstadisticaJugador.getString("cognom"),
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
    //Contar els EstadisticsJugadorHistoric.
    public int count(Connection connexio) throws SQLException {
        PreparedStatement sentencia = connexio.prepareStatement(
                "SELECT COUNT(*) FROM estadistiques_jugadors_historics"
        );

        ResultSet rsEstadisticaJugador = sentencia.executeQuery();
        rsEstadisticaJugador.next();

        return rsEstadisticaJugador.getInt(1);
    }

    //MÈTODES D'INTERFÍCIE ESPECÍFICS EXERCICIS

    //8 Inserir les dades obtingudes a la nova taula històrics
    public boolean traspassarEstadistiques(List<EstadisticaJugadorHistoric> estadistiquesHistoriques, Connection connexio) throws SQLException {
        Vista.mostrarMissatge("Retirant jugador...");
        PreparedStatement sentencia = connexio.prepareStatement(
                "INSERT INTO estadistiques_jugadors_historics (jugador_id,nom,cognom,equip_id,partit_id,minuts_jugats,punts,tirs_anotats,tirs_tirats,tirs_triples_anotats,tirs_triples_tirats,tirs_lliures_anotats,tirs_lliures_tirats,rebots_ofensius,rebots_defensius,assistencies,robades,bloqueigs) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)"
        );
        connexio.setAutoCommit(false);

        for (EstadisticaJugadorHistoric estadisticaHistorica : estadistiquesHistoriques) {
            sentencia.setInt(1,estadisticaHistorica.getJugadorId());
            sentencia.setString(2,estadisticaHistorica.getNom());
            sentencia.setString(3,estadisticaHistorica.getCognom());
            sentencia.setInt(4,estadisticaHistorica.getEquipId());
            sentencia.setInt(5,estadisticaHistorica.getPartitId());
            sentencia.setFloat(6,estadisticaHistorica.getMinutsJugats());
            sentencia.setInt(7,estadisticaHistorica.getPunts());
            sentencia.setInt(8,estadisticaHistorica.getTirsAnotats());
            sentencia.setInt(9,estadisticaHistorica.getTirsTirats());
            sentencia.setInt(10,estadisticaHistorica.getTirsTriplesAnotats());
            sentencia.setInt(11,estadisticaHistorica.getTirsTriplesTirats());
            sentencia.setInt(12,estadisticaHistorica.getTirsLliuresAnotats());
            sentencia.setInt(13,estadisticaHistorica.getTirsLliuresTirats());
            sentencia.setInt(14,estadisticaHistorica.getRebotsOfensius());
            sentencia.setInt(15,estadisticaHistorica.getRebotsDefensius());
            sentencia.setInt(16,estadisticaHistorica.getAssistencies());
            sentencia.setInt(17,estadisticaHistorica.getRobades());
            sentencia.setInt(18,estadisticaHistorica.getBloqueigs());

            boolean correcte = sentencia.executeUpdate() > 0;

            if (!correcte) {
                connexio.rollback();
                return false;
            }
        }
        connexio.commit();
        return true;
    }
}
