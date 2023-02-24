package org.sunso.test.perf.report;

public abstract class AbstractBenchmarkStatisticsReport {
    private int totalProcessingNum;
    private int totalThreadNum;
    private String remark;

    private int totalStatisticsNum;

    public int getTotalProcessingNum() {
        return totalProcessingNum;
    }

    public AbstractBenchmarkStatisticsReport setTotalProcessingNum(int totalProcessingNum) {
        this.totalProcessingNum = totalProcessingNum;
        return this;
    }

    public int getTotalThreadNum() {
        return totalThreadNum;
    }

    public AbstractBenchmarkStatisticsReport setTotalThreadNum(int totalThreadNum) {
        this.totalThreadNum = totalThreadNum;
        return this;
    }

    public String getRemark() {
        return remark;
    }

    public AbstractBenchmarkStatisticsReport setRemark(String remark) {
        this.remark = remark;
        return this;
    }

    public int getTotalStatisticsNum() {
        return totalStatisticsNum;
    }

    public AbstractBenchmarkStatisticsReport setTotalStatisticsNum(int totalStatisticsNum) {
        this.totalStatisticsNum = totalStatisticsNum;
        return this;
    }

    @Override
    public String toString() {
        return "BenchmarkStatisticsReport{" + "totalProcessingNum=" + totalProcessingNum + ", totalThreadNum="
                + totalThreadNum + ", remark='" + remark + '\'' + ", totalStatisticsNum=" + totalStatisticsNum + '}';
    }
}
