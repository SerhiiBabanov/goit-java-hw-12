package fizzbuzz;

import static fizzbuzz.Application.*;

public class D implements Runnable {
    @Override
    public void run() {
        while (!isWorkDone){
            try {
                BARRIER2.await();

                BARRIER.await();

                if (currentNumberState == 0){
                    number(currentNumber);
                }
                BARRIER1.await();
            } catch (Exception e) {
                throw new RuntimeException(e);
            } finally {
               // lock.unlock();
            }
        }
    }

    public static void number(int number){
        System.out.print(number + " ");

    }
}
