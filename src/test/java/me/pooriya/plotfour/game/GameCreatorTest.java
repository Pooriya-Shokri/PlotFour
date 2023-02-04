package me.pooriya.plotfour.game;

import me.pooriya.plotfour.board.Board;
import me.pooriya.plotfour.board.handler.BoardHandler;
import me.pooriya.plotfour.board.plotter.BoardPlotter;
import me.pooriya.plotfour.player.Player;
import me.pooriya.plotfour.player.reader.PlayerReader;
import org.junit.Test;

import java.io.ByteArrayOutputStream;

import static me.pooriya.plotfour.BoardObjectMother.customBoard;
import static me.pooriya.plotfour.PlayerObjectMother.first;
import static me.pooriya.plotfour.PlayerObjectMother.second;
import static me.pooriya.plotfour.player.Stance.FIRST;
import static me.pooriya.plotfour.player.Stance.SECOND;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.*;

public class GameCreatorTest {

	@Test
	public void itShouldCreateBoardAndPlayers() {
		ByteArrayOutputStream output = new ByteArrayOutputStream();

		PlayerReader fakePlayerReader = mock(PlayerReader.class);
		Player first = first();
		when(fakePlayerReader.readPlayer(FIRST)).thenReturn(first);
		Player second = second();
		when(fakePlayerReader.readPlayer(SECOND)).thenReturn(second);

		Board fakeBoard = customBoard(6, 7);
		BoardHandler fakeBoardHandler = mock(BoardHandler.class);
		when(fakeBoardHandler.handle()).thenReturn(fakeBoard);

		BoardPlotter fakePlotter = mock(BoardPlotter.class);

		GameCreator gameCreator = GameCreator.of(output, fakePlayerReader, fakeBoardHandler, fakePlotter);
		Game result = gameCreator.initiateGame();
		assertSame(fakeBoard, result.getBoard());
		assertSame(first, result.getFirst());
		assertSame(second, result.getSecond());

		assertEquals(String.format("Plot Four\n%s VS %s\n6 x 7 board\n", first.getName(), second.getName()), output.toString());
		verify(fakePlotter).plot(fakeBoard);
	}

}