package me.pooriya.plotfour.player.reader;

import lombok.SneakyThrows;
import lombok.Value;
import me.pooriya.plotfour.player.Player;

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
	public Player readPlayer(String place) {
		output.write(String.format("Enter %s player's name:%n", place).getBytes(UTF_8));
		String name = new Scanner(input).nextLine();
		return Player.of(name);
	}

}
