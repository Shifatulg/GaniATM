import java.util.Scanner;
public class ATM {
    private static final Scanner s = new Scanner(System.in);
    Customer man = new Customer();
    public ATM() { }

    public void start() {
        introduce();
        mainMenu();
    }

    private void introduce() {
        System.out.println("Hello " + man.getName() + " welcome to the ATM");
    }

    private int mainMenu() {
        System.out.println("You are currently in your " + man.getAcc().getType() + " account");
        System.out.print("Would you like to switch accounts? ");
        String choice = s.nextLine();
        if (choice.toLowerCase().equals("yes") || choice.toLowerCase().equals("y")) {
            man.switchAccounts();
            System.out.println("Switched to " + man.getAcc().getType() + "account");
        }
        System.out.println("---------------------");
        System.out.println("1. Withdraw Money");
        System.out.println("2. Deposit money");
        System.out.println("3. Transfer money between accounts");
        System.out.println("4. Get account balances");
        System.out.println("5. Get transaction history");
        System.out.println("6. Change PIN");
        System.out.println("7. Exit");
        System.out.println("---------------------");
        System.out.println("What action do you want to do: ");
        int input = s.nextInt();
        s.nextLine();
        return input;
    }

    private boolean verify() {
        System.out.print("Enter pin: ");
        int pin = s.nextInt();
        return man.enterPin(pin);
    }
}
