package me.pooriya.plotfour.board.turn;

import lombok.Value;

@Value(staticConstructor = "of")
public class TurnResultSuccess implements TurnResult {

	int rowIndex;
	int colIndex;

	@Override
	public boolean isSuccess() {
		return true;
	}

}
