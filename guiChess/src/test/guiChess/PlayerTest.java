package guiChess;
import org.junit.Test;
import static org.junit.Assert.*;

public class PlayerTest {

    @Test
    public void testGetPawnRow() {
        Player player = new Human("Alice", true);
        assertEquals(1, player.getPawnRow());

        player = new Bot("Bot1", false);
        assertEquals(6, player.getPawnRow());
    }

    @Test
    public void testGetBackRow() {
        Player player = new Human("Alice", true);
        assertEquals(0, player.getBackRow());

        player = new Bot("Bot1", false);
        assertEquals(7, player.getBackRow());
    }

    @Test
    public void testGetDirection() {
        Player player = new Human("Alice", true);
        assertEquals(1, player.getDirection());

        player = new Bot("Bot1", false);
        assertEquals(-1, player.getDirection());
    }
}