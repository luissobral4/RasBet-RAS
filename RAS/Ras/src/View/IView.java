package View;

import java.util.List;

public interface IView {

    void printLoginSucesso(boolean ingles);

    void printRegistoSucesso(boolean ingles);

    void printLogoutSucesso(boolean ingles);

    void printPedirUsername(boolean ingles);

    void printPedirPassword(boolean ingles);

    void printPedirEmail(boolean ingles);

    void printMenuLogin(boolean ingles);

    void printMessage(String m);

    void printPedirOpção(boolean ingles);

    void printErroComandoInvalido(boolean ingles);

    void printErroDadosInvalidos(boolean ingles);

    void printResetPassword(boolean ingles);

    void printMenuAdmin(boolean i);

    void printMainMenuLogOut(String name,double balance,String coin, boolean ingles);

    void printMenuDefinicoes(String name,double balance,String coin, boolean ingles);

    void printEditarSucesso(boolean ingles);

    void printPedirDeposito(boolean ingles);

    void printPedirLevantamento(boolean ingles);

    void printErroMontanteInvalido(boolean ingles);

    void printDepositoSucesso(boolean ingles);

    void printLevantamentoSucesso(boolean ingles);

    void printPedirNovaPassword(boolean ingles);

    void printPedirOdd(boolean ingles);

    void printPedirOddVencedora(boolean ingles);

    void printNovaOdd(boolean ingles);

    void printPedirMontante(boolean ingles);

    void printErroOddInvalida(boolean ingles);

    void printApostaSucesso(boolean ingles);

    void printEscolherMoeda(boolean ingles);

    void printEscolherEvento(boolean ingles);

    void printHistorico(boolean ingles,String hist);

    void printPedirResultado(boolean i);
}
