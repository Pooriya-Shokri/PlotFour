package me.pooriya.plotfour.board.plotter;

import me.pooriya.plotfour.board.Board;
import org.junit.Test;

import java.io.ByteArrayOutputStream;

import static me.pooriya.plotfour.BoardObjectMother.defaultBoard;
import static me.pooriya.plotfour.BoardObjectMother.emptyBoard;
import static org.junit.Assert.assertEquals;

public class SimpleBoardPlotterTest {

	@Test
	public void shouldPlotNothing() {
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		SimpleBoardPlotter plotter = new SimpleBoardPlotter(output);
		Board emptyBoard = emptyBoard();
		plotter.plot(emptyBoard);
		assertEquals("", output.toString());
	}

	@Test
	public void shouldAddColumnNumbers() {
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		SimpleBoardPlotter plotter = new SimpleBoardPlotter(output);
		Board board = defaultBoard();
		plotter.plot(board);
		String[] outputLines = output.toString().split("\n");
		assertEquals(" 1 2 3 4 5 6 7", outputLines[0]);
	}

	@Test
	public void shouldAddColumnBarsForEachRow() {
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		SimpleBoardPlotter plotter = new SimpleBoardPlotter(output);
		Board board = defaultBoard();
		plotter.plot(board);
		String[] outputLines = output.toString().split("\n");

		// outputLines[0] is Already checked
		for (int i = 1; i < 7 ; i++) {    // checking 6 lines
			assertEquals("| | | | | | | | ", outputLines[i]);
		}
	}

	@Test
	public void shouldAddFinalRow() {
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		SimpleBoardPlotter plotter = new SimpleBoardPlotter(output);
		Board board = defaultBoard();
		plotter.plot(board);
		String[] outputLines = output.toString().split("\n");

		assertEquals("===============", outputLines[7]);
	}

}