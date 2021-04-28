package com.example.lambda;

import com.ForkJoin.ForkJoinCalculate;
import org.junit.Test;

import java.time.Instant;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;

/**
 * @program: lambda
 * @description
 * @author: xiangyuyi
 * @create: 2020-10-25 14:34
 **/
public class ForkJoin {

    @Test
    public void testForkJoin(){
        ForkJoinPool pool = new ForkJoinPool();
        ForkJoinTask<Long> task = new ForkJoinCalculate(0,100000000L);
        long sum = pool.invoke(task);
        System.out.println(sum);
    }

    @Test
    public void test(){
        long sum = 0;
        for(long i=0; i<100000000L; i++){
            sum += i;
        }
        System.out.println(sum);
    }
}


