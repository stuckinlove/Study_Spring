package com.study.springasync.async;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureAdapter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@SpringBootApplication
@Slf4j
public class Web_SpringAsync {

    @RestController
    static class MyController{
        @GetMapping("/async")
        public String async(){
            return null;
        }
    }

    @Component
    @EnableAsync
    static class MyService{
        public ListenableFuture<String> hello() throws InterruptedException{
            log.info("hello()");
            Thread.sleep(2000);
            return new AsyncResult<String>("Hello");
        }

    }

    public static void main(String[] args) {
        SpringApplication.run(Web_SpringAsync.class, args);
    }
}
