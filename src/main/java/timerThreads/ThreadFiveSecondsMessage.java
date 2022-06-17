package timerThreads;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class ThreadFiveSecondsMessage implements Runnable {
    private final Lock lock;
    private final Condition oneSecondCondition;

    public ThreadFiveSecondsMessage(Lock lock, Condition oneSecondCondition) {
        this.lock = lock;
        this.oneSecondCondition = oneSecondCondition;
    }

    @Override
    public void run() {
        while (true) {
            for (int i = 0; i < 5; i++) {
                try {
                    lock.lock();
                    oneSecondCondition.await();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                } finally {
                    lock.unlock();
                }
            }
            System.out.println(String.format("---------- Thread %s: 5 seconds have passed ----------",
                    Thread.currentThread().getName()));
        }
    }
}
