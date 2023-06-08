package guiChess.Pieces;

import guiChess.*;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class KingTest {
    private Player player;
    private Board board;
    private Position center = new Position(3, 3);

    @Before
    public void setUp() {
        player = new Human("Alice", true); // Example player
        board = new Board();
    }

    @Test
    public void testGetLegalMoves() {
        King king = new King(player, board);
        board.placePieceAt(center, king);

        List<Move> legalMoves = king.getLegalMoves();

        // Assert that the king has the correct number of legal moves
        assertEquals(8, legalMoves.size());

        // Assert that the king can move to all adjacent positions
        int[][] offsets = {
                {-1, -1}, {-1, 0}, {-1, 1},
                {0, -1},           {0, 1},
                {1, -1},  {1, 0},  {1, 1}
        };
        for (int[] offset : offsets) {
            int newRow = center.getRow() + offset[0];
            int newCol = center.getCol() + offset[1];
            assertTrue(legalMoves.contains(new Move(center, new Position(newRow, newCol))));
        }
    }
}
