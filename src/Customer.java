import java.util.Scanner;
public class Customer {
    private static final Scanner scan = new Scanner(System.in);
    private int pin;
    private String name;
    private Account checkings;
    private Account savings;

    public Customer() {
        System.out.println("What is your name?");
        name = scan.nextLine();
        System.out.println("What is your pin?");
        pin = scan.nextInt();
        checkings = new Account(true);
        savings = new Account(false);
    }

    public String getName() {
        return name;
    }

    public void setPin(int newPin) {
        pin = newPin;
    }
    public boolean enterPin(int attempt) {
        if (pin == attempt) {
            return true;
        } return false;
    }
}