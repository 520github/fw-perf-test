package org.sunso.test.perf.task;

import org.sunso.test.perf.request.AsyncBenchmarkRequest;
import org.sunso.test.perf.request.BenchmarkRequest;
import org.sunso.test.perf.item.BenchmarkItem;
import org.sunso.test.perf.report.AbstractBenchmarkStatisticsReport;

import java.util.concurrent.CountDownLatch;

/**
 * 对原始任务进行包装处理
 */
public class BenchmarkTask implements Runnable {
    private BenchmarkRequest request;
    private BenchmarkItem item;
    private CountDownLatch countDownLatch;

    public BenchmarkTask(BenchmarkRequest request, BenchmarkItem item, CountDownLatch countDownLatch) {
        this.request = request;
        this.item = item;
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        try {
            item.setThreadName(Thread.currentThread().getName());
            item.toStart();
            request.getRunnable().run();
            item.toEnd();
            notifyListener();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            countDownLatch.countDown();
        }
    }

    /**
     * 通知监听器执行数据指标统计
     */
    private void notifyListener() {
        // 异步去统计相关数据
        if (request instanceof AsyncBenchmarkRequest) {
            request.convertToAsyncBenchmarkRequest().getStatisticsExecutorService()
                    .submit(BenchmarkStatisticsTask.create(request, item));
        } else {
            doNotifyListener();
        }
    }

    private void doNotifyListener() {
        request.getListeners().forEach(listener -> {
            AbstractBenchmarkStatisticsReport report = listener.statistics(item, request);
            request.addReport(report);
            request.getDisplayStrategy().displayStrategy(report);
        });
    }
}
