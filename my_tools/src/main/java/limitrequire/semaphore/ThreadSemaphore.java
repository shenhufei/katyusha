package limitrequire.semaphore;

import lombok.SneakyThrows;

/**
 * @author shenhufei
 * @version 1.0
 * @Description:
 * @date 20200703
 */
public class ThreadSemaphore extends Thread {
    @SneakyThrows
    @Override
    public void run() {
        SemaphoreLimit.doLimit();
    }
}
