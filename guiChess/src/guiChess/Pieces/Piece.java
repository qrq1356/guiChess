package guiChess.Pieces;
import guiChess.Player;
import guiChess.Board;
import guiChess.Move;
import guiChess.Position;


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
