package Model.Partit;
import Model.Connexio;
import Model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class PartitDAO implements DAO<Partit> {

    //MÈTODES D'INTERFÍCIE DAO GENERALS
    @Override
    //Insertar Partit
    public boolean insertar(Partit partit, Connection connexio) throws SQLException {
        PreparedStatement sentencia = connexio.prepareStatement(
                "INSERT INTO partits (equip_id,data_partit,matx,resultat) VALUES (?,?,?,?)"
        );

        sentencia.setInt(1,partit.getEquipId());
        sentencia.setDate(2,partit.getDataPartit());
        sentencia.setString(3,partit.getMatx());
        sentencia.setString(4,partit.getResultat());

        return sentencia.executeUpdate() > 0;
    }

    @Override
    //Actualitzar Partit
    public boolean actualitzar(Partit partit, Connection connexio) throws SQLException {
        PreparedStatement sentencia = connexio.prepareStatement(
                "UPDATE partits SET data_partit= ?, matx= ?, resultat= ? WHERE partit_id= ? AND equip_id = ?"
        );

        sentencia.setDate(1,partit.getDataPartit());
        sentencia.setString(2,partit.getMatx());
        sentencia.setString(3,partit.getResultat());
        sentencia.setInt(4,partit.getPartitId());
        sentencia.setInt(5,partit.getEquipId());

        return sentencia.executeUpdate() > 0;
    }

    @Override
    //Esborrar Partit
    public boolean esborrar(Partit partit, Connection connexio) throws SQLException {
        PreparedStatement sentencia = connexio.prepareStatement("DELETE FROM partits WHERE partit_id = ?");

        sentencia.setInt(1,partit.getPartitId());

        return sentencia.executeUpdate() > 0;
    }

    @Override
    //Cercar Partits
    public Partit cercar(int id, Connection connexio) throws SQLException {
        PreparedStatement sentencia = connexio.prepareStatement(
                "SELECT * FROM partits WHERE partit_id = ?"
        );

        sentencia.setInt(1,id);
        ResultSet rsPartit = sentencia.executeQuery();

        if (rsPartit.next()) {
            Partit p = new Partit(
                    rsPartit.getInt("partit_id"),
                    rsPartit.getInt("equip_id"),
                    rsPartit.getDate("data_partit"),
                    rsPartit.getString("matx"),
                    rsPartit.getString("resultat"));

            p.setPartitId(rsPartit.getInt("partit_id"));
            return p;
        } else {
            return null;
        }
    }

    @Override
    //Contar Partits
    public int count(Connection connexio) throws SQLException {
        PreparedStatement sentencia = connexio.prepareStatement(
                "SELECT COUNT(*) FROM partits"
        );

        ResultSet rsNumPartits = sentencia.executeQuery();
        rsNumPartits.next();
        return rsNumPartits.getInt(1);
    }

    //MÈTODES D'INTERFÍCIE ESPECÍFICS EXERCICIS

    //6 Actualitzar les dades de jugadors o equips després d'un partit.
    public boolean actualitzarEnMassa(List<Partit> partits, Connection connexio) throws SQLException {
        PreparedStatement sentencia = connexio.prepareStatement(
                "UPDATE partits SET data_partit=?, matx=?, resultat=? WHERE partit_id=? AND equip_id =?"
        );
        connexio.setAutoCommit(false);

        for (Partit partit : partits) {
            sentencia.setDate(1,partit.getDataPartit());
            sentencia.setString(2,partit.getMatx());
            sentencia.setString(3,partit.getResultat());
            sentencia.setInt(4,partit.getPartitId());
            sentencia.setInt(5,partit.getEquipId());

            boolean correcte = sentencia.executeUpdate() > 0;

            if (!correcte) {
                connexio.rollback();
                return false;
            }
        }
        connexio.commit();
        return true;
    }

    //7 Buscar si exsistei el partit inserit
    public int cercarPartitID(int partitID, Connection connexio) throws SQLException {
        //Preparem la sentencia SQL.
        PreparedStatement sentencia = connexio.prepareStatement(
                "SELECT * FROM partits WHERE partit_id = ?"
        );

        sentencia.setInt(1,partitID);
        ResultSet rsPartit = sentencia.executeQuery();

        if (rsPartit.next()) {
            return rsPartit.getInt("partit_id");
        } else {
            return 0;
        }
    }
}
