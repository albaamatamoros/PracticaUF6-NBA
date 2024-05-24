package Model.EstadisticsJugadorsHistorics;
import Model.Connexio;
import Model.DAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class EstadisticsJugadorsHistoricsDAO implements DAO<EstadisticsJugadorsHistorics> {

    @Override
    public boolean insertar(EstadisticsJugadorsHistorics estadisticsJugadorsHistorics) throws SQLException {
        Connection connexio = null;
        PreparedStatement sentencia = null;

        try {
            connexio = Connexio.getConnection();
            sentencia = connexio.prepareStatement("INSERT INTO estadistics_jugadors_historics (jugador_id,equip_id,partit_id,minuts_jugats,punts,tirs_anotats,tirs_tirats,tirs_triples_antotats,tirs_triples_tirats,tirs_lliures_anotats,tirs_lliures_tirats,rebots_ofensius,rebots_defensius,assistencies,robades,bloqueigs) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");

            sentencia.setInt(1,estadisticsJugadorsHistorics.getJugadorId());
            sentencia.setInt(2,estadisticsJugadorsHistorics.getEquipId());
            sentencia.setInt(3,estadisticsJugadorsHistorics.getPartitId());
            sentencia.setFloat(4,estadisticsJugadorsHistorics.getMinutsJugats());
            sentencia.setInt(5,estadisticsJugadorsHistorics.getPunts());
            sentencia.setInt(6,estadisticsJugadorsHistorics.getTirsAnotats());
            sentencia.setInt(7,estadisticsJugadorsHistorics.getTirsTirats());
            sentencia.setInt(8,estadisticsJugadorsHistorics.getTirsTriplesAnotats());
            sentencia.setInt(9,estadisticsJugadorsHistorics.getTirsTriplesTirats());
            sentencia.setInt(10,estadisticsJugadorsHistorics.getTirsLliuresAnotats());
            sentencia.setInt(11,estadisticsJugadorsHistorics.getTirsLliuresTirats());
            sentencia.setInt(12,estadisticsJugadorsHistorics.getRebotsOfensius());
            sentencia.setInt(13,estadisticsJugadorsHistorics.getRebotsDefensius());
            sentencia.setInt(14,estadisticsJugadorsHistorics.getAssistencies());
            sentencia.setInt(15,estadisticsJugadorsHistorics.getRobades());
            sentencia.setInt(16,estadisticsJugadorsHistorics.getBloqueigs());
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
    public boolean actualitzar(EstadisticsJugadorsHistorics obj) throws SQLException {
        return false;
    }

    @Override
    public boolean esborrar(EstadisticsJugadorsHistorics obj) throws SQLException {
        return false;
    }

    @Override
    public EstadisticsJugadorsHistorics cercar(int id) throws SQLException {
        return null;
    }

    @Override
    public int count() throws SQLException {
        return 0;
    }
}
