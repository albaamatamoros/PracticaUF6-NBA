package Model.Partit;
import java.sql.Date;

public class Partit {
    private Date dataPartit;
    private int equipId;
    private String matx;
    private int partitId;
    private String resultat;

    public Partit(Date dataPartit, int equipId, String matx, int partitId, String resultat) {
        this.dataPartit = dataPartit;
        this.equipId = equipId;
        this.matx = matx;
        this.partitId = partitId;
        this.resultat = resultat;
    }

    public Date getDataPartit() {
        return dataPartit;
    }

    public void setDataPartit(Date dataPartit) {
        this.dataPartit = dataPartit;
    }

    public int getEquipId() {
        return equipId;
    }

    public void setEquipId(int equipId) {
        this.equipId = equipId;
    }

    public String getMatx() {
        return matx;
    }

    public void setMatx(String matx) {
        this.matx = matx;
    }

    public int getPartitId() {
        return partitId;
    }

    public void setPartitId(int partitId) {
        this.partitId = partitId;
    }

    public String getResultat() {
        return resultat;
    }

    public void setResultat(String resultat) {
        this.resultat = resultat;
    }
}
