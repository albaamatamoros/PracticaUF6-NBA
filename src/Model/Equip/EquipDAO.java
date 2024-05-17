package Model.Equip;

import Model.DAO;
import Model.Jugador.Jugador;
import Model.Connexio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EquipDAO implements DAO<Equip> {
    @Override
    public boolean insertar(Equip equip) throws SQLException {
        Connection connexio = Connexio.getConnection();
        PreparedStatement sentencia = connexio.prepareStatement(
                "INSERT INTO equips (ciutat,nom,acronim,divisio,guanyades,perdudes) VALUES (?,?,?,?,?,?)"
        );

        sentencia.setString(1, equip.getCiutat());
        sentencia.setString(2, equip.getNom());
        sentencia.setString(3, equip.getAcronim());
        sentencia.setString(4, equip.getDivisio());
        sentencia.setInt(5, equip.getGuanyades());
        sentencia.setInt(6,equip.getPerdudes());

        return sentencia.executeUpdate() > 0;
    }

    @Override
    public boolean actualitzar(Equip equip) throws SQLException {
        Connection connexio = Connexio.getConnection();
        PreparedStatement sentencia = connexio.prepareStatement(
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
    }

    @Override
    public boolean esborrar(Equip equip) throws SQLException {
        Connection connexio = Connexio.getConnection();
        PreparedStatement sentencia = connexio.prepareStatement(
                "DELETE FROM equips WHERE equip_id = ?"
        );

        sentencia.setInt(1,equip.getId());

        return sentencia.executeUpdate() > 0;
    }

    @Override
    public Equip cercar(int id) throws SQLException {
        Connection connexio = Connexio.getConnection();
        PreparedStatement sentencia = connexio.prepareStatement(
                "SELECT * FROM equips WHERE equip_id = ?"
        );

        sentencia.setInt(1,id);
        ResultSet rsEquip = sentencia.executeQuery();

        if (rsEquip.next()) {
            Equip equip = new Equip(
                    rsEquip.getString("ciutat"),
                    rsEquip.getString("nom"),
                    rsEquip.getString("acronim"),
                    rsEquip.getString("divisio"),
                    rsEquip.getInt("guanyades"),
                    rsEquip.getInt("perdudes"));

            equip.setId(rsEquip.getInt("equip_id"));
            return equip;
        } else {
            return null;
        }
    }

    @Override
    public int count() throws SQLException {
        Connection connexio = Connexio.getConnection();
        PreparedStatement sentencia = connexio.prepareStatement(
                "SELECT COUNT(*) FROM equips"
        );

        ResultSet rsNumEquips = sentencia.executeQuery();
        rsNumEquips.next();

        return rsNumEquips.getInt(1);
    }

    public List<Jugador> obtenirJugadors(String nom_equip) throws Exception {
        Connection connexio = Connexio.getConnection();
        PreparedStatement sentenciaEquipId = connexio.prepareStatement(
                "SELECT equip_id,CONCAT(ciutat,' ',nom) AS nom_equip FROM `equips` HAVING nom_equip LIKE ?"
        );

        sentenciaEquipId.setString(1, nom_equip);
        ResultSet resultSetId = sentenciaEquipId.executeQuery();

        int equipId = resultSetId.getInt("equip_id");

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
