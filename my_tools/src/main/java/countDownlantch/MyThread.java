package countDownlantch;

import lock.TestMain;

import java.util.Vector;

/**
 * @author shenhufei
 * @version 1.0
 * @Description:
 * @date 20200618
 */
public class MyThread extends  Thread{
    @Override
    public  void run(){
        System.out.println("MyThread:"+1);
    }
}
