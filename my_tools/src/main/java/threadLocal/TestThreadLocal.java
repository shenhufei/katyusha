package threadLocal;

import mythread.MyThread;

public class TestThreadLocal {
    private static final ThreadLocal<Object> threadLocal = new ThreadLocal<Object>() ;
    public static void main(String[] args) {
        for(int i = 0;i< 10 ;i++){
            MyThread myThread = new MyThread();

        }
    }

    public static class MyThread extends  Thread{
        @Override
        public  void run(){
            System.out.println("MyThread1准备好了:");
        }
    }
}
