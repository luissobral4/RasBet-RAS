import DAO.MarketDAO;
import DAO.UserDAO;
import Models.IRasBet;

import java.util.List;
import java.util.Map;

public class RasBet implements IRasBet {
    MarketDAO marketDAO;
    UserDAO userDAO;

    RasBet(){
        marketDAO = new MarketDAO();
        userDAO = new UserDAO();
    }

    public void addUser(String password,String name,String email){
        userDAO.addUser(password, name, email);
    }

    public void addMarket(String sport){
        marketDAO.addMarket(sport);
    }

    public void setMarketEvent(int marketid,String description){
        marketDAO.setMarketEvent(marketid,description);
    }

    public void setEventOdd(int eventid,String betType,double odd){
        marketDAO.setEventOdd(eventid,betType, odd);
    }

    @Override
    public String eventsList() {
        return marketDAO.eventsList();
    }

    @Override
    public boolean login(String name, String password) {
        return userDAO.login(name, password);
    }

    @Override
    public boolean logout(String name) {
        return userDAO.logout(name);
    }

    @Override
    public boolean deposit(String name, int amount) {
        return userDAO.deposit(name, amount);
    }

    @Override
    public boolean withdraw(String name, double amount) {
        return userDAO.withdraw(name, amount);
    }

    @Override
    public boolean register(String password, String name, String email) {
        return userDAO.register(password, name, email);
    }

    @Override
    public boolean editPorfile(String password, String name, String email) {
        return userDAO.editPorfile(password, name, email);
    }

    @Override
    public boolean resetpassword(String email, String password) {
        return userDAO.resetpassword(email, password);
    }

    @Override
    public String betRecord(int userid) {
        return userDAO.betRecord(userid);
    }

    @Override
    public int getID(String name) {
        return userDAO.getID(name);
    }

    public int apostar(String name,double value, List<Integer> odds){
        return userDAO.setUserBet(name,value,odds);
    }

    @Override
    public Double getBalance(String name) {
        return userDAO.getBalance(name);
    }

    @Override
    public boolean setCoin(String name, String coin) {
        return userDAO.setCoin(name,coin);
    }

    @Override
    public String getHistorico(String name) {
        return userDAO.getHistorico(name);
    }

    @Override
    public boolean fecharEvento(int eventid, int oddVencedora, String resultado) {
        Map<Integer,List<Integer>> l = marketDAO.fecharEvento(eventid, oddVencedora, resultado);
        System.out.println(l);
        return userDAO.fecharBet(l);

    }

}
