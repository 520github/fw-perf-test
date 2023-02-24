package org.sunso.test.perf.listener;

import org.sunso.test.perf.request.BenchmarkRequest;
import org.sunso.test.perf.item.BenchmarkItem;
import org.sunso.test.perf.report.AbstractBenchmarkStatisticsReport;
import org.sunso.test.perf.report.TpsStatisticsReport;

/**
 * 统计所有线程的总耗时、平均耗时、tps等指标
 */
public class TpsStatisticsListener extends AbstractBenchmarkStatisticsListener {

    @Override
    protected void doStatistics(BenchmarkItem item, BenchmarkRequest request) {
    }

    @Override
    protected AbstractBenchmarkStatisticsReport doStatisticsEnd(BenchmarkItem item, BenchmarkRequest request) {
        return TpsStatisticsReport.create().report(item, request, getTotalNanoTime())
                .setMinTime(BenchmarkItem.getMsTimeByNano(getUseMinTime()))
                .setMaxTime(BenchmarkItem.getMsTimeByNano(getUseMaxTime()))
                .setTotalStatisticsNum(getTotalStatisticsNum());
    }
}
