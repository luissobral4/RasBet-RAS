package View;

public class View implements IView {

    /**
     * Apresenta menu login
     */
    public void printMenuLogin(boolean i) {
        if (i)
            printMenus((new String[]{"Login", "Register","Forgot password"}),"RASBET", i);
        else
            printMenus((new String[]{"Login", "Registar","Recuperar password"}),"RASBET", i);
    }

    public void printMenuAdmin(boolean i) {
        if (i)
            printMenus((new String[]{"Close event"}),"ADMIN", i);
        else
            printMenus((new String[]{"Fechar evento"}),"ADMIN", i);
    }

    public void printMainMenuLogOut(String name,double balance,String coin, boolean i) {
        if(!i)
            printMenus((new String[]{"Editar perfil", "Ver jogos disponiveis","Efetuar aposta","Deposito","Levantamento","Lista de apostas","Historico","Definições"}),name + "      " + (int)balance + coin,i);
        else
            printMenus((new String[]{"Edit profile", "Show available games", "Bet" ,"Deposit","Withdraw","Bet Historic","History","Settings"}),name + "      " + (int)balance + coin,i);

    }

    public void printMenuDefinicoes(String name,double balance,String coin, boolean i) {
        if(!i)
            printMenus((new String[]{"Alterar linguagem", "Alterar moeda"}),name + "      " + (int)balance + coin,i);
        else
            printMenus((new String[]{"Change language", "Change currency"}),name + "      " + (int)balance + coin,i);

    }

    private void printLine(int size) {
        for(int i=0; i<size; i++)
            System.out.print("-");

        System.out.println("");
    }

    public void printMenus(String []menu, String message, boolean in){

        int size, length=message.length();

        for(String linha: menu)
            if(linha.length() + 4 > length)
                length = linha.length() + 4;

        if(length < 20)
            length = 20;

        printLine(length);
        System.out.println(message);
        printLine(length);

        size = menu.length;
        for(int i = 0;i < size;i++)
            System.out.println(i+1+" | "+menu[i]);

        if (!in)
            System.out.println("0 | Sair");
        else
            System.out.println("0 | Exit");

    }

    /**
     * Apresenta login com sucesso
     */
    public void printLoginSucesso(boolean i) {
        if (!i)
            System.out.println("Login efetuado com sucesso");
        else
            System.out.println("Successful Login");

    }

    public void printRegistoSucesso(boolean i) {
        if (!i)
            System.out.println("Registo efetuado com sucesso");
        else
            System.out.println("Successful Register");

    }

    public void printApostaSucesso(boolean i) {
        if (!i)
            System.out.println("Aposta efetuada com sucesso");
        else
            System.out.println("Successful Bet");
    }

    public void printEditarSucesso(boolean i) {
        if (!i)
            System.out.println("Perfil editado com sucesso");
        else
            System.out.println("Successful Profile Edit");

    }

    public void printDepositoSucesso(boolean i) {
        if (!i)
            System.out.println("Montante depositado com sucesso");
        else
            System.out.println("Successful deposit amount");
    }

    public void printLevantamentoSucesso(boolean i) {
        if (!i)
            System.out.println("Montante levantado com sucesso");
        else
            System.out.println("Amount withdraw successfully");
    }

    public void printEscolherEvento(boolean ingles) {
        if (!ingles)
            System.out.println("Escolha um evento:");
        else
            System.out.println("Chose an event:");
    }

    /**
     * Apresenta logout com sucesso
     */
    public void printLogoutSucesso(boolean i) {
        if (!i)
            System.out.println("Logout efetuado com sucesso");
        else
            System.out.println("Successful Logout");
    }

    public void printResetPassword(boolean i) {
        if (!i)
            System.out.println("Password recuperada com sucesso");
        else
            System.out.println("Successful Password Reset");
    }


    public void printPedirUsername(boolean i) {
        if (!i)
            System.out.println("Introduza o username: ");
        else
            System.out.println("Username: ");
    }

    /**
     * Apresenta pedir pass
     */
    public void printPedirPassword(boolean i) {
        if (!i)
            System.out.println("Introduza a password: ");
        else
            System.out.println("Password: ");
    }

    public void printPedirNovaPassword(boolean i) {
        if (!i)
            System.out.println("Introduza a nova password: ");
        else
            System.out.println("Select a new password: ");
    }

    public void printPedirOdd(boolean i) {
        if (!i)
            System.out.println("Introduza um odd ID: ");
        else
            System.out.println("Select a odd ID: ");
    }

    public void printPedirOddVencedora(boolean i){
        if (!i)
            System.out.println("Introduza o odd ID vencedor: ");
        else
            System.out.println("Select the winner odd ID: ");
    }

    public void printPedirResultado(boolean i) {
        if (!i)
            System.out.println("Resultado: ");
        else
            System.out.println("Result: ");
    }

    public void printNovaOdd(boolean i) {
        if (!i)
            System.out.println("Pretende adicionar outra odd à aposta?(S/N)");
        else
            System.out.println("Do you want to add other odd to the bet?(Y/N)");
    }

    public void printPedirEmail(boolean i) {
        if (!i)
            System.out.println("Introduza o email: ");
        else
            System.out.println("Email: ");
    }

    public void printPedirDeposito(boolean i) {
        if (!i)
            System.out.println("Introduza o montante a depositar: ");
        else
            System.out.println("Select the deposit amount: ");
    }

    public void printPedirMontante(boolean i) {
        if (!i)
            System.out.println("Introduza o montante da aposta: ");
        else
            System.out.println("Select the bet amount: ");
    }

    public void printPedirLevantamento(boolean i) {
        if (!i)
            System.out.println("Introduza o montante a levantar: ");
        else
            System.out.println("Select the withdraw amount: ");
    }

    public void printPedirOpção(boolean i) {
        if (!i)
            System.out.println("Escolha a sua opção: ");
        else
            System.out.println("Choose one option: ");
    }

    public void printMessage(String m) {
        System.out.println(m);
    }

    public void printErroComandoInvalido(boolean i){
        if (!i)
            System.out.println("Comando Inválido");
        else
            System.out.println("Invalid Comand");
    }

    public void printErroDadosInvalidos(boolean i) {
        if (!i)
            System.out.println("Dados inválidos");
        else
            System.out.println("Invalid Information");
    }

    public void printErroMontanteInvalido(boolean i) {
        if (!i)
            System.out.println("Montante inválido");
        else
            System.out.println("Invalid Amount");
    }

    public void printErroOddInvalida(boolean i) {
        if (!i)
            System.out.println("Odd inválida");
        else
            System.out.println("Invalid Odd");
    }

    public void printEscolherMoeda(boolean i) {
        if (i){
            System.out.println("+--------- CURRENCY --------+\n"+
                    "|1 - EUR (Euro)             |\n"+
                    "|2 - USD (US Dollar)        |\n"+
                    "|3 - GPB (Pound Sterling)   |\n"+
                    "|4 - ADA (Cardano)          |\n"+
                    "+---------------------------+");
        }
        else{
            System.out.println("+---------- MOEDA ---------+\n"+
                    "|1 - EUR (Euro)            |\n"+
                    "|2 - USD (Dólar Americano) |\n"+
                    "|3 - GPB (Libra Esterlina) |\n"+
                    "|4 - ADA (Cardano)         |\n"+
                    "+--------------------------+");
        }
    }

    @Override
    public void printHistorico(boolean ingles, String hist) {
        if(ingles)
            System.out.println("--------  HISTORY  ---------\n"+hist);
        else
            System.out.println("-------- HISTÓRIO ---------\n"+hist);
    }


}
