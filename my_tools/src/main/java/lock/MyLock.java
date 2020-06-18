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
    private static Unsafe unsafe;

    static {
        try {
            unsafe = getUnsafe();
            //TODO 需要初始化偏移量；
            offest = unsafe.objectFieldOffset(MyLock.class.getDeclaredField("offest"));
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    volatile Integer state = 0;
    private static Long offest;
    //TODO 需要定义当前锁的持有线程对象；
    private Thread threadHolder;

    public MyLock() throws NoSuchFieldException, IllegalAccessException {
    }

    public void lock(){
        Thread thread = Thread.currentThread();
        queue.add(thread);
        int status = getState();
        //TODO 1.需要判断 状态量是否是1，才决定是否做；比较与交换的操作；
        if(1==status){
            for(;;){
                //TODO 2.需要做 比较与交换的操作；来控制是否获取到锁
                if((queue.size()==0||null!=queue.peek() && unsafe.compareAndSwapInt(this,offest,0,1))){
                    //相当于获取到了锁；
                    //TODO 3.在比较与交换的操作成功之后，需要把当前持有锁的线程赋值给 ThreadHolder
                    threadHolder = Thread.currentThread();
                    break;
                }
                //将没有获取到锁的线程都进行阻塞；
                LockSupport.park(thread);
            }
        }


    }

    public void unLock(){
        Thread thread = Thread.currentThread();
        //TODO 做解锁操作之前还需要判断，当前线程是否是锁的持有线程，
        if(thread.equals(threadHolder)){
            //TODO 需要把已经获取到锁的线程从队列中移除
            queue.peek();
            //TODO 需要再次使用比较与交换方法，把修改后的值再次修改回去；
            unsafe.compareAndSwapInt(this,offest,1,0);
            //TODO 需要把队列当中第一个等待的获取锁的线程进行唤醒；
            if(null != queue.poll()){
                LockSupport.unpark(queue.poll());
            }
        }

    }
    public static Unsafe getUnsafe() throws NoSuchFieldException, IllegalAccessException {
        // 通过反射获取rt.jar下的Unsafe类
        Field theUnsafeInstance = Unsafe.class.getDeclaredField("theUnsafe");
        theUnsafeInstance.setAccessible(true);
        return (Unsafe) theUnsafeInstance.get(Unsafe.class);
    }
    public ConcurrentLinkedDeque<Thread> getQueue() {
        return queue;
    }

    public void setQueue(ConcurrentLinkedDeque<Thread> queue) {
        this.queue = queue;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
    public static Long getOffest() {
        return offest;
    }


    public void setUnsafe(Unsafe unsafe) {
        this.unsafe = unsafe;
    }

    public Thread getThreadHolder() {
        return threadHolder;
    }

    public void setThreadHolder(Thread threadHolder) {
        this.threadHolder = threadHolder;
    }
}
