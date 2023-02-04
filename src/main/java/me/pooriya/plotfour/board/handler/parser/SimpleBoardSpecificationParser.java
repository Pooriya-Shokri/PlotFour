package me.pooriya.plotfour.board.handler.parser;

import me.pooriya.plotfour.board.BoardSpecification;
import org.apache.commons.lang3.StringUtils;

import static me.pooriya.plotfour.board.BoardSpecification.*;

public class SimpleBoardSpecificationParser implements BoardSpecificationParser {

	public static final String SPLITTER_GROUP = String.format("[%s%s]", SPLITTER_SYMBOL, String.valueOf(SPLITTER_SYMBOL).toUpperCase());

	@Override
	public BoardSpecification parse(String input) throws IllegalArgumentException {
		if (StringUtils.isEmpty(input) || StringUtils.isAllBlank(input))
			return DEFAULT_SPECIFICATION;

		try {
			String[] splitLine = input.split(SPLITTER_GROUP);
			if (splitLine.length != 2)
				throw new IllegalArgumentException();
			return of(Integer.parseInt(splitLine[0].trim()), Integer.parseInt(splitLine[1].trim()));
		} catch (Exception e) {
			throw new IllegalArgumentException(e);
		}
	}

}
