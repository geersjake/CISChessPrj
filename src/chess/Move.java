package chess;

/***********************************************************************
 *
 * Plays Chess
 *
 * @author Jake, Tyler, Jonathan
 * @version 1.0
 *
 **********************************************************************/

public class Move {
    //fromRow is the row where the piece currently is
    //fromColumn is the column where the piece currently is
    //toRow is the row where the piece is trying to go
    //toColumn is the column where the piece is trying to go
    public int fromRow, fromColumn, toRow, toColumn;

    public Move() {
    }

    /***********************************************************************
     *
     * Sets the proper integers from where and to where the pieces are
     * going to the proper variables
     *
     * @param fromRow is the row where the piece currently is
     * @param fromColumn is the column where the piece currently is
     * @param toRow is the row where the piece is trying to go
     * @param toColumn is the column where the piece is trying to go
     *
     **********************************************************************/

    public Move(int fromRow, int fromColumn, int toRow, int toColumn) {
        this.fromRow = fromRow;
        this.fromColumn = fromColumn;
        this.toRow = toRow;
        this.toColumn = toColumn;
    }
}
