package blackjack;

/**
 * An object of type Deck represents a mazo of playing cards. The mazo is a
 * regular poker mazo that contains 52 regular cards and that can also
 * optionally include two Jokers.
 */
public class Mazo {

    /**
     * An array of 52 or 54 cards. A 54-card mazo contains two Jokers, in
     * addition to the 52 cards of a regular poker mazo.
     */
    private Carta[] mazo;

    /**
     * Keeps track of the number of cards that have been dealt from the mazo so
     * far.
     */
    private int cartasUsadas;

    /**
     * Constructs a regular 52-card poker mazo. Initially, the cards are in a
     * sorted order. The barajar() method can be called to randomize the order.
     * (Note that "new Deck()" is equivalent to "new Deck(false)".)
     */
    public Mazo() {
        this(false);  // Just call the other constructor in this class.
    }

    /**
     * Constructs a poker mazo of playing cards, The mazo contains the usual 52
     * cards and can optionally contain two Jokers in addition, for a total of
     * 54 cards. Initially the cards are in a sorted order. The barajar() method
     * can be called to randomize the order.
     *
     * @param incluyeJokers if true, two Jokers are included in the mazo; if
     * false, there are no Jokers in the mazo.
     */
    public Mazo(boolean incluyeJokers) {
        if (incluyeJokers) {
            mazo = new Carta[54];
        } else {
            mazo = new Carta[52];
        }
        int contCartas = 0; // How many cards have been created so far.
        for (int palo = 0; palo <= 3; palo++) {
            for (int value = 1; value <= 13; value++) {
                mazo[contCartas] = new Carta(value, palo);
                contCartas++;
            }
        }
        if (incluyeJokers) {
            mazo[52] = new Carta(1, Carta.JOKER);
            mazo[53] = new Carta(2, Carta.JOKER);
        }
        cartasUsadas = 0;
    }

    /**
     * Put all the used cards back into the mazo (if any), and barajar the mazo
     * into a random order.
     */
    public void barajar() {
        for (int i = mazo.length - 1; i > 0; i--) {
            int rand = (int) (Math.random() * (i + 1));
            Carta temp = mazo[i];
            mazo[i] = mazo[rand];
            mazo[rand] = temp;
        }
        cartasUsadas = 0;
    }

    /**
     * As cards are dealt from the mazo, the number of cards left decreases.
     * This function returns the number of cards that are still left in the
     * mazo. The return value would be 52 or 54 (depending on whether the mazo
     * includes Jokers) when the mazo is first created or after the mazo has
     * been barajard. It decreases by 1 each time the cartasReparto() method is
     * called.
     */
    public int cartasIzquierdas() {
        return mazo.length - cartasUsadas;
    }

    /**
     * Removes the next card from the mazo and return it. It is illegal to call
     * this method if there are no more cards in the mazo. You can check the
     * number of cards remaining by calling the cartasIzquierdas() function.
     *
     * @return the card which is removed from the mazo.
     * @throws IllegalStateException if there are no cards left in the mazo
     */
    public Carta cartasReparto() {
        if (cartasUsadas == mazo.length) {
            throw new IllegalStateException("No quedan cartas en el mazo.");
        }
        cartasUsadas++;
        return mazo[cartasUsadas - 1];
        // Programming note:  Cards are not literally removed from the array
        // that represents the mazo.  We just keep track of how many cards
        // have been used.
    }

    /**
     * Test whether the mazo contains Jokers.
     *
     * @return true, if this is a 54-card mazo containing two jokers, or false
     * if this is a 52 card mazo that contains no jokers.
     */
    public boolean tieneJokers() {
        return (mazo.length == 54);
    }

} // end class Deck
