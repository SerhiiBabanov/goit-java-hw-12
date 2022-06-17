package timerThreads;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

import static java.lang.Thread.sleep;

public class ThreadOneSecondsMessage implements Runnable {
    private final Long startTime;
    private final Lock lock;
    private final Condition oneSecondCondition;
    public ThreadOneSecondsMessage(Long startTime, Lock lock, Condition oneSecondCondition) {
        this.startTime = startTime;
        this.lock = lock;
        this.oneSecondCondition = oneSecondCondition;
    }

    @Override
    public void run() {
        while (true) {
            try {
                sleep(1000);
                lock.lock();
                System.out.println(String.format("Thread %s %d: seconds have passed since the program was launched",
                        Thread.currentThread().getName(),(System.currentTimeMillis() -startTime) / 1000));
                oneSecondCondition.signalAll();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                lock.unlock();
            }
        }
    }
}
