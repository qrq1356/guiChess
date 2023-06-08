package guiChess.Pieces;
import guiChess.Player;
import guiChess.Board;
import guiChess.Move;
import guiChess.Position;

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
            if (board.canMoveToPosition(pos, new Position(row, col), owner, false)) {
                legalMoves.add(new Move(pos, new Position(row, col)));
            }
        }
        return legalMoves;
    }
}
