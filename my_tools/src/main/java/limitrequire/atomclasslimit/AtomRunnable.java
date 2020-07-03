package limitrequire.atomclasslimit;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author shenhufei
 * @version 1.0
 * @Description:
 * @date 20200703
 */
public class AtomRunnable implements Runnable{
    private static AtomicInteger count = new AtomicInteger(0);
    @Override
    public void run() {
        if (count.get() >= 3) {
            System.out.println("请求用户过多，请稍后在试！"+System.currentTimeMillis()/1000);
        } else {
            count.incrementAndGet();
            try {
                //处理核心逻辑
                TimeUnit.SECONDS.sleep(1);
                System.out.println("当前线程名称是："+Thread.currentThread().getName()+"时间是："+System.currentTimeMillis()/1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                count.decrementAndGet();
            }
        }
    }
}
