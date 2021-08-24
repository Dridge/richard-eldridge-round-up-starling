package core;

import exception.SavingsGoalParsingException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import requests.*;

import java.util.logging.Level;
import java.util.logging.Logger;

public class SavingsGoalManager extends PropertyAware {
    private static final Logger logger = Logger.getLogger(SavingsGoalManager.class.getName());

    public static final String AUTO_SAVINGS_GOAL_NAME = "Auto Savings Goal";
    public static final String SAVINGS_GOAL_UID = "savingsGoalUid";
    private final String accountUid;
    private IRequestCommand requester;
    private RequestFactory factory;

    public SavingsGoalManager(RequestFactory factory, String accountUid) {
        this.factory = factory;
        this.requester = factory.getRequestCommand(RequestType.GET);
        this.accountUid = accountUid;
    }

    /**
     * Gets the savings goal for round ups, if it doesnt exist creates it first
     * @return savingsGoalUid
     */
    public String getOrCreateSavingsGoal() throws SavingsGoalParsingException {
        String savingsGoalEndpoint = "/api/v2/account/%s/savings-goals";
        String savingsGoalUid;
        //If the property is available, no need for extra work set it and move on
        savingsGoalUid = getSavingsGoalUidPropertyOrNull();
        if(savingsGoalUid == null) {
            // If the property is not available, check if an auto generated goal exists use that one
            sendSavingsGoalRequest(savingsGoalEndpoint);
            savingsGoalUid = getSavingsGoalUidFromResponseWithJsonParser(requester.getResponseBody());
            if (savingsGoalUid.isEmpty()) {
                // if it doesn't exit create it
                requester = factory.getRequestCommand(RequestType.PUT);
                String body = String.format("""
                        {
                          "name": "%s",
                          "currency": "GBP",
                          "target": {
                            "currency": "GBP",
                            "minorUnits": 999
                          },
                          "base64EncodedPhoto": "string"
                        }""", AUTO_SAVINGS_GOAL_NAME);
                requester.sendRequest(String.format(savingsGoalEndpoint, accountUid), body);
                System.out.println(requester.getResponseBody());
                savingsGoalUid = getSavingsGoalUidFromResponseWithJsonParser(requester.getResponseBody());
            }
        }
        setSavingsGoalUidProperty(savingsGoalUid);
        return savingsGoalUid;
    }

    private void sendSavingsGoalRequest(String savingsGoalEndpoint) {
        requester = factory.getRequestCommand(RequestType.GET);
        requester.sendRequest(String.format(savingsGoalEndpoint, accountUid));
    }

    /**
     * Improved to use JSONsimple, copying examples from
     * https://code.google.com/archive/p/json-simple/wikis/DecodingExamples.wiki
     * @param body
     * @return The SavingsGoalUid
     */
    private String getSavingsGoalUidFromResponseWithJsonParser(String body) throws SavingsGoalParsingException {
        String uid = "";
        JSONParser parser = new JSONParser();
        try {
            JSONObject jsonObj  = (JSONObject) parser.parse(body);
            JSONArray array = (JSONArray) jsonObj.get("savingsGoalList");
            for(Object savingsGoal : array) {
                if (((JSONObject) savingsGoal).get("name").equals(AUTO_SAVINGS_GOAL_NAME)) {
                    return ((JSONObject) savingsGoal).get(SAVINGS_GOAL_UID).toString();
                }
            }
        } catch (ParseException e) {
            logger.log(Level.WARNING, "ERROR, json in unexpected format. Unable to parse.\n"
                    + e.getMessage());
            throw new SavingsGoalParsingException();
        }
        return uid;
    }
}
