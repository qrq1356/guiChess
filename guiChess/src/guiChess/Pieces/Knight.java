package guiChess.Pieces;
import guiChess.Player;
import guiChess.Board;
import guiChess.Move;
import guiChess.Position;

import java.util.ArrayList;
import java.util.List;

public class Knight extends Piece {
    public Knight(Player owner, Board board) {
        super(owner, board);
    }
    public List<Move> getLegalMoves() {
        List<Move> legalMoves = new ArrayList<>();
        Position pos = board.findPiece(this);
        // potential moves are so few we can just track them all.
        int[][] moves = {
            {-1, -2},{-2, -1},{-2, 1},{-1, 2}, {1, 2},{2, 1},{2, -1},{1, -2}
        };
        for (int[] move : moves) {
            int newRow = pos.getRow() + move[0];
            int newCol = pos.getCol() + move[1];
            if (newRow >= 0 && newRow < Board.NUM_ROWS && newCol >= 0 && newCol < Board.NUM_COLS) {
                if(board.getPieceAt(newRow, newCol).getOwner() != owner) {
                    legalMoves.add(new Move(pos, new Position(newRow, newCol)));
                }
            }
        }
        return legalMoves;
    }
}
