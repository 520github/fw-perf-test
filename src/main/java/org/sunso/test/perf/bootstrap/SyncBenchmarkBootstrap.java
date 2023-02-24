package org.sunso.test.perf.bootstrap;

import org.sunso.test.perf.SyncStatisticsBenchmark;
import org.sunso.test.perf.display.strategy.BenchmarkStatisticsDisplayStrategy;
import org.sunso.test.perf.display.strategy.DisplayStrategys;
import org.sunso.test.perf.listener.BenchmarkStatisticsListener;
import org.sunso.test.perf.listener.Listeners;
import org.sunso.test.perf.request.BenchmarkRequest;
import org.sunso.test.perf.request.SyncBenchmarkRequest;

import java.util.List;

/**
 * 性能测试-同步计算统计指标-引导入口
 */
public class SyncBenchmarkBootstrap {

    /**
     * 性能测试的请求参数
     */
    protected SyncBenchmarkRequest request;

    public SyncBenchmarkBootstrap() {
        request = SyncBenchmarkRequest.create();
    }

    public static SyncBenchmarkBootstrap create() {
        return new SyncBenchmarkBootstrap();
    }

    /**
     * 设置总处理数
     * 
     * @param totalProcessingNum
     * @return
     */
    public SyncBenchmarkBootstrap setTotalProcessingNum(int totalProcessingNum) {
        request.setTotalProcessingNum(totalProcessingNum);
        return this;
    }

    /**
     * 设置总线程数
     * 
     * @param totalThreadNum
     * @return
     */
    public SyncBenchmarkBootstrap setTotalThreadNum(int totalThreadNum) {
        request.setTotalThreadNum(totalThreadNum);
        return this;
    }

    /**
     * 设置执行的任务
     * 
     * @param runnable
     * @return
     */
    public SyncBenchmarkBootstrap setRunnable(Runnable runnable) {
        request.setRunnable(runnable);
        return this;
    }

    /**
     * 设置任务备注
     * 
     * @param remark
     * @return
     */
    public SyncBenchmarkBootstrap setRemark(String remark) {
        request.setRemark(remark);
        return this;
    }

    /**
     * 设置任务执行完的监听处理器
     * 
     * @param listeners
     * @return
     */
    public SyncBenchmarkBootstrap setListeners(List<BenchmarkStatisticsListener> listeners) {
        request.setListeners(listeners);
        return this;
    }

    /**
     * 设置统计结果的显示策略
     * 
     * @param displayStrategy
     * @return
     */
    public SyncBenchmarkBootstrap setDisplayStrategy(BenchmarkStatisticsDisplayStrategy displayStrategy) {
        request.setDisplayStrategy(displayStrategy);
        return this;
    }

    public BenchmarkRequest run() {
        setDefaultListener();
        setDefaultDisplayStrategy();
        doRun();
        return request;
    }

    /**
     * 开始性能测试
     */
    protected void doRun() {
        new SyncStatisticsBenchmark().benchmark(request);
    }

    /**
     * 设置任务执行完的默认监听处理器
     */
    protected void setDefaultListener() {
        if (request.getListeners() == null) {
            request.setListeners(Listeners.getDefaults());
        }
    }

    /**
     * 设置统计结果的默认显示策略
     */
    protected void setDefaultDisplayStrategy() {
        if (request.getDisplayStrategy() == null) {
            request.setDisplayStrategy(DisplayStrategys.getDefaultDisplayStrategy());
        }
    }

}
