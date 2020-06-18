package lock;

/**
 * @author shenhufei
 * @version 1.0
 * @Description:
 * @date 20200618
 */
public class MyThread extends  Thread{
    volatile  Integer j = 0;
    @Override
    public  void run(){
        synchronized (this){
            for ( int i=0;i<3;i++){
                j++;
                System.out.println(j);
            }
        }
    }
}
