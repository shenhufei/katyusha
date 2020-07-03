package limitrequire.limitrate;

import com.google.common.util.concurrent.RateLimiter;

import java.util.Calendar;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static com.google.common.collect.Lists.newArrayList;

public class ExcutorsRateLimitDemo {
    public static void main(String[] args) {
        List<String> queryNos = newArrayList("1", "2", "3", "4", "5", "6", "7");
        RateLimiter limiter = RateLimiter.create(1);
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        for (final String queryNo : queryNos) {
            limiter.acquire();
            executorService.submit(new Runnable() {
                public void run() {
                    //...发送请求
                    System.out.println(Thread.currentThread().getName());
                    System.out.println(queryNo + ":" + Calendar.getInstance().getTimeInMillis());
                }
            });
        }
        executorService.shutdownNow();
    }
}
