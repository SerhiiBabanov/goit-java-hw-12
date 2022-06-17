package timerThreads;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadTasks {
    public static void main(String[] args) {
        Long timeStart = System.currentTimeMillis();
        Lock lock = new ReentrantLock();
        Condition oneSecondCondition = lock.newCondition();

        new Thread(new ThreadOneSecondsMessage(timeStart, lock, oneSecondCondition)).start();
        new Thread(new ThreadFiveSecondsMessage(lock, oneSecondCondition)).start();
    }
}
