package su.cus;

public class Account {
    private final String id;
    private int money;

    public Account(String id, int money) {
        this.id = id;
        this.money = money;
    }

    public String getId() {
        return id;
    }

    public synchronized int getMoney() {
        return money;
    }

    public synchronized void setMoney(int money) {
        this.money = money;
    }
}
