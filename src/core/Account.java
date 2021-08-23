package core;

import org.jetbrains.annotations.Nullable;

import java.util.Currency;
import java.util.Locale;

/**
 * A POJO for account details
 */
public class Account {
    String accountUid = "";
    String accountType = "";
    Currency currency;

    Account(String accountUid, String accountType, @Nullable Currency currency) {
        this.accountUid = accountUid;
        this.accountType = accountType;
        this.currency = currency == null ? Currency.getInstance(Locale.getDefault()) : currency;
    }
}
