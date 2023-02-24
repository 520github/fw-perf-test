package org.sunso.test.perf.display;

import org.sunso.test.perf.report.AbstractBenchmarkStatisticsReport;

/**
 * 以系统打印方式显示统计结果
 */
public class SystemPrintlnBenchmarkStatisticsDisplay implements BenchmarkStatisticsDisplay {
    @Override
    public void display(AbstractBenchmarkStatisticsReport report) {
        System.out.println("display:" + report);
    }
}
