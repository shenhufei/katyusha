package com.shenhufei.service;

import org.springframework.stereotype.Service;

import java.util.concurrent.ConcurrentLinkedDeque;

/**
 * @author shenhufei
 * @version 1.0
 * @Description:
 * @date 20200619
 */
@Service
public class DemoService1 {

    public void doService(ConcurrentLinkedDeque queue1){
        System.out.println(queue1.getFirst());
    }
}
