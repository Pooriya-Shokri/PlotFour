package me.pooriya.plotfour.player;

import lombok.NonNull;
import lombok.Value;

@Value(staticConstructor = "of")
public class Player {

	@NonNull
	String name;

	@NonNull
	Stance stance;

}
