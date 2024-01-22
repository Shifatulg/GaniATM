public class TransactionHistory {
  // static variable with a default value so it can act as a receipt
  private static String history = "\n---------------------\n";
  
  public TransactionHistory() { }

  // method to add an action to the history
  public static void addLine(String s, String ID) {
    history += ID + ": " + s + "\n";
  }

  // getter method
  public static String getHistory() {
    return history;
  }
}