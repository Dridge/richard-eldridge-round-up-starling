package core;

import requests.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class SavingsGoalManager {
    private final String accountUid;
    private IRequestCommand requester;
    private RequestFactory factory;
    public SavingsGoalManager(RequestFactory factory, String accountUid) {
        this.factory = factory;
        this.requester = factory.getRequestCommand(RequestType.GET);
        this.accountUid = accountUid;
    }

    /**
     * Gets the savings goal for round ups, if it doesnt exist creates it first
     * @return savingsGoalUid
     */
    public String getOrCreateSavingsGoal() {
        String savingsGoalEndpoint = "/api/v2/account/%s/savings-goals";
        requester = factory.getRequestCommand(RequestType.GET);
        requester.sendParameterisedRequest(savingsGoalEndpoint, accountUid);

        if (!requester.responseContains("savingsGoalUid")
                && !requester.responseContains("Round Up Savings Goal")) {
            requester = factory.getRequestCommand(RequestType.PUT);
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
            requester.sendParameterisedRequest(savingsGoalEndpoint, body, accountUid);
        }

        return getSavingsGoalUidFromResponse(requester.getResponseBody());
    }

    private String getSavingsGoalUidFromResponse(String body) {
        String uid = "";
        String savingsGoalUidFieldText = "\"savingsGoalUid\":";
        List<String> feedItems = Arrays.asList(body.split("\\{"));
        List<String> savingsGoalsOnly = feedItems.subList(2, feedItems.size())
                .stream()
                .filter(e -> e.contains(savingsGoalUidFieldText))
                .collect(Collectors
                        .toList());
        String[] item = savingsGoalsOnly.get(0).split(",");
        uid= item[0].split(":")[1].replace("\"", "");
        return uid;
    }
}
