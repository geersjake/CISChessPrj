package chess;

import javax.swing.*;

/***********************************************************************
 *
 * Plays Chess. Initializes the JFrame from Chess
 *
 * @author Jake, Tyler, Jonathan
 * @version 1.0
 *
 **********************************************************************/


public class ChessGUI {

    public static void main(String[] args) {
        JFrame frame = new JFrame("Chess Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ChessPanel panel = new ChessPanel();
        frame.getContentPane().add(panel);

        frame.setResizable(false);//merge this
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }
}
