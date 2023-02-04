package me.pooriya.plotfour.game.checker;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import me.pooriya.plotfour.player.Player;

import static lombok.AccessLevel.PRIVATE;

@Getter
@FieldDefaults(level = PRIVATE, makeFinal = true)
@AllArgsConstructor(access = PRIVATE, staticName = "of")
public class GameStatus {

	public static GameStatus notFinished() {
		return GameStatus.of(false, null);
	}

	public static GameStatus finishedWithWinner(Player winner) {
		return GameStatus.of(true, winner);
	}

	public static GameStatus finishedWithDraw() {
		return GameStatus.of(true, null);
	}

	boolean isFinished;

	Player winner;

}
