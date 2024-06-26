package Model.Jugador;
import java.sql.Date;

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

    //Constructor Jugador
    public Jugador(String nom, String cognom, Date dataNaixement, float alcada, float pes, String dorsal, String posicio, int equipId) {
        this.id = 0;
        this.nom = nom;
        this.cognom = cognom;
        this.dataNaixement = dataNaixement;
        this.alcada = alcada;
        this.pes = pes;
        this.dorsal = dorsal;
        this.posicio = posicio;
        this.equipId = equipId;
    }

    //Setter i getters Jugador
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public String getCognom() {
        return cognom;
    }

    public Date getDataNaixement() {
        return dataNaixement;
    }

    public float getAlcada() {
        return alcada;
    }

    public float getPes() {
        return pes;
    }

    public String getDorsal() {
        return dorsal;
    }

    public String getPosicio() {
        return posicio;
    }

    public int getEquipId() {
        return equipId;
    }

    public void setEquipId(int equipId) {
        this.equipId = equipId;
    }
}
