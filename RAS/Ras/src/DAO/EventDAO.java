package DAO;

import Models.Bet;
import Models.Event;
import Models.Market;
import Models.Odd;

import java.sql.*;
import java.util.*;

/**
 * Classe que representa o acesso aos dados do Gestor
 */

public class EventDAO {
    OddDAO oddDAO = new OddDAO();

    public Event get(int eventOdd) {
        Event p = null;
        try (Connection conn =
                     DriverManager.getConnection(DAOconfig.URL+ DAOconfig.CREDENTIALS);
             Statement stm = conn.createStatement();
             ResultSet rs = stm.executeQuery("SELECT * FROM Event WHERE idEvent="+eventOdd+" ")) {
            if (rs.next()) {
                p = new Event(rs.getInt("idEvent"),
                        rs.getString("description"),
                        rs.getInt("status"),
                        rs.getInt("betCount"),
                        rs.getString("result"),
                        rs.getInt("vencedor"),
                        rs.getString("hora"),
                        rs.getString("dia"));
            } else {
                p = null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        return p;
    }

    public Event getDescription(String description) {
        Event p = null;
        try (Connection conn =
                     DriverManager.getConnection(DAOconfig.URL+ DAOconfig.CREDENTIALS);
             Statement stm = conn.createStatement();
             ResultSet rs = stm.executeQuery("SELECT * FROM Event WHERE description='"+description+"' ")) {
            if (rs.next()) {
                p = new Event(rs.getInt("idEvent"),
                        rs.getString("description"),
                        rs.getInt("status"),
                        rs.getInt("betCount"),
                        rs.getString("result"),
                        rs.getInt("vencedor"),
                        rs.getString("hora"),
                        rs.getString("dia"));
            } else {
                p = null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        return p;
    }

    public void set(Event p) {
        try (Connection conn =
                     DriverManager.getConnection(DAOconfig.URL+ DAOconfig.CREDENTIALS);
             Statement stm = conn.createStatement()) {
            stm.executeUpdate("INSERT INTO Event VALUES ("+p.getEventID()+",'"+p.getDescription()+"'," +
                    ""+p.getStatus()+","+p.getBetCount()+",'"+p.getResults()+"',"+p.getVencedor()+",'"+p.getHora()+"','"+p.getData()+"')" +
                    "ON DUPLICATE KEY UPDATE Status=VALUES(Status), BetCount=VALUES(BetCount)," +
                    " Result=VALUES(Result)");

        } catch (SQLException e) {
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
    }

    public void setOddEvent(int eventid,String betType,double odd) {
        int oddid = oddDAO.addOdd(betType, odd);
        if (oddid == -1)
            return;
        try (Connection conn =
                     DriverManager.getConnection(DAOconfig.URL+ DAOconfig.CREDENTIALS);
             Statement stm = conn.createStatement()) {
            stm.executeUpdate("INSERT INTO OddEventRelation VALUES ("+oddid+","+eventid+")" +
                    "ON DUPLICATE KEY UPDATE idEvent=VALUES(idEvent), idOdd=VALUES(idOdd)");

        } catch (SQLException e) {
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
    }


    public int addEvent(String description){
        if(getDescription(description) != null)
            return -1;
        try (Connection conn =
                     DriverManager.getConnection(DAOconfig.URL+DAOconfig.CREDENTIALS);
             Statement stm = conn.createStatement();
             ResultSet rs = stm.executeQuery("SELECT count(*) FROM Event")) {

            rs.next();
            int size = rs.getInt(1);

            Event m = new Event(size + 1,description,0,0,"", 0, "", "");
            set(m);
            return size + 1;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return -1;
    }

    public String eventsList() {
        List<String> events = new ArrayList();
        try (Connection conn =
                     DriverManager.getConnection(DAOconfig.URL+DAOconfig.CREDENTIALS);
             Statement stm = conn.createStatement();
             ResultSet rs = stm.executeQuery("SELECT count(*) FROM Event")) {

            rs.next();
            int size = rs.getInt(1);

            Event event = null;
            for (int i = 1;i < size + 1;i++){
                event = get(i);
                if(event.getStatus() == 0) {
                    events.add(event.toString());
                    events.add(oddDAO.eventsOddsList(event.getEventID()) + "\n");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        String s = "\n";
        for(String l: events){
            s += l;
        }
        return s;
    }

    public Map<Integer,List<Integer>> fecharEvento(int eventid,int oddVencedora,String resultado) {
        Event e = get(eventid);
        if(e.getStatus() == 1)
            return new HashMap<>();

        e.setStatus(1);
        e.setVencedor(oddVencedora);
        e.setResults(resultado);
        set(e);
        return oddDAO.updateBets(oddVencedora,eventid);
    }
}