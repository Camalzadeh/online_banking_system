import Enums.AccountType;
import Enums.TransactionTypes;
import Exceptions.InsufficientFundsException;
import Exceptions.InvalidAccountException;
import POJOs.BankAccountPOJO;
import POJOs.CustomerPOJO;
import Types.CustomerAccount;
import Types.Transaction;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    static String adminPassword="humbet2006";
    static Scanner sc = new Scanner(System.in);
    static List<CustomerAccount> accounts= new ArrayList<>();
    static void addCustomerAccount(){
        CustomerAccount account = new CustomerAccount();
        while(true){
            System.out.println("Enter username: ");
            account.setUsername(sc.hasNext() ? sc.next():null );
            if(account.getUsername()!=null){
                break;
            }
            System.out.println("Invalid username");
        }
        while(true){
            System.out.println("Enter password: ");
            account.setPassword(sc.hasNext() ? sc.next():null );
            if(account.getPassword()!=null){
                break;
            }
            System.out.println("Invalid password");
        }
        int customerID;
        String name;
        String address;
        String phone;
        String email;
        int accountNumber;
        double balance;
        AccountType accountType;
        while(true){
            customerID = accounts.size()+1;
            System.out.printf("Remember your customer ID is %d \n",customerID );
            System.out.println("Enter name: ");
            name =  sc.hasNext() ? sc.next():null ;
            if(name==null){
                System.out.println("Invalid name");
                continue;
            }
            System.out.println("Enter address: ");
            address =  sc.hasNext() ? sc.next():null ;
            if(address==null){
                System.out.println("Invalid address");
                continue;
            }
            System.out.println("Enter phone like (0701112233): ");
            phone =  sc.hasNext() ? sc.next():null ;

            if(phone==null || phone.length()!=10 || phone.charAt(0)!='0' || phone.contains("0123456789")){
                System.out.println("Invalid phone");
                continue;
            }
            System.out.println("Enter email: ");
            email =  sc.hasNext() ? sc.next():null ;
            if(email==null || !email.endsWith("@gmail.com")){
                System.out.println("Invalid email");
                continue;
            }
            System.out.println("Enter account number: ");
            accountNumber =  sc.hasNextInt() ? sc.nextInt():-1 ;
            if(accountNumber==-1){
                System.out.println("Invalid account number");
                continue;
            }
            System.out.println("Enter balance: ");
            balance =  sc.hasNextDouble() ? sc.nextDouble():-1 ;
            if(balance<0){
                System.out.println("Invalid balance");
                continue;
            }
            System.out.println("Enter account type for Savings (1), for Checking (2): ");
            int temp =  sc.hasNextInt() ? sc.nextInt():0 ;
            if(temp!=1 && temp!=2){
                System.out.println("Invalid account type");
                continue;
            }
            accountType=AccountType.values()[temp-1];
            break;
        }
        CustomerPOJO pojoCustomer = new CustomerPOJO(customerID,name,address,phone,email);
        BankAccountPOJO pojoBankAccount = new BankAccountPOJO(accountNumber,balance,accountType);
        account.setCustomer(pojoCustomer);
        account.setBankAccount(pojoBankAccount);
        accounts.add(account);
        System.out.println("Account successfully created");
        mainMenu(customerID);
    }
    static void updateCustomerAccount(int customerID){
        String name= accounts.get(customerID - 1).getCustomer().getName();
        String address= accounts.get(customerID - 1).getCustomer().getAddress();
        String phone= accounts.get(customerID - 1).getCustomer().getPhone();
        String email= accounts.get(customerID - 1).getCustomer().getEmail();
        int accountNumber= accounts.get(customerID - 1).getAccountNumber();
        double balance= accounts.get(customerID - 1).getBalance();
        AccountType accountType= accounts.get(customerID - 1).getAccountType();
        while(true){
            System.out.printf("Current name %s, Enter new name: ",name );
            name =  sc.hasNext() ? sc.next():null ;
            if(name==null){
                System.out.println("Invalid name");
                continue;
            }
            System.out.printf("Current address %s, Enter new adress: ",address );
            address =  sc.hasNext() ? sc.next():null ;
            if(address==null){
                System.out.println("Invalid address");
                continue;
            }
            System.out.printf("Current phone %s, Enter new phone: ",phone );
            phone =  sc.hasNext() ? sc.next():null ;
            if(phone==null || phone.length()!=10 || phone.charAt(0)!='0' || phone.contains("0123456789")){
                System.out.println("Invalid phone");
                continue;
            }
            System.out.printf("Current email %s, Enter new email: ",email );
            email =  sc.hasNext() ? sc.next():null ;
            if(email==null || !email.endsWith("@gmail.com")){
                System.out.println("Invalid email");
                continue;
            }
            System.out.printf("Current account number %d, Enter new account number: ",accountNumber );
            accountNumber =  sc.hasNextInt() ? sc.nextInt():-1 ;
            if(accountNumber<0){
                System.out.println("Invalid account number");
                continue;
            }
            System.out.printf("Current balance %s, Enter new balance: ",balance );
            balance =  sc.hasNextDouble() ? sc.nextDouble():-1 ;
            if(balance<0){
                System.out.println("Invalid balance");
                continue;
            }
            System.out.printf("Current account type %s, Enter new account type: ",accountType.toString() );
            int temp =  sc.hasNextInt() ? sc.nextInt():0 ;
            if(temp!=1 && temp!=2){
                System.out.println("Invalid account type");
                continue;
            }
            accountType=AccountType.values()[temp-1];
            break;
        }
        CustomerPOJO pojoCustomer = new CustomerPOJO(customerID,name,address,phone,email);
        BankAccountPOJO pojoBankAccount = new BankAccountPOJO(accountNumber,balance,accountType);
        accounts.get(customerID - 1).setCustomer(pojoCustomer);
        accounts.get(customerID - 1).setBankAccount(pojoBankAccount);
        System.out.println("Account successfully updated");
    }
    static void deleteCustomerAccount(int customerID){
        accounts.remove(customerID-1);
        System.out.println("Account successfully deleted");
    }
    static void deposit(int customerID){
        double money;
        System.out.println("Enter Money to DEPOSIT: ");
        while(true){
            if(sc.hasNextDouble()){
                double temp=sc.nextDouble();
                if(temp>0){
                    money = temp;
                    break;
                }
            }
            System.out.println("Invalid money for DEPOSIT");
        }
        accounts.get(customerID - 1).setBalance(accounts.get(customerID - 1).getBalance() + money);
        Transaction transaction = new Transaction(LocalDateTime.now(), TransactionTypes.DEPOSIT, money);
        accounts.get(customerID - 1).addTransaction(transaction);
        System.out.println("Account successfully deposited");
    }
    static void withdraw(int customerID) throws InsufficientFundsException {
        double money;
        System.out.println("Enter Money to WITHDRAW: ");
        while(true){
            if(sc.hasNextDouble()){
                money=sc.nextDouble();
                break;
            }
            System.out.println("Invalid money for WITHDRAW");
        }
        if(money>accounts.get(customerID - 1).getBalance()){
            throw new InsufficientFundsException("Insufficient funds to withdraw");
        }
        accounts.get(customerID - 1).setBalance(accounts.get(customerID - 1).getBalance() - money);
        Transaction transaction = new Transaction(LocalDateTime.now(), TransactionTypes.WITHDRAW, money);
        accounts.get(customerID - 1).addTransaction(transaction);
        System.out.println("Account successfully withdrawn");
    }
    static void transfer(int customerFromID){
        int customerToID;
        System.out.println("Enter second Types.Customer ID to accept: ");
        while(true){
            if(sc.hasNextInt()){
                int temp=sc.nextInt();
                if(temp<accounts.size()+1 && temp>0 && temp!=customerFromID){
                    customerToID = temp;
                    break;
                }
            }
            System.out.println("Invalid Types.Customer ID");
        }
        double money;
        System.out.println("Enter Money to transfer: ");
        while(true){
            if(sc.hasNextDouble()){
                double temp=sc.nextDouble();
                if(temp>0 && temp<= accounts.get(customerFromID).getBalance()){
                    money = temp;
                    break;
                }
            }
        }
        accounts.get(customerFromID - 1).setBalance(accounts.get(customerFromID - 1).getBalance() - money);
        accounts.get(customerToID - 1).setBalance(accounts.get(customerToID - 1).getBalance() + money);
        Transaction transaction1 = new Transaction(LocalDateTime.now(), TransactionTypes.TRANSFERFROM, money);
        Transaction transaction2 = new Transaction(LocalDateTime.now(), TransactionTypes.TRANSFERTO, money);
        accounts.get(customerFromID - 1).addTransaction(transaction1);
        accounts.get(customerToID - 1).addTransaction(transaction2);
        System.out.println("Account successfully transferred");
    }
    static void display(int id){
        CustomerAccount account=accounts.get(id-1);
        System.out.println("Types.Customer ID: " + account.getCustomer().getCustomerID());
        System.out.println("Types.Customer name: "+ account.getCustomer().getName());
        System.out.println("Types.Customer address: "+ account.getCustomer().getAddress());
        System.out.println("Types.Customer phone: "+ account.getCustomer().getPhone());
        System.out.println("Types.Customer email: "+ account.getCustomer().getEmail());
        System.out.println("Account number: "+ account.getAccountNumber());
        System.out.println("Account balance: "+ account.getBalance());
        System.out.println("Account type: "+ account.getAccountType().toString());
        System.out.println("Recent Transactions: ");
        try{
            for(int i=account.getTransactionHistory().size()-1; i>=0 && i>=account.getTransactionHistory().size()-3; i--){
                System.out.println(account.getTransactionHistory().get(i).toString());
            }
        }catch (Exception e){
            System.out.println("No transactions found");
        }
    }
    static void displayMonthlyTransactions(int id){
        System.out.println("Types.Transaction history: ");
        CustomerAccount account=accounts.get(id-1);
        for(int i=account.getTransactionHistory().size()-1; account.getTransactionHistory().get(i).getDate().isAfter(LocalDateTime.now().minusMonths(1)); i--){
            System.out.println(account.getTransactionHistory().get(i).toString());
        }
    }
    static void logIn() throws InvalidAccountException {
        String username, password;
        System.out.println("Enter username: ");
        username=sc.next();
        System.out.println("Enter password: ");
        password=sc.next();
        if(searchCustomer(username, password)!=0){
            System.out.println("Login successfully");
            mainMenu(searchCustomer(username, password));
        }
        throw new InvalidAccountException("Invalid username or password");
    }
    static int searchCustomer(String username, String password){
        for(int i=0; i<accounts.size(); i++){
            if(accounts.get(i).getUsername().equals(username) && accounts.get(i).getPassword().equals(password)){
                return i+1;
            }
        }
        return 0;
    }
    static void viewBalance(int id){
        CustomerAccount account=accounts.get(id-1);
        System.out.println("Account balance: "+ account.getBalance());
    }
    static void mainMenu(int id) {
        int ans;
        do {
            System.out.println("""
                    Main Menu:
                    Dear Types.Customer, you can
                    Update Account with (1),
                    Delete Account with (2),
                    Deposit with (3),
                    Withdraw with (4),
                    Transfer with (5),
                    Display account with (6),
                    Display monthly transactions with (7),
                    Export transactions history with (8),
                    View Balance with (9),
                    Return login menu with (10)
                    """);
            System.out.println("Press Enter a number to continue");
            while (true) {
                ans = sc.hasNextInt() ? sc.nextInt() : 0;
                if (ans > 0 && ans <= 10) {
                    break;
                }
                
                System.out.println("Invalid Input");
            }
            switch (ans) {
                case 1:
                    updateCustomerAccount(id);
                    break;
                case 2:
                    deleteCustomerAccount(id);
                    ans=9;
                    break;
                case 3:
                    deposit(id);
                    break;
                case 4:
                    try{
                        withdraw(id);
                    }catch (InsufficientFundsException e){
                        System.out.println("Invalid value for money");
                    }
                    break;
                case 5:
                    transfer(id);
                    break;
                case 6:
                    display(id);
                    break;
                case 7:
                    displayMonthlyTransactions(id);
                    break;
                case 8:
                    transactionsReport(id);
                    break;
                case 9:
                    viewBalance(id);
                    break;
                default:
                    break;
            }
        } while (ans != 10);
        loginMenu();
    }
    static void loginMenu() {
        while (true) {
            System.out.println("""
    Welcome to our Banking System!
    You can Log In with (1),
    Create Account with (2),
    If you are Admin Log In with (3),
    Exit system with (4)
    """);
            int ans;
            while(true){
                System.out.println("Enter a number to continue");
                ans=sc.hasNextInt()?sc.nextInt():0;
                if(ans==1 || ans==2 || ans==3 || ans==4){
                    break;
                }
                System.out.println("Invalid Input");
            }
            switch(ans){
                case 1:
                    try{
                        logIn();
                    }catch (InvalidAccountException e){
                        System.out.println("Invalid username or password");
                    }
                    break;
                case 2:
                    addCustomerAccount();
                    break;
                case 3:
                    admin();
                    break;
                case 4:
                    System.exit(0);
                default:
                    break;
            }
        }

    }
    static void admin() {
        while (true) {
            System.out.println("Please enter a password");
            String password = sc.next();
            if (password.equals(adminPassword)) {
                System.out.println("Login successfully");
                break;
            }
            System.out.println("Invalid password");
        }
        int ans;
        do {
            System.out.println("""
                    Main Menu:
                    Search accounts with  Types.Customer ID (1),
                    Search accounts with  Account Number (2),
                    Delete Types.Customer Account with (3),
                    Return login menu with (4)
                    """);
            System.out.println("Press Enter a number to continue");
            ans = sc.hasNextInt() ? sc.nextInt() : 0;
            switch (ans) {
                case 1:
                    searchAccountWithID();
                    break;
                case 2:
                    searchAccountWithAccNum();
                    break;
                case 3:

                    while (true) {
                        System.out.println("Enter Types.Customer ID to delete : ");
                        int id = sc.nextInt();
                        if (id > 0 && id <= accounts.size()) {
                            deleteCustomerAccount(id);
                            break;
                        }
                        System.out.println("Invalid Types.Customer ID");
                    }
                default:
                    break;
            }
        } while (ans != 4);
        loginMenu();
    }
    static void searchAccountWithAccNum() {
        System.out.println("Please enter an account number");
        int accNum;
        while (true) {
            accNum = sc.nextInt();
            if (accNum > 0) {
                break;
            }
            System.out.println("Invalid account number");
        }
        boolean found = false;
        for(int i=0; i<accounts.size()+1; i++){
            if(accounts.get(i).getAccountNumber()==accNum){
                System.out.println("Account found");
                display(i+1);
                found=true;
                break;
            }
        }
        if(!found){
            System.out.println("Account not found");
        }
    }
    static void searchAccountWithID() {
        int id;
        while (true) {
            id = sc.nextInt();
            if (id > 0 ) {
                if(id<=accounts.size()+1){
                    System.out.println("Account found");
                    display(id);
                    break;
                }
                else{
                    System.out.println("Types.Customer not found");
                }
            }
            System.out.println("Invalid Types.Customer ID");
        }
    }
    static void transactionsReport(int id){
        String filePath = "C:\\Users\\Avtoban\\Desktop\\Learning\\coding\\Java\\online_banking_system\\src\\TransactionReports\\"+accounts.get(id-1).getCustomer().getName()+".txt";
        StringBuilder content= new StringBuilder();
        for(int i=0; i<accounts.get(id-1).getTransactionHistory().size(); i++){
            content.append((i + 1)).append(") ").append(accounts.get(id - 1).getTransactionHistory().get(i).toString());
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write(String.valueOf(content));
            System.out.println("File created successfully with the content.");
        } catch (IOException e) {
            System.err.println("An error occurred while creating or writing to the file.");
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        loginMenu();
    }
}