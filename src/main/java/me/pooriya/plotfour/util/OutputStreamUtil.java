package me.pooriya.plotfour.util;

import lombok.NoArgsConstructor;
import lombok.SneakyThrows;

import java.io.OutputStream;

import static java.nio.charset.StandardCharsets.UTF_8;
import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class OutputStreamUtil {

	@SneakyThrows
	public static void printMsgToOutput(OutputStream output, String x) {
		output.write(x.getBytes(UTF_8));
	}

	public static void printlnMsgToOutput(OutputStream outputStream, String x) {
		printMsgToOutput(outputStream, x);
		printMsgToOutput(outputStream, "\n");
	}

}
