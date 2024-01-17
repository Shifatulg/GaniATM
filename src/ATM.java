import java.util.Scanner;
public class ATM {
    private static final Scanner s = new Scanner(System.in);
    Customer man = new Customer();
    public ATM() { }

    public void start() {

    }

    private void introduce() {
        System.out.println("Hello" + man.getName() + "welcome to the ATM");
    }

    private void mainMenu() {
        System.out.println("Which account would you like to use, (1) checkings, (2) savings" );
        int choice = s.nextInt();
        System.out.println("---------------------");
        System.out.println("1. Withdraw Money");
        System.out.println("2. Deposit money");
        System.out.println("3. Transfer money between accounts");
        System.out.println("4. Get account balances");
        System.out.println("5. Get transaction history");
        System.out.println("6. Change PIN");
        System.out.println("7. Exit");
        System.out.println("---------------------");
    }
}
