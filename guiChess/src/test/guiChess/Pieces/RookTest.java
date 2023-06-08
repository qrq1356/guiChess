package guiChess.Pieces;

import guiChess.*;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class RookTest {
    private Player player;
    private Board board;

    @Before
    public void setUp() {
        player = new Human("Alice", true); // Example player
        board = new Board();
    }

    @Test
    public void testGetLegalMoves() {
        Rook rook = new Rook(player, board);
        Position rookPosition = new Position(3, 3); // Assuming a rook at the center of the board
        board.placePieceAt(rookPosition, rook);

        List<Move> legalMoves = rook.getLegalMoves();

        // Assert that the rook has the correct number of legal moves
        assertEquals(14, legalMoves.size());

        // Assert that the rook can move to all positions in the same row and column
        for (int i = 0; i < Board.NUM_ROWS; i++) {
            if (i != rookPosition.getRow()) {
                assertTrue(legalMoves.contains(new Move(rookPosition, new Position(i, rookPosition.getCol()))));
            }
            if (i != rookPosition.getCol()) {
                assertTrue(legalMoves.contains(new Move(rookPosition, new Position(rookPosition.getRow(), i))));
            }
        }
    }
}

