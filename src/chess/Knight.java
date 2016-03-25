package chess;

/**
 * Created by tylerfaulk on 2/29/16.
 */
public class Knight extends ChessPiece {
    protected Knight(Player player) {
        super(player);
    }

    @Override
    public String type() {
        return "Knight";
    }

    @Override
    public boolean isValidMove(Move move, IChessPiece[][] board) {
        int changeRow;
        int changeCol;
        boolean validMove = false;
        changeRow = Math.abs(move.fromRow - move.toRow);
        changeCol = Math.abs(move.fromColumn - move.toColumn);
        if (super.isValidMove(move, board)){
            if (changeRow == 1 || changeRow == 2){
                if (changeCol == 1 || changeCol == 2) {
                    if (changeRow != changeCol){
                        validMove = true;
                    }
                }

            }
        }

        //fix in step 8
        return validMove;
    }
}
