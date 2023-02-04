package me.pooriya.plotfour;

import me.pooriya.plotfour.board.Board;
import me.pooriya.plotfour.board.BoardSpecification;
import me.pooriya.plotfour.player.Player;

import static me.pooriya.plotfour.board.BoardSpecification.DEFAULT_SPECIFICATION;

public class BoardObjectMother {

	public static Board emptyBoard() {
		return customBoard(0, 0);
	}

	public static Board defaultBoard() {
		return customBoard(DEFAULT_SPECIFICATION.getRows(), DEFAULT_SPECIFICATION.getColumns());
	}

	public static Board customBoard(int row, int col) {
		return Board.of(new Player[row][col], BoardSpecification.of(row, col));
	}

}
