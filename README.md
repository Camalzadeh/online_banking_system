# Online Banking System

Customer accounts, deposits, withdrawals and transfers, driven from a terminal menu.
Java Lessons, task 3.

## What it covers

- POJOs separated from behaviour - `BankAccountPOJO` and `CustomerPOJO` carry the
  data, `BankAccount` and `CustomerAccount` act on it.
- Enums for account type, customer type, transaction type and transaction status.
- Custom exceptions - `InsufficientFundsException`, `InvalidAccountException` -
  thrown from the transaction path instead of failing silently.
- A transaction log appended to disk.

## Running it

```bash
javac -d out $(find online_banking_system/src -name "*.java")
java -cp out Main
```

The admin menu is protected by a password read from the environment, defaulting to
`admin` when nothing is set:

```bash
ADMIN_PASSWORD=choose-something java -cp out Main
```

## Layout

- `online_banking_system/src/POJOs/`, `Types/`, `Enums/`, `Exceptions/` - the model.
- `online_banking_system/src/Main.java` - the menus and the entry point.
