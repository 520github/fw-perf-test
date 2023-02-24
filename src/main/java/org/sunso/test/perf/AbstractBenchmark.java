package org.sunso.test.perf;

import org.sunso.test.perf.item.BenchmarkItem;
import org.sunso.test.perf.request.BenchmarkRequest;
import org.sunso.test.perf.task.BenchmarkTask;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public abstract class AbstractBenchmark<B extends BenchmarkRequest> implements Benchmark<B> {
    protected void submitTask(BenchmarkRequest request) {
        CountDownLatch countDownLatch = new CountDownLatch(request.getTotalProcessingNum());
        ExecutorService executorService = Executors.newFixedThreadPool(request.getTotalThreadNum());
        for (int i = 1; i <= request.getTotalProcessingNum(); i++) {
            executorService.submit(getBenchmarkTask(request, i, countDownLatch));
        }
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    protected BenchmarkTask getBenchmarkTask(BenchmarkRequest request, int index, CountDownLatch countDownLatch) {
        return new BenchmarkTask(request, getBenchmarkItem(index), countDownLatch);
    }

    private BenchmarkItem getBenchmarkItem(int index) {
        return new BenchmarkItem(index);
    }
}
