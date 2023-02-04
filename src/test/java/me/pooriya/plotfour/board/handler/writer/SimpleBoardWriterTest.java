package me.pooriya.plotfour.board.handler.writer;

import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;

import static org.junit.Assert.assertEquals;

public class SimpleBoardWriterTest {

	private ByteArrayOutputStream output;
	private SimpleBoardWriter writer;

	@Before
	public void setup(){
		output = new ByteArrayOutputStream();
		writer = new SimpleBoardWriter(output);
	}

	@Test
	public void printInputDimensions_itShouldShowDefaultDimensions() {
		writer.printInputDimensions();
		assertEquals("Set the board dimensions (Rows x Columns)\nPress Enter for default (6 x 7)\n> ", output.toString());
	}

	@Test
	public void printInvalidRow_itShouldShowMinAndMaxValues() {
		writer.printInvalidRow();
		assertEquals("Board rows should be from 5 to 9\n", output.toString());
	}

	@Test
	public void printInvalidColumn_itShouldShowMinAndMaxValues() {
		writer.printInvalidColumn();
		assertEquals("Board columns should be from 5 to 9\n", output.toString());
	}

	@Test
	public void printInvalidInput() {
		writer.printInvalidInput();
		assertEquals("Invalid input\n", output.toString());
	}

}