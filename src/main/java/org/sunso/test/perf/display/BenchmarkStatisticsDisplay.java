package org.sunso.test.perf.display;

import org.sunso.test.perf.report.AbstractBenchmarkStatisticsReport;

/**
 * 统计结果显示处理
 */
public interface BenchmarkStatisticsDisplay {
    void display(AbstractBenchmarkStatisticsReport report);
}
