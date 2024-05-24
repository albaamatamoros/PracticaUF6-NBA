package Model.EstadisticsJugadorsHistorics;
import Model.Connexio;
import Model.DAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class EstadisticsJugadorsHistoricsDAO implements DAO<EstadisticaJugadorHistoric> {

    @Override
    public boolean insertar(EstadisticaJugadorHistoric estadisticaJugadorHistoric) throws SQLException {
        Connection connexio = null;
        PreparedStatement sentencia = null;

        try {
            connexio = Connexio.getConnection();
            sentencia = connexio.prepareStatement("INSERT INTO estadistics_jugadors_historics (jugador_id,equip_id,partit_id,minuts_jugats,punts,tirs_anotats,tirs_tirats,tirs_triples_antotats,tirs_triples_tirats,tirs_lliures_anotats,tirs_lliures_tirats,rebots_ofensius,rebots_defensius,assistencies,robades,bloqueigs) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");

            sentencia.setInt(1, estadisticaJugadorHistoric.getJugadorId());
            sentencia.setInt(2, estadisticaJugadorHistoric.getEquipId());
            sentencia.setInt(3, estadisticaJugadorHistoric.getPartitId());
            sentencia.setFloat(4, estadisticaJugadorHistoric.getMinutsJugats());
            sentencia.setInt(5, estadisticaJugadorHistoric.getPunts());
            sentencia.setInt(6, estadisticaJugadorHistoric.getTirsAnotats());
            sentencia.setInt(7, estadisticaJugadorHistoric.getTirsTirats());
            sentencia.setInt(8, estadisticaJugadorHistoric.getTirsTriplesAnotats());
            sentencia.setInt(9, estadisticaJugadorHistoric.getTirsTriplesTirats());
            sentencia.setInt(10, estadisticaJugadorHistoric.getTirsLliuresAnotats());
            sentencia.setInt(11, estadisticaJugadorHistoric.getTirsLliuresTirats());
            sentencia.setInt(12, estadisticaJugadorHistoric.getRebotsOfensius());
            sentencia.setInt(13, estadisticaJugadorHistoric.getRebotsDefensius());
            sentencia.setInt(14, estadisticaJugadorHistoric.getAssistencies());
            sentencia.setInt(15, estadisticaJugadorHistoric.getRobades());
            sentencia.setInt(16, estadisticaJugadorHistoric.getBloqueigs());
            return sentencia.executeUpdate() > 0;

        }catch (SQLException e) {
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
    public boolean actualitzar(EstadisticaJugadorHistoric obj) throws SQLException {
        return false;
    }

    @Override
    public boolean esborrar(EstadisticaJugadorHistoric obj) throws SQLException {
        return false;
    }

    @Override
    public EstadisticaJugadorHistoric cercar(int id) throws SQLException {
        return null;
    }

    @Override
    public int count() throws SQLException {
        return 0;
    }
}
