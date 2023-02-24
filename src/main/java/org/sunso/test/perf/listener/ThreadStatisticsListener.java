package org.sunso.test.perf.listener;

import org.sunso.test.perf.item.BenchmarkItem;
import org.sunso.test.perf.report.AbstractBenchmarkStatisticsReport;
import org.sunso.test.perf.report.ThreadStatisticsReport;
import org.sunso.test.perf.request.BenchmarkRequest;

/**
 * 统计每个线程对应的总耗时、平均耗时、最小耗时、最大耗时、tps等指标
 */
public class ThreadStatisticsListener extends AbstractBenchmarkStatisticsListener {
    ThreadStatisticsReport threadStatisticsReport = new ThreadStatisticsReport();

    @Override
    protected void doStatistics(BenchmarkItem item, BenchmarkRequest request) {
        threadStatisticsReport.report(item, request);
    }

    @Override
    protected AbstractBenchmarkStatisticsReport doStatisticsEnd(BenchmarkItem item, BenchmarkRequest request) {
        return threadStatisticsReport.setTotalProcessingNum(request.getTotalProcessingNum())
                .setTotalThreadNum(request.getTotalThreadNum()).setTotalStatisticsNum(getTotalStatisticsNum())
                .setRemark(request.getRemark());
    }
}
