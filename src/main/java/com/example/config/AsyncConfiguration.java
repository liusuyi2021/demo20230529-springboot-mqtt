package com.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName: AsyncConfiguration
 * @Description: 异步线程配置类
 * @Author: Administrator
 * @Date: 2023年02月03日 11:25
 * @Version: 1.0
 **/
@Configuration
@EnableAsync(proxyTargetClass = true)
public class AsyncConfiguration {
    @Bean("mqtt")
    public ThreadPoolTaskExecutor executor(){
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        //配置核心线程数
        executor.setCorePoolSize(40);
        //配置最大线程数
        executor.setMaxPoolSize(1000);
        //配置队列大小
        executor.setQueueCapacity(500);
        executor.setAllowCoreThreadTimeOut(true);
        //线程的名称前缀
        executor.setThreadNamePrefix("Executor-");
        //线程活跃时间（秒）
        executor.setKeepAliveSeconds(30);
        //等待所有任务结束后再关闭线程池
        executor.setWaitForTasksToCompleteOnShutdown(true);
        //设置拒绝策略
        //executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        //执行初始化
        executor.initialize();
        return executor;
    }
}
