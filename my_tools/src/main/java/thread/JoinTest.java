package thread;

public class JoinTest implements Runnable{

    private static int a = 10;

    @Override
    public void run() {
        try {
            System.out.println("job execute start: "+System.currentTimeMillis());
            Thread.sleep(2000);
            a++;
            System.out.println("job execute complete: "+System.currentTimeMillis());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Thread t = new Thread(new JoinTest());
        try {
            System.out.println("***************"+System.currentTimeMillis());
            Thread.sleep(300);
            System.out.println("begin to join: "+System.currentTimeMillis());
            t.start();
            t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("================"+System.currentTimeMillis());
        if (a == 11) {
            System.out.println("a=11");
        }else {
            System.out.println("a=10");
        }

    }
}
