package Types;

import Enums.TransactionTypes;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Transaction {
    LocalDateTime date;
    TransactionTypes type;
    double amount;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
    public Transaction(LocalDateTime date, TransactionTypes type, double amount) {
        this.date = date;
        this.type = type;
        this.amount = amount;
    }
    public String toString(){
        return "Date: " + date.format(formatter) + "\nType: " + type.toString() + "\nAmount: " + amount;
    }
    public LocalDateTime getDate() {
        return date;
    }
}
