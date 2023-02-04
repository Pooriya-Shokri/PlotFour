package me.pooriya.plotfour.board;

import lombok.Value;
import me.pooriya.plotfour.board.Board.TurnResult;
import me.pooriya.plotfour.player.Player;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static java.util.Arrays.asList;
import static me.pooriya.plotfour.BoardObjectMother.defaultBoard;
import static me.pooriya.plotfour.PlayerObjectMother.first;
import static me.pooriya.plotfour.PlayerObjectMother.second;
import static me.pooriya.plotfour.board.Board.TurnResult.*;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;

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
		assertSame(INVALID_COLUMN, board.turn(player, 20));
		assertSame(INVALID_COLUMN, board.turn(player, -1));
		assertSame(INVALID_COLUMN, board.turn(player, 0));
		assertSame(INVALID_COLUMN, board.turn(player, board.getSpec().getColumns() + 1));
	}

	@Test
	public void shouldAddPlayerToEmptyColumn() {
		assertSame(SUCCESS, board.turn(player, 1));
		assertSame(player, board.getState()[0][0]);

		assertSame(SUCCESS, board.turn(player, 2));
		assertSame(player, board.getState()[0][1]);
	}

	@Test
	public void shouldAddPlayerToPreOccupiedColumn() {
		assertSuccessfulTurn(board.turn(player, 1), 0, 0);
		assertSuccessfulTurn(board.turn(player, 1), 1, 0);
		assertSuccessfulTurn(board.turn(player, 1), 2, 0);
		assertSuccessfulTurn(board.turn(player, 1), 3, 0);
		assertSuccessfulTurn(board.turn(player, 1), 4, 0);
		assertSuccessfulTurn(board.turn(player, 1), 5, 0);


		assertSuccessfulTurn(board.turn(player, 3), 0, 2);
		assertSuccessfulTurn(board.turn(player, 3), 1, 2);
		assertSuccessfulTurn(board.turn(player, 3), 2, 2);
		assertSuccessfulTurn(board.turn(player, 3), 3, 2);
		assertSuccessfulTurn(board.turn(player, 3), 4, 2);
		assertSuccessfulTurn(board.turn(player, 3), 5, 2);
	}

	@Test
	public void shouldReturnColumnFull() {
		assertSuccessfulTurn(board.turn(player, 1), 0, 0);
		assertSuccessfulTurn(board.turn(player, 1), 1, 0);
		assertSuccessfulTurn(board.turn(player, 1), 2, 0);
		assertSuccessfulTurn(board.turn(player, 1), 3, 0);
		assertSuccessfulTurn(board.turn(player, 1), 4, 0);
		assertSuccessfulTurn(board.turn(player, 1), 5, 0);
		assertSame(FULL_COLUMN, board.turn(player, 1));
	}

	private void assertSuccessfulTurn(TurnResult result, int i, int j) {
		assertSuccessfulTurnForPlayer(result, i, j, player);
	}

	@Test
	public void shouldReturnSuccessForAnyPlayer() {
		Player anotherPlayer = second();
		assertSuccessfulTurn(board.turn(player, 1), 0, 0);
		assertSuccessfulTurnForPlayer(board.turn(anotherPlayer, 1), 1, 0, anotherPlayer);
		assertSuccessfulTurn(board.turn(player, 1), 2, 0);
		assertSuccessfulTurnForPlayer(board.turn(anotherPlayer, 1), 3, 0, anotherPlayer);
		assertSuccessfulTurn(board.turn(player, 1), 4, 0);
		assertSuccessfulTurnForPlayer(board.turn(anotherPlayer, 1), 5, 0, anotherPlayer);
	}

	private void assertSuccessfulTurnForPlayer(TurnResult result, int i, int j, Player player) {
		assertSame(SUCCESS, result);
		assertSame(player, board.getState()[i][j]);
	}

	@Test
	public void shouldNotTouchOtherColumns() {
		assertSuccessfulTurn(board.turn(player, 1), 0, 0);
		assertOtherBlocksAreEmpty(asList(Pair.of(0, 0)));

		assertSuccessfulTurn(board.turn(player, 2), 0, 1);
		assertOtherBlocksAreEmpty(asList(Pair.of(0, 0), Pair.of(0, 1)));

		assertSuccessfulTurn(board.turn(player, 2), 1, 1);
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