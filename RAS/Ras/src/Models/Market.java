package Models;

public class Market {
    private String sport;
    private int sportID;

    public Market(){
        this.sport = " ";
        this.sportID = 0;
    }

    public Market(int sportID, String sport){
        this.sport = sport;
        this.sportID = sportID;
    }

    public Market(Market m){
        this.sport = m.getSport();
        this.sportID = m.getSportID();
    }

    public String getSport() {
        return sport;
    }

    public int getSportID() {
        return sportID;
    }

    public void setSport(String sport) {
        this.sport = sport;
    }

    public void setSportID(int sportID) {
        this.sportID = sportID;
    }

    public Market clone(){
        return new Market(this);
    }

    public boolean equals(Object obj){
        if (this == obj) return true;
        if (obj == null || this.getClass() != obj.getClass()) return false;
        Market m = (Market) obj;
        return this.sport.equals(m.getSport()) &&
               this.sportID == m.getSportID();
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Sport: ").append(this.sport).append("\n");
        sb.append("SportID: ").append(this.sportID).append("\n");
        return sb.toString();
    }

    
}
