package Model.Partit;

import java.util.Date;

public class Partit {
    private Date data_partit;
    private int equip_id;
    private String matx;
    private int partit_id;
    private String resultat;

    public Partit(Date data_partit, int equip_id, String matx, int partit_id, String resultat) {
        this.data_partit = data_partit;
        this.equip_id = equip_id;
        this.matx = matx;
        this.partit_id = partit_id;
        this.resultat = resultat;
    }

    public Date getData_partit() {
        return data_partit;
    }

    public void setData_partit(Date data_partit) {
        this.data_partit = data_partit;
    }

    public int getEquip_id() {
        return equip_id;
    }

    public void setEquip_id(int equip_id) {
        this.equip_id = equip_id;
    }

    public String getMatx() {
        return matx;
    }

    public void setMatx(String matx) {
        this.matx = matx;
    }

    public int getPartit_id() {
        return partit_id;
    }

    public void setPartit_id(int partit_id) {
        this.partit_id = partit_id;
    }

    public String getResultat() {
        return resultat;
    }

    public void setResultat(String resultat) {
        this.resultat = resultat;
    }
}
