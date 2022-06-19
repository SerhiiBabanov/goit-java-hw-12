package fizzbuzz;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.IntStream;

public class Application {

    public static volatile  CyclicBarrier BARRIER = new CyclicBarrier(4);
    public static volatile  CyclicBarrier BARRIER1 = new CyclicBarrier(5);
    public static volatile  CyclicBarrier BARRIER2 = new CyclicBarrier(5);

    public static volatile int currentNumber = 0;
    public static volatile byte currentNumberState = 0;

    public static volatile boolean isWorkDone = false;

    public static void main(String[] args) {
        new Thread(new A()).start();
        new Thread(new B()).start();
        new Thread(new C()).start();
        new Thread(new D()).start();
        IntStream.range(1, 16).forEach(Application::processNumber);


    }

    public static void processNumber(int n) {
        currentNumber = n;
        currentNumberState = 0;
        try {
            BARRIER2.await();

            //isNewNumber.signalAll();

            BARRIER1.await();

        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            //lock.unlock();
        }

    }


}
