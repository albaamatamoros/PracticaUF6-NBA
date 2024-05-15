package Model.Partit;
import Model.Model;
import Model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Collections;

public class PartitDAO implements DAO<Partit> {

    @Override
    public boolean insertar(Partit partit) {
        Connection connexio = null;
        PreparedStatement sentencia = null;

        try {
            connexio = Model.getConnection();
            sentencia = connexio.prepareStatement("INSERT INTO partits (data_partit,equi_id,matx,partit_id,resultat) VALUES (?,?,?,?,?)");

            sentencia.setDate(1,partit.getDataPartit());
            sentencia.setInt(2,partit.getPartitId());
            sentencia.setInt(3,partit.getPartitId());
            sentencia.setString(4,partit.getMatx());
            sentencia.setInt(5,partit.getPartitId());
            sentencia.setString(6,partit.getResultat());

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
