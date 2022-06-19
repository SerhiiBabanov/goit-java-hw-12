package fizzbuzz;

import static fizzbuzz.Application.*;

public class C implements Runnable {
    @Override
    public void run() {
        while (!isWorkDone){
            try {
                BARRIER2.await();
                if (fizzbuzz(currentNumber)){

                    currentNumberState |= (1 << 0);
                    currentNumberState |= (1 << 1);

                }
                BARRIER.await();
                if (currentNumberState == 3){
                    System.out.print("fizzbuzz ");
                }
                BARRIER1.await();
            } catch (Exception e) {
                throw new RuntimeException(e);
            } finally {
                //lock.unlock();
            }
        }
    }
    public static boolean fizzbuzz(int number){
        return number % 3 == 0 && number % 5 == 0;
    }
}
