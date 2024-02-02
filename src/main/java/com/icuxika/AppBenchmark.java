package com.icuxika;

import org.openjdk.jmh.annotations.*;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

//使用模式 默认是Mode.Throughput
@BenchmarkMode(Mode.AverageTime)
// 配置预热次数，默认是每次运行1秒，运行10次，这里设置为3次
@Warmup(iterations = 3, time = 1)
// 本例是一次运行4秒，总共运行3次，在性能对比时候，采用默认1秒即可
@Measurement(iterations = 3, time = 4)
// 配置同时起多少个线程执行
@Threads(1)
//代表启动多个单独的进程分别测试每个方法，这里指定为每个方法启动一个进程
@Fork(1)
// 定义类实例的生命周期，Scope.Benchmark：所有测试线程共享一个实例，用于测试有状态实例在多线程共享下的性能
@State(Scope.Benchmark)
// 统计结果的时间单元
@OutputTimeUnit(TimeUnit.NANOSECONDS)
public class AppBenchmark {

    static AtomicInteger integer = new AtomicInteger();

    @Benchmark
    public void test() {
        integer.incrementAndGet();
    }

}
