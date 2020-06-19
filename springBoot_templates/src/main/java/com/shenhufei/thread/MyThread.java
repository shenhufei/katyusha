package com.shenhufei.thread;

import com.shenhufei.service.DemoService1;
import com.shenhufei.utils.ListUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentLinkedDeque;

/**
 * @author shenhufei
 * @version 1.0
 * @Description:
 * @date 20200618
 */
@Component
public class MyThread extends  Thread{
    @Autowired
    private DemoService1 demoService1;
    @Override
    public  void run(){
        ConcurrentLinkedDeque queue1 = ListUtils.getQueue1();
        demoService1.doService(queue1);
    }
}
