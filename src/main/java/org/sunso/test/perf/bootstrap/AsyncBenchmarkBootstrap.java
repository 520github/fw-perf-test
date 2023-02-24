package org.sunso.test.perf.bootstrap;

import org.sunso.test.perf.AsyncStatisticsBenchmark;
import org.sunso.test.perf.request.AsyncBenchmarkRequest;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 性能测试-异步计算统计指标-引导入口
 */
public class AsyncBenchmarkBootstrap extends SyncBenchmarkBootstrap {

    public AsyncBenchmarkBootstrap() {
        request = AsyncBenchmarkRequest.create();
    }

    public static AsyncBenchmarkBootstrap create() {
        return new AsyncBenchmarkBootstrap();
    }

    /**
     * 设置异步统计指标的线程池执行器
     * 
     * @param statisticsExecutorService
     * @return
     */
    public AsyncBenchmarkBootstrap setStatisticsExecuteService(ExecutorService statisticsExecutorService) {
        getAsyncBenchmarkRequest().setStatisticsExecutorService(statisticsExecutorService);
        return this;
    }

    protected void doRun() {
        setDefaultStatisticsExecutorService();
        getAsyncBenchmarkRequest().setStatisticsCountDownLatch(new CountDownLatch(request.getTotalProcessingNum()));
        new AsyncStatisticsBenchmark().benchmark((AsyncBenchmarkRequest) request);
    }

    /**
     * 设置异步统计指标的默认线程池执行器
     */
    private void setDefaultStatisticsExecutorService() {
        if (getAsyncBenchmarkRequest() == null) {
            getAsyncBenchmarkRequest().setStatisticsExecutorService(Executors.newFixedThreadPool(1));
        }
    }

    private AsyncBenchmarkRequest getAsyncBenchmarkRequest() {
        return request.convertToAsyncBenchmarkRequest();
    }
}
