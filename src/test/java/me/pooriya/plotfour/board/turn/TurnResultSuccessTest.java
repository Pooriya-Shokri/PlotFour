package me.pooriya.plotfour.board.turn;

import org.junit.Test;

import static org.junit.Assert.*;

public class TurnResultSuccessTest {

    @Test
    public void shouldCreateSuccessResultWithRowAndColumn() {
        int rowIndex = 3;
        int colIndex = 5;

        TurnResultSuccess result = TurnResultSuccess.of(rowIndex, colIndex);

        assertEquals(rowIndex, result.getRowIndex());
        assertEquals(colIndex, result.getColIndex());
        assertTrue(result.isSuccess());
    }

    @Test
    public void shouldEnsureImmutability() {
        int rowIndex = 2;
        int colIndex = 4;

        TurnResultSuccess result1 = TurnResultSuccess.of(rowIndex, colIndex);
        TurnResultSuccess result2 = TurnResultSuccess.of(rowIndex, colIndex);

        assertEquals(result1, result2);
        assertEquals(result1.hashCode(), result2.hashCode());
    }

    @Test
    public void shouldCreateDifferentResultsForDifferentIndices() {
        TurnResultSuccess result1 = TurnResultSuccess.of(1, 2);
        TurnResultSuccess result2 = TurnResultSuccess.of(3, 4);

        assertNotEquals(result1, result2);
        assertNotEquals(result1.hashCode(), result2.hashCode());
    }

    @Test
    public void shouldAllowZeroIndices() {
        TurnResultSuccess result = TurnResultSuccess.of(0, 0);

        assertEquals(0, result.getRowIndex());
        assertEquals(0, result.getColIndex());
        assertTrue(result.isSuccess());
    }

    @Test
    public void shouldAllowNegativeIndices() {
        TurnResultSuccess result = TurnResultSuccess.of(-1, -1);

        assertEquals(-1, result.getRowIndex());
        assertEquals(-1, result.getColIndex());
        assertTrue(result.isSuccess());
    }

    @Test
    public void shouldImplementTurnResult() {
        TurnResultSuccess result = TurnResultSuccess.of(1, 2);
        assertTrue(result instanceof TurnResult);
    }

    @Test
    public void shouldAlwaysReturnTrueForIsSuccess() {
        TurnResultSuccess result1 = TurnResultSuccess.of(0, 0);
        TurnResultSuccess result2 = TurnResultSuccess.of(5, 6);
        TurnResultSuccess result3 = TurnResultSuccess.of(-1, -1);

        assertTrue(result1.isSuccess());
        assertTrue(result2.isSuccess());
        assertTrue(result3.isSuccess());
    }

    @Test
    public void shouldHaveProperToString() {
        TurnResultSuccess result = TurnResultSuccess.of(3, 7);
        String toString = result.toString();

        assertTrue(toString.contains("TurnResultSuccess"));
        assertTrue(toString.contains("3"));
        assertTrue(toString.contains("7"));
    }
}