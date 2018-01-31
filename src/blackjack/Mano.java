package blackjack;

/**
 * An object of type Hand represents a mano of cards. The cards belong to the
 * class Card. A mano is empty when it is created, and any number of cards can
 * be added to it.
 */
import java.util.ArrayList;

public class Mano {

    private ArrayList<Carta> mano;   // The cards in the mano.

    /**
     * Create a mano that is initially empty.
     */
    public Mano() {
        mano = new ArrayList<Carta>();
    }

    /**
     * Remove all cards from the mano, leaving it empty.
     */
    public void vaciar() {
        mano.clear();
    }

    /**
     * Add a card to the mano. It is added at the end of the current mano.
     *
     * @param c the non-null card to be added.
     * @throws NullPointerException if the parameter c is null.
     */
    public void agregarCarta(Carta c) {
        if (c == null) {
            throw new NullPointerException("No se puede agregar una carta nula a una mano.");
        }
        mano.add(c);
    }

    /**
     * Remove a card from the mano, if present.
     *
     * @param c the card to be removed. If c is null or if the card is not in
     * the mano, then nothing is done.
     */
    public void removerCarta(Carta c) {
        mano.remove(c);
    }

    /**
     * Remove the card in a specified posicion from the mano.
     *
     * @param posicion the posicion of the card that is to be removed, where
     * posicions are starting from zero.
     * @throws IllegalArgumentException if the posicion does not exist in the
     * mano, that is if the posicion is less than 0 or greater than or equal to
     * the number of cards in the mano.
     */
    public void removerCarta(int posicion) {
        if (posicion < 0 || posicion >= mano.size()) {
            throw new IllegalArgumentException("La posición no existe en la mano: "
                    + posicion);
        }
        mano.remove(posicion);
    }

    /**
     * Returns the number of cards in the mano.
     */
    public int getContCartas() {
        return mano.size();
    }

    /**
     * Gets the card in a specified posicion in the mano. (Note that this card
     * is not removed from the mano!)
     *
     * @param posicion the posicion of the card that is to be returned
     * @throws IllegalArgumentException if posicion does not exist in the mano
     */
    public Carta getCarta(int posicion) {
        if (posicion < 0 || posicion >= mano.size()) {
            throw new IllegalArgumentException("La posición no existe en la mano: "
                    + posicion);
        }
        return mano.get(posicion);
    }

    /**
     * Sorts the cards in the mano so that cards of the same suit are grouped
     * together, and within a suit the cards are sorted by value. Note that aces
     * are considered to have the lowest value, 1.
     */
    public void ordenarXPalo() {
        ArrayList<Carta> newHand = new ArrayList<Carta>();
        while (mano.size() > 0) {
            int pos = 0;  // Position of minimal card.
            Carta c = mano.get(0);  // Minimal card.
            for (int i = 1; i < mano.size(); i++) {
                Carta c1 = mano.get(i);
                if (c1.getPalo()< c.getPalo()
                        || (c1.getPalo()== c.getPalo() && c1.getValor()< c.getValor())) {
                    pos = i;
                    c = c1;
                }
            }
            mano.remove(pos);
            newHand.add(c);
        }
        mano = newHand;
    }

    /**
     * Sorts the cards in the mano so that cards of the same value are grouped
     * together. Cards with the same value are sorted by suit. Note that aces
     * are considered to have the lowest value, 1.
     */
    public void ordenarXValor() {
        ArrayList<Carta> newHand = new ArrayList<Carta>();
        while (mano.size() > 0) {
            int pos = 0;  // Position of minimal card.
            Carta c = mano.get(0);  // Minimal card.
            for (int i = 1; i < mano.size(); i++) {
                Carta c1 = mano.get(i);
                if (c1.getValor()< c.getValor()
                        || (c1.getValor()== c.getValor()&& c1.getPalo()< c.getPalo())) {
                    pos = i;
                    c = c1;
                }
            }
            mano.remove(pos);
            newHand.add(c);
        }
        mano = newHand;
    }

}
