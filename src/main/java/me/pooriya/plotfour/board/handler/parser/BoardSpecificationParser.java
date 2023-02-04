package me.pooriya.plotfour.board.handler.parser;

import me.pooriya.plotfour.board.BoardSpecification;

public interface BoardSpecificationParser {

	BoardSpecification parse(String input) throws IllegalArgumentException;

}
