package me.pooriya.plotfour.game.handler;

import lombok.NonNull;
import lombok.Value;
import lombok.experimental.NonFinal;
import me.pooriya.plotfour.board.Board.TurnResult;
import me.pooriya.plotfour.board.plotter.BoardPlotter;
import me.pooriya.plotfour.game.Game;
import me.pooriya.plotfour.game.reader.EndgameException;
import me.pooriya.plotfour.game.reader.GameReader;
import me.pooriya.plotfour.game.writer.GameWriter;
import me.pooriya.plotfour.player.Player;

import static me.pooriya.plotfour.board.Board.TurnResult.*;

@Value
@NonFinal
public class SimpleGameHandler implements GameHandler {

	@NonNull GameReader reader;

	@NonNull GameWriter writer;

	@NonNull Game game;

	@NonNull BoardPlotter plotter;

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
			if (result == SUCCESS) {
				plotter.plot(game.getBoard());
				return false;
			}
			if (result == FULL_COLUMN) {
				writer.printColumnIsFull(col);
			} else if (result == INVALID_COLUMN) {
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
