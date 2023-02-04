package me.pooriya.plotfour.board.handler.creator;

import me.pooriya.plotfour.board.Board;
import me.pooriya.plotfour.board.BoardSpecification;

public interface BoardCreator {

	Board createBoard(BoardSpecification spec);

}
