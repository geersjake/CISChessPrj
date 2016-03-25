package chess;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/***********************************************************************
 *
 * Has all the GUI code that makes and updates the GUI for the player
 *
 * @author Jake, Tyler, Jonathan
 * @version 1.0
 **********************************************************************/

public class ChessPanel extends JPanel {
    //The JButtons for the 8x8 board
    private JButton[][] board;
    //The model for the ChessModel
    private ChessModel model;
    //The center JPanel for the board buttons to go on
    private JPanel center = new JPanel();
    //The bottom JPanel fro the Quit New and Castle buttons
    private JPanel bottom = new JPanel();
    //The button to quit the game
    private JButton butQuit;
    //The button for a new game
    private JButton butNewGame;
    //The button if you want to castle
    private JButton butCastle;
    //The button listener to figure out what button
    // is pushed and what logic to call
    private ButtonListener buttonListener = new ButtonListener();

/***********************************************************************
 *
 * Puts all of the buttons on on the Panels and makes
 * the board of buttons with the nested for loops
 *
 **********************************************************************/

    public ChessPanel() {

        model = new ChessModel();
        board = new JButton[8][8];


        center.setLayout(new GridLayout(model.getNumRows(), model.getNumCol()));
        bottom.setLayout(new GridLayout(2, 2));
        butQuit = new JButton("Quit");
        butQuit.addActionListener(buttonListener);
        butNewGame = new JButton("New Game");
        butNewGame.addActionListener(buttonListener);
        butCastle = new JButton("Castle?");
        butCastle.addActionListener(buttonListener);
        bottom.add(butQuit);
        bottom.add(butNewGame);
        bottom.add(butCastle);

        int count = 1;
        for (int row = 0; row < model.getNumRows(); row++) {
            for (int col = 0; col < model.getNumCol(); col++) {
                board[row][col] = new JButton("");
                board[row][col].addActionListener(buttonListener);
                board[row][col].setPreferredSize(new Dimension(75, 75));
                center.add(board[row][col]);

                if (count % 2 == 0)
                    board[row][col].setBackground(Color.gray);
                else
                    board[row][col].setBackground(Color.white);

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
    }

/***********************************************************************
 *
 * Makes sure the board looks like what the logic says to look like
 * updates the 8x8 board
 *
 **********************************************************************/

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

/***********************************************************************
 *
 * The class for the button listener to track clicks
 *
 **********************************************************************/

    private class ButtonListener implements ActionListener {

/***********************************************************************
 *
 * Has all the GUI code that makes and updates the GUI for the player
 *
 * @param event the clicked button to find what button was clicked
 *
 **********************************************************************/

        public void actionPerformed(ActionEvent event) {

            if (butNewGame == event.getSource()){
                //model.isComplete();
                model.newGame();
                displayBoard();
            }
            if (butQuit == event.getSource()){
                setVisible(false);
                System.exit(0);
            }
            if(butCastle == event.getSource()){
                String[] options = {"Left", "Right"};
                int choice = JOptionPane.showOptionDialog(null,
                        "Left or Right?", "Choose an option", 0,
                        JOptionPane.INFORMATION_MESSAGE, null, options, null);
                //Component parentComponent, Object message, String title, int optionType,
                //int messageType, Icon icon, Object[] options, Object initialValue


                model.Castle(choice);
                displayBoard();
            }

            //first and second click to make sure what piece
            // and where you want it to go
            if (firstClick == true) {
                for (int row = 0; row < model.getNumRows(); row++) {
                    for (int col = 0; col < model.getNumCol(); col++) {

                        if (board[row][col] == event.getSource()) {
                            //Needs highlights
                            mover.fromRow = row;
                            mover.fromColumn = col;

                            if(model.pieceAt(mover.fromRow, mover.fromColumn) == null)
                                JOptionPane.showMessageDialog(null, "Click on a piece.");
                            else {
                                firstClick = false;
                                board[mover.fromRow][mover.fromColumn].setBackground(Color.green);
                            }

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
                            //should have highlights
                            firstClick = true;
                            mover.toRow = row;
                            mover.toColumn = col;
                            model.move(mover);

                            if((mover.fromColumn % 2 == 0 && mover.fromRow % 2 == 0)||(mover.fromColumn % 2 == 1 && mover.fromRow % 2 == 1))
                                board[mover.fromRow][mover.fromColumn].setBackground(Color.white);
                            else
                                board[mover.fromRow][mover.fromColumn].setBackground(Color.gray);

                            displayBoard();
                            if (model.isComplete()) {
                                JOptionPane.showMessageDialog(null, "Game Over");
                                for (int r = 0; row < model.getNumRows(); row++) {
                                    for (int c = 0; c < model.getNumCol(); col++) {
                                        //iCell = game.getCell(r, c);
                                        board[r][c].setEnabled(false);
                                    }
                                }
                                displayBoard();
                            }
                            return;
                        }
                    }
                }
            }
        }
    }
}

