package me.pooriya.plotfour.game.reader;

public interface GameReader {

	int readNextColumn() throws IllegalArgumentException, EndgameException;

}
