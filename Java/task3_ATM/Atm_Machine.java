
/*Create a class to represent 1. the ATM machine.
2. Design the user interface for the ATM, including options such as withdrawing, depositing, and
checking the balance.
3. Implement methods for each option, such as withdraw(amount), deposit(amount), and
checkBalance().
4. Create a class to represent the user's bank account, which stores the account balance.
5. Connect the ATM class with the user's bank account class to access and modify the account
balance.
6. Validate user input to ensure it is within acceptable limits (e.g., sufficient balance for withdrawals).
7. Display appropriate messages to the user based on their chosen options and the success or failure
of their transactions. */

package Java.task3_ATM;

import java.util.Date;
import java.util.Scanner;

class BankAccount {
    private int balance = 50000;

    public int getBalance() {
        return balance;
    }

    public void deposit(int amount) {
        balance += amount;
    }

    public void withdraw(int amount) {
        if (balance >= amount) {
            balance -= amount;
            System.out.println("Please collect your money");
        } else {
            System.out.println("Insufficient Balance");
        }
    }
}

class ATM {
    private BankAccount userAccount;
    private Scanner scanner;

    public ATM(BankAccount account) {
        this.userAccount = account;
        this.scanner = new Scanner(System.in);
    }

    public Scanner getScanner() {
        return scanner;
    }

    public void displayMenu() {
        System.out.println("Automated Teller Machine");
        System.out.println("==================================================");
        System.out.println("Choose 1 for Withdraw");
        System.out.println("Choose 2 for Deposit");
        System.out.println("Choose 3 for Check Balance");
        System.out.println("=================================================");
        System.out.print("Choose the operation you want to perform: ");
    }

    public void performTransaction(int choice) {
        switch (choice) {
            case 1:
                System.out.print("Enter money to be withdrawn: ");
                int withdrawAmount = scanner.nextInt();
                userAccount.withdraw(withdrawAmount);
                break;

            case 2:
                System.out.print("Enter money to be deposited: ");
                int depositAmount = scanner.nextInt();
                userAccount.deposit(depositAmount);
                System.out.println("Your money has been successfully deposited");
                break;

            case 3:
                System.out.println("Balance: " + userAccount.getBalance());
                break;

            default:
                System.out.println("Invalid choice");
        }
    }

    public void printReceipt() {
        System.out.println("==================================================");
        System.out.println("IF YOU WANT RECEIPT");
        System.out.println("==================================================");
        System.out.println("CHOOSE 1. FOR YES");
        System.out.println("CHOOSE 2. FOR NO");
        System.out.println("==================================================");
        System.out.print("Enter your choice: ");
        int receiptChoice = scanner.nextInt();
        System.out.println("==================================================");
        if (receiptChoice == 1) {
            printTransactionReceipt();
        }

        System.out.println("Thank you!\nGoodbye");
        System.out.println("=================================================");
        System.out.println("\n");
    }

    private void printTransactionReceipt() {
        System.out.println("==================================================");
        Date now = new Date();
        System.out.println("DATE & TIME: " + now);
        System.out.println("Current Balance: " + userAccount.getBalance());
        System.out.println("==================================================");
    }
}

public class Atm_Machine {
    public static void main(String args[]) {
        BankAccount userAccount = new BankAccount();
        ATM atm = new ATM(userAccount);

        atm.displayMenu();
        int choice = atm.getScanner().nextInt();
        atm.performTransaction(choice);
        atm.printReceipt();
    }
}
