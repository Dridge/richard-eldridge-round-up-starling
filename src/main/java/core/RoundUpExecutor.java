package core;

import requests.IRequestCommand;
import requests.RequestFactory;
import requests.RequestType;

import java.util.List;

public class RoundUpExecutor extends PropertyAware {
    /**
     * Executes the round up
     */
    public void execute() {
        AccountManager accountManager = new AccountManager(RequestFactory.getRequestCommand(RequestType.GET));
        Account account = accountManager.getAccount();
        //TODO retrieve transactions for a customer
        TransactionManager transactionManager = new TransactionManager(RequestFactory.getRequestCommand(RequestType.PUT), account);
        List<Double> transactions = transactionManager.getTransactionsFromPastWeek(1);

//        SavingsGoalManager goalManager = new SavingsGoalManager(accountUid);
//        String savingsGoalUid = goalManager.getOrCreateSavingsGoal();
//
//        RoundUpCalculator calculator = new RoundUpCalculator(transactions);
//        Double transferAmount = calculator.getResult();
//
//        Transferor transferor = new Transferor();
//        transferor.execute(accountUid, savingsGoalUid, transferAmount);

    }
}
