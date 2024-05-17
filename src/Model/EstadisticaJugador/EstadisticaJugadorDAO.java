package Model.EstadisticaJugador;

import Model.DAO;
import Model.Equip.Equip;
import Model.Model;
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
            sentencia = connexio.prepareStatement("INSERT INTO estadistiques_jugadors () VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");


            sentencia.setInt(4,estadisticaJugador.getJugadorId());
            sentencia.setInt(3,estadisticaJugador.getEquipId());
            sentencia.setInt(6,estadisticaJugador.getPartitId());
            sentencia.setFloat(5,estadisticaJugador.getMinutsJugats());
            sentencia.setInt(1,estadisticaJugador.getPunts());
            sentencia.setInt(11,estadisticaJugador.getTirsAnotats());
            sentencia.setInt(14,estadisticaJugador.getTirsTirats());
            sentencia.setInt(15,estadisticaJugador.getTirsTriplesAnotats());
            sentencia.setInt(16,estadisticaJugador.getTirsTriplesTirats());
            sentencia.setInt(12,estadisticaJugador.getTirsLliuresAnotats());
            sentencia.setInt(13,estadisticaJugador.getTirsLliuresTirats());
            sentencia.setInt(9,estadisticaJugador.getRebotsOfensius());
            sentencia.setInt(8,estadisticaJugador.getRebotsDefensius());
            sentencia.setInt(7,estadisticaJugador.getAssistencies());
            sentencia.setInt(10,estadisticaJugador.getRobades());
            sentencia.setInt(2,estadisticaJugador.getBloqueigs());

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
    public boolean actualitzar(Equip equip) {return true;}

    @Override
    public boolean esborrar(Equip equip) {return false;}

    @Override
    public int count() {return 1;}
}
