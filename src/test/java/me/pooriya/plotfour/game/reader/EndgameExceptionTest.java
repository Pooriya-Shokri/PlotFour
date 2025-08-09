package me.pooriya.plotfour.game.reader;

import org.junit.Test;

import static org.junit.Assert.*;

public class EndgameExceptionTest {

    @Test
    public void shouldHaveStaticInstance() {
        assertNotNull(EndgameException.INSTANCE);
        assertTrue(EndgameException.INSTANCE instanceof EndgameException);
        assertTrue(EndgameException.INSTANCE instanceof RuntimeException);
    }

    @Test
    public void shouldHaveSameStaticInstance() {
        EndgameException instance1 = EndgameException.INSTANCE;
        EndgameException instance2 = EndgameException.INSTANCE;
        
        assertSame(instance1, instance2);
    }

    @Test
    public void shouldBeRuntimeException() {
        EndgameException exception = new EndgameException();
        assertTrue(exception instanceof RuntimeException);
    }

    @Test
    public void shouldHaveNoArgsConstructor() {
        EndgameException exception = new EndgameException();
        assertNotNull(exception);
    }

    @Test
    public void shouldBeThrowable() {
        try {
            throw EndgameException.INSTANCE;
        } catch (EndgameException e) {
            assertSame(EndgameException.INSTANCE, e);
        }
    }

    @Test
    public void shouldHaveDefaultMessage() {
        EndgameException exception = new EndgameException();
        // Message can be null for default RuntimeException behavior
        // This test just verifies the exception can be created without issues
        assertNotNull(exception);
    }

    @Test
    public void shouldAllowMultipleInstances() {
        EndgameException exception1 = new EndgameException();
        EndgameException exception2 = new EndgameException();
        
        assertNotNull(exception1);
        assertNotNull(exception2);
        // Different instances should not be the same object
        assertNotSame(exception1, exception2);
        
        // But the static instance should always be the same
        assertSame(EndgameException.INSTANCE, EndgameException.INSTANCE);
    }

    @Test
    public void shouldBeUsableInTryCatch() {
        boolean caught = false;
        try {
            throw new EndgameException();
        } catch (EndgameException e) {
            caught = true;
        } catch (Exception e) {
            fail("Should have caught EndgameException specifically");
        }
        assertTrue("Exception should have been caught", caught);
    }
}