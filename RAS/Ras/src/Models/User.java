package Models;

public class User {
    private int userID;
    private String password;
    private boolean status;
    private double balance;
    private String name;
    private String email;
    private String historico;
    private String coin;


    /**
     * Construtores para objetos da classe Filial.
     */
    public User(){
        this.name = " ";
        this.password = " ";
        this.status = true;
        this.balance = 0.0;
        this.userID = 0;
        this.email = " ";
        this.historico = " ";
    }

    public User(int userID, String password, boolean status, double balance,  String email,String name, String historico, String coin){
        this.name = name;
        this.password = password;
        this.status = status;
        this.balance = balance;
        this.userID = userID;
        this.email = email;
        this.historico = historico;
        this.coin = coin;
    }

    public User(User u){
        this.name = u.getName();
        this.password = u.getPassword();
        this.status = u.isStatus();
        this.balance = u.getBalance();
        this.userID = u.getUserID();
        this.email = u.getEmail();
        this.historico = u.getHistorico();
        this.coin = u.getCoin();
    }

    public String getCoin() {
        return coin;
    }

    public void setCoin(String coin) {
        this.coin = coin;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public boolean isStatus() {
        return status;
    }

    public double getBalance() {
        return balance;
    }

    public int getUserID() {
        return userID;
    }

    public String getEmail() {
        return email;
    }

    public String getHistorico() {
        return historico;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setHistorico(String historico) {
        this.historico = historico;
    }

    public User clone(){
        return new User(this);
    }

    public boolean equals(Object obj){
        if (this == obj) return true;
        if (obj == null || this.getClass() != obj.getClass()) return false;
        User u = (User) obj;
        return this.name.equals(u.getName()) &&
               this.password.equals(u.getPassword()) &&
               this.status == u.isStatus() &&
               this.balance == u.getBalance() &&
               this.userID == u.getUserID() &&
               this.email.equals(u.getHistorico());
      }

      public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Name: ").append(this.name).append("\n");
        sb.append("Password: ").append(this.password).append("\n");
        sb.append("Status: ").append(this.status).append("\n");
        sb.append("Balance: ").append(this.balance).append("\n");
        sb.append("UserID: ").append(this.userID).append("\n");
        sb.append("Email: ").append(this.email).append("\n");
          sb.append("Coin: ").append(this.coin).append("\n");
        return sb.toString();
      }

}