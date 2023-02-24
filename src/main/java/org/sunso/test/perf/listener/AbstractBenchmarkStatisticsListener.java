package org.sunso.test.perf.listener;

import org.sunso.test.perf.request.BenchmarkRequest;
import org.sunso.test.perf.item.BenchmarkItem;
import org.sunso.test.perf.report.AbstractBenchmarkStatisticsReport;

import java.util.concurrent.atomic.AtomicInteger;

public abstract class AbstractBenchmarkStatisticsListener implements BenchmarkStatisticsListener {
    /**
     * 总统计次数
     */
    protected AtomicInteger totalNum = new AtomicInteger();
    /**
     * 所有线程里最小的开始时间
     */
    protected volatile long minBeginTime = Long.MAX_VALUE;
    /**
     * 所有线程里最大的结束时间
     */
    protected volatile long maxEndTime = Long.MIN_VALUE;

    /**
     * 所有线程中耗时最大的时间, end-begin
     */
    protected volatile long useMaxTime = Long.MIN_VALUE;

    /**
     * 所有线程中耗时最小的时间, end-begin
     */
    protected volatile long useMinTime = Long.MAX_VALUE;

    @Override
    public AbstractBenchmarkStatisticsReport statistics(BenchmarkItem item, BenchmarkRequest request) {
        setMinBeginTime(item);
        setMaxEndTime(item);
        setUseMinTime(item);
        setUseMaxTime(item);
        doStatistics(item, request);
        // 最后一个完成的任务
        if (totalNum.incrementAndGet() == request.getTotalProcessingNum()) {
            return doStatisticsEnd(item, request);
        }
        return null;
    }

    /**
     * 处理每个任务完成后的统计
     * 
     * @param item
     * @param request
     */
    protected abstract void doStatistics(BenchmarkItem item, BenchmarkRequest request);

    /**
     * 处理最后一个任务完成后的统计
     * 
     * @param item
     * @param request
     * @return
     */
    protected abstract AbstractBenchmarkStatisticsReport doStatisticsEnd(BenchmarkItem item, BenchmarkRequest request);

    /**
     * 设置所有线程里最小的开始时间
     * 
     * @param item
     */
    protected void setMinBeginTime(BenchmarkItem item) {
        if (item.getStartNanoTime() < minBeginTime) {
            minBeginTime = item.getStartNanoTime();
        }
    }

    /**
     * 设置所有线程里最大的结束时间
     *
     * @param item
     */
    protected void setMaxEndTime(BenchmarkItem item) {
        if (item.getEndNanoTime() > maxEndTime) {
            maxEndTime = item.getEndNanoTime();
        }
    }

    /**
     * 设置所有线程里耗时最小时间
     * 
     * @param item
     */
    protected void setUseMinTime(BenchmarkItem item) {
        if (item.useNanoTime() < useMinTime) {
            useMinTime = item.useNanoTime();
        }
    }

    /**
     * 设置所有线程里耗时最大时间
     * 
     * @param item
     */
    protected void setUseMaxTime(BenchmarkItem item) {
        if (item.useNanoTime() > useMaxTime) {
            useMaxTime = item.useNanoTime();
        }
    }

    /**
     * 获取总共统计次数
     * 
     * @return
     */
    protected int getTotalStatisticsNum() {
        return totalNum.get();
    }

    /**
     * 获取最大结束和最小开始时间之间的总耗时
     * 
     * @return
     */
    protected long getTotalNanoTime() {
        return maxEndTime - minBeginTime;
    }

    protected long getUseMinTime() {
        return useMinTime;
    }

    protected long getUseMaxTime() {
        return useMaxTime;
    }

}
