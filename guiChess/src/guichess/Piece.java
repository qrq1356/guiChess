package guichess;

import java.util.List;

public abstract class Piece {
    protected Player owner;
    protected Board board;
    public Piece(Player owner, Board board) {
        this.owner = owner;
        this.board = board;
    }
    public Player getOwner() { return owner; };
    public abstract List<Move> getLegalMoves();
}
