package Model.Partit;
import Model.Model;
import Model.DAO;

import java.sql.PreparedStatement;
import java.util.Collections;

public class PartitDAO implements DAO<Partit> {

    @Override
    public boolean insertar(Partit partit) {
        Collections connexio = null;
        PreparedStatement sentencia = null;

        try {
            connexio = Model.getConnection();
            sentencia = connexio.prepareStatement("INSERT INTO ");
        }
    }
}
