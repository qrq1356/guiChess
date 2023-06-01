package guichess;

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
    public int getRow() {
        return row;
    }
    public int getCol() {
        return col;
    }
}