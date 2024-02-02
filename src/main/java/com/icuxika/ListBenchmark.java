package com.icuxika;

import org.openjdk.jmh.annotations.*;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.concurrent.TimeUnit;

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
public class ListBenchmark {

    private ArrayList<Integer> arrayList;
    private LinkedList<Integer> linkedList;

    @Setup(Level.Invocation)
    public void setup() {
        this.arrayList = new ArrayList<>();
        this.linkedList = new LinkedList<>();
        for (int i = 0; i < 1_1000; i++) {
            this.arrayList.add(i);
            this.linkedList.add(i);
        }
    }

    @TearDown(Level.Invocation)
    public void clear() {
        arrayList.clear();
        linkedList.clear();
    }

    @Benchmark
    public void arrayListAddToBack() {
        for (int i = 0; i < 1_1000; i++) {
            this.arrayList.add(i);
        }
    }

    @Benchmark
    public void linkedListAddToBack() {
        for (int i = 0; i < 1_1000; i++) {
            this.linkedList.add(i);
        }
    }

}
