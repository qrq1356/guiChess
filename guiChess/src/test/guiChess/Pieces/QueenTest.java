package guiChess.Pieces;

import guiChess.*;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class QueenTest {
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
        Queen queen = new Queen(player, board);
        board.placePieceAt(center, queen);

        List<Move> legalMoves = queen.getLegalMoves();

        // Assert that the queen has the correct number of legal moves
        assertEquals(27, legalMoves.size());

        // Assert that the queen can move to all positions in the same row and column
        for (int i = 0; i < Board.NUM_ROWS; i++) {
            if (i != center.getRow()) {
                assertTrue(legalMoves.contains(new Move(center, new Position(i, center.getCol()))));
            }
            if (i != center.getCol()) {
                assertTrue(legalMoves.contains(new Move(center, new Position(center.getRow(), i))));
            }
        }

        // Assert that the queen can move diagonally in all four directions
        int[][] directions = {{-1, -1}, {-1, 1}, {1, -1}, {1, 1}};
        for (int[] dir : directions) {
            int row = center.getRow() + dir[0];
            int col = center.getCol() + dir[1];
            while (row >= 0 && row < Board.NUM_ROWS && col >= 0 && col < Board.NUM_COLS) {
                assertTrue(legalMoves.contains(new Move(center, new Position(row, col))));
                row += dir[0];
                col += dir[1];
            }
        }
    }
}



