package me.pooriya.plotfour.board.plotter;

import lombok.NonNull;
import lombok.Value;
import me.pooriya.plotfour.board.Board;
import me.pooriya.plotfour.board.BoardSpecification;

import java.io.OutputStream;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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

		String eachRow = IntStream.rangeClosed(0, spec.getColumns())
				.mapToObj(i -> "| ")
				.collect(Collectors.joining());
		for (int i = 0; i < spec.getRows(); i++) {
			printlnMsgToOutput(output, eachRow);
		}

		String finalRow = IntStream.rangeClosed(1, spec.getColumns() * 2 + 1)
				.mapToObj(i -> "=")
				.collect(Collectors.joining());
		printlnMsgToOutput(output, finalRow);
	}

}
