package su.cus;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Random;

public class Transfer implements Runnable {
    private static final Logger logger = LogManager.getLogger(Transfer.class);
    private final Account from;
    private final Account to;
    private static final int MAX_AMOUNT = 500;
    private static final Random random = new Random();

    public Transfer(Account from, Account to) {
        this.from = from;
        this.to = to;
    }

    @Override
    public void run() {
        while (Main.transactionCount.get() < Main.MAX_TRANSACTIONS) {
            int amount = random.nextInt(MAX_AMOUNT);
            synchronized (from) {
                synchronized (to) {
                    if (from.getMoney() >= amount) {
                        from.setMoney(from.getMoney() - amount);
                        to.setMoney(to.getMoney() + amount);
                        logger.info("Перевод выполнен: " + amount + " от " + from.getId() + " к " + to.getId());
                        if (Main.transactionCount.incrementAndGet() >= Main.MAX_TRANSACTIONS) {
                            logger.info("Достигнуто максимальное количество транзакций.");
                            break;
                        }
                    }
                }
            }
            try {
                Thread.sleep(random.nextInt(1000) + 1000);
            } catch (InterruptedException e) {
                logger.error("Ошибка в потоке: ", e);
                Thread.currentThread().interrupt();
            }
        }
    }

}

