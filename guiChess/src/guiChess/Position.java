package guiChess;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return row == position.row && col == position.col;
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, col);
    }
}
