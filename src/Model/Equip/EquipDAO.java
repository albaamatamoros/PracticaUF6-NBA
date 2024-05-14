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
    public boolean insertar(Equip obj) {
        return false;
    }

    @Override
    public boolean actualitzar(Equip obj) {
        return false;
    }

    @Override
    public boolean esborrar(Equip obj) {
        return false;
    }

    @Override
    public Equip cercar(int id) {
        return null;
    }

    public List<Jugador> obtenirJugadors(String nom_equip) throws Exception {
        Connection connexio = null;
        PreparedStatement sentenciaEquipId = null;
        PreparedStatement sentenciaIdsJugadors = null;
        PreparedStatement sentenciaJugador = null;

        try {
            connexio = Model.getConnection();
            sentenciaEquipId = connexio.prepareStatement(
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

            sentenciaIdsJugadors = connexio.prepareStatement(
                    "SELECT jugador_id FROM jugadors WHERE equip_id = ?"
            );

            sentenciaIdsJugadors.setInt(1, equipId);
            ResultSet rsIdsJugadors = sentenciaIdsJugadors.executeQuery();

            sentenciaJugador = connexio.prepareStatement(
                    "SELECT * FROM jugadors WHERE jugador_id = ?"
            );

            List<Jugador> llistaJugadors = new ArrayList<>();

            while (rsIdsJugadors.next()) {
                sentenciaJugador.setInt(1, rsIdsJugadors.getInt(1));
                ResultSet rsJugador = sentenciaJugador.executeQuery();
                rsJugador.next();
                llistaJugadors.add(new Jugador(
                        rsJugador.getString("nom"),
                        rsJugador.getString("cognom"),
                        rsJugador.getDate("data_naixement"),
                        rsJugador.getFloat("alcada"),
                        rsJugador.getFloat("pes"),
                        rsJugador.getString("dorsal"),
                        rsJugador.getString("posicio"),
                        rsJugador.getInt("equip_id")
                ));
            }

            return llistaJugadors;

        } catch (SQLException e) {
            return null;
        }
    }
}
