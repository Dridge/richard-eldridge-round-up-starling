package core;

import java.util.Currency;
import java.util.Locale;

/**
 * A POJO for account details
 */
public class Account {
    String accountUid = "";
    String accountType = "";
    Currency currency = Currency.getInstance(Locale.getDefault());
}
