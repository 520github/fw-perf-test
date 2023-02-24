package org.sunso.test.perf;

import org.sunso.test.perf.bootstrap.SyncBenchmarkBootstrap;
import org.sunso.test.perf.request.BenchmarkRequest;
import org.junit.Assert;
import org.junit.Test;

import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

public class SyncBenchmarkTest {

    @Test
    public void mapPutTest() {
        Map<Integer, Integer> map = new ConcurrentHashMap<>();
        BenchmarkRequest request = SyncBenchmarkBootstrap.create()
                .setTotalProcessingNum(1000)
                .setTotalThreadNum(5)
                .setRemark("map put test")
                .setRunnable(() -> {
                    Random random = new Random();
                    for (int i = 0; i < 1000; i++) {
                        int value = random.nextInt(100000000);
                        map.put(value, value);
                    }
                }).run();
        Assert.assertNotNull(request.getReportList());
        request.getReportList().forEach(report -> {
            Assert.assertEquals(report.getTotalProcessingNum(), request.getTotalProcessingNum());
            Assert.assertEquals(report.getTotalThreadNum(), request.getTotalThreadNum());
            Assert.assertEquals(report.getTotalStatisticsNum(), request.getTotalProcessingNum());
            Assert.assertEquals(report.getRemark(), request.getRemark());
        });
    }
}
