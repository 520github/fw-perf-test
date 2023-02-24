package org.sunso.test.perf.task;

import org.sunso.test.perf.request.BenchmarkRequest;
import org.sunso.test.perf.item.BenchmarkItem;
import org.sunso.test.perf.report.AbstractBenchmarkStatisticsReport;

/**
 * 数据指标统计任务
 */
public class BenchmarkStatisticsTask implements Runnable {
    BenchmarkRequest request;
    BenchmarkItem item;

    public static BenchmarkStatisticsTask create(BenchmarkRequest request, BenchmarkItem item) {
        return new BenchmarkStatisticsTask(request, item);
    }

    public BenchmarkStatisticsTask(BenchmarkRequest request, BenchmarkItem item) {
        this.request = request;
        this.item = item;
    }

    @Override
    public void run() {
        try {
            request.getListeners().forEach(listener -> {
                AbstractBenchmarkStatisticsReport report = listener.statistics(item, request);
                request.addReport(report);
                request.getDisplayStrategy().displayStrategy(report);
            });
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            request.convertToAsyncBenchmarkRequest().getStatisticsCountDownLatch().countDown();
        }

    }
}
