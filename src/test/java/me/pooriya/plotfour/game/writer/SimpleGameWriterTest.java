package me.pooriya.plotfour.game.writer;

import me.pooriya.plotfour.player.Player;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;

import static me.pooriya.plotfour.PlayerObjectMother.first;
import static org.junit.Assert.assertEquals;

public class SimpleGameWriterTest {

	private SimpleGameWriter writer;
	private OutputStream output;

	@Before
	public void setup() {
		output = new ByteArrayOutputStream();
		writer = new SimpleGameWriter(output);
	}

	@Test
	public void printInputPlayerTurn_itShouldPrintPlayerName() {
		Player first = first();
		writer.printInputPlayerTurn(first);
		assertEquals(String.format("%s's turn:%n> ", first.getName()), output.toString());
	}

	@Test
	public void printInputOutOfRange_itShouldPrintRange() {
		writer.printInputOutOfRange(7);
		assertEquals("The column number is out of range (1 - 7)\n", output.toString());
	}

	@Test
	public void printIncorrectColumnNumber_itShouldPrintInvalidCol() {
		writer.printIncorrectColumnNumber();
		assertEquals("Incorrect column number\n", output.toString());
	}

	@Test
	public void printColumnIsFull_itShouldPrintColumnIsFull() {
		writer.printColumnIsFull(5);
		assertEquals("Column 5 is full\n", output.toString());
	}

	@Test
	public void printGameOver_itShouldPrintGameOver() {
		writer.printGameOver();
		assertEquals("Game over!\n", output.toString());
	}

}