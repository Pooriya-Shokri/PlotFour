package me.pooriya.plotfour.board;

import lombok.NonNull;
import lombok.Value;
import me.pooriya.plotfour.player.Player;

@Value(staticConstructor = "of")
public class Board {

	Player[][] state;

	@NonNull
	BoardSpecification spec;



}
