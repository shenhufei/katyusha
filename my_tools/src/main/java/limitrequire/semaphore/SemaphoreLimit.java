package limitrequire.semaphore;

import java.util.concurrent.Semaphore;

/**
 * @author shenhufei
 * @version 1.0
 * @Description:
 * @date 20200703
 */
public class SemaphoreLimit {
    private  static Semaphore semaphore = new Semaphore(1);
    public  static void  doLimit() throws InterruptedException {
        semaphore.acquire();
        Thread.sleep(1000L);
        System.out.println(Thread.currentThread().getName());
        semaphore.release();
   }
}
