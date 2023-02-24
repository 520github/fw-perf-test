package org.sunso.test.perf.request;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;

/**
 * 异步统计数据指标的请求参数
 */
public class AsyncBenchmarkRequest extends SyncBenchmarkRequest {

    public static AsyncBenchmarkRequest create() {
        return new AsyncBenchmarkRequest();
    }

    private ExecutorService statisticsExecutorService;

    private CountDownLatch statisticsCountDownLatch;

    public ExecutorService getStatisticsExecutorService() {
        return statisticsExecutorService;
    }

    public AsyncBenchmarkRequest setStatisticsExecutorService(ExecutorService statisticsExecutorService) {
        this.statisticsExecutorService = statisticsExecutorService;
        return this;
    }

    public CountDownLatch getStatisticsCountDownLatch() {
        return statisticsCountDownLatch;
    }

    public AsyncBenchmarkRequest setStatisticsCountDownLatch(CountDownLatch statisticsCountDownLatch) {
        this.statisticsCountDownLatch = statisticsCountDownLatch;
        return this;
    }
}
