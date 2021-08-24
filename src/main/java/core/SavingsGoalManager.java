package core;

import requests.*;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class SavingsGoalManager extends PropertyAware {
    public static final String AUTO_SAVINGS_GOAL = "Auto Savings Goal";
    public static final String SAVINGS_GOAL_UID = "savingsGoalUid";
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
        String savingsGoalUid;
        //If the property is available, no need for extra work set it and move on
        if(!getSavingsGoalUidProperty().isEmpty()) {
            savingsGoalUid = getSavingsGoalUidProperty();
        } else {
            // If the property is not available, check if an auto generated goal exists use that one
            sendSavingsGoalRequest(savingsGoalEndpoint);
            if (requester.responseContains(SAVINGS_GOAL_UID)
                    || requester.responseContains(AUTO_SAVINGS_GOAL)) {
                savingsGoalUid = getSavingsGoalUidFromResponse(requester.getResponseBody());
            } else {
                // scenario where we need to create a new savings goal
                requester = factory.getRequestCommand(RequestType.PUT);
                String body = String.format("""
                        {
                          "name": "%s",
                          "currency": "GBP",
                          "target": {
                            "currency": "GBP",
                            "minorUnits": 999
                          },
                          "base64EncodedPhoto": "string"
                        }""", AUTO_SAVINGS_GOAL);
                requester.sendRequest(String.format(savingsGoalEndpoint, accountUid), body);
                savingsGoalUid = getSavingsGoalUidFromResponse(requester.getResponseBody());
            }
        }
        setSavingsGoalUidProperty(savingsGoalUid);
        return savingsGoalUid;
    }

    private void sendSavingsGoalRequest(String savingsGoalEndpoint) {
        requester = factory.getRequestCommand(RequestType.GET);
        requester.sendRequest(String.format(savingsGoalEndpoint, accountUid));
    }

    /**
     * A bit of a verbose way to extract the field we need, look for JSON java interpreters in future
     * Handles both put and get responses, but like other response body handlers is very brittle at the moment
     * @param body
     * @return The SavingsGoalUid
     */
    private String getSavingsGoalUidFromResponse(String body) {
        String uid = "";

        
        String savingsGoalUidFieldText = "\"" + SAVINGS_GOAL_UID + "\":";
        List<String> feedItems = Arrays.asList(body.split("\\{"));
        List<String> savingsGoalsOnly = feedItems.subList(2, feedItems.size())
                .stream()
                .filter(e -> e.contains(savingsGoalUidFieldText))
                .collect(Collectors.toList());
        String[] item = savingsGoalsOnly.get(0).split(",");
        uid = item[0].split(":")[1].replace("\"", "");
        return uid;
    }
}
