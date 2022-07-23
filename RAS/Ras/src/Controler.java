import Models.IRasBet;
import View.IView;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Controler {
    IRasBet iRasBet;
    IView iView;
    Input input;
    String username;
    String coin;
    boolean ingles;
    double balance;

    Controler(IRasBet iRasBet, IView iView){
        this.iRasBet = iRasBet;
        this.iView = iView;
        input = new Input();
        coin = "EUR";
        ingles = false;
    }

    private boolean login() {
        Scanner s = new Scanner(System.in);
        String user, pass;

        iView.printPedirUsername(ingles);
        user = s.nextLine();
        iView.printPedirPassword(ingles);
        pass = s.nextLine();

        boolean login = iRasBet.login(user,pass);

        if(login)
            username = user;

        return login;
    }

    private boolean registarUtilizador(){
        Scanner s = new Scanner(System.in);
        String user, pass,email;

        iView.printPedirUsername(ingles);
        user = s.nextLine();
        iView.printPedirPassword(ingles);
        pass = s.nextLine();
        iView.printPedirEmail(ingles);
        email = s.nextLine();

        return iRasBet.register(pass,user,email);
    }

    private boolean recuperarPassword(){
        Scanner s = new Scanner(System.in);
        String pass,email;

        iView.printPedirEmail(ingles);
        email = s.nextLine();
        iView.printPedirNovaPassword(ingles);
        pass = s.nextLine();

        return iRasBet.resetpassword(email,pass);
    }

    void controler() {
        boolean bool = false, r = true;
        int command;

        while (r) {
            iView.printMenuLogin(ingles);
            command = input.lerInt(iView, 0, 3,ingles);

            switch (command) {
                case 1:
                    bool = login();
                    if (bool) {
                        if(username.equals("admin")) {
                            iView.printLoginSucesso(ingles);
                            controler4();
                        }
                        else {
                            iView.printLoginSucesso(ingles);
                            controler2();
                        }
                    }
                    else
                        iView.printErroDadosInvalidos(ingles);
                    break;
                case 2:
                    bool = registarUtilizador();
                    if (bool)
                        iView.printRegistoSucesso(ingles);
                    else
                        iView.printErroDadosInvalidos(ingles);
                    break;
                case 3:
                    bool = recuperarPassword();
                    if (bool)
                        iView.printResetPassword(ingles);
                    else
                        iView.printErroDadosInvalidos(ingles);
                    break;
                case 0:
                    r = false;
                    break;
                default:
                    iView.printErroComandoInvalido(ingles);
                    break;
            }
        }
    }



    void controler2() {
        boolean bool = false, r = true;
        int command;
        coin = "EUR";
        ingles = false;
        balance = iRasBet.getBalance(username);


        while (r) {
            iView.printMainMenuLogOut(username,balance,coin,ingles);
            command = (int) input.lerInt(iView, 0, 8,ingles);

            switch (command) {
                case 1:
                    bool = editarPerfil();
                    if (bool)
                        iView.printEditarSucesso(ingles);
                    else
                        iView.printErroDadosInvalidos(ingles);
                    break;
                case 2:
                    iView.printMessage(iRasBet.eventsList());
                    break;
                case 3:
                    int aposta = efetuarAposta();
                    if(aposta == 1)
                        iView.printErroMontanteInvalido(ingles);
                    else if (aposta == 2)
                        iView.printErroOddInvalida(ingles);
                    else {
                        iView.printApostaSucesso(ingles);
                        balance = iRasBet.getBalance(username);
                    }
                    break;
                case 4:
                    bool = depositar();
                    if (bool){
                        iView.printDepositoSucesso(ingles);
                        balance = iRasBet.getBalance(username);
                    }
                    else
                        iView.printErroMontanteInvalido(ingles);

                    break;
                case 5:
                    bool = levantamento();
                    if (bool) {
                        iView.printLevantamentoSucesso(ingles);
                        balance = iRasBet.getBalance(username);
                    }
                    else
                        iView.printErroMontanteInvalido(ingles);

                    break;
                case 6:
                    int id = iRasBet.getID(username);
                    iView.printMessage(iRasBet.betRecord(id));
                    break;
                case 7:
                    iView.printHistorico(ingles,iRasBet.getHistorico(username));
                    break;
                case 8:
                    controler3();
                    break;
                case 0:
                    iRasBet.logout(username);
                    iView.printLogoutSucesso(ingles);
                    r = false;
                    break;
                default:
                    iView.printErroComandoInvalido(ingles);
                    break;
            }
        }

    }

    private boolean depositar(){
        int amount;

        iView.printPedirDeposito(ingles);
        amount = (int) input.lerDouble(iView);

        return iRasBet.deposit(username,amount);
    }

    private boolean levantamento(){
        int amount;

        iView.printPedirLevantamento(ingles);
        amount = (int) input.lerDouble(iView);

        return iRasBet.withdraw(username,amount);
    }

    private boolean editarPerfil(){
        Scanner s = new Scanner(System.in);
        String user, pass,email;

        iView.printPedirUsername(ingles);
        user = s.nextLine();
        iView.printPedirPassword(ingles);
        pass = s.nextLine();
        iView.printPedirEmail(ingles);
        email = s.nextLine();

        boolean res = iRasBet.editPorfile(pass,user,email);
        if(res)
            username = user;

        return res;
    }

    private int efetuarAposta(){
        Scanner s = new Scanner(System.in);
        boolean bool = true;
        List<Integer> odds = new ArrayList<>();

        while (bool) {
            iView.printMessage(iRasBet.eventsList());
            iView.printPedirOdd(ingles);
            odds.add((int)input.lerDouble(iView));
            bool = input.lerSN(iView,ingles);
        }

        iView.printPedirMontante(ingles);
        double amount = input.lerDouble(iView);

        return iRasBet.apostar(username,amount,odds);
    }

    String conversor(){
        int opcao;
        String moeda;
        iView.printEscolherMoeda(ingles);
        opcao = (int) input.lerInt(iView, 1, 4,ingles);

        if(opcao == 1)
            moeda = "EUR";
        else if(opcao == 2)
            moeda = "USD";
        else if(opcao == 3)
            moeda = "GPB";
        else
            moeda = "ADA";

        return moeda;
    }

    void controler3() {
        boolean bool = false, r = true;
        int command;


        while (r) {
            iView.printMenuDefinicoes(username,balance,coin,ingles);
            command = (int) input.lerInt(iView, 0, 2,ingles);

            switch (command) {
                case 1:
                    ingles = !ingles;
                    break;
                case 2:
                    coin = conversor();
                    iRasBet.setCoin(username,coin);
                    balance = iRasBet.getBalance(username);

                    break;
                case 0:
                    iRasBet.logout(username);
                    iView.printLogoutSucesso(ingles);
                    r = false;
                    break;
                default:
                    iView.printErroComandoInvalido(ingles);
                    break;
            }
        }
    }

    boolean fecharEvento(){
        Scanner s = new Scanner(System.in);
        String evento, resutado;

        iView.printMessage(iRasBet.eventsList());
        iView.printEscolherEvento(ingles);
        int eventoid =(int) input.lerDouble(iView);


        iView.printMessage(iRasBet.eventsList());
        iView.printPedirOddVencedora(ingles);
        int vencedor = (int)input.lerDouble(iView);

        iView.printPedirResultado(ingles);
        resutado = s.nextLine();

        boolean res = iRasBet.fecharEvento(eventoid,vencedor,resutado);

        return res;

    }

    void controler4() {
        boolean bool = false, r = true;
        int command;


        while (r) {
            iView.printMenuAdmin(ingles);
            command = (int) input.lerInt(iView, 0, 1,ingles);

            switch (command) {
                case 1:
                    fecharEvento();
                    break;
                case 0:
                    iRasBet.logout(username);
                    iView.printLogoutSucesso(ingles);
                    r = false;
                    break;
                default:
                    iView.printErroComandoInvalido(ingles);
                    break;
            }
        }
    }
}
