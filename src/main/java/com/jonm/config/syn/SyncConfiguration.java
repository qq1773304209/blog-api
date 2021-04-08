package com.jonm.config.syn;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ThreadPoolExecutor;


@Configuration
@EnableAsync
public class SyncConfiguration {
    @Bean(name="sendEmilTaskExecutor")
    public ThreadPoolTaskExecutor sendSimpleMail(){
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        // 核心线程数
        taskExecutor.setCorePoolSize(5);
        // 设置缓存队列
        taskExecutor.setQueueCapacity(50);
        // 线程最大数量（队列满了后才会申请超过核心线程数的线程）
        taskExecutor.setMaxPoolSize(100);
        // 当超过空闲时间，就会销毁超出核心线程外的线程
        taskExecutor.setKeepAliveSeconds(200);
        // 异步方法内部线程名称
        taskExecutor.setThreadNamePrefix("sendSimpleMail-");
        /*
        *     拒绝策略（4种）：
        *
        *       AbortPolicy():      丢弃任务并抛异常Reject。。。
        *       DiscardPolicy():      丢弃不抛异常
        *       DiscardOldestPolicy(): 丢弃队列最前面任务，然后重新尝试执行
        *       CallerRunsPolicy() : 自动重复调用execute()，知道成功
        */
        taskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        taskExecutor.initialize();
        return taskExecutor;
    }
}
