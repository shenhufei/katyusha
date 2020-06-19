package com.shenhufei.thread;

import com.shenhufei.service.DemoService2;
import com.shenhufei.utils.ListUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentLinkedDeque;

/**
 * @author shenhufei
 * @version 1.0
 * @Description:
 * @date 20200619
 */
@Component
public class MyThread2 extends Thread {
    @Autowired
    private DemoService2 demoService2;
    @Override
    public  void run(){
        ConcurrentLinkedDeque queue2 = ListUtils.getQueue2();
        demoService2.doService2(queue2);
    }
}
