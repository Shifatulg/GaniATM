public class Account {
    private int balance;
    private String type;
    public Account(String type) {
        this.type = type;
        balance = 0;
    }

    public String getType() {
        return type;
    }

    public void withdraw(int amount) {
        balance -= amount;
    }

    public void deposit(int amount) {
        balance += amount;
    }
}
