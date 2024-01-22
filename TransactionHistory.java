public class TransactionHistory {
  private static String history = "\n---------------------\n";
  public TransactionHistory() { }

  public static void addLine(String s, String ID) {
    history += ID + ": " + s + "\n";
  }

  public static String getHistory() {
    return history;
  }
}