# Round Up

A java application to help users round up their purchases for their next saving goal

# Plan

1. Get an account details
1. Get a list of transactions from that account for a given week
1. Calculate rounding up
1. Transfer the difference to the savings goal
1. Authorization key should be read in, not hard coded

# Overall Structure

The RoundUpExecutor runs through each step: 
1. Get all accounts for the given user
1. Get every transaction for each account within the last x time (tbc)
1. Get the total rounded up figure per account (within the last x time)
1. Creates a savings goal
1. Transfers money from each account into the savings goal (tbc)

Values that might represent each install, or need to be shared across many parts of the application should be added as 
properties to `config.properties`, and loaded in by the `PropertiesReader`, exposed by a getter method in the 
`PropertiesManager`.

## core.RoundUp
The main class that runs the program, with help from the `PropertiesManager` and the `core.RoundUpExecutor`

## AccountManager
Will provide all accounts for the user defined in the `config.properties` file

## TransactionManager
Will provide a list of transactions over a given time period

## SavingGoalManager
Creates a named saving goal

## Transferor
Transfers the given amount(s) to the savings goals


