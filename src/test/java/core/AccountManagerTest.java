package core;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import requests.IRequestCommand;
import requests.RequestFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AccountManagerTest {
    AccountManager managerUnderTest;
    IRequestCommand requester = Mockito.mock(IRequestCommand.class);
    RequestFactory factory = Mockito.mock(RequestFactory.class);

    @Test
    void testAccountManagerGetsAccount() {
        String dummyResponse = "{\"accounts\":[{\"accountUid\":\"12345\",\"accountType\":\"PRIMARY\",\"defaultCategory\":\"98765\"";
        Mockito.when(factory.getRequestCommand(Mockito.any())).thenReturn(requester);
        Mockito.when(requester.getResponseCode()).thenReturn(200);
        Mockito.when(requester.getResponseBody()).thenReturn(dummyResponse);
        managerUnderTest = new AccountManager(factory);
        Account account = managerUnderTest.getAccount();
        assertEquals("12345", account.getAccountUid());
        assertEquals("98765", account.getDefaultCategory());
    }
}
