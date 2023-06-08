package guiChess;
import org.junit.Test;
import static org.junit.Assert.*;

public class PositionTest {

    @Test
    public void testConstructorWithInts() {
        Position position = new Position(2, 3);
        assertEquals(2, position.getRow());
        assertEquals(3, position.getCol());
    }

    @Test
    public void testEquals() {
        Position position1 = new Position(1, 2);
        Position position2 = new Position(1, 2);
        Position position3 = new Position(3, 4);

        assertTrue(position1.equals(position2));
        assertTrue(position2.equals(position1));
        assertFalse(position1.equals(position3));
        assertFalse(position3.equals(position1));
    }

    @Test
    public void testHashCode() {
        Position position1 = new Position(1, 2);
        Position position2 = new Position(1, 2);

        assertEquals(position1.hashCode(), position2.hashCode());
    }
}
