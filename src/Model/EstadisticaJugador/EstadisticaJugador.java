package Model.EstadisticaJugador;

public class EstadisticaJugador {
    private int assistencies;
    private int bloqueigs;
    private int equipId;
    private int jugadorId;
    private float minutsJugats;
    private int partitId;
    private int punts;
    private int rebotsDefensius;
    private int rebotsOfensius;
    private int robades;
    private int tirsAnotats;
    private int tirsLliuresAnotats;
    private int tirsLliuresTirats;
    private int tirsTirats;
    private int tirsTriplesAnotats;
    private int tirsTriplesTirats;

    public EstadisticaJugador(int assistencies, int bloqueigs, int equipId, int jugadorId, float minutsJugats, int partitId, int punts, int rebotsDefensius, int rebotsOfensius, int robades, int tirsAnotats, int tirsLliuresAnotats, int tirsLliuresTirats, int tirsTirats, int tirsTriplesAnotats, int tirsTriplesTirats) {
        this.assistencies = assistencies;
        this.bloqueigs = bloqueigs;
        this.equipId = equipId;
        this.jugadorId = jugadorId;
        this.minutsJugats = minutsJugats;
        this.partitId = partitId;
        this.punts = punts;
        this.rebotsDefensius = rebotsDefensius;
        this.rebotsOfensius = rebotsOfensius;
        this.robades = robades;
        this.tirsAnotats = tirsAnotats;
        this.tirsLliuresAnotats = tirsLliuresAnotats;
        this.tirsLliuresTirats = tirsLliuresTirats;
        this.tirsTirats = tirsTirats;
        this.tirsTriplesAnotats = tirsTriplesAnotats;
        this.tirsTriplesTirats = tirsTriplesTirats;
    }

    public int getAssistencies() {
        return assistencies;
    }

    public void setAssistencies(int assistencies) {
        this.assistencies = assistencies;
    }

    public int getBloqueigs() {
        return bloqueigs;
    }

    public void setBloqueigs(int bloqueigs) {
        this.bloqueigs = bloqueigs;
    }

    public int getEquipId() {
        return equipId;
    }

    public void setEquipId(int equipId) {
        this.equipId = equipId;
    }

    public int getJugadorId() {
        return jugadorId;
    }

    public void setJugadorId(int jugadorId) {
        this.jugadorId = jugadorId;
    }

    public float getMinutsJugats() {
        return minutsJugats;
    }

    public void setMinutsJugats(float minutsJugats) {
        this.minutsJugats = minutsJugats;
    }

    public int getPartitId() {
        return partitId;
    }

    public void setPartitId(int partitId) {
        this.partitId = partitId;
    }

    public int getPunts() {
        return punts;
    }

    public void setPunts(int punts) {
        this.punts = punts;
    }

    public int getRebotsDefensius() {
        return rebotsDefensius;
    }

    public void setRebotsDefensius(int rebotsDefensius) {
        this.rebotsDefensius = rebotsDefensius;
    }

    public int getRebotsOfensius() {
        return rebotsOfensius;
    }

    public void setRebotsOfensius(int rebotsOfensius) {
        this.rebotsOfensius = rebotsOfensius;
    }

    public int getRobades() {
        return robades;
    }

    public void setRobades(int robades) {
        this.robades = robades;
    }

    public int getTirsAnotats() {
        return tirsAnotats;
    }

    public void setTirsAnotats(int tirsAnotats) {
        this.tirsAnotats = tirsAnotats;
    }

    public int getTirsLliuresAnotats() {
        return tirsLliuresAnotats;
    }

    public void setTirsLliuresAnotats(int tirsLliuresAnotats) {
        this.tirsLliuresAnotats = tirsLliuresAnotats;
    }

    public int getTirsLliuresTirats() {
        return tirsLliuresTirats;
    }

    public void setTirsLliuresTirats(int tirsLliuresTirats) {
        this.tirsLliuresTirats = tirsLliuresTirats;
    }

    public int getTirsTirats() {
        return tirsTirats;
    }

    public void setTirsTirats(int tirsTirats) {
        this.tirsTirats = tirsTirats;
    }

    public int getTirsTriplesAnotats() {
        return tirsTriplesAnotats;
    }

    public void setTirsTriplesAnotats(int tirsTriplesAnotats) {
        this.tirsTriplesAnotats = tirsTriplesAnotats;
    }

    public int getTirsTriplesTirats() {
        return tirsTriplesTirats;
    }

    public void setTirsTriplesTirats(int tirsTriplesTirats) {
        this.tirsTriplesTirats = tirsTriplesTirats;
    }
}
