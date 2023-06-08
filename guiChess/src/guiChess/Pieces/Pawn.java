package guiChess.Pieces;
import guiChess.Player;
import guiChess.Board;
import guiChess.Move;
import guiChess.Position;


import java.util.ArrayList;
import java.util.List;

public class Pawn extends Piece {
    public Pawn(Player owner, Board board) {
        super(owner, board);
    }
    public List<Move> getLegalMoves() {
        List<Move> legalMoves = new ArrayList<>();
        Position pos = board.findPiece(this);

        Position forwardOnce = new Position(pos.getRow() + owner.getDirection(), pos.getCol());
        if (board.isPositionValid(forwardOnce) && board.getPieceAt(forwardOnce) == null) {
            legalMoves.add(new Move(pos, forwardOnce));
        }

        Position forwardTwice = new Position(pos.getRow() + (2 * owner.getDirection()), pos.getCol());
        if (pos.getRow() == owner.getPawnRow() && board.isPositionValid(forwardTwice)
                && board.getPieceAt(forwardTwice) == null && board.isPathFree(pos, forwardTwice)) {
            legalMoves.add(new Move(pos, forwardTwice));
        }

        int[] captureCols = { pos.getCol() - 1, pos.getCol() + 1 };
        for (int col : captureCols) {
            Position capturePos = new Position(pos.getRow() + owner.getDirection(), col);
            if (board.isPositionValid(capturePos)) {
                Piece piece = board.getPieceAt(capturePos);
                if (piece != null && piece.getOwner() != owner) {
                    legalMoves.add(new Move(pos, capturePos));
                }
            }
        }

        return legalMoves;
    }


}
