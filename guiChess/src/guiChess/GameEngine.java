package guiChess;
import guiChess.Pieces.Piece;
import guiChess.UI.GameObserver;
import java.util.ArrayList;
import java.util.List;

public class GameEngine {
    private List<GameObserver> observers = new ArrayList<>();
    private Board board;
    private Player up, down, current;
    private List<Move> moves;

    public GameEngine() {
        board = new Board();
        moves = new ArrayList<>();
    }
    public void initPlayers(Player up, Player down) {
        this.up = up;
        this.down = down;
        current = up;
        board.addStartingPieces(up);
        board.addStartingPieces(down);
    }
    public void registerObserver(GameObserver observer) {
        observers.add(observer);
    }
    public void unregisterObserver(GameObserver observer) {
        observers.remove(observer);
    }
    public void notifyObservers() {
        for (GameObserver observer : observers) {
            observer.onGameStateChange(this);
        }
    }

    public void playMove(Move move) {
        playMove(current, move);
    }

    public void playMove(Player player, Move move) {
        // check if the move is valid
        List<Move> validMoves = board.getValidMoves(player);
        for (Move m : validMoves) {
            // if move is in validMoves, make the move
            if (m.equals(move)) {
                makeMove(move);
            }
        }
    }

    public void makeMove(Move move) {
        // move the piece
        Piece piece = board.getPieceAt(move.getFrom());
        board.removePieceAt(move.getFrom());
        board.placePieceAt(move.getTo(), piece);
        // add the move to the list
        moves.add(move);
        // switch the current player
        current = (current == up) ? down : up;
        notifyObservers();
    }
    public void playGameFromList(List<Move> moves) {
        for (Move move : moves) {
            makeMove(move);
        }
    }
}
