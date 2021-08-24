package core;

import requests.RequestFactory;
import java.util.List;

public class RoundUpExecutor extends PropertyAware {
    /**
     * Executes the round up
     * TODO command pattern? builder pattern?
     */
    public void execute() {
        RequestFactory factory = new RequestFactory();
        AccountManager accountManager = new AccountManager(factory);
        Account account = accountManager.getAccount();
        TransactionManager transactionManager = new TransactionManager(factory, account.getAccountUid(), account.getCategoryUid());
        List<Integer> transactions = transactionManager.getTransactionsFromPastWeek();
        String savingsGoalUid = new SavingsGoalManager(factory, account.getAccountUid()).getOrCreateSavingsGoal();
        int transferAmount = new RoundUpCalculator(transactions).getResult();
        new Transferor().execute(account.getAccountUid(), savingsGoalUid, transferAmount);
    }
}
