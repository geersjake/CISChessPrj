package chess;

/***********************************************************************
 *
 * Queen Class takes care of the the Queen logic
 *
 * @author Jake, Tyler, Jonathan
 * @version 1.0
 *
 **********************************************************************/

public class Queen extends ChessPiece {

/***********************************************************************
 *
 * Calls the super class which is ChessPiece sends player to the super
 *
 * @param  player that owns the piece being checked
 *
 **********************************************************************/

    protected Queen(Player player) {
        super(player);
    }

/***********************************************************************
 *
 * Queen a string with the word Queen in it
 *
 * @return Returns the type of piece which is Queen in string form
 *
 **********************************************************************/

    @Override
    public String type() {
        return "Queen";
    }

/***********************************************************************
 *
 * Checks if the move made by the Queen meets the required move
 * requirements. Queen can move in diagonals and rows and columns
 * can't jump
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
            if(move.toRow == move.fromRow || move.toColumn == move.fromColumn){
                validMove = true;
            }
            if(Math.abs(move.fromRow - move.toRow)
                    == Math.abs(move.fromColumn - move.toColumn)){
                validMove = true;
            }

            int tempCol = move.fromColumn;
            int tempRow = move.fromRow;

            //Checking if pieces are in the way prevents jumping
            if(validMove) {
                while ((tempRow != move.toRow) || (tempCol != move.toColumn)) {

                    if (tempCol > move.toColumn && tempRow == move.toRow
                            && tempCol <= 7 && tempCol >= 0)
                        tempCol--;

                    if (tempCol < move.toColumn && tempRow == move.toRow
                            && tempCol <= 7 && tempCol >= 0)
                        tempCol++;

                    if (tempRow > move.toRow && tempCol == move.toColumn
                            && tempRow <= 7 && tempRow >= 0)
                        tempRow--;

                    if (tempRow < move.toRow && tempCol == move.toColumn
                            && tempCol <= 7 && tempCol >= 0)
                        tempRow++;

                    if (tempRow > move.toRow && tempCol > move.toColumn
                            && tempCol <= 7 && tempCol >= 0
                            && tempRow <= 7 && tempRow >= 0) {
                        tempCol--;
                        tempRow--;
                    }

                    if (tempRow < move.toRow && tempCol < move.toColumn
                            && tempCol <= 7 && tempCol >= 0
                            && tempRow <= 7 && tempRow >= 0) {
                        tempCol++;
                        tempRow++;
                    }

                    if (tempRow > move.toRow && tempCol < move.toColumn
                            && tempCol <= 7 && tempCol >= 0
                            && tempRow <= 7 && tempRow >= 0) {
                        tempRow--;
                        tempCol++;
                    }

                    if (tempRow < move.toRow && tempCol > move.toColumn
                            && tempCol <= 7 && tempCol >= 0
                            && tempRow <= 7 && tempRow >= 0) {
                        tempRow++;
                        tempCol--;
                    }

                    if (!(tempCol == move.toColumn && tempRow == move.toRow)) {
                        if (board[tempRow][tempCol] != null)
                            validMove = false;
                    }

                }

            }
        }
        return validMove;
    }
}