package org.sunso.test.perf.display.strategy;

import org.sunso.test.perf.report.AbstractBenchmarkStatisticsReport;

/**
 * 统计结果的显示策略
 */
public interface BenchmarkStatisticsDisplayStrategy {
    void displayStrategy(AbstractBenchmarkStatisticsReport report);
}
