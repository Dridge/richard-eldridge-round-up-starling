package core;

/**
 * A POJO for account details
 */
public class Account {
    private String accountUid = "";
    private String categoryUid = "";

    Account(String accountUid, String categoryUid) {
        this.accountUid = accountUid;
        this.categoryUid = categoryUid;
    }

    public String getAccountUid() {
        return accountUid;
    }

    public String getCategoryUid() {
        return categoryUid;
    }
}
