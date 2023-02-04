package me.pooriya.plotfour.turn.writer;

import me.pooriya.plotfour.player.Player;

public interface TurnWriter {

	void printInputPlayerTurn(Player player);

	void printInputOutOfRange(int range);

	void printIncorrectColumnNumber();

	void printColumnIsFull(int col);

	void printGameOver();

}
