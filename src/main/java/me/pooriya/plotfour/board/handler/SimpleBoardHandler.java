package me.pooriya.plotfour.board.handler;

import lombok.NonNull;
import lombok.Value;
import lombok.experimental.NonFinal;
import me.pooriya.plotfour.board.Board;
import me.pooriya.plotfour.board.BoardSpecification;
import me.pooriya.plotfour.board.handler.creator.BoardCreator;
import me.pooriya.plotfour.board.handler.parser.BoardSpecificationParser;
import me.pooriya.plotfour.board.handler.reader.BoardReader;
import me.pooriya.plotfour.board.handler.writer.BoardWriter;

import static me.pooriya.plotfour.board.BoardSpecification.MAX_VAL;
import static me.pooriya.plotfour.board.BoardSpecification.MIN_VAL;

@Value
@NonFinal
public class SimpleBoardHandler implements BoardHandler {

	@NonNull BoardReader reader;

	@NonNull BoardWriter writer;

	@NonNull BoardCreator creator;

	@NonNull BoardSpecificationParser parser;

	@Override
	public Board handle() {
		BoardSpecification spec = readSpec();
		return creator.createBoard(spec);
	}

	private BoardSpecification readSpec() {
		BoardSpecification result;
		do {
			writer.printInputDimensions();
			String newInput = reader.readNextSpecification();
			result = extractBoardSpecification(newInput);
		} while (result == null);
		return result;
	}

	BoardSpecification extractBoardSpecification(String givenLine) {
		try {
			BoardSpecification result = parser.parse(givenLine);
			if (isInvalidRange(result.getRows())) {
				writer.printInvalidRow();
				return null;
			}
			if (isInvalidRange(result.getColumns())) {
				writer.printInvalidColumn();
				return null;
			}
			return result;
		} catch (IllegalArgumentException e) {
			writer.printInvalidInput();
			return null;
		}
	}

	static boolean isInvalidRange(int inputVal) {
		return inputVal > MAX_VAL || inputVal < MIN_VAL;
	}

}
