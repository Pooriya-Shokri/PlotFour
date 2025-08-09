package me.pooriya.plotfour.board.turn;

import org.junit.Test;

import static me.pooriya.plotfour.board.turn.TurnResultError.TurnResultErrorType.FULL_COLUMN;
import static org.junit.Assert.*;

public class TurnResultTest {

    @Test
    public void shouldImplementTurnResultInterfaceForSuccess() {
        TurnResult successResult = TurnResultSuccess.of(1, 2);
        
        assertTrue(successResult instanceof TurnResult);
        assertTrue(successResult.isSuccess());
    }

    @Test
    public void shouldImplementTurnResultInterfaceForError() {
        TurnResult errorResult = TurnResultError.of(FULL_COLUMN);
        
        assertTrue(errorResult instanceof TurnResult);
        assertFalse(errorResult.isSuccess());
    }

    @Test
    public void shouldHaveCorrectInterfaceContract() {
        TurnResult successResult = TurnResultSuccess.of(0, 0);
        TurnResult errorResult = TurnResultError.of(FULL_COLUMN);

        // Test that both implementations properly implement the interface
        assertTrue("Success result should return true", successResult.isSuccess());
        assertFalse("Error result should return false", errorResult.isSuccess());
    }

    @Test
    public void shouldAllowPolymorphicUsage() {
        TurnResult[] results = {
            TurnResultSuccess.of(2, 3),
            TurnResultError.of(FULL_COLUMN)
        };

        // Should be able to call isSuccess on all implementations
        boolean[] expectedResults = {true, false};
        for (int i = 0; i < results.length; i++) {
            assertEquals("Result at index " + i + " should match expected", 
                        expectedResults[i], results[i].isSuccess());
        }
    }

    @Test
    public void shouldWorkWithInstanceOfChecks() {
        TurnResult successResult = TurnResultSuccess.of(1, 1);
        TurnResult errorResult = TurnResultError.of(FULL_COLUMN);

        if (successResult instanceof TurnResultSuccess) {
            TurnResultSuccess success = (TurnResultSuccess) successResult;
            assertEquals(1, success.getRowIndex());
            assertEquals(1, success.getColIndex());
        } else {
            fail("Should be instance of TurnResultSuccess");
        }

        if (errorResult instanceof TurnResultError) {
            TurnResultError error = (TurnResultError) errorResult;
            assertEquals(FULL_COLUMN, error.getErrorType());
        } else {
            fail("Should be instance of TurnResultError");
        }
    }
}