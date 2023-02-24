package org.sunso.test.perf.listener;

import java.util.Arrays;
import java.util.List;

public class Listeners {
    public static List<BenchmarkStatisticsListener> getDefaults() {
        return Arrays.asList(new TpsStatisticsListener(), new ThreadStatisticsListener());
    }
}
