import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadTasks {
    public static void main(String[] args) {
        Long timeStart = System.currentTimeMillis();
        Lock lock = new ReentrantLock();
        Condition oneSecondCondition = lock.newCondition();
        ThreadOneSecondsMessage threadOneSecondsMessage = new ThreadOneSecondsMessage(timeStart, lock, oneSecondCondition);
        ThreadFiveSecondsMessage threadFiveSecondsMessage = new ThreadFiveSecondsMessage(lock, oneSecondCondition);
        new Thread(threadOneSecondsMessage).start();
        new Thread(threadFiveSecondsMessage).start();
    }
}
