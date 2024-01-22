import java.util.Scanner;

public class ATM {
    private static final Scanner s = new Scanner(System.in);
    Customer man = new Customer();
    private int accID;
    private int secID;

    public ATM() {
      // starts at one because the ID number starts at one
      accID = 1;
      secID = 1;
    }
    // method ran in the main method
    public void start() {
        introduce();
        // become a continuous loop without a while loop because while mainMenu() is being ran, it is being called on itself again
        mainMenu();
    }

    private void introduce() {
        System.out.println("Hello " + man.getName() + " welcome to the ATM");
    }

    private void mainMenu() {  
        // if pin is incorrect, it will buffer and make you re-enter. It does this by calling Thread.sleep for 2 seconds and then calling mainMenu() to return to the beginning of the statement
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
        // I changed this from the outline because it is easier to visualize what account you are currently in and being able to switch and the beginning of every usage
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
        // this is used to get a valid user input
        // when a valid user input is entered, another helper method called runAction is ran
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
  // runAction is a helper method that is ran when a valid user input is entered. 
  private void runAction(int command) {
    if (command == 1) {
      System.out.print("The ATM can only dispense $20 and $5 bills, how much would you like to withdraw: ");
      double amount = s.nextInt();
      s.nextLine();
      /* 
        if condition is not met, no withdrawal will be made and transaction history will not be updated
        */
      if (amount % 5 == 0) {
        // receipt/confirmation message
        System.out.println("Withdrawal of $" + amount + ": " + man.getAcc().getType());
        // edits the object of currentAcc by calling the getAcc method on man (customer object) and then calling the withdraw method on the currentAcc object
        man.getAcc().withdraw(amount);
        // For transaction history, the "%04d", accID line is used to fill 0 before the number otherwise 0001 will become 1
        System.out.println("This is action A" + String.format("%04d", accID));
        // addLine is a static method in transactionHistory and it updates a static variable called history. The first parameter is a string that summarizes the action that was committed while the second parameter is the ID of the action
        TransactionHistory.addLine(("Withdrawal of $" + amount + ": " + man.getAcc().getType()), "A" + String.format("%04d", accID));
        // increment ID for future placements
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
      // Pin is edited via a method in the customer Class
      System.out.print("What will be your new pin? ");
      man.setPin(s.nextInt());
      s.nextLine();
      System.out.println("Pin succesfully changed");
      System.out.println("This is action S" + String.format("%04d", secID));
      TransactionHistory.addLine("Pin changed: ", "S" + String.format("%04d", secID));
      secID++;
    } else if (command == 7) {
      System.out.println("Thank you for using the ATM");
      // terminates program
      System.exit(0);
    }
    // sleep then clear
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
    // mainMenu() is ran here to continue the loop unless the user exits the program
    mainMenu();
  }
  
  // verify is a helper method to check if the inputted pin is correct
  private boolean verify() {
      System.out.print("Enter pin: ");
      int pin = s.nextInt();
      s.nextLine();
      return man.enterPin(pin);
  }
}