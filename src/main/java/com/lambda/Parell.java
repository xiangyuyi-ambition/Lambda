package com.lambda;

import org.junit.Test;

import java.time.Duration;
import java.time.Instant;
import java.util.stream.LongStream;

/**
 * @program: lambda
 * @description
 * @author: xiangyuyi
 * @create: 2020-10-25 14:52
 **/
public class Parell {

    @Test
    public void parell(){
        //并行parallel()
        Instant instant = Instant.now();
        LongStream.rangeClosed(0,100000000000L).parallel().reduce(0,Long::sum);
        System.out.println(Duration.between(Instant.now(),instant).toMillis());
    }
}
