package me.pooriya.plotfour;

import me.pooriya.plotfour.board.Board;
import me.pooriya.plotfour.board.BoardSpecification;
import me.pooriya.plotfour.board.creator.BoardCreator;
import me.pooriya.plotfour.board.reader.BoardReader;
import me.pooriya.plotfour.player.Player;
import me.pooriya.plotfour.player.reader.PlayerReader;
import org.junit.Test;

import java.io.ByteArrayOutputStream;

import static me.pooriya.plotfour.player.Stance.FIRST;
import static me.pooriya.plotfour.player.Stance.SECOND;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ControllerTest {

	@Test
	public void itShouldCreateBoardAndPlayers() {
		ByteArrayOutputStream output = new ByteArrayOutputStream();

		PlayerReader fakePlayerReader = mock(PlayerReader.class);
		when(fakePlayerReader.readPlayer(FIRST)).thenReturn(Player.of("first-player", FIRST));
		when(fakePlayerReader.readPlayer(SECOND)).thenReturn(Player.of("second-player", SECOND));

		BoardReader fakeBoardReader = mock(BoardReader.class);
		when(fakeBoardReader.readSpec()).thenReturn(BoardSpecification.DEFAULT_SPECIFICATION);

		BoardCreator fakeBoardCreator = mock(BoardCreator.class);
		Board fakeBoard = Board.of(new int[0][]);
		when(fakeBoardCreator.createBoard(BoardSpecification.DEFAULT_SPECIFICATION)).thenReturn(fakeBoard);

		Controller controller = Controller.of(output, fakePlayerReader, fakeBoardReader, fakeBoardCreator);
		Board result = controller.initiateGame();
		assertSame(fakeBoard, result);
		assertEquals("Plot Four\nfirst-player VS second-player\n6 X 7 board\n", output.toString());
	}

}