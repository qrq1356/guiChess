package guichess;


public class Board {
    public static final int NUM_ROWS = 8, NUM_COLS = 8;
    private final Piece[][] board;
    private Player up, down;
    public Board() {
        this.board = new Piece[NUM_ROWS][NUM_COLS];
    }
    // should be revised to add by player type to avoid reptition.
    public void addStartingPieces() {
        // add pawns
        for (int c = 0; c < NUM_COLS; c++) {
            board[c][1] = new Pawn(up, this);
            board[c][6] = new Pawn(down, this);
        }
        board[0][0] = new Rook(up, this);
        board[0][1] = new Knight(up, this);
        board[0][2] = new Bishop(up, this);
        board[0][3] = new Queen(up, this);
        board[0][4] = new King(up, this);
        board[0][5] = new Bishop(up, this);
        board[0][6] = new Knight(up, this);
        board[0][7] = new Rook(up, this);
        board[6][0] = new Rook(down, this);
        board[6][1] = new Knight(down, this);
        board[6][2] = new Bishop(down, this);
        board[6][3] = new Queen(down, this);
        board[6][4] = new King(down, this);
        board[6][5] = new Bishop(down, this);
        board[6][6] = new Knight(down, this);
        board[6][7] = new Rook(down, this);
    }
    public void removePieceAt(Position pos) {
        board[pos.getRow()][pos.getCol()] = null;
    }
    public Piece getPieceAt(Position pos) {
        return board[pos.getRow()][pos.getCol()];
    }
    public Piece getPieceAt(int r, int c) {
        return board[r][c];
    }
    public void placePieceAt(Position pos, Piece piece) {
        board[pos.getRow()][pos.getCol()] = piece;
    }
    public Position findPiece(Piece piece) {
        for (int r = 0; r < NUM_ROWS; r++) {
            for (int c = 0; c < NUM_COLS; c++) {
                if(board[r][c] == piece) {
                    return new Position(r, c);
                }
            }
        }
        return null;
    }
    public boolean isPathFree(Position from, Position to) {
        // determine direction
        int rowDif = from.getRow() - to.getRow();
        int colDif = from.getCol() - to.getCol();
        int rowDir = (rowDif == 0) ? 0 : rowDif / Math.abs(rowDif);
        int colDir = (colDif == 0) ? 0 : colDif / Math.abs(colDif);
        // make the first step, as the from position may be ocupied
        int row = from.getRow() + rowDir;
        int col = from.getCol() + colDir;
        // loop over each space between
        while (row != to.getRow() || col != to.getCol()) {
            if(board[row][col] != null) {
                return false;
            }
            row += rowDir;
            col += colDir;
        }
        return true;
    }
}