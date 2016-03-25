package chess;

import javax.swing.*;
import java.util.ArrayList;

/***********************************************************************
 * Has all the logic for the Model and where the pieces are
 * and other logic for chess to play like check and checkmate
 *
 * @author Jake, Tyler, Jonathan
 * @version 1.0
 **********************************************************************/

public class ChessModel implements IChessModel {
    //The 8x8 board that is made for chess
    private IChessPiece[][] board;
    //The player that is playing White or Black
    private Player player;
    //height of board
    private int numRows;
    //width of board
    private int numCol;
    //used to let white go first
    private boolean firstMove = true;
    //used in castle to keep track if the white king can castle with left
    public boolean ableToCastleWhite00;
    //used in castle to keep track if the black king can castle with left
    public boolean ableToCastleBlack70;
    //used in castle to keep track if the white king can castle with right
    public boolean ableToCastleWhite07;
    //used in castle to keep track if the black king can castle with right
    public boolean ableToCastleBlack77;
    //place holders for dead white pieces
    private ArrayList<IChessPiece> deadWhite;
    //place holders for dead black pieces
    private ArrayList<IChessPiece> deadBlack;


    /******************************************************************
     * Sets the pieces to the right at the start of a game
     *****************************************************************/

    public ChessModel() {

        firstMove = true;
        ableToCastleWhite00 = true;
        ableToCastleBlack70 = true;
        ableToCastleWhite07 = true;
        ableToCastleBlack77 = true;

        numRows = 8;
        numCol = 8;
        player = Player.WHITE; //changed to white
        //Instantiate player
        board = new IChessPiece[8][8];

        // TODO: 3/25/2016
        deadBlack = new ArrayList<>();
        deadWhite = new ArrayList<>();

        // adding all of the pieces onto the board
        board[0][0] = new Rook(player.WHITE);
        board[0][1] = new Knight(player.WHITE);
        board[0][2] = new Bishop(player.WHITE);
        board[0][4] = new Queen(player.WHITE);
        board[0][3] = new King(player.WHITE);
        board[0][5] = new Bishop(player.WHITE);
        board[0][6] = new Knight(player.WHITE);
        board[0][7] = new Rook(player.WHITE);

        board[7][0] = new Rook(player.BLACK);
        board[7][1] = new Knight(player.BLACK);
        board[7][2] = new Bishop(player.BLACK);
        board[7][4] = new Queen(player.BLACK);
        board[7][3] = new King(player.BLACK);
        board[7][5] = new Bishop(player.BLACK);
        board[7][6] = new Knight(player.BLACK);
        board[7][7] = new Rook(player.BLACK);

        for (int col = 0; col < numCol; col++) {
            board[6][col] = new Pawn(player.BLACK);
            board[1][col] = new Pawn(player.WHITE);
        }


    }

    /******************************************************************
     * Has all the GUI code that makes and updates the GUI for the player
     *****************************************************************/

    public boolean isComplete() {
        Move testMove = new Move();
        boolean isCheckMate = true;
        IChessPiece[][] testBoard = new IChessPiece[8][8];
        Player testPlayer;
        if (inCheck(Player.WHITE)) {
            testPlayer = Player.WHITE; //setting testplayer color
        } else {
            testPlayer = Player.BLACK;
        }

        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                if (board[row][col] != null && board[row][col].player() == testPlayer) {
                    testMove.fromRow = row; //setting testMove
                    testMove.fromColumn = col;
                    for (int row1 = 0; row1 < 8; row1++) {
                        for (int col1 = 0; col1 < 8; col1++) {
                            testMove.toRow = row1; //testing different positions
                            testMove.toColumn = col1;
                            if (isValidMove(testMove)) {
                                if (board[testMove.toRow][testMove.toColumn] != null) {
                                    testBoard[testMove.toRow][testMove.toColumn] =
                                            board[testMove.toRow][testMove.toColumn];

                                    board[testMove.toRow][testMove.toColumn] =
                                            board[testMove.fromRow][testMove.fromColumn];
                                    board[testMove.fromRow][testMove.fromColumn] = null;
                                    if (!inCheck(testPlayer)) { //determining if moving out is possible
                                        isCheckMate = false;

                                    }
                                    board[testMove.fromRow][testMove.fromColumn] =
                                            board[testMove.toRow][testMove.toColumn];
                                    board[testMove.toRow][testMove.toColumn] =
                                            testBoard[testMove.toRow][testMove.toColumn];
                                } else {
                                    board[testMove.toRow][testMove.toColumn] =
                                            board[testMove.fromRow][testMove.fromColumn];
                                    board[testMove.fromRow][testMove.fromColumn] = null;
                                    if (!inCheck(testPlayer)) { //determining if test found an option
                                        isCheckMate = false;
                                    }
                                    board[testMove.fromRow][testMove.fromColumn] =
                                            board[testMove.toRow][testMove.toColumn];
                                    board[testMove.toRow][testMove.toColumn] = null;
                                }

                            }
                        }
                    }
                }
            }
        }
        if (isCheckMate == true) {
            JOptionPane.showMessageDialog(null, "CheckMate. Game is over");
        } else {
            JOptionPane.showMessageDialog(null, "Keep going");
        }
        return isCheckMate;
    }

    /******************************************************************
     * Has all the GUI code that makes and updates the GUI for the player
     *****************************************************************/

    public boolean isValidMove(Move move) {
        boolean validMove = false;

        if ((firstMove == true) &&
                (board[move.fromRow][move.fromColumn].player() == Player.BLACK)) {
            JOptionPane.showMessageDialog(null, "Red Goes First");
            validMove = false; //not necessary
            firstMove = true;
        } else if (board[move.fromRow][move.fromColumn].isValidMove(move, board)) {
            validMove = true;
            firstMove = false;
        }
        return validMove;
    }

    /******************************************************************
     * Has all the GUI code that makes and updates the GUI for the player
     *****************************************************************/

    public void move(Move move) {
        IChessPiece[][] testBoard = new IChessPiece[8][8];
        if (board[move.fromRow][move.fromColumn].player() == currentPlayer()) {
            if (isValidMove(move)) {
                if (board[move.fromRow][move.fromColumn].type().equals("Pawn")//chekcing if pawn reached end
                        && board[move.fromRow][move.fromColumn].player() ==
                        Player.BLACK && move.toRow == 0) {
                    if (deadBlack.size() > 0) {
                        JOptionPane.showMessageDialog(null, "Your Best Piece Will Be Added Back");
                        ArrayList<String> names = new ArrayList<>();
                        for (int i = deadBlack.size() - 1; i >= 0; i--) {
                            names.add(deadBlack.get(i).type()); //creates a string array to seach for names
                            deadBlack.remove(i);//removes from dead black list
                        }
                        if (!names.isEmpty()) {
                            if (names.contains("Queen")) { //searching for best piece
                                board[move.toRow][move.toColumn] = new Queen(Player.BLACK);
                                names.remove("Queen");
                            } else if (names.contains("Rook")) {
                                board[move.toRow][move.toColumn] = new Rook(Player.BLACK);
                                names.remove("Rook");
                            } else if (names.contains("Bishop")) {
                                board[move.toRow][move.toColumn] = new Bishop(Player.BLACK);
                                names.remove("Bishop");
                            } else if (names.contains("Knight")) {
                                board[move.toRow][move.toColumn] = new Knight(Player.BLACK);
                                names.remove("Knight");
                            } else if (names.contains("Pawn")) {
                                board[move.toRow][move.toColumn] = new Pawn(Player.BLACK);
                                names.remove("Pawn");
                            }
                        }
                        board[move.fromRow][move.fromColumn] = null;
                        setPlayer(currentPlayer().next());
                        return;
                    }
                }
                if (board[move.fromRow][move.fromColumn].type().equals("Pawn")
                        && board[move.fromRow][move.fromColumn].player() ==
                        Player.WHITE && move.toRow == 7) {
                    if (deadWhite.size() > 0) {
                        JOptionPane.showMessageDialog(null, "Your Best Piece Will Be Added Back");
                        ArrayList<String> names2 = new ArrayList<>();
                        for (int i = deadWhite.size() - 1; i >= 0; i--) {
                            names2.add(deadWhite.get(i).type()); //creates a string array to seach for names
                            deadWhite.remove(i);//removes from dead black list
                        }
                        if (!names2.isEmpty()) {
                            if (names2.contains("Queen")) { //searching for best piece
                                board[move.toRow][move.toColumn] = new Queen(Player.WHITE);
                                names2.remove("Queen");
                            } else if (names2.contains("Rook")) {
                                board[move.toRow][move.toColumn] = new Rook(Player.WHITE);
                                names2.remove("Rook");
                            } else if (names2.contains("Bishop")) {
                                board[move.toRow][move.toColumn] = new Bishop(Player.WHITE);
                                names2.remove("Bishop");
                            } else if (names2.contains("Knight")) {
                                board[move.toRow][move.toColumn] = new Knight(Player.WHITE);
                                names2.remove("Knight");
                            } else if (names2.contains("Pawn")) {
                                board[move.toRow][move.toColumn] = new Pawn(Player.WHITE);
                                names2.remove("Pawn");
                            }
                        }
                        board[move.fromRow][move.fromColumn] = null; //setting cell to null
                        setPlayer(currentPlayer().next());
                        return;
                    }
                }
                if (!inCheck(currentPlayer())) { //determining if player is in check
                    if (board[move.toRow][move.toColumn] != null) {
                        testBoard[move.toRow][move.toColumn] = board[move.toRow][move.toColumn];
                        if (currentPlayer() == Player.BLACK) {
                            deadWhite.add(board[move.toRow][move.toColumn]);
                        } else if (currentPlayer() == Player.WHITE) {
                            deadBlack.add(board[move.toRow][move.toColumn]);
                        }
                    }
                    board[move.toRow][move.toColumn] = board[move.fromRow][move.fromColumn];
                    board[move.fromRow][move.fromColumn] = null; //setting to null
                    if (inCheck(currentPlayer())) {
                        board[move.fromRow][move.fromColumn] = board[move.toRow][move.toColumn];
                        board[move.toRow][move.toColumn] = testBoard[move.toRow][move.toColumn];
                        JOptionPane.showMessageDialog(null,
                                "You are not allowed to move into check!");
                    } else {
                        setPlayer(currentPlayer().next());
                    }
                } else {
                    if (board[move.toRow][move.toColumn] != null) {
                        testBoard[move.toRow][move.toColumn] = board[move.toRow][move.toColumn];
                    }
                    board[move.toRow][move.toColumn] = board[move.fromRow][move.fromColumn];
                    board[move.fromRow][move.fromColumn] = null;
                    if (inCheck(currentPlayer())) {
                        board[move.fromRow][move.fromColumn] = board[move.toRow][move.toColumn];
                        board[move.toRow][move.toColumn] = testBoard[move.toRow][move.toColumn];
                        JOptionPane.showMessageDialog(null, "You must move out of check");
                    }
                    if (!inCheck(currentPlayer())) {
                        setPlayer(currentPlayer().next());

                    }
                }
            } else {
                JOptionPane.showMessageDialog(null, "This is not a valid move");
            }
        } else {
            JOptionPane.showMessageDialog(null, "It is " + currentPlayer() + " turn");
        }
    }

    /******************************************************************
     * Has all the GUI code that makes and updates the GUI for the player
     *****************************************************************/

    public boolean inCheck(Player p) {
        int[][] possibleMoves = new int[8][8]; // Array of Integers representing board
        Move checkMove = new Move();
        boolean isKingInCheck = false;
        if (firstMove)
            return false;

        for (int row = 0; row < 8; row++) {       //searches for pieces on board
            for (int col = 0; col < 8; col++) {
                // if (board[row][col])

                if (board[row][col] != null && board[row][col].player() != p) {
                    //looks for Piece that isn't owned by that person
                    checkMove.fromRow = row;  //sets a move starting location
                    checkMove.fromColumn = col;
                    for (int row1 = 0; row1 < 8; row1++) {
                        //scans every spot on board and checks if its a valid move
                        for (int col1 = 0; col1 < 8; col1++) {
                            checkMove.toRow = row1;
                            checkMove.toColumn = col1;
                            if (isValidMove(checkMove) == true) {
                                possibleMoves[row1][col1] = 1;
                                //If enemy piece can move to that location, it is
                                // represented by a 1
                            }
                        }
                    }
                }
            }
        }

        for (int row2 = 0; row2 < 8; row2++) {
            for (int col2 = 0; col2 < 8; col2++) {
                if (board[row2][col2] != null) {
                    if (board[row2][col2].type().equals("King") &&
                            board[row2][col2].player() == p) { //Find the King on the board.
                        if (possibleMoves[row2][col2] == 1) {
                            isKingInCheck = true;
                        }
                    }
                }
            }
        }

        return isKingInCheck;
    }

    /******************************************************************
     * Has all the GUI code that makes and updates the GUI for the player
     ********************************************************************/

    public void castleMoveTracking() {

        if (!board[0][0].type().equals("Rook")) //tracking movement
            ableToCastleWhite00 = false;
        if (!board[0][7].type().equals("Rook"))
            ableToCastleWhite07 = false;
        if (!board[7][0].type().equals("Rook"))
            ableToCastleBlack70 = false;
        if (!board[7][7].type().equals("Rook"))
            ableToCastleBlack77 = false;

        if (!board[0][4].type().equals("King")) {
            ableToCastleWhite07 = false;
            ableToCastleWhite07 = false;
        }
        if (!board[7][4].type().equals("King")) {
            ableToCastleBlack70 = false;
            ableToCastleBlack77 = false;
        }

    }

    /******************************************************************
     * Has all the GUI code that makes and updates the GUI for the player
     *****************************************************************/

    public void Castle(int side) {
        boolean swap = false;
        if (!inCheck(currentPlayer())) { //determining if player is incheck
            if (side == 0) {
                if (getPlayer() == Player.WHITE && ableToCastleWhite00 == true) {
                    if (board[0][1] == null && board[0][2] == null && board[0][3] == null) {
                        board[0][3] = board[0][0]; //swaping king and rook places
                        board[0][2] = board[0][4];
                        board[0][0] = null;
                        board[0][4] = null;
                        ableToCastleWhite07 = false;
                        ableToCastleWhite00 = false;
                        swap = true;
                    }
                } else if (ableToCastleBlack70 == true) {
                    if (board[7][1] == null && board[7][2] == null && board[7][3] == null) {
                        board[7][3] = board[7][0];
                        board[7][2] = board[7][4];
                        board[7][4] = null;
                        board[7][0] = null;
                        ableToCastleBlack70 = false;
                        ableToCastleBlack77 = false;
                        swap = true;
                    }
                }
            }
            if (side == 1) {
                if (getPlayer() == Player.WHITE && ableToCastleWhite07 == true) {
                    if (board[0][6] == null && board[0][5] == null) {
                        board[0][5] = board[0][7];
                        board[0][6] = board[0][4];//swaping rook and castle places
                        board[0][7] = null;
                        board[0][4] = null;
                        ableToCastleWhite07 = false;
                        ableToCastleWhite00 = false;
                        swap = true;
                    }
                } else if (ableToCastleBlack77 == true) {
                    if (board[7][6] == null && board[7][5] == null) {
                        board[7][5] = board[7][7];
                        board[7][6] = board[7][4];
                        board[7][4] = null;
                        board[7][7] = null;
                        ableToCastleBlack70 = false;
                        ableToCastleBlack77 = false;
                        swap = true;
                    }
                }
            }
        }
        if (swap)
            setPlayer(currentPlayer().next());

    }

    /******************************************************************
     * Has all the GUI code that makes and updates the GUI for the player
     *****************************************************************/

    public void newGame() {
        firstMove = true;
        ableToCastleWhite00 = true; //reseting variables
        ableToCastleBlack70 = true;
        ableToCastleWhite07 = true;
        ableToCastleBlack77 = true;

        numRows = 8;
        numCol = 8;
        player = Player.WHITE; //changed to white
        //Instantiate player
        board = new IChessPiece[8][8];

        // adding all of the pieces onto the board
        board[0][0] = new Rook(player.WHITE);
        board[0][1] = new Knight(player.WHITE);
        board[0][2] = new Bishop(player.WHITE);
        board[0][3] = new Queen(player.WHITE);
        board[0][4] = new King(player.WHITE);
        board[0][5] = new Bishop(player.WHITE);
        board[0][6] = new Knight(player.WHITE);
        board[0][7] = new Rook(player.WHITE);

        board[7][0] = new Rook(player.BLACK);
        board[7][1] = new Knight(player.BLACK);
        board[7][2] = new Bishop(player.BLACK);
        board[7][3] = new Queen(player.BLACK);
        board[7][4] = new King(player.BLACK);
        board[7][5] = new Bishop(player.BLACK);
        board[7][6] = new Knight(player.BLACK);
        board[7][7] = new Rook(player.BLACK);

        for (int col = 0; col < numCol; col++) {
            board[6][col] = new Pawn(player.BLACK);
            board[1][col] = new Pawn(player.WHITE);
        }


    }

    /******************************************************************
     * Has all the GUI code that makes and updates the GUI for the player
     *****************************************************************/

    public Player currentPlayer() {
        return player;
    }

    /******************************************************************
     * Has all the GUI code that makes and updates the GUI for the player
     *****************************************************************/

    public int numRows() {
        return numRows;
    }

    /******************************************************************
     * Has all the GUI code that makes and updates the GUI for the player
     *****************************************************************/

    public int numColumns() {
        return numCol;
    }

    /******************************************************************
     * Has all the GUI code that makes and updates the GUI for the player
     *****************************************************************/

    public Player getPlayer() {
        return player; // same as currentPlayer()
    }

    /******************************************************************
     * Has all the GUI code that makes and updates the GUI for the player
     *****************************************************************/

    public void setPlayer(Player player) {
        this.player = player;
    }

    /******************************************************************
     * Has all the GUI code that makes and updates the GUI
     * for the player
     *****************************************************************/

    public int getNumRows() {
        return numRows;
    }

    /******************************************************************
     * Has all the GUI code that makes and updates the GUI for the player
     *****************************************************************/

    public void setNumRows(int numRows) {
        this.numRows = numRows;
    }

    /******************************************************************
     * Has all the GUI code that makes and updates the GUI for the player
     *****************************************************************/

    public int getNumCol() {
        return numCol;
    }

    /******************************************************************
     * Has all the GUI code that makes and updates the GUI for the player
     *****************************************************************/

    public void setNumCol(int numCol) {
        this.numCol = numCol;
    }

    /******************************************************************
     * Has all the GUI code that makes and updates the GUI for the player
     *****************************************************************/

    public IChessPiece pieceAt(int row, int col) {
        return board[row][col];
    }

    /******************************************************************
     * Has all the GUI code that makes and updates the GUI for the player
     *****************************************************************/

    public IChessPiece[][] getBoard() {
        return board;
    }

    /******************************************************************
     * Has all the GUI code that makes and updates the GUI for the player
     *****************************************************************/

    public void setBoard(IChessPiece[][] board) {
        this.board = board;
    }

}
