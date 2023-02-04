package me.pooriya.plotfour.game.checker;

import me.pooriya.plotfour.player.Player;
import org.junit.Test;

import static me.pooriya.plotfour.PlayerObjectMother.first;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class GameStatusTest {

	@Test
	public void notFinished_() {
		GameStatus result = GameStatus.notFinished();
		assertFalse(result.isFinished());
		assertFalse(result.isWinner());
	}

	@Test
	public void finishedWithWinner_() {
		Player player = first();
		GameStatus result = GameStatus.finishedWithWinner();
		assertTrue(result.isFinished());
		assertTrue(result.isWinner());
	}

	@Test
	public void finishedWithDraw_() {
		GameStatus result = GameStatus.finishedWithDraw();
		assertTrue(result.isFinished());
		assertFalse(result.isWinner());
	}

}