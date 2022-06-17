package fizzbuzz;
import static fizzbuzz.Application.*;
public class A implements Runnable {

    @Override
    public void run() {
        while (!isWorkDone){
            try {
                lock.lock();
                isNewNumber.await();
                if (fizz(currentNumber)){
                    System.out.println("fizz ");
                    isNewNumberProcessed.signalAll();
                } else {
                    //isNewNumberNotDivided3.signalAll();
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                lock.unlock();
            }
        }
    }


}
