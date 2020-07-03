package limitrequire.limitrate;

import com.google.common.util.concurrent.RateLimiter;
import mythread.MyThread;

public class ThreadRateLimitDemo {
    public static void main(String[] args) {
        RateLimiter rateLimiter = RateLimiter.create(0.5);
        for (int i = 1; i <= 10; i++) {
            MyThread thread = new MyThread();
            thread.start();
            rateLimiter.acquire();// 请求RateLimiter, 超过permits会被阻塞
            System.out.println("call execute.." + i);
        }
    }
}
