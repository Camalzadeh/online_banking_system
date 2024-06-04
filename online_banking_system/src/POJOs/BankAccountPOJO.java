package POJOs;

import Enums.AccountType;

public record BankAccountPOJO(int accountNumber, double balance, AccountType accountType) {
}
