package chess;

/***********************************************************************
 *
 * King Class takes care of the the king logic
 *
 * @author Jake, Tyler, Jonathan
 * @version 1.0
 *
 **********************************************************************/

public class King extends ChessPiece {

/***********************************************************************
 *
 * Calls the super class which is ChessPiece sends player to the super
 *
 * @param  player that owns the piece being checked
 *
 **********************************************************************/

    protected King(Player player) {
        super(player);
    }

/***********************************************************************
 *
 * King a string with the word King in it
 *
 * @return Returns the type of piece which is King in string form
 *
 **********************************************************************/
    @Override
    public String type() {
        return "King";
    }

/***********************************************************************
 *
 * Checks if the move made by the king meets the required move
 * requirements. King can move all spaces around it
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
        if(super.isValidMove(move, board)){
          if (Math.abs(move.fromRow - move.toRow) == 0
                  || Math.abs(move.fromRow - move.toRow) == 1){
              if (Math.abs(move.fromColumn - move.toColumn) == 0
                      || Math.abs(move.fromColumn - move.toColumn) == 1){
                  validMove = true;
              }
                //Test
            }
        }



        return validMove;

    }
}
