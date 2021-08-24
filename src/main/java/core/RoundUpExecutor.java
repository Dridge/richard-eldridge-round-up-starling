package core;

import exception.SavingsGoalParsingException;
import exception.TransferFailedException;
import requests.RequestFactory;
import java.util.List;

public class RoundUpExecutor {
    /**
     * Executes the round up
     * TODO command pattern? builder pattern? or chain of responsibility?
     */
    public void execute() throws TransferFailedException, SavingsGoalParsingException {
        RequestFactory factory = new RequestFactory();
        AccountManager accountManager = new AccountManager(factory);
        Account account = accountManager.getAccount();
        TransactionManager transactionManager = new TransactionManager(factory, account.getAccountUid(), account.getCategoryUid());
        List<Integer> transactions = transactionManager.getTransactionsFromPastWeek();
        String savingsGoalUid = new SavingsGoalManager(factory, account.getAccountUid()).getOrCreateSavingsGoal();
        int transferAmount = new RoundUpCalculator(transactions).getResult();
        if(!(Transferor.execute(factory, account.getAccountUid(), savingsGoalUid, transferAmount))) {
            throw new TransferFailedException();
        }
    }
}
