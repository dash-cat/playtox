package su.cus;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {
    public static final int MAX_TRANSACTIONS = 30;
    static final AtomicInteger transactionCount = new AtomicInteger(0);

    public static void main(String[] args) {
        Account acc1 = new Account("ID1", 10000);
        Account acc2 = new Account("ID2", 10000);
        Account acc3 = new Account("ID3", 10000);
        Account acc4 = new Account("ID4", 10000);

        Thread t1 = new Thread(new Transfer(acc1, acc2));
        Thread t2 = new Thread(new Transfer(acc3, acc4));

        t1.start();
        t2.start();
    }
}