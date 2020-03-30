import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.*;

import java.util.concurrent.locks.ReentrantLock;

public class Opgave3011 {
    // opgave 32 11

    static Integer n = 10; // set num of threads
    static final Lock locka = new ReentrantLock();
    static final Lock lockb = new ReentrantLock();

    public static void main(String[] args) {

        ExecutorService executorService = Executors.newCachedThreadPool();

        for (int i = 0; i < n; i++) {
            executorService.execute(new Locka());
            executorService.execute(new Lockb());
            
        }
        executorService.shutdown();

        while (!executorService.isTerminated()){

        }
    }

    static class Locka implements Runnable {

        @Override
        public void run() {
        long id = Thread.currentThread().getId();

        synchronized (locka){
            lockb.lock();
            System.out.println("b locked" + id);
        }
        synchronized (lockb){
            locka.lock();
            System.out.println("a locked" + id);
            locka.unlock();
            lockb.unlock();
        }
        }
    }

    static class Lockb implements Runnable {
        @Override
        public void run() {
            long id = Thread.currentThread().getId();

            synchronized (lockb){
                lockb.lock();
                System.out.println("b locked" + id);
            }
            synchronized (locka){
                locka.lock();
                System.out.println("a locked" + id);
                locka.unlock();
                lockb.unlock();
            }

        }
    }

}
