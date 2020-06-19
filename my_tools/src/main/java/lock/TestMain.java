package lock;

import sun.misc.Unsafe;

import java.util.Vector;

/**
 * @author shenhufei
 * @version 1.0
 * @Description:
 * @date 20200617
 */
public class TestMain {

    public static Vector vector;

    public static void main(String[] args)  {
        Vector vectors = new Vector<Integer>();
        vectors.add(1);
        vectors.add(2);
        vectors.add(3);
        vectors.add(4);
        vectors.add(5);
        vectors.add(6);
        vector =vectors;
        MyThread t1 = new MyThread();
        MyThread2 t2 = new MyThread2();
        MyThread3 t3 = new MyThread3();
        t1.start();
        t2.start();
        t3.start();

    }
    public static Vector getVector() {
        return vector;
    }

    public static void setVector(Vector vector) {
        TestMain.vector = vector;
    }


}
