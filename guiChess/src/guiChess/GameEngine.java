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
        current = up; // up always starts
        board.addStartingPieces(up);
    }

    public void initDown(Player given) {
        this.down = given;
        board.addStartingPieces(down);
    }

    public void registerObserver(GameObserver observer) {
        observers.add(observer);
    }

    public void notifyObservers() {
        for (GameObserver observer : observers) {
            observer.onGameStateChange(this);
        }
    }

    // selects a random valid move.
    public void botMove() {
        List<Move> validMoves = board.getValidMoves(current);
        Move move = validMoves.get((int) (Math.random() * validMoves.size()));
        makeMove(move);
    }

    // cycles a player and bot turn
    public void play(Move move) {
        if (isCheckmate(down)) {
            System.out.println("Checkmate! Up player wins!");
        }
        if (isCheckmate(up)) {
            System.out.println("Checkmate! Down player wins!");
        }
        if (playMove(move)) {
            botMove();
        }
    }

    // to be used with player input
    public boolean playMove(Move move) {
        List<Move> validMoves = board.getValidMoves(current);
        if (validMoves.contains(move) && board.wontCheckAfterMove(current, move)) {
            makeMove(move);
            return true;
        }
        return false;
    }

    // to be used with known good input
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

    public List<Move> getMoves() {
        return moves;
    }

    public Piece getPieceAt(Position pos) {
        return board.getPieceAt(pos);
    }
    public boolean isCheckmate(Player player) {
        // Check if the player's king is in check
        if (!board.isInCheck(player)) {
            return false;
        }

        // Check if the player has any valid moves that can remove the check
        List<Move> validMoves = board.getValidMoves(player);
        for (Move move : validMoves) {
            // Check if the move can remove the check
            Piece piece = board.getPieceAt(move.getFrom());
            Piece capturedPiece = board.getPieceAt(move.getTo());

            // Make the move
            board.removePieceAt(move.getFrom());
            board.placePieceAt(move.getTo(), piece);

            boolean isInCheck = board.isInCheck(player);

            // Undo the move
            board.removePieceAt(move.getTo());
            board.placePieceAt(move.getFrom(), piece);
            if (capturedPiece != null) {
                board.placePieceAt(move.getTo(), capturedPiece);
            }

            if (!isInCheck) {
                return false;
            }
        }

        return true;
    }

}
