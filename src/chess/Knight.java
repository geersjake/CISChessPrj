package chess;

/***********************************************************************
 *
 * Knight Class takes care of the the knight logic
 *
 * @author Jake, Tyler, Jonathan
 * @version 1.0
 *
 **********************************************************************/
public class Knight extends ChessPiece {

/***********************************************************************
 *
 * Calls the super class which is ChessPiece sends player to the super
 *
 * @param  player that owns the piece being checked
 *
 **********************************************************************/

    protected Knight(Player player) {
        super(player);
    }

/***********************************************************************
 *
 * Knight a string with the word Knight in it
 *
 * @return Returns the type of piece which is Knight in string form
 *
 **********************************************************************/

    @Override
    public String type() {
        return "Knight";
    }

/***********************************************************************
 *
 * Checks if the move made by the Knight meets the required move
 * requirements. Knight can move in an L
 *
 * @return true or false depending on whether or not the move
 * given is valid
 * @param move where the piece is and were it is going
 * @param board the place on the board where the piece is
 *
 **********************************************************************/

    @Override
    public boolean isValidMove(Move move, IChessPiece[][] board) {
        int changeRow;
        int changeCol;
        boolean validMove = false;

        //Checks change in col and row to see if it is a valid move
        changeRow = Math.abs(move.fromRow - move.toRow);
        changeCol = Math.abs(move.fromColumn - move.toColumn);
        if (super.isValidMove(move, board)){
            if (changeRow == 1 || changeRow == 2){
                if (changeCol == 1 || changeCol == 2) {
                    if (changeRow != changeCol){
                        validMove = true;
                    }
                }

            }
        }

        //fix in step 8
        return validMove;
    }
}
