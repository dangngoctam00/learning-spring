package ngtam.learningspring.asycnannotation;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.cloud.sleuth.instrument.async.LazyTraceThreadPoolTaskExecutor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Configuration
@Slf4j
@RequiredArgsConstructor
public class ExecutorConfig {

    private final AsyncThreadPoolProperties properties;
    private final BeanFactory beanFactory;

    @Bean(name = "asyncExecutor")
    public Executor asyncExecutor() throws InterruptedException {
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();

        taskExecutor.setCorePoolSize(properties.getCoreSize());
        taskExecutor.setMaxPoolSize(properties.getMaxSize());
        taskExecutor.setQueueCapacity(properties.getQueueSize());
        taskExecutor.setThreadNamePrefix(properties.getPrefixName());
        taskExecutor.setPrestartAllCoreThreads(properties.isPreStart());
        taskExecutor.setRejectedExecutionHandler(properties.getPolicy().getHandler());
        taskExecutor.setBeanName("asyncExecutor");
        taskExecutor.initialize();
        log.info("Initialize ThreadPoolTaskExecutor: {}", properties);
//        new LazyTraceThreadPoolTaskExecutor()
//        printStats(taskExecutor.getThreadPoolExecutor());

//        AtomicInteger atomicInteger = new AtomicInteger();
//        IntStream.rangeClosed(1, 20).forEach(i -> {
//
//            try {
//                TimeUnit.SECONDS.sleep(1);
//
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//
//            }
//
//            int id = atomicInteger.incrementAndGet();
//
//            try {
//                taskExecutor.submit(() -> {
//                    log.info("{} started", id);
//
//                    //每个任务耗时10秒
//                    try {
//                        TimeUnit.SECONDS.sleep(10);
//                    } catch (InterruptedException e) {
//
//                    }
//                    log.info("{} finished", id);
//                });
//
//            } catch (Exception ex) {
//
//                //提交出现异常的话，打印出错信息并为计数器减一
//                log.error("error submitting task {}", id, ex);
//                atomicInteger.decrementAndGet();
//
//            }
//
//        });
//
//        TimeUnit.SECONDS.sleep(60);

        return taskExecutor;
    }

    private void printStats(ThreadPoolExecutor threadPool) {

        Executors.newSingleThreadScheduledExecutor().scheduleAtFixedRate(() -> {
            log.info("=========================");
            log.info("Pool Size: {}", threadPool.getPoolSize());
            log.info("Active Threads: {}", threadPool.getActiveCount());
            log.info("Number of Tasks Completed: {}", threadPool.getCompletedTaskCount());
            log.info("Number of Tasks in Queue: {}", threadPool.getQueue().size());
            log.info("=========================");

        }, 0, 30, TimeUnit.SECONDS);

    }


    @PostConstruct
    public void batchJob() {
        Runnable task = () -> {
            log.info("Run task");
        };
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(5);
        executor.scheduleAtFixedRate(task, 0, 10, TimeUnit.SECONDS);
    }

}
