package limitrequire.atomclasslimit;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author shenhufei
 * @version 1.0
 * @Description:
 * @date
 */
public class AtomThread  extends  Thread{
        private static AtomicInteger count = new AtomicInteger(0);

    @Override
    public void run() {
        try {
            AtomThread.exec();
        } catch (Exception e) {
            try {
                throw  new Exception();
            } catch (Exception ex) {
                System.out.println("-");
                ex.printStackTrace();
            }
        }
    }

    public static void exec() throws Exception {
            if (count.get() >= 3) {
                System.out.println("第1层，线程名称："+Thread.currentThread().getName());
            } else {
                if(count.get() >= 3){

                    System.out.println("第2层，线程名称："+Thread.currentThread().getName());
                    throw new Exception();
                }
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
