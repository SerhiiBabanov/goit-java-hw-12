package fizzbuzz;

import java.util.concurrent.BrokenBarrierException;

import static fizzbuzz.Application.*;

public class D implements Runnable {
    @Override
    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                //wait to new number to check
                BARRIER_READY_TO_CHECK_NUMBER.await();

                //wait to finish checking number by all four threads
                BARRIER_READY_TO_PRINT_NUMBER.await();

                //print number if bytes of currentNumberState = 0000 -- no any threads don`t change bites
                if (currentNumberState == 0) {
                    number(currentNumber);
                }

                //wait to all threads finish work with this number
                BARRIER_READY_TO_NEXT_NUMBER.await();
            }
        } catch (Exception e) {

        }
    }

    public static void number(int number) {
        result.append(" ").append(number);
    }
}
