package Models;

public class Event {
    private int eventID;
    private String description;
    private int status;
    private int betCount;
    private String result;
    private int vencedor;
    private String hora;
    private String data;


    /**
     * Construtores para objetos da classe Filial.
     */
    public Event(){
        this.description = " ";
        this.betCount = 0;
        this.status = 0;
        this.eventID = 0;
        this.result = " ";
        this.vencedor = 0;
        this.hora = " ";
        this.data = " ";
    }

    public Event(int eventID,String description, int status, int betCount, String results, int vencedor, String hora, String data){
        this.description = description;
        this.betCount = betCount;
        this.status = status;
        this.eventID = eventID;
        this.result = results;
        this.vencedor = vencedor;
        this.hora = hora;
        this.data = data;
    }

    public Event(Event u){
        this.description = u.getDescription();
        this.betCount = u.getBetCount();
        this.status = u.getStatus();
        this.eventID = u.getEventID();
        this.result = u.getResults();
        this.vencedor = u.getVencedor();
        this.hora = u.getHora();
        this.data = u.getData();
    }

    public String getDescription() {
        return description;
    }

    public int getBetCount() {
        return betCount;
    }

    public int getEventID() {
        return eventID;
    }

    public String getResults() {
        return result;
    }

    public int getStatus() {
        return status;
    }

    public String getData() {
        return data;
    }

    public String getHora() {
        return hora;
    }

    public int getVencedor() {
        return vencedor;
    }

    public void setBetCount(int betCount) {
        this.betCount = betCount;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setEventID(int eventID) {
        this.eventID = eventID;
    }

    public void setResults(String results) {
        this.result = results;
    }
    
    public void setStatus(int status) {
        this.status = status;
    }

    public void setData(String data) {
        this.data = data;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public void setVencedor(int vencedor) {
        this.vencedor = vencedor;
    }

    public Event clone(){
        return new Event(this);
    }

    public boolean equals(Object obj){
        if (this == obj) return true;
        if (obj == null || this.getClass() != obj.getClass()) return false;
        Event u = (Event) obj;
        return this.result.equals(u.getResults()) &&
               this.description.equals(u.getDescription()) &&
               this.status == u.getStatus() &&
               this.eventID == u.getEventID() &&
               this.betCount == u.getBetCount();
    }

      public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("+----------- EVENT %3d ----------+\n",eventID));//"------------- EVENT "+eventID+" -------------\n");
        sb.append(String.format("| %30s |\n",this.description));
        sb.append(String.format("| %15s %14s |\n",data,hora));
        String state = "";
        if(status == 1){
            state = "Fechado";
            sb.append(String.format("| Resultado %20s |\n",this.result));
        } else if (status == 0)
            state = "Aberto";
        else state = "Sustenso";

        sb.append(String.format("| Estado do evento %13s |\n",state));

        return sb.toString();
      }

}
