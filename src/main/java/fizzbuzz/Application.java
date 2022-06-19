package fizzbuzz;

import java.util.concurrent.CyclicBarrier;
import java.util.stream.IntStream;

public class Application {
    public static final CyclicBarrier BARRIER_READY_TO_CHECK_NUMBER = new CyclicBarrier(5);
    public static final CyclicBarrier BARRIER_READY_TO_PRINT_NUMBER = new CyclicBarrier(5);
    public static final CyclicBarrier BARRIER_READY_TO_NEXT_NUMBER = new CyclicBarrier(5);

    public static volatile int currentNumber = 0;
    public static volatile byte currentNumberState = 0;
    public static volatile StringBuilder result = new StringBuilder("");


    public static void main(String[] args) {
        //create threads
        Thread threadA = new Thread(new A());
        Thread threadB = new Thread(new B());
        Thread threadC = new Thread(new C());
        Thread threadD = new Thread(new D());
        //start threads
        threadA.start();
        threadB.start();
        threadC.start();
        threadD.start();
        //process number from 1 to 15 inclusive
        IntStream.range(1, 16).forEach(Application::processNumber);

        //stop threads
        threadA.interrupt();
        threadB.interrupt();
        threadC.interrupt();
        threadD.interrupt();

        //replace spaces to ", " and print result
        System.out.println(result.deleteCharAt(0).toString().replace(" ", ", "));
    }

    public static void processNumber(int n) {
        currentNumber = n;
        currentNumberState = 0;
        try {
            //wait to all threads for start check number
            BARRIER_READY_TO_CHECK_NUMBER.await();

            //wait to all threads for print result
            BARRIER_READY_TO_PRINT_NUMBER.await();

            //wait to all threads ready to next number
            BARRIER_READY_TO_NEXT_NUMBER.await();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }


}
