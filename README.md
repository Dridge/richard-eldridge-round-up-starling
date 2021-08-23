# Round Up

A java application to help users round up their purchases for their next saving goal

# Plan

1. Get an account details
1. Get a list of transactions from that account for a given week
1. Calculate rounding up
1. Transfer the difference to the savings goal
1. Authorization key should be read in, not hard coded

# Overall Structure

Assuming one account.
The RoundUpExecutor runs through each step: 
1. Get the account for given user
1. Get every transaction for that account within the last x time (tbc)
1. Get the total rounded up figure of that subset of transactions
1. Creates a savings goal
1. Transfers money from each account into the savings goal (tbc)

Values that might represent each install, or need to be shared across many parts of the application should be added as 
properties to `config.properties`, and loaded in by the `PropertiesReader`, exposed by a getter method in the 
`PropertiesManager`.

## RoundUp
The main class that runs the program, with help from the `PropertiesManager` and the `core.RoundUpExecutor`

## core.AccountManager
Will provide all accounts for the user defined in the `config.properties` file

Requires an accountHolderUid

Returns accountUid

## TransactionManager
Will provide a list of transactions over a given time period

Requires an accountUid

Returns a list of doubles

## RoundUpCalculator
Calculates the round up given a set of doubles.

Requires a list of doubles

returns a total for the round up of that week

## SavingGoalManager
Creates a named saving goal, or gets one matching the same name

Requires a savings goal name

returns the savingsGoalUid

## Transferor
Transfers the given amount(s) to the savings goals

Requires an accountUid, savingsGoalUid and an amount.

Returns true if successful, else program throws exception

