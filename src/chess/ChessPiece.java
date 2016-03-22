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
public abstract class ChessPiece implements IChessPiece {

    private Player owner;

    protected ChessPiece(Player player) {
        this.owner = player;
    }

    public abstract String type();

    public Player player() {
        return(owner); //should do the same as if statments if(owner == Player.Black) etc

    }

    public boolean isValidMove(Move move, IChessPiece[][] board) {
        boolean validMove = false;
        if(move.fromColumn != move.toColumn || move.toRow != move.fromRow){
            if (this == board[move.fromRow][move.fromColumn]) {
                try {

                    if (board[move.toRow][move.toColumn].player() == this.owner)
                        validMove = false;

                    else
                        validMove = true;

                } catch (NullPointerException e) {
                    validMove = true;
                }
            }
        }
        return validMove;

    }


}

