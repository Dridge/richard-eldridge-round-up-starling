package core;

/**
 * A POJO for account details
 */
public class Account {
    private String accountUid = "";
    private String defaultCategory = "";

    Account(String accountUid, String defaultCategory) {
        this.accountUid = accountUid;
        this.defaultCategory = defaultCategory;
    }

    public String getAccountUid() {
        return accountUid;
    }

    public String getDefaultCategory() {
        return defaultCategory;
    }
}
