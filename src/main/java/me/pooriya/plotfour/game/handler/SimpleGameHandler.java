package me.pooriya.plotfour.game.handler;

import lombok.NonNull;
import lombok.Value;
import lombok.experimental.NonFinal;
import me.pooriya.plotfour.board.plotter.BoardPlotter;
import me.pooriya.plotfour.board.turn.TurnResult;
import me.pooriya.plotfour.board.turn.TurnResultError;
import me.pooriya.plotfour.board.turn.TurnResultSuccess;
import me.pooriya.plotfour.game.Game;
import me.pooriya.plotfour.game.checker.GameChecker;
import me.pooriya.plotfour.game.checker.GameStatus;
import me.pooriya.plotfour.game.reader.EndgameException;
import me.pooriya.plotfour.game.reader.GameReader;
import me.pooriya.plotfour.game.writer.GameWriter;
import me.pooriya.plotfour.player.Player;

import static me.pooriya.plotfour.board.turn.TurnResultError.TurnResultErrorType.FULL_COLUMN;
import static me.pooriya.plotfour.board.turn.TurnResultError.TurnResultErrorType.INVALID_COLUMN;


@Value
@NonFinal
public class SimpleGameHandler implements GameHandler {

	@NonNull GameReader reader;

	@NonNull GameWriter writer;

	@NonNull Game game;

	@NonNull BoardPlotter plotter;

	@NonNull GameChecker checker;

	@Override
	public void handle() {
		Player currentPlayer = game.getFirst();
		while (!handlePlayerColSelection(currentPlayer)) {
			currentPlayer = currentPlayer != game.getFirst() ? game.getFirst() : game.getSecond();
		}
		writer.printGameOver();
	}

	boolean handlePlayerColSelection(Player player) {
		try {
			int col = getPlayerCol(player);
			TurnResult result = game.getBoard().turn(player, col);
			if (result.isSuccess()) {
				TurnResultSuccess success = (TurnResultSuccess) result;
				plotter.plot(game.getBoard());
				GameStatus checkResult = checker.check(success.getRowIndex(), success.getColIndex());
				if (checkResult.isFinished()) {
					if (checkResult.isWinner())
						writer.printPlayerWin(player);
					else
						writer.printDraw();
				}
				return checkResult.isFinished();
			}
			TurnResultError error = (TurnResultError) result;
			if (error.getErrorType() == FULL_COLUMN) {
				writer.printColumnIsFull(col);
			} else if (error.getErrorType() == INVALID_COLUMN) {
				writer.printInputOutOfRange(game.getBoard().getSpec().getColumns());
			}
			return handlePlayerColSelection(player);
		} catch (EndgameException e) {
			return true;
		}
	}

	int getPlayerCol(Player player) throws EndgameException {
		Integer col = null;
		do {
			try {
				writer.printInputPlayerTurn(player);
				col = reader.readNextColumn();
			} catch (IllegalArgumentException e) {
				writer.printIncorrectColumnNumber();
			}
		} while (col == null);
		return col;
	}

}
