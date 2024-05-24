package Model.EstadisticsJugadorsHistorics;

public class EstadisticsJugadorsHistorics {

    private int jugadorId;
    private int equipId;
    private int partitId;
    private int minutsJugats;
    private int punts;
    private int tirsAnotats;
    private int tirsTirats;
    private int tirsTriplesAnotats;
    private int tirsTriplesTirats;
    private int tirsLliuresAnotats;
    private int tirsLliuresTirats;
    private int rebotsOfensius;
    private int rebotsDefensius;
    private int assistencies;
    private int robades;
    private int bloqueigs;

    public EstadisticsJugadorsHistorics(int jugadorId, int equipId, int partitId, int minutsJugats, int punts, int tirsAnotats, int tirsTirats, int tirsTriplesAnotats, int tirsTriplesTirats, int tirsLliuresAnotats, int tirsLliuresTirats, int rebotsOfensius, int rebotsDefensius, int assistencies, int robades, int bloqueigs) {
        this.jugadorId = jugadorId;
        this.equipId = equipId;
        this.partitId = partitId;
        this.minutsJugats = minutsJugats;
        this.punts = punts;
        this.tirsAnotats = tirsAnotats;
        this.tirsTirats = tirsTirats;
        this.tirsTriplesAnotats = tirsTriplesAnotats;
        this.tirsTriplesTirats = tirsTriplesTirats;
        this.tirsLliuresAnotats = tirsLliuresAnotats;
        this.tirsLliuresTirats = tirsLliuresTirats;
        this.rebotsOfensius = rebotsOfensius;
        this.rebotsDefensius = rebotsDefensius;
        this.assistencies = assistencies;
        this.robades = robades;
        this.bloqueigs = bloqueigs;
    }

    public int getJugadorId() {
        return jugadorId;
    }

    public void setJugadorId(int jugadorId) {
        this.jugadorId = jugadorId;
    }

    public int getEquipId() {
        return equipId;
    }

    public void setEquipId(int equipId) {
        this.equipId = equipId;
    }

    public int getPartitId() {
        return partitId;
    }

    public void setPartitId(int partitId) {
        this.partitId = partitId;
    }

    public int getMinutsJugats() {
        return minutsJugats;
    }

    public void setMinutsJugats(int minutsJugats) {
        this.minutsJugats = minutsJugats;
    }

    public int getPunts() {
        return punts;
    }

    public void setPunts(int punts) {
        this.punts = punts;
    }

    public int getTirsAnotats() {
        return tirsAnotats;
    }

    public void setTirsAnotats(int tirsAnotats) {
        this.tirsAnotats = tirsAnotats;
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

    public int getRebotsOfensius() {
        return rebotsOfensius;
    }

    public void setRebotsOfensius(int rebotsOfensius) {
        this.rebotsOfensius = rebotsOfensius;
    }

    public int getRebotsDefensius() {
        return rebotsDefensius;
    }

    public void setRebotsDefensius(int rebotsDefensius) {
        this.rebotsDefensius = rebotsDefensius;
    }

    public int getAssistencies() {
        return assistencies;
    }

    public void setAssistencies(int assistencies) {
        this.assistencies = assistencies;
    }

    public int getRobades() {
        return robades;
    }

    public void setRobades(int robades) {
        this.robades = robades;
    }

    public int getBloqueigs() {
        return bloqueigs;
    }

    public void setBloqueigs(int bloqueigs) {
        this.bloqueigs = bloqueigs;
    }
}
