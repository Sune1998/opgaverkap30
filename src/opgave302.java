import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;


public class opgave302 {
    // opgave 30 04
static Integer sum = 0;
static Integer sumWithoutsync = 0;

final static int numoftreads = 10000;
final static int sleep = 5; // wait in ms

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();

        for (int i = 0; i < numoftreads; i++) {
            executorService.execute(new sum());
            executorService.execute(new sumWithoutSync());
        }
        executorService.shutdown();

        while (!executorService.isTerminated()){

        }
        System.out.println("sum without sync is" + sumWithoutsync);
        System.out.println("sum is" + sum);
    }
    private static class sumWithoutSync implements Runnable {

        @Override
        public void run() {
        sumWithoutsync += 1;

        try {
            Thread.sleep(sleep);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        }
    }

private static class sum implements Runnable{
static final Semaphore lock = new Semaphore(1);

    @Override
    public void run() {
    synchronized (lock){
        sum += 1;
    }
    try {
        Thread.sleep(sleep);
    } catch (InterruptedException e) {
        e.printStackTrace();
    }
    }
}


}
