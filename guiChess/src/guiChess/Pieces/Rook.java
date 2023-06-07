package guiChess.Pieces;
import guiChess.Player;
import guiChess.Board;
import guiChess.Move;
import guiChess.Position;


import java.util.ArrayList;
import java.util.List;

public class Rook extends Piece {
    public Rook(Player owner, Board board) {
        super(owner, board);
    }
    public List<Move> getLegalMoves() {
        List<Move> legalMoves = new ArrayList<>();
        Position pos = board.findPiece(this);
        for (int col = 0; col < Board.NUM_COLS; col++) {
            if (col != pos.getCol()) {
                if (!board.matchingOwner(new Position(pos.getRow(), col), owner)) {
                    if (board.isPathFree(pos, new Position(pos.getRow(), col))) {
                        legalMoves.add(new Move(pos, new Position(pos.getRow(), col)));
                    }
                }
            }
        }
        for (int row = 0; row < Board.NUM_COLS; row++) {
            if (row != pos.getRow()) {
                if (!board.matchingOwner(new Position(row, pos.getCol()), owner)) {
                    if (board.isPathFree(pos, new Position(row, pos.getCol()))) {
                        legalMoves.add(new Move(pos, new Position(row, pos.getCol())));
                    }
                }
            }
        }
        return legalMoves;
    }
}
