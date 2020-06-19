package lock;

import java.util.Vector;

/**
 * @author shenhufei
 * @version 1.0
 * @Description:
 * @date 20200619
 */
public class MyThread3 extends Thread{

    @Override
    public  void run(){
        TestMain testMain =new TestMain();
        Vector vector = testMain.getVector();
        Object o = vector.get(2);
        System.out.println("MyThread3:"+o);
    }
}
