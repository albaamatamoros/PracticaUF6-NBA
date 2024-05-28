package Model.EstadisticsJugadorsHistorics;
import Model.Connexio;
import Model.DAO;
import Vista.Vista;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class EstadisticsJugadorsHistoricsDAO implements DAO<EstadisticaJugadorHistoric> {

    @Override
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
    public boolean actualitzar(EstadisticaJugadorHistoric obj, Connection connexio) throws SQLException {
        return false;
    }

    @Override
    public boolean esborrar(EstadisticaJugadorHistoric obj, Connection connexio) throws SQLException {
        return false;
    }

    @Override
    public EstadisticaJugadorHistoric cercar(int id, Connection connexio) throws SQLException {
        return null;
    }

    @Override
    public int count(Connection connexio) throws SQLException {
        return 0;
    }

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
