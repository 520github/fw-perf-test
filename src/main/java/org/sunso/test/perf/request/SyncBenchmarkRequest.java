package org.sunso.test.perf.request;

/**
 * 同步统计数据指标的请求参数
 */
public class SyncBenchmarkRequest extends BenchmarkRequest {
    public static SyncBenchmarkRequest create() {
        return new SyncBenchmarkRequest();
    }
}
