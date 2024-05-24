package Model.EstadisticaJugador;
import Model.DAO;
import Model.Connexio;
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
    public boolean insertar(EstadisticaJugador estadisticaJugador) {
        Connection connexio = null;
        PreparedStatement sentencia = null;

        try {
            connexio = Connexio.getConnection();
            sentencia = connexio.prepareStatement("INSERT INTO estadistiques_jugadors (jugador_id,equip_id,partit_id,minuts_jugats,punts,tirs_anotats,tirs_tirats,tirs_triples_antotats,tirs_triples_tirats,tirs_lliures_anotats,tirs_lliures_tirats,rebots_ofensius,rebots_defensius,assistencies,robades,bloqueigs) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");


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
    public boolean actualitzar(EstadisticaJugador estadisticaJugador) {
        Connection connexio = null;
        PreparedStatement sentencia = null;

        try {
            connexio = Connexio.getConnection();
            sentencia = connexio.prepareStatement(
                    "UPDATE estadistiques_jugadors SET equip_id=?,partit_id=?,minuts_jugats=?,punts=?,tirs_anotats=?,tirs_tirats=?,tirs_triples_antotats=?,tirs_triples_tirats=?,tirs_lliures_anotats=?,tirs_lliures_tirats=?,rebots_ofensius=?,rebots_defensius=?,assistencies=?,robades=?,bloqueigs=? WHERE jugador_id=?"
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
    public boolean esborrar(EstadisticaJugador estadisticaJugador) {
        Connection connexio = null;
        PreparedStatement sentencia = null;

        try {
            connexio = Connexio.getConnection();
            sentencia = connexio.prepareStatement(
                    "DELETE FROM estadistiques_jugadors WHERE jugador_id = ?"
            );

            sentencia.setInt(1,estadisticaJugador.getJugadorId());
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
    public EstadisticaJugador cercar(int id) {
        Connection connexio = null;
        PreparedStatement sentencia = null;

        try {
            connexio = Connexio.getConnection();
            sentencia = connexio.prepareStatement(
                    "SELECT * FROM estadistiques_jugadors WHERE jugador_id = ?"
            );

            sentencia.setInt(1,id);
            ResultSet rsEstadisticaJugador = sentencia.executeQuery();
            if (rsEstadisticaJugador.next()) {
                EstadisticaJugador e = new EstadisticaJugador(
                        rsEstadisticaJugador.getInt("jugador_id"),
                        rsEstadisticaJugador.getInt("equip_id"),
                        rsEstadisticaJugador.getInt("partit_id"),
                        rsEstadisticaJugador.getFloat("minuts_jugats"),
                        rsEstadisticaJugador.getInt("punts"),
                        rsEstadisticaJugador.getInt("tirs_anotats"),
                        rsEstadisticaJugador.getInt("tirs_tirats"),
                        rsEstadisticaJugador.getInt("tirs_triples_antotats"),
                        rsEstadisticaJugador.getInt("tirs_triples_tirats"),
                        rsEstadisticaJugador.getInt("tirs_lliures_anotats"),
                        rsEstadisticaJugador.getInt("tirs_lliures_tirats"),
                        rsEstadisticaJugador.getInt("rebots_ofensius"),
                        rsEstadisticaJugador.getInt("rebots_defensius"),
                        rsEstadisticaJugador.getInt("assistencies"),
                        rsEstadisticaJugador.getInt("robades"),
                        rsEstadisticaJugador.getInt("bloqueigs"));

                e.setJugadorId(rsEstadisticaJugador.getInt("jugador_id"));
                return e;
            } else { return null; }

        } catch (SQLException e) {
            return null;
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
    public int count() {
        Connection connexio = null;
        PreparedStatement sentencia = null;

        try {
            connexio = Connexio.getConnection();
            sentencia = connexio.prepareStatement(
                    "SELECT COUNT(*) FROM estadistiques_jugadors"
            );

            ResultSet rsEstadisticaJugador = sentencia.executeQuery();
            rsEstadisticaJugador.next();
            return rsEstadisticaJugador.getInt(1);
        } catch (SQLException e) {
            return -1;
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

    //MÈTODES D'INTERFÍCIE ESPECÍFICS EXERCICIS
    public void traspasarEstadistiques(int jugadorId){
        Connection connexio = null;
        PreparedStatement sentencia = null;

        try {
            connexio = Connexio.getConnection();
            sentencia = connexio.prepareStatement(
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
                        rsEstadisticaJugador.getInt("tirs_triples_antotats"),
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
        } catch (SQLException e) {
            System.out.println(e.getMessage());
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
