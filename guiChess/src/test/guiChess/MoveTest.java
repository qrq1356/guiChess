package guiChess;
import org.junit.Test;

import static org.junit.Assert.*;

public class MoveTest {

    @Test
    public void testConstructorAndGetters() {
        Position from = new Position(2, 3);
        Position to = new Position(4, 5);

        Move move = new Move(from, to);

        assertEquals(from, move.getFrom());
        assertEquals(to, move.getTo());
    }

    @Test
    public void testEquals() {
        Position from1 = new Position(2, 3);
        Position to1 = new Position(4, 5);
        Position from2 = new Position(2, 3);
        Position to2 = new Position(4, 5);
        Position from3 = new Position(1, 1);
        Position to3 = new Position(6, 7);

        Move move1 = new Move(from1, to1);
        Move move2 = new Move(from2, to2);
        Move move3 = new Move(from3, to3);

        assertEquals(move1, move2);
        assertEquals(move2, move1);
        assertNotEquals(move1, move3);
        assertNotEquals(move3, move1);
    }

    @Test
    public void testHashCode() {
        Position from = new Position(2, 3);
        Position to = new Position(4, 5);

        Move move1 = new Move(from, to);
        Move move2 = new Move(from, to);

        assertEquals(move1.hashCode(), move2.hashCode());
    }
}
