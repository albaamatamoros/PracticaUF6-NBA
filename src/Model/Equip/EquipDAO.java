package Model.Equip;
import Model.DAO;
import Model.Jugador.Jugador;
import Model.Connexio;
import Model.Jugador.JugadorDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

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

    public List<Jugador> obtenirJugadors(String nomEquip) throws Exception {
        Connection connexio = Connexio.getConnection();
        PreparedStatement sentenciaJugadors = connexio.prepareStatement(
                "SELECT j.jugador_id,CONCAT(e.ciutat,' ',e.nom) AS nom_equip FROM jugadors j INNER JOIN equips e ON j.equip_id = e.equip_id HAVING nom_equip = ?"
        );

        sentenciaJugadors.setString(1, nomEquip);
        ResultSet rsJugadors = sentenciaJugadors.executeQuery();

        List<Jugador> llistaJugadors;

        if (rsJugadors.next()) {
            llistaJugadors = new ArrayList<>();
            JugadorDAO jugadorDAO = new JugadorDAO();

            while (rsJugadors.next()) {
                llistaJugadors.add(jugadorDAO.cercar(rsJugadors.getInt("jugador_id")));
            }
        } else {
            throw new Exception("Equip no trobat");
        }

        return llistaJugadors;

    }

    public List<Set<Map.Entry<String,Integer>>> obtenirResultatPartits(String nomEquip) throws Exception {
        Connection connexio = Connexio.getConnection();
        PreparedStatement sentenciaEquip = connexio.prepareStatement(
                "SELECT equip_id,acronim,CONCAT(ciutat,' ',nom) AS nom_equip FROM equips HAVING nom_equip LIKE ?"
        );

        sentenciaEquip.setString(1, nomEquip);
        ResultSet rsEquip = sentenciaEquip.executeQuery();

        int equipId;
        String acronim;

        if (rsEquip.next()) {
            equipId = rsEquip.getInt("equip_id");
            acronim = rsEquip.getString("acronim");
        } else {
            throw new Exception("Equip no trobat");
        }

        PreparedStatement sentenciaPuntsPartitsPropis = connexio.prepareStatement(
                "SELECT CONCAT(e.ciutat,' ',e.nom) AS nom,SUM(punts) AS punts " +
                        "FROM estadistiques_jugadors es " +
                        "INNER JOIN equips e ON e.equip_id = es.equip_id " +
                        "WHERE es.equip_id = ? GROUP BY es.partit_id ORDER BY es.partit_id"
        );

        sentenciaPuntsPartitsPropis.setInt(1, equipId);
        ResultSet rsPuntsPartitsPropis = sentenciaPuntsPartitsPropis.executeQuery();

        PreparedStatement sentenciaPuntsPartitsRivals = connexio.prepareStatement(
                "SELECT CONCAT(e.ciutat,' ',e.nom) AS nom,SUM(punts) AS punts " +
                        "FROM estadistiques_jugadors es " +
                        "INNER JOIN equips e ON e.equip_id = es.equip_id " +
                        "WHERE es.partit_id IN (SELECT partit_id FROM partits WHERE LEFT(matx,3) = ?) " +
                        "AND es.equip_id != ? " +
                        "GROUP BY partit_id,es.equip_id " +
                        "ORDER BY partit_id"
        );

        sentenciaPuntsPartitsRivals.setString(1, acronim);
        sentenciaPuntsPartitsRivals.setInt(2, equipId);
        ResultSet rsPuntsPartitsRivals = sentenciaPuntsPartitsRivals.executeQuery();

        List<Set<Map.Entry<String,Integer>>> llistaResultats = new ArrayList<>();

        while (rsPuntsPartitsPropis.next() && rsPuntsPartitsRivals.next()) {
            String nomPropi = rsPuntsPartitsPropis.getString("nom");
            int puntuacioPropia = rsPuntsPartitsPropis.getInt("punts");

            String nomRival = rsPuntsPartitsRivals.getString("nom");
            int puntuacioRival = rsPuntsPartitsRivals.getInt("punts");

            Set<Map.Entry<String,Integer>> resultatPartit = new HashSet<>();

            Map.Entry<String,Integer> resultatPropi = new AbstractMap.SimpleEntry<>(nomPropi, puntuacioPropia);
            resultatPartit.add(resultatPropi);

            Map.Entry<String,Integer> resultatRival = new AbstractMap.SimpleEntry<>(nomRival, puntuacioRival);
            resultatPartit.add(resultatRival);

            llistaResultats.add(resultatPartit);
        }

        return llistaResultats;
    }
}
