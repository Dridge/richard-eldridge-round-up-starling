package core;

import requests.IRequestCommand;
import requests.RequestFactory;
import requests.RequestType;

import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Transferor {
    private static final Logger logger = Logger.getLogger(Transferor.class.getName());

    /**
     * Performs the transfer request, and only returns true if the transfer was made successfully
     *
     * @param accountUid
     * @param savingsGoalUid
     * @param transferAmount
     * @return boolean whether the transfer was successful or not.
     */
    public static boolean execute(RequestFactory factory, String accountUid, String savingsGoalUid, int transferAmount) {
        if (transferAmount <= 0) {
            logger.log(Level.INFO, "Nothing to transfer, no transactions with round ups in the period given");
            return true;
        }
        String transferEndpoint = "/api/v2/account/%s/savings-goals/%s/add-money/%s";
        String requestBody = """
                {
                  "amount": {
                    "currency": "GBP",
                    "minorUnits": %s
                  }
                }
                """;
        IRequestCommand requester = factory.getRequestCommand(RequestType.PUT);
        transferEndpoint = String.format(transferEndpoint, accountUid, savingsGoalUid, UUID.randomUUID());
        requester.sendRequest(transferEndpoint, String.format(requestBody, transferAmount));
        return requester.getResponseCode() == 200;
    }
}
