package semaphore;

import java.util.concurrent.Semaphore;

public class MyThread extends  Thread {
    private static Semaphore limit = new Semaphore(5);
    public void run(){
        try {
            Thread.sleep(500L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            limit.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName());
        limit.release();
    }
}
