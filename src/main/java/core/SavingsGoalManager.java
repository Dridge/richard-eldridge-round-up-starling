package core;

import requests.*;

public class SavingsGoalManager {
    private final String accountUid;
    private IRequestCommand requester;
    public SavingsGoalManager(IRequestCommand requester, String accountUid) {
        this.requester = requester;
        this.accountUid = accountUid;
    }

    public String getOrCreateSavingsGoal() {
        String createSavingsGoalEndpoint = "/api/v2/account/%s/savings-goals";
        requester.sendParameterisedRequest(createSavingsGoalEndpoint, accountUid);
        if (requester.responseContains("savingsGoalUid")
                && requester.responseContains("Round Up Savings Goal")) {
            //TODO get existing savings goal?
        } else { //TODO split this into two methods for testability
            requester = RequestFactory.getRequestCommand(RequestType.PUT);
            String body = """
                    {
                      "name": "Round Up Savings Goal",
                      "currency": "GBP",
                      "target": {
                        "currency": "GBP",
                        "minorUnits": 123456
                      },
                      "base64EncodedPhoto": "string"
                    }""";
            requester.sendParameterisedRequest(createSavingsGoalEndpoint, body, accountUid);
        }
        return "";
    }
}
