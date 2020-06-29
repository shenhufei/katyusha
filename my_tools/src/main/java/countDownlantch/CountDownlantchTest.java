package countDownlantch;

import java.util.concurrent.CountDownLatch;

public class CountDownlantchTest {
    static CountDownLatch lantch =  new CountDownLatch(4);
    public static void main(String[] args) throws InterruptedException {
        MyThread myThread1 = new MyThread();
        MyThread2 myThread2 = new MyThread2();
        MyThread3 myThread3 = new MyThread3();
        MyThread4 myThread4 = new MyThread4();
        Thread array []  ={myThread1,myThread2,myThread3,myThread4};
        for(int i =0; i<array.length;i++){
            lantch.countDown();
            array[i].start();
        }
        lantch.await();
        System.out.println("最后一起返回");
    }

}
