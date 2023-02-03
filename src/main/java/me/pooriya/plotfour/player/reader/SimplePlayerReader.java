package me.pooriya.plotfour.player.reader;

import lombok.NonNull;
import lombok.SneakyThrows;
import lombok.Value;
import me.pooriya.plotfour.player.Player;
import me.pooriya.plotfour.player.Stance;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.Scanner;

import static java.nio.charset.StandardCharsets.UTF_8;

@Value
public class SimplePlayerReader implements PlayerReader {

	InputStream input;

	OutputStream output;

	@Override
	@SneakyThrows
	public Player readPlayer(@NonNull Stance stance) {
		output.write(String.format("%s player's name:%n", stance.getOutputName()).getBytes(UTF_8));
		String name = new Scanner(input).nextLine();
		return Player.of(name, stance);
	}

}
