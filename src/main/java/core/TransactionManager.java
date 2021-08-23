package core;

import requests.IRequestCommand;

import java.util.List;

public class TransactionManager {
    String requestString = "/api/v2/feed/account/{accountUid}/category/{categoryUid}/transactions-between";
    Account account;
    IRequestCommand requester;
    public TransactionManager(IRequestCommand requester, Account account) {
        this.account = account;
        this.requester = requester;
    }

    /**
     * Get transactions between any previous 7 day period (sun-mon?)
     *
     * @param i
     * @return
     */
    public List<Double> getTransactionsFromPastWeek(int i) {
        String body = "";

        return null;
    }
}
