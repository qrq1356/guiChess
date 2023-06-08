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
        // check moving forward once
        Position once = new Position(pos.getRow()+owner.getDirection(), pos.getCol());
        if(board.getPieceAt(once) == null) {
            legalMoves.add(new Move(pos, once));
        }
        // check capturing diagonally
        int[] captureCols = { pos.getCol() + 1, pos.getCol() - 1 };
        for (int targetCol : captureCols) {
            Position target = new Position(pos.getRow() + owner.getDirection(), targetCol);
            Piece targetPiece = board.getPieceAt(target);
            if (targetPiece != null && targetPiece.getOwner() != owner) {
                legalMoves.add(new Move(pos, target));
            }
        }
        // check two positions forward
        if (pos.getRow() == owner.getPawnRow()) {
            Position target = new Position(pos.getRow() + (2 * owner.getDirection()), pos.getCol());
            if (board.isPathFree(pos, new Position(pos.getRow() + (2 * owner.getDirection()), pos.getCol()))) {
                if (board.getPieceAt(target) == null) {
                    legalMoves.add(new Move(pos, target));
                }
            }
        }
        return legalMoves;
    }
}
