package me.pooriya.plotfour.game.checker;

import lombok.NonNull;
import lombok.Value;
import me.pooriya.plotfour.board.Board;
import me.pooriya.plotfour.game.Game;

import static me.pooriya.plotfour.game.checker.GameStatus.*;

@Value
public class SimpleGameChecker implements GameChecker {

	@NonNull Game game;

	@Override
	public GameStatus check(int rowIndex, int colIndex) {
		if (checkVerticalSurrounding(rowIndex, colIndex) || checkHorizontalSurrounding(rowIndex, colIndex))
			return finishedWithWinner();
		return isThereAnyEmptyCol() ? notFinished() : finishedWithDraw();
	}

	boolean checkVerticalSurrounding(int rowIndex, int colIndex) {
		Board board = game.getBoard();

		int forwardSurSize = 0;
		for (int i = 1; i < 4 && rowIndex + i < board.getSpec().getRows() ; i++) {
			if (board.getState()[rowIndex][colIndex].equals(board.getState()[rowIndex + i][colIndex]))
				forwardSurSize++;
			else
				break;
		}

		int backwarSurSize = 0;
		for (int i = 1; i < 4 && rowIndex - i >= 0 ; i++) {
			if (board.getState()[rowIndex][colIndex].equals(board.getState()[rowIndex - i][colIndex]))
				backwarSurSize++;
			else
				break;
		}

		return backwarSurSize + forwardSurSize >= 3;
	}

	boolean checkHorizontalSurrounding(int rowIndex, int colIndex) {
		Board board = game.getBoard();

		int forwardSurSize = 0;
		for (int i = 1; i < 4 && colIndex + i < board.getSpec().getColumns() ; i++) {
			if (board.getState()[rowIndex][colIndex].equals(board.getState()[rowIndex][colIndex + i]))
				forwardSurSize++;
			else
				break;
		}

		int backwarSurSize = 0;
		for (int i = 1; i < 4 && colIndex - i >= 0 ; i++) {
			if (board.getState()[rowIndex][colIndex].equals(board.getState()[rowIndex][colIndex - i]))
				backwarSurSize++;
			else
				break;
		}

		return backwarSurSize + forwardSurSize >= 3;
	}

	boolean isThereAnyEmptyCol() {
		Board board = game.getBoard();
		int columns = board.getSpec().getColumns();
		int rows = board.getSpec().getRows();
		for (int i = 0; i < columns; i++)
			if (board.getState()[rows -1][i] == null)
				return true;

		return false;
	}

}
