package me.pooriya.plotfour.game.reader;

import org.apache.commons.io.IOUtils;
import org.junit.Test;

import java.io.InputStream;

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.junit.Assert.assertEquals;

public class SimpleGameReaderTest {

	@Test
	public void itShouldReadCol() {
		InputStream input = toInputStream("1\n");
		SimpleGameReader reader = new SimpleGameReader(input);
		int result = reader.readNextColumn();
		assertEquals(1, result);
	}

	@Test(expected = IllegalArgumentException.class)
	public void itShouldThrowIllegalArg() {
		InputStream input = toInputStream("not-in-number-format\n");
		SimpleGameReader reader = new SimpleGameReader(input);
		reader.readNextColumn();
	}

	@Test(expected = EndgameException.class)
	public void itShouldThrowEndgame() {
		InputStream input = toInputStream("end\n");
		SimpleGameReader reader = new SimpleGameReader(input);
		reader.readNextColumn();
	}

	private InputStream toInputStream(String input) {
		return IOUtils.toInputStream(input, UTF_8);
	}

}