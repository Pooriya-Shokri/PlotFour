package me.pooriya.plotfour.player.reader;

import lombok.NonNull;
import lombok.SneakyThrows;
import lombok.Value;
import me.pooriya.plotfour.player.Player;
import me.pooriya.plotfour.player.Stance;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.Scanner;

import static me.pooriya.plotfour.util.OutputStreamUtil.printMsgToOutput;

@Value
public class SimplePlayerReader implements PlayerReader {

	@NonNull
	InputStream input;

	@NonNull
	OutputStream output;

	@Override
	@SneakyThrows
	public Player readPlayer(@NonNull Stance stance) {
		printMsgToOutput(output, String.format("%s player's name:%n> ", stance.getOutputName()));
		String name = new Scanner(input).nextLine();
		return Player.of(name, stance);
	}

}
