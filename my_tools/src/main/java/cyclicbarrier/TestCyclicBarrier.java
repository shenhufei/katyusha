package cyclicbarrier;

import countDownlantch.MyThread;
import countDownlantch.MyThread2;
import countDownlantch.MyThread3;
import countDownlantch.MyThread4;

import java.util.ArrayList;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class TestCyclicBarrier {
    static CyclicBarrier cyclicBarrier =  new CyclicBarrier(4);

    public static void main(String[] args) throws BrokenBarrierException, InterruptedException {
        ArrayList<Long> list = new ArrayList<>();
        for(int i =0; i<4 ;i++){
            Thread iii= new Thread(()->{
                long l = System.currentTimeMillis();
                list.add(l);
                //System.out.println(System.currentTimeMillis());
                try {
                    int await = cyclicBarrier.await();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
            iii.start();
        }
        //System.out.println("都返回来了，开始做事情了");
        System.out.println("都返回来了，开始做事情了"+list.size());

    }
}
