package me.pooriya.plotfour.player.reader;

import me.pooriya.plotfour.player.Player;
import org.apache.commons.io.IOUtils;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.junit.Assert.assertEquals;

public class SimplePlayerReaderTest {

	@Test
	public void shouldReturnPlayer() {
		InputStream input = IOUtils.toInputStream("some-name", UTF_8);
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		SimplePlayerReader reader = new SimplePlayerReader(input, output);

		Player result = reader.readPlayer("first");
		assertEquals("some-name", result.getName());
		assertEquals("Enter first player's name:\n", output.toString());
	}

}