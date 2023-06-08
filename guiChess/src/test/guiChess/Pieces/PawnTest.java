package guiChess.Pieces;

import guiChess.*;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class PawnTest {
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
    public void testGetLegalMoves_UpPlayer_InitialPosition() {
        Position pawnPosition = new Position(1, 3); // Assuming initial position for a pawn
        Pawn pawn = new Pawn(upPlayer, board);
        board.placePieceAt(pawnPosition, pawn);

        List<Move> legalMoves = pawn.getLegalMoves();
        // print all the moves to and from positions row and col

        // Assert that the pawn has two legal moves forward
        assertEquals(2, legalMoves.size());
        assertTrue(legalMoves.contains(new Move(pawnPosition, new Position(2, 3))));
        assertTrue(legalMoves.contains(new Move(pawnPosition, new Position(3, 3))));
    }

    @Test
    public void testGetLegalMoves_DownPlayer_InitialPosition() {
        Position pawnPosition = new Position(6, 3); // Assuming initial position for a pawn
        Pawn pawn = new Pawn(downPlayer, board);
        board.placePieceAt(pawnPosition, pawn);

        List<Move> legalMoves = pawn.getLegalMoves();

        // Assert that the pawn has two legal moves forward
        assertEquals(2, legalMoves.size());
        assertTrue(legalMoves.contains(new Move(pawnPosition, new Position(5, 3))));
        assertTrue(legalMoves.contains(new Move(pawnPosition, new Position(4, 3))));
    }

    @Test
    public void testGetLegalMoves_UpPlayer_MoveAfterInitialPosition() {
        Position pawnPosition = new Position(2, 3);
        Pawn pawn = new Pawn(upPlayer, board);
        board.placePieceAt(pawnPosition, pawn);

        List<Move> legalMoves = pawn.getLegalMoves();

        // Assert that the pawn has one legal move forward
        assertEquals(1, legalMoves.size());
        assertTrue(legalMoves.contains(new Move(pawnPosition, new Position(3, 3))));
    }

    @Test
    public void testGetLegalMoves_DownPlayer_MoveAfterInitialPosition() {
        Position pawnPosition = new Position(5, 3);
        Pawn pawn = new Pawn(downPlayer, board);
        board.placePieceAt(pawnPosition, pawn);

        List<Move> legalMoves = pawn.getLegalMoves();

        // Assert that the pawn has one legal move forward
        assertEquals(1, legalMoves.size());
        assertTrue(legalMoves.contains(new Move(pawnPosition, new Position(4, 3))));
    }

    @Test
    public void testGetLegalMoves_CaptureOpponentPiece() {
        Position pawnPosition = new Position(2, 2);
        Pawn pawn = new Pawn(upPlayer, board);
        board.placePieceAt(pawnPosition, pawn);

        Position opponentPiecePosition = new Position(3, 3);
        Pawn opponentPawn = new Pawn(downPlayer, board);
        Position opponent2PiecePosition = new Position(3, 1);
        Pawn opponent2Pawn = new Pawn(downPlayer, board);
        board.placePieceAt(opponentPiecePosition, opponentPawn);
        board.placePieceAt(opponent2PiecePosition, opponent2Pawn);

        List<Move> legalMoves = pawn.getLegalMoves();

        assertTrue(legalMoves.contains(new Move(pawnPosition, new Position(3, 3))));
        assertTrue(legalMoves.contains(new Move(pawnPosition, new Position(3, 1))));

    }
    @Test
    public void testGetLegalMoves_AvoidSameOwnerCapture() {
        Position pawnPosition = new Position(2, 2);
        Pawn pawn = new Pawn(upPlayer, board);
        board.placePieceAt(pawnPosition, pawn);

        Position sameOwnerPiecePosition = new Position(3, 1);
        Pawn sameOwnerPawn = new Pawn(upPlayer, board);
        board.placePieceAt(sameOwnerPiecePosition, sameOwnerPawn);

        List<Move> legalMoves = pawn.getLegalMoves();

        // Assert that the pawn cannot capture the same-owner piece
        assertTrue(!legalMoves.contains(new Move(pawnPosition, sameOwnerPiecePosition)));
    }
}
