java 性能测试框架
======================

#### 前言
* 对java代码段进行性能测试，可以配置执行总次数和线程数。
* 默认提供全局的总耗时、平均耗时、最大耗时、最小耗时、tps，以及线程级的总耗时、平均耗时、最大耗时、最小耗时、tps的数据统计，可自行扩展实现自定义统计逻辑。
* 默认提供控制台打印统计数据，可自行扩展接口，把统计数据写入数据库或发送邮件都可以。
* 支持异步或同步的数据统计。


#### 使用例子

###### 数据统计以同步方式进行
~~~~
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
~~~~
~~~~
控制台打印结果
display:BenchmarkStatisticsReport{totalProcessingNum=1000, totalThreadNum=5, remark='test', totalStatisticsNum=1000}--TpsStatisticsReport{totalTime=182.533083, avgTime=0.182533083, minTime=0.083334, maxTime=49.927125, tps=5478.458937769653}

display:ThreadStatisticsReport{threadReportMap={pool-2-thread-2=SingleThreadReport{statisticsNum=222, totalTime=180.969841, avgTime=0.815179463963964, maxTime=46.618334, minTime=0.086166, tps=1226.7237390124026}, pool-2-thread-1=SingleThreadReport{statisticsNum=170, totalTime=181.086758, avgTime=1.0652162235294118, maxTime=47.113833, minTime=0.083334, tps=938.7765393646288}, pool-2-thread-5=SingleThreadReport{statisticsNum=243, totalTime=178.751126, avgTime=0.735601341563786, maxTime=47.2255, minTime=0.085917, tps=1359.4319959696365}, pool-2-thread-4=SingleThreadReport{statisticsNum=176, totalTime=180.500964, avgTime=1.0255736590909093, maxTime=47.630875, minTime=0.093708, tps=975.0640445333023}, pool-2-thread-3=SingleThreadReport{statisticsNum=189, totalTime=175.420715, avgTime=0.9281519312169312, maxTime=49.927125, minTime=0.084916, tps=1077.4098144566335}}}

~~~~


###### 数据统计以异步方式进行
~~~~
        Map<Integer, Integer> map = new ConcurrentHashMap<>();
        BenchmarkRequest request = AsyncBenchmarkBootstrap.create()
                .setStatisticsExecuteService(Executors.newFixedThreadPool(1))
                .setTotalProcessingNum(1000)
                .setTotalThreadNum(5)
                .setRemark("test")
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
~~~~

~~~~
控制台打印结果
display:BenchmarkStatisticsReport{totalProcessingNum=1000, totalThreadNum=5, remark='map put test', totalStatisticsNum=1000}--TpsStatisticsReport{totalTime=214.618458, avgTime=0.214618458, minTime=0.083541, maxTime=46.852333, tps=4659.431482822414}
display:ThreadStatisticsReport{threadReportMap={pool-1-thread-1=SingleThreadReport{statisticsNum=138, totalTime=201.943511, avgTime=1.463358775362319, maxTime=46.852333, minTime=0.0965, tps=683.3594172778347}, pool-1-thread-3=SingleThreadReport{statisticsNum=199, totalTime=162.24054, avgTime=0.815279095477387, maxTime=26.463125, minTime=0.087625, tps=1226.573826738989}, pool-1-thread-2=SingleThreadReport{statisticsNum=246, totalTime=206.494676, avgTime=0.8394092520325204, maxTime=44.825792, minTime=0.083541, tps=1191.3140075340248}, pool-1-thread-5=SingleThreadReport{statisticsNum=178, totalTime=202.887125, avgTime=1.139815308988764, maxTime=46.297708, minTime=0.093917, tps=877.3351192196153}, pool-1-thread-4=SingleThreadReport{statisticsNum=239, totalTime=202.091509, avgTime=0.8455711673640167, maxTime=45.933708, minTime=0.084959, tps=1182.6325667151111}}}
~~~~

#### 具体参考单元测试类
~~~~
org.sunso.test.perf.AsyncBenchmarkTest
org.sunso.test.perf.SyncBenchmarkTest
~~~~
