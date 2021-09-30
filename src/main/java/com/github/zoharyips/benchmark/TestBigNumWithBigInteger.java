package com.github.zoharyips.benchmark;

import com.github.zoharyips.algorithm.BigNum;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.math.BigInteger;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * <h3>JMH 测试 BigNum 与 BigInteger</h3>
 *
 * Benchmark                                   Mode  Cnt  Score    Error  Units
 * TestBigNumWithBigInteger.testBigIntegerAdd  avgt   10  1.093 ±  0.016  ms/op
 * TestBigNumWithBigInteger.testBigNumAdd      avgt   10  0.046 ±  0.001  ms/op
 *
 * @author zohar
 * @version 1.0
 * 2021-10-01 01:18
 */
@Fork(2)
@State(Scope.Benchmark)
@BenchmarkMode(Mode.AverageTime)
@Warmup(iterations = 5, time = 3)
@Measurement(iterations = 5, time = 5)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
public class TestBigNumWithBigInteger {

    public static final Random random = new Random(System.currentTimeMillis());
    public static final StringBuilder stringBuilder = new StringBuilder();
    private static String num1;
    private static String num2;

    public static void main(String[] args) throws RunnerException {
        Options options = new OptionsBuilder().include(TestBigNumWithBigInteger.class.getSimpleName()).build();
        new Runner(options).run();
    }

    @Setup(Level.Invocation)
    public void setUp() {
        num1 = genString();
        num2 = genString();
    }

    @Benchmark
    public void testBigNumAdd(Blackhole blackHole) {
        blackHole.consume(BigNum.add(num1, num2));
    }

    @Benchmark
    public void testBigIntegerAdd(Blackhole blackHole) {
        BigInteger bigInteger1 = new BigInteger(num1);
        BigInteger bigInteger2 = new BigInteger(num2);
        blackHole.consume(bigInteger1.add(bigInteger2));
    }

    private String genString() {
        stringBuilder.delete(0, stringBuilder.length());
        int len = 0;
        while (len == 0) {
            len = random.nextInt(10000);
        }
        int first = 0;
        while (first == 0) {
            first = random.nextInt(10);
        }
        stringBuilder.append(first);
        for (int i = 1; i < len; i++) {
            stringBuilder.append(random.nextInt(10));
        }
        return stringBuilder.toString();
    }
}
