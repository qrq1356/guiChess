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
        Position currentPos = board.findPiece(this);
        // check all positions in the current row and col
        for (int i = 0; i < Board.NUM_ROWS; i++) {
            // check positions in current row
            if (i != currentPos.getRow()) {
                Position targetPosition = new Position(i, currentPos.getCol());
                if (board.canMoveToPosition(currentPos, targetPosition, owner, false)) {
                    legalMoves.add(new Move(currentPos, targetPosition));
                }
            }
            if (i != currentPos.getCol()) {
                Position targetPosition = new Position(currentPos.getRow(), i);
                if (board.canMoveToPosition(currentPos, targetPosition, owner, false)) {
                    legalMoves.add(new Move(currentPos, targetPosition));
                }
            }
        }
        // check all positions diagonally
        int[][] directions = {{-1, -1}, {-1, 1}, {1, -1}, {1, 1}};
        for (int[] dir : directions) {
            int row = currentPos.getRow() + dir[0];
            int col = currentPos.getCol() + dir[1];
            while (row >= 0 && row < Board.NUM_ROWS && col >= 0 && col < Board.NUM_COLS) {
                Position targetPosition = new Position(row, col);
                if (board.canMoveToPosition(currentPos, targetPosition, owner, false)) {
                    legalMoves.add(new Move(currentPos, targetPosition));
                }
                if (board.getPieceAt(targetPosition) != null) {
                    break; // Stop if there's a piece blocking the diagonal
                }
                row += dir[0];
                col += dir[1];
            }
        }
        return legalMoves;
    }
}
