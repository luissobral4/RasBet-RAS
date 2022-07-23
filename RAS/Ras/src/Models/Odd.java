package Models;

public class Odd {
    private double odd;
    private int count;
    private String betType;
    private int oddID;
    private int state;
    /**
     * Construtores para objetos da classe Filial.
     */
    public Odd(){
        this.odd = 0.0;
        this.count = 0;
        this.betType = " ";
        this.oddID = 0;
        state = 0;
    }

    public Odd(int oddID, int count, String betType, double odd,int state) {
        this.odd = odd;
        this.count = count;
        this.betType = betType;
        this.oddID = oddID;
        this.state = state;
    }

    public Odd(Odd u){
        this.odd = u.getOdd();
        this.count = u.getCount();
        this.betType = u.getBetType();
        this.oddID = u.getOddID();
        state = u.getState();
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public double getOdd() {
        return odd;
    }

    public String getBetType() {
        return betType;
    }

    public int getCount() {
        return count;
    }

    public int getOddID() {
        return oddID;
    }

    public void setBetType(String betType) {
        this.betType = betType;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void setOdd(double odd) {
        this.odd = odd;
    }

    public void setOddID(int oddID) {
        this.oddID = oddID;
    }

    public Odd clone(){
        return new Odd(this);
    }

    public boolean equals(Object obj){
        if (this == obj) return true;
        if (obj == null || this.getClass() != obj.getClass()) return false;
        Odd u = (Odd) obj;
        return this.betType.equals(u.getBetType()) &&
                this.odd == u.getOdd() &&
                this.count == u.getCount() &&
                this.oddID == u.getOddID();
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        String s = "";
        if (state == 0)
            s = "";
        else if(state == 2)
            s = "Ganho";
        else
            s = "Perdido";

        sb.append(String.format("%d %s %.2f (%d) %s",this.oddID,betType,odd,count,s));
        return sb.toString();
    }

}