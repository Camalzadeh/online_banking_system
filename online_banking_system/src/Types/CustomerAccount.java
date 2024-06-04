package Types;

import Enums.AccountType;
import POJOs.BankAccountPOJO;
import POJOs.CustomerPOJO;

import java.util.ArrayList;
import java.util.List;

public class CustomerAccount extends BankAccount {
    private Customer customer;
    private List<Transaction> transactionHistory = new ArrayList<>();
    private String username;
    private String password;
    public void setCustomer(CustomerPOJO pojo){
        customer = new Customer();
        customer.setCustomerID(pojo.id());
        customer.setAddress(pojo.address());
        customer.setEmail(pojo.email());
        customer.setPhone(pojo.phone());
        customer.setName(pojo.name());
    }
    public void setBankAccount(BankAccountPOJO pojo){
        setAccountNumber(pojo.accountNumber());
        double balance = pojo.balance();
        AccountType accountType = pojo.accountType();
    }

    public Customer getCustomer() {
        return customer;
    }

    public List<Transaction> getTransactionHistory() {
        return transactionHistory;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public void addTransaction(Transaction transaction) {
        transactionHistory.add(transaction);
    }

    public void setTransactionHistory(List<Transaction> transactionHistory) {
        this.transactionHistory = transactionHistory;
    }
}
