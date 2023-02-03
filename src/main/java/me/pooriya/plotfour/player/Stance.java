package me.pooriya.plotfour.player;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Stance {

	FIRST("First", 'o'),
	SECOND("Second", '*');

	private final String outputName;
	private final char symbol;

}
