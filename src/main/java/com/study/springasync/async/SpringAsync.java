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
import org.springframework.stereotype.Component;

import java.util.concurrent.Future;

@SpringBootApplication
@Slf4j
public class SpringAsync {

    public static void main(String[] args) {
        try(ConfigurableApplicationContext run = SpringApplication.run(SpringAsync.class, args)){
        };
    }

    // application가 뜨고 바로 실행되는 메소드
    @Bean
    ApplicationRunner run(){
        return args -> {
            log.info("run()");
            Future<String> res = myService.hello();
            log.info(res.get());
            log.info("Exit");
        };
    }

    @Autowired MyService myService;

    @Component
    @EnableAsync
    static class MyService{
        @Async
        public Future<String> hello() throws InterruptedException {
            log.info("hello()");
            Thread.sleep(2000);
            return new AsyncResult<String>("hello");
        }
    }

}
