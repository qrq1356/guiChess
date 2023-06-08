package guiChess;

import guiChess.Pieces.*;

import java.util.ArrayList;
import java.util.List;

public class Board {
    public static final int NUM_ROWS = 8, NUM_COLS = 8;
    private final Piece[][] board;

    public Board() {
        this.board = new Piece[NUM_ROWS][NUM_COLS];
    }

    public void addStartingPieces(Player owner) {
        int p = owner.getPawnRow();
        for (int c = 0; c < NUM_COLS; c++) {
            board[p][c] = new Pawn(owner, this);
        }
        int b = owner.getBackRow();
        board[b][0] = new Rook(owner, this);
        board[b][1] = new Knight(owner, this);
        board[b][2] = new Bishop(owner, this);
        board[b][3] = new Queen(owner, this);
        board[b][4] = new King(owner, this);
        board[b][5] = new Bishop(owner, this);
        board[b][6] = new Knight(owner, this);
        board[b][7] = new Rook(owner, this);
    }

    public void removePieceAt(Position pos) {
        board[pos.getRow()][pos.getCol()] = null;
    }

    public Piece getPieceAt(Position pos) {
        return board[pos.getRow()][pos.getCol()];
    }

    public Piece getPieceAt(int row, int col) {
        return board[row][col];
    }

    public void placePieceAt(Position pos, Piece piece) {
        board[pos.getRow()][pos.getCol()] = piece;
    }

    public Position findPiece(Piece piece) {
        for (int r = 0; r < NUM_ROWS; r++) {
            for (int c = 0; c < NUM_COLS; c++) {
                if (board[r][c] == piece) {
                    return new Position(r, c);
                }
            }
        }
        return null;
    }

    public boolean canMoveToPosition(Position from, Position to, Player owner, boolean jump) {
        if (isPositionValid(to)) {
            if (matchingOwner(to, owner))
                return false;
            else if (jump)
                return true;
            else
                return isPathFree(from, to);
        }
        return false;
    }


    public boolean isPositionValid(Position pos) {
        return pos.getRow() >= 0 && pos.getRow() < NUM_ROWS && pos.getCol() >= 0 && pos.getCol() < NUM_COLS;
    }

    public boolean matchingOwner(Position pos, Player owner) {
        if (board[pos.getRow()][pos.getCol()] == null) {
            return false;
        } else {
            return board[pos.getRow()][pos.getCol()].getOwner().equals(owner);
        }
    }

    public boolean isPathFree(Position from, Position to) {
        //direction calculation check
        int rowDir = (to.getRow() - from.getRow() == 0) ? 0 : (to.getRow() - from.getRow()) / Math.abs(to.getRow() - from.getRow());
        int colDir = (to.getCol() - from.getCol() == 0) ? 0 : (to.getCol() - from.getCol()) / Math.abs(to.getCol() - from.getCol());
        //loop over between all squares between to and from
        int row = from.getRow() + rowDir;
        int col = from.getCol() + colDir;
        // loop over each space between
        while (row != to.getRow() || col != to.getCol()) {
            if (board[row][col] != null) {
                return false;
            }
            row += rowDir;
            col += colDir;
        }
        return true;
    }

    public List<Move> getValidMoves(Player player) {
        List<Move> validMoves = new ArrayList<>();
        for (int r = 0; r < NUM_ROWS; r++) {
            for (int c = 0; c < NUM_COLS; c++) {
                if (matchingOwner(new Position(r, c), player)) {
                    validMoves.addAll(getPieceAt(r, c).getLegalMoves());
                }
            }
        }
        return validMoves;
    }

    public boolean wontCheckAfterMove(Player player, Move move) {
        // Make the move
        Piece piece = this.getPieceAt(move.getFrom());
        Piece capturedPiece = this.getPieceAt(move.getTo());
        this.removePieceAt(move.getFrom());
        this.placePieceAt(move.getTo(), piece);

        // Check if the king is in check
        Position kingPos = findPiece(new King(player, this));
        boolean inCheck = isInCheck(player, kingPos);

        // Undo the move
        this.removePieceAt(move.getTo());
        this.placePieceAt(move.getFrom(), piece);
        if (capturedPiece != null) {
            this.placePieceAt(move.getTo(), capturedPiece);
        }

        return !inCheck;
    }


    public boolean isInCheck(Player player, Position kingPos) {
        for (int r = 0; r < NUM_ROWS; r++) {
            for (int c = 0; c < NUM_COLS; c++) {
                Piece piece = board[r][c];
                if (piece != null && !piece.getOwner().equals(player)) {
                    List<Move> moves = piece.getLegalMoves();
                    for (Move move : moves) {
                        if (move.getTo().equals(kingPos)) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }
}
