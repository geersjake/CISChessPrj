package chess;

/***********************************************************************
 * Plays Chess
 *
 * @author Jake, Tyler, Jonathan
 * @version 1.0
 **********************************************************************/

import javax.swing.*;

public class ChessModel implements IChessModel {
    private IChessPiece[][] board;
    private Player player;
    private int numRows; //height of board
    private int numCol; // width of board
    private boolean firstMove = true; //used to let white go first


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
            firstMove = false;
        } else if (board[move.fromRow][move.fromColumn].isValidMove(move, board)) {
            validMove = true;
            firstMove = false;
        }
        return validMove;
    }

    public void move(Move move) {

        if (isValidMove(move)) {
            board[move.toRow][move.toColumn] = board[move.fromRow][move.fromColumn];
            board[move.fromRow][move.fromColumn] = null;
            //JOptionPane.showMessageDialog(null, "Valid Move");
        } else {
            JOptionPane.showMessageDialog(null, "This is not a valid move");
        }
    }


    public boolean inCheck(Player p) {
        this.isComplete();
        //for()
            //for()
                //if()
                    return true;

        //return false;
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
