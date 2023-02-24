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

#### 具体参考单元测试类
~~~~
org.sunso.test.perf.AsyncBenchmarkTest
org.sunso.test.perf.SyncBenchmarkTest
~~~~
