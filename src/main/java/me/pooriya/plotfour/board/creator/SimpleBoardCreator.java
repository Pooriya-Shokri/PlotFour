package me.pooriya.plotfour.board.creator;

public class SimpleBoardCreator implements BoardCreator {

	@Override
	public int[][] createBoard(int rows, int columns) {
		if (rows * columns != 0)
			return new int[rows][columns];
		return new int[0][];
	}


}
