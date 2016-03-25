package chess;

/***********************************************************************
 *
 * Pawn Class takes care of the the Pawn logic
 *
 * @author Jake, Tyler, Jonathan
 * @version 1.0
 *
 **********************************************************************/

public class Pawn extends ChessPiece {

/***********************************************************************
 *
 * Calls the super class which is ChessPiece sends player to the super
 *
 * @param  player that owns the piece being checked
 *
 **********************************************************************/

    protected Pawn(Player player) {
        super(player);
    }

/***********************************************************************
 *
 * King a string with the word Pawn in it
 *
 * @return Returns the type of piece which is Pawn in string form
 *
 **********************************************************************/
    @Override
    public String type() {
        return "Pawn";
    }

/***********************************************************************
 *
 * Checks if the move made by the Pawn meets the required move
 * requirements. Pawn can move one space unless it is its first move
 * and can take diagonally
 *
 * @return true or false depending on whether or not the move
 * given is valid
 * @param move where the piece is and were it is going
 * @param board the place on the board where the piece is
 *
 **********************************************************************/

    @Override
    public boolean isValidMove(Move move, IChessPiece[][] board) {
        boolean validMove = false;


        if (super.isValidMove(move, board)) {
            if (board[move.fromRow][move.fromColumn].player()
                    == Player.BLACK){
                if ((move.fromRow - move.toRow == 1)
                        && move.fromColumn == move.toColumn
                        && (board[move.toRow][move.toColumn] == null)) {
                    validMove = true;
                }
                if (move.fromRow - move.toRow == 1
                        && board[move.toRow][move.toColumn] != null) {
                    if (Math.abs(move.fromColumn - move.toColumn) == 1
                            && board[move.toRow][move.toColumn].player()
                            != board[move.fromRow][move.fromColumn].player()) {
                        validMove = true;
                    }
                }
                if ((move.fromRow - move.toRow == 2) && move.fromColumn == move.toColumn
                        && (board[move.toRow][move.toColumn] == null)){
                    if (move.fromRow == 6) {
                        validMove = true;
                    }
                }
            }
            if (board[move.fromRow][move.fromColumn].player() == Player.WHITE){
                if ((move.fromRow - move.toRow == -1) && move.fromColumn
                        == move.toColumn && (board[move.toRow][move.toColumn] == null)) {
                    validMove = true;
                }
                if (move.fromRow - move.toRow == -1
                        && board[move.toRow][move.toColumn] != null) {
                   if (Math.abs(move.fromColumn - move.toColumn)
                           == 1 && board[move.toRow][move.toColumn].player()
                           != board[move.fromRow][move.fromColumn].player()) {
                       validMove = true;
                   }
                }
                if ((move.fromRow - move.toRow == -2) && move.fromColumn
                        == move.toColumn && (board[move.toRow][move.toColumn] == null)){
                    if (move.fromRow == 1){
                        validMove = true;
                    }
                }
            }
        }

        return validMove;
    }
}