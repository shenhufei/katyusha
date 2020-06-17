package lock;

import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.Vector;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.locks.LockSupport;

/**
 * @author shenhufei
 * @version 1.0
 * @Description:
 * @date 20200617
 */
public class MyLock {
    ConcurrentLinkedDeque<Thread> queue = new ConcurrentLinkedDeque<Thread>();
    volatile Integer state = 0;

    public void lock(){
        Thread thread = Thread.currentThread();
        queue.add(thread);
        for(;;){
            if(queue.size()==0||null!=queue.peek()){
                //相当于获取到了锁；
                break;
            }
            //将没有获取到锁的线程都进行阻塞；
            LockSupport.park(thread);
        }

    }

    public void unLock(){
        Thread thread = Thread.currentThread();

        LockSupport.park(thread);
        //TODO
    }
    public static Unsafe getUnsafe() throws NoSuchFieldException, IllegalAccessException {
        // 通过反射获取rt.jar下的Unsafe类
        Field theUnsafeInstance = Unsafe.class.getDeclaredField("theUnsafe");
        theUnsafeInstance.setAccessible(true);
        return (Unsafe) theUnsafeInstance.get(Unsafe.class);
    }
}
