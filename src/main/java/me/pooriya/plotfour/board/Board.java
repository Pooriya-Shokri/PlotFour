package me.pooriya.plotfour.board;

import lombok.NonNull;
import lombok.Value;
import me.pooriya.plotfour.player.Player;

import static me.pooriya.plotfour.board.Board.TurnResult.*;

@Value(staticConstructor = "of")
public class Board {

	@NonNull
	Player[][] state;

	@NonNull
	BoardSpecification spec;

	public TurnResult turn(Player player, int col) {
		if (col > spec.getColumns() || col < 1)
			return INVALID_COLUMN;
		int  i = 0;
		while ( i < spec.getRows() && state[i][col-1] != null)
			i++;
		if (i >= spec.getRows())
			return FULL_COLUMN;
		state[i][col -1] = player;
		return SUCCESS;
	}

	public enum TurnResult {
		SUCCESS,
		FULL_COLUMN,
		INVALID_COLUMN
	}

}
