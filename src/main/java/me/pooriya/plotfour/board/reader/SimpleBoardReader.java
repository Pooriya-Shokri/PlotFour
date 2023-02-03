package me.pooriya.plotfour.board.reader;

import lombok.SneakyThrows;
import lombok.Value;
import me.pooriya.plotfour.board.BoardSpecification;
import org.apache.commons.lang3.StringUtils;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.Scanner;

import static java.nio.charset.StandardCharsets.UTF_8;
import static me.pooriya.plotfour.board.BoardSpecification.MAX_VAL;
import static me.pooriya.plotfour.board.BoardSpecification.MIN_VAL;

@Value
public class SimpleBoardReader implements BoardReader {

	InputStream input;

	OutputStream output;

	@Override
	@SneakyThrows
	public BoardSpecification readSpec() {
		Scanner scanner = new Scanner(input);

		BoardSpecification result = null;
		do {
			output.write("Set the board dimensions (Rows x Columns)\nPress Enter for default (6 x 7)\n> ".getBytes(UTF_8));
			String readLine = getAnotherInput(scanner);
			if (StringUtils.isEmpty(readLine) || StringUtils.isAllBlank(readLine))
				return BoardSpecification.DEFAULT_SPECIFICATION;

			try {
				result = parseInputLine(readLine);
				if (!isInValidRange(result.getRows())) {
					result = null;
					output.write("Board rows should be from 5 to 9\n".getBytes(UTF_8));
				} else if (!isInValidRange(result.getColumns())) {
					result = null;
					output.write("Board columns should be from 5 to 9\n".getBytes(UTF_8));
				}
			} catch (IllegalArgumentException e) {
				output.write("Invalid input\n".getBytes(UTF_8));
				result = null;
			}
		} while (result == null);
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
		try {
			String[] splitLine = givenLine.split("[xX]");
			if (splitLine.length != 2)
				throw new IllegalArgumentException("inputLine is not acceptable");
			return BoardSpecification.of(Integer.parseInt(splitLine[0].trim()), Integer.parseInt(splitLine[1].trim()));
		} catch (Exception e) {
			throw new IllegalArgumentException(e);
		}
	}

	static boolean isInValidRange(int inputVal) {
		return inputVal <= MAX_VAL && inputVal >= MIN_VAL;
	}

}
