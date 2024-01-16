public class Account {
    private int balance;
    private boolean savings;
    public Account(boolean checkings) {
        if (checkings) {
            savings = false;
        } else {
            savings = true;
        }
        balance = 0;
    }

    public void withdraw(int amount) {
        balance -= amount;
    }

    public void deposit(int amount) {
        balance += amount;
    }
}
