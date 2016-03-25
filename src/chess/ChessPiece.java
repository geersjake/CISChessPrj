package chess;

/***********************************************************************
 *
 * Takes care of the logic for the pieces is override by the individual
 * piece types for individual logic
 *
 * @author Jake, Tyler, Jonathan
 * @version 1.0
 *
 **********************************************************************/

public abstract class ChessPiece implements IChessPiece {

    // The owner of the piece
    private Player owner;

/***********************************************************************
 *
 * Sets the owner of the chess piece to the player
 *
 * @param player to to set as owner
 *
 **********************************************************************/

    protected ChessPiece(Player player) {
        this.owner = player;
    }

/***********************************************************************
 *
 * Override the method type so it child classes can override it`
 *
 **********************************************************************/

    public abstract String type();

/***********************************************************************
 *
 * Returns the owner of the chess piece
 *
 * @return hte owner of the piece
 *
 **********************************************************************/

    public Player player() {
        return(owner);

    }

/***********************************************************************
 *
 * Checks if the move is a Valid move for any and all pieces
 * Makes sure all pieces follow the rules that are subject to all pieces
 *
 * @return returns true if all the requirements are met to make
 * it a valid move if not return false
 * @param move the move that the piece us making
 * @param board the information of where the piece is on the board
 *
 **********************************************************************/

    public boolean isValidMove(Move move, IChessPiece[][] board) {
        boolean validMove = false;
        if(move.fromColumn != move.toColumn || move.toRow
                != move.fromRow){
            if (this == board[move.fromRow][move.fromColumn]) {
                //prevents players from attacking their own pieces
                try {
                    if (board[move.toRow][move.toColumn].player()
                            != this.owner) {
                        validMove = true;
                    }
                } catch (NullPointerException e) {
                    validMove = true;
                }
            }
        }
        return validMove;

    }
}

