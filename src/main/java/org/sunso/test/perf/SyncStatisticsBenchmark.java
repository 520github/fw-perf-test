package org.sunso.test.perf;

import org.sunso.test.perf.request.SyncBenchmarkRequest;

/**
 * 同步统计数据指标的性能测试
 */
public class SyncStatisticsBenchmark extends AbstractBenchmark<SyncBenchmarkRequest> {
    @Override
    public void benchmark(SyncBenchmarkRequest request) {
        submitTask(request);
    }
}
