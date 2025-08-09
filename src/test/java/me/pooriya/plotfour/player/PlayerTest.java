package me.pooriya.plotfour.player;

import org.junit.Test;

import static me.pooriya.plotfour.player.Stance.FIRST;
import static me.pooriya.plotfour.player.Stance.SECOND;
import static org.junit.Assert.*;

public class PlayerTest {

    @Test
    public void shouldCreatePlayerWithNameAndStance() {
        String playerName = "John";
        Stance stance = FIRST;

        Player player = Player.of(playerName, stance);

        assertEquals(playerName, player.getName());
        assertSame(stance, player.getStance());
    }

    @Test
    public void shouldEnsureImmutability() {
        String playerName = "Alice";
        Stance stance = SECOND;

        Player player1 = Player.of(playerName, stance);
        Player player2 = Player.of(playerName, stance);

        assertEquals(player1, player2);
        assertEquals(player1.hashCode(), player2.hashCode());
    }

    @Test
    public void shouldCreateDifferentPlayersWithDifferentNames() {
        Stance stance = FIRST;

        Player player1 = Player.of("John", stance);
        Player player2 = Player.of("Jane", stance);

        assertNotEquals(player1, player2);
        assertNotEquals(player1.hashCode(), player2.hashCode());
    }

    @Test
    public void shouldCreateDifferentPlayersWithDifferentStances() {
        String playerName = "John";

        Player player1 = Player.of(playerName, FIRST);
        Player player2 = Player.of(playerName, SECOND);

        assertNotEquals(player1, player2);
        assertNotEquals(player1.hashCode(), player2.hashCode());
    }

    @Test(expected = NullPointerException.class)
    public void shouldThrowExceptionForNullName() {
        Player.of(null, FIRST);
    }

    @Test(expected = NullPointerException.class)
    public void shouldThrowExceptionForNullStance() {
        Player.of("John", null);
    }

    @Test
    public void shouldHaveProperToString() {
        String playerName = "TestPlayer";
        Stance stance = FIRST;

        Player player = Player.of(playerName, stance);

        String toString = player.toString();
        assertTrue(toString.contains("Player"));
        assertTrue(toString.contains(playerName));
        assertTrue(toString.contains(stance.toString()));
    }
}