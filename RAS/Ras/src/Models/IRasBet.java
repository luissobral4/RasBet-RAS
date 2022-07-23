package Models;

import java.util.List;

public interface IRasBet{
    String eventsList();
    boolean login(String name,String password);
    boolean logout(String name);
    boolean deposit(String name,int amount);
    boolean withdraw(String name,double amount);
    boolean register(String password,String name,String email);
    boolean editPorfile(String password,String name,String email);
    boolean resetpassword(String email,String password);
    String betRecord(int userid);
    int getID(String name);
    int apostar(String name,double value, List<Integer> odds);
    Double getBalance(String name);
    boolean setCoin(String name,String coin);
    String getHistorico(String name);
    boolean fecharEvento(int eventid,int oddVencedora,String resultado);
}
