package me.pooriya.plotfour.game;

import lombok.NonNull;
import lombok.Value;
import me.pooriya.plotfour.board.Board;
import me.pooriya.plotfour.player.Player;

@Value(staticConstructor = "of")
public class Game {

	@NonNull
	Player first;

	@NonNull
	Player second;

	@NonNull
	Board board;

}
