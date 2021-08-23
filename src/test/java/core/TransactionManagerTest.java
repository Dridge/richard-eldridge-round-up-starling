package core;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import requests.IRequestCommand;

import java.text.ParseException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TransactionManagerTest {
    TransactionManager managerUnderTest;
    IRequestCommand requester = Mockito.mock(IRequestCommand.class);
    Account account = Mockito.mock(Account.class);

    private String dummyResponse = """
                {
                  "feedItems": [
                    {
                      "feedItemUid": "11221122-1122-1122-1122-112211221122",
                      "categoryUid": "98765",
                      "amount": {
                        "currency": "GBP",
                        "minorUnits": 123456
                      },
                      "sourceAmount": {
                        "currency": "GBP",
                        "minorUnits": 123456
                      },
                      "direction": "OUT",
                      "updatedAt": "2021-08-23T18:32:06.105Z",
                      "transactionTime": "2021-08-23T18:32:06.105Z",
                      "settlementTime": "2021-08-23T18:32:06.105Z",
                      "retryAllocationUntilTime": "2021-08-23T18:32:06.105Z",
                      "source": "MASTER_CARD",
                      "sourceSubType": "CONTACTLESS",
                      "status": "PENDING",
                      "transactingApplicationUserUid": "aaaaaaaa-aaaa-4aaa-aaaa-aaaaaaaaaaaa",
                      "counterPartyType": "MERCHANT",
                      "counterPartyUid": "68e16af4-c2c3-413b-bf93-1056b90097fa",
                      "counterPartyName": "Tesco",
                      "counterPartySubEntityUid": "35d46207-d90e-483c-a40a-128cc4da4bee",
                      "counterPartySubEntityName": "Tesco Southampton",
                      "counterPartySubEntityIdentifier": "608371",
                      "counterPartySubEntitySubIdentifier": "01234567",
                      "exchangeRate": 0,
                      "totalFees": 0,
                      "totalFeeAmount": {
                        "currency": "GBP",
                        "minorUnits": 123456
                      },
                      "reference": "TESCO-STORES-6148      SOUTHAMPTON   GBR",
                      "country": "GB",
                      "spendingCategory": "GROCERIES",
                      "userNote": "Tax deductable, submit me to payroll",
                      "roundUp": {
                        "goalCategoryUid": "68e16af4-c2c3-413b-bf93-1056b90097fa",
                        "amount": {
                          "currency": "GBP",
                          "minorUnits": 123456
                        }
                      },
                      "hasAttachment": true,
                      "hasReceipt": true
                    }
                  ]
                }""";

    @Test
    void testAccountManagerGetsAccount() throws Exception {
        Mockito.when(requester.getResponseCode()).thenReturn(200);
        Mockito.when(requester.getResponseBody()).thenReturn(dummyResponse);
        Mockito.when(account.getAccountUid()).thenReturn("12345");
        Mockito.when(account.getDefaultCategory()).thenReturn("98765");
        managerUnderTest = new TransactionManager(requester, account);
        List<Integer> listTransaction = managerUnderTest.getTransactionsFromPastWeek();
        assertEquals(123456, listTransaction.get(0)); // equivalent of 1234.56
    }
}
