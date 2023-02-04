package me.pooriya.plotfour.board;

import lombok.Value;
import me.pooriya.plotfour.board.turn.TurnResult;
import me.pooriya.plotfour.board.turn.TurnResultError;
import me.pooriya.plotfour.board.turn.TurnResultSuccess;
import me.pooriya.plotfour.player.Player;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static java.util.Arrays.asList;
import static me.pooriya.plotfour.BoardObjectMother.defaultBoard;
import static me.pooriya.plotfour.PlayerObjectMother.first;
import static me.pooriya.plotfour.PlayerObjectMother.second;
import static me.pooriya.plotfour.board.turn.TurnResultError.TurnResultErrorType.FULL_COLUMN;
import static me.pooriya.plotfour.board.turn.TurnResultError.TurnResultErrorType.INVALID_COLUMN;
import static org.junit.Assert.*;

public class BoardTest {

	private Board board;
	private Player player;

	@Before
	public void setup() {
		board = defaultBoard();
		player = first();
	}

	@Test
	public void shouldReturnInvalidColumn() {
		assertTurnResultIsInvalidColumn(board.turn(player, 20));
		assertTurnResultIsInvalidColumn(board.turn(player, -1));
		assertTurnResultIsInvalidColumn(board.turn(player, 0));
		assertTurnResultIsInvalidColumn(board.turn(player, board.getSpec().getColumns() + 1));
	}

	private void assertTurnResultIsInvalidColumn(TurnResult result) {
		assertFalse(result.isSuccess());
		assertTrue(result instanceof TurnResultError);
		assertSame(INVALID_COLUMN, ((TurnResultError) result).getErrorType());
	}

	@Test
	public void shouldAddPlayerToEmptyColumn() {
		assertSuccessfulTurnResult(board.turn(player, 1), 0, 0);
		assertSuccessfulTurnResult(board.turn(player, 2), 0, 1);
	}

	@Test
	public void shouldAddPlayerToPreOccupiedColumn() {
		assertSuccessfulTurnResult(board.turn(player, 1), 0, 0);
		assertSuccessfulTurnResult(board.turn(player, 1), 1, 0);
		assertSuccessfulTurnResult(board.turn(player, 1), 2, 0);
		assertSuccessfulTurnResult(board.turn(player, 1), 3, 0);
		assertSuccessfulTurnResult(board.turn(player, 1), 4, 0);
		assertSuccessfulTurnResult(board.turn(player, 1), 5, 0);


		assertSuccessfulTurnResult(board.turn(player, 3), 0, 2);
		assertSuccessfulTurnResult(board.turn(player, 3), 1, 2);
		assertSuccessfulTurnResult(board.turn(player, 3), 2, 2);
		assertSuccessfulTurnResult(board.turn(player, 3), 3, 2);
		assertSuccessfulTurnResult(board.turn(player, 3), 4, 2);
		assertSuccessfulTurnResult(board.turn(player, 3), 5, 2);
	}

	@Test
	public void shouldReturnColumnFull() {
		assertSuccessfulTurnResult(board.turn(player, 1), 0, 0);
		assertSuccessfulTurnResult(board.turn(player, 1), 1, 0);
		assertSuccessfulTurnResult(board.turn(player, 1), 2, 0);
		assertSuccessfulTurnResult(board.turn(player, 1), 3, 0);
		assertSuccessfulTurnResult(board.turn(player, 1), 4, 0);
		assertSuccessfulTurnResult(board.turn(player, 1), 5, 0);
		assertResultTurnIsFullColumn(board.turn(player, 1));
	}

	private void assertResultTurnIsFullColumn(TurnResult result) {
		assertFalse(result.isSuccess());
		assertTrue(result instanceof TurnResultError);
		assertSame(FULL_COLUMN, ((TurnResultError) result).getErrorType());
	}

	private void assertSuccessfulTurnResult(TurnResult result, int i, int j) {
		assertSuccessfulTurnForPlayer(result, i, j, player);
	}

	@Test
	public void shouldReturnSuccessForAnyPlayer() {
		Player anotherPlayer = second();
		assertSuccessfulTurnResult(board.turn(player, 1), 0, 0);
		assertSuccessfulTurnForPlayer(board.turn(anotherPlayer, 1), 1, 0, anotherPlayer);
		assertSuccessfulTurnResult(board.turn(player, 1), 2, 0);
		assertSuccessfulTurnForPlayer(board.turn(anotherPlayer, 1), 3, 0, anotherPlayer);
		assertSuccessfulTurnResult(board.turn(player, 1), 4, 0);
		assertSuccessfulTurnForPlayer(board.turn(anotherPlayer, 1), 5, 0, anotherPlayer);
	}

	private void assertSuccessfulTurnForPlayer(TurnResult result, int i, int j, Player player) {
		assertTrue(result.isSuccess());
		assertTrue(result instanceof TurnResultSuccess);
		assertEquals(i, ((TurnResultSuccess) result).getRowIndex());
		assertEquals(j, ((TurnResultSuccess) result).getColIndex());
		assertSame(player, board.getState()[i][j]);
	}

	@Test
	public void shouldNotTouchOtherColumns() {
		assertSuccessfulTurnResult(board.turn(player, 1), 0, 0);
		assertOtherBlocksAreEmpty(asList(Pair.of(0, 0)));

		assertSuccessfulTurnResult(board.turn(player, 2), 0, 1);
		assertOtherBlocksAreEmpty(asList(Pair.of(0, 0), Pair.of(0, 1)));

		assertSuccessfulTurnResult(board.turn(player, 2), 1, 1);
		assertOtherBlocksAreEmpty(asList(Pair.of(0, 0), Pair.of(0, 1), Pair.of(1, 1)));
	}

	public void assertOtherBlocksAreEmpty(List<Pair> touched) {
		for (int i=0 ; i < board.getSpec().getRows() ; i ++) {
			for (int j=0; j < board.getSpec().getColumns() ; j++) {
				if (!touched.contains(Pair.of(i, j)))
					assertNull(board.getState()[i][j]);
			}
		}
	}

	@Value(staticConstructor = "of")
	private static class Pair {
		int i, j;
	}


}