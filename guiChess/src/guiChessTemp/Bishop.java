package guichess;
import java.util.ArrayList;
import java.util.List;

public class Bishop extends Piece {
    public Bishop(Player owner, Board board) {
        super(owner, board);
    }
    public List<Move> getLegalMoves() {
        List<Move> legalMoves = new ArrayList<>();
        Position pos = board.findPiece(this);
        int[][] directions = {{-1, -1}, {-1, 1}, {1, -1}, {1, 1}};
        for (int[] dir : directions) {
            int row = pos.getRow();
            int col = pos.getCol();
            while (row >= 0 && row < board.NUM_ROWS && col >= 0 && col < board.NUM_COLS) {
                if (row != pos.getRow() || col != pos.getCol()) {
                    if (board.getPieceAt(row, col).getOwner() != owner) {
                        if (board.isPathFree(pos, new Position(row, col))) {
                            legalMoves.add(new Move(pos, new Position(row, col)));
                        }
                    }
                }
                row += dir[0];
                col += dir[1];
            }
        }
        return legalMoves;
    }
}