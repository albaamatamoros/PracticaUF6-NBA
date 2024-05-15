package Model.EstadisticaJugador;

import Model.Jugador.Jugador;
import Model.Model;
import Model.Partit.Partit;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class EstadisticaJugadorDAO implements DAO<EstadisticaJugador>{

    @Override
    public boolean insertar(EstadisticaJugador estadisticaJugador) {
        Connection connexio = null;
        PreparedStatement sentencia = null;

        try {
            connexio = Model.getConnection();
            sentencia = connexio.prepareStatement("INSERT INTO estadistiques_jugadors (assistencies,bloqueigs,equip_id,jugador_id,minuts_jugats,partit_id,punts,rebots_defensius,rebots_ofensius,robades,tirs_anotats,tirs_lliures_anotats,tirs_lliures_tirats,tirs_tirats,tirs_triples_anotats,tirs_triples_tirats) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");

            sentencia.setInt(1,estadisticaJugador.getAssistencies());
            sentencia.setInt(2,estadisticaJugador.getBloqueigs());
            sentencia.setInt(3,estadisticaJugador.getEquipId());
            sentencia.setInt(4,estadisticaJugador.getJugadorId());
            sentencia.setFloat(5,estadisticaJugador.getMinutsJugats());
            sentencia.setInt(6,estadisticaJugador.getPartitId());
            sentencia.setInt(7,estadisticaJugador.getRebotsDefensius());
            sentencia.setInt(8,estadisticaJugador.getRebotsDefensius());
            sentencia.setInt(9,estadisticaJugador.getRebotsOfensius());
            sentencia.setInt(10,estadisticaJugador.getRobades());
            sentencia.setInt(11,estadisticaJugador.getTirsAnotats());
            sentencia.setInt(12,estadisticaJugador.getTirsLliuresAnotats());
            sentencia.setInt(13,estadisticaJugador.getTirsLliuresTirats());
            sentencia.setInt(14,estadisticaJugador.getTirsTirats());
            sentencia.setInt(15,estadisticaJugador.getTirsTriplesAnotats());
            sentencia.setInt(16,estadisticaJugador.getTirsTriplesTirats());

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
}
