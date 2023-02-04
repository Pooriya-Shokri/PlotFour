package me.pooriya.plotfour.game.checker;

import lombok.NonNull;
import lombok.Value;
import me.pooriya.plotfour.board.Board;
import me.pooriya.plotfour.board.BoardSpecification;
import me.pooriya.plotfour.game.Game;
import me.pooriya.plotfour.player.Player;

import static me.pooriya.plotfour.game.checker.GameStatus.*;

@Value
public class SimpleGameChecker implements GameChecker {

	@NonNull Game game;

	@Override
	public GameStatus check(int rowIndex, int colIndex) {
		if (checkVerticalSurrounding(rowIndex, colIndex)
				|| checkHorizontalSurrounding(rowIndex, colIndex)
				|| checkDiagonalSurroundings(rowIndex, colIndex))
			return finishedWithWinner();
		return isThereAnyEmptyCol() ? notFinished() : finishedWithDraw();
	}

	boolean checkVerticalSurrounding(int rowIndex, int colIndex) {
		Board board = game.getBoard();
		int forwardSurSize = countVerticalForwardSurrounding(rowIndex, colIndex, board);
		int backwardSurSize = countVerticalBackwardSurrounding(rowIndex, colIndex, board);
		return backwardSurSize + forwardSurSize >= 3;
	}

	private static int countVerticalBackwardSurrounding(int rowIndex, int colIndex, Board board) {
		int backwarSurSize = 0;
		for (int i = 1; i < 4 && rowIndex - i >= 0 ; i++) {
			if (board.getState()[rowIndex][colIndex].equals(board.getState()[rowIndex - i][colIndex]))
				backwarSurSize++;
			else
				break;
		}
		return backwarSurSize;
	}

	private static int countVerticalForwardSurrounding(int rowIndex, int colIndex, Board board) {
		int forwardSurSize = 0;
		for (int i = 1; i < 4 && rowIndex + i < board.getSpec().getRows() ; i++) {
			if (board.getState()[rowIndex][colIndex].equals(board.getState()[rowIndex + i][colIndex]))
				forwardSurSize++;
			else
				break;
		}
		return forwardSurSize;
	}

	boolean checkHorizontalSurrounding(int rowIndex, int colIndex) {
		Board board = game.getBoard();
		int forwardSurSize = countHorizontalForwardSurrounding(rowIndex, colIndex, board);
		int backwardSurSize = countHorizontalBackwardSurrounding(rowIndex, colIndex, board);
		return backwardSurSize + forwardSurSize >= 3;
	}

	private static int countHorizontalBackwardSurrounding(int rowIndex, int colIndex, Board board) {
		int backwarSurSize = 0;
		for (int i = 1; i < 4 && colIndex - i >= 0 ; i++) {
			if (board.getState()[rowIndex][colIndex].equals(board.getState()[rowIndex][colIndex - i]))
				backwarSurSize++;
			else
				break;
		}
		return backwarSurSize;
	}

	private static int countHorizontalForwardSurrounding(int rowIndex, int colIndex, Board board) {
		int forwardSurSize = 0;
		for (int i = 1; i < 4 && colIndex + i < board.getSpec().getColumns() ; i++) {
			if (board.getState()[rowIndex][colIndex].equals(board.getState()[rowIndex][colIndex + i]))
				forwardSurSize++;
			else
				break;
		}
		return forwardSurSize;
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

	boolean checkDiagonalSurroundings(int rowIndex, int colIndex) {
		BoardSpecification spec = game.getBoard().getSpec();
		Player[][] state = game.getBoard().getState();

		int upRightWingCount = countUpRightWing(rowIndex, colIndex, spec, state);
		int downLeftWingCount = countDownLeftWing(rowIndex, colIndex, state);

		int downRightWingCount = countDownRightWing(rowIndex, colIndex, spec, state);
		int upLeftWingCount = countUpLeftWing(rowIndex, colIndex, spec, state);

		return (downLeftWingCount + upRightWingCount >= 3) || (downRightWingCount + upLeftWingCount >= 3);
	}

	private static int countDownLeftWing(int rowIndex, int colIndex, Player[][] state) {
		int downLeftWingCount = 0;
		for (int i = 1; i < 4 && rowIndex - i >= 0 && colIndex - i >= 0 ; i++) {
			if (state[rowIndex][colIndex].equals(state[rowIndex - i][colIndex - i]))
				downLeftWingCount++;
			else
				break;
		}
		return downLeftWingCount;
	}

	private static int countDownRightWing(int rowIndex, int colIndex, BoardSpecification spec, Player[][] state) {
		int downRightWingCount = 0;
		for (int i = 1; i < 4 && rowIndex - i >= 0 && colIndex + i < spec.getColumns() ; i++) {
			if (state[rowIndex][colIndex].equals(state[rowIndex - i][colIndex + i]))
				downRightWingCount++;
			else
				break;
		}
		return downRightWingCount;
	}

	private static int countUpRightWing(int rowIndex, int colIndex, BoardSpecification spec, Player[][] state) {
		int upRightWingCount = 0;
		for (int i = 1; i < 4 && rowIndex + i < spec.getRows() && colIndex + i < spec.getColumns() ; i++) {
			if (state[rowIndex][colIndex].equals(state[rowIndex + i][colIndex + i]))
				upRightWingCount++;
			else
				break;
		}
		return upRightWingCount;
	}

	private static int countUpLeftWing(int rowIndex, int colIndex, BoardSpecification spec, Player[][] state) {
		int upLeftWingCount = 0;
		for (int i = 1; i < 4 && rowIndex + i < spec.getRows() && colIndex - i >= 0 ; i++) {
			if (state[rowIndex][colIndex].equals(state[rowIndex + i][colIndex - i]))
				upLeftWingCount++;
			else
				break;
		}
		return upLeftWingCount;
	}

}
