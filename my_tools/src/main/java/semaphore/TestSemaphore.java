package semaphore;


import java.util.concurrent.Semaphore;

public class TestSemaphore {
    private static Semaphore limit = new Semaphore(5);
    public static void main(String[] args) throws InterruptedException {
        Thread[]  thread= new Thread[100];
        for (int i = 0;i<100;i++){
            thread[i] =new Thread(()->{
                try {
                    limit.acquire();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                try {
                    Thread.sleep(500l);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName());
                limit.release();
            });
            thread[i].start();
        }
    }

}
