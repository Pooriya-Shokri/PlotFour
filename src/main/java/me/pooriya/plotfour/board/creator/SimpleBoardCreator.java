package me.pooriya.plotfour.board.creator;

import lombok.NonNull;
import me.pooriya.plotfour.board.Board;
import me.pooriya.plotfour.board.BoardSpecification;

public class SimpleBoardCreator implements BoardCreator {

	@Override
	public Board createBoard(@NonNull BoardSpecification spec) {
		int columns = spec.getColumns();
		int rows = spec.getRows();
		if (rows * columns != 0)
			return Board.of(new int[rows][columns], spec);
		return Board.of(new int[0][], spec);
	}

}
