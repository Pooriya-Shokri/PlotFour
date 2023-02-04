package me.pooriya.plotfour;

import me.pooriya.plotfour.board.creator.BoardCreator;
import me.pooriya.plotfour.board.creator.SimpleBoardCreator;
import me.pooriya.plotfour.board.plotter.BoardPlotter;
import me.pooriya.plotfour.board.plotter.SimpleBoardPlotter;
import me.pooriya.plotfour.board.reader.BoardReader;
import me.pooriya.plotfour.board.reader.SimpleBoardReader;
import me.pooriya.plotfour.game.Game;
import me.pooriya.plotfour.game.GameCreator;
import me.pooriya.plotfour.game.checker.GameChecker;
import me.pooriya.plotfour.game.checker.SimpleGameChecker;
import me.pooriya.plotfour.game.handler.GameHandler;
import me.pooriya.plotfour.game.handler.SimpleGameHandler;
import me.pooriya.plotfour.game.reader.GameReader;
import me.pooriya.plotfour.game.reader.SimpleGameReader;
import me.pooriya.plotfour.game.writer.GameWriter;
import me.pooriya.plotfour.game.writer.SimpleGameWriter;
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
		BoardPlotter plotter = new SimpleBoardPlotter(output);

		GameCreator con = GameCreator.of(output, playerReader, boardReader, boardCreator, plotter);
		Game game = con.initiateGame();

		GameReader gameReader = new SimpleGameReader(input);
		GameWriter gameWriter = new SimpleGameWriter(output);
		GameChecker gameChecker = new SimpleGameChecker(game);
		GameHandler gameHandler = new SimpleGameHandler(gameReader, gameWriter, game, plotter, gameChecker);
		gameHandler.handle();
	}

}
