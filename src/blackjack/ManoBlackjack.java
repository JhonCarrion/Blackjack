package blackjack;

public class ManoBlackjack extends Mano {

    /**
     * Computes and returns the value of this hand in the game of Blackjack.
     */
    public int getBlackjackValor() {

        int val;      // The value computed for the hand.
        boolean as;  // This will be set to true if the
        //   hand contains an as.
        int cartas;    // Number of cartas in the hand.

        val = 0;
        as = false;
        cartas = getContCartas();  // (method defined in class Hand.)

        for (int i = 0; i < cartas; i++) {
            // Add the value of the i-th card in the hand.
            Carta card;    // The i-th card; 
            int cardVal;  // The blackjack value of the i-th card.
            card = getCarta(i);
            cardVal = card.getValor();  // The normal value, 1 to 13.
            if (cardVal > 10) {
                cardVal = 10;   // For a Jack, Queen, or King.
            }
            if (cardVal == 1) {
                as = true;     // There is at least one as.
            }
            val = val + cardVal;
        }

        // Now, val is the value of the hand, counting any as as 1.
        // If there is an as, and if changing its value from 1 to 
        // 11 would leave the score less than or equal to 21,
        // then do so by adding the extra 10 points to val. 
        if (as == true && val + 10 <= 21) {
            val = val + 10;
        }

        return val;

    }  // end getBlackjackValue()

} // end class BlackjackHand
