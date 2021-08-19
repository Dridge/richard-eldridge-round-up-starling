package core;

import requests.Requester;

import java.util.ArrayList;
import java.util.List;

public class AccountManager {
    private final String accountsEndPoint = "/api/v2/accounts";
    private final String roundUpEndpoint = "/api/v2/feed/account/%s/round-up";
    private final String enableRoundUpEndpoint = "/api/v2/feed/account/%s/round-up";

    public void getAccounts() {
        Requester requester = new Requester();
        requester.sendRequest(accountsEndPoint);
        System.out.println(requester.getResponse());
    }

    private List<String> getAllAccountUid() {
        List<String> allAccountUids = new ArrayList<>();
        allAccountUids.add("c717d23f-cde7-41d6-8c0f-deedc77742e5"); //TODO actually retrieve this, instead of hardcoded
        return allAccountUids;
    }

    public void enableRoundUp() {
        for (String accountUid : getAllAccountUid()) {
            Requester requester = new Requester();
            requester.sendParameterisedRequest(enableRoundUpEndpoint, accountUid);
            System.out.println(requester.getResponse());
        }
    }

    public void doRoundUp() {
        for (String accountUid : getAllAccountUid()) {
            Requester requester = new Requester();
            requester.sendParameterisedRequest(roundUpEndpoint, accountUid);
            System.out.println(requester.getResponse());
        }
    }

}
