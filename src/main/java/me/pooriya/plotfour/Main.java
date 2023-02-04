package me.pooriya.plotfour;

import me.pooriya.plotfour.board.handler.BoardHandler;
import me.pooriya.plotfour.board.handler.SimpleBoardHandler;
import me.pooriya.plotfour.board.handler.creator.BoardCreator;
import me.pooriya.plotfour.board.handler.creator.SimpleBoardCreator;
import me.pooriya.plotfour.board.handler.parser.BoardSpecificationParser;
import me.pooriya.plotfour.board.handler.parser.SimpleBoardSpecificationParser;
import me.pooriya.plotfour.board.handler.reader.BoardReader;
import me.pooriya.plotfour.board.handler.reader.SimpleBoardReader;
import me.pooriya.plotfour.board.handler.writer.BoardWriter;
import me.pooriya.plotfour.board.handler.writer.SimpleBoardWriter;
import me.pooriya.plotfour.board.plotter.BoardPlotter;
import me.pooriya.plotfour.board.plotter.SimpleBoardPlotter;
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

		BoardPlotter plotter = new SimpleBoardPlotter(output);
		GameCreator con = createGameCreator(input, output, plotter);
		Game game = con.initiateGame();

		GameHandler gameHandler = createGameHandler(input, output, plotter, game);
		gameHandler.handle();
	}

	private static GameCreator createGameCreator(InputStream input, OutputStream output, BoardPlotter plotter) {
		PlayerReader playerReader = new SimplePlayerReader(input, output);
		BoardHandler boardHandler = createBoardHandler(input, output);
		return GameCreator.of(output, playerReader,boardHandler, plotter);
	}

	private static GameHandler createGameHandler(InputStream input, OutputStream output, BoardPlotter plotter, Game game) {
		GameReader gameReader = new SimpleGameReader(input);
		GameWriter gameWriter = new SimpleGameWriter(output);
		GameChecker gameChecker = new SimpleGameChecker(game);
		return new SimpleGameHandler(gameReader, gameWriter, game, plotter, gameChecker);
	}

	private static BoardHandler createBoardHandler(InputStream input, OutputStream output) {
		BoardReader boardReader = new SimpleBoardReader(input);
		BoardWriter boardWriter = new SimpleBoardWriter(output);
		BoardCreator boardCreator = new SimpleBoardCreator();
		BoardSpecificationParser boardParser = new SimpleBoardSpecificationParser();
		return new SimpleBoardHandler(boardReader, boardWriter, boardCreator, boardParser);
	}



}
