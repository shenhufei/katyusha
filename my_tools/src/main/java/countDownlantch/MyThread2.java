package countDownlantch;

import lock.TestMain;

import java.util.Vector;

/**
 * @author shenhufei
 * @version 1.0
 * @Description:
 * @date 20200619
 */
public class MyThread2 extends Thread {
    @Override
    public  void run(){
        System.out.println("MyThread2:"+2);
    }
}
