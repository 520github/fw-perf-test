package org.sunso.test.perf.listener;

import org.sunso.test.perf.request.BenchmarkRequest;
import org.sunso.test.perf.item.BenchmarkItem;
import org.sunso.test.perf.report.AbstractBenchmarkStatisticsReport;

/**
 * 每一个任务完成后的统计监听处理
 */
public interface BenchmarkStatisticsListener {
    AbstractBenchmarkStatisticsReport statistics(BenchmarkItem item, BenchmarkRequest request);
}
