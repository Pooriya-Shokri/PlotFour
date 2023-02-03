package me.pooriya.plotfour.board.reader;

import lombok.Value;
import me.pooriya.plotfour.board.BoardSpecification;
import org.apache.commons.lang3.StringUtils;

import java.io.InputStream;
import java.util.Scanner;

import static me.pooriya.plotfour.board.BoardSpecification.MAX_VAL;
import static me.pooriya.plotfour.board.BoardSpecification.MIN_VAL;

@Value
public class InputStreamBoardReader implements BoardReader {

	InputStream input;

	@Override
	public BoardSpecification readSpec() {
		Scanner scanner = new Scanner(input);

		BoardSpecification result = null;
		do {
			String readLine = getAnotherInput(scanner);
			if (StringUtils.isEmpty(readLine))
				return BoardSpecification.DEFAULT_SPECIFICATION;

			try {
				result = parseInputLine(readLine);
			} catch (IllegalArgumentException e) {
				// do nothing
			}
		} while (result == null || !isValid(result));
		return result;
	}

	private String getAnotherInput(Scanner scanner) {
		try {
			return scanner.nextLine();
		} catch (Exception e) {
			return null;
		}
	}

	static BoardSpecification parseInputLine(String givenLine) {
		if (StringUtils.isEmpty(givenLine) || StringUtils.isAllBlank(givenLine))
			return null;
		try {
			String[] splitLine = givenLine.split("[xX]");
			if (splitLine.length != 2)
				throw new IllegalArgumentException("inputLine is not acceptable");
			return BoardSpecification.of(Integer.parseInt(splitLine[0].trim()), Integer.parseInt(splitLine[1].trim()));
		} catch (Exception e) {
			throw new IllegalArgumentException(e);
		}
	}

	static boolean isValid(BoardSpecification spec) {
		return spec.getColumns() <= MAX_VAL && spec.getColumns() >= MIN_VAL &&
				spec.getRows() <= MAX_VAL && spec.getRows() >= MIN_VAL;
	}

}
