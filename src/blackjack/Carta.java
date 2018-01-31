package blackjack;

/**
 * An object of type Card represents a playing card from a standard Poker deck,
 * including Jokers. The card has a palo, which can be spades, hearts, diamonds,
 * clubs, or joker. A spade, heart, diamond, or club has one of the 13 valors:
 * ace, 2, 3, 4, 5, 6, 7, 8, 9, 10, jack, queen, or king. Note that "ace" is
 * considered to be the smallest valor. A joker can also have an associated
 * valor; this valor can be anything and can be used to keep track of several
 * different jokers.
 */
public class Carta {

    public final static int ESPADAS = 0;   // Codes for the 4 palos, plus Joker.
    public final static int CORAZONES = 1;
    public final static int DIAMANTES = 2;
    public final static int TREBOLES = 3;
    public final static int JOKER = 4;

    public final static int AS = 1;      // Codes for the non-numeric cards.
    public final static int JOTA = 11;    //   Cards 2 through 10 have their 
    public final static int REINA = 12;   //   numerical valors for their codes.
    public final static int REY = 13;

    /**
     * This card's palo, one of the constants ESPADAS, CORAZONES, DIAMANTES,
     * TREBOLES, or JOKER. The palo cannot be changed after the card is
     * constructed.
     */
    private final int palo;

    /**
     * The card's valor. For a normal card, this is one of the valors 1 through
     * 13, with 1 representing AS. For a JOKER, the valor can be anything. The
     * valor cannot be changed after the card is constructed.
     */
    private final int valor;

    /**
     * Creates a Joker, with 1 as the associated valor. (Note that "new Card()"
     * is equivalent to "new Card(1,Card.JOKER)".)
     */
    public Carta() {
        palo = JOKER;
        valor = 1;
    }

    /**
     * Creates a card with a specified palo and valor.
     *
     * @param elValor the valor of the new card. For a regular card (non-joker),
     * the valor must be in the range 1 through 13, with 1 representing an Ace.
     * You can use the constants Card.AS, Card.JOTA, Card.REINA, and Card.REY.
     * For a Joker, the valor can be anything.
     * @param elPalo the palo of the new card. This must be one of the valors
     * Card.ESPADAS, Card.CORAZONES, Card.DIAMANTES, Card.TREBOLES, or
     * Card.JOKER.
     * @throws IllegalArgumentException if the parameter valors are not in the
     * permissible ranges
     */
    public Carta(int elValor, int elPalo) {
        if (elPalo != ESPADAS && elPalo != CORAZONES && elPalo != DIAMANTES
                && elPalo != TREBOLES && elPalo != JOKER) {
            throw new IllegalArgumentException("Palo de naipes ilegal.");
        }
        if (elPalo != JOKER && (elValor < 1 || elValor > 13)) {
            throw new IllegalArgumentException("Valor ilegal de naipes.");
        }
        valor = elValor;
        palo = elPalo;
    }

    /**
     * Returns the palo of this card.
     *
     * @returns the palo, which is one of the constants Card.ESPADAS,
     * Card.CORAZONES, Card.DIAMANTES, Card.TREBOLES, or Card.JOKER
     */
    public int getPalo() {
        return palo;
    }

    /**
     * Returns the valor of this card.
     *
     * @return the valor, which is one of the numbers 1 through 13, inclusive
     * for a regular card, and which can be any valor for a Joker.
     */
    public int getValor() {
        return valor;
    }

    /**
     * Returns a String representation of the card's palo.
     *
     * @return one of the strings "Spades", "Hearts", "Diamonds", "Clubs" or
     * "Joker".
     */
    public String getPaloComoString() {
        switch (palo) {
            case ESPADAS:
                return "Espadas";
            case CORAZONES:
                return "Corazones";
            case DIAMANTES:
                return "Diamantes";
            case TREBOLES:
                return "Treboles";
            default:
                return "Joker";
        }
    }

    /**
     * Returns a String representation of the card's valor.
     *
     * @return for a regular card, one of the strings "Ace", "2", "3", ...,
     * "10", "Jack", "Queen", or "King". For a Joker, the string is always
     * numerical.
     */
    public String getValorComoString() {
        if (palo == JOKER) {
            return "" + valor;
        } else {
            switch (valor) {
                case 1:
                    return "As";
                case 2:
                    return "2";
                case 3:
                    return "3";
                case 4:
                    return "4";
                case 5:
                    return "5";
                case 6:
                    return "6";
                case 7:
                    return "7";
                case 8:
                    return "8";
                case 9:
                    return "9";
                case 10:
                    return "10";
                case 11:
                    return "Jota";
                case 12:
                    return "Reina";
                default:
                    return "Rey";
            }
        }
    }

    /**
     * Returns a string representation of this card, including both its palo and
     * its valor (except that for a Joker with valor 1, the return valor is just
     * "Joker"). Sample return valors are: "Queen of Hearts", "10 of Diamonds",
     * "Ace of Spades", "Joker", "Joker #2"
     */
    public String toString() {
        if (palo == JOKER) {
            if (valor == 1) {
                return "Joker";
            } else {
                return "Joker #" + valor;
            }
        } else {
            return getValorComoString() + " de " + getPaloComoString();
        }
    }

} // end class Card
