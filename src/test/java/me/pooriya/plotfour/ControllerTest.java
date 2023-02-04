package me.pooriya.plotfour;

import me.pooriya.plotfour.board.Board;
import me.pooriya.plotfour.board.BoardSpecification;
import me.pooriya.plotfour.board.creator.BoardCreator;
import me.pooriya.plotfour.board.plotter.BoardPlotter;
import me.pooriya.plotfour.board.reader.BoardReader;
import me.pooriya.plotfour.player.reader.PlayerReader;
import org.junit.Test;

import java.io.ByteArrayOutputStream;

import static me.pooriya.plotfour.BoardObjectMother.defaultBoard;
import static me.pooriya.plotfour.PlayerObjectMother.first;
import static me.pooriya.plotfour.PlayerObjectMother.second;
import static me.pooriya.plotfour.player.Stance.FIRST;
import static me.pooriya.plotfour.player.Stance.SECOND;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.*;

public class ControllerTest {

	@Test
	public void itShouldCreateBoardAndPlayers() {
		ByteArrayOutputStream output = new ByteArrayOutputStream();

		PlayerReader fakePlayerReader = mock(PlayerReader.class);
		when(fakePlayerReader.readPlayer(FIRST)).thenReturn(first());
		when(fakePlayerReader.readPlayer(SECOND)).thenReturn(second());

		BoardReader fakeBoardReader = mock(BoardReader.class);
		when(fakeBoardReader.readSpec()).thenReturn(BoardSpecification.DEFAULT_SPECIFICATION);

		BoardCreator fakeBoardCreator = mock(BoardCreator.class);
		Board fakeBoard = defaultBoard();
		when(fakeBoardCreator.createBoard(BoardSpecification.DEFAULT_SPECIFICATION)).thenReturn(fakeBoard);

		BoardPlotter fakePlotter = mock(BoardPlotter.class);

		Controller controller = Controller.of(output, fakePlayerReader, fakeBoardReader, fakeBoardCreator, fakePlotter);
		Board result = controller.initiateGame();
		assertSame(fakeBoard, result);
		assertEquals("Plot Four\nfirst-player VS second-player\n6 X 7 board\n", output.toString());
		verify(fakePlotter).plot(fakeBoard);
	}

}