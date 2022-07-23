package DAO;
import Models.Bet;
import Models.Event;
import Models.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class UserDAO {
    BetDAO betDAO = new BetDAO();
    public static void set(User u) {
        try (Connection conn =
                     DriverManager.getConnection(DAOconfig.URL+DAOconfig.CREDENTIALS);
             Statement stm = conn.createStatement()) {

            int status = 0;
            if(u.isStatus())
                status = 1;

            stm.executeUpdate("INSERT INTO User VALUES ("+u.getUserID()+",'"+u.getPassword()+"',"+status+","+
                    ""+u.getBalance()+",'"+u.getName()+"','"+u.getEmail()+"','"+u.getHistorico()+"','"+u.getCoin()+"')" +
                    "ON DUPLICATE KEY UPDATE Password=Values(Password), Status=Values(Status), Balance=Values(Balance), " +
                    "Name=Values(Name),Email=Values(Email),Historico=Values(Historico),Coin=VALUES(Coin)");

        } catch (SQLException e) {
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
    }

    public User get(String name) {
        User user = null;
        try (Connection conn =
                     DriverManager.getConnection(DAOconfig.URL+DAOconfig.CREDENTIALS);
             Statement stm = conn.createStatement();
             ResultSet rs = stm.executeQuery("SELECT * FROM User WHERE name='"+name+"' ")) {
            if (rs.next()) {
                int on = rs.getInt("Status");
                boolean online = false;
                if(on == 1)
                    online = true;

                user = new User(rs.getInt("idUser"),
                        rs.getString("password"),
                        online,
                        rs.getDouble("balance"),
                        rs.getString("email"),
                        rs.getString("name"),
                        rs.getString("historico"),
                        rs.getString("coin"));
            } else {
                user = null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }

        return user;
    }

    public User getuser(int id) {
        User user = null;
        try (Connection conn =
                     DriverManager.getConnection(DAOconfig.URL+DAOconfig.CREDENTIALS);
             Statement stm = conn.createStatement();
             ResultSet rs = stm.executeQuery("SELECT * FROM User WHERE idUser="+id+" ")) {
            if (rs.next()) {
                int on = rs.getInt("Status");
                boolean online = false;
                if(on == 1)
                    online = true;

                user = new User(rs.getInt("idUser"),
                        rs.getString("password"),
                        online,
                        rs.getDouble("balance"),
                        rs.getString("email"),
                        rs.getString("name"),
                        rs.getString("historico"),
                        rs.getString("coin"));
            } else {
                user = null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }

        return user;
    }

    public User getEmail(String email) {
        User user = null;
        try (Connection conn =
                     DriverManager.getConnection(DAOconfig.URL+DAOconfig.CREDENTIALS);
             Statement stm = conn.createStatement();
             ResultSet rs = stm.executeQuery("SELECT * FROM User WHERE email='"+email+"' ")) {
            if (rs.next()) {
                int on = rs.getInt("Status");
                boolean online = false;
                if(on == 1)
                    online = true;

                user = new User(rs.getInt("idUser"),
                        rs.getString("password"),
                        online,
                        rs.getDouble("balance"),
                        rs.getString("email"),
                        rs.getString("name"),
                        rs.getString("historico"),
                        rs.getString("coin"));
            } else {
                user = null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        return user;
    }

    public int setUserBet(String name,double value, List<Integer> odds) {
        User u = get(name);
        if(u.getBalance() < value)
            return 1;

        int betid = betDAO.addBet(value, odds);
        if(betid == -1)
            return 2;
        int userid = u.getUserID();

        u.setHistorico(u.getHistorico() + "Bet efetuada           | ID: "+ betid+"\n");
        u.setBalance(u.getBalance() - value);
        set(u);

        try (Connection conn =
                     DriverManager.getConnection(DAOconfig.URL+ DAOconfig.CREDENTIALS);
             Statement stm = conn.createStatement()) {
            stm.executeUpdate("INSERT INTO UserBetRelation VALUES ("+userid+","+betid+")" +
                    "ON DUPLICATE KEY UPDATE idUser=VALUES(idUser), idBet=VALUES(idBet)");

        } catch (SQLException e) {
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        return 0;
    }

    public  void addUser(String password,String name,String email)  {
        try (Connection conn =
                     DriverManager.getConnection(DAOconfig.URL+DAOconfig.CREDENTIALS);
                     Statement stm = conn.createStatement();
                     ResultSet rs = stm.executeQuery("SELECT count(*) FROM User")) {

            rs.next();
            int size = rs.getInt(1);

            User u = new User(size + 1,password,false,0.0,email,name,"","EUR");
            set(u);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public boolean login(String name,String password){
        User user = get(name);

        if(user == null)
            return false;

        if(user.getPassword().equals(password)){
            user.setStatus(true);
            set(user);
            return true;
        }
        return false;
    }

    public boolean logout(String name){
        User user = get(name);

        if(user == null)
            return false;

        user.setStatus(false);
        set(user);
        return true;
    }

    public boolean deposit(String name,int amount){
        User user = get(name);

        if(user == null)
            return false;

        user.setBalance(user.getBalance() + amount);
        user.setHistorico(user.getHistorico() + "Deposito efetuado     | AMOUNT: "+ amount+"\n");
        set(user);
        return true;
    }

    public boolean withdraw(String name,double amount){
        User user = get(name);

        if(user == null || user.getBalance() < amount)
            return false;

        user.setBalance(user.getBalance() - amount);
        user.setHistorico(user.getHistorico() + "Levantamento efetuado | AMOUNT: "+ amount+"\n");
        set(user);
        return true;
    }

    public boolean register(String password,String name,String email) {
        User user = get(name);

        if(user == null){
            addUser(password, name, email);
        } else
            return false;

        return true;
    }

    public boolean editPorfile(String password,String name,String email) {
        User user = get(name);

        if(user != null){
            user.setPassword(password);
            user.setName(name);
            user.setEmail(email);
            set(user);
        } else
            return false;

        return true;
    }

    public boolean resetpassword(String email,String password) {
        User user = getEmail(email);
        if(user != null){
            user.setPassword(password);
            set(user);
        } else
            return false;

        return true;
    }

    public String betRecord(int userid){
        return betDAO.betRecord(userid);
    }

    public int getID(String name) {
        User u = get(name);
        if(u ==  null)
            return -1;
        else
            return u.getUserID();
    }

    public Double getBalance(String name){
        User u = get(name);
        return u.getBalance();
    }

    public Double getCoin(String name){
        User u = get(name);
        return u.getBalance();
    }

    private Double converter(String coin,String coin2,double amount) {
        amount -= amount * 0.05;
        if(coin.equals("EUR")){
            if (coin2.equals("USD"))
                return amount * 1.14;
            else if (coin2.equals("GBP"))
                return amount * 0.83;
            else
                return amount * 0.79;

        } else if (coin.equals("USD")) {
            if (coin2.equals("EUR"))
                return amount * 0.88;
            else if (coin2.equals("GBP"))
                return amount * 0.74;
            else
                return amount * 0.70;

        } else if (coin.equals("GBP")) {
            if (coin2.equals("USD"))
                return amount * 1.36;
            else if (coin2.equals("EUR"))
                return amount * 1.20;
            else
                return amount * 1.26;
        } else if (coin.equals("ADA")){
            if (coin2.equals("USD"))
                return amount * 1.42;
            else if (coin2.equals("EUR"))
                return amount * 1.26;
            else
                return amount * 1.05;
        } else
            return amount;
    }

    public boolean setCoin(String name,String coin){
        User u = get(name);
        System.out.println(name);
        if (u == null)
            return false;
        String coin2 = u.getCoin();
        u.setCoin(coin);
        u.setBalance(converter(coin2,coin,u.getBalance()));
        set(u);
        return true;
    }

    boolean updateHistorico(String name,String hist) {
        User u = get(name);
        if (u == null)
            return false;
        u.setHistorico(u.getHistorico()+hist);
        return true;
    }

    public String getHistorico(String name) {
        User u = get(name);
        if (u == null)
            return "";

        return u.getHistorico();
    }

    public boolean fecharBet(Map<Integer,List<Integer>> l) {
        for (int bet:l.get(0)){
            double amout = betDAO.fecharBet(bet);
            List<Integer> users = userBet(bet);
            for (int id:users){
                User u = getuser(id);
                u.setBalance(u.getBalance() + amout);
                u.setHistorico(u.getHistorico()+ "Aposta ganha           | ID "+bet+"\n");
                set(u);
            }
        }
        for (int bet:l.get(1)){
            betDAO.fecharBet2(bet);
        }
        return true;
    }

    List<Integer> userBet(int bet) {
        List<Integer> users = new ArrayList<>();
        try (Connection conn =
                     DriverManager.getConnection(DAOconfig.URL+ DAOconfig.CREDENTIALS);
             Statement stm = conn.createStatement();
             ResultSet rs = stm.executeQuery("SELECT idUser FROM UserBetRelation WHERE idBet="+bet+" ")) {
            while (rs.next()) {
                users.add((rs.getInt("idUser")));
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        return users;
    }

}
