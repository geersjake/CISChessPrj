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

            int tempCol = move.fromColumn;
            int tempRow = move.fromRow;
            while((tempRow != move.toRow) || (tempCol != move.toColumn)){

                if(tempCol > move.toColumn && tempRow == move.toRow
                        && tempCol <= 7 && tempCol >= 0)
                    tempCol --;

                if(tempCol < move.toColumn && tempRow == move.toRow
                        && tempCol <= 7 && tempCol >= 0)
                    tempCol ++;

                if(tempRow > move.toRow && tempCol == move.toColumn
                        && tempRow <= 7 && tempRow >= 0)
                    tempRow --;

                if(tempRow < move.toRow && tempCol == move.toColumn
                        && tempCol <= 7 && tempCol >= 0)
                    tempRow ++;

                if(tempRow > move.toRow && tempCol > move.toColumn
                        && tempCol <= 7 && tempCol >= 0 && tempRow <= 7 && tempRow >= 0) {
                    tempCol--;
                    tempRow--;
                }

                if(tempRow < move.toRow && tempCol < move.toColumn
                        && tempCol <= 7 && tempCol >= 0 && tempRow <= 7 && tempRow >= 0) {
                    tempCol++;
                    tempRow++;
                }

                if(tempRow > move.toRow && tempCol < move.toColumn
                        && tempCol <= 7 && tempCol >= 0 && tempRow <= 7 && tempRow >= 0) {
                    tempRow--;
                    tempCol ++;
                }

                if(tempRow < move.toRow && tempCol > move.toColumn
                        && tempCol <= 7 && tempCol >= 0 && tempRow <= 7 && tempRow >= 0) {
                    tempRow++;
                    tempCol--;
                }

                if(!(tempCol == move.toColumn && tempRow == move.toRow)) {
                    if (board[tempRow][tempCol] != null)
                        validMove = false;
                }

            }
        }
        return validMove;
    }
}