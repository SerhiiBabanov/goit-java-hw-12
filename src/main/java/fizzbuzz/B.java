package fizzbuzz;

import static fizzbuzz.Application.*;

public class B implements Runnable {
    @Override
    public void run() {
        while (!isWorkDone){
            try {
                BARRIER2.await();
                if (buzz(currentNumber)){

                    currentNumberState |= (1 << 1);

                }
                BARRIER.await();
                if (currentNumberState == 2){
                    System.out.print("buzz ");
                }
                BARRIER1.await();
            } catch (Exception e) {
                throw new RuntimeException(e);
            } finally {
               // lock.unlock();
            }
        }
    }
    public static boolean buzz(int number){
        return number % 5 == 0;
    }
}
