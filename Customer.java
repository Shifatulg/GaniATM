import java.util.Scanner;

public class Customer {
    private static final Scanner scan = new Scanner(System.in);
    private int pin;
    private String name;
    // currentAcc is used for ease in all the methods. When objects are assigned to each other they are the same in memory so we can apply all the methods to currentAcc and it will edit the account it is currently on
    private Account currentAcc;
    private Account checkings;
    private Account savings;
  
    public Customer() {
        // pin and name based on user input
        System.out.print("What is your name? ");
        name = scan.nextLine();
        System.out.print("What is your pin? ");
        pin = scan.nextInt();
        scan.nextLine();
        // checkings and savings accounts with a parameter which is the version of the account
        checkings = new Account("Checkings");
        savings = new Account("Savings");
        // currentAcc is defaulted to checkings
        currentAcc = checkings;
    }

    public String getName() {
        return name;
    }

    public Account getAcc() {
        return currentAcc;
    }

    public void setPin(int newPin) {
        pin = newPin;
    }

    // switches currentAcc to the other version of the account
    public void switchAccounts() {
        if (currentAcc == savings) {
            currentAcc = checkings;
        } else {
            currentAcc = savings;
        }
    }
  
    public boolean transfer(double amount, int direction, String ID) {
      // ID references the transaction ID
      if (direction == 1) {
        // direction references if you want to import or export money from the account
        // all the secondary conditions on the if statements are in place to make sure the amount withdrawn is not more than the balance of the account
        if (currentAcc == checkings && checkings.getBalance() > amount) {
            checkings.withdraw(amount);
            savings.deposit(amount);
            System.out.println("Transfer of $" + amount + " from checkings to savings successful.");
          TransactionHistory.addLine("Transfer of $" + amount + " from checkings to savings successful.", ID);
          // ID is used along with the receipt message to add to the transaction history with the static method in TransactionHistory
        } else if (currentAcc == savings && savings.getBalance() > amount) {
            savings.withdraw(amount);
            checkings.deposit(amount);
            System.out.println("Transfer of $" + amount + " from savings to checkings successful.");
            TransactionHistory.addLine("Transfer of $" + amount + " from savings to checkings successful.", ID);
        } else { return false; } // these return false statements are in place if the amount withdrawn is greater than the balance of the account
      } else if (direction == 2) {
        if (currentAcc == checkings && savings.getBalance() > amount) {
            savings.withdraw(amount);
            checkings.deposit(amount);
            System.out.println("Transfer of $" + amount + " from savings to checkings successful.");
            TransactionHistory.addLine("Transfer of $" + amount + " from savings to checkings successful.", ID);
        } else if (currentAcc == savings && checkings.getBalance() > amount) {
            checkings.withdraw(amount);
            savings.deposit(amount);
            System.out.println("Transfer of $" + amount + " from checkings to savings successful.");
            TransactionHistory.addLine("Transfer of $" + amount + " from checkings to savings successful.", ID);
        } else { return false; }
      }
      return true;
      // returns true if the transfer was successful
      // the boolean aspect of this method is in place to make sure the transfer was successful and not to increment the ID of the transaction if the transaction was faulty along with sending an error
    }

    // checks if entered pin is valid
    public boolean enterPin(int attempt) {
        if (pin == attempt) {
            return true;
        } return false;
    }
}