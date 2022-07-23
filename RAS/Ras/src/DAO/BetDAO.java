package DAO;

import Models.Bet;
import Models.User;

import java.sql.*;
import java.util.*;

/**
 * Classe que representa o acesso aos dados do Gestor
 */

public class BetDAO {
    OddDAO oddDAO = new OddDAO();

    public Bet get(int betID) {
        Bet p = null;
        try (Connection conn =
                     DriverManager.getConnection(DAOconfig.URL+ DAOconfig.CREDENTIALS);
             Statement stm = conn.createStatement();
             ResultSet rs = stm.executeQuery("SELECT * FROM Bet WHERE idBet="+betID+" ")) {
            if (rs.next()) {
                p = new Bet(rs.getInt("idBet"),
                        rs.getDouble("Odd"),
                        rs.getInt("Status"),
                        rs.getDouble("Value"));
            } else {
                p = null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        return p;
    }

    public void set(Bet p) {
        Bet res = null;
        try (Connection conn =
                     DriverManager.getConnection(DAOconfig.URL+ DAOconfig.CREDENTIALS);
             Statement stm = conn.createStatement()) {
            stm.executeUpdate("INSERT INTO Bet VALUES ("+p.getBetID()+",'"+p.getOdd()+"',"+
                    ""+p.getStatus()+","+p.getValue()+")" +
                    "ON DUPLICATE KEY UPDATE Status=VALUES(Status)");

        } catch (SQLException e) {
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
    }

    private void setBetOdd(int betid,int oddid) {
        try (Connection conn =
                     DriverManager.getConnection(DAOconfig.URL+ DAOconfig.CREDENTIALS);
             Statement stm = conn.createStatement()) {
            stm.executeUpdate("INSERT INTO BetOddRelation VALUES ("+betid+","+oddid+")" +
                    "ON DUPLICATE KEY UPDATE idBet=VALUES(idBet), idOdd=VALUES(idOdd)");

        } catch (SQLException e) {
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
    }

    public int addBet(double value, List<Integer> odds){
        double odd = oddDAO.getBetOdd(odds);
        if (odd < 0)
            return -1;
        try (Connection conn =
                     DriverManager.getConnection(DAOconfig.URL+DAOconfig.CREDENTIALS);
             Statement stm = conn.createStatement();
             ResultSet rs = stm.executeQuery("SELECT count(*) FROM Bet")) {

            rs.next();
            int size = rs.getInt(1);


            Bet u = new Bet(size + 1,odd,0,value);
            set(u);
            int eventID;

            for (int oddid:odds){
                setBetOdd(size + 1,oddid);
                eventID = oddDAO.getEventId(oddid,value);
                if(eventID != -1)
                    oddDAO.updateOdds(eventID);
            }
            return size + 1;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return 0;
    }

    public String betRecord(int userid) {
        List<String> bets = new ArrayList();
        try (Connection conn =
                     DriverManager.getConnection(DAOconfig.URL+ DAOconfig.CREDENTIALS);
             Statement stm = conn.createStatement();
             ResultSet rs = stm.executeQuery("SELECT idBet FROM UserBetRelation WHERE idUser="+userid+" ")) {
            List<Integer> betids = new ArrayList<>();
            while (rs.next()) {
                betids.add((rs.getInt("idBet")));
            }
            Bet bet = null;
            for (int betid:betids){
                bet = get(betid);
                bets.add(bet.toString());
                bets.add(oddDAO.betsOddsList(bet.getBetID())+"+-------------  BET  ------------+\n\n");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        String s = "\n";
        for(String l: bets){
            s += l;
        }
        return s;
    }

    public List<Integer> betsOdd(int oddid) {
        List<Integer> betids = new ArrayList<>();
        try (Connection conn =
                     DriverManager.getConnection(DAOconfig.URL+ DAOconfig.CREDENTIALS);
             Statement stm = conn.createStatement();
             ResultSet rs = stm.executeQuery("SELECT idBet FROM BetOddRelation WHERE idOdd="+oddid+" ")) {
            while (rs.next()) {
                betids.add((rs.getInt("idBet")));
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        return betids;
    }

    public double fecharBet(int betid){
        Bet b = get(betid);
        b.setStatus(1);
        set(b);
        double odd = b.getOdd();

        return odd * b.getValue();
    }

    public double fecharBet2(int betid){
        Bet b = get(betid);
        b.setStatus(2);
        set(b);
        return 0;
    }

}