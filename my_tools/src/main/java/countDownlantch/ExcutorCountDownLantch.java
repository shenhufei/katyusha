package countDownlantch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class ExcutorCountDownLantch {
    static CountDownLatch countDownLantch = new CountDownLatch(5);
    public static void main(String[] args) throws InterruptedException {
        ScheduledExecutorService threadPool = Executors.newScheduledThreadPool(5);
        for (int i =0;i<5;i++){
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    System.out.println(System.currentTimeMillis());
                }
            };
            countDownLantch.countDown();
            threadPool.execute(runnable);
        }
        countDownLantch.await();
        System.out.println("一起返回");
    }
}
