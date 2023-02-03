package me.pooriya.plotfour.board.reader;

import lombok.NonNull;
import lombok.SneakyThrows;
import lombok.Value;
import me.pooriya.plotfour.board.BoardSpecification;
import org.apache.commons.lang3.StringUtils;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.Scanner;

import static java.nio.charset.StandardCharsets.UTF_8;
import static me.pooriya.plotfour.board.BoardSpecification.*;

@Value
public class SimpleBoardReader implements BoardReader {

	@NonNull
	InputStream input;

	@NonNull
	OutputStream output;

	@Override
	public BoardSpecification readSpec() {
		Scanner scanner = new Scanner(input);

		BoardSpecification result;
		do {
			printMsgToOutput("Set the board dimensions (Rows x Columns)\nPress Enter for default (6 x 7)\n> ");
			String readLine = getAnotherInput(scanner);
			result = extractBoardSpecification(readLine);
		} while (result == null);
		return result;
	}

	private BoardSpecification extractBoardSpecification(String givenLine) {
		if (StringUtils.isEmpty(givenLine) || StringUtils.isAllBlank(givenLine))
			return DEFAULT_SPECIFICATION;

		try {
			BoardSpecification result = parseInputLine(givenLine);
			if (isInvalidRange(result.getRows())) {
				printMsgToOutput("Board rows should be from 5 to 9\n");
				return null;
			}
			if (isInvalidRange(result.getColumns())) {
				printMsgToOutput("Board columns should be from 5 to 9\n");
				return null;
			}
			return result;
		} catch (IllegalArgumentException e) {
			printMsgToOutput("Invalid input\n");
			return null;
		}
	}

	@SneakyThrows
	private void printMsgToOutput(String x) {
		output.write(x.getBytes(UTF_8));
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
				throw new IllegalArgumentException();
			return of(Integer.parseInt(splitLine[0].trim()), Integer.parseInt(splitLine[1].trim()));
		} catch (Exception e) {
			throw new IllegalArgumentException(e);
		}
	}

	static boolean isInvalidRange(int inputVal) {
		return inputVal > MAX_VAL || inputVal < MIN_VAL;
	}

}
