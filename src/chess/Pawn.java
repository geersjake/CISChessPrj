package chess;

/***********************************************************************
 *
 * Plays Chess
 *
 * @author Jake, Tyler, Jonathan
 * @version 1.0
 * testing ignore
 *
 **********************************************************************/

import javax.swing.*;

/**
 * Created by tylerfaulk on 2/29/16.
 */
public class Pawn extends ChessPiece {

    protected Pawn(Player player) {
        super(player);
    }

    @Override
    public String type() {
        return "Pawn";
    }

    @Override
    public boolean isValidMove(Move move, IChessPiece[][] board) {
        boolean validMove = false;
        if (super.isValidMove(move, board)) {

            //if(model.pieceAt(move.fromRow, move.fromColumn).player() == Player.WHITE)
                if (move.fromRow == 1) {
                    if (move.toRow == move.fromRow + 2 || move.toRow == move.fromRow + 1)
                        validMove = true;
                }
                else {
                    if (move.toRow == move.fromRow + 1)
                        validMove = true;
                }
            //else
                if (move.fromRow == 6) {
                    if (move.toRow == move.fromRow - 2 || move.toRow == move.fromRow - 1)
                        validMove = true;
                }
                else {
                    if (move.toRow == move.fromRow - 1)
                        validMove = true;
                }

        }
        return validMove;

    }
}