package org.sunso.test.perf.display;

public class BenchmarkStatisticsDisplayFactory {
    public static BenchmarkStatisticsDisplay getDefault() {
        return new SystemPrintlnBenchmarkStatisticsDisplay();
    }
}
