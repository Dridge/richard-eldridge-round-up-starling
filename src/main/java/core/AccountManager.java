package core;

import requests.IRequestCommand;
import java.util.Arrays;

public class AccountManager {
    private final String accountsEndPoint = "/api/v2/accounts";

    IRequestCommand requester;
    public AccountManager(IRequestCommand requester) {
        this.requester = requester;
    }

    /**
     * Gets accountUid if it exists, defaults to first account in list if multiple present
     * @return AccountUid of a users account
     */
    public Account getAccount() {
        requester.sendRequest(accountsEndPoint);
        String response = requester.getResponseBody();
        String accountUid = Arrays.asList(response
                .split(",|:"))
                .get(2)
                .replace("\"","");
        String defaultCategory = Arrays.asList(response
                .split(",|:"))
                .get(6)
                .replace("\"","");
        return new Account(accountUid, defaultCategory);
    }
}
