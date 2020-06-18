package lock;

import sun.misc.Unsafe;

/**
 * @author shenhufei
 * @version 1.0
 * @Description:
 * @date 20200617
 */
public class TestMain {
    MyLock mylock;

    {
        try {
            mylock = new MyLock();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        for ( int i=0 ; i<10 ; i++){
            MyThread thread = new MyThread();
            thread.start();
        }

    }

}
