package me.pooriya.plotfour.player;

import org.junit.Test;

import static me.pooriya.plotfour.player.Stance.FIRST;
import static me.pooriya.plotfour.player.Stance.SECOND;
import static org.junit.Assert.*;

public class StanceTest {

    @Test
    public void shouldHaveCorrectValuesForFirst() {
        assertEquals("First", FIRST.getOutputName());
        assertEquals('o', FIRST.getSymbol());
    }

    @Test
    public void shouldHaveCorrectValuesForSecond() {
        assertEquals("Second", SECOND.getOutputName());
        assertEquals('*', SECOND.getSymbol());
    }

    @Test
    public void shouldHaveCorrectEnumValues() {
        Stance[] values = Stance.values();
        assertEquals(2, values.length);
        assertEquals(FIRST, values[0]);
        assertEquals(SECOND, values[1]);
    }

    @Test
    public void shouldHaveCorrectValueOfMethod() {
        assertEquals(FIRST, Stance.valueOf("FIRST"));
        assertEquals(SECOND, Stance.valueOf("SECOND"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionForInvalidValueOf() {
        Stance.valueOf("INVALID");
    }

    @Test
    public void shouldHaveUniqueSymbols() {
        assertNotEquals(FIRST.getSymbol(), SECOND.getSymbol());
    }

    @Test
    public void shouldHaveUniqueOutputNames() {
        assertNotEquals(FIRST.getOutputName(), SECOND.getOutputName());
    }
}