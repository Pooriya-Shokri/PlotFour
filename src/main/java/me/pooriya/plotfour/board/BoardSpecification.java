package me.pooriya.plotfour.board;

import lombok.Value;

@Value(staticConstructor = "of")
public class BoardSpecification {

	int rows;

	int columns;

	public static final int MIN_VAL = 5;
	public static final int MAX_VAL = 9;

	public static final BoardSpecification DEFAULT_SPECIFICATION = BoardSpecification.of(6, 7);

	public static final char SPLITTER_SYMBOL = 'x';

	@Override
	public String toString() {
		return String.format("%s x %s", rows, columns);
	}

}
