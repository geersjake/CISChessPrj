package chess;

/***********************************************************************
 * Plays Chess
 *
 * @author Jake, Tyler, Jonathan
 * @version 1.0
 **********************************************************************/

/**
 * Created by tylerfaulk on 2/29/16.
 */
public class Pawn extends ChessPiece {

    protected Pawn(Player player) {
        super(player);
    }


    private boolean firstMove = true;

    @Override
    public String type() {
        return "Pawn";
    }


    @Override
    public boolean isValidMove(Move move, IChessPiece[][] board) {
        boolean validMove = false;
        if (super.isValidMove(move, board)) {
            if (board[move.fromRow][move.fromColumn].player() == Player.BLACK){
                if ((move.fromRow - move.toRow == 1) && move.fromColumn==move.toColumn && (board[move.toRow][move.toColumn] == null)) {
                    validMove = true;
                }
                if (move.fromRow - move.toRow == 1 && board[move.toRow][move.toColumn] != null) {
                    if (Math.abs(move.fromColumn - move.toColumn) == 1 && board[move.toRow][move.toColumn].player()
                            != board[move.fromRow][move.fromColumn].player()) {
                        validMove = true;
                    }
                }
                if ((move.fromRow - move.toRow == 2) && move.fromColumn==move.toColumn && (board[move.toRow][move.toColumn] == null)){
                    if (move.fromRow == 6) {
                        validMove = true;
                    }
                }
            }
            if (board[move.fromRow][move.fromColumn].player() == Player.WHITE){
                if ((move.fromRow - move.toRow == -1) && move.fromColumn==move.toColumn && (board[move.toRow][move.toColumn] == null)) {
                    validMove = true;
                }
                if (move.fromRow - move.toRow == -1 && board[move.toRow][move.toColumn] != null) {
                   if (Math.abs(move.fromColumn - move.toColumn) == 1 && board[move.toRow][move.toColumn].player()
                           != board[move.fromRow][move.fromColumn].player()) {
                       validMove = true;
                   }
                }
                if ((move.fromRow - move.toRow == -2) && move.fromColumn==move.toColumn && (board[move.toRow][move.toColumn] == null)){
                    if (move.fromRow == 1){
                        validMove = true;
                    }
                }
            }
        }

        return validMove;
    }

    public boolean isFirstMove() {
        return firstMove;
    }

    public void setFirstMove(boolean firstMove) {
        this.firstMove = firstMove;
    }
}