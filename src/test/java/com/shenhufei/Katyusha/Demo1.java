package com.shenhufei.Katyusha;

import java.time.Duration;
import java.time.Instant;
import java.util.stream.LongStream;

public class Demo1 {

    public static void main(String[] args) {
        /*Instant start = Instant.now();
        // 普通做法求0加到10000000000的和
        LongStream.rangeClosed(0, 100000000000L).reduce(0, Long::sum);
        Instant end = Instant.now();
        System.out.println("耗费" + Duration.between(end, start) + "秒");// 55秒

        Instant start2 = Instant.now();
        // 并行流求0加到10000000000的和
        LongStream.rangeClosed(0, 100000000000L).parallel().reduce(0,
                Long::sum);
        // 使用并行流

        Instant end2 = Instant.now();
        System.out.println("耗费" + Duration.between(end2, start2) + "秒");// 30秒
*/    }

}
