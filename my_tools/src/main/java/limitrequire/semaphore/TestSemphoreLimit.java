package limitrequire.semaphore;

/**
 * @author shenhufei
 * @version 1.0
 * @Description:
 * @date 20200703
 */
public class TestSemphoreLimit {
    public static void main(String[] args) {
        System.out.println("开始时间是："+System.currentTimeMillis());
        for (int i =0 ;i<20 ;i++){
            ThreadSemaphore thread = new ThreadSemaphore();
            thread.start();
        }
        System.out.println("结束时间是："+System.currentTimeMillis());
    }
}
