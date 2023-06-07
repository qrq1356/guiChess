package guiChess.Pieces;
import guiChess.Player;
import guiChess.Board;
import guiChess.Move;
import guiChess.Position;


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
                if (!board.matchingOwner(new Position(i, pos.getCol()), owner)) {
                    if (board.isPathFree(pos, new Position(i, pos.getCol()))) {
                        legalMoves.add(new Move(pos, new Position(i, pos.getCol())));
                    }
                }
            }
            // check positions in the same col
            if (i != pos.getCol()) {
                if (!board.matchingOwner(new Position(pos.getRow(), i), owner)) {
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
            while (row >= 0 && row < Board.NUM_ROWS && col >= 0 && col < Board.NUM_COLS) {
                if (row != pos.getRow() || col != pos.getCol()) {
                    if(!board.matchingOwner(new Position(row, col), owner)) {
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
