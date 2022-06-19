package fizzbuzz;

import static fizzbuzz.Application.*;

public class A implements Runnable {
    @Override
    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                //wait to new number to check
                BARRIER_READY_TO_CHECK_NUMBER.await();

                //check new number and set bite number 0 to 1 if n%3==0
                fizz(currentNumber);

                //wait to finish checking number by all four threads
                BARRIER_READY_TO_PRINT_NUMBER.await();

                //add 'fizz' to result if bytes of currentNumberState == 0001;
                if (currentNumberState == 1) {
                    result.append(" ").append("fizz");
                }

                //wait to all threads finish work with this number
                BARRIER_READY_TO_NEXT_NUMBER.await();
            }
        } catch (Exception e) {

        }
    }

    //check number and set bite number 0 to 1 if n%3==0
    private void fizz(int number) {
        if (number % 3 == 0) currentNumberState |= (1 << 0);
    }


}
