package me.pooriya.plotfour;

import me.pooriya.plotfour.board.Board;
import me.pooriya.plotfour.board.BoardSpecification;

public class BoardObjectMother {

	public static Board emptyBoard() {
		return Board.of(new int[0][0], BoardSpecification.of(0, 0));
	}

	public static Board defaultBoard() {
		BoardSpecification defaultSpec = BoardSpecification.DEFAULT_SPECIFICATION;
		return Board.of(new int[defaultSpec.getRows()][defaultSpec.getRows()], defaultSpec);
	}

	public static Board customBoard(int row, int col) {
		return Board.of(new int[row][col], BoardSpecification.of(row, col));
	}

}
