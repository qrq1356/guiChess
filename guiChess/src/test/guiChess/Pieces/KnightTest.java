package guiChess.Pieces;
import guiChess.*;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class KnightTest {
    private Player upPlayer;
    private Player downPlayer;
    private Board board;

    @Before
    public void setUp() {
        upPlayer = new Human("Alice", true); // Example player facing up
        downPlayer = new Human("Bob", false); // Example player facing down
        board = new Board();
    }

    @Test
    public void testGetLegalMoves() {
        Position knightPosition = new Position(3, 3); // Assuming a knight at the center of the board
        Knight knight = new Knight(upPlayer, board);
        board.placePieceAt(knightPosition, knight);

        List<Move> legalMoves = knight.getLegalMoves();

        // Assert that the knight has the correct number of legal moves
        assertEquals(8, legalMoves.size());

        // Assert that the knight can move to all valid positions
        assertTrue(legalMoves.contains(new Move(knightPosition, new Position(1, 2))));
        assertTrue(legalMoves.contains(new Move(knightPosition, new Position(2, 1))));
        assertTrue(legalMoves.contains(new Move(knightPosition, new Position(4, 1))));
        assertTrue(legalMoves.contains(new Move(knightPosition, new Position(5, 2))));
        assertTrue(legalMoves.contains(new Move(knightPosition, new Position(5, 4))));
        assertTrue(legalMoves.contains(new Move(knightPosition, new Position(4, 5))));
        assertTrue(legalMoves.contains(new Move(knightPosition, new Position(2, 5))));
        assertTrue(legalMoves.contains(new Move(knightPosition, new Position(1, 4))));
    }
}
