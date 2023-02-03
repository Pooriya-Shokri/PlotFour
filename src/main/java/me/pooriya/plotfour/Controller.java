package me.pooriya.plotfour;

import lombok.NonNull;
import lombok.Value;
import me.pooriya.plotfour.board.Board;
import me.pooriya.plotfour.board.BoardSpecification;
import me.pooriya.plotfour.board.creator.BoardCreator;
import me.pooriya.plotfour.board.reader.BoardReader;
import me.pooriya.plotfour.player.Player;
import me.pooriya.plotfour.player.reader.PlayerReader;

import java.io.OutputStream;

import static me.pooriya.plotfour.player.Stance.FIRST;
import static me.pooriya.plotfour.player.Stance.SECOND;
import static me.pooriya.plotfour.util.OutputStreamUtil.printlnMsgToOutput;

@Value(staticConstructor = "of")
public class Controller {

	private static final String GAME_NAME_MSG = "Plot Four";

	@NonNull OutputStream output;

	@NonNull PlayerReader playerReader;

	@NonNull BoardReader boardReader;

	@NonNull BoardCreator boardCreator;

	public Board initiateGame() {
		printlnMsgToOutput(output, GAME_NAME_MSG);
		Player firstPlayer = playerReader.readPlayer(FIRST);
		Player secondPlayer = playerReader.readPlayer(SECOND);

		BoardSpecification boardSpec = boardReader.readSpec();

		printlnMsgToOutput(output, String.format("%s VS %s", firstPlayer.getName(), secondPlayer.getName()));
		printlnMsgToOutput(output, String.format("%s X %s board", boardSpec.getRows(), boardSpec.getColumns()));

		return boardCreator.createBoard(boardSpec);
	}

}
