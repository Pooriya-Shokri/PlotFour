package me.pooriya.plotfour;

import me.pooriya.plotfour.board.creator.BoardCreator;
import me.pooriya.plotfour.board.creator.SimpleBoardCreator;
import me.pooriya.plotfour.board.reader.BoardReader;
import me.pooriya.plotfour.board.reader.SimpleBoardReader;
import me.pooriya.plotfour.player.reader.PlayerReader;
import me.pooriya.plotfour.player.reader.SimplePlayerReader;

import java.io.InputStream;
import java.io.OutputStream;

public class Main {

	public static void main(String[] args) {
		InputStream input = System.in;
		OutputStream output = System.out;
		PlayerReader playerReader = new SimplePlayerReader(input, output);
		BoardReader boardReader = new SimpleBoardReader(input, output);
		BoardCreator boardCreator = new SimpleBoardCreator();
		Controller con = Controller.of(output, playerReader, boardReader, boardCreator);
		con.initiateGame();
	}

}
