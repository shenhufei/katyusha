package com.shenhufei.utils;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentLinkedDeque;

/**
 * @author shenhufei
 * @version 1.0
 * @Description:
 * @date 20200619
 */
public class ListUtils {
    static ConcurrentLinkedDeque queue1 =  new ConcurrentLinkedDeque<Object>();

    static ConcurrentLinkedDeque queue2 =  new ConcurrentLinkedDeque<Object>();

    static ConcurrentLinkedDeque queue3 =  new ConcurrentLinkedDeque<Object>();

    public static ConcurrentLinkedDeque getQueue3() {
        return queue3;
    }

    public static void setQueue3(ConcurrentLinkedDeque queue3) {
        ListUtils.queue3 = queue3;
    }

    public static ConcurrentLinkedDeque getQueue1() {
        return queue1;
    }

    public static void setQueue1(ConcurrentLinkedDeque queue1) {
        ListUtils.queue1 = queue1;
    }

    public static ConcurrentLinkedDeque getQueue2() {
        return queue2;
    }

    public static void setQueue2(ConcurrentLinkedDeque queue2) {
        ListUtils.queue2 = queue2;
    }
}
