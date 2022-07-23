package DAO;

import DAO.DAOconfig;
import Models.Bet;
import Models.Event;
import Models.Odd;
import Models.User;

import javax.print.DocFlavor;
import java.sql.*;
import java.util.*;

public class OddDAO {
    public static void set(Odd o) {
        try (Connection conn =
                     DriverManager.getConnection(DAOconfig.URL+DAOconfig.CREDENTIALS);
             Statement stm = conn.createStatement()) {

            stm.executeUpdate("INSERT INTO Odd VALUES ("+o.getOddID()+","+o.getCount()+",'"+o.getBetType()+"',"+
                    o.getOdd()+","+o.getState()+")" +
                    "ON DUPLICATE KEY UPDATE Count=Values(Count), odd=Values(odd),state=Values(state)");

        } catch (SQLException e) {
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
    }

    public Odd get(int oddID) {
        Odd odd = null;
        try (Connection conn =
                     DriverManager.getConnection(DAOconfig.URL+DAOconfig.CREDENTIALS);
             Statement stm = conn.createStatement();
             ResultSet rs = stm.executeQuery("SELECT * FROM Odd WHERE idOdd="+oddID+" ")) {
            if (rs.next()) {

                odd = new Odd(rs.getInt("idOdd"),
                        rs.getInt("count"),
                        rs.getString("betType"),
                        rs.getDouble("odd"),
                        rs.getInt("state"));
            } else {
                odd = null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        return odd;
    }

    public Odd getType(String type) {
        Odd odd = null;
        try (Connection conn =
                     DriverManager.getConnection(DAOconfig.URL+DAOconfig.CREDENTIALS);
             Statement stm = conn.createStatement();
             ResultSet rs = stm.executeQuery("SELECT * FROM Odd WHERE betType='"+type+"' ")) {
            if (rs.next()) {

                odd = new Odd(rs.getInt("idOdd"),
                        rs.getInt("count"),
                        rs.getString("betType"),
                        rs.getDouble("odd"),
                        rs.getInt("state"));
            } else {
                odd = null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        return odd;
    }

    public int addOdd(String betType,double odd){
        if(getType(betType) != null)
            return -1;
        try (Connection conn =
                     DriverManager.getConnection(DAOconfig.URL+DAOconfig.CREDENTIALS);
             Statement stm = conn.createStatement();
             ResultSet rs = stm.executeQuery("SELECT count(*) FROM Odd")) {

            rs.next();
            int size = rs.getInt(1);

            Odd o = new Odd(size + 1,0,betType,odd,0);
            set(o);
            return size + 1;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }return -1;
    }

    public int getEventId(int oddID,double value){

            int idevent = getOddEventId(oddID);
            Odd o = get(oddID);
            o.setCount(o.getCount()+(int)value);
            set(o);

            return idevent;
    }

    public int getOddEventId(int oddID){
        try (Connection conn =
                     DriverManager.getConnection(DAOconfig.URL+DAOconfig.CREDENTIALS);
             Statement stm = conn.createStatement();
             ResultSet rs = stm.executeQuery("SELECT idEvent FROM OddEventRelation WHERE idOdd="+oddID+" ")) {

            rs.next();
            int idevent = rs.getInt(1);
            return idevent;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }return -1;
    }

    public Map<Integer,List<Integer>> updateBets(int oddID,int eventid){
        //int eventID = getOddEventId(oddID);
        List<Integer> oddids = new ArrayList<>();
        Set<Integer> bets = new TreeSet<>();

        try (Connection conn =
                     DriverManager.getConnection(DAOconfig.URL+DAOconfig.CREDENTIALS);
             Statement stm = conn.createStatement();
             ResultSet rs = stm.executeQuery("SELECT idOdd FROM OddEventRelation WHERE idEvent="+eventid+"")) {

            while (rs.next()) {
                oddids.add((rs.getInt("idOdd")));
            }

            Odd odd = null;
            for (int oddid:oddids){
                odd = get(oddid);
                odd.setState(1);
                bets.addAll(betsOdd(oddid));
                set(odd);
                System.out.println(oddid);
            }
            odd = get(oddID);
            odd.setState(2);
            set(odd);

        } catch (SQLException e) {
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }

        return betsGanhas((Set<Integer>) bets);
    }

    List<Integer> betsOdd(int oddid) {
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

    public List<Integer> oddsBet(int betid) {
        List<Integer> oddids = new ArrayList<>();
        try (Connection conn =
                     DriverManager.getConnection(DAOconfig.URL+ DAOconfig.CREDENTIALS);
             Statement stm = conn.createStatement();
             ResultSet rs = stm.executeQuery("SELECT idOdd FROM BetOddRelation WHERE idBet="+betid+" ")) {
            while (rs.next()) {
                oddids.add((rs.getInt("idOdd")));
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        return oddids;
    }

    Map<Integer,List<Integer>> betsGanhas(Set<Integer> bet){
        Map<Integer,List<Integer>> bets = new HashMap();
        bets.put(0,new ArrayList<>());
        bets.put(1,new ArrayList<>());
        boolean ganha = false,fechada;
        for (int betId:bet) {
            List<Integer> odds = oddsBet(betId);
            Odd o = null;
            ganha = true;
            fechada = false;
            for (int oddid:odds){
                fechada = true;
                o = get(oddid);
                if(o.getState() == 0)
                    fechada = false;
                if(o.getState() == 1)
                    ganha = false;
            }
            if(fechada == true) {
                if (ganha) {
                    bets.get(0).add(betId);
                } else
                    bets.get(1).add(betId);
            }
        }
        return bets;
    }



    public  double getBetOdd(List<Integer> listOdds){
        double betOdd = 0;
        Odd odd = null;
        for(int oddSimple:listOdds){
            odd = get(oddSimple);
            if(odd == null)
                return -1;
            if (betOdd == 0)
                betOdd = odd.getOdd();
            else
                betOdd *= odd.getOdd();
        }
        return betOdd;
    }

    public String eventsOddsList(int event) {
        List<String> odds = new ArrayList();
        try (Connection conn =
                     DriverManager.getConnection(DAOconfig.URL+DAOconfig.CREDENTIALS);
             Statement stm = conn.createStatement();
             ResultSet rs = stm.executeQuery("SELECT idOdd FROM OddEventRelation WHERE idEvent="+event+"")) {

            List<Integer> oddids = new ArrayList<>();
            while (rs.next()) {
                oddids.add((rs.getInt("idOdd")));
            }

            Odd odd = null;
            for (int oddid:oddids){
                odd = get(oddid);
                odds.add(odd.toString());
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        String s = "+------------- ODDS -------------+\n";
        for(String l: odds){
            s += String.format("| %30s |\n",l);// "| " + l + "\n";
        }
        s += "+------------- ODDS -------------+\n";
        return s;
    }

    public boolean updateOdds(int event) {
        try (Connection conn =
                     DriverManager.getConnection(DAOconfig.URL+DAOconfig.CREDENTIALS);
             Statement stm = conn.createStatement();
             ResultSet rs = stm.executeQuery("SELECT idOdd FROM OddEventRelation WHERE idEvent="+event+"")) {

            List<Integer> oddids = new ArrayList<>();
            while (rs.next()) {
                oddids.add((rs.getInt("idOdd")));
            }

            Odd odd = null;
            int amount = 0;
            double newOdd = 0;
            for (int oddid:oddids){
                odd = get(oddid);
                if(odd.getState() != 0)
                    return false;
                amount += odd.getCount();
            }
            for (int oddid:oddids){
                odd = get(oddid);
                newOdd = 100 / ((((double) odd.getCount() + 0.1)/ (double) amount) * 100);
                if(newOdd >100)
                    newOdd = 100;
                if(newOdd < 1.01)
                    newOdd = 1.01;
                odd.setOdd(newOdd);
                set(odd);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        return true;
    }

    public String betsOddsList(int idBet) {
        List<String> odds = new ArrayList();
        try (Connection conn =
                     DriverManager.getConnection(DAOconfig.URL+DAOconfig.CREDENTIALS);
             Statement stm = conn.createStatement();
             ResultSet rs = stm.executeQuery("SELECT idOdd FROM BetOddRelation WHERE idBet="+idBet+"")) {

            List<Integer> oddids = new ArrayList<>();
            while (rs.next()) {
                oddids.add((rs.getInt("idOdd")));
            }

            Odd odd = null;
            for (int oddid:oddids){
                odd = get(oddid);
                odds.add(odd.toString());
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        String s = "+------------- ODDS -------------+\n";
        for(String l: odds){
            s += String.format("| %30s |\n",l);// "| " + l + "\n";
        }
        s += "+------------- ODDS -------------+\n";
        return s;
    }

}