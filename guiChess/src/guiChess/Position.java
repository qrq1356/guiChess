package guiChess;

public class Position {
    private int row, col;
    public Position(int row, int col) {
        this.row = row;
        this.col = col;
    }
    public Position(char row, int col) {
        this.row = row - 'a';
        this.col = col;
    }
    public Position(String val) {
        this(val.charAt(0), Integer.parseInt(val.substring(1)));
    }
    public int getRow() {
        return row;
    }
    public int getCol() {
        return col;
    }
}
