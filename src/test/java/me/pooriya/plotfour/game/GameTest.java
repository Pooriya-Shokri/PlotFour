package me.pooriya.plotfour.game;

import me.pooriya.plotfour.board.Board;
import me.pooriya.plotfour.player.Player;
import org.junit.Test;

import static me.pooriya.plotfour.BoardObjectMother.defaultBoard;
import static me.pooriya.plotfour.PlayerObjectMother.first;
import static me.pooriya.plotfour.PlayerObjectMother.second;
import static org.junit.Assert.*;

public class GameTest {

    @Test
    public void shouldCreateGameWithPlayersAndBoard() {
        Player firstPlayer = first();
        Player secondPlayer = second();
        Board board = defaultBoard();

        Game game = Game.of(firstPlayer, secondPlayer, board);

        assertSame(firstPlayer, game.getFirst());
        assertSame(secondPlayer, game.getSecond());
        assertSame(board, game.getBoard());
    }

    @Test
    public void shouldEnsureImmutability() {
        Player firstPlayer = first();
        Player secondPlayer = second();
        Board board = defaultBoard();

        Game game = Game.of(firstPlayer, secondPlayer, board);
        Game anotherGame = Game.of(firstPlayer, secondPlayer, board);

        assertEquals(game, anotherGame);
        assertEquals(game.hashCode(), anotherGame.hashCode());
    }

    @Test(expected = NullPointerException.class)
    public void shouldThrowExceptionForNullFirstPlayer() {
        Player secondPlayer = second();
        Board board = defaultBoard();

        Game.of(null, secondPlayer, board);
    }

    @Test(expected = NullPointerException.class)
    public void shouldThrowExceptionForNullSecondPlayer() {
        Player firstPlayer = first();
        Board board = defaultBoard();

        Game.of(firstPlayer, null, board);
    }

    @Test(expected = NullPointerException.class)
    public void shouldThrowExceptionForNullBoard() {
        Player firstPlayer = first();
        Player secondPlayer = second();

        Game.of(firstPlayer, secondPlayer, null);
    }

    @Test
    public void shouldHaveProperToString() {
        Player firstPlayer = first();
        Player secondPlayer = second();
        Board board = defaultBoard();

        Game game = Game.of(firstPlayer, secondPlayer, board);

        String toString = game.toString();
        assertTrue(toString.contains("Game"));
        assertTrue(toString.contains(firstPlayer.toString()));
        assertTrue(toString.contains(secondPlayer.toString()));
        assertTrue(toString.contains(board.toString()));
    }
}