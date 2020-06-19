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
public class DemoService3 {

    public void doService3(ConcurrentLinkedDeque queue3) {
        System.out.println(queue3.getFirst());
    }
}
