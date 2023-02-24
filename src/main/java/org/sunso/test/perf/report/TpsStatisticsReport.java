package org.sunso.test.perf.report;

import org.sunso.test.perf.item.BenchmarkItem;
import org.sunso.test.perf.request.BenchmarkRequest;

/**
 * 所有线程的统计数据报告
 */
public class TpsStatisticsReport extends AbstractBenchmarkStatisticsReport {
    /**
     * 总耗时
     */
    private double totalTime;
    /**
     * 总平均耗时
     */
    private double avgTime;
    /**
     * 最小耗时时间
     */
    private double minTime;
    /**
     * 最大耗时时间
     */
    private double maxTime;
    /**
     * 总tps
     */
    private double tps;

    public static TpsStatisticsReport create() {
        return new TpsStatisticsReport();
    }

    public TpsStatisticsReport report(BenchmarkItem item, BenchmarkRequest request, long totalNanoTime) {
        setTotalTime(BenchmarkItem.getMsTimeByNano(totalNanoTime));
        setAvgTime(getTotalTime() / request.getTotalProcessingNum());
        setTps(request.getTotalProcessingNum() / BenchmarkItem.getSecondTimeByNano(totalNanoTime));
        setTotalProcessingNum(request.getTotalProcessingNum());
        setTotalThreadNum(request.getTotalThreadNum());
        setRemark(request.getRemark());
        return this;
    }

    public double getTotalTime() {
        return totalTime;
    }

    public TpsStatisticsReport setTotalTime(double totalTime) {
        this.totalTime = totalTime;
        return this;
    }

    public double getAvgTime() {
        return avgTime;
    }

    public TpsStatisticsReport setAvgTime(double avgTime) {
        this.avgTime = avgTime;
        return this;
    }

    public double getMinTime() {
        return minTime;
    }

    public TpsStatisticsReport setMinTime(double minTime) {
        this.minTime = minTime;
        return this;
    }

    public double getMaxTime() {
        return maxTime;
    }

    public TpsStatisticsReport setMaxTime(double maxTime) {
        this.maxTime = maxTime;
        return this;
    }

    public double getTps() {
        return tps;
    }

    public TpsStatisticsReport setTps(double tps) {
        this.tps = tps;
        return this;
    }

    // @Override
    // public String toString() {
    // return super.toString() + "--" + "TpsStatisticsReport{" + "totalTime=" + totalTime + ", avgTime=" + avgTime
    // + ", tps=" + tps + '}';
    // }

    @Override
    public String toString() {
        return super.toString() + "--TpsStatisticsReport{" + "totalTime=" + totalTime + ", avgTime=" + avgTime
                + ", minTime=" + minTime + ", maxTime=" + maxTime + ", tps=" + tps + '}';
    }
}
