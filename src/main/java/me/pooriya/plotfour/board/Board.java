package me.pooriya.plotfour.board;

import lombok.NonNull;
import lombok.Value;
import lombok.experimental.NonFinal;
import me.pooriya.plotfour.board.turn.TurnResult;
import me.pooriya.plotfour.board.turn.TurnResultError;
import me.pooriya.plotfour.board.turn.TurnResultSuccess;
import me.pooriya.plotfour.player.Player;

import static me.pooriya.plotfour.board.turn.TurnResultError.TurnResultErrorType.FULL_COLUMN;
import static me.pooriya.plotfour.board.turn.TurnResultError.TurnResultErrorType.INVALID_COLUMN;

@NonFinal
@Value(staticConstructor = "of")
public class Board {

	@NonNull
	Player[][] state;

	@NonNull
	BoardSpecification spec;

	public TurnResult turn(Player player, int col) {
		if (col > spec.getColumns() || col < 1)
			return TurnResultError.of(INVALID_COLUMN);
		int  i = 0;
		int actualCol = col - 1;
		while ( i < spec.getRows() && state[i][actualCol] != null)
			i++;
		if (i >= spec.getRows())
			return TurnResultError.of(FULL_COLUMN);
		state[i][actualCol] = player;
		return TurnResultSuccess.of(i, actualCol);
	}

}
