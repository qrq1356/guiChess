package guiChess;

import guiChess.Pieces.Piece;
import guiChess.UI.GameObserver;

import java.util.ArrayList;
import java.util.List;

public class GameEngine {
    private final List<GameObserver> observers = new ArrayList<>();
    private final Board board;
    private Player up, down, current;
    private final List<Move> moves;

    public GameEngine() {
        board = new Board();
        moves = new ArrayList<>();
    }

    public void initUp(Player given) {
        this.up = given;
        current = up;
        board.addStartingPieces(up);
    }

    public void initDown(Player given) {
        this.down = given;
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

    public void botMove() {
        List<Move> validMoves = board.getValidMoves(current);
        Move move = validMoves.get((int) (Math.random() * validMoves.size()));
        playMove(move);
    }

    public void playMove(Move move) {
        playMove(current, move);
    }

    public void playMove(Player player, Move move) {
        // check if the move is valid
        List<Move> validMoves = board.getValidMoves(player);
        System.out.println("Valid moves:");
        for (Move m : validMoves) {
            System.out.println(m);
        }
        if (!validMoves.contains(move)) {
            System.out.println("Move not in validMoves list: " + move);
        }
        if (!board.wontCheckAfterMove(player, move)) {
            System.out.println("Move results in check: " + move);
        }
        if (validMoves.contains(move) && board.wontCheckAfterMove(player, move)) {
            makeMove(move);
        } else {
            System.out.println("Invalid move: " + move);
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

    public Piece getPieceAt(Position pos) {
        return board.getPieceAt(pos);
    }
}
