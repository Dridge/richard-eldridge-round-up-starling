package core;

import requests.*;

public class SavingsGoalManager {
    private final String accountUid;
    public SavingsGoalManager(IRequestCommand requester, String accountUid) {
        this.accountUid = accountUid;
    }

    public String getOrCreateSavingsGoal() {
        String createSavingsGoalEndpoint = "/api/v2/account/%s/savings-goals";
        IRequestCommand requester = RequestFactory.getRequestCommand(RequestType.GET);
        requester.sendParameterisedRequest(createSavingsGoalEndpoint, accountUid);
        if (requester.responseContains("savingsGoalUid")
                && requester.responseContains("Round Up Savings Goal")) {
            //TODO get existing savings goal?
        } else {
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
            requester.sendParameterisedRequest(createSavingsGoalEndpoint, accountUid, body);
        }
        return "";
    }
}
