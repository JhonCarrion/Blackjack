/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import blackjack.Carta;
import blackjack.ManoBlackjack;
import blackjack.Mazo;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * En este programa, el usuario juega un juego de Blackjack. La computadora
 * actúa como distribuidor. El usuario juega haciendo clic en "¡Golpear!" y
 * "¡Stand!" botones.
 *
 * Esta clase define un panel, pero también contiene una rutina principal () que
 * hace es posible ejecutar el programa como una aplicación independiente.
 *
 * Este programa depende de las siguientes clases: Tarjeta, Mano, BlackjackHand,
 * Cubierta.
 */
/**
 *
 * @author alex
 */
public class BlackjackGUI extends JPanel {

    /**
     * La rutina principal simplemente abre una ventana que muestra un
     * BlackjackGUI.
     */
    public static void main(String[] args) {
        JFrame window = new JFrame("Blackjack");
        BlackjackGUI content = new BlackjackGUI();
        window.setContentPane(content);
        window.pack();  // Establecer el tamaño de la ventana al tamaño preferido de su contenido.
        window.setResizable(false);  // El usuario no puede cambiar el tamaño de la ventana.
        Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();
        window.setLocation((screensize.width - window.getWidth()) / 2,
                (screensize.height - window.getHeight()) / 2);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setVisible(true);
    }

    /**
     * El constructor establece el panel. Un CardPanel ocupa el CENTRO posición
     * del panel (donde CardPanel es una subclase de JPanel que es definido a
     * continuación). En la parte inferior hay un panel que contiene tres
     * botones. los CardPanel escucha ActionEvents desde los botones y hace todo
     * lo real trabajo del programa.
     */
    public BlackjackGUI() {

        setBackground(new Color(130, 50, 40));

        setLayout(new BorderLayout(3, 3));

        CardPanel board = new CardPanel();
        add(board, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(220, 200, 180));
        add(buttonPanel, BorderLayout.SOUTH);

        JButton hitButton = new JButton("Pedir!");
        hitButton.addActionListener(board);
        buttonPanel.add(hitButton);

        JButton standButton = new JButton("Detener!");
        standButton.addActionListener(board);
        buttonPanel.add(standButton);

        JButton newGame = new JButton("Nuevo Juego");
        newGame.addActionListener(board);
        buttonPanel.add(newGame);

        setBorder(BorderFactory.createLineBorder(new Color(130, 50, 40), 3));

    }  // end constructor

    /**
     * Una clase anidada que muestra el juego y hace todo el trabajo de mantener
     * seguimiento del estado y respuesta a los eventos del usuario.
     */
    private class CardPanel extends JPanel implements ActionListener {

        Mazo deck;         // Una baraja de cartas para ser usada en el juego.

        ManoBlackjack dealerHand;   // Mano que contiene las cartas del crupier.
        ManoBlackjack playerHand;   // Mano que contiene las tarjetas del usuario.

        String message;  // Un mensaje dibujado en el lienzo, que cambia
        //    para reflejar el estado del juego.

        boolean gameInProgress;  // Establecer como verdadero cuando un juego comienza y es falso
        //   cuando el juego termina.

        Font bigFont;      // Fuente que se usará para mostrar el mensaje.
        Font smallFont;    // Fuente que se usará para dibujar las cartas.

        /**
         * El constructor crea las fuentes e inicia el primer juego. También
         * establece un tamaño preferido de 460 por 310 para el panel. los el
         * método paintComponent () supone que este es, de hecho, el tamaño del
         * panel (aunque puede ser un poco más alto sin ningún efecto negativo).
         */
        CardPanel() {
            setPreferredSize(new Dimension(460, 310));
            setBackground(new Color(0, 120, 0));
            smallFont = new Font("SansSerif", Font.PLAIN, 12);
            bigFont = new Font("Serif", Font.BOLD, 14);
            doNewGame();
        }

        /**
         * Responda cuando el usuario haga clic en un botón llamando al método.
         * Tenga en cuenta que los botones se crean y se configura la escucha en
         * el constructor de la clase BlackjackGUI.
         */
        public void actionPerformed(ActionEvent evt) {
            String command = evt.getActionCommand();
            if (command.equals("Pedir!")) {
                doHit();
            } else if (command.equals("Detener!")) {
                doStand();
            } else if (command.equals("Nuevo Juego")) {
                doNewGame();
            }
        }

        /**
         * Se llama a este método cuando el usuario hace clic en "¡Hit!" botón.
         * primero verificar que un juego esté realmente en progreso. Si no, da
         * un error mensaje y salida. De lo contrario, dale una tarjeta al
         * usuario. El juego puede terminar en este punto si el usuario supera
         * los 21 o si el usuario ha tomado 5 tarjetas sin pasar de 21.
         */
        void doHit() {
            if (gameInProgress == false) {
                message = "Click \"Nuevo Juego\" para empezar un nuevo juego.";
                repaint();
                return;
            }
            playerHand.agregarCarta(deck.cartasReparto());
            if (playerHand.getBlackjackValor() > 21) {
                message = "¡Has fallado! Lo siento, pierdes";
                gameInProgress = false;
            } else if (playerHand.getContCartas() == 5) {
                message = "Usted gana tomando 5 cartas sin pasar de 21.";
                gameInProgress = false;
            } else {
                message = "Tu tienes " + playerHand.getBlackjackValor() + ".  Pedir o Detener?";
            }
            repaint();
        }

        /**
         * Se llama a este método cuando el usuario hace clic en "¡Stand!"
         * botón. Comprobar si un juego está actualmente en progreso. Si es así,
         * el juego termina. los el crupier toma cartas hasta que el crupier
         * tenga 5 cartas o más 16 puntos. Luego se determina el ganador del
         * juego.
         */
        void doStand() {
            if (gameInProgress == false) {
                message = "Click \"Nuevo Juego\" para empezar un nuevo juego.";
                repaint();
                return;
            }
            gameInProgress = false;
            while (dealerHand.getBlackjackValor() <= 16 && dealerHand.getContCartas() < 5) {
                dealerHand.agregarCarta(deck.cartasReparto());
            }
            if (dealerHand.getBlackjackValor() > 21) {
                message = "¡Tú ganas! El distribuidor ha roto con " + dealerHand.getBlackjackValor() + ".";
            } else if (dealerHand.getContCartas() == 5) {
                message = "Lo siento, pierdes El distribuidor tomó 5 cartas sin pasar de 21.";
            } else if (dealerHand.getBlackjackValor() > playerHand.getBlackjackValor()) {
                message = "Lo siento, pierdes, " + dealerHand.getBlackjackValor()
                        + " a " + playerHand.getBlackjackValor() + ".";
            } else if (dealerHand.getBlackjackValor() == playerHand.getBlackjackValor()) {
                message = "Lo siento, pierdes El distribuidor gana en un empate.";
            } else {
                message = "Tu ganas, " + playerHand.getBlackjackValor()
                        + " a " + dealerHand.getBlackjackValor() + "!";
            }
            repaint();
        }

        /**
         * Llamado por el constructor y llamado por actionPerformed () si el
         * usuario hace clic en el botón "Nuevo juego". Comience un nuevo juego
         * Repartir dos cartas a cada jugador. El juego podría terminar en ese
         * momento si uno de los jugadores tenía blackjack. De lo contrario,
         * gameInProgress se establece en true y el juego comienza.
         */
        void doNewGame() {
            if (gameInProgress) {
                // Si el juego actual no ha terminado, es un error intentarlo
                // para comenzar un nuevo juego
                message = "¡Aún tienes que terminar este juego!";
                repaint();
                return;
            }
            deck = new Mazo();   // Crea el mazo y las manos para usar en este juego.
            dealerHand = new ManoBlackjack();
            playerHand = new ManoBlackjack();
            deck.barajar();
            dealerHand.agregarCarta(deck.cartasReparto());  // Entrega dos cartas a cada jugador.
            dealerHand.agregarCarta(deck.cartasReparto());
            playerHand.agregarCarta(deck.cartasReparto());
            playerHand.agregarCarta(deck.cartasReparto());
            if (dealerHand.getBlackjackValor() == 21) {
                message = "Lo siento, pierdes El distribuidor tiene Blackjack.";
                gameInProgress = false;
            } else if (playerHand.getBlackjackValor() == 21) {
                message = "¡Tú ganas! Tienes Blackjack.";
                gameInProgress = false;
            } else {
                message = "Tu tienes " + playerHand.getBlackjackValor() + ".  Pedir o Detener?";
                gameInProgress = true;
            }
            repaint();
        }  // end newGame();

        /**
         * El método de pintura muestra el mensaje en la parte inferior del
         * lienzo, y dibuja todas las cartas repartidas en el lienzo.
         */
        public void paintComponent(Graphics g) {

            super.paintComponent(g); // llenar con color de fondo.

            g.setFont(bigFont);
            g.setColor(Color.GREEN);
            g.drawString(message, 10, getHeight() - 10);

            // Dibuja las etiquetas de los dos juegos de cartas.
            g.drawString("Tarjetas de distribuidor:", 10, 23);
            g.drawString("Tus cartas:", 10, 153);

            // Dibuja las cartas del repartidor. Dibuja la primera carta boca abajo si
            // el juego todavía está en progreso, será revelado
            // cuando el juego termina.
            g.setFont(smallFont);
            if (gameInProgress) {
                drawCard(g, null, 10, 30);
            } else {
                drawCard(g, dealerHand.getCarta(0), 10, 30);
            }
            for (int i = 1; i < dealerHand.getContCartas(); i++) {
                drawCard(g, dealerHand.getCarta(i), 10 + i * 90, 30);
            }

            // Dibuja las cartas del usuario.
            for (int i = 0; i < playerHand.getContCartas(); i++) {
                drawCard(g, playerHand.getCarta(i), 10 + i * 90, 160);
            }

        }  // end paint();

        /**
         * Dibuja una carta como un rectángulo de 80 por 100 con la esquina
         * superior izquierda en (x, y) La tarjeta se dibuja en el contexto de
         * gráficos g. Si la tarjeta es nula, luego se dibuja una carta boca
         * abajo. (¡Las cartas son bastante primitivas!)
         */
        void drawCard(Graphics g, Carta card, int x, int y) {
            if (card == null) {
                // Dibuja una carta boca abajo
                g.setColor(Color.blue);
                g.fillRect(x, y, 80, 100);
                g.setColor(Color.white);
                g.drawRect(x + 3, y + 3, 73, 93);
                g.drawRect(x + 4, y + 4, 71, 91);
            } else {
                g.setColor(Color.white);
                g.fillRect(x, y, 80, 100);
                g.setColor(Color.gray);
                g.drawRect(x, y, 79, 99);
                g.drawRect(x + 1, y + 1, 77, 97);
                if (card.getPalo() == Carta.DIAMANTES || card.getPalo() == Carta.CORAZONES) {
                    g.setColor(Color.red);
                } else {
                    g.setColor(Color.black);
                }
                g.drawString(card.getValorComoString(), x + 10, y + 30);
                g.drawString("de", x + 10, y + 50);
                g.drawString(card.getPaloComoString(), x + 10, y + 70);
            }
        }  // end drawCard()

    } // end nested class CardPanel

} // end class BlackjackGUI
