package com.shenhufei.controller;
import com.shenhufei.redis.RedisService;
import com.shenhufei.service.DemoService1;
import com.shenhufei.service.DemoService2;
import com.shenhufei.thread.MyThread;
import com.shenhufei.thread.MyThread2;
import com.shenhufei.thread.MyThread3;
import com.shenhufei.utils.ListUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ConcurrentLinkedDeque;

/**
 * @author shenhufei
 * @version 1.0
 * @Description:
 * @date 20200615
 */
@RestController
public class IndexController {
    @Autowired
    private RedisService redisService;
    @Autowired
    private MyThread myThread;
    @Autowired
    private MyThread2 myThread2;
    @Autowired
    private MyThread3 myThread3;

    @RequestMapping("/setString")
    public String setString(String key, String value){
        redisService.set(key, value, 5000l); //超时时间5000s   l表示long型
        return "成功";
    }

    @RequestMapping("/testThread")
    public String get(String key) {
        ConcurrentLinkedDeque queue1 = new ConcurrentLinkedDeque();
        queue1.add("queue1:"+1);
        queue1.add(3);
        ConcurrentLinkedDeque queue2 = new ConcurrentLinkedDeque();
        queue2.add(1);
        queue2.add(2);
        queue2.add(3);
        ConcurrentLinkedDeque queue3 = new ConcurrentLinkedDeque();
        queue3.add(1);
        queue3.add(2);
        queue3.add(3);
        ListUtils.setQueue1(queue1);
        ListUtils.setQueue2(queue2);
        ListUtils.setQueue3(queue3);
        myThread.start();
        myThread2.start();
        myThread3.start();
        return "";
    }
}