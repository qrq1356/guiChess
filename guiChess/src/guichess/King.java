package guichess;
import java.util.ArrayList;
import java.util.List;

public class King extends Piece {
    public King(Player owner, Board board) {
        super(owner, board);
    }
    public List<Move> getLegalMoves() {
        List<Move> legalMoves = new ArrayList<>();
        Position pos = board.findPiece(this);
        // potential moves are so few we can just track them all.
        int[] rowOffsets = {-1, -1, -1, 0, 0, 1, 1, 1};
        int[] colOffsets = {-1, 0, 1, -1, 1, -1, 0, 1};
        for (int i = 0; i < rowOffsets.length; i++) {
            int row = pos.getRow() + rowOffsets[i];
            int col = pos.getCol() + colOffsets[i];
            if (row >= 0 && row < Board.NUM_ROWS && col >= 0 && col < Board.NUM_COLS) {
                if (board.getPieceAt(row, col).getOwner() != owner) {
                    // don't need to check for obstructed paths as the king is unable to move more than one position at a time
                    legalMoves.add(new Move(pos, new Position(row, col)));
                }
            }
        }
        return legalMoves;
    }
}
