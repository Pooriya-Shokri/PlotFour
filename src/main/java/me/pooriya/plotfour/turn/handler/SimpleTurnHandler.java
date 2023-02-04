package me.pooriya.plotfour.turn.handler;

import lombok.NonNull;
import lombok.Value;
import lombok.experimental.NonFinal;
import me.pooriya.plotfour.board.Board;
import me.pooriya.plotfour.board.Board.TurnResult;
import me.pooriya.plotfour.player.Player;
import me.pooriya.plotfour.turn.reader.EndgameException;
import me.pooriya.plotfour.turn.reader.TurnReader;
import me.pooriya.plotfour.turn.writer.TurnWriter;

import static me.pooriya.plotfour.board.Board.TurnResult.*;

@Value
@NonFinal
public class SimpleTurnHandler implements TurnHandler {

	@NonNull TurnReader reader;

	@NonNull TurnWriter writer;

	@NonNull Player first;

	@NonNull Player second;

	@NonNull Board board;

	@Override
	public void handle() {
		Player currentPlayer = first;
		while (!handlePlayerColSelection(currentPlayer)) {
			currentPlayer = currentPlayer != first ? first : second;
		}
		writer.printGameOver();
	}

	boolean handlePlayerColSelection(Player player) {
		try {
			int col = getPlayerCol(player);
			TurnResult result = board.turn(player, col);
			if (result == SUCCESS) {
				return false;
			}
			if (result == FULL_COLUMN) {
				writer.printColumnIsFull(col);
			} else if (result == INVALID_COLUMN) {
				writer.printInputOutOfRange(board.getSpec().getColumns());
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
