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
public class Queen extends ChessPiece {
    protected Queen(Player player) {
        super(player);
    }

    @Override
    public String type() {
        return "Queen";
    }

    @Override
    public boolean isValidMove(Move move, IChessPiece[][] board) {
        boolean validMove = false;
        if(super.isValidMove(move, board)){
                if(move.toRow == move.fromRow || move.toColumn == move.fromColumn){
                    validMove = true;
                }
                if(Math.abs(move.fromRow - move.toRow) == Math.abs(move.fromColumn - move.toColumn)){
                    validMove = true;
                }

            //Needs proper row and col
        }
        return validMove;
    }
}
