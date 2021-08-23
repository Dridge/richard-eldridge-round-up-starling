package core;

import requests.Requester;

public class RoundUpExecutor extends PropertyAware {
    /**
     * Executes the round up
     */
    public void execute() {
        Requester requester = new Requester();
        requester.sendGetRequest("/api/v2/account-holder/individual");

        AccountManager accountManager = new AccountManager();
        accountManager.getAccounts();
        //TODO retrieve transactions for a customer
        accountManager.getTransactionsFromPastWeek(1);
        accountManager.workOutRoundUps();
        //accountManager.createSavingsGoal(); //TODO if savings goal is not present, create one?
        accountManager.getSavingsGoal();
        accountManager.transferRoundedUpToSavingsGoal();
//        accountManager.getRoundUpId();
//        accountManager.enableRoundUp();
//        accountManager.doRoundUp();
    }
}
