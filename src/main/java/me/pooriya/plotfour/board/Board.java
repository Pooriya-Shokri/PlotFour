package me.pooriya.plotfour.board;

import lombok.Value;

@Value(staticConstructor = "of")
public class Board {

	int[][] state;

}
