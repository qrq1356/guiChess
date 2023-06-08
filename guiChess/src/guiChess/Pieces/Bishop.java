package guiChess.Pieces;
import guiChess.Player;
import guiChess.Board;
import guiChess.Move;
import guiChess.Position;

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
            while (row >= 0 && row < Board.NUM_ROWS && col >= 0 && col < Board.NUM_COLS) {
                if (row != pos.getRow() || col != pos.getCol()) {
                    if (board.canMoveToPosition(pos, new Position(row, col), owner, false)) {
                        legalMoves.add(new Move(pos, new Position(row, col)));
                    }
                }
                row += dir[0];
                col += dir[1];
            }
        }
        return legalMoves;
    }
}
