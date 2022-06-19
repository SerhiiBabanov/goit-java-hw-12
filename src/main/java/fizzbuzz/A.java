package fizzbuzz;
import static fizzbuzz.Application.*;
public class A implements Runnable {

    @Override
    public void run() {
        while (!isWorkDone){
            try {
                BARRIER2.await();
                if (fizz(currentNumber)){
                    currentNumberState |= (1 << 0);
                }

                BARRIER.await();

                if (currentNumberState == 1){
                    System.out.print("fizz ");
                }
                BARRIER1.await();
            } catch (Exception e) {
                throw new RuntimeException(e);
            } finally {
               // lock.unlock();
            }
        }
    }
    public static boolean fizz(int number){
        return number % 3 == 0;
    }


}
