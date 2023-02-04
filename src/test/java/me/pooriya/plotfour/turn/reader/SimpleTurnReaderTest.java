package me.pooriya.plotfour.turn.reader;

import org.apache.commons.io.IOUtils;
import org.junit.Test;

import java.io.InputStream;

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.junit.Assert.assertEquals;

public class SimpleTurnReaderTest {

	@Test
	public void itShouldReadCol() {
		InputStream input = toInputStream("1\n");
		SimpleTurnReader reader = new SimpleTurnReader(input);
		int result = reader.readNextColumn();
		assertEquals(1, result);
	}

	@Test(expected = IllegalArgumentException.class)
	public void itShouldThrowIllegalArg() {
		InputStream input = toInputStream("not-in-number-format\n");
		SimpleTurnReader reader = new SimpleTurnReader(input);
		reader.readNextColumn();
	}

	@Test(expected = EndgameException.class)
	public void itShouldThrowEndgame() {
		InputStream input = toInputStream("end\n");
		SimpleTurnReader reader = new SimpleTurnReader(input);
		reader.readNextColumn();
	}

	private InputStream toInputStream(String input) {
		return IOUtils.toInputStream(input, UTF_8);
	}

}