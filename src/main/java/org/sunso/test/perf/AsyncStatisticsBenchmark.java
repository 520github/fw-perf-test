package org.sunso.test.perf;

import org.sunso.test.perf.request.AsyncBenchmarkRequest;

/**
 * 异步统计数据指标的性能测试
 */
public class AsyncStatisticsBenchmark extends AbstractBenchmark<AsyncBenchmarkRequest> {

    @Override
    public void benchmark(AsyncBenchmarkRequest request) {
        submitTask(request);
        try {
            request.getStatisticsCountDownLatch().await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
