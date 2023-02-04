package me.pooriya.plotfour.turn.reader;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class EndgameException extends RuntimeException {

	public static final EndgameException INSTANCE = new EndgameException();

}
