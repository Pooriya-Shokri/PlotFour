package me.pooriya.plotfour.game.checker;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import static lombok.AccessLevel.PRIVATE;

@Getter
@FieldDefaults(level = PRIVATE, makeFinal = true)
@AllArgsConstructor(access = PRIVATE, staticName = "of")
public class GameStatus {

	public static GameStatus notFinished() {
		return GameStatus.of(false, false);
	}

	public static GameStatus finishedWithWinner() {
		return GameStatus.of(true, true);
	}

	public static GameStatus finishedWithDraw() {
		return GameStatus.of(true, false);
	}

	boolean isFinished;

	boolean isWinner;

}
