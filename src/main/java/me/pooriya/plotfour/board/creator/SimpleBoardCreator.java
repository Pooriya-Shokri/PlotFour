package me.pooriya.plotfour.board.creator;

import me.pooriya.plotfour.board.Board;
import me.pooriya.plotfour.board.BoardSpecification;

public class SimpleBoardCreator implements BoardCreator {

	@Override
	public Board createBoard(BoardSpecification spec) {
		int columns = spec.getColumns();
		int rows = spec.getRows();
		if (rows * columns != 0)
			return Board.of(new int[rows][columns]);
		return Board.of(new int[0][]);
	}

}
