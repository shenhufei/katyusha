package learn.thread;

import java.util.Random;

public class ThreadLocalDemo {
	public static  Integer  in = 19;
	
	public static void main(String[] args) throws InterruptedException {
		System.err.println("-----");
		Runtime.getRuntime().exit(0);
		for (int i = 0; i < 10; i++) {
			Thread.sleep(100);
		   new TestThread().start();
		}

	}
	static class TestThread extends Thread{
		static ThreadLocal<String> local = new ThreadLocal<String>();
		@Override
		public void run(){
			local.set(Thread.currentThread().getName()+":"+System.currentTimeMillis()/in);
			System.out.println(Thread.currentThread().getName()+""+local.get());
		}
	}
}
