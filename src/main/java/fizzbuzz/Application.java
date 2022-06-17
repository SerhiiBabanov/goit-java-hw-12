package fizzbuzz;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.IntStream;

public class Application {
    public static Lock lock = new ReentrantLock();
    public static Condition isNewNumber = lock.newCondition();
    public static Condition isNewNumberProcessed = lock.newCondition();
    public static Condition isNewNumberNotDividedBy3Or5 = lock.newCondition();

    public static int currentNumber = 0;
    public static boolean isWorkDone = false;

    public static void main(String[] args) {
        new Thread(new A()).start();
        new Thread(new B()).start();
        new Thread(new C()).start();
        new Thread(new D()).start();
        IntStream.range(1, 16).forEach(Application::processNumber);

        isWorkDone = true;
        isNewNumber.signalAll();
    }

    public static void processNumber(int n) {
        currentNumber = n;
        try {
            lock.lock();
            isNewNumber.signalAll();
            isNewNumberProcessed.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            lock.unlock();
        }

    }
    public static boolean fizz(int number){
        return number % 3 == 0;
    }
    public static boolean buzz(int number){
        return number % 5 == 0;
    }
    public static boolean fizzbuzz(int number){
        return number % 3 == 0 && number % 5 == 0;
    }
    public static void number(int number){
        System.out.println(number + " ");
        isNewNumberProcessed.signalAll();
    }
}
