package me.pooriya.plotfour.game.reader;

import lombok.NonNull;
import lombok.Value;

import java.io.InputStream;
import java.util.Scanner;

@Value
public class SimpleGameReader implements GameReader {

	Scanner scanner;

	public SimpleGameReader(@NonNull InputStream input) {
		this.scanner = new Scanner(input);
	}

	@Override
	public int readNextColumn() throws IllegalArgumentException, EndgameException {
		String readLine = scanner.nextLine();
		if ("end".equalsIgnoreCase(readLine))
			throw EndgameException.INSTANCE;
		try {
			return Integer.parseInt(readLine);
		} catch (NumberFormatException e) {
			throw new IllegalArgumentException(e);
		}
	}

}
