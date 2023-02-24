package org.sunso.test.perf.request;

import org.sunso.test.perf.display.strategy.BenchmarkStatisticsDisplayStrategy;
import org.sunso.test.perf.listener.BenchmarkStatisticsListener;
import org.sunso.test.perf.report.AbstractBenchmarkStatisticsReport;

import java.util.ArrayList;
import java.util.List;

public class BenchmarkRequest {
    /**
     * 总执行次数
     */
    private int totalProcessingNum;
    /**
     * 总线程数
     */
    private int totalThreadNum;
    /**
     * 执行任务
     */
    private Runnable runnable;
    /**
     * 任务执行完之后的监听处理器
     */
    private List<BenchmarkStatisticsListener> listeners;
    /**
     * 任务备注
     */
    private String remark;
    /**
     * 性能测试完成后，统计数据的显示策略
     */
    private BenchmarkStatisticsDisplayStrategy displayStrategy;

    /**
     * 统计后的报告结果列表
     */
    private List<AbstractBenchmarkStatisticsReport> reportList;

    public static BenchmarkRequest create() {
        return new BenchmarkRequest();
    }

    public int getTotalProcessingNum() {
        return totalProcessingNum;
    }

    public BenchmarkRequest setTotalProcessingNum(int totalProcessingNum) {
        this.totalProcessingNum = totalProcessingNum;
        return this;
    }

    public int getTotalThreadNum() {
        return totalThreadNum;
    }

    public BenchmarkRequest setTotalThreadNum(int totalThreadNum) {
        this.totalThreadNum = totalThreadNum;
        return this;
    }

    public Runnable getRunnable() {
        return runnable;
    }

    public BenchmarkRequest setRunnable(Runnable runnable) {
        this.runnable = runnable;
        return this;
    }

    public List<BenchmarkStatisticsListener> getListeners() {
        return listeners;
    }

    public BenchmarkRequest setListeners(List<BenchmarkStatisticsListener> listeners) {
        this.listeners = listeners;
        return this;
    }

    public String getRemark() {
        return remark;
    }

    public BenchmarkRequest setRemark(String remark) {
        this.remark = remark;
        return this;
    }

    public BenchmarkStatisticsDisplayStrategy getDisplayStrategy() {
        return displayStrategy;
    }

    public BenchmarkRequest setDisplayStrategy(BenchmarkStatisticsDisplayStrategy displayStrategy) {
        this.displayStrategy = displayStrategy;
        return this;
    }

    public AsyncBenchmarkRequest convertToAsyncBenchmarkRequest() {
        return (AsyncBenchmarkRequest) this;
    }

    public List<AbstractBenchmarkStatisticsReport> getReportList() {
        return reportList;
    }

    public void addReport(AbstractBenchmarkStatisticsReport report) {
        if (report == null) {
            return;
        }
        if (reportList == null) {
            reportList = new ArrayList<>();
        }
        reportList.add(report);
    }
}
