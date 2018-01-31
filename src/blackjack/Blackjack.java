/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blackjack;

import java.util.Calendar;
import java.util.Scanner;

/**
 *
 * @author alex
 */
public class Blackjack {

    static Calendar cal = Calendar.getInstance();
    static String logstr = cal.get(Calendar.YEAR) + "/" + (cal.get(Calendar.MONTH) + 1) + "/" + cal.get(Calendar.DAY_OF_MONTH) + "\n";

    /**
     * Let the user play one game of Blackjack, with the computer as dealer.
     *
     * @return true if the user wins the game, false if the user loses.
     */
    static boolean playBlackjack() {
        Scanner sc = new Scanner(System.in);
        Mazo mazo;                  // A mazo of cards.  A new mazo for each game.
        ManoBlackjack manoDistribuidor;   // The dealer's hand.
        ManoBlackjack manoUsuario;     // The user's hand.

        mazo = new Mazo();
        manoDistribuidor = new ManoBlackjack();
        manoUsuario = new ManoBlackjack();

        /*  Shuffle the mazo, then deal two cards to each player. */
        mazo.barajar();
        manoDistribuidor.agregarCarta(mazo.cartasReparto());
        manoDistribuidor.agregarCarta(mazo.cartasReparto());
        manoUsuario.agregarCarta(mazo.cartasReparto());
        manoUsuario.agregarCarta(mazo.cartasReparto());

        Blackjack.logstr += "\n";
        Blackjack.logstr += "\n";
        System.out.println();
        System.out.println();


        /* Check if one of the players has Blackjack (two cards totaling to 21).
         The player with Blackjack wins the game.  Dealer wins ties.
         */
        if (manoDistribuidor.getBlackjackValor() == 21) {
            System.out.println("El distribuidor tiene el " + manoDistribuidor.getCarta(0)
                    + " y el " + manoDistribuidor.getCarta(1) + ".");
            System.out.println("El usuario tiene el " + manoUsuario.getCarta(0)
                    + " y el " + manoUsuario.getCarta(1) + ".");
            System.out.println();
            System.out.println("El distribuidor tiene Blackjack. El distribuidor gana.");
            Blackjack.logstr += "El distribuidor tiene el " + manoDistribuidor.getCarta(0)
                    + " y el " + manoDistribuidor.getCarta(1) + ".\n";
            Blackjack.logstr += "El usuario tiene el " + manoUsuario.getCarta(0)
                    + " y el " + manoUsuario.getCarta(1) + ".\n";
            Blackjack.logstr += "\n";
            Blackjack.logstr += "El distribuidor tiene Blackjack. El distribuidor gana.\n";
            return false;
        }

        if (manoUsuario.getBlackjackValor() == 21) {
            System.out.println("El distribuidor tiene el " + manoDistribuidor.getCarta(0)
                    + " y el " + manoDistribuidor.getCarta(1) + ".");
            System.out.println("El usuario tiene el " + manoUsuario.getCarta(0)
                    + " y el " + manoUsuario.getCarta(1) + ".");
            System.out.println();
            System.out.println("Usted tiene Blackjack.  Usted Gana.");
            Blackjack.logstr += "El distribuidor tiene el " + manoDistribuidor.getCarta(0)
                    + " y el " + manoDistribuidor.getCarta(1) + ".\n";
            Blackjack.logstr += "El usuario tiene el " + manoUsuario.getCarta(0)
                    + " y el " + manoUsuario.getCarta(1) + ".\n";
            Blackjack.logstr += "\n";
            Blackjack.logstr += "Usted tiene Blackjack.  Usted Gana.\n";
            return true;
        }

        /*  If neither player has Blackjack, play the game.  First the user 
         gets a chance to draw cards (i.e., to "Hit").  The while loop ends 
         when the user chooses to "Stand".  If the user goes over 21,
         the user loses immediately.
         */
        while (true) {

            /* Display user's cards, and let user decide to Hit or Stand. */
            Blackjack.logstr += "\n";
            Blackjack.logstr += "\n";
            System.out.println();
            System.out.println();
            System.out.println("Sus cartas son: ");
            Blackjack.logstr += "Sus cartas son: \n";
            for (int i = 0; i < manoUsuario.getContCartas(); i++) {
                System.out.println("    " + manoUsuario.getCarta(i));
                Blackjack.logstr += "    " + manoUsuario.getCarta(i) + "\n";
            }
            System.out.println("Su total es: " + manoUsuario.getBlackjackValor());
            Blackjack.logstr += "Su total es: " + manoUsuario.getBlackjackValor() + "\n";
            System.out.println();
            Blackjack.logstr += "\n";
            System.out.println("El Distribuidor muestra el  " + manoDistribuidor.getCarta(0));
            Blackjack.logstr += "El Distribuidor muestra el  " + manoDistribuidor.getCarta(0) + "\n";
            System.out.println();
            Blackjack.logstr += "\n";
            System.out.print("Hit (H) o Stand (S)? ");
            Blackjack.logstr += "Hit (H) o Stand (S)? ";
            char userAction;  // User's response, 'H' or 'S'.
            do {
                String entrada = sc.next();
                userAction = Character.toUpperCase(entrada.charAt(0));
                Blackjack.logstr += entrada + "\n";
                if (userAction != 'H' && userAction != 'S') {
                    System.out.print("Por favor responda H o S:  ");
                    Blackjack.logstr += "Por favor responda H o S:  ";
                }
            } while (userAction != 'H' && userAction != 'S');

            /* If the user Hits, the user gets a card.  If the user Stands,
             the loop ends (and it's the dealer's turn to draw cards).
             */
            if (userAction == 'S') {
                // Loop ends; user is done taking cards.
                break;
            } else {  // userAction is 'H'.  Give the user a card.  
                // If the user goes over 21, the user loses.
                Carta newCard = mazo.cartasReparto();
                manoUsuario.agregarCarta(newCard);
                System.out.println();
                System.out.println("hits del Usuario.");
                System.out.println("Su carta es " + newCard);
                System.out.println("Su total es ahora " + manoUsuario.getBlackjackValor());
                Blackjack.logstr += "\n";
                Blackjack.logstr += "hits del Usuario.\n";
                Blackjack.logstr += "Su carta es " + newCard + "\n";
                Blackjack.logstr += "Su total es ahora " + manoUsuario.getBlackjackValor() + "\n";
                if (manoUsuario.getBlackjackValor() > 21) {
                    System.out.println();
                    System.out.println("Has subido por encima de 21. Pierdes.");
                    System.out.println("La otra tarjeta del distribuidor era la "
                            + manoDistribuidor.getCarta(1));
                    Blackjack.logstr += "\n";
                    Blackjack.logstr += "Has subido por encima de 21. Pierdes.\n";
                    Blackjack.logstr += "La otra tarjeta del distribuidor era la "
                            + manoDistribuidor.getCarta(1) + "\n";
                    return false;
                }
            }

        } // end while loop

        /* If we get to this point, the user has Stood with 21 or less.  Now, it's
         the dealer's chance to draw.  Dealer draws cards until the dealer's
         total is > 16.  If dealer goes over 21, the dealer loses.
         */
        System.out.println();
        System.out.println("stands del Usuario.");
        System.out.println("Las cartas de Distribuidor son ");
        System.out.println("    " + manoDistribuidor.getCarta(0));
        System.out.println("    " + manoDistribuidor.getCarta(1));
        Blackjack.logstr += "\n";
        Blackjack.logstr += "stands del Usuario.\n";
        Blackjack.logstr += "Las cartas de Distribuidor son \n";
        Blackjack.logstr += "    " + manoDistribuidor.getCarta(0) + "\n";
        Blackjack.logstr += "    " + manoDistribuidor.getCarta(1) + "\n";
        while (manoDistribuidor.getBlackjackValor() <= 16) {
            Carta newCard = mazo.cartasReparto();
            System.out.println("El tristribuidor hace hit y obtiene el " + newCard);
            Blackjack.logstr += "El tristribuidor hace hit y obtiene el " + newCard + "\n";
            manoDistribuidor.agregarCarta(newCard);
            if (manoDistribuidor.getBlackjackValor() > 21) {
                System.out.println();
                System.out.println("Distribuidor detenido por pasar de 21. Usted gana.");
                Blackjack.logstr += "\n";
                Blackjack.logstr += "Distribuidor detenido por pasar de 21. Usted gana.\n";
                return true;
            }
        }
        System.out.println("El total del distribuidor es " + manoDistribuidor.getBlackjackValor());
        Blackjack.logstr += "El total del distribuidor es " + manoDistribuidor.getBlackjackValor() + "\n";

        /* If we get to this point, both players have 21 or less.  We
         can determine the winner by comparing the values of their hands. */
        System.out.println();
        Blackjack.logstr += "\n";
        if (manoDistribuidor.getBlackjackValor() == manoUsuario.getBlackjackValor()) {
            System.out.println("El distribuidor gana en un empate. Tú pierdes.");
            Blackjack.logstr += "El distribuidor gana en un empate. Tú pierdes.\n";
            return false;
        } else if (manoDistribuidor.getBlackjackValor() > manoUsuario.getBlackjackValor()) {
            System.out.println("Distribuidor gana, " + manoDistribuidor.getBlackjackValor()
                    + " puntos a " + manoUsuario.getBlackjackValor() + ".");
            Blackjack.logstr += "Distribuidor gana, " + manoDistribuidor.getBlackjackValor()
                    + " puntos a " + manoUsuario.getBlackjackValor() + ".\n";
            return false;
        } else {
            System.out.println("Tu ganas, " + manoUsuario.getBlackjackValor()
                    + " puntos a " + manoDistribuidor.getBlackjackValor() + ".");
            Blackjack.logstr += "Tu ganas, " + manoUsuario.getBlackjackValor()
                    + " puntos a " + manoDistribuidor.getBlackjackValor() + ".\n";
            return true;
        }

    }  // end playBlackjack()

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int dolares;          // Amount of dolares the user has.
        int apuesta;            // Amount user apuestas on a game.
        boolean ganadas;   // Did the user win the game?

        System.out.println("Bienvenido al juego de blackjack.");
        System.out.println();
        Blackjack.logstr += "Bienvenido al juego de blackjack.\n\n";

        dolares = 100;  // User starts with $100.

        while (true) {
            System.out.println("Usted tiene " + dolares + " dolares.");
            Blackjack.logstr += "Usted tiene " + dolares + " dolares.\n";
            do {
                System.out.println("¿Cuántos dólares quieres apostar? (Ingrese 0 para finalizar)");
                System.out.print("? ");
                Blackjack.logstr += "¿Cuántos dólares quieres apostar? (Ingrese 0 para finalizar)\n";
                Blackjack.logstr += "? ";
                apuesta = sc.nextInt();
                Blackjack.logstr += apuesta + "\n";
                if (apuesta < 0 || apuesta > dolares) {
                    System.out.println("Su respuesta debe estar entre 0 y" + dolares + '.');
                    Blackjack.logstr += "Su respuesta debe estar entre 0 y" + dolares + ".\n";
                }
            } while (apuesta < 0 || apuesta > dolares);
            if (apuesta == 0) {
                break;
            }
            ganadas = playBlackjack();
            if (ganadas) {
                dolares = dolares + apuesta;
            } else {
                dolares = dolares - apuesta;
            }
            System.out.println();
            Blackjack.logstr += "\n";
            if (dolares == 0) {
                System.out.println("¡Parece que te has quedado sin dolares!");
                Blackjack.logstr += "¡Parece que te has quedado sin dolares!\n";
                break;
            }
        }

        System.out.println();
        System.out.println("Te vas con $" + dolares + '.');
        Blackjack.logstr += "\nTe vas con $" + dolares + '.';
        Blackjack.logstr += "\n";
        BlackjackLogs blackjackLogs = new BlackjackLogs(logstr);
    } // end main()

}
