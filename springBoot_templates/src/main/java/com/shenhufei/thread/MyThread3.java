package com.shenhufei.thread;

import com.shenhufei.service.DemoService3;
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
public class MyThread3 extends Thread{
    @Autowired
    private DemoService3 demoService3;
    @Override
    public  void run(){
        ConcurrentLinkedDeque queue3 = ListUtils.getQueue3();
        demoService3.doService3(queue3);
    }
}
