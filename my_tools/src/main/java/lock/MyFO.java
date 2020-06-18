package lock;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/**
 * @author shenhufei
 * @version 1.0
 * @Description:
 * @date 20200617
 */
public class MyFO extends FutureTask {
    public MyFO(Callable callable) {
        super(callable);
    }
}
