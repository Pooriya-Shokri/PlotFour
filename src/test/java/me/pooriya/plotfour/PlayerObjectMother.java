package me.pooriya.plotfour;

import me.pooriya.plotfour.player.Player;

import static me.pooriya.plotfour.player.Stance.FIRST;
import static me.pooriya.plotfour.player.Stance.SECOND;

public class PlayerObjectMother {

	public static Player first() {
		return Player.of("player-one", FIRST);
	}

	public static Player second() {
		return Player.of("player-two", SECOND);
	}

}
