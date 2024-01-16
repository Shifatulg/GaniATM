import java.util.Scanner;
public class Customer {
    private static Scanner scan = new Scanner(System.in);
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
}
