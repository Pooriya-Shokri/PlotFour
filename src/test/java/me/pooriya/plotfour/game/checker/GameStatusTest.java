package me.pooriya.plotfour.game.checker;

import me.pooriya.plotfour.player.Player;
import org.junit.Test;

import static me.pooriya.plotfour.PlayerObjectMother.first;
import static org.junit.Assert.*;

public class GameStatusTest {

	@Test
	public void notFinished_() {
		GameStatus result = GameStatus.notFinished();
		assertFalse(result.isFinished());
		assertNull(result.getWinner());
	}

	@Test
	public void finishedWithWinner_() {
		Player player = first();
		GameStatus result = GameStatus.finishedWithWinner(player);
		assertTrue(result.isFinished());
		assertSame(player, result.getWinner());
	}

	@Test
	public void finishedWithDraw_() {
		GameStatus result = GameStatus.finishedWithDraw();
		assertTrue(result.isFinished());
		assertNull(result.getWinner());
	}

}