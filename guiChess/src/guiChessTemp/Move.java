package guichess;

public class Move {
    private Position from, to;
    public Move(Position from, Position to) {
        this.from = from;
        this.to = to;
    }
    public Position getFrom() { return from; };
    public Position getTo() { return to; };
}
