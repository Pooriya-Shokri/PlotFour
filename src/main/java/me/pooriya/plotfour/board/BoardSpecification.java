package me.pooriya.plotfour.board;

import lombok.Value;

@Value(staticConstructor = "of")
public class BoardSpecification {

	int rows;

	int columns;

}
