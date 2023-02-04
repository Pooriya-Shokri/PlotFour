package me.pooriya.plotfour.game.handler;

import me.pooriya.plotfour.board.Board;
import me.pooriya.plotfour.board.plotter.BoardPlotter;
import me.pooriya.plotfour.game.Game;
import me.pooriya.plotfour.game.reader.EndgameException;
import me.pooriya.plotfour.game.reader.GameReader;
import me.pooriya.plotfour.game.writer.GameWriter;
import me.pooriya.plotfour.player.Player;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static me.pooriya.plotfour.PlayerObjectMother.first;
import static me.pooriya.plotfour.PlayerObjectMother.second;
import static me.pooriya.plotfour.board.Board.TurnResult.*;
import static me.pooriya.plotfour.board.BoardSpecification.DEFAULT_SPECIFICATION;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class SimpleGameHandlerTest {

	@Mock
	private Board board;
	private Player first;
	private Player second;
	@Mock
	private GameWriter writer;
	@Mock
	private GameReader reader;
	@Mock
	private BoardPlotter plotter;
	private SimpleGameHandler handler;

	@Before
	public void setup() {
		first = first();
		second = second();
		handler = new SimpleGameHandler(reader, writer, Game.of(first, second, board), plotter);
		when(board.getSpec()).thenReturn(DEFAULT_SPECIFICATION);
	}

	@Test
	public void getPlayerCol_itShouldPrintFirstPlayerAndReadItsInputAtInitiation() {
		when(reader.readNextColumn()).thenReturn(1);
		assertEquals(1, handler.getPlayerCol(first));
		verify(writer).printInputPlayerTurn(first);
		verify(reader).readNextColumn();
	}

	@Test
	public void getPlayerCol_itShouldRetryGettingValueWhenInputIsInvalid() {
		when(reader.readNextColumn()).thenThrow(IllegalArgumentException.class).thenReturn(2);
		assertEquals(2, handler.getPlayerCol(first));
		verify(writer, times(2)).printInputPlayerTurn(first);
		verify(reader, times(2)).readNextColumn();
		verify(writer).printIncorrectColumnNumber();
	}

	@Test
	public void handlePlayerColSelection_itShouldReturnWhenBoardAccepts() {
		handler = spy(handler);
		when(handler.getPlayerCol(any())).thenReturn(3);
		when(board.turn(first, 3)).thenReturn(SUCCESS);
		assertFalse(handler.handlePlayerColSelection(first));
		verify(plotter).plot(board);
	}

	@Test
	public void handlePlayerColSelection_itShouldTryAgainWhenBoardColIsFull() {
		handler = spy(handler);
		when(handler.getPlayerCol(any())).thenReturn(3).thenReturn(4);
		when(board.turn(eq(first), anyInt())).thenReturn(FULL_COLUMN).thenReturn(SUCCESS);
		assertFalse(handler.handlePlayerColSelection(first));
		verify(handler, times(2)).getPlayerCol(first);
	}

	@Test
	public void handlePlayerColSelection_itShouldTryAgainWhenBoardColIsInvalid() {
		handler = spy(handler);
		when(handler.getPlayerCol(any())).thenReturn(17).thenReturn(5);
		when(board.turn(eq(first), anyInt())).thenReturn(INVALID_COLUMN).thenReturn(SUCCESS);
		assertFalse(handler.handlePlayerColSelection(first));
		verify(handler, times(2)).getPlayerCol(first);
	}

	@Test
	public void handlePlayerColSelection_itShouldReturnTrueToEndGame() {
		handler = spy(handler);
		when(handler.getPlayerCol(any())).thenThrow(EndgameException.class);
		assertTrue(handler.handlePlayerColSelection(first));
	}

	@Test
	public void handle_itShouldGotoNextPlayerUntilEndgame() {
		handler = spy(handler);
		doReturn(false).doReturn(false).doReturn(false).doReturn(false).doReturn(true)
				.when(handler).handlePlayerColSelection(any());
		handler.handle();
		ArgumentCaptor<Player> handleArgCaptor = ArgumentCaptor.forClass(Player.class);
		verify(handler, times(5)).handlePlayerColSelection(handleArgCaptor.capture());
		assertSame(first, handleArgCaptor.getAllValues().get(0));
		assertSame(second, handleArgCaptor.getAllValues().get(1));
		assertSame(first, handleArgCaptor.getAllValues().get(2));
		assertSame(second, handleArgCaptor.getAllValues().get(3));
		assertSame(first, handleArgCaptor.getAllValues().get(4));
		verify(writer).printGameOver();
	}

}