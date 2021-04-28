package com.ForkJoin;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.concurrent.RecursiveTask;

/**
 * @program: lambda
 * @description
 * @author: xiangyuyi
 * @create: 2020-10-25 14:25
 **/
@Data
@AllArgsConstructor
public class ForkJoinCalculate extends RecursiveTask<Long> {

    private long start;

    private long end;

    private static final long  CONTINUE = 10000;
    @Override
    protected Long compute() {
        Long length = end - start;
        if(length < CONTINUE){
            long sum = 0;
            for(long i=start; i<end; i++){
                sum += i;
            }
            return sum;
        }else{
            long middle = (start + end) /2;
            ForkJoinCalculate left = new ForkJoinCalculate(start,middle);
            left.fork();
            ForkJoinCalculate right = new ForkJoinCalculate(middle + 1,end);
            right.fork();
            return right.join() + left.join();
        }
    }
}
