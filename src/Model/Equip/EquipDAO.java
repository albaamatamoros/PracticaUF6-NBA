package Model.Equip;

import Model.DAO;
import Model.Jugador.Jugador;
import Model.Model;

import javax.xml.transform.Result;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EquipDAO implements DAO<Equip> {
    @Override
    public boolean insertar(Equip equip) {
        Connection connexio = null;
        PreparedStatement sentencia = null;

        try {
            connexio = Model.getConnection();
            sentencia = connexio.prepareStatement(
                    "INSERT INTO equips (ciutat,nom,acronim,divisio,guanyades,perdudes) VALUES (?,?,?,?,?,?)"
            );

            sentencia.setString(1, equip.getCiutat());
            sentencia.setString(2, equip.getNom());
            sentencia.setString(3, equip.getAcronim());
            sentencia.setString(4, equip.getDivisio());
            sentencia.setInt(5, equip.getGuanyades());
            sentencia.setInt(6,equip.getPerdudes());

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
    public boolean actualitzar(Equip equip) {
        Connection connexio = null;
        PreparedStatement sentencia = null;

        try {
            connexio = Model.getConnection();
            sentencia = connexio.prepareStatement(
                    "UPDATE equips SET ciutat = ?, nom = ?, acronim = ?, divisio = ?, guanyades = ?, perdudes = ? WHERE equip_id = ?"
            );

            sentencia.setString(1, equip.getCiutat());
            sentencia.setString(2, equip.getNom());
            sentencia.setString(3, equip.getAcronim());
            sentencia.setString(4, equip.getDivisio());
            sentencia.setInt(5, equip.getGuanyades());
            sentencia.setInt(6, equip.getPerdudes());
            sentencia.setInt(7, equip.getId());

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
    public boolean esborrar(Equip equip) {
        Connection connexio = null;
        PreparedStatement sentencia = null;

        try {
            connexio = Model.getConnection();
            sentencia = connexio.prepareStatement(
                    "DELETE FROM equips WHERE equip_id = ?"
            );

            sentencia.setInt(1,equip.getId());
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
    public Equip cercar(int id) {
        Connection connexio = null;
        PreparedStatement sentencia = null;

        try {
            connexio = Model.getConnection();
            sentencia = connexio.prepareStatement(
                    "SELECT * FROM equips WHERE equip_id = ?"
            );

            sentencia.setInt(1,id);
            ResultSet rsEquip = sentencia.executeQuery();
            if (rsEquip.next()) {
                Equip e = new Equip(
                        rsEquip.getString("ciutat"),
                        rsEquip.getString("nom"),
                        rsEquip.getString("acronim"),
                        rsEquip.getString("divisio"),
                        rsEquip.getInt("guanyades"),
                        rsEquip.getInt("perdudes"));

                e.setId(rsEquip.getInt("equip_id"));
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
            connexio = Model.getConnection();
            sentencia = connexio.prepareStatement(
                    "SELECT COUNT(*) FROM equips"
            );

            ResultSet rsNumEquips = sentencia.executeQuery();
            rsNumEquips.next();
            return rsNumEquips.getInt(1);
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

    public List<Jugador> obtenirJugadors(String nom_equip) throws Exception {
        Connection connexio = Model.getConnection();
        PreparedStatement sentenciaEquipId = connexio.prepareStatement(
                "SELECT equip_id FROM equips WHERE ciutat = ? AND nom = ?"
        );

        sentenciaEquipId.setString(1, nom_equip.split(" ")[0]);
        sentenciaEquipId.setString(2, nom_equip.split(" ")[1]);

        ResultSet resultSetId = sentenciaEquipId.executeQuery();

        int equipId;
        if (resultSetId.next()) {
            equipId = resultSetId.getInt("equip_id");
        } else {
            throw new Exception("No s'ha trobat l'equip");
        }

        PreparedStatement sentenciaJugadors = connexio.prepareStatement(
                "SELECT * FROM jugadors WHERE equip_id = ?"
        );

        sentenciaJugadors.setInt(1, equipId);
        ResultSet rsJugadors = sentenciaJugadors.executeQuery();

        List<Jugador> llistaJugadors = new ArrayList<>();

        while (rsJugadors.next()) {
            llistaJugadors.add(new Jugador(
                    rsJugadors.getString("nom"),
                    rsJugadors.getString("cognom"),
                    rsJugadors.getDate("data_naixement"),
                    rsJugadors.getFloat("alcada"),
                    rsJugadors.getFloat("pes"),
                    rsJugadors.getString("dorsal"),
                    rsJugadors.getString("posicio"),
                    rsJugadors.getInt("equip_id")
            ));
        }

        return llistaJugadors;

    }
}
