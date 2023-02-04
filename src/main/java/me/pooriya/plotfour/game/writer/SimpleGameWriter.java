package me.pooriya.plotfour.game.writer;

import lombok.NonNull;
import lombok.Value;
import me.pooriya.plotfour.player.Player;

import java.io.OutputStream;

import static me.pooriya.plotfour.util.OutputStreamUtil.printMsgToOutput;
import static me.pooriya.plotfour.util.OutputStreamUtil.printlnMsgToOutput;

@Value
public class SimpleGameWriter implements GameWriter {

	@NonNull OutputStream output;

	@Override
	public void printInputPlayerTurn(Player player) {
		printMsgToOutput(output, String.format("%s's turn:%n> ", player.getName()));
	}

	@Override
	public void printInputOutOfRange(int range) {
		printlnMsgToOutput(output, String.format("The column number is out of range (1 - %s)", range));
	}

	@Override
	public void printIncorrectColumnNumber() {
		printlnMsgToOutput(output, "Incorrect column number");
	}

	@Override
	public void printColumnIsFull(int col) {
		printlnMsgToOutput(output, String.format("Column %s is full", col));
	}

	@Override
	public void printGameOver() {
		printlnMsgToOutput(output, "Game over!");
	}

	@Override
	public void printPlayerWin(Player player) {

	}

	@Override
	public void printDraw() {

	}

}
