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
        assertFalse(board.canMoveToPosition(from, to2, upPlayer, false));

        // Test invalid move (out of bounds)
        Position to3 = new Position(-1, 3);
        assertFalse(board.canMoveToPosition(from, to3, upPlayer, false));
    }

    @Test
    public void testWontCheckAfterMove() {
        // Test when move does not result in check
        Position from = new Position(3, 3);
        Position to = new Position(4, 3);
        Move move = new Move(from, to);
        assertTrue(board.wontCheckAfterMove(upPlayer, move));

        // Test when move results in check
        board.placePieceAt(to, new Rook(downPlayer, board));
        assertFalse(board.wontCheckAfterMove(upPlayer, move));
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

    @Test
    public void testIsInCheck() {
        // Test when king is not in check
        Player player = upPlayer;
        Position kingPosition = new Position(4, 4);
        Piece king = new King(player, board);
        board.placePieceAt(kingPosition, king);
        System.out.println(kingPosition.getCol() + ":" + kingPosition.getRow());
        assertFalse(board.isInCheck(player, kingPosition));

        // Test when king is in check
        board.placePieceAt(new Position(3, 3), new Rook(downPlayer, board));
        assertTrue(board.isInCheck(player, kingPosition));
    }
}

