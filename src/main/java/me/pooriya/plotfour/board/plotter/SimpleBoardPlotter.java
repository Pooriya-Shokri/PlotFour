package me.pooriya.plotfour.board.plotter;

import lombok.NonNull;
import lombok.Value;
import me.pooriya.plotfour.board.Board;
import me.pooriya.plotfour.board.BoardSpecification;
import me.pooriya.plotfour.player.Player;

import java.io.OutputStream;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static me.pooriya.plotfour.util.OutputStreamUtil.printMsgToOutput;
import static me.pooriya.plotfour.util.OutputStreamUtil.printlnMsgToOutput;

@Value
public class SimpleBoardPlotter implements BoardPlotter {

	@NonNull
	OutputStream output;

	@Override
	public void plot(@NonNull Board board) {
		BoardSpecification spec = board.getSpec();
		if (spec.getRows() * spec.getColumns() == 0)
			return;

		String indexRow = IntStream.rangeClosed(1, spec.getColumns())
				.mapToObj(i -> String.format(" %s", i))
				.collect(Collectors.joining());
		printlnMsgToOutput(output, indexRow);


		for (int i = spec.getRows() -1; i >= 0; i--) {
			for (int j=0; j< spec.getColumns(); j++) {
				printMsgToOutput(output, "|"+ getBlockRepresentation(board, i, j));
			}
			printlnMsgToOutput(output, "|");
		}

		String finalRow = IntStream.rangeClosed(1, spec.getColumns() * 2 + 1)
				.mapToObj(i -> "=")
				.collect(Collectors.joining());
		printlnMsgToOutput(output, finalRow);
	}

	private char getBlockRepresentation(Board board, int i, int j) {
		Player blockVal = board.getState()[i][j];
		return blockVal != null ? blockVal.getStance().getSymbol() : ' ';
	}

}
