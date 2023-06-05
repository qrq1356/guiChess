package guichess;

import java.util.ArrayList;
import java.util.List;

public class Pawn extends Piece {
    public Pawn(Player owner, Board board) {
        super(owner, board);
    }
    public List<Move> getLegalMoves() {
        List<Move> legalMoves = new ArrayList<>();
        Position pos = board.findPiece(this);
        if (pos.getRow() == owner.getStartRow()) {
            Position target = new Position(pos.getRow() + (2 * owner.getDirection()), pos.getCol());
            if(board.isPathFree(pos, new Position(pos.getRow(), pos.getCol()+2))) {
                if(board.getPieceAt(target) == null) {
                    legalMoves.add(new Move(pos, target));
                }
            }
        } else {
            Position once = new Position(pos.getRow()+owner.getDirection(), pos.getCol());
            if(board.getPieceAt(once) == null) {
                legalMoves.add(new Move(pos, once));
            }
            int[] captureCols = {pos.getCol() + 1, pos.getCol() - 1};
            for (int targetCol: captureCols) {
                Position target = new Position(pos.getRow()+owner.getDirection(), targetCol);
                if (board.getPieceAt(target).getOwner() != owner) {
                    legalMoves.add(new Move(pos, target));
                }
            }
        }
        return legalMoves;
    }
}
