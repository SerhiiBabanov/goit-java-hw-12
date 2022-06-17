package fizzbuzz;

import static fizzbuzz.Application.*;

public class D implements Runnable {
    @Override
    public void run() {
        while (!isWorkDone){
            try {
                lock.lock();
                isNewNumberNotDividedBy3Or5.await();

                number(currentNumber);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                lock.unlock();
            }
        }
    }


}
