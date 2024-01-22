public class Account {
    private double balance;
    private String type;
    public Account(String type) {
        this.type = type;
        balance = 0;
    }

    public String getType() {
        return type;
    }

    public double getBalance() {
      return balance;
    }
  
    public void withdraw(double amount) {
      if (amount > balance) {
        System.out.println("INSUFFICIENT FUNDS; RETURNING TO MAIN MENU");
      } else {
        balance -= amount;
      }
      balance = Math.round(balance * 100) / 100.0;
    }

    public void deposit(double amount) {
      balance += amount;
      balance = Math.round(balance * 100) / 100.0;
    }
}
