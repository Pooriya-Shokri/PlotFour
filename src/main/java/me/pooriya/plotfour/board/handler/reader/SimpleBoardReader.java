package me.pooriya.plotfour.board.handler.reader;

import lombok.NonNull;
import lombok.Value;

import java.io.InputStream;
import java.util.Scanner;

@Value
public class SimpleBoardReader implements BoardReader {

	Scanner scanner;

	public SimpleBoardReader(@NonNull InputStream input) {
		scanner = new Scanner(input);
	}

	@Override
	public String readNextSpecification() {
		return scanner.nextLine();
	}

}
