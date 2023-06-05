package guichess;

public class GameEngine {
    private Board board;
    private Player up, down;
    public GameEngine(Player up, Player down) {
        this.up = up;
        this.down = down;
        board = new Board(up, down);
        board.addStartingPieces();
    }
    // assumes valid move
    public void makeMove(Player player, Move move) {
        if (board.willBeInCheck(player, move))
        board.placePieceAt(move.getTo(), board.getPieceAt(move.getFrom()));
        board.removePieceAt(move.getFrom());
    }
}
