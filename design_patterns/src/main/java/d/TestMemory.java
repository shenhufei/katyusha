package d;

import java.util.ArrayList;

/**
 * @author shenhufei
 * @version 1.0
 * @Description:o
 * @date 20200623
 */
public class TestMemory {
    public static void main(String[] args) throws InterruptedException {
        ArrayList<Person> list = new ArrayList<>();
        for(int i =0 ;i <100;i++){
            Thread.sleep(300L);
            Person person = new Person();
            person.setAge(10L);
            person.setName("dasdasdasdas");
            list.add(person);
            System.out.println("sadas");
            MyThread myThread = new MyThread();
            myThread.setName("线程的名称是："+i);
            myThread.start();
        }
    }
}
