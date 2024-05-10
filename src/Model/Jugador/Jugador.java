package Model.Jugador;

import java.util.Date;

public class Jugador {
    private int id;
    private String nom;
    private String cognom;
    private Date dataNaixement;
    private float alcada;
    private float pes;
    private String dorsal;
    private String posicio;
    private int equipId;

    public Jugador(String nom, String cognom, Date dataNaixement, float alcada, float pes, String dorsal, String posicio, int equipId) {
        this.nom = nom;
        this.cognom = cognom;
        this.dataNaixement = dataNaixement;
        this.alcada = alcada;
        this.pes = pes;
        this.dorsal = dorsal;
        this.posicio = posicio;
        this.equipId = equipId;
    }
}
