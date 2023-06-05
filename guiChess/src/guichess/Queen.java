package guichess;

import java.util.ArrayList;
import java.util.List;

public class Queen extends Piece {
    public Queen(Player owner, Board board) {
        super(owner, board);
    }
    public List<Move> getLegalMoves() {
        List<Move> legalMoves = new ArrayList<>();
        Position pos = board.findPiece(this);
        // check all the squares in the current rank and file
        for (int i = 0;  i < pos.getRow(); i++) {
            // check positions in the same row
            if (i != pos.getRow()) {
                if (board.getPieceAt(i, pos.getCol()).getOwner() != owner) {
                    if (board.isPathFree(pos, new Position(i, pos.getCol()))) {
                        legalMoves.add(new Move(pos, new Position(i, pos.getCol())));
                    }
                }
            }
            // check positions in the same col
            if (i != pos.getCol()) {
                if (board.getPieceAt(pos.getRow(), i).getOwner() != owner) {
                    if (board.isPathFree(pos, new Position(pos.getRow(), i))) {
                        legalMoves.add(new Move(pos, new Position(pos.getRow(), i)));
                    }
                }
            }
        }
        int[][] directions = {{-1, -1}, {-1, 1}, {1, -1}, {1, 1}};
        for (int[] dir : directions) {
            int row = pos.getRow();
            int col = pos.getCol();
            while (row >= 0 && row < board.NUM_ROWS && col >= 0 && col < board.NUM_COLS) {
                if (row != pos.getRow() || col != pos.getCol()) {
                    if(board.getPieceAt(row, col).getOwner() != owner) {
                        if(board.isPathFree(pos, new Position(row, col))) {
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
