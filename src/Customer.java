import java.util.Scanner;
public class Customer {
    private static final Scanner scan = new Scanner(System.in);
    private int pin;
    private String name;
    private Account currentAcc;
    private Account checkings;
    private Account savings;

    public Customer() {
        System.out.println("What is your name?");
        name = scan.nextLine();
        System.out.println("What is your pin?");
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

    public boolean enterPin(int attempt) {
        if (pin == attempt) {
            return true;
        } return false;
    }
}