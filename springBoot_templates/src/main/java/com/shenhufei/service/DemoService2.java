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
public class DemoService2 {

    public void doService2(ConcurrentLinkedDeque queue2) {
        System.out.println(queue2.getFirst());
    }
}
