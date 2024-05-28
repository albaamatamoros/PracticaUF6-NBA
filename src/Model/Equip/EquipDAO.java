package Model.Equip;
import Model.DAO;
import Model.Jugador.Jugador;
import Model.Connexio;
import Model.Jugador.JugadorDAO;
import Vista.Vista;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class EquipDAO implements DAO<Equip> {

    //MÈTODES D'INTERFÍCIE DAO GENERALS
    @Override
    //Insertar un equip.
    public boolean insertar(Equip equip, Connection connexio) throws SQLException {
        //Preparem la sentència DDL per afegir el nostre nou equip amb PreparedStatement.
        PreparedStatement sentencia = connexio.prepareStatement(
                "INSERT INTO equips (ciutat,nom,acronim,divisio,guanyades,perdudes) VALUES (?,?,?,?,?,?)"
        );

        //Amb la sentència, per cada columna assignem el valor que haurà de recollir a l'hora d'inserir l'equip.
        sentencia.setString(1, equip.getCiutat());
        sentencia.setString(2, equip.getNom());
        sentencia.setString(3, equip.getAcronim());
        sentencia.setString(4, equip.getDivisio());
        sentencia.setInt(5, equip.getGuanyades());
        sentencia.setInt(6,equip.getPerdudes());

        //Tornem un valor major a 0 si s'ha pogut executa la sentència correctament i un valor menor o igual a 0 si no s'ha pogut.
        return sentencia.executeUpdate() > 0;
    }

    @Override
    //Actualitzar un equip.
    public boolean actualitzar(Equip equip, Connection connexio) throws SQLException {
        Vista.mostrarMissatge("Modificant equip...");
        //Preparem la sentència DDL per actualitzar dades d'un equip amb PreparedStatement.
        PreparedStatement sentencia = connexio.prepareStatement(
                "UPDATE equips SET ciutat = ?, nom = ?, acronim = ?, divisio = ?, guanyades = ?, perdudes = ? WHERE equip_id = ?"
        );

        //Amb la sentència, per cada columna assignem el valor que haurà de recollir a l'hora d'inserir l'equip.
        sentencia.setString(1, equip.getCiutat());
        sentencia.setString(2, equip.getNom());
        sentencia.setString(3, equip.getAcronim());
        sentencia.setString(4, equip.getDivisio());
        sentencia.setInt(5, equip.getGuanyades());
        sentencia.setInt(6, equip.getPerdudes());
        sentencia.setInt(7, equip.getId());

        //Tornem un valor major a 0 si s'ha pogut executa la sentència correctament i un valor menor o igual a 0 si no s'ha pogut.
        return sentencia.executeUpdate() > 0;
    }

    @Override
    //Esborrar un equip.
    public boolean esborrar(Equip equip, Connection connexio) throws SQLException {
        //Preparem la sentència DDL per esborrar un equip amb PreparedStatement.
        PreparedStatement sentencia = connexio.prepareStatement(
                "DELETE FROM equips WHERE equip_id = ?"
        );

        //Assignem el valor demanat amb les dades corresponents.
        sentencia.setInt(1,equip.getId());

        //Tornem un valor major a 0 si s'ha pogut executa la sentència correctament i un valor menor o igual a 0 si no s'ha pogut.
        return sentencia.executeUpdate() > 0;
    }

    @Override
    //Cercar un equip.
    public Equip cercar(int id, Connection connexio) throws SQLException {
        //Preparem una consulta per poder cercar un equip en concret.
        PreparedStatement sentencia = connexio.prepareStatement(
                "SELECT * FROM equips WHERE equip_id = ?"
        );

        //Assignem el valor corresponent.
        sentencia.setInt(1,id);

        //Executem la consulta i guardem les dades en l'objecte ResultSet.
        ResultSet rsEquip = sentencia.executeQuery();

        //Amb rsEquip.next() comprovem fila per fila fins que ens retorni true (significa que ha trobat el que buscava) per mostrar les dades de l'equip demanat.
        if (rsEquip.next()) {
            Equip equip = new Equip(
                    rsEquip.getString("ciutat"),
                    rsEquip.getString("nom"),
                    rsEquip.getString("acronim"),
                    rsEquip.getString("divisio"),
                    rsEquip.getInt("guanyades"),
                    rsEquip.getInt("perdudes"));

            equip.setId(rsEquip.getInt("equip_id"));
            //Un cop trobat retornem l'objecte equip.
            return equip;
        } else {
            return null;
        }
    }

    @Override
    //Contar quants equips tenim.
    public int count(Connection connexio) throws SQLException {
        PreparedStatement sentencia = connexio.prepareStatement(
                "SELECT COUNT(*) FROM equips"
        );

        ResultSet rsNumEquips = sentencia.executeQuery();
        rsNumEquips.next();

        return rsNumEquips.getInt(1);
    }

    //MÈTODES D'INTERFÍCIE ESPECÍFICS EXERCICIS

    //1 Llistar tots els jugadors d'un equip
    public List<Jugador> obtenirJugadors(String nomEquip, Connection connexio) throws Exception {
        Vista.mostrarMissatge("CERCANT JUGADORS...");
        //Preparem la nostra sentència amb la consulta DML que volem utilitzar.
        PreparedStatement sentenciaJugadors = connexio.prepareStatement(
                //La nostra sentència mostra amb una concatenació el nom i el cognom dels jugadors que pertanyin de l'equip que insereix l'usuari.
                "SELECT j.jugador_id,CONCAT(e.ciutat,' ',e.nom) AS nom_equip FROM jugadors j INNER JOIN equips e ON j.equip_id = e.equip_id HAVING nom_equip = ?"
        );

        //Diem al programa que el valor que necessitem equival a nomEquip.
        sentenciaJugadors.setString(1, nomEquip);
        ResultSet rsJugadors = sentenciaJugadors.executeQuery();

        //Fem una llista de jugadors.
        List<Jugador> llistaJugadors;

        if (rsJugadors.next()) {
            llistaJugadors = new ArrayList<>();
            JugadorDAO jugadorDAO = new JugadorDAO();

            //Amb rsJugadors.next() anem llegint i afegint els jugadors que complexin els requisits a la llista jugadors.
            while (rsJugadors.next()) {
                llistaJugadors.add(jugadorDAO.cercar(rsJugadors.getInt("j.jugador_id"),connexio));
            }
        } else {
            throw new Exception("Equip no trobat");
        }

        return llistaJugadors;

    }

    //4 Inserir un nou jugador a un equip. (Cercar l'ID)
    public int cercarIdPerNom(String nomEquip, Connection connexio) throws SQLException {
        PreparedStatement sentencia = connexio.prepareStatement(
                "SELECT equip_id,CONCAT(ciutat,' ',nom) AS nom_equip FROM equips HAVING nom_equip = ?"
        );

        sentencia.setString(1,nomEquip);
        ResultSet rsEquip = sentencia.executeQuery();

        if (rsEquip.next()) {
            int equipId = rsEquip.getInt("equip_id");
            return equipId;
        } else {
            return 0;
        }
    }

    //3 Llistar tots els partits jugats per un equip amb el seu resultat.
    public List<Set<Map.Entry<String,Integer>>> obtenirResultatPartits(String nomEquip, Connection connexio) throws Exception {
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

        PreparedStatement sentenciaPuntsPartitsRivals = connexio.prepareStatement(
                "SELECT CONCAT(e.ciutat,' ',e.nom) AS nom,SUM(punts) AS punts " +
                        "FROM estadistiques_jugadors es " +
                        "INNER JOIN equips e ON e.equip_id = es.equip_id " +
                        "WHERE es.partit_id IN (SELECT partit_id FROM partits WHERE LEFT(matx,3) = ?) " +
                        "AND es.equip_id != ? " +
                        "GROUP BY partit_id,es.equip_id " +
                        "ORDER BY partit_id"
        );

        sentenciaPuntsPartitsPropis.setInt(1, equipId);
        ResultSet rsPuntsPartitsPropis = sentenciaPuntsPartitsPropis.executeQuery();

        sentenciaPuntsPartitsRivals.setString(1, acronim);
        sentenciaPuntsPartitsRivals.setInt(2, equipId);
        ResultSet rsPuntsPartitsRivals = sentenciaPuntsPartitsRivals.executeQuery();

        List<Set<Map.Entry<String,Integer>>> llistaResultats = new ArrayList<>();

        while (rsPuntsPartitsPropis.next() && rsPuntsPartitsRivals.next()) {
            String nomPropi = rsPuntsPartitsPropis.getString("nom");
            int puntuacioPropia = rsPuntsPartitsPropis.getInt("punts");

            String nomRival = rsPuntsPartitsRivals.getString("nom");
            int puntuacioRival = rsPuntsPartitsRivals.getInt("punts");

            Set<Map.Entry<String,Integer>> resultatPartit = new LinkedHashSet<>();

            Map.Entry<String,Integer> resultatPropi = new AbstractMap.SimpleEntry<>(nomPropi, puntuacioPropia);
            resultatPartit.add(resultatPropi);

            Map.Entry<String,Integer> resultatRival = new AbstractMap.SimpleEntry<>(nomRival, puntuacioRival);
            resultatPartit.add(resultatRival);

            llistaResultats.add(resultatPartit);
        }

        return llistaResultats;
    }
}
