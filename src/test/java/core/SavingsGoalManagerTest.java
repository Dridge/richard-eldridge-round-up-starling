package core;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SavingsGoalManagerTest extends RequesterFactoryTest {
    SavingsGoalManager goalManager;
    String dummyResponse = """
            {"savingsGoalList":[{"savingsGoalUid":"176c7fea-ebe6-4cbc-8ce6-19ffa13684c0","name":"Round Up Savings Goal","target":{"currency":"GBP","minorUnits":123456},"totalSaved":{"currency":"GBP","minorUnits":0},"savedPercentage":0}]}
            """;
    @Test
    public void testWhenSavingsGoalExists_thenReturnCorrectUid() {
        goalManager = new SavingsGoalManager(factory, "12345");
        String expectedUid = "176c7fea-ebe6-4cbc-8ce6-19ffa13684c0";
        Mockito.when(factory.getRequestCommand(Mockito.any())).thenReturn(requester);
        Mockito.when(requester.getResponseBody()).thenReturn(dummyResponse);
        String savingsGoalUid = goalManager.getOrCreateSavingsGoal();
        assertEquals(expectedUid, savingsGoalUid, "Expected: " + expectedUid + ", was: " + savingsGoalUid);
    }
}
