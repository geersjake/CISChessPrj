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
        if (this.isFirstMove()) {
            if (super.isValidMove(move, board)) {
                if (move.toColumn == move.fromColumn) {
                    if (this.player() == Player.WHITE) {
                        if (move.toRow == move.fromRow + 1 || move.toRow == move.fromRow + 2) {
                            validMove = true;
                            this.setFirstMove(false);
                        }
                    } else if (this.player() == Player.BLACK) {
                        if (move.toRow == move.fromRow - 1 || move.toRow == move.fromRow - 2) {
                            validMove = true;
                            this.setFirstMove(false);
                        }
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