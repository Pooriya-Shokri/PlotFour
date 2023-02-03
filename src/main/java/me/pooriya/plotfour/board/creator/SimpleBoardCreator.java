package me.pooriya.plotfour.board.creator;

import me.pooriya.plotfour.board.BoardSpecification;

public class SimpleBoardCreator implements BoardCreator {

	@Override
	public int[][] createBoard(BoardSpecification spec) {
		int columns = spec.getColumns();
		int rows = spec.getRows();
		if (rows * columns != 0)
			return new int[rows][columns];
		return new int[0][];
	}


}
