package guiChess;

public class GameEngine {
    private Board board;
    private Player up, down, current;
    public GameEngine(Player up, Player down) {
        this.up = up;
        this.down = down;
        current = up;
        board = new Board(up, down);
        board.addStartingPieces(up);
        board.addStartingPieces(down);
    }
    // assumes valid move
    public void makeMove(Player player, Move move) {
        return;
    }
}
