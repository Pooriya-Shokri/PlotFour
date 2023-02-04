package me.pooriya.plotfour.board.turn;

import lombok.Value;

@Value(staticConstructor = "of")
public class TurnResultError implements TurnResult{

	TurnResultErrorType errorType;

	@Override
	public boolean isSuccess() {
		return false;
	}

	public enum TurnResultErrorType {

		FULL_COLUMN,
		INVALID_COLUMN

	}

}
