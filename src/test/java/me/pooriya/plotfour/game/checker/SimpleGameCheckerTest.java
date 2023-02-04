package me.pooriya.plotfour.game.checker;

import me.pooriya.plotfour.board.Board;
import me.pooriya.plotfour.game.Game;
import me.pooriya.plotfour.player.Player;
import org.junit.Before;
import org.junit.Test;

import static me.pooriya.plotfour.BoardObjectMother.customBoard;
import static me.pooriya.plotfour.PlayerObjectMother.first;
import static me.pooriya.plotfour.PlayerObjectMother.second;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class SimpleGameCheckerTest {
	private Board board;

	private Player first;
	private Player second;
	private Game game;
	private SimpleGameChecker checker;

	@Before
	public void setup() {
		first = first();
		second = second();
		board = customBoard(5, 5);
		game = Game.of(first, second, board);
		checker = new SimpleGameChecker(game);
	}

	@Test
	public void checkVerticalSurrounding_itShouldReturnNullWhenNoSurroundingIsPresent() {
		Player[][] state = board.getState();
		state[3][3] = first;
		assertFalse(checker.checkVerticalSurrounding(3, 3));
	}

	@Test
	public void checkVerticalSurrounding_itShouldReturnFalseWhenSurroundingIsDifferent() {
		Player[][] state = board.getState();
		state[3][3] = first;
		state[2][3] = state[4][3] = second;
		assertFalse(checker.checkVerticalSurrounding(3, 3));
	}

	@Test
	public void checkVerticalSurrounding_itShouldCheckForwardSurrounding() {
		Player[][] state = board.getState();
		state[1][3] = state[2][3] = state[3][3] = state[4][3] =  first;
		assertTrue(checker.checkVerticalSurrounding(1, 3));
	}

	@Test
	public void checkVerticalSurrounding_itShouldCheckBackwardSurrounding() {
		Player[][] state = board.getState();
		state[1][3] = state[2][3] = state[3][3] = state[4][3] =  first;
		assertTrue(checker.checkVerticalSurrounding(4, 3));
	}

	@Test
	public void checkVerticalSurrounding_itShouldCheckCombinedBackwardAndForwardSurrounding() {
		Player[][] state = board.getState();
		state[1][3] = state[2][3] = state[3][3] = state[4][3] =  first;
		assertTrue(checker.checkVerticalSurrounding(3, 3));
	}

	@Test
	public void isThereAnyEmptyCol_itShouldReturnTrue() {
		Player[][] state = board.getState();
		state[0][0] = state[0][1] = state[0][2] = state[0][3] = state[0][4] = first;
		state[1][0] = state[1][1] = state[1][2] = state[1][3] = state[1][4] = second;
		state[2][0] = state[2][1] = state[2][2] = state[2][3] = state[2][4] = first;
		state[3][0] = state[3][1] = state[3][2] = state[3][3] = state[3][4] = second;
		state[4][0] = state[4][1] = state[4][2] = state[4][3]  = first;
		assertTrue(checker.isThereAnyEmptyCol());
	}

	@Test
	public void isThereAnyEmptyCol_itShouldReturnFalse() {
		Player[][] state = board.getState();
		state[0][0] = state[0][1] = state[0][2] = state[0][3] = state[0][4] = first;
		state[1][0] = state[1][1] = state[1][2] = state[1][3] = state[1][4] = second;
		state[2][0] = state[2][1] = state[2][2] = state[2][3] = state[2][4] = first;
		state[3][0] = state[3][1] = state[3][2] = state[3][3] = state[3][4] = second;
		state[4][0] = state[4][1] = state[4][2] = state[4][3] = state[4][4] = first;
		assertFalse(checker.isThereAnyEmptyCol());
	}

	@Test
	public void checkHorizontalSurrounding_itShouldReturnNullWhenNoSurroundingIsPresent() {
		Player[][] state = board.getState();
		state[3][3] = first;
		assertFalse(checker.checkHorizontalSurrounding(3, 3));
	}

	@Test
	public void checkHorizontalSurrounding_itShouldReturnFalseWhenSurroundingIsDifferent() {
		Player[][] state = board.getState();
		state[3][3] = first;
		state[3][2] = state[3][4] = second;
		assertFalse(checker.checkHorizontalSurrounding(3, 3));
	}

	@Test
	public void checkHorizontalSurrounding_itShouldCheckForwardSurrounding() {
		Player[][] state = board.getState();
		state[3][1] = state[3][2] = state[3][3] = state[3][4] =  first;
		assertTrue(checker.checkHorizontalSurrounding(3, 1));
	}

	@Test
	public void checkHorizontalSurrounding_itShouldCheckBackwardSurrounding() {
		Player[][] state = board.getState();
		state[3][1] = state[3][2] = state[3][3] = state[3][4] =  first;
		assertTrue(checker.checkHorizontalSurrounding(3, 4));
	}

	@Test
	public void checkHorizontalSurrounding_itShouldCheckCombinedBackwardAndForwardSurrounding() {
		Player[][] state = board.getState();
		state[3][1] = state[3][2] = state[3][3] = state[3][4] =  first;
		assertTrue(checker.checkHorizontalSurrounding(3, 3));
	}

	@Test
	public void checkDiagonalSurroundings_itShouldReturnFalseWhenNoSurroundings() {
		Player[][] state = board.getState();
		state[3][3] = first;
		assertFalse(checker.checkDiagonalSurroundings(3, 3));
	}

	@Test
	public void checkDiagonalSurroundings_itShouldReturnFalseWhenNoConsecutive() {
		Player[][] state = board.getState();
		state[3][3] = first;
		state[2][2] = state[4][4] = state[4][2] = state[2][4] = second;
		assertFalse(checker.checkDiagonalSurroundings(3, 3));
	}

	@Test
	public void checkDiagonalSurroundings_itShouldReturnTrueWhenUpRightDiagonal() {
		Player[][] state = board.getState();
		state[1][1] = state[2][2] = state[3][3] = state[4][4] = first;
		assertTrue(checker.checkDiagonalSurroundings(1, 1));
	}

	@Test
	public void checkDiagonalSurroundings_itShouldReturnTrueWhenDownLeftDiagonal() {
		Player[][] state = board.getState();
		state[1][1] = state[2][2] = state[3][3] = state[4][4] = first;
		assertTrue(checker.checkDiagonalSurroundings(4, 4));
	}

	@Test
	public void checkDiagonalSurroundings_itShouldReturnTrueWhenDownLeftAndUpRightDiagonal() {
		Player[][] state = board.getState();
		state[1][1] = state[2][2] = state[3][3] = state[4][4] = first;
		assertTrue(checker.checkDiagonalSurroundings(3, 3));
	}

	@Test
	public void checkDiagonalSurroundings_itShouldReturnTrueWhenUpLeftDiagonal() {
		Player[][] state = board.getState();
		state[4][1] = state[3][2] = state[2][3] = state[1][4] = first;
		assertTrue(checker.checkDiagonalSurroundings(1, 4));
	}

	@Test
	public void checkDiagonalSurroundings_itShouldReturnTrueWhenDownRightDiagonal() {
		Player[][] state = board.getState();
		state[4][1] = state[3][2] = state[2][3] = state[1][4] = first;
		assertTrue(checker.checkDiagonalSurroundings(4, 1));
	}

	@Test
	public void checkDiagonalSurroundings_itShouldReturnTrueWhenDownRightAndUpLeftDiagonal() {
		Player[][] state = board.getState();
		state[4][1] = state[3][2] = state[2][3] = state[1][4] = first;
		assertTrue(checker.checkDiagonalSurroundings(2, 3));
	}

	@Test
	public void check_itShouldReturnFinishedWhenConsecutiveVertical() {
		Player[][] state = board.getState();
		state[0][0]= state[1][0] = state[2][0] = state [3][0] = first;
		GameStatus result = checker.check(3, 0);
		assertTrue(result.isFinished());
		assertTrue(result.isWinner());
	}

	@Test
	public void check_itShouldReturnNotFinishedWhenThereIsEmptySlotsAndNoWinner() {
		Player[][] state = board.getState();
		state[0][0] = state[0][2] = first;
		state[0][1] = state [0][3] = second;
		GameStatus result = checker.check(0, 3);
		assertFalse(result.isFinished());
		assertFalse(result.isWinner());
	}

	@Test
	public void check_itShouldReturnDraw() {
		Player[][] state = board.getState();
		state[0][0] = state[0][1] = state[0][2] = state[0][4] = first;
		state[0][3] = second;
		state[1][0] = state[1][1] = state[1][3] = state[1][4] = second;
		state[1][2] = first;
		state[2][0] = state[2][1] = state[2][3] = state[2][4] = first;
		state[2][2] = second;
		state[3][0] = state[3][2] = state[3][3] = state[3][4] = second;
		state[3][1] = first;
		state[4][0] = state[4][1] = state[4][2] = state[4][4] = first;
		state[4][3] = second;
		GameStatus result = checker.check(0, 3);
		assertTrue(result.isFinished());
		assertFalse(result.isWinner());
	}

	@Test
	public void check_itShouldReturnFinishedWhenConsecutiveHorizontal() {
		Player[][] state = board.getState();
		state[0][0]= state[0][1] = state[0][2] = state [0][3] = first;
		GameStatus result = checker.check(0, 2);
		assertTrue(result.isFinished());
		assertTrue(result.isWinner());
	}

	@Test
	public void check_itShouldReturnFinishedWhenConsecutiveAscendingDiagonal() {
		Player[][] state = board.getState();
		state[0][0]= state[1][1] = state[2][2] = state [3][3] = first;
		GameStatus result = checker.check(2, 2);
		assertTrue(result.isFinished());
		assertTrue(result.isWinner());
	}

	@Test
	public void check_itShouldReturnFinishedWhenConsecutiveDescendingDiagonal() {
		Player[][] state = board.getState();
		state[3][1]= state[2][2] = state[1][3] = state [0][4] = first;
		GameStatus result = checker.check(2, 2);
		assertTrue(result.isFinished());
		assertTrue(result.isWinner());
	}

}