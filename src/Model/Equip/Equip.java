package Model.Equip;

public class Equip {
    private int id;
    private String ciutat;
    private String nom;
    private String acronim;
    private String divisio;
    private int guanyades;
    private int perdudes;

    public Equip(String ciutat, String nom, String acronim, String divisio, int guanyades, int perdudes) {
        this.id = 0;
        this.ciutat = ciutat;
        this.nom = nom;
        this.acronim = acronim;
        this.divisio = divisio;
        this.guanyades = guanyades;
        this.perdudes = perdudes;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getCiutat() {
        return ciutat;
    }

    public void setCiutat(String ciutat) {
        this.ciutat = ciutat;
    }

    public String getNom() {
        return nom;
    }

    public String getAcronim() {
        return acronim;
    }

    public String getDivisio() {
        return divisio;
    }

    public int getGuanyades() {
        return guanyades;
    }

    public int getPerdudes() {
        return perdudes;
    }
}
