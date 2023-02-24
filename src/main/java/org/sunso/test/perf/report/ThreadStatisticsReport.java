package org.sunso.test.perf.report;

import org.sunso.test.perf.item.BenchmarkItem;
import org.sunso.test.perf.request.BenchmarkRequest;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/**
 * 每个线程的统计数据报告
 */
public class ThreadStatisticsReport extends AbstractBenchmarkStatisticsReport {
    private Map<String, SingleThreadReport> threadReportMap = new ConcurrentHashMap();

    public void report(BenchmarkItem item, BenchmarkRequest request) {
        SingleThreadReport singleThreadReport = getSingleThreadReport(item.getThreadName());
        singleThreadReport.report(item, request);
    }

    private synchronized SingleThreadReport getSingleThreadReport(String threadName) {
        SingleThreadReport singleThreadReport = threadReportMap.get(threadName);
        if (singleThreadReport == null) {
            singleThreadReport = new SingleThreadReport();
            threadReportMap.put(threadName, singleThreadReport);
        }
        return singleThreadReport;
    }

    private static class SingleThreadReport {
        /**
         * 线程执行次数
         */
        private final AtomicInteger statisticsNum = new AtomicInteger();

        /**
         * 线程总耗时
         */
        private final AtomicLong totalTime = new AtomicLong();
        /**
         * 线程平均耗时
         */
        private volatile double avgTime;
        /**
         * 线程最大耗时时间
         */
        private volatile double maxTime = Double.MIN_VALUE;
        /**
         * 线程最小耗时时间
         */
        private volatile double minTime = Double.MAX_VALUE;
        /**
         * 线程tps
         */
        private volatile double tps;

        public void report(BenchmarkItem item, BenchmarkRequest request) {
            long useNanoTime = item.useNanoTime();
            double useMsTime = item.useMsTime();
            increaseStatisticsNum();
            sumTotalTime(useNanoTime);
            setMaxTime(useMsTime);
            setMinTime(useMsTime);
            setAvgTime();
            setTps();
        }

        private void increaseStatisticsNum() {
            statisticsNum.incrementAndGet();
        }

        private void sumTotalTime(long useTime) {
            totalTime.addAndGet(useTime);
        }

        private void setAvgTime() {
            avgTime = BenchmarkItem.getMsTimeByNano(totalTime.get()) / statisticsNum.get();
        }

        private void setMaxTime(double useMsTime) {
            if (useMsTime > maxTime) {
                maxTime = useMsTime;
            }
        }

        private void setMinTime(double useMsTime) {
            if (useMsTime < minTime) {
                minTime = useMsTime;
            }
        }

        private void setTps() {
            tps = statisticsNum.get() / BenchmarkItem.getSecondTimeByNano(totalTime.get());
        }

        @Override
        public String toString() {
            return "SingleThreadReport{" + "statisticsNum=" + statisticsNum + ", totalTime="
                    + BenchmarkItem.getMsTimeByNano(totalTime.get()) + ", avgTime=" + avgTime + ", maxTime=" + maxTime
                    + ", minTime=" + minTime + ", tps=" + tps + '}';
        }
    }

    @Override
    public String toString() {
        return "ThreadStatisticsReport{" + "threadReportMap=" + threadReportMap + '}';
    }
}
