package guiChess;
import guiChess.Pieces.*;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class BoardTest {
    private Player upPlayer;
    private Player downPlayer;
    private Board board;

    @Before
    public void setUp() {
        upPlayer = new Human("Alice", true);
        downPlayer = new Human("Bob", false);
        board = new Board();
        board.addStartingPieces(upPlayer);
        board.addStartingPieces(downPlayer);
    }

    @Test
    public void testIsPathFree() {
        // Test when path is free
        Position from = new Position(3, 3);
        Position to = new Position(5, 5);
        assertTrue(board.isPathFree(from, to));

        // Test when path is blocked
        Position blocked = new Position(4, 4);
        board.placePieceAt(blocked, new Pawn(downPlayer, board));
        assertFalse(board.isPathFree(from, to));
    }

    @Test
    public void testCanMoveToPosition() {
        // Test valid move
        Position from = new Position(3, 3);
        Position to1 = new Position(4, 3);
        assertTrue(board.canMoveToPosition(from, to1, upPlayer, false));

        // Test invalid move (same owner)
        Position to2 = new Position(4, 3);
        board.placePieceAt(to2, new Pawn(upPlayer, board));  // Added this line
        assertFalse(board.canMoveToPosition(from, to2, upPlayer, false));

        // Test invalid move (out of bounds)
        Position to3 = new Position(-1, 3);
        assertFalse(board.canMoveToPosition(from, to3, upPlayer, false));
    }


    @Test
    public void testWontCheckAfterMove() {
        setUp(); // Initializes the board and the players
        GameEngine engine = new GameEngine();
        engine.initUp(upPlayer);
        engine.initDown(downPlayer);

        // Perform some moves
        engine.playMove(new Move(new Position(1, 4), new Position(3, 4))); // Player 1 pawn move
        engine.playMove(new Move(new Position(6, 4), new Position(4, 4))); // Player 2 pawn move

        // Add a custom piece that puts player 1's king in a position where it can be taken
        board.removePieceAt(new Position(6, 5));
        board.placePieceAt(new Position(6, 5), new Rook(upPlayer, board));

        // Verify that moving the king to that position is not a valid move
        Move dangerousMove = new Move(new Position(7, 4), new Position(5, 4));
        assertFalse(board.wontCheckAfterMove(downPlayer, dangerousMove));
    }





    @Test
    public void testGetValidMoves() {
        // Test valid moves for player
        Player player = upPlayer;
        Piece piece = new Pawn(player, board);
        Position position = new Position(1, 2);
        board.placePieceAt(position, piece);
        board.placePieceAt(new Position(2, 3), new Rook(downPlayer, board));
        board.placePieceAt(new Position(1, 1), new Pawn(downPlayer, board));

        Move expectedMove1 = new Move(position, new Position(2, 2));
        Move expectedMove2 = new Move(position, new Position(3, 2));
        Move expectedMove3 = new Move(position, new Position(2, 3));

        assertTrue(board.getValidMoves(player).contains(expectedMove1));
        assertTrue(board.getValidMoves(player).contains(expectedMove2));
        assertTrue(board.getValidMoves(player).contains(expectedMove3));
    }
}

