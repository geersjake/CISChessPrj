package chess;

/***********************************************************************
 *
 * Rook Class takes care of the the Rook logic
 *
 * @author Jake, Tyler, Jonathan
 * @version 1.0
 *
 **********************************************************************/
public class Rook extends ChessPiece {

/***********************************************************************
 *
 * Calls the super class which is ChessPiece sends player to the super
 *
 * @param  player that owns the piece being checked
 *
 **********************************************************************/

    protected Rook(Player player) {
        super(player);
    }

/***********************************************************************
 *
 * Rook a string with the word Rook in it
 *
 * @return Returns the type of piece which is Rook is string form
 *
 **********************************************************************/

    @Override
    public String type() {
        return "Rook";
    }

/***********************************************************************
 *
 * Checks if the move made by the Rook meets the required move
 * requirements. Rook can move rows and columns
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

        //Checks change in col and row to see if it is a valid move
        if(super.isValidMove(move, board)){
            if(move.toRow == move.fromRow
                    || move.toColumn == move.fromColumn){
                validMove = true;
            }

            int tempCol = move.fromColumn;
            int tempRow = move.fromRow;

            //Checking if pieces are in the way prevents jumping
            if(validMove) {
                while ((tempRow != move.toRow)
                        || (tempCol != move.toColumn)) {
                    if (tempCol > move.toColumn
                            && tempCol <= 7 && tempCol >= 0)
                        tempCol--;
                    else if (tempCol < move.toColumn
                            && tempCol <= 7 && tempCol >= 0)
                        tempCol++;

                    if (tempRow > move.toRow
                            && tempRow <= 7 && tempRow >= 0)
                        tempRow--;
                    else if (tempRow < move.toRow
                            && tempCol <= 7 && tempCol >= 0)
                        tempRow++;
                    if (!(tempCol == move.toColumn
                            && tempRow == move.toRow)) {
                        if (board[tempRow][tempCol] != null)
                            validMove = false;
                    }
                }
            }

        }
        return validMove;
    }
}