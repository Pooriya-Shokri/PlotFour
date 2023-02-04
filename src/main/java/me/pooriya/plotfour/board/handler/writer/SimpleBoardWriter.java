package me.pooriya.plotfour.board.handler.writer;

import lombok.NonNull;
import lombok.Value;

import java.io.OutputStream;

import static me.pooriya.plotfour.board.BoardSpecification.*;
import static me.pooriya.plotfour.util.OutputStreamUtil.printMsgToOutput;
import static me.pooriya.plotfour.util.OutputStreamUtil.printlnMsgToOutput;

@Value
public class SimpleBoardWriter implements BoardWriter {

	@NonNull OutputStream output;

	@Override
	public void printInputDimensions() {
		printMsgToOutput(output, String.format("Set the board dimensions (Rows x Columns)%nPress Enter for default (%s)%n> ", DEFAULT_SPECIFICATION));
	}

	@Override
	public void printInvalidRow() {
		printlnMsgToOutput(output, String.format("Board rows should be from %s to %s", MIN_VAL, MAX_VAL));
	}

	@Override
	public void printInvalidColumn() {
		printlnMsgToOutput(output, String.format("Board columns should be from %s to %s", MIN_VAL, MAX_VAL));
	}

	@Override
	public void printInvalidInput() {
		printlnMsgToOutput(output, "Invalid input");
	}

}
