import Models.IRasBet;
import View.IView;
import View.View;

public class Main {
    public static void main(String[] args) {
        //RasBet rasBet = new RasBet();
        IRasBet iRasBet = new RasBet();
        IView iView = new View();

        iRasBet.register("admin","admin","admin@gmail.com");
        iRasBet.register("pass133","133","133@gmail.com");
/*
        iRasBet.addMarket("Futebol");
        iRasBet.addMarket("Tenis de mesa");

        iRasBet.setMarketEvent(1,"Sporting vs Dortmund");
        iRasBet.setMarketEvent(1,"Sporting vs Besiktas");
        iRasBet.setMarketEvent(1,"Sporting vs Benfica");

        iRasBet.setMarketEvent(2,"Luís Sobral vs Diogo Carvalho");
        iRasBet.setMarketEvent(2,"Luís vs João Monteiro");

        iRasBet.setEventOdd(1,"Sporting",1.45);
        iRasBet.setEventOdd(1,"Dortmund",3);
        iRasBet.setEventOdd(5,"Luís Sobral",1.30);
        iRasBet.setEventOdd(5,"Diogo Carvalho",4.44);

        ArrayList<Integer> odds = new ArrayList<>();
        odds.add(1);odds.add(3);

        iRasBet.setUserBet(1,100,odds);
*/
        //iRasBet.login("4","pass4");

        Controler c = new Controler(iRasBet,iView);
        c.controler();
    }
}
