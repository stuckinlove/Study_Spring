package com.study.springasync.async;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;

/**
 * 옛날 스프링의 비동기 방법
 */
@SpringBootApplication
@Slf4j
public class SpringAsync {

    public static void main(String[] args) {
        try(ConfigurableApplicationContext run = SpringApplication.run(SpringAsync.class, args)){
        };
    }

    @Bean(name = "test_thread")
    ThreadPoolTaskExecutor tx(){
        ThreadPoolTaskExecutor tx = new ThreadPoolTaskExecutor();
        tx.setCorePoolSize(10);  // 1. 먼저 기본
        tx.setQueueCapacity(50); // 2. 코어 스레드가 가득 차면 큐에 대기
        tx.setMaxPoolSize(100); // 3. 큐가 가득 찼을 시 맥스 설정대로 풀 사이즈 늘림(0 또는 설정 안하면 무한대로 설정됨)
//        tx.setThreadNamePrefix("test_thread-"); // thread name
        tx.initialize();
        return tx;
    }

    // application가 뜨고 바로 실행되는 메소드
    @Bean
    ApplicationRunner run(){
        return args -> {
            log.info("run()");
            ListenableFuture<String> f = myService.hello();
            f.addCallback(s -> System.out.println(s), e -> System.out.println(e.getMessage()));
            // 콜백헬 우려.
            log.info("exit");

//            log.info("Exit: " + f.isDone()); // future
//            log.info("Result: " + f.get()); // future
        };
    }

    @Autowired MyService myService;

    @Component
    @EnableAsync
    static class MyService{
        @Async(value = "tx")
        public ListenableFuture<String> hello() throws InterruptedException {
            log.info("hello()");
            Thread.sleep(2000);
            return new AsyncResult<String>("hello");
        }
    }
// 1.21.55


}
