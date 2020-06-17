package lock;

import sun.misc.Unsafe;

/**
 * @author shenhufei
 * @version 1.0
 * @Description:
 * @date 20200617
 */
public class TestMain {
    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        Unsafe unsafe = MyLock.getUnsafe();
    }
}
