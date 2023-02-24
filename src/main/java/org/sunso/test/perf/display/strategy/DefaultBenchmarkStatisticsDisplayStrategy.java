package org.sunso.test.perf.display.strategy;

import org.sunso.test.perf.display.BenchmarkStatisticsDisplayFactory;
import org.sunso.test.perf.report.AbstractBenchmarkStatisticsReport;

/**
 * 统计结果的默认显示策略
 */
public class DefaultBenchmarkStatisticsDisplayStrategy implements BenchmarkStatisticsDisplayStrategy {
    @Override
    public void displayStrategy(AbstractBenchmarkStatisticsReport report) {
        if (report == null) {
            return;
        }
        BenchmarkStatisticsDisplayFactory.getDefault().display(report);
    }
}
