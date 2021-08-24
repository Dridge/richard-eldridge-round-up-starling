package core;

import requests.IRequestCommand;
import requests.RequestFactory;
import requests.RequestType;

import java.text.ParseException;
import java.util.List;

public class RoundUpExecutor extends PropertyAware {
    /**
     * Executes the round up
     */
    public void execute() throws Exception {
        RequestFactory factory = new RequestFactory();
        AccountManager accountManager = new AccountManager(factory);
        Account account = accountManager.getAccount();
        TransactionManager transactionManager = new TransactionManager(factory, account);
        List<Integer> transactions = transactionManager.getTransactionsFromPastWeek();
        transactions.stream().forEach(e -> System.out.println(e.doubleValue()));
//        SavingsGoalManager goalManager = new SavingsGoalManager(accountUid);
//        String savingsGoalUid = goalManager.getOrCreateSavingsGoal();

        int transferAmount = new RoundUpCalculator(transactions).getResult();
        System.out.println(transferAmount);
//        Transferor transferor = new Transferor();
//        transferor.execute(accountUid, savingsGoalUid, transferAmount);

    }
}
