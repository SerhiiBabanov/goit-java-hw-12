package fizzbuzz;

import java.util.concurrent.BrokenBarrierException;

import static fizzbuzz.Application.*;

public class B implements Runnable {
    @Override
    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                //wait to new number to check
                BARRIER_READY_TO_CHECK_NUMBER.await();

                //check new number and set bit
                buzz(currentNumber);

                //wait to finish checking number by all four threads
                BARRIER_READY_TO_PRINT_NUMBER.await();

                //print number if bytes of currentNumberState = 0010;
                if (currentNumberState == 2) {
                    result.append(" ").append("buzz");
                }

                //wait to all threads finish work with this number
                BARRIER_READY_TO_NEXT_NUMBER.await();
            }
        } catch (Exception e) {

        }
    }

    //check number and set bite number 1 to 1 if n%5==0
    private void buzz(int number) {
        if (number % 5 == 0) currentNumberState |= (1 << 1);
    }
}
