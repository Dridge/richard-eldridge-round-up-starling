package core;

import requests.Requester;

public class RoundUpExecutor extends PropertyAware {
    /**
     * Executes the round up
     */
    public void execute() {
        Requester requester = new Requester();
        requester.sendRequest("/api/v2/account-holder/individual");
        System.out.println(requester.getResponse());

        AccountManager accountManager = new AccountManager();
        accountManager.getAccounts();
        accountManager.enableRoundUp();
        accountManager.doRoundUp();
    }
}
