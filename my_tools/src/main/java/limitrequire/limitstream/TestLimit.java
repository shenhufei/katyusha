package limitrequire.limitstream;

import com.google.common.util.concurrent.RateLimiter;

/**
 * @author shenhufei
 * @version 1.0
 * @Description:
 * @date 20200701
 */
public class TestLimit {
    public static void main(String[] args) {
        RateLimiter rateLimiter = RateLimiter.create(1);

    }
}
