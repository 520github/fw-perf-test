package org.sunso.test.perf.display.strategy;

/**
 * 统计结果的显示策略入口
 */
public class DisplayStrategys {
    public static BenchmarkStatisticsDisplayStrategy getDefaultDisplayStrategy() {
        return new DefaultBenchmarkStatisticsDisplayStrategy();
    }
}
