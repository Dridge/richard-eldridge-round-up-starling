package core;

import core.AccountManager;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import requests.IRequestCommand;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AccountManagerTest {
    AccountManager managerUnderTest;
    IRequestCommand requester = Mockito.mock(IRequestCommand.class);


    @Test
    void testAccountManagerGetsAccount() {
        String dummyResponse = "{\"accounts\":[{\"accountUid\":\"12345\",\"accountType\":\"PRIMARY\",\"defaultCategory\":\"98765\"";
        Mockito.when(requester.getResponseCode()).thenReturn(200);
        Mockito.when(requester.getResponseBody()).thenReturn(dummyResponse);
        managerUnderTest = new AccountManager(requester);
        Account account = managerUnderTest.getAccount();
        assertEquals("12345", account.getAccountUid());
        assertEquals("98765", account.getDefaultCategory());
    }
}
