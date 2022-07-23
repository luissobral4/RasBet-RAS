package Models;

public class Bet {
    private double value;
    private double odd;
    private int status;
    private int betID;
    /**
     * Construtores para objetos da classe Filial.
     */
    public Bet(){
        this.value = 0.0;
        this.odd = 0.0;
        this.status = 0;
        this.betID = 0;
    }

    public Bet(int betID, double odd, int status, double value){
        this.value = value;
        this.odd = odd;
        this.status = status;
        this.betID = betID;
    }

    public Bet(Bet u){
        this.value = u.getValue();
        this.odd = u.getOdd();
        this.status = u.getStatus();
        this.betID = u.getBetID();
    }

    public int getBetID() {
        return betID;
    }

    public double getOdd() {
        return odd;
    }

    public int getStatus() {
        return status;
    }

    public double getValue() {
        return value;
    }

    public void setBetID(int betID) {
        this.betID = betID;
    }

    public void setOdd(double odd) {
        this.odd = odd;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public Bet clone(){
        return new Bet(this);
    }

    public boolean equals(Object obj){
        if (this == obj) return true;
        if (obj == null || this.getClass() != obj.getClass()) return false;
        Bet u = (Bet) obj;
        return this.betID == u.getBetID() &&
                this.odd == u.getOdd() &&
                this.status == u.getStatus() &&
                this.value == u.getValue();
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("+------------ BET %3d -----------+\n",betID));//"------------- EVENT "+eventID+" -------------\n");
        sb.append(String.format("| Odd: %25s |\n",this.odd));
        String state = "";
         if (status == 0)
            state = "Aberto";
        else state = "Fechado";
        sb.append(String.format("| Status: %22s |\n",state));
        sb.append(String.format("| Value: %23s |\n",this.value));
        //sb.append("+------------- BET "+betID+" ------------+\n");

        return sb.toString();
    }
}
