package guichess;

public class GameEngine {
    private Board board;
    private Player up, down;
    public GameEngine(Player up, Player down) {
        Player up = new Player(direction, name, )
        board = new Board(up, down);
        board.addStartingPieces();
    }
    // assumes valid move
    public void makeMove(Player player, Move move) {
        if (player.willBeInCheck(move))
        board.placePieceAt(move.getTo(), board.getPieceAt(move.getFrom()));
        board.removePieceAt(move.getFrom());
    }
}
