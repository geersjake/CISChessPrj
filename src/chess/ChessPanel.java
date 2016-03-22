package chess;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by tylerfaulk on 2/29/16.
 */
public class ChessPanel extends JPanel {

    private JButton[][] board;
    private ChessModel model;
    private JPanel center = new JPanel();
    private JPanel bottom = new JPanel();
    private JButton butQuit;
    private JButton butNewGame;

    //private JButton pieceLocation;
    //  private Move move = new Move();

    // declare other instance variables as needed

    private ButtonListener buttonListener = new ButtonListener();

    public ChessPanel() {

        model = new ChessModel();
        board = new JButton[8][8];


        center.setLayout(new GridLayout(model.getNumRows(), model.getNumCol()));
        bottom.setLayout(new GridLayout(1, 2));
        butQuit = new JButton("Quit");
        butNewGame = new JButton("New Game");
        bottom.add(butQuit);
        bottom.add(butNewGame);

        int count = 1;
        for (int row = 0; row < model.getNumRows(); row++) {
            for (int col = 0; col < model.getNumCol(); col++) {
                board[row][col] = new JButton("");
                board[row][col].addActionListener(buttonListener);
                board[row][col].setPreferredSize(new Dimension(75, 75));
                center.add(board[row][col]);

                if (count % 2 == 0)
                    board[row][col].setBackground(Color.gray);
                board[row][col].setOpaque(true);
                board[row][col].setBorderPainted(false);
                if (!(col == 7))
                    count++;
            }
        }

        //adding to content pane
        setLayout(new BorderLayout());
        add(new JLabel("Chess Game"), BorderLayout.NORTH);
        add(center, BorderLayout.CENTER);
        add(bottom, BorderLayout.SOUTH);
        displayBoard();

        // testing button and panel, remove from final
        //pieceLocation = new JButton("pieceLocation");
        //pieceLocation.addActionListener(buttonListener);
        //JPanel PLPanel = new JPanel();
        //PLPanel.add(pieceLocation);
        //this.add(PLPanel, BorderLayout.EAST);
        // testing button and panel, remove from final
    }

    private void displayBoard() {
        for (int row = 0; row < model.getNumRows(); row++) {
            for (int col = 0; col < model.getNumCol(); col++) {
                if (model.pieceAt(row, col) != null) {
                    if (model.pieceAt(row, col).type().equals("Rook")) {

                        if (model.pieceAt(row, col).player() == Player.WHITE) {
                            board[row][col].setIcon(new ImageIcon("R Rook.png"));
                        }
                        if (model.pieceAt(row, col).player() == Player.BLACK) {
                            board[row][col].setIcon(new ImageIcon("B Rook.png"));
                        }
                    }
                    if (model.pieceAt(row, col).type().equals("Knight")) {
                        if (model.pieceAt(row, col).player() == Player.WHITE) {
                            board[row][col].setIcon(new ImageIcon("R Knight.png"));
                        }
                        if (model.pieceAt(row, col).player() == Player.BLACK) {
                            board[row][col].setIcon(new ImageIcon("B Knight.png"));
                        }
                    }
                    if (model.pieceAt(row, col).type().equals("Bishop")) {
                        if (model.pieceAt(row, col).player() == Player.WHITE) {
                            board[row][col].setIcon(new ImageIcon("R Bishop.png"));

                        }
                        if (model.pieceAt(row, col).player() == Player.BLACK) {
                            board[row][col].setIcon(new ImageIcon("B Bishop.png"));
                        }
                    }
                    if (model.pieceAt(row, col).type().equals("King")) {
                        if (model.pieceAt(row, col).player() == Player.WHITE) {
                            board[row][col].setIcon(new ImageIcon("R King.png"));
                        }
                        if (model.pieceAt(row, col).player() == Player.BLACK) {
                            board[row][col].setIcon(new ImageIcon("B King.png"));
                        }
                    }
                    if (model.pieceAt(row, col).type().equals("Queen")) {
                        if (model.pieceAt(row, col).player() == Player.WHITE) {
                            board[row][col].setIcon(new ImageIcon("R Queen.png"));
                        }
                        if (model.pieceAt(row, col).player() == Player.BLACK) {
                            board[row][col].setIcon(new ImageIcon("B Queen.png"));
                        }
                    }
                    if (model.pieceAt(row, col).type().equals("Pawn")) {
                        if (model.pieceAt(row, col).player() == Player.WHITE) {
                            board[row][col].setIcon(new ImageIcon("R Pawn.png"));
                        }
                        if (model.pieceAt(row, col).player() == Player.BLACK) {
                            board[row][col].setIcon(new ImageIcon("B Pawn.png"));
                        }
                    }
                }
                else {
                    board[row][col].setIcon(null);
                }
            }
        }
    }

    // inner class that represents action listener for buttons
    Boolean firstClick = true;
    Move mover = new Move();
    private class ButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {

            // testing button
            //JComponent buttonPressed = (JComponent) event.getSource();
            //if (buttonPressed == pieceLocation) {
            //    JOptionPane.showMessageDialog(null, "" + model.pieceAt(2, 3).player());
            //}
            // testing button

            if (firstClick == true) {
                for (int row = 0; row < model.getNumRows(); row++) {
                    for (int col = 0; col < model.getNumCol(); col++) {

                        if (board[row][col] == event.getSource()) {
                            //JOptionPane.showMessageDialog(null, "first click");
                            firstClick = false;
                            mover.fromRow = row;
                            mover.fromColumn = col;
                            displayBoard();
                            return;
                        }
                    }
                }
            }
            if (firstClick == false) {
                for (int row = 0; row < model.getNumRows(); row++) {
                    for (int col = 0; col < model.getNumCol(); col++) {
                        if (board[row][col] == event.getSource()) {
                            //JOptionPane.showMessageDialog(null, "second click");
                            firstClick = true;
                            mover.toRow = row;
                            mover.toColumn = col;
                            try {
                                model.move(mover);
                            } catch (NullPointerException e) {
                                //this is a shitty fix for when Player = null
                                // FIXME: 3/21/2016 figure out a better way
                            }
                            displayBoard();
                            return;
                        }
                    }
                }
            }
        }
    }
}

