package me.pooriya.plotfour.board;

import org.junit.Test;

import static me.pooriya.plotfour.board.BoardSpecification.*;
import static org.junit.Assert.*;

public class BoardSpecificationTest {

    @Test
    public void shouldCreateBoardSpecificationWithRowsAndColumns() {
        int rows = 6;
        int columns = 7;

        BoardSpecification spec = BoardSpecification.of(rows, columns);

        assertEquals(rows, spec.getRows());
        assertEquals(columns, spec.getColumns());
    }

    @Test
    public void shouldEnsureImmutability() {
        int rows = 6;
        int columns = 7;

        BoardSpecification spec1 = BoardSpecification.of(rows, columns);
        BoardSpecification spec2 = BoardSpecification.of(rows, columns);

        assertEquals(spec1, spec2);
        assertEquals(spec1.hashCode(), spec2.hashCode());
    }

    @Test
    public void shouldCreateDifferentSpecsForDifferentDimensions() {
        BoardSpecification spec1 = BoardSpecification.of(6, 7);
        BoardSpecification spec2 = BoardSpecification.of(8, 9);

        assertNotEquals(spec1, spec2);
        assertNotEquals(spec1.hashCode(), spec2.hashCode());
    }

    @Test
    public void shouldHaveCorrectConstants() {
        assertEquals(5, MIN_VAL);
        assertEquals(9, MAX_VAL);
        assertEquals('x', SPLITTER_SYMBOL);
    }

    @Test
    public void shouldHaveDefaultSpecification() {
        assertNotNull(DEFAULT_SPECIFICATION);
        assertEquals(6, DEFAULT_SPECIFICATION.getRows());
        assertEquals(7, DEFAULT_SPECIFICATION.getColumns());
    }

    @Test
    public void shouldHaveProperToString() {
        BoardSpecification spec = BoardSpecification.of(6, 7);
        String toString = spec.toString();
        assertEquals("6 x 7", toString);
    }

    @Test
    public void shouldFormatToStringCorrectlyForDifferentSizes() {
        BoardSpecification spec1 = BoardSpecification.of(5, 8);
        BoardSpecification spec2 = BoardSpecification.of(9, 5);

        assertEquals("5 x 8", spec1.toString());
        assertEquals("9 x 5", spec2.toString());
    }

    @Test
    public void shouldAllowMinimumValues() {
        BoardSpecification spec = BoardSpecification.of(MIN_VAL, MIN_VAL);
        assertEquals(MIN_VAL, spec.getRows());
        assertEquals(MIN_VAL, spec.getColumns());
    }

    @Test
    public void shouldAllowMaximumValues() {
        BoardSpecification spec = BoardSpecification.of(MAX_VAL, MAX_VAL);
        assertEquals(MAX_VAL, spec.getRows());
        assertEquals(MAX_VAL, spec.getColumns());
    }

    @Test
    public void shouldAllowValuesWithinRange() {
        int midValue = (MIN_VAL + MAX_VAL) / 2;
        BoardSpecification spec = BoardSpecification.of(midValue, midValue);
        assertEquals(midValue, spec.getRows());
        assertEquals(midValue, spec.getColumns());
    }
}