package core;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TransferorTest extends RequesterFactoryTest {

    @Test
    public void testExecutingWithZero_ReturnsTrue() {
        Mockito.when(factory.getRequestCommand(Mockito.any())).thenReturn(requester);
        Mockito.when(requester.getResponseCode()).thenReturn(200);
        Mockito.when(requester.getResponseBody()).thenReturn("");
        assertTrue(Transferor.execute(factory, "", "", 1));
    }

    @Test
    public void testExecutingValidRequest_ReturnsTrue() {
        Mockito.when(factory.getRequestCommand(Mockito.any())).thenReturn(requester);
        Mockito.when(requester.getResponseCode()).thenReturn(200);
        Mockito.when(requester.getResponseBody()).thenReturn("");
        assertTrue(Transferor.execute(factory, "", "", 1));
    }

    @Test
    public void testExecutingReturnsNon200Code_ReturnsFalse() {
        Mockito.when(factory.getRequestCommand(Mockito.any())).thenReturn(requester);
        Mockito.when(requester.getResponseCode()).thenReturn(201);
        Mockito.when(requester.getResponseBody()).thenReturn("");
        assertFalse(Transferor.execute(factory, "", "", 1));
    }

    @Test
    public void testExecutingReturns400Code_ReturnsFalse() {
        Mockito.when(factory.getRequestCommand(Mockito.any())).thenReturn(requester);
        Mockito.when(requester.getResponseCode()).thenReturn(400);
        Mockito.when(requester.getResponseBody()).thenReturn("");
        assertFalse(Transferor.execute(factory, "", "", 1));
    }
}
