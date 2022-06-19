package fizzbuzz;

import java.util.concurrent.BrokenBarrierException;

import static fizzbuzz.Application.*;

public class C implements Runnable {
    @Override
    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                //wait to new number to check
                BARRIER_READY_TO_CHECK_NUMBER.await();

                //check new number and set bit
                fizzbuzz(currentNumber);

                //wait to finish checking number by all four threads
                BARRIER_READY_TO_PRINT_NUMBER.await();

                //print number if bytes of currentNumberState = 0011;
                if (currentNumberState == 3) {
                    result.append(" ").append("fizzbuzz");
                }

                //wait to all threads finish work with this number
                BARRIER_READY_TO_NEXT_NUMBER.await();
            }
        } catch (Exception e) {

        }
    }
    //check number and set bites numbers 0 and 1 to 1 if n%3==0
    private void fizzbuzz(int number) {
        if (number % 3 == 0 && number % 5 == 0) {
            currentNumberState |= (1 << 0);
            currentNumberState |= (1 << 1);
        }
    }
}
