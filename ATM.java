import java.util.Scanner;

public class ATM {
    private static final Scanner s = new Scanner(System.in);
    Customer man = new Customer();
    private int accID;
    private int secID;

    public ATM() {
      accID = 1;
      secID = 1;
    }

    public void start() {
        introduce();
        mainMenu();
    }

    private void introduce() {
        System.out.println("Hello " + man.getName() + " welcome to the ATM");
    }

    private void mainMenu() {    
        if (!verify()) {
          System.out.println("Incorrect pin, please wait");
          try {
            Thread.sleep(2000);
          } catch (InterruptedException err) { }
          System.out.print("\033[H\033[2J");  
          System.out.flush();
          mainMenu();
        } else {
          System.out.println("Correct pin entered; proceed");
        }
        System.out.println("You are currently in your " + man.getAcc().getType() + " account");
        System.out.print("Would you like to switch accounts? ");
        String choice = s.nextLine();
        if (choice.toLowerCase().equals("yes") || choice.toLowerCase().equals("y")) {
            man.switchAccounts();
            System.out.println("Switched to " + man.getAcc().getType() + " account");
        }
        System.out.println("\n---------------------");
        System.out.println("1. Withdraw Money");
        System.out.println("2. Deposit money");
        System.out.println("3. Transfer money between accounts");
        System.out.println("4. Get account balances");
        System.out.println("5. Get transaction history");
        System.out.println("6. Change PIN");
        System.out.println("7. Exit");
        System.out.println("---------------------\n");
        System.out.print("What action do you want to do: ");
        while (true) {
          int input = s.nextInt();
          s.nextLine();
          if (input > 0 && input < 8) {
            runAction(input);
          } else {
            System.out.println("Invalid option, please enter a valid option: ");;
          }
        }
    }

  private void runAction(int command) {
    if (command == 1) {
      System.out.print("The ATM can only dispense $20 and $5 bills, how much would you like to withdraw: ");
      double amount = s.nextInt();
      s.nextLine();
      if (amount % 5 == 0) {
        System.out.println("Withdrawal of $" + amount + ": " + man.getAcc().getType());
        man.getAcc().withdraw(amount);
        System.out.println("This is action A" + String.format("%04d", accID));
        TransactionHistory.addLine(("Withdrawal of $" + amount + ": " + man.getAcc().getType()), "A" + String.format("%04d", accID));
        accID++;
      } else {
        System.out.print("INVALID AMOUNT ENTERED; RETURNING TO MAIN MENU");
      }
    } else if (command == 2) {
      System.out.print("How much would you like to deposit: ");
      double amount = s.nextDouble();
      s.nextLine();
      man.getAcc().deposit(amount);
      System.out.println("Deposit of $" + amount + ": " +  man.getAcc().getType());
      System.out.println("This is action A" + String.format("%04d", accID));
      TransactionHistory.addLine(("Deposit of $" + amount + ": " + man.getAcc().getType()), "A" + String.format("%04d", accID));
      accID++;
    } else if (command == 3) {
      System.out.println("You are currently in your " + man.getAcc().getType() + " account");
      System.out.println("Would you like to export (1) money or import (2) money from your " + man.getAcc().getType());
      int transfer = s.nextInt();
      s.nextLine();
      if (transfer == 1 || transfer == 2) {
        System.out.print("How much would you like to transfer: ");
        if (!man.transfer(s.nextDouble(), transfer, "A" + String.format("%04d", accID))) {
          System.out.println("INVALID AMOUNT ENTERED; RETURNING TO MAIN MENU");
          mainMenu();
        }
        System.out.println("This is action A" + String.format("%04d", accID));
        accID++;
        s.nextLine();
      } else {
        System.out.println("INVALID OPTION ENTERED; RETURNING TO MAIN MENU");
      }
    } else if (command == 4) {
      System.out.println("Your current balance is: $" + man.getAcc().getBalance());
      System.out.println("This is action S" + String.format("%04d", secID));
      TransactionHistory.addLine("Balance checked: ", "S" + String.format("%04d", secID));
      secID++;
    } else if (command == 5) {
      TransactionHistory.addLine("Transaction history checked ", "S" + String.format("%04d", secID));
      System.out.print(TransactionHistory.getHistory());
      System.out.println("---------------------\n");
      System.out.println("This is action S" + String.format("%04d", secID));
      secID++;    
      System.out.print("Press enter to continue ");
      s.nextLine();
    } else if (command == 6) {
      System.out.print("What will be your new pin? ");
      man.setPin(s.nextInt());
      s.nextLine();
      System.out.println("Pin succesfully changed");
      System.out.println("This is action S" + String.format("%04d", secID));
      TransactionHistory.addLine("Pin changed: ", "S" + String.format("%04d", secID));
      secID++;
    } else if (command == 7) {
      System.out.println("Thank you for using the ATM");
      System.exit(0);
    }
    try {
      Thread.sleep(2000);
    } catch (InterruptedException err) { }
    System.out.print("\033[H\033[2J");  
    System.out.flush();
    System.out.print("Would you like to do anything else? ");
    String response = s.nextLine();
    if (response.toLowerCase().equals("n") || response.toLowerCase().equals("no")) {
      System.out.println("Thank you for using the ATM");
      System.exit(0);
    }
    mainMenu();
  }
  
  private boolean verify() {
      System.out.print("Enter pin: ");
      int pin = s.nextInt();
      s.nextLine();
      return man.enterPin(pin);
  }
}