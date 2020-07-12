package threadpool;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class WorkStealingPool {
    public static void main(String[] args) {
         ExecutorService service = Executors.newWorkStealingPool();
         //返回可用于Java虚拟机的处理器数量。
         System.out.println(Runtime.getRuntime().availableProcessors());
         service.submit(new R(1)); //精灵线程
         service.submit(new R(2));
         service.submit(new R(2));
         service.submit(new R(2));
         service.submit(new R(2));
         try {
             System.in.read();
             //由于产生的是精灵线程（守护线程、后台线程），主程序不阻塞的话看不到打印信息
         } catch (IOException e) {
             e.printStackTrace();
         }
    }
    static class R implements Runnable {
        int time;
        R(int runTime) {
             this.time = runTime;

        }
        @Override
        public void run() {
            try {
                TimeUnit.SECONDS.sleep(time);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(time + " " + Thread.currentThread().getName());
        }
    }
}
