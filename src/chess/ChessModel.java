package chess;

/***********************************************************************
 * Plays Chess
 *
 * @author Jake, Tyler, Jonathan
 * @version 1.0
 **********************************************************************/

import javax.swing.*;

/**
 * Created by tylerfaulk on 2/29/16.
 */
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

    public boolean isComplete() {  //Need to make sure other pieces cant move through pieces before we're able to test if this works correctly
        Move testMove = new Move();
        boolean isCheckMate = true;
        Player testPlayer;
        if (inCheck(Player.WHITE)){
            testPlayer = Player.WHITE;
        }
        else{
            testPlayer = Player.BLACK;
        }

        for (int row = 0; row < 8; row++){
            for (int col = 0; col < 8; col++){
                if (board[row][col] != null && board[row][col].player() == testPlayer) {
                    testMove.fromRow = row;
                    testMove.fromColumn = col;
                    for (int row1 = 0; row1 < 8; row1++){
                        for (int col1 = 0; col1 < 8; col1++){
                            testMove.toRow = row1;
                            testMove.toColumn = col1;
                            if (isValidMove(testMove)){
                                board[testMove.toRow][testMove.toColumn] = board[testMove.fromRow][testMove.fromColumn];
                                board[testMove.fromRow][testMove.fromColumn] = null;
                                if (!inCheck(testPlayer)){
                                    isCheckMate = false;
                                }
                                board[testMove.fromRow][testMove.fromColumn] = board[testMove.toRow][testMove.toColumn];
                                board[testMove.toRow][testMove.toColumn] = null;
                            }
                        }
                    }
                }
            }
        }
   /*     if (isCheckMate == true){
            JOptionPane.showMessageDialog(null, "CheckMate. Game is over");
        }
        else{
            JOptionPane.showMessageDialog(null, "Keep going");
        }*/
        return isCheckMate;
    }

    public boolean isValidMove(Move move) {
        boolean validMove = false;

        if ((firstMove == true) && (board[move.fromRow][move.fromColumn].player() == Player.BLACK)) {
            JOptionPane.showMessageDialog(null, "Red Goes First");
            validMove = false; //not necessary
            firstMove = true;
        } else if (board[move.fromRow][move.fromColumn].isValidMove(move, board)) {
            validMove = true;
            firstMove = false;
        }
        return validMove;
    }

    public void move(Move move) {
        if (board[move.fromRow][move.fromColumn].player() == currentPlayer()) {
            if (isValidMove(move)) {
                if (!inCheck(currentPlayer())) {
                    board[move.toRow][move.toColumn] = board[move.fromRow][move.fromColumn];
                    board[move.fromRow][move.fromColumn] = null;
                    setPlayer(currentPlayer().next());
                    if (inCheck(Player.BLACK) == true) {
                        JOptionPane.showMessageDialog(null, " BLACK in check");
                    }
                    if (inCheck(Player.WHITE) == true) {
                        JOptionPane.showMessageDialog(null, "WHITE in check");
                    }
                }
                else {
                    board[move.toRow][move.toColumn] = board[move.fromRow][move.fromColumn];
                    board[move.fromRow][move.fromColumn] = null;
                    if(inCheck(currentPlayer())){
                        board[move.fromRow][move.fromColumn] = board[move.toRow][move.toColumn];
                        board[move.toRow][move.toColumn] = null;
                        JOptionPane.showMessageDialog(null, "You must move out of check");
                    }
                    else{
                        setPlayer(currentPlayer().next());
                    }
                }
            }
            else {
                JOptionPane.showMessageDialog(null, "This is not a valid move");
            }
        }
        else{
            JOptionPane.showMessageDialog(null, "It is " + currentPlayer() + " turn" );
        }
    }

    public boolean inCheck(Player p) {
        int[][] possibleMoves = new int[8][8]; // Array of Integers representing board and inCheck spaces.
        Move checkMove = new Move();
        boolean isKingInCheck = false;
        if(firstMove)
            return false;

        for (int row = 0; row < 8; row++){       //searches for pieces on board
            for ( int col = 0; col < 8; col++){
               // if (board[row][col])

                if (board[row][col] != null && board[row][col].player() != p) {  //looks for Piece that isn't owned by that person
                    checkMove.fromRow = row;  //sets a move starting location
                    checkMove.fromColumn = col;
                    for (int row1 = 0; row1 < 8; row1++) {  //scans every spot on board and checks if its a valid move
                        for (int col1 = 0; col1 < 8; col1++) {
                            checkMove.toRow = row1;
                            checkMove.toColumn = col1;
                            if (isValidMove(checkMove) == true) {
                                possibleMoves[row1][col1] = 1; //If enemy piece can move to that location, it is represented by a 1
                            }
                        }
                    }
                }
            }
        }

        for (int row2 = 0; row2 < 8; row2++){
            for ( int col2 = 0; col2 < 8; col2++) {
                if (board[row2][col2] != null){
                    if (board[row2][col2].type().equals("King") && board[row2][col2].player() == p){ //Find the King on the board.
                        if (possibleMoves[row2][col2] == 1){
                            isKingInCheck = true;
                        }
                    }
                }
            }
        }

        return isKingInCheck;
        // FIXME: 3/17/2016 step 9
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
