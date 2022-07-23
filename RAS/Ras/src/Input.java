/**
 *  Classe que recebe e valida o imput recebido
 */

import View.IView;

import java.io.Serializable;
import java.util.Scanner;

public class Input implements Serializable {

    public boolean lerSN(IView a,boolean ingles){
        Scanner s = new Scanner(System.in);
        String line;

        do{
            a.printNovaOdd(ingles);
            line = s.nextLine();
        } while (!line.toUpperCase().equals("S") && !line.toUpperCase().equals("N") && !line.toUpperCase().equals("Y"));

        return line.toUpperCase().equals("S") || line.toUpperCase().equals("Y");
    }


    public double lerDouble(IView a){
        Scanner s = new Scanner(System.in);
        double n = -1;

        do {
            try {
                String line = s.nextLine();
                n = Double.parseDouble(line);
            } catch (NumberFormatException nfe) {
                a.printMessage(nfe.getMessage());
                n = -1;
            }
        } while (n == -1);
        return n;
    }

    public int lerInt(IView a, int min, int max,boolean ingles){
        Scanner s = new Scanner(System.in);
        int n = -1;

        do{
            a.printPedirOpção(ingles);
            try {
                String line = s.nextLine();
                n = Integer.parseInt(line);
            } catch (NumberFormatException nfe) {
                a.printMessage(nfe.getMessage());
                n = -1;
            }
        } while (n < min || n > max);

        return n;
    }


}
