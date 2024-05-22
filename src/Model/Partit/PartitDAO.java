package Model.Partit;
import Model.Connexio;
import Model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PartitDAO implements DAO<Partit> {

    //MÈTODES D'INTERFÍCIE DAO GENERALS
    @Override
    public boolean insertar(Partit partit) {
        Connection connexio = null;
        PreparedStatement sentencia = null;

        try {
            connexio = Connexio.getConnection();
            sentencia = connexio.prepareStatement("INSERT INTO partits (equip_id,data_partit,matx,resultat) VALUES (?,?,?,?)");

            sentencia.setInt(1,partit.getEquipId());
            sentencia.setDate(2,partit.getDataPartit());
            sentencia.setString(3,partit.getMatx());
            sentencia.setString(4,partit.getResultat());

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
    public boolean actualitzar(Partit partit) {
        Connection connexio = null;
        PreparedStatement sentencia = null;

        try {
            connexio = Connexio.getConnection();
            sentencia = connexio.prepareStatement(
                    "UPDATE partits SET equip_id=?,data_partit=?,matx=?,resultat=? WHERE partit_id=?"
            );

            sentencia.setInt(1,partit.getEquipId());
            sentencia.setDate(2,partit.getDataPartit());
            sentencia.setString(3,partit.getMatx());
            sentencia.setString(4,partit.getResultat());
            sentencia.setInt(5,partit.getPartitId());

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
    public boolean esborrar(Partit partit) {
        Connection connexio = null;
        PreparedStatement sentencia = null;

        try {
            connexio = Connexio.getConnection();
            sentencia = connexio.prepareStatement(
                    "DELETE FROM partits WHERE partit_id = ?"
            );

            sentencia.setInt(1,partit.getPartitId());
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
    public Partit cercar(int id) {
        Connection connexio = null;
        PreparedStatement sentencia = null;

        try {
            connexio = Connexio.getConnection();
            sentencia = connexio.prepareStatement(
                    "SELECT * FROM partits WHERE partit_id = ?"
            );

            sentencia.setInt(1,id);
            ResultSet rsPartit = sentencia.executeQuery();
            if (rsPartit.next()) {
                Partit p = new Partit(
                        rsPartit.getInt("equip_id"),
                        rsPartit.getDate("data_partit"),
                        rsPartit.getString("matx"),
                        rsPartit.getInt("partit_id"),
                        rsPartit.getString("resultat"));

                p.setPartitId(rsPartit.getInt("partit_id"));
                return p;
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
                    "SELECT COUNT(*) FROM partits"
            );

            ResultSet rsNumPartits = sentencia.executeQuery();
            rsNumPartits.next();
            return rsNumPartits.getInt(1);
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
}
