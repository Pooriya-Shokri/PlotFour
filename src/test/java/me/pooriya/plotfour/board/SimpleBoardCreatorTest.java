package me.pooriya.plotfour.board;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SimpleBoardCreatorTest {

	@Test
	public void shouldReturnEmptyBoard() {
		SimpleBoardCreator creator = new SimpleBoardCreator();
		int[][] result = creator.createBoard(0, 0);
		assertEquals(0, result.length);
	}

}