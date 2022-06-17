package fizzbuzz;

import static fizzbuzz.Application.*;

public class C implements Runnable {
    @Override
    public void run() {
        while (!isWorkDone){
            try {
                lock.lock();
                isNewNumber.await();
                if (fizzbuzz(currentNumber)){
                    System.out.println("fizzbuzz ");
                    isNewNumberProcessed.signalAll();
                } else {
                    isNewNumberNotDividedBy3Or5.signalAll();
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                lock.unlock();
            }
        }
    }

}
