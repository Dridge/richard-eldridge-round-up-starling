package core;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AccountManagerTest extends RequesterFactoryTest {
    AccountManager managerUnderTest;

    @Test
    void testAccountManagerGetsAccount() {
        String dummyResponse = "{\"accounts\":[{\"accountUid\":\"12345\",\"accountType\":\"PRIMARY\",\"defaultCategory\":\"98765\"";
        Mockito.when(factory.getRequestCommand(Mockito.any())).thenReturn(requester);
        Mockito.when(requester.getResponseCode()).thenReturn(200);
        Mockito.when(requester.getResponseBody()).thenReturn(dummyResponse);
        managerUnderTest = new AccountManager(factory);
        Account account = managerUnderTest.getAccount();
        assertEquals("12345", account.getAccountUid());
        assertEquals("98765", account.getCategoryUid());
    }
}
