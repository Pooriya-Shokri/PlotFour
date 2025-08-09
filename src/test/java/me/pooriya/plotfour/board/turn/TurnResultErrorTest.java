package me.pooriya.plotfour.board.turn;

import org.junit.Test;

import static me.pooriya.plotfour.board.turn.TurnResultError.TurnResultErrorType.FULL_COLUMN;
import static me.pooriya.plotfour.board.turn.TurnResultError.TurnResultErrorType.INVALID_COLUMN;
import static org.junit.Assert.*;

public class TurnResultErrorTest {

    @Test
    public void shouldCreateErrorResultWithErrorType() {
        TurnResultError result = TurnResultError.of(FULL_COLUMN);

        assertEquals(FULL_COLUMN, result.getErrorType());
        assertFalse(result.isSuccess());
    }

    @Test
    public void shouldEnsureImmutability() {
        TurnResultError result1 = TurnResultError.of(INVALID_COLUMN);
        TurnResultError result2 = TurnResultError.of(INVALID_COLUMN);

        assertEquals(result1, result2);
        assertEquals(result1.hashCode(), result2.hashCode());
    }

    @Test
    public void shouldCreateDifferentResultsForDifferentErrorTypes() {
        TurnResultError result1 = TurnResultError.of(FULL_COLUMN);
        TurnResultError result2 = TurnResultError.of(INVALID_COLUMN);

        assertNotEquals(result1, result2);
        assertNotEquals(result1.hashCode(), result2.hashCode());
    }

    @Test
    public void shouldImplementTurnResult() {
        TurnResultError result = TurnResultError.of(FULL_COLUMN);
        assertTrue(result instanceof TurnResult);
    }

    @Test
    public void shouldAlwaysReturnFalseForIsSuccess() {
        TurnResultError result1 = TurnResultError.of(FULL_COLUMN);
        TurnResultError result2 = TurnResultError.of(INVALID_COLUMN);

        assertFalse(result1.isSuccess());
        assertFalse(result2.isSuccess());
    }

    @Test
    public void shouldHaveCorrectErrorTypes() {
        TurnResultError.TurnResultErrorType[] errorTypes = TurnResultError.TurnResultErrorType.values();
        assertEquals(2, errorTypes.length);
        assertEquals(FULL_COLUMN, errorTypes[0]);
        assertEquals(INVALID_COLUMN, errorTypes[1]);
    }

    @Test
    public void shouldHaveCorrectValueOfForErrorTypes() {
        assertEquals(FULL_COLUMN, TurnResultError.TurnResultErrorType.valueOf("FULL_COLUMN"));
        assertEquals(INVALID_COLUMN, TurnResultError.TurnResultErrorType.valueOf("INVALID_COLUMN"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionForInvalidErrorType() {
        TurnResultError.TurnResultErrorType.valueOf("INVALID_TYPE");
    }

    @Test
    public void shouldHaveProperToString() {
        TurnResultError result = TurnResultError.of(FULL_COLUMN);
        String toString = result.toString();

        assertTrue(toString.contains("TurnResultError"));
        assertTrue(toString.contains("FULL_COLUMN"));
    }

    @Test
    public void shouldAllowNullErrorType() {
        // Note: TurnResultError doesn't have @NonNull annotation, so null is allowed
        TurnResultError result = TurnResultError.of(null);
        assertNull(result.getErrorType());
        assertFalse(result.isSuccess());
    }
}