package Model.Partit;
import java.sql.Date;

public class Partit {
    private int partitId;
    private int equipId;
    private Date dataPartit;
    private String matx;
    private String resultat;

    public Partit( int partitId, int equipId, Date dataPartit, String matx, String resultat) {
        this.partitId = partitId;
        this.equipId = equipId;
        this.dataPartit = dataPartit;
        this.matx = matx;
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
