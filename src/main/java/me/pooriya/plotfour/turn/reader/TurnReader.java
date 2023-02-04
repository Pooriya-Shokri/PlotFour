package me.pooriya.plotfour.turn.reader;

public interface TurnReader {

	int readNextColumn() throws IllegalArgumentException, EndgameException;

}
