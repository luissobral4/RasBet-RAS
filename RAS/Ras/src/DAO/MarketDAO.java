package DAO;

import DAO.DAOconfig;
import Models.Event;
import Models.Market;

import java.sql.*;
import java.util.List;
import java.util.Map;

/**
 * Classe que representa o acesso aos dados do Gestor
 */

public class MarketDAO {
    EventDAO eventDAO = new EventDAO();

    public Market get(String sport) {
        Market p = null;
        try (Connection conn =
                     DriverManager.getConnection(DAOconfig.URL+DAOconfig.CREDENTIALS);
             Statement stm = conn.createStatement();
             ResultSet rs = stm.executeQuery("SELECT * FROM Market WHERE sport='"+sport+"' ")) {
            if (rs.next()) {
                p = new Market(rs.getInt("idSport"),
                        rs.getString("sport"));
            } else {
                p = null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        return p;
    }


    /**
     * Atualiza o estado de um gestor fornecido
     *
     * @param p gestor a ser atualizado
     */

    public void set(Market p) {
        Market res = null;
        try (Connection conn =
                     DriverManager.getConnection(DAOconfig.URL+DAOconfig.CREDENTIALS);
             Statement stm = conn.createStatement()) {
            stm.executeUpdate("INSERT INTO Market VALUES ("+p.getSportID()+",'"+p.getSport()+"')");

        } catch (SQLException e) {
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
    }

    public void setMarketEvent(int marketid,String description) {
        int eventid = eventDAO.addEvent(description);
        if (eventid == -1)
            return;
        try (Connection conn =
                     DriverManager.getConnection(DAOconfig.URL+ DAOconfig.CREDENTIALS);
             Statement stm = conn.createStatement()) {
            stm.executeUpdate("INSERT INTO EventMarketRelation VALUES ("+eventid+","+marketid+")" +
                    "ON DUPLICATE KEY UPDATE idEvent=VALUES(idEvent)");

        } catch (SQLException e) {
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
    }

    public void setEventOdd(int eventid, String betType,double odd){
        eventDAO.setOddEvent(eventid, betType, odd);
    }

    public  void addMarket(String sport){
        if(get(sport) != null)
            return;
        try (Connection conn =
                     DriverManager.getConnection(DAOconfig.URL+DAOconfig.CREDENTIALS);
             Statement stm = conn.createStatement();
             ResultSet rs = stm.executeQuery("SELECT count(*) FROM Market")) {

            rs.next();
            int size = rs.getInt(1);

            Market m = new Market(size + 1,sport);
            set(m);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public String eventsList() {
        return eventDAO.eventsList();
    }

    public Map<Integer,List<Integer>> fecharEvento(int eventid, int oddVencedora, String resultado){
        return eventDAO.fecharEvento(eventid,oddVencedora,resultado);
    }

}