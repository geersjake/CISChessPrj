package chess;

/***********************************************************************
 *
 * Plays Chess
 *
 * @author Jake, Tyler, Jonathan
 * @version 1.0
 *
 **********************************************************************/

/**
 * Created by tylerfaulk on 2/29/16.
 */
public class King extends ChessPiece {
    protected King(Player player) {
        super(player);
    }

    @Override
    public String type() {
        return "King";
    }

    @Override
    public boolean isValidMove(Move move, IChessPiece[][] board) {
        boolean validMove = false;
        if(super.isValidMove(move, board)){
          if (Math.abs(move.fromRow - move.toRow) == 0 || Math.abs(move.fromRow - move.toRow) == 1){
              if (Math.abs(move.fromColumn - move.toColumn) == 0 || Math.abs(move.fromColumn - move.toColumn) == 1){
                  validMove = true;
              }
                //Test
            }

        }
        return validMove;
    }
}
