package guiChess;

import java.util.Objects;

public class Move {
    private Position from, to;
    public Move(Position from, Position to) {
        this.from = from;
        this.to = to;
    }
    public Position getFrom() { return from; };
    public Position getTo() { return to; };

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Move move = (Move) o;
        return Objects.equals(from, move.from) && Objects.equals(to, move.to);
    }

    @Override
    public int hashCode() {
        return Objects.hash(from, to);
    }
}
