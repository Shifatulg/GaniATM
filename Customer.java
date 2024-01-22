import java.util.Scanner;

public class Customer {
    private static final Scanner scan = new Scanner(System.in);
    private int pin;
    private String name;
    private Account currentAcc;
    private Account checkings;
    private Account savings;
  
    public Customer() {
        System.out.print("What is your name? ");
        name = scan.nextLine();
        System.out.print("What is your pin? ");
        pin = scan.nextInt();
        checkings = new Account("Checkings");
        savings = new Account("Savings");
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

    public void switchAccounts() {
        if (currentAcc == savings) {
            currentAcc = checkings;
        } else {
            currentAcc = savings;
        }
    }
  
    public boolean transfer(double amount, int direction, String ID) {
      if (direction == 1) {
        if (currentAcc == checkings && checkings.getBalance() > amount) {
            checkings.withdraw(amount);
            savings.deposit(amount);
            System.out.println("Transfer of $" + amount + " from checkings to savings successful.");
          TransactionHistory.addLine("Transfer of $" + amount + " from checkings to savings successful.", ID);
        } else if (currentAcc == savings && savings.getBalance() > amount) {
            savings.withdraw(amount);
            checkings.deposit(amount);
            System.out.println("Transfer of $" + amount + " from savings to checkings successful.");
            TransactionHistory.addLine("Transfer of $" + amount + " from savings to checkings successful.", ID);
        } else { return false; }
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
    }
  
    public boolean enterPin(int attempt) {
        if (pin == attempt) {
            return true;
        } return false;
    }
}