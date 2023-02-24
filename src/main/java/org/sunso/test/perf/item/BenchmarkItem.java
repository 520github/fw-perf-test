package org.sunso.test.perf.item;

/**
 * 线程每次执行任务的相关数据
 */
public class BenchmarkItem {
    private final static double MILLI_SECOND_UNIT = 1000000.0D;
    private final static double SECOND_UNIT = 1000000000.0D;

    /**
     * 当前次数
     */
    private int currentNum;
    /**
     * 线程执行开始时间
     */
    private long startNanoTime;
    /**
     * 线程执行结束时间
     */
    private long endNanoTime;

    private String threadName;

    public BenchmarkItem(int currentNum) {
        this.currentNum = currentNum;
    }

    public void toStart() {
        this.startNanoTime = System.nanoTime();
    }

    public void toEnd() {
        this.endNanoTime = System.nanoTime();
    }

    public int getCurrentNum() {
        return currentNum;
    }

    public long getStartNanoTime() {
        return startNanoTime;
    }

    public long getEndNanoTime() {
        return endNanoTime;
    }

    public String getThreadName() {
        return threadName;
    }

    public void setThreadName(String threadName) {
        this.threadName = threadName;
    }

    public long useNanoTime() {
        return endNanoTime - startNanoTime;
    }

    public double useMsTime() {
        return useNanoTime() / MILLI_SECOND_UNIT;
    }

    public static double getMsTimeByNano(long nano) {
        return nano / MILLI_SECOND_UNIT;
    }

    public static double getSecondTimeByNano(long nano) {
        return nano / SECOND_UNIT;
    }

    @Override
    public String toString() {
        return "BenchmarkItem{" + "currentNum=" + currentNum + ", startNanoTime=" + startNanoTime + ", endNanoTime="
                + endNanoTime + ", threadName='" + threadName + '\'' + '}';
    }
}
