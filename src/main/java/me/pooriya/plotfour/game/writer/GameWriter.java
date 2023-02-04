package me.pooriya.plotfour.game.writer;

import me.pooriya.plotfour.player.Player;

public interface GameWriter {

	void printInputPlayerTurn(Player player);

	void printInputOutOfRange(int range);

	void printIncorrectColumnNumber();

	void printColumnIsFull(int col);

	void printGameOver();

}
