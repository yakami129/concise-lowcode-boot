package com.github.yakami.library.infrastructure.common.utils.thread;

import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.*;

@SuppressWarnings({"unused"})
public class ThreadPoolUtils {

    /**
     * spring默认支持的线程池
     * 注意，
     * 线程池使用完毕之后需要手动调用 executor.shutdown();
     */
    public static ThreadPoolTaskExecutor createAsyncExecutor(int corePoolSize, int maxPoolSize, int queueCapacity) {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        // 设置核心线程数
        executor.setCorePoolSize(corePoolSize);
        // 设置最大线程数
        executor.setMaxPoolSize(maxPoolSize);
        // 设置队列容量
        executor.setQueueCapacity(queueCapacity);
        // 设置线程活跃时间（秒）
        executor.setKeepAliveSeconds(300);
        // 设置默认线程名称
        executor.setThreadNamePrefix("thread-");
        // 设置拒绝策略rejection-policy：当pool已经达到max size的时候，如何处理新任务 CALLER_RUNS：不在新线程中执行任务，而是有调用者所在的线程来执行
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        // 等待所有任务结束后再关闭线程池
        executor.setWaitForTasksToCompleteOnShutdown(true);
        executor.initialize();
        return executor;
    }

    /**
     * JDK内置的线程池
     * 注意，
     * 线程池使用完毕之后需要手动调用  threadPoolExecutor.shutdown();
     */
    public static ThreadPoolExecutor createDefaultExecutor(int corePoolSize, int maxPoolSize, int queueCapacity, ThreadFactory threadFactory) {
        if (threadFactory == null) {
            threadFactory = Executors.defaultThreadFactory();
        }

        // 创建线程池用于存储线程任务的队列
        BlockingQueue<Runnable> blockingQueue = queueCapacity > 0
                ? new LinkedBlockingQueue<>(queueCapacity)
                : new SynchronousQueue<>();
        // 创建线程池对象
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                // 设置核心线程数
                corePoolSize,
                // 设置最大线程数
                maxPoolSize,
                // 设置线程活跃时间（秒）
                300,
                // 设置线程活跃的时间单位：秒
                TimeUnit.SECONDS,
                // 设置线程池队列
                blockingQueue);
        // 设置拒绝策略rejection-policy：当pool已经达到max size的时候，如何处理新任务 CALLER_RUNS：不在新线程中执行任务，而是有调用者所在的线程来执行
        threadPoolExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        // 设置用于创建新线程的线程工厂
        threadPoolExecutor.setThreadFactory(threadFactory);
        return threadPoolExecutor;
    }

    /**
     *
     * 使用fork / join框架可以加速处理大型任务，但要实现这一结果，应遵循一些指导原则：
     * 1、使用尽可能少的线程池 - 在大多数情况下，最好的决定是为每个应用程序或系统使用一个线程池
     * 2、如果不需要特定调整，请使用默认的公共线程池
     * 3、使用合理的阈值将ForkJoingTask拆分为子任务
     * 4、避免在 ForkJoingTasks中出现任何阻塞
     * 建议：
     * 使用invokeAll（）方法向ForkJoinPool提交多个任务通常是个好主意
     */
    public static ForkJoinPool createForkJoinPool() {
        // 提供对公共池的引用，公共池是每个ForkJoinTask的默认线程池。根据Oracle的文档，使用预定义的公共池可以减少资源消耗，因为这会阻止为每个任务创建单独的线程池。
        return ForkJoinPool.commonPool();
    }
}
