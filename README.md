# Round Up

A java application to help users round up their purchases for their next saving goal
There's still plenty more I'd like to do, but this hopefully shows my thinking and direction of travel as well as
being functionally complete.

# Overall Structure

- The `core` package contains the majority of the code
- There is a simple properties reader in the `properties` package for values that should be more consistent
- A couple of empty Exception classes to indicate where a more specific exception would be appropriate in the aptly named `exception` package
- A `RequestFactory` is used to try to decouple the requests from the code for increased testability, in the `requests` package
- Either a get or put request can be created by the factory, leading to some unimplemented methods for put requests that need a body parameter and get requests that are sent a body parameter.
- When parsing json I did most of it with strings, but eventually found JSONsimple was useful for this. This was used in the `SavingsGoalManager`
- I think the `RoundUpExceutor` should be refactored with an easier to read design pattern (see java docs in class), but is left as is for now.
- Some additional logic was added to avoid attempting to transfer zero fund

The solution currently assumes one account, creates an 'auto generated' savings goal. Which is used to distinguish between goals created outside of this application. Time was also spent handling the cases where a savings goal did not exist, did exist, or did exist but was not an 'auto generated' savings goal.

The `RoundUpExecutor` is part of the core package, and runs through each step:
1. Get the account for given user id `AccountManager`
1. Get every transaction for that account within the last 7 days `TransactionManager`
1. Get the total rounded up figure of that subset of transactions `RoundUpCalculator`
1. Creates a savings goal `SavingsGoalManager`
1. Transfers money from each account into the savings goal `Transferor`

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

Returns a list of amounts

## RoundUpCalculator
Calculates the round up given a set of amounts.

Requires a list of integers

returns a total for the round up of the given transactions

## SavingGoalManager
Creates a named saving goal, or gets one matching the 'auto generate' name

Requires a savings goal name

returns the savingsGoalUid

## Transferor
Transfers the given amount(s) to the savings goals

Requires an accountUid, savingsGoalUid and an amount.

Returns true if successful, else program throws exception

