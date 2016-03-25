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
public class Rook extends ChessPiece {
    protected Rook(Player player) {
        super(player);
    }

    @Override
    public String type() {
        return "Rook";
    }

    @Override
    public boolean isValidMove(Move move, IChessPiece[][] board) {
        boolean validMove = false;
        if(super.isValidMove(move, board)){
            if(move.toRow == move.fromRow || move.toColumn == move.fromColumn){
                validMove = true;
            }

            int tempCol = move.fromColumn;
            int tempRow = move.fromRow;

            while((tempRow != move.toRow) || (tempCol != move.toColumn)){
                if(tempCol > move.toColumn && tempCol <= 7 && tempCol >= 0)
                    tempCol --;
                else if(tempCol < move.toColumn &&tempCol <= 7 && tempCol >= 0)
                    tempCol ++;

                if(tempRow > move.toRow && tempRow <= 7 && tempRow >= 0)
                    tempRow --;
                else if(tempRow < move.toRow && tempCol <= 7 && tempCol >= 0)
                    tempRow ++;
                if(!(tempCol == move.toColumn && tempRow == move.toRow)) {
                    if (board[tempRow][tempCol] != null)
                        validMove = false;
                }
            }
        }
        return validMove;
    }
}