package guiChess.Pieces;

import guiChess.*;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class BishopTest {
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
        Bishop bishop = new Bishop(player, board);
        board.placePieceAt(center, bishop);

        List<Move> legalMoves = bishop.getLegalMoves();

        // Assert that the bishop has the correct number of legal moves
        assertEquals(13, legalMoves.size());

        // Assert that the bishop can move diagonally in all four directions
        int[][] directions = {{-1, -1}, {-1, 1}, {1, -1}, {1, 1}};
        for (int[] dir : directions) {
            int newRow = center.getRow() + dir[0];
            int newCol = center.getCol() + dir[1];
            while (newRow >= 0 && newRow < Board.NUM_ROWS && newCol >= 0 && newCol < Board.NUM_COLS) {
                assertTrue(legalMoves.contains(new Move(center, new Position(newRow, newCol))));
                newRow += dir[0];
                newCol += dir[1];
            }
        }
    }
}
