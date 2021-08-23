package core;

import requests.Requester;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AccountManager {
    private static final Logger logger = Logger.getLogger(AccountManager.class.getName());
    private final String accountsEndPoint = "/api/v2/accounts";
    private final String roundUpEndpoint = "/api/v2/feed/account/%s/round-up";
    private final String enableRoundUpEndpoint = "/api/v2/feed/account/%s/round-up";
    private final String createSavingsGoalEndpoint = "/api/v2/account/%s/savings-goals";
    List<Account> accountList = Collections.emptyList();

    public void getAccounts() {
        Requester requester = new Requester();
        requester.sendGetRequest(accountsEndPoint);
    }

    private List<String> getAllAccountUid() {
        List<String> allAccountUids = new ArrayList<>();
        allAccountUids.add("c717d23f-cde7-41d6-8c0f-deedc77742e5"); //TODO actually retrieve this, instead of hardcoded
        return allAccountUids;
    }

    /**
     * This uses the already available roundup feature, which works i presume on future transactions
     */
    public void enableRoundUp() {
        for (String accountUid : getAllAccountUid()) {
            Requester requester = new Requester();
            String body = "{\n" +
                    "  \"roundUpGoalUid\": \"%s\",\n" +
                    "  \"roundUpMultiplier\": 2\n" +
                    "}";
            requester.sendParameterisedPutRequest(enableRoundUpEndpoint, accountUid,
                    String.format(body, "176c7fea-ebe6-4cbc-8ce6-19ffa13684c0"));
        }
    }

    public void createSavingsGoal() {
        for (String accountUid : getAllAccountUid()) {
            Requester requester = new Requester();
            String body = String.format("{\n" +
                    "  \"name\": \"Round Up Savings Goal\",\n" +
                    "  \"currency\": \"GBP\",\n" +
                    "  \"target\": {\n" +
                    "    \"currency\": \"GBP\",\n" +
                    "    \"minorUnits\": 123456\n" +
                    "  },\n" +
                    "  \"base64EncodedPhoto\": \"string\"\n" +
                    "}");
            requester.sendParameterisedPutRequest(createSavingsGoalEndpoint, accountUid, body);
        }
    }

    public boolean getSavingsGoal() {
        boolean hasSavingsGoal = false;
        for (String accountUid : getAllAccountUid()) {
            Requester requester = new Requester();
            requester.sendParameterisedGetRequest(createSavingsGoalEndpoint, accountUid);
            if (requester.responseContains("savingsGoalUid")) {
                hasSavingsGoal = true;
            }
        }
        return hasSavingsGoal;
    }

    public void doRoundUp() {
        for (String accountUid : getAllAccountUid()) {
            Requester requester = new Requester();
            requester.sendParameterisedGetRequest(roundUpEndpoint, accountUid);
        }
    }

    public String getRoundUpId() {
        for (String accountUid : getAllAccountUid()) {
            Requester requester = new Requester();
            requester.sendParameterisedGetRequest(roundUpEndpoint, accountUid);
        }
        return "";
    }

    /**
     * /api/v2/feed/account/{accountUid}/category/{categoryUid}/transactions-between
     * @param nWeeksAgo
     */
    public void getTransactionsFromPastWeek(int nWeeksAgo) {
    }

    /**
     *
     */
    public void workOutRoundUps() {
    }

    /**
     * /api/v2/account/{accountUid}/savings-goals/{savingsGoalUid}/add-money/{transferUid}
     */
    public void transferRoundedUpToSavingsGoal() {
    }
}
