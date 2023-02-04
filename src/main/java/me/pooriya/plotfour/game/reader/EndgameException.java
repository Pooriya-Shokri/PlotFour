package me.pooriya.plotfour.game.reader;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class EndgameException extends RuntimeException {

	public static final EndgameException INSTANCE = new EndgameException();

}
