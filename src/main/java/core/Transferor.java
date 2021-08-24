package core;

public class Transferor {
    /**
     * Performs the transfer request, and only returns true if the transfer was made successfully
     * /api/v2/account/{accountUid}/savings-goals/{savingsGoalUid}/add-money/{transferUid}
     *      is transferUid generated?
     * @param accountUid
     * @param savingsGoalUid
     * @param transferAmount
     * @return boolean whether the transfer was successful or not.
     */
    public boolean execute(String accountUid, String savingsGoalUid, int transferAmount) {
        return false;
    }
}
