package org.sunso.test.perf;

/**
 * 性能测试接口类
 * 
 * @param <B>
 */
public interface Benchmark<B> {

    void benchmark(B request);
}
