package me.pooriya.plotfour.board;

import lombok.NonNull;
import lombok.Value;

@Value(staticConstructor = "of")
public class Board {

	int[][] state;

	@NonNull
	BoardSpecification spec;

}
