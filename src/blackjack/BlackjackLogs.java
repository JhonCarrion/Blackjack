/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blackjack;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author alex
 */
public class BlackjackLogs {

    ArrayList<String> logs;

    public BlackjackLogs(String contenido) {
        this.logs = new ArrayList<>();
        if (new File("BlackjackLogs.txt").exists()) {
            this.logs = cargar();
        }
        archivo(contenido);
    }

    private void archivo(String contenido) {
        try {
            logs.add(contenido);
            BufferedWriter salida = new BufferedWriter(new FileWriter("BlackjackLogs.txt"));
            for (int i = logs.size() - 1; i >= 0; i--) {
                salida.write(logs.get(i) + "\n%\n");
            }
            salida.close();
        } catch (IOException ex) {
            Logger.getLogger(Blackjack.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private ArrayList<String> cargar() {
        ArrayList<String> aux = new ArrayList<>();
        String contenido = "";
        try {
            String s;
            String linea[];
            BufferedReader lector = new BufferedReader(new FileReader("BlackjackLogs.txt"));
            while ((s = lector.readLine()) != null) {
                contenido += s + "\n";
            }

            for (int i = contenido.split("%").length - 1; i >= 0; i--) {
                aux.add(contenido.split("%")[i]);
            }

            lector.close();
        } catch (IOException ex) {
            Logger.getLogger(Blackjack.class.getName()).log(Level.SEVERE, null, ex);
        }
        return aux;
    }
}
