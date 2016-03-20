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
    private Move move = new Move();

    // declare other instance variables as needed

    private ButtonListener buttonListener = new ButtonListener();

    public ChessPanel() {

        model = new ChessModel();
        board = new JButton[8][8];


        center.setLayout(new GridLayout(model.getNumRows(),model.getNumCol()));

        for (int row = 0; row < model.getNumRows(); row++){
            for (int col = 0; col < model.getNumCol(); col++){
                board[row][col] = new JButton("");
                board[row][col].addActionListener(buttonListener);
                board[row][col].setPreferredSize(new Dimension(50,50));
                center.add(board[row][col]);
            }
        }

        //adding to content pane
        setLayout(new BorderLayout());
        add(new JLabel("Chess Game"), BorderLayout.NORTH);
        add(center, BorderLayout.CENTER);
        displayBoard();

    }

    private void displayBoard() {
        for (int row = 0; row < model.getNumRows(); row++) {
            for (int col = 0; col < model.getNumCol(); col++) {
                if (model.pieceAt(row, col) != null){
                    if (model.pieceAt(row, col).type().equals("Rook")){

                        if (model.pieceAt(row, col).player() == Player.WHITE){    // need to add icons still
                            board[row][col].setText("Rw");
                            board[row][col].setIcon(new ImageIcon());
                        }
                        if (model.pieceAt(row, col).player() == Player.BLACK){
                            board[row][col].setText("Rb");
                            board[row][col].setIcon(new ImageIcon());
                        }
                    }
                    if (model.pieceAt(row, col).type().equals("Knight")){
                        if (model.pieceAt(row, col).player() == Player.WHITE){
                            board[row][col].setText("Kw");
                            board[row][col].setIcon(new ImageIcon());
                        }
                        if (model.pieceAt(row, col).player() == Player.BLACK){
                            board[row][col].setText("Kb");
                            board[row][col].setIcon(new ImageIcon());
                        }
                    }
                    if (model.pieceAt(row, col).type().equals("Bishop")){
                        if (model.pieceAt(row, col).player() == Player.WHITE){
                            board[row][col].setText("Bw");
                            board[row][col].setIcon(new ImageIcon());

                        }
                        if (model.pieceAt(row, col).player() == Player.BLACK){
                            board[row][col].setText("Bb");
                            board[row][col].setIcon(new ImageIcon());
                        }
                    }
                    if (model.pieceAt(row, col).type().equals("King")){
                        if (model.pieceAt(row, col).player() == Player.WHITE){
                            board[row][col].setText("Kw");
                            board[row][col].setIcon(new ImageIcon());
                        }
                        if (model.pieceAt(row, col).player() == Player.BLACK){
                            board[row][col].setText("Kb");
                            board[row][col].setIcon(new ImageIcon());
                        }
                    }
                    if (model.pieceAt(row, col).type().equals("Queen")){
                        if (model.pieceAt(row, col).player() == Player.WHITE){
                            board[row][col].setText("Qw");
                            board[row][col].setIcon(new ImageIcon());
                        }
                        if (model.pieceAt(row, col).player() == Player.BLACK){
                            board[row][col].setText("Qb");
                            board[row][col].setIcon(new ImageIcon());
                        }
                    }
                    if (model.pieceAt(row, col).type().equals("Pawn")){
                        if (model.pieceAt(row, col).player() == Player.WHITE){
                            board[row][col].setText("Pw");
                            board[row][col].setIcon(new ImageIcon());
                        }
                        if (model.pieceAt(row, col).player() == Player.BLACK){
                            board[row][col].setText("Pb");
                            board[row][col].setIcon(new ImageIcon());
                        }
                    }
                }


            }


        }
    }




    // add other helper methods as needed

    // inner class that represents action listener for buttons
    private class ButtonListener implements ActionListener {
        JButton pieceToMoveButton = null;
        public void actionPerformed(ActionEvent event) {
            for (int row = 0; row < model.getNumRows(); row++) {
                for (int col = 0; col < model.getNumCol(); col++) {      //Need to figure out how to get move to work.
                    if (board[row][col] == event.getSource()){

                    }
                }

            }
        }
    }
}

