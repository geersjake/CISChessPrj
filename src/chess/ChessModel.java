package chess;

/***********************************************************************
 * Plays Chess
 *
 * @author Jake, Tyler, Jonathan
 * @version 1.0
 **********************************************************************/

import javax.swing.*;
import java.util.ArrayList;

public class ChessModel implements IChessModel {
    private IChessPiece[][] board;
    private Player player;
    private int numRows; //height of board
    private int numCol; // width of board
    private boolean firstMove = true; //used to let white go first
    private ArrayList theDead;



    public ChessModel() {
        numRows = 8;
        numCol = 8;
        player = Player.WHITE; //changed to white
        //Instantiate player
        board = new IChessPiece[8][8];

        board[0][0] = new Rook(player.WHITE);     // adding all of the pieces onto the board
        board[0][1] = new Knight(player.WHITE);
        board[0][2] = new Bishop(player.WHITE);
        board[0][3] = new King(player.WHITE);
        board[0][4] = new Queen(player.WHITE);
        board[0][5] = new Bishop(player.WHITE);
        board[0][6] = new Knight(player.WHITE);
        board[0][7] = new Rook(player.WHITE);

        board[7][0] = new Rook(player.BLACK);
        board[7][1] = new Knight(player.BLACK);
        board[7][2] = new Bishop(player.BLACK);
        board[7][3] = new King(player.BLACK);
        board[7][4] = new Queen(player.BLACK);
        board[7][5] = new Bishop(player.BLACK);
        board[7][6] = new Knight(player.BLACK);
        board[7][7] = new Rook(player.BLACK);

        for (int col = 0; col < numCol; col++) {
            board[6][col] = new Pawn(player.BLACK);
            board[1][col] = new Pawn(player.WHITE);
        }

        ArrayList<IChessPiece> dead = new ArrayList<>();

    }

    public boolean isComplete() {


        return false;
        // FIXME: 3/17/2016 step 10
    }

    public boolean isValidMove(Move move) {
        boolean validMove = false;

        if ((firstMove == true) && (board[move.fromRow][move.fromColumn].player() == Player.BLACK)) {
            JOptionPane.showMessageDialog(null, "Red Goes First");
            validMove = false; //not necessary
            firstMove = true;
        }else if ((board[move.fromRow][move.fromColumn].isValidMove(move, board))
                && (board[move.fromRow][move.fromColumn].player() == getPlayer())) {
            validMove = true;
            firstMove = false;
        }

        return validMove;
    }

    public void move(Move move) {

        if(inCheck(currentPlayer()))
            JOptionPane.showMessageDialog(null, "In Check");

        if (isValidMove(move)) {

            if((Player.BLACK == getPlayer()) ) {
                setPlayer(Player.WHITE);
            }
            else {
                setPlayer(Player.BLACK);
            }


            board[move.toRow][move.toColumn] = board[move.fromRow][move.fromColumn];
            board[move.fromRow][move.fromColumn] = null;

            //if(pieceAt(move.toRow, move.toColumn) != null)
                //theDead.add(pieceAt(move.toRow, move.toColumn));

            if(board[move.fromRow][move.fromColumn].type().equals("Pawn")) {
                if (move.fromRow == 0 || move.fromRow == 7)
                    this.promotion(move);
            }

        } else {
            if(board[move.fromRow][move.fromColumn].player() != getPlayer())
                JOptionPane.showMessageDialog(null, "It is " + getPlayer() + " turn.");
            else
                JOptionPane.showMessageDialog(null, "This is not a valid move");
        }
    }


    public boolean inCheck(Player p) {
        int[][] possibleMoves = new int[8][8]; // Array of Integers representing board and available moves
        // to help determine check. "0" will represent no possible moves
        Move checkMove = new Move();                                   // and "1" will represent possible move. Just Theorizing right now...

        if (firstMove)
            return false;

        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                // if (board[row][col])

                if (board[row][col] != null && board[row][col].player() != currentPlayer()) {  //find rook possible moves
                    checkMove.fromRow = row;
                    checkMove.fromColumn = col;

                    for (int row1 = 0; row1 < 8; row1++) {
                        for (int col1 = 0; col1 < 8; col1++) {
                            checkMove.toRow = row1;
                            checkMove.toColumn = col1;
                            if (isValidMove(checkMove) == true) {

                                possibleMoves[row1][col1] = 1;

                            }
                        }
                    }
                }
            }
        }
        for (int row2 = 0; row2 < 8; row2++) {
            for (int col2 = 0; col2 < 8; col2++) {
                if (board[row2][col2].type().equals("King") && board[row2][col2].player() == currentPlayer()) {
                    if (possibleMoves[row2][col2] == 1)
                        return true;
                    else
                        return false;
                }
            }
        }
        return false;
    }

    public void promotion (Move move){

        //if(theDead == null)
            if(getPlayer() == Player.WHITE)
                board[move.toRow][move.toColumn] = new Queen(player.WHITE);
            else
                board[move.toRow][move.toColumn] = new Queen(player.BLACK);

    }


    public Player currentPlayer() {
        return player;
    }

    public int numRows() {
        return numRows;
    }

    public int numColumns() {
        return numCol;
    }

    public Player getPlayer() {
        return player; // same as currentPlayer()
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public int getNumRows() {
        return numRows;
    }

    public void setNumRows(int numRows) {
        this.numRows = numRows;
    }

    public int getNumCol() {
        return numCol;
    }

    public void setNumCol(int numCol) {
        this.numCol = numCol;
    }

    public IChessPiece pieceAt(int row, int col) {
        return board[row][col];

    }

    public IChessPiece[][] getBoard() {
        return board;
    }

    public void setBoard(IChessPiece[][] board) {
        this.board = board;
    }

}
