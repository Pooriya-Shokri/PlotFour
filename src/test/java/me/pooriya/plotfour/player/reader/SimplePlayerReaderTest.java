package me.pooriya.plotfour.player.reader;

import me.pooriya.plotfour.player.Player;
import org.apache.commons.io.IOUtils;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

import static java.nio.charset.StandardCharsets.UTF_8;
import static me.pooriya.plotfour.player.Stance.FIRST;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

public class SimplePlayerReaderTest {


	@Test(expected = NullPointerException.class)
	public void shouldThrowExceptionOnNullStance() {
		InputStream input = IOUtils.toInputStream("some-name", UTF_8);
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		SimplePlayerReader reader = new SimplePlayerReader(input, output);

		reader.readPlayer(null);
	}
	@Test
	public void shouldReturnPlayer() {
		InputStream input = IOUtils.toInputStream("some-name", UTF_8);
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		SimplePlayerReader reader = new SimplePlayerReader(input, output);

		Player result = reader.readPlayer(FIRST);
		assertEquals("some-name", result.getName());
		assertSame(FIRST, result.getStance());
		assertEquals("First player's name:\n> ", output.toString());
	}

}