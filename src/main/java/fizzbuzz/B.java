package fizzbuzz;

import static fizzbuzz.Application.*;

public class B implements Runnable {
    @Override
    public void run() {
        while (!isWorkDone){
            try {
                lock.lock();
                isNewNumber.await();
                if (buzz(currentNumber)){
                    System.out.println("buzz ");
                    isNewNumberProcessed.signalAll();
                } else {
                    //isNewNumberNotDivided5.signalAll();
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                lock.unlock();
            }
        }
    }

}
